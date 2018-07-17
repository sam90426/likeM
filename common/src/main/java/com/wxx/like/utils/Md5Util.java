package com.wxx.like.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {
    public static String md5(String data) {
        String encoderStr = "";
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(data.getBytes("UTF-8"));
            byte[] bytes = md5.digest();
            if (bytes != null && bytes.length > 0) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < bytes.length; i++) {
                    int v = bytes[i] & 0xFF;
                    String hv = Integer.toHexString(v);
                    if (hv.length() < 2) {
                        stringBuilder.append(0);
                    }
                    stringBuilder.append(hv);
                }
                encoderStr = stringBuilder.toString();
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encoderStr;
    }
}
