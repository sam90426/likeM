package com.quygt.dkcs.utils;

import java.util.Random;

public class RandomUtil {
    public static String number(int length) {
        String data = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            data += random.nextInt(10);
        }
        return data;
    }
}
