package com.yuhao.TeachingServiceSystem.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "systemlog")
public class SystemLog extends BaseModel{
    private Long id;
    private Long userId;
    private String module;
    private String operation;
    private String params;
    private Date date;
    private String url;
    private Boolean isSuccess;
    private String errorMessage;
    private String ip;
    private String requestMethod;
    private String userAgent;
    private String headers;
    private User userByUserId;

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id", nullable = true)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "module", nullable = true, length = 50)
    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    @Basic
    @Column(name = "operation", nullable = true, length = 50)
    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    @Basic
    @Column(name = "params", nullable = true, length = -1)
    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    @Basic
    @Column(name = "date", nullable = true)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "url", nullable = true, length = 255)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "is_success", nullable = true)
    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    @Basic
    @Column(name = "error_message", nullable = true, length = -1)
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Basic
    @Column(name = "ip", nullable = true, length = 20)
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Basic
    @Column(name = "request_method", nullable = true, length = 20)
    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    @Basic
    @Column(name = "user_agent", nullable = true, length = 255)
    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    @Basic
    @Column(name = "headers", nullable = true, length = -1)
    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SystemLog systemlog = (SystemLog) o;

        if (id != null ? !id.equals(systemlog.id) : systemlog.id != null) return false;
        if (userId != null ? !userId.equals(systemlog.userId) : systemlog.userId != null) return false;
        if (module != null ? !module.equals(systemlog.module) : systemlog.module != null) return false;
        if (operation != null ? !operation.equals(systemlog.operation) : systemlog.operation != null) return false;
        if (params != null ? !params.equals(systemlog.params) : systemlog.params != null) return false;
        if (date != null ? !date.equals(systemlog.date) : systemlog.date != null) return false;
        if (url != null ? !url.equals(systemlog.url) : systemlog.url != null) return false;
        if (isSuccess != null ? !isSuccess.equals(systemlog.isSuccess) : systemlog.isSuccess != null) return false;
        if (errorMessage != null ? !errorMessage.equals(systemlog.errorMessage) : systemlog.errorMessage != null)
            return false;
        if (ip != null ? !ip.equals(systemlog.ip) : systemlog.ip != null) return false;
        if (requestMethod != null ? !requestMethod.equals(systemlog.requestMethod) : systemlog.requestMethod != null)
            return false;
        if (userAgent != null ? !userAgent.equals(systemlog.userAgent) : systemlog.userAgent != null) return false;
        if (headers != null ? !headers.equals(systemlog.headers) : systemlog.headers != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (module != null ? module.hashCode() : 0);
        result = 31 * result + (operation != null ? operation.hashCode() : 0);
        result = 31 * result + (params != null ? params.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (isSuccess != null ? isSuccess.hashCode() : 0);
        result = 31 * result + (errorMessage != null ? errorMessage.hashCode() : 0);
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        result = 31 * result + (requestMethod != null ? requestMethod.hashCode() : 0);
        result = 31 * result + (userAgent != null ? userAgent.hashCode() : 0);
        result = 31 * result + (headers != null ? headers.hashCode() : 0);
        return result;
    }


    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }
}
