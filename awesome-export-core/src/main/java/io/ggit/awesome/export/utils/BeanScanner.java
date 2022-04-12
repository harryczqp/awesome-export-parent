package io.ggit.awesome.export.utils;

import io.ggit.awesome.export.model.CanBeExported;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import io.ggit.awesome.export.model.ScannedBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author harryczq
 */
public class BeanScanner implements ApplicationListener<ApplicationStartedEvent> {

    private static final Logger log = LoggerFactory.getLogger(BeanScanner.class);

    /**
     * 配置需要扫描的包
     */
    @Value("${beanScanner.package:io.ggit.awesome}")
    private String scannedPackage;

    /**
     * 扫描到的包
     */
    protected static final Map<String, ScannedBean> scannedBeanMap = MapUtil.newHashMap();

    /**
     * 获取注册的bean
     *
     * @param key key
     * @return bean
     */
    public static ScannedBean getBeanMap(String key) {
        return scannedBeanMap.getOrDefault(key, null);
    }

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        ConfigurableApplicationContext context = event.getApplicationContext();
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        log.debug("BeanScanner->开始扫描Bean");
        for (String beanName : beanDefinitionNames) {
            Object bean = context.getBean(beanName);
            if (ObjectUtil.isNull(bean) || ObjectUtil.isNull(bean.getClass()) || ObjectUtil.isNull(bean.getClass().getPackage())) {
                log.debug("未找到bean:{}的信息！", beanName);
                continue;
            }
            if (!bean.getClass().getPackage().getName().contains(scannedPackage)) {
                continue;
            }
            Method[] methods = ReflectUtil.getMethods(bean.getClass());
            //移除null及没有标识的方法
            methods = Arrays.stream(methods).filter(f ->
                    ObjectUtil.isNotNull(f) && ObjectUtil.isNotNull(AnnotationUtil.getAnnotation(f, CanBeExported.class))
            ).toArray(Method[]::new);
            for (Method method : methods) {
                Class<?> parameterType = Arrays.stream(method.getParameterTypes()).findFirst().orElse(null);
                ScannedBean scannedBean = new ScannedBean().setBean(bean).setParameterType(parameterType).setMethod(method);
                for (String key : buildKey(scannedBean).stream().filter(CharSequenceUtil::isNotEmpty).toArray(String[]::new)) {
                    scannedBeanMap.put(key, scannedBean);
                }
            }
        }
        log.debug("BeanScanner->扫描到{}个Bean", scannedBeanMap.size());
    }

    /**
     * 构造scannedPackage的key
     *
     * @param scannedBean ScannedBean
     * @return key
     */
    private List<String> buildKey(ScannedBean scannedBean) {
        List<String> keys = new ArrayList<>();
        Class<?> beanClass = scannedBean.getBean().getClass();
        //CanBeScanned 优先
        String scannedValue = AnnotationUtil.getAnnotationValue(scannedBean.getMethod(), CanBeExported.class);
        if (CharSequenceUtil.isNotEmpty(scannedValue)) {
            keys.add(scannedValue);
            return keys;
        }
        //实现方法上扫描
        appendKey(beanClass, scannedBean.getMethod(), keys);
        //接口上扫描
        Class<?>[] interfaces = beanClass.getInterfaces();
        for (Class<?> interfacesClass : interfaces) {
            Method method = Arrays.stream(ReflectUtil.getMethods(interfacesClass)).filter(f -> f.getName().contains(scannedBean.getMethod().getName())).findFirst().orElse(null);
            appendKey(interfacesClass, method, keys);
        }
        return keys;
    }

    private void appendKey(Class<?> clazz, Method method, List<String> keys) {
        if (clazz == null || method == null || keys == null) {
            return;
        }
        RequestMapping requestMapping = AnnotationUtil.getAnnotation(clazz, RequestMapping.class);
        String[] requestValues = requestMapping == null ? new String[]{""} : requestMapping.value();
        PostMapping postMapping = AnnotationUtil.getAnnotation(method, PostMapping.class);
        String[] postValues = postMapping == null ? new String[]{""} : postMapping.value();
        for (String requestValue : requestValues) {
            for (String postValue : postValues) {
                if (CharSequenceUtil.isNotEmpty(postValue)) {
                    keys.add(CharSequenceUtil.format("{}{}", requestValue, CharSequenceUtil.addPrefixIfNot(postValue, "/")));
                }
            }
        }
    }

}
