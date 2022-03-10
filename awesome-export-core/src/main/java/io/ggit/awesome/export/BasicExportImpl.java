package io.ggit.awesome.export;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import io.ggit.awesome.export.model.ExportRequest;
import io.ggit.awesome.export.model.ScannedBean;
import io.ggit.awesome.export.utils.ExcelGeneratorUtil;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author harryczq
 */
public class BasicExportImpl {

    public File exportFile(Map<String, Object> request) throws InvocationTargetException, IllegalAccessException {
        ExportRequest exportRequest = castToBean(request, ExportRequest.class);

        ScannedBean beanMap = exportRequest
                .getScannedBean();
        Object parameter = castToBean(request, beanMap.getParameterType());
            Object result = beanMap.getMethod().invoke(beanMap.getBean(), parameter);
        // TODO: 2022/3/10  getMethod
//            List<?> list = castList(result, beanMap.getMethod());
            File file = ExcelGeneratorUtil.writeExcel(new ArrayList<>(), exportRequest.getExportDetail());
            return file;

    }


    public  <T> T castToBean(Object obj, Class<T> clazz) {
        return JSONUtil.toBean(JSONUtil.parse(obj).toString(), clazz);
    }
    public  <T> List<T> castList(Object obj, Class<T> clazz) {
        List<T> result = new ArrayList<>();
        if (obj instanceof List<?>) {
            for (Object o : (List<?>) obj) {
                result.add(clazz.cast(o));
            }
        }
        return result;
    }
}
