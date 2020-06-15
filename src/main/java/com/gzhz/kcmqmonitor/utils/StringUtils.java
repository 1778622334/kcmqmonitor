package com.gzhz.kcmqmonitor.utils;

public class StringUtils {
    /**
     * 将多空格替换为指定字符
     *
     * @Param str 原字符串 specific 指定字符串
     */
    public static String blankToSpecific(String str, String specific) {
        String[] person = str.split(" ");
        str = "";
        for (int i = 0; i < person.length - 1; i++) {
            if (person[i].compareTo("") != 0) {
                str = str + person[i] + specific;
            }
        }
        if (person.length > 0) {
            str = str + person[person.length - 1];
        }

        return str;
    }
}
