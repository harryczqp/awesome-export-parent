package io.ggit.awesome.export.utils;

/**
 * @author qingluo
 * @since 2023/6/1
 */
public final class Predications {

    private Predications() {

    }

    /**
     * 判断期望状态值
     *
     * @param expression 期望状态值
     * @param errorMessage 非期望状态值 定义的错误信息
     * @throws IllegalStateException 如果不是期望的状态值
     */
    public static void checkState(boolean expression, String errorMessage) {
        if (!expression) {
            throw new IllegalStateException(errorMessage);
        }
    }


    /**
     * 判断参数是否正确
     *
     * @param expression 表达式
     * @param errorMessage 错误信息
     * @throws IllegalArgumentException 如果表达式返回false
     */
    public static void checkArgument(boolean expression, String errorMessage) {
        if (!expression) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    /**
     * 判断给定对象是否为空
     *
     * @param reference 给定对象
     * @param errorMessage 当给定对象为null时，对象为空
     * @param <T> 泛型
     * @return 原始对象
     * @throws NullPointerException 如果给定对象为null
     */
    public static <T extends Object> T checkNotNull(T reference, String errorMessage) {
        if (reference == null) {
            throw new NullPointerException(errorMessage);
        }
        return reference;
    }
}