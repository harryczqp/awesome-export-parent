package io.ggit.awesome.export.utils;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.poi.excel.BigExcelWriter;
import cn.hutool.poi.excel.ExcelUtil;
import io.ggit.awesome.export.model.ExportDetailRequest;

import java.io.File;
import java.util.*;

/**
 * ExcelGeneratorUtil
 *
 * @author harryczq
 */
public class ExcelGeneratorUtil {

    private ExcelGeneratorUtil() {
    }

    /**
     * 临时文件目录
     */
    public static final String SYS_TEM_DIR = System.getProperty("java.io.tmpdir") + File.separator;

    public static <T> File writeExcel(List<T> data, List<ExportDetailRequest> exportDetail) {
        //排序
        Assert.notEmpty(data, "导出数据不能为空！");
        Assert.notEmpty(exportDetail, "字段映射关系不为空！");
        exportDetail.sort(Comparator.comparing(ExportDetailRequest::getDataIndex));
        List<Map<String, Object>> dataList = new ArrayList<>();
        for (T item : data) {
            Map<String, Object> map = new LinkedHashMap<>();
            for (ExportDetailRequest detail : exportDetail) {
                Object value = BeanUtil.getFieldValue(item, detail.getFieldName());
                //日期 后期可以扩展
                if (CharSequenceUtil.isNotEmpty(detail.getFormat()) && detail.getFormat().startsWith("yy")) {
                    value = DateUtil.format(DateUtil.parse(value.toString(), detail.getFormat()), detail.getFormat());
                }
                map.put(detail.getTitle(), value);
            }
            dataList.add(map);
        }
        return writeExcel2TempFile(dataList);
    }


    public static File writeExcel2TempFile(Iterable<?> data) {
        String tempPath = SYS_TEM_DIR + IdUtil.fastSimpleUUID() + ".xlsx";
        //通过工具类创建writer
        File file = new File(tempPath);
        BigExcelWriter writer = ExcelUtil.getBigWriter(file);
        //一次性写出内容，强制输出标题
        writer.write(data, true);
        // 终止后删除临时文件
        writer.close();
        return file;
    }

}
