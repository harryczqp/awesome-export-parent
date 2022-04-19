package io.ggit.awesome.export.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * ExportRequestTemplate
 *
 * @author harryczq
 */
@ApiModel("标准导出请求模板")
public class ExportRequestTemplate {
    /**
     * 接口地址
     */
    @ApiModelProperty("接口地址")
    private String url;
    /**
     * 导出文件名
     */
    @ApiModelProperty("导出文件名")
    private String exportFileName;
    /**
     * 标准导出明细请映射关系
     */
    @ApiModelProperty("标准导出明细请映射关系")
    private List<ExportDetailRequest> exportDetail;

    public List<ExportDetailRequest> getExportDetail() {
        return exportDetail;
    }

    public ExportRequestTemplate setExportDetail(List<ExportDetailRequest> exportDetail) {
        this.exportDetail = exportDetail;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public ExportRequestTemplate setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getExportFileName() {
        return exportFileName;
    }

    public ExportRequestTemplate setExportFileName(String exportFileName) {
        this.exportFileName = exportFileName;
        return this;
    }
}
