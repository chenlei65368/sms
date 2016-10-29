package com.dzmsoft.sms.base.dto;

import com.dzmsoft.sms.base.pojo.SmsOrder;

public class SmsOrderDto<T> {
    
    public SmsOrderDto(){
        
    }

    private SmsOrder smsOrder;
    private T data;
    
    public SmsOrder getSmsOrder() {
        return smsOrder;
    }
    public void setSmsOrder(SmsOrder smsOrder) {
        this.smsOrder = smsOrder;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
}
