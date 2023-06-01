package io.ggit.awesome.export;

import io.ggit.awesome.export.model.ExportServiceDefinition;
import io.ggit.awesome.export.utils.Predications;

import java.lang.reflect.Method;

/**
 * 导出服务处理器抽象
 *
 * @author qingluo
 * @since 2023/6/1
 */
public abstract class AbstractExportServiceResolver implements ExportServiceResolver {
    @Override
    public ExportServiceDefinition resolve(Object service) {
        Class<?> interfaceClass = findServiceInterfaceClass(service);
        Predications.checkArgument(interfaceClass != null, "服务未实现任何接口");
        final Method[] declaredMethods = interfaceClass.getDeclaredMethods();
        Predications.checkArgument(declaredMethods.length !=0, "接口未定义任何方法");
        final String serviceName = interfaceClass.getName();
        // TODO: 2023/6/1 完成方法解析

        return null;
    }

    /**
     * 对于给定的service， 解析一个接口
     *
     * @param service 服务
     * @return 接口
     */
    protected abstract Class<?> findServiceInterfaceClass(Object service);
}
