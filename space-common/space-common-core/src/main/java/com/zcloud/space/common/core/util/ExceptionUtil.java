package com.zcloud.space.common.core.util;

/**
 * @Description
 * @Author
 * @Date
 */
public class ExceptionUtil {
    /**
     * 编写一个泛型方法对异常进行包装
     *
     * @param e
     * @param <E>
     * @throws E
     */
    public static <E extends Exception> void doThrow(Exception e) throws E {
        throw (E) e;
    }
}
