package io.ggit.awesome.export.spring.config;

import io.ggit.awesome.export.model.CanBeExported;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

/**
 * @author qingluo
 * @since 2023/6/1
 */
public class AwesomeExportServiceDiscover implements ApplicationContextAware, InitializingBean {

    private ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(CanBeExported.class);
    }
}
