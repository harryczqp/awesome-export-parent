package io.ggit.awesome.export;

import io.ggit.awesome.export.model.ExportServiceDefinition;

/**
 * 导出服务处理器
 *
 * @author qingluo
 * @since 2023/6/1
 */
public interface ExportServiceResolver {
    /**
     * 给定service（instance）,生成ExportServiceDefinition
     *
     * @param service 实例
     * @return {@link ExportServiceDefinition}
     */
    ExportServiceDefinition resolve(Object service);
}
