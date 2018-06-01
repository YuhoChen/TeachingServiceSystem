package com.yuhao.TeachingServiceSystem.dto.api;

import java.io.Serializable;

public class MobileApiOutput implements Serializable {
    private static final long serialVersionUID = 1L;

    private String status = "0";
    private Object data;
    private String error;
    private String rootClause;
    private String errorDetail;
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
    public String getErrorDetail() {
        return errorDetail;
    }
    public void setErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
    }
    public String getRootClause() {
        return rootClause;
    }
    public void setRootClause(String rootClause) {
        this.rootClause = rootClause;
    }


}
