package com.dzmsoft.sms.base.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class SmsEmployeeScheduleArrangeDto {
    /**
     * 排班月份
     */
    @DateTimeFormat(pattern = "yyyy-MM")
    private Date month;
    /**
     * 班次
     */
    private String shift;
    public String getShift() {
        return shift;
    }
    public void setShift(String shift) {
        this.shift = shift;
    }
    public Date getMonth() {
        return month;
    }
    public void setMonth(Date month) {
        this.month = month;
    }
    
}
