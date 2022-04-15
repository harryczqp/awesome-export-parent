package io.ggit.awesome.export.model;


import java.util.List;

/**
 * ExportRequestTemplate
 *
 * @author harryczq
 */
public class ExportRequestTemplate {
    /**
     * 接口地址
     */
    private String url;
    /**
     * 导出文件名
     */
    private String exportFileName;
    /**
     * 标准导出明细请映射关系
     */
    private List<ExportDetailRequest> exportDetail;

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

    public List<ExportDetailRequest> getExportDetail() {
        return exportDetail;
    }

    public ExportRequestTemplate setExportDetail(List<ExportDetailRequest> exportDetail) {
        this.exportDetail = exportDetail;
        return this;
    }
}
