package io.ggit.awesome.export.spring;


import cn.hutool.core.map.MapUtil;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import io.ggit.awesome.export.model.ScannedBean;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author harryczq
 */
public class BeanScanner implements ApplicationListener<ApplicationStartedEvent> {

    /**
     * 扫描到的包
     */
    protected static final Map<String, ScannedBean> scannedBeanMap = MapUtil.newHashMap();

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        ConfigurableApplicationContext context = event.getApplicationContext();
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String beanName : beanDefinitionNames) {
            Object bean = context.getBean(beanName);
            if (ObjectUtil.isNull(bean) || ObjectUtil.isNull(bean.getClass()) || ObjectUtil.isNull(bean.getClass().getPackage())) {
                continue;
            }

            Method[] methods = ReflectUtil.getMethods(bean.getClass());

            for (Method method : methods) {
                Class<?> parameterType = Arrays.stream(method.getParameterTypes()).findFirst().orElse(null);
                ScannedBean scannedBean = new ScannedBean().setBean(bean).setParameterType(parameterType).setMethod(method);
                for (String key : buildKey(scannedBean).stream().filter(CharSequenceUtil::isNotEmpty).toArray(String[]::new)) {
                    scannedBeanMap.put(key, scannedBean);
                }
            }
        }
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
        // TODO: 2022/2/24  完善
        return keys;
    }
}
