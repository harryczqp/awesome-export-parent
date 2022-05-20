package io.ggit.awesome.export.utils;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.text.CharSequenceUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 请求地址帮助类
 *
 * @author harryczq
 */
public class RequestLocationUtil {
    /**
     * 请求方法
     */
    public enum RequestMethod {
        /**
         * GET
         */
        GET("GET", GetMapping.class),
        /**
         * POST
         */
        POST("POST", PostMapping.class);

        RequestMethod(String description, Class<? extends Annotation> annotation) {
            this.description = description;
            this.annotation = annotation;
        }

        /**
         * 描述
         */
        private final String description;
        /**
         * 请求注解
         */
        private final Class<? extends Annotation> annotation;
    }

    /**
     * 获取请求地址注解
     *
     * @param method        方法
     * @param requestMethod 请求方法枚举
     * @return 地址信息
     */
    public static List<String> getLocationByMethod(Method method, RequestMethod requestMethod) {
        return getLocationByMethod(method, requestMethod.annotation);
    }

    private static List<String> getLocationByMethod(Method method, Class<? extends Annotation> annotationType) {
        Assert.notNull(method, "method is not null");
        Class<?> clazz = method.getDeclaringClass();
        RequestMapping requestMapping = AnnotationUtil.getAnnotation(clazz, RequestMapping.class);
        String[] requestValues = requestMapping == null ? new String[]{""} : requestMapping.value();
        String[] values = AnnotationUtil.getAnnotationValue(method, annotationType);
        return Arrays.stream(requestValues).map(m ->
                Arrays.stream(values).map(v ->
                                CharSequenceUtil.format("{}{}", m, CharSequenceUtil.addPrefixIfNot(v, "/")))
                        .collect(Collectors.toList())
        ).flatMap(Collection::stream).distinct().collect(Collectors.toList());
    }
}