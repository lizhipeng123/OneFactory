package com.daoran.newfactory.onefactory.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lizhipeng on 2017/4/21.
 */

public class StringUtil {
    /**
    * @param stream 要处理的字符串
    * @param trimstr 要去掉的字符串
    * @return 处理后的字符串
    */
    public static String sideTrim(String stream, String trimstr) {
        // null或者空字符串的时候不处理
        if (stream == null || stream.length() == 0 || trimstr == null || trimstr.length() == 0) {
            return stream;
        }

        // 结束位置
        int epos = 0;

        // 正规表达式
        String regpattern = "[" + trimstr + "]*+";
        Pattern pattern = Pattern.compile(regpattern, Pattern.CASE_INSENSITIVE);

        // 去掉结尾的指定字符
        StringBuffer buffer = new StringBuffer(stream).reverse();
        Matcher matcher = pattern.matcher(buffer);
        if (matcher.lookingAt()) {
            epos = matcher.end();
            stream = new StringBuffer(buffer.substring(epos)).reverse().toString();
        }

        // 去掉开头的指定字符
        matcher = pattern.matcher(stream);
        if (matcher.lookingAt()) {
            epos = matcher.end();
            stream = stream.substring(epos);
        }

        // 返回处理后的字符串
        return stream;
    }
}
