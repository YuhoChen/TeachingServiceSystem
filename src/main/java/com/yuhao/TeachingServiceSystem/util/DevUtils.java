package com.yuhao.TeachingServiceSystem.util;

import java.io.File;

public class DevUtils {
    public DevUtils() {
    }

    public static boolean isLocal() {
        return (new File("/Users/kevin")).exists() || (new File("C:/Users/duffiye")).exists();
    }
}
