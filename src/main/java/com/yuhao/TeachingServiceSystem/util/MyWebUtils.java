package com.yuhao.TeachingServiceSystem.util;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class MyWebUtils {
    public MyWebUtils() {
    }

    public static String getCtx() {
        HttpServletRequest request = getCurrentRequest();
        return (String)request.getSession().getAttribute("ctx");
    }

    public static HttpServletRequest getCurrentRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
            return request;
        } else {
            return null;
        }
    }

    public static boolean isFromMobile() {
        HttpServletRequest request = getCurrentRequest();
        if (request == null) {
            return false;
        } else {
            return StringUtils.isNotBlank(request.getHeader("client"));
        }
    }

    public static boolean isAndroid() {
        HttpServletRequest request = getCurrentRequest();
        if (request == null) {
            return false;
        } else {
            return StringUtils.isNotBlank(request.getHeader("client")) && "android".equalsIgnoreCase(request.getHeader("client"));
        }
    }

    public static boolean isIOS() {
        HttpServletRequest request = getCurrentRequest();
        if (request == null) {
            return false;
        } else {
            return StringUtils.isNotBlank(request.getHeader("client")) && "ios".equalsIgnoreCase(request.getHeader("client"));
        }
    }
}

