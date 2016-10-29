package com.dzmsoft.sms.base.listener.event;

import org.springframework.context.ApplicationEvent;

public class SmsOrderLogEvent extends ApplicationEvent{
    
    private static final long serialVersionUID = 5400407652189367828L;
    // 会员取消订单
    public static final String CANCELBYMEMBER = "cancelByMember";
    // 抢单
    public static final String GRAB = "grab";
    // 派单 
    public static final String SEND = "send";
    
    private String smsOrderId;
    
    private String action;
    
    public SmsOrderLogEvent(Object source) {
        super(source);
    }
    
    public SmsOrderLogEvent(Object source, String smsOrderId, String action) {
        super(source);
        this.action = action;
        this.smsOrderId = smsOrderId;
    }

    public String getAction() {
        return action;
    }

    public String getSmsOrderId() {
        return smsOrderId;
    }

}
