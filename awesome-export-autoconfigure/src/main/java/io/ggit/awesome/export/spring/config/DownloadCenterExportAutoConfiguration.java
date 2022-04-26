package io.ggit.awesome.export.spring.config;

import io.ggit.awesome.export.BasicExportImpl;
import io.ggit.awesome.export.utils.BeanScanner;
import org.springframework.context.annotation.Bean;

/**
 * DownloadCenterExportAutoConfiguration
 * @author harryczqp
 */
public class DownloadCenterExportAutoConfiguration {
    @Bean
    public BeanScanner beanScanner() {
        return new BeanScanner();
    }

    @Bean
    public BasicExportImpl basicExport() {
        return new BasicExportImpl();
    }
}
