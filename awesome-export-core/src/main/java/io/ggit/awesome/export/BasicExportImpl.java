package io.ggit.awesome.export;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import io.ggit.awesome.export.model.ExportRequest;
import io.ggit.awesome.export.model.ScannedBean;
import io.ggit.awesome.export.utils.BasicUtil;
import io.ggit.awesome.export.utils.ExcelGeneratorUtil;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * @author harryczq
 */
public class BasicExportImpl {




    public File exportFile(Map<String, Object> request) throws InvocationTargetException, IllegalAccessException {
        ExportRequest exportRequest = BasicUtil.castToBean(request, ExportRequest.class);
        ScannedBean beanMap = exportRequest
                .getScannedBean();
        Object parameter = BasicUtil.castToBean(request, beanMap.getParameterType());
        Object result = beanMap.getMethod().invoke(beanMap.getBean(), parameter);
        List<?> list = BasicUtil.castList(result, BasicUtil.getMethodReturnListDefaultType(beanMap.getMethod()));
        File file = ExcelGeneratorUtil.writeExcel(list, exportRequest.getExportDetail());
        return file;

    }

}
