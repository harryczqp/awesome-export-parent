package io.ggit.awesome.export.spring.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * EnableDownloadCenterExport
 * @author harryczq
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({DownloadCenterExportRegistrar.class})
public @interface EnableDownloadCenterExport {
    String[] value() default {""};
}