package io.ggit.awesome.export;

/**
 * 默认导出服务处理器
 *
 * @author qingluo
 * @since 2023/6/1
 */
public class DefaultExportServiceResolver extends AbstractExportServiceResolver {
    /**
     * 对于给定的service， 解析一个接口
     *
     * @param service 服务
     * @return 接口
     */
    @Override
    protected Class<?> findServiceInterfaceClass(Object service) {
        // TODO: 2023/6/1 对于给定的service
        return null;
    }
}
