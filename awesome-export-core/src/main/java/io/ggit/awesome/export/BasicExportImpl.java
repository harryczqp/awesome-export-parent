package io.ggit.awesome.export;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ObjectUtil;
import io.ggit.awesome.export.model.ExportRequest;
import io.ggit.awesome.export.model.ScannedBean;
import io.ggit.awesome.export.utils.BasicUtil;
import io.ggit.awesome.export.utils.ExcelGeneratorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * @author harryczq
 */
public class BasicExportImpl {

    private static final Logger log = LoggerFactory.getLogger(BasicExportImpl.class);


    /**
     * 下载中心-导出为Base64
     * 下一步需支持异步
     *
     * @param request 请求信息
     * @return Base64
     */
    public String export2Base64(Map<String, Object> request) {
        File file = null;
        try {
            file = exportFile(request);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (ObjectUtil.isNull(file)) {
            throw new IllegalArgumentException("文件导出失败！");
        }
        String fileString = BasicUtil.writeToBase64(file);
        FileUtil.del(file);
        return fileString;
    }

    /**
     * 导出成文件
     *
     * @param request 请求信息
     * @return 文件
     */
    private File exportFile(Map<String, Object> request) throws InvocationTargetException, IllegalAccessException {
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
