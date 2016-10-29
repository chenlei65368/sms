package com.dzmsoft.sms.base.dto;

import java.util.List;

import com.dzmsoft.sms.base.pojo.SmsCommodityOrder;
import com.dzmsoft.sms.base.pojo.SmsOrder;

public class SmsCommodityOrderDto {
    private String id;
    private List<SmsCommodityOrder> smsCommodityOrders;
    private SmsOrder smsOrder;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public SmsOrder getSmsOrder() {
        return smsOrder;
    }
    public void setSmsOrder(SmsOrder smsOrder) {
        this.smsOrder = smsOrder;
    }
    public List<SmsCommodityOrder> getSmsCommodityOrders() {
        return smsCommodityOrders;
    }
    public void setSmsCommodityOrders(List<SmsCommodityOrder> smsCommodityOrders) {
        this.smsCommodityOrders = smsCommodityOrders;
    }
    
}
