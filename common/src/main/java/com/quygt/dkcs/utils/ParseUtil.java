package com.quygt.dkcs.utils;

public class ParseUtil {
    public static int toInt(String value, int defaultValue) {
        if (value == null || value == "") return defaultValue;
        int data = 0;
        try {
            data = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
        return data;
    }

    public static long toLong(String value, long defaultValue) {
        if (value == null || value == "") return defaultValue;
        long data = 0;
        try {
            data = Long.parseLong(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
        return data;
    }
}
