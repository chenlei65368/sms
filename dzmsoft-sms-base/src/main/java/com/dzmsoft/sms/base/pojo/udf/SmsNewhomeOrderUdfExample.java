package com.dzmsoft.sms.base.pojo.udf;

import com.dzmsoft.sms.base.pojo.SmsNewhomeOrderExample;
import com.dzmsoft.sms.base.pojo.SmsOrderExample;

public class SmsNewhomeOrderUdfExample {
    private SmsNewhomeOrderExample smsNewhomeOrderExample;
    private SmsOrderExample smsOrderExample;
    
    public SmsNewhomeOrderUdfExample(){
        this.smsNewhomeOrderExample = new SmsNewhomeOrderExample();
        this.smsOrderExample = new SmsOrderExample();
    }

    public SmsNewhomeOrderExample getSmsNewhomeOrderExample() {
        return smsNewhomeOrderExample;
    }

    public void setSmsNewhomeOrderExample(SmsNewhomeOrderExample smsNewhomeOrderExample) {
        this.smsNewhomeOrderExample = smsNewhomeOrderExample;
    }

    public SmsOrderExample getSmsOrderExample() {
        return smsOrderExample;
    }

    public void setSmsOrderExample(SmsOrderExample smsOrderExample) {
        this.smsOrderExample = smsOrderExample;
    }
}
