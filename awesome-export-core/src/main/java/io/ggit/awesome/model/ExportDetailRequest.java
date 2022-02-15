package io.ggit.awesome.model;

/**
 * ExportDetail
 *
 * @author harryczq
 */
public class ExportDetailRequest {

    private String title;
    private String fieldName;
    private Integer dataIndex = 0;
    private String format;

    public String getTitle() {
        return title;
    }

    public ExportDetailRequest setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getFieldName() {
        return fieldName;
    }

    public ExportDetailRequest setFieldName(String fieldName) {
        this.fieldName = fieldName;
        return this;
    }

    public Integer getDataIndex() {
        return dataIndex;
    }

    public ExportDetailRequest setDataIndex(Integer dataIndex) {
        this.dataIndex = dataIndex;
        return this;
    }

    public String getFormat() {
        return format;
    }

    public ExportDetailRequest setFormat(String format) {
        this.format = format;
        return this;
    }
}
