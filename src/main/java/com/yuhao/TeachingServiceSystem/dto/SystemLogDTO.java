package com.yuhao.TeachingServiceSystem.dto;

import java.util.Date;

import com.yuhao.TeachingServiceSystem.model.SystemLog;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;


public class SystemLogDTO extends SystemLog {
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;
    private String formattedParams;
    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }
    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }
    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public String getFormattedParams() {
        if (StringUtils.isBlank(getParams())) {
            return getParams();
        }
        String newParams = getParams();
        newParams = newParams.replace(", ", "&");
        newParams = newParams.replace(",", "&");
        newParams = newParams.substring(0, newParams.length() - 2);
        return newParams;
    }
    public void setFormattedParams(String formattedParams) {
        this.formattedParams = formattedParams;
    }


}
