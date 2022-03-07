package com.github.jiangxch.mypring.util;

/**
 * @author: jiangxch
 * @date: 2022/3/6 6:10 PM
 */
public class StringUtil {
    public static String[] delimitedStringToStringArray(String str, Character delimiter) {
        if (str == null) {
            return new String[]{};
        }
        if (delimiter == null) {
            return new String[]{str};
        }
        return str.split(delimiter + "");
    }
}
