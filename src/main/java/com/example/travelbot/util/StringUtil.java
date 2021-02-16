package com.example.travelbot.util;

public class StringUtil {

    public static final String EMPTY_STRING = "";

    public static boolean isStringEmpty(String str) {
        return (str == null || str.trim().equals(EMPTY_STRING));
    }

}
