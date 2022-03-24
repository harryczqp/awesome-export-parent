package io.ggit.awesome.export.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.TypeUtil;
import cn.hutool.json.JSONUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * BasicUtil
 *
 * @author harryczq
 */
public class BasicUtil {

    private static final Logger log = LoggerFactory.getLogger(BasicUtil.class);

    private BasicUtil() {
    }

    /**
     * excel转base64字符串
     *
     * @param file file
     * @return base64字符串
     */
    public static String writeToBase64(File file) {
        String base64 = "";

        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            base64 = Base64.encode(new byte[fileInputStream.available()]);
        } catch (IOException e) {
            log.warn("excel转base64字符串失败:{}", e.getMessage());
        }
        return CharSequenceUtil.format("data:application/octet-stream;base64,{}", base64);
    }

    /**
     * 优雅的转化为 list
     *
     * @param obj   对象
     * @param clazz list类型
     * @param <T>   list类型
     * @return list
     */
    public static <T> List<T> castList(Object obj, Class<T> clazz) {
        List<T> result = new ArrayList<>();
        if (obj instanceof List<?>) {
            for (Object o : (List<?>) obj) {
                result.add(clazz.cast(o));
            }
        }
        return result;
    }

    /**
     * 获取方法返回的list类型
     *
     * @param method 方法信息
     * @return list类型
     */
    public static Class<?> getMethodReturnListDefaultType(Method method) {
        Assert.notNull(method, "方法不能为空，否则无法获取返回类型！");
        Type returnType = TypeUtil.getReturnType(method);
        Type typeArgument = TypeUtil.getTypeArgument(returnType, 0);
        return (Class<?>) typeArgument;
    }

    /**
     * 优雅的转化为Bean
     *
     * @param obj   对象
     * @param clazz list类型
     * @param <T>   list类型
     * @return Bean
     */
    public static <T> T castToBean(Object obj, Class<T> clazz) {
        return JSONUtil.toBean(JSONUtil.parse(obj).toString(), clazz);
    }
}
