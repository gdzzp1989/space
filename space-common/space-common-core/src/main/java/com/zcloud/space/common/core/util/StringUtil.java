package com.zcloud.space.common.core.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.PinyinUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.Iterator;

/**
 * @Description 字符串工具类
 * @Author
 * @Date
 */
public class StringUtil {

    /**
     * 替换指定位置的字符串
     *
     * @param start  开始位置
     * @param end    结束位置
     * @param source 原字符串
     * @param str    替换字符串
     * @return 替换好的字符串
     */
    public static String replace(int start, int end, String source, String str) {
        if (StringUtils.isNotBlank(source) && source.length() > start && source.length() > end) {
            StringBuilder sb = new StringBuilder(source);
            return sb.replace(start, end, str).toString();
        } else {
            return source;
        }
    }

    /**
     * @description: 字符串去空
     */
    public static String removeNull(String str) {
        return (StringUtils.isEmpty(str) ? "" : str);
    }

    /**
     * @description: 获取集合字符串为固定形式
     */
    public static String getCollections(Collection c) {
        StringBuilder str = new StringBuilder("");
        Iterator iterator = c.iterator();
        while (iterator.hasNext()) {
            str.append(iterator.next());
            if (iterator.hasNext()) {
                str.append(",");
            }
        }
        return str.toString();
    }

    public static String getFileNameNew(String orginFileName) { // yyyy-MM-dd-hhmmss

        String path = orginFileName + DateUtil.format(DateUtil.date(),"yyyy-MM-dd-hhmmss");
        path = PinyinUtil.getAllFirstLetter(path);

        return path;
    }

    public static String passwordMD(String password) {
        return "{bcrypt}" + new BCryptPasswordEncoder().encode(password);
    }

}
