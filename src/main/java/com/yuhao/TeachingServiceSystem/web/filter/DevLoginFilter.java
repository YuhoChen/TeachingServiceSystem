package com.yuhao.TeachingServiceSystem.web.filter;

import java.io.File;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DevLoginFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain) throws IOException,
            ServletException {
        if (1 == 1) {
            chain.doFilter(arg0, arg1);
            return;
        }
        HttpServletRequest request = (HttpServletRequest) arg0;

        Object currentDev = request.getSession().getAttribute("currentDev");
        if (currentDev == null) {
            if (new File("/Users/kevin/code").exists()) {
                currentDev = "self";
                request.getSession().setAttribute("currentDev", currentDev);
            }
        }
        if (currentDev == null) {
            String uri = request.getRequestURI();
            if ((uri.equals(request.getContextPath() + "/dev") || uri.startsWith(request.getContextPath() + "/dev/")
                    || uri.equals(request.getContextPath() + "/druid")
                    || uri.startsWith(request.getContextPath() + "/druid/"))
                    && !uri.equals(request.getContextPath() + "/dev/login")) {
                // 要登陆
                String ctx = (String) request.getSession().getAttribute("ctx");
                // String redirectUrl = ctx + "/wsadmin/login";
                String redirectUrl = ctx + "/dev/login";
                HttpServletResponse response = (HttpServletResponse) arg1;
                response.sendRedirect(redirectUrl);
                return;
            }
        }

        chain.doFilter(arg0, arg1);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

}

