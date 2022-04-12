package io.ggit.awesome.export.model;

import java.lang.annotation.*;

/**
 * CanBeExported
 *
 * @author harryczq
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CanBeExported {
    /**
     * 带有以下标签的可自动生成导出方法的key
     *
     * @return 导出方法的key
     */
    String value() default "";
}