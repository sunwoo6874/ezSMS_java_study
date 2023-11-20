package com.llsollu.ezsms.common.utils;

public class TextUtil {
    public static boolean isNullOrEmpty(String text) {
        if (null == text) {
            return true;
        }

        if (text.isEmpty()) {
            return true;
        }

        return false;
    }
}