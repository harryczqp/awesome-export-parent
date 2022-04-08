package io.ggit.awesome.export.model;


import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import io.ggit.awesome.export.utils.BasicUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * ExportRequest
 * @author harryczq
 */
@ApiModel("标准导出请求体")
public class ExportRequest {
    /**
     * 接口地址
     */
    @ApiModelProperty("接口地址")
    private String url;
    /**
     * 导出文件名
     */
    @ApiModelProperty("导出文件名")
    private String fileName;
    /**
     * 标准导出明细请映射关系
     */
    @ApiModelProperty("标准导出明细请映射关系")
    private List<ExportDetailRequest> exportDetail;
    /**
     * 跳过映射关系检查
     */
    @ApiModelProperty("跳过映射关系检查")
    private Boolean skipDetailChecked = Boolean.FALSE;

    /**
     * bean信息
     */
    @ApiModelProperty(value = "跳过映射关系检查", hidden = true)
    private ScannedBean scannedBean;


    public Boolean getSkipDetailChecked() {
        return skipDetailChecked;
    }

    public ExportRequest setSkipDetailChecked(Boolean skipDetailChecked) {
        this.skipDetailChecked = skipDetailChecked;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public ExportRequest setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public ExportRequest setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public List<ExportDetailRequest> getExportDetail() {
        return exportDetail;
    }

    public ExportRequest setExportDetail(List<ExportDetailRequest> exportDetail) {
        this.exportDetail = exportDetail;
        return this;
    }

    /**
     * 标准导出请求体检查
     */
    public ExportRequest checkExportRequestForExport() {
        String formatError = "请检查请求参数【{}】必填！";
        if (CharSequenceUtil.isEmpty(url)) {
            throw new IllegalArgumentException(CharSequenceUtil.format(formatError, "url::接口地址"));
        }
        if (CharSequenceUtil.isEmpty(fileName)) {
            fileName = Convert.toStr(DateUtil.current());
        }
        if (!Boolean.TRUE.equals(skipDetailChecked) && CollUtil.isEmpty(exportDetail)) {
            throw new IllegalArgumentException(CharSequenceUtil.format(formatError, "exportDetail::标准导出明细请映射关系"));
        }
        return this;
    }


    /**
     * 获取注册的bean
     *
     * @return bean
     */
    public ScannedBean getScannedBean() {

        return scannedBean;
    }


    /**
     * 如果不存在映射关系则自动生成
     */
    public ExportRequest ifNotExistAutoGenerateExportDetail() {
        ScannedBean bean = getScannedBean();
        if (CollUtil.isEmpty(exportDetail) && ObjectUtil.isNotNull(bean)) {
            autoGenerateExportDetail(this.scannedBean.getMethod());
        }
        return this;
    }

    /**
     * 自动生成映射关系
     *
     * @param method 方法
     */
    private void autoGenerateExportDetail(Method method) {
        exportDetail = new ArrayList<>();
        Class<?> defaultType = BasicUtil.getMethodReturnListDefaultType(method);
        Field[] fields = ReflectUtil.getFields(defaultType);
        for (Field field : fields) {
            String annotationValue = AnnotationUtil.getAnnotationValue(field, ApiModelProperty.class);
            if (annotationValue == null) {
                continue;
            }
            exportDetail.add(new ExportDetailRequest().setFieldName(field.getName()).setTitle(annotationValue));
        }
    }

}
