package io.ggit.awesome.export.model;


import java.util.List;

/**
 * ExportRequest
 * @author harryczq
 */
public class ExportRequest {

    private String url;

    private String fileName;

    private List<ExportDetailRequest> exportDetail;

    private Boolean skipDetailChecked = Boolean.FALSE;

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

    public Boolean getSkipDetailChecked() {
        return skipDetailChecked;
    }

    public ExportRequest setSkipDetailChecked(Boolean skipDetailChecked) {
        this.skipDetailChecked = skipDetailChecked;
        return this;
    }
    private ScannedBean scannedBean;

    /**
     * 获取注册的bean
     *
     * @return bean
     */
    public ScannedBean getScannedBean() {
        return scannedBean;
    }

}
