package io.ggit.awesome.export.spring.config;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ArrayUtil;
import io.ggit.awesome.export.utils.BeanScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

/**
 * DownloadCenterExportRegistrar
 *
 * @author harryczq
 */
public class DownloadCenterExportRegistrar implements ImportBeanDefinitionRegistrar {
    private static final Logger log = LoggerFactory.getLogger(DownloadCenterExportRegistrar.class);

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(EnableDownloadCenterExport.class.getName()));
        String[] values = attributes != null ? attributes.getStringArray("value") : new String[0];
        String strValues = ArrayUtil.join(values, ",");
        if (CharSequenceUtil.isNotEmpty(strValues)) {
            log.debug("DownloadCenterExportRegistrar-{}！", values);
        }
        BeanRegistrationUtil.registerBeanDefinitionIfNotExists(registry, BeanScanner.class.getName(), BeanScanner.class);
        log.debug("DownloadCenterExportRegistrar do success！");
    }
}
