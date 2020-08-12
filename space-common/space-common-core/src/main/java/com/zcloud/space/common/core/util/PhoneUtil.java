package com.zcloud.space.common.core.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description
 * @Author
 * @Date
 */
public class PhoneUtil {

    /**
     * 手机号长度
     */
    private final static int PHONE_LENGTH = 11;
    /**
     * 移动运营商手机号段
     */
    private final static String MOBILE_OPERATOR_REGEX = "^((13[4-9])|(147)|(15[0-2,7-9])|(178)|(18[2-4,7-8]))\\d{8}|(1705)\\d{7}$";
    /**
     * 联通运营商手机号段
     */
    private final static String UNICOM_OPERATOR_REGEX = "^((13[0-2])|(145)|(15[5-6])|(176)|(18[5,6]))\\d{8}|(1709)\\d{7}$";
    /**
     * 电信运营商手机号段
     */
    private final static String TELECOM_OPERATOR_REGEX = "^((133)|(153)|(177)|(18[0,1,9])|(149))\\d{8}$";
    /**
     * 虚拟运营商手机号段
     */
    private final static String VIRTUAL_OPERATOR_REGEX = "^((170))\\d{8}|(1718)|(1719)\\d{7}$";

    /**
     * 验证手机号是否合法
     * @return
     */
    public static boolean phoneValid(String phone) {
        if (null == phone || phone.trim().length() == 0) {
            return false;
        }
        if (phone.length() != PHONE_LENGTH) {
            return false;
        } else {
            String[] regexArr = {MOBILE_OPERATOR_REGEX, UNICOM_OPERATOR_REGEX, TELECOM_OPERATOR_REGEX, VIRTUAL_OPERATOR_REGEX};
            boolean flag = false;
            for (String regex : regexArr) {
                if (phoneValidRegex(phone, regex)) {
                    flag = true;
                    break;
                }
                flag = false;
            }
            return flag;
        }
    }

    /**
     * 正则校验手机号
     */
    private static boolean phoneValidRegex(String phone, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher(phone);
        return match.matches();
    }

}
