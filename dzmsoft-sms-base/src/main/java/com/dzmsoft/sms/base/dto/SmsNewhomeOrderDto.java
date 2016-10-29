package com.dzmsoft.sms.base.dto;

import com.dzmsoft.sms.base.pojo.SmsNewhomeOrder;
import com.dzmsoft.sms.base.pojo.SmsOrder;

public class SmsNewhomeOrderDto {
    private String id;
    private SmsNewhomeOrder smsNewhomeOrder;
    private SmsOrder smsOrder;
    public SmsNewhomeOrder getSmsNewhomeOrder() {
        return smsNewhomeOrder;
    }
    public void setSmsNewhomeOrder(SmsNewhomeOrder smsNewhomeOrder) {
        this.smsNewhomeOrder = smsNewhomeOrder;
    }
    public SmsOrder getSmsOrder() {
        return smsOrder;
    }
    public void setSmsOrder(SmsOrder smsOrder) {
        this.smsOrder = smsOrder;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}
