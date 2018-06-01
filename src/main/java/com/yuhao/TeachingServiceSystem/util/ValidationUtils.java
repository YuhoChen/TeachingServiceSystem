package com.yuhao.TeachingServiceSystem.util;


import com.yuhao.TeachingServiceSystem.exception.BizException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils {
    private static Pattern regexMobile = Pattern.compile("^1\\d{10}$");

    public ValidationUtils() {
    }

    public static void assertTrue(boolean value, String msg) {
        if (!value) {
            throw new BizException(msg);
        }
    }

    public static void validateMobile(String mobile, String msg) {
        Matcher m = regexMobile.matcher(mobile);
        if (!m.matches()) {
            throw new BizException(msg);
        }
    }

    public static void main(String[] args) {
        validateMobile("18670053471", "aa");
    }
}

