package com.wxx.like.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class DesUtil {
    //des加密
    public static String desEncrypt(String key, String data) {
        try {
            byte[] bt = desEncrypt(key.getBytes("UTF-8"), data.getBytes("UTF-8"));
            if (bt == null)
                return null;
            return new BASE64Encoder().encode(bt);
        } catch (UnsupportedEncodingException e) {
            System.out.println("DesUtil -> desEncrypt exception：" + e.getMessage());
        }
        return null;
    }

    //des解密
    public static String desDecrypt(String key, String data) {
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] buf = decoder.decodeBuffer(data);
            byte[] bt = desDecrypt(key.getBytes("UTF-8"), buf);
            if (bt == null)
                return null;
            return new String(bt, "UTF-8");
        } catch (IOException e) {
            System.out.println("DesUtil -> desDecrypt exception：" + e.getMessage());
        }
        return null;
    }

    //des加密
    private static byte[] desEncrypt(byte[] key, byte[] data) {
        try {
            DESKeySpec dks = new DESKeySpec(key);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(dks);
            IvParameterSpec iv = new IvParameterSpec(key);
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
            return cipher.doFinal(data);
        } catch (Exception e) {
            System.out.println("DesUtil -> desEncrypt exception：" + e.getMessage());
        }
        return null;
    }

    //des解密
    private static byte[] desDecrypt(byte[] key, byte[] data) {
        try {
            DESKeySpec dks = new DESKeySpec(key);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(dks);
            IvParameterSpec iv = new IvParameterSpec(key);
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
            return cipher.doFinal(data);
        } catch (Exception e) {
            System.out.println("DesUtil -> desDecrypt exception：" + e.getMessage());
        }
        return null;
    }
}
