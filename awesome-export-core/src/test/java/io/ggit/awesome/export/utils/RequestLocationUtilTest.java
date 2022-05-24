package io.ggit.awesome.export.utils;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ReflectUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.List;

/**
 * RequestLocationUtilTest
 *
 * @author harryczq
 */
public class RequestLocationUtilTest {
    @Test
    public void test() {
        Method method = ReflectUtil.getMethod(IRequestLocationUtilTest.class, "getData");
        List<String> location = RequestLocationUtil.getLocationByMethod(method, RequestLocationUtil.RequestMethod.GET);
        System.out.println(CharSequenceUtil.join(",", location));
        Assert.assertTrue(location.size() > 0);
        location = RequestLocationUtil.getLocationByMethod(method, RequestLocationUtil.RequestMethod.POST);
        System.out.println(CharSequenceUtil.join(",", location));
        Assert.assertTrue(location.size() > 0);
    }

    @RequestMapping({"/test1", "/test2"})
    public interface IRequestLocationUtilTest {
        @PostMapping({"p1", "p2"})
        @GetMapping({"g1", "g2"})
        void getData();
    }
}