package com.quygt.dkcs.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtil {
    public static boolean isMobile(String data) {
        Pattern pattern = Pattern.compile("^[1]+[3,4,5,7,8]+\\d{9}$");
        Matcher matcher = pattern.matcher(data);
        return matcher.matches();
    }

    public static boolean isTel(String data) {
        Pattern pattern = Pattern.compile("^(\\d{3,4}-)?\\d{6,8}$");
        Matcher matcher = pattern.matcher(data);
        return matcher.matches();
    }

    public static boolean isEmail(String data){
        Pattern pattern = Pattern.compile("^([\\w-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([\\w-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
        Matcher matcher = pattern.matcher(data);
        return matcher.matches();
    }

    public static boolean isUrl(String data){
        Pattern pattern = Pattern.compile("http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?");
        Matcher matcher = pattern.matcher(data);
        return matcher.matches();
    }

    public static boolean isIdCard(String data){
        Pattern pattern = Pattern.compile("(^\\d{18}$)|(^\\d{15}$)");
        Matcher matcher = pattern.matcher(data);
        return matcher.matches();
    }
}
