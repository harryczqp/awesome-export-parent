package io.ggit.awesome.export.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import io.ggit.awesome.export.model.ExportDetailRequest;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * ExcelGeneratorUtilTest
 *
 * @author harryczq
 */
public class ExcelGeneratorUtilTest {
    @Test
    public void writeExcelTest() {
        List<ExcelGeneratorUtilTestData> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add(buildExcelGeneratorUtilTestData());
        }
        List<ExportDetailRequest> req = buildExportDetailRequest();
        File file = ExcelGeneratorUtil.writeExcel(data, req);
        System.out.println(file.getAbsolutePath());
        Assert.assertTrue(FileUtil.exist(file));
        FileUtil.del(file);
    }

    public List<ExportDetailRequest> buildExportDetailRequest() {
        List<ExportDetailRequest> result = new ArrayList<>();
        result.add(new ExportDetailRequest().setDataIndex(1).setTitle("名字").setFieldName("title"));
        result.add(new ExportDetailRequest().setDataIndex(2).setTitle("数值").setFieldName("value"));
        result.add(new ExportDetailRequest().setDataIndex(3).setTitle("时间").setFieldName("time"));
        return result;
    }

    public ExcelGeneratorUtilTestData buildExcelGeneratorUtilTestData() {
        return new ExcelGeneratorUtilTestData()
                .setTime(LocalDateTime.now())
                .setValue(RandomUtil.randomBigDecimal())
                .setTitle(IdUtil.randomUUID());
    }

    class ExcelGeneratorUtilTestData {
        private String title;
        private BigDecimal value;
        private LocalDateTime time;


        public String getTitle() {
            return title;
        }

        public ExcelGeneratorUtilTestData setTitle(String title) {
            this.title = title;
            return this;
        }

        public BigDecimal getValue() {
            return value;
        }

        public ExcelGeneratorUtilTestData setValue(BigDecimal value) {
            this.value = value;
            return this;
        }

        public LocalDateTime getTime() {
            return time;
        }

        public ExcelGeneratorUtilTestData setTime(LocalDateTime time) {
            this.time = time;
            return this;
        }
    }
}
