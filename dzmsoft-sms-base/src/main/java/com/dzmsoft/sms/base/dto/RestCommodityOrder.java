package com.dzmsoft.sms.base.dto;

import java.util.List;

import com.dzmsoft.sms.base.pojo.SmsCommodityOrder;

public class RestCommodityOrder implements Order{

    List<SmsCommodityOrder> smsCommodityOrders;

    public List<SmsCommodityOrder> getSmsCommodityOrders() {
        return smsCommodityOrders;
    }

    public void setSmsCommodityOrders(List<SmsCommodityOrder> smsCommodityOrders) {
        this.smsCommodityOrders = smsCommodityOrders;
    }
}
