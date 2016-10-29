package com.dzmsoft.sms.base.pojo.udf;

import com.dzmsoft.sms.base.pojo.SmsCommodityOrderExample;
import com.dzmsoft.sms.base.pojo.SmsOrderExample;

public class SmsCommodityOrderUdfExample {
    
    public SmsCommodityOrderUdfExample(){
        this.smsCommodityOrderExample = new SmsCommodityOrderExample();
        this.smsOrderExample = new SmsOrderExample();
    }
    private SmsCommodityOrderExample smsCommodityOrderExample;
    private SmsOrderExample smsOrderExample;
    public SmsCommodityOrderExample getSmsCommodityOrderExample() {
        return smsCommodityOrderExample;
    }
    public void setSmsCommodityOrderExample(SmsCommodityOrderExample smsCommodityOrderExample) {
        this.smsCommodityOrderExample = smsCommodityOrderExample;
    }
    public SmsOrderExample getSmsOrderExample() {
        return smsOrderExample;
    }
    public void setSmsOrderExample(SmsOrderExample smsOrderExample) {
        this.smsOrderExample = smsOrderExample;
    }
    
}
