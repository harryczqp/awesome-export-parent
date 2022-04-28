package io.ggit.awesome.export.spring.config;

import io.ggit.awesome.export.BasicExportImpl;
import io.ggit.awesome.export.utils.BeanScanner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * DownloadCenterExportAutoConfiguration
 *
 * @author harryczqp
 */
public class DownloadCenterExportAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean(BeanScanner.class)
    public BeanScanner beanScanner() {
        return new BeanScanner();
    }

    @Bean
    @ConditionalOnBean(BeanScanner.class)
    @ConditionalOnMissingBean(BasicExportImpl.class)
    public BasicExportImpl basicExport() {
        return new BasicExportImpl();
    }
}
