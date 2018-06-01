package com.yuhao.TeachingServiceSystem.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MerchantLoginFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest request = (HttpServletRequest) arg0;

        Object currentMerchant = request.getSession().getAttribute("currentMerchant");
        if (currentMerchant == null) {
            String uri = request.getRequestURI();
            if ((uri.equals(request.getContextPath() + "/ca") || uri.startsWith(request.getContextPath() + "/ca/"))
                    && !uri.equals(request.getContextPath() + "/ca/login")) {
                // 要登陆
                String ctx = (String) request.getSession().getAttribute("ctx");
                // String redirectUrl = ctx + "/wsadmin/login";
                String redirectUrl = ctx;
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
