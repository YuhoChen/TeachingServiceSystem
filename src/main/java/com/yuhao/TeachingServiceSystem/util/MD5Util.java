package com.yuhao.TeachingServiceSystem.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    public MD5Util() {
    }

    public static String getMD5Encoding(String s) {
        byte[] input = s.getBytes();
        String output = null;
        char[] hexChar = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input);
            byte[] tmp = md.digest();
            char[] str = new char[32];

            for(int i = 0; i < 16; ++i) {
                byte b = tmp[i];
                str[2 * i] = hexChar[b >>> 4 & 15];
                str[2 * i + 1] = hexChar[b & 15];
            }

            output = new String(str);
        } catch (NoSuchAlgorithmException var9) {
            var9.printStackTrace();
        }

        return output;
    }

    public static void main(String[] args) {
        String str = getMD5Encoding("abd2");
        System.out.println(str);
    }
}
