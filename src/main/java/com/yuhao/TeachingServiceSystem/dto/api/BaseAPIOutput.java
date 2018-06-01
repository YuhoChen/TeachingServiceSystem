package com.yuhao.TeachingServiceSystem.dto.api;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

public class BaseAPIOutput {
    private String state = "";
    private String error = "";
    private String detail;

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state
     *            the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    public static MobileApiOutput error(Throwable e) {
        String message = e.getMessage();
        if (StringUtils.isBlank(message)) {
            message = "系统异常，请稍后重试";
        }
        MobileApiOutput output = new MobileApiOutput();
        output.setStatus("10");
        output.setError(message);
        output.setRootClause(ExceptionUtils.getRootCauseMessage(e));
        output.setErrorDetail(ExceptionUtils.getStackTrace(e));
        return output;
    }

    /**
     * @return the error
     */
    public String getError() {
        return error;
    }

    /**
     * @param error
     *            the error to set
     */
    public void setError(String error) {
        this.error = error;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public static Object errorNoLogin(Throwable e) {
        String message = e.getMessage();
        if (StringUtils.isBlank(message)) {
            message = "请先登陆";
        }
        MobileApiOutput output = new MobileApiOutput();
        output.setStatus("20");
        output.setError(message);
        output.setRootClause(ExceptionUtils.getRootCauseMessage(e));
        output.setErrorDetail(ExceptionUtils.getStackTrace(e));
        return output;
    }

}
