package com.zcloud.space.common.core.util;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @Description 数字类型Util
 * @Author
 * @Date
 */
public class NumberUtil {

    private static  final BigDecimal zero = new BigDecimal(0);

    /**
     * 判断 Byte 是否null或0
     * @param val
     * @return
     */
    public static boolean isNullOrZero(Byte val) {
        return null == val || val == 0;
    }

    /**
     * @description: 反向判断 Byte 是否null或0
     */
    public static boolean isNotNullOrZero(Byte val) {
        return !isNullOrZero(val);
    }

    /**
     * 判断 Interger 是否null或0
     * @param val
     * @return
     */
    public static boolean isNullOrZero(Integer val) {
        return null == val || val == 0;
    }

    /**
     * @description: 反向判断 Integer 是否null或0
     */
    public static boolean isNotNullOrZero(Integer val) {
        return !isNullOrZero(val);
    }

    /**
     * 判断 Long 数字是否有效
     * @param val
     * @return
     */
    public static boolean isNullOrZero(Long val) {
        return null == val || val == 0;
    }

    /**
     * @description: 反向判断 Long 是否null或0
     */
    public static boolean isNotNullOrZero(Long val) {
        return !isNullOrZero(val);
    }

    /**
     * @description: 判断bigDecimal函数是不是空或0
     */
    public static boolean isNullOrZero(BigDecimal val) {
        return null == val || val.compareTo(zero) == 0;
    }

    /**
     * @description: 反向判断bigDecimal函数是不是空或0
     */
    public static boolean isNotNullOrZero(BigDecimal val) {
        return !isNullOrZero(val);
    }

    /**
     * 把 object 转为 BigDecimal
     * @param value
     * @return
     */
    public static BigDecimal getBigDecimal(Object value) {
        BigDecimal ret = null;
        if( value != null ) {
            if( value instanceof BigDecimal ) {
                ret = (BigDecimal) value;
            } else if( value instanceof String ) {
                ret = new BigDecimal( (String) value );
            } else if( value instanceof BigInteger) {
                ret = new BigDecimal( (BigInteger) value );
            } else if( value instanceof Number ) {
                ret = new BigDecimal( ((Number)value).doubleValue() );
            } else {
                throw new ClassCastException("Not possible to coerce ["+value+"] from class "+value.getClass()+" into a BigDecimal.");
            }
        }
        return ret;
    }


}
