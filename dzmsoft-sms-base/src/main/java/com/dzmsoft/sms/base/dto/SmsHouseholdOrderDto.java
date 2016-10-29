package com.dzmsoft.sms.base.dto;

import com.dzmsoft.sms.base.pojo.SmsHouseholdOrder;
import com.dzmsoft.sms.base.pojo.SmsOrder;

public class SmsHouseholdOrderDto {
    private String id;
    private SmsHouseholdOrder smsHouseholdOrder;
    private SmsOrder smsOrder;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public SmsHouseholdOrder getSmsHouseholdOrder() {
        return smsHouseholdOrder;
    }
    public void setSmsHouseholdOrder(SmsHouseholdOrder smsHouseholdOrder) {
        this.smsHouseholdOrder = smsHouseholdOrder;
    }
    public SmsOrder getSmsOrder() {
        return smsOrder;
    }
    public void setSmsOrder(SmsOrder smsOrder) {
        this.smsOrder = smsOrder;
    }
}
