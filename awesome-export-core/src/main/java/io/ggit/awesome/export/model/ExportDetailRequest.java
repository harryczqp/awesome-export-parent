package io.ggit.awesome.export.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * ExportDetail
 *
 * @author harryczq
 */
@ApiModel("标准导出明细请映射关系")
public class ExportDetailRequest {
    @ApiModelProperty("显示列名")
    private String title;
    @ApiModelProperty("字段名")
    private String fieldName;
    @ApiModelProperty("排序 默认0")
    private Integer dataIndex = 0;
    @ApiModelProperty("格式")
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
