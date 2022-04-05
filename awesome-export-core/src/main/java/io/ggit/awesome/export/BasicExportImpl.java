package io.ggit.awesome.export;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ObjectUtil;
import io.ggit.awesome.export.model.ExportRequest;
import io.ggit.awesome.export.model.ScannedBean;
import io.ggit.awesome.export.utils.BasicUtil;
import io.ggit.awesome.export.utils.ExcelGeneratorUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
     * 标准化日志
     */
    private static final String FORMAT_LOG_STRING = "通用导出接口-{}\r\n{}";

    /**
     * 下载中心-导出为Base64
     * 下一步需支持异步
     *
     * @param request 请求信息
     * @return Base64
     */
    @ApiOperation("下载中心-导出为Base64")
    @PostMapping("export2Base64")
    public String export2Base64(@RequestBody Map<String, Object> request) {
        File file = exportFile(request);
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
    private File exportFile(Map<String, Object> request)  {
        ExportRequest exportRequest = BasicUtil.castToBean(request, ExportRequest.class);
        ScannedBean beanMap = exportRequest
                .getScannedBean();
        Object parameter = BasicUtil.castToBean(request, beanMap.getParameterType());
        Object result = null;
        try {
            result = beanMap.getMethod().invoke(beanMap.getBean(), parameter);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        List<?> list = BasicUtil.castList(result, BasicUtil.getMethodReturnListDefaultType(beanMap.getMethod()));
        File file = ExcelGeneratorUtil.writeExcel(list, exportRequest.getExportDetail());
        return file;

    }

}
