package io.ggit.awesome.export.model;

import java.lang.reflect.Method;

/**
 * BeanInfos
 *
 * @author harryczq
 */
public class ScannedBean {
    private Class<?> parameterType;
    private Object bean;
    private Method method;

    public Class<?> getParameterType() {
        return parameterType;
    }

    public ScannedBean setParameterType(Class<?> parameterType) {
        this.parameterType = parameterType;
        return this;
    }

    public Object getBean() {
        return bean;
    }

    public ScannedBean setBean(Object bean) {
        this.bean = bean;
        return this;
    }

    public Method getMethod() {
        return method;
    }

    public ScannedBean setMethod(Method method) {
        this.method = method;
        return this;
    }
}
