package com.dzmsoft.sms.base.pojo.udf;

import com.dzmsoft.sms.base.pojo.SmsHouseholdOrderExample;
import com.dzmsoft.sms.base.pojo.SmsOrderExample;

public class SmsHouseholdOrderUdfExample {
    private SmsHouseholdOrderExample smsHouseholdOrderExample;
    private SmsOrderExample smsOrderExample;
    
    public SmsHouseholdOrderUdfExample(){
        this.smsHouseholdOrderExample = new SmsHouseholdOrderExample();
        this.smsOrderExample = new SmsOrderExample();
    }

    public SmsHouseholdOrderExample getSmsHouseholdOrderExample() {
        return smsHouseholdOrderExample;
    }

    public void setSmsHouseholdOrderExample(SmsHouseholdOrderExample smsHouseholdOrderExample) {
        this.smsHouseholdOrderExample = smsHouseholdOrderExample;
    }

    public SmsOrderExample getSmsOrderExample() {
        return smsOrderExample;
    }

    public void setSmsOrderExample(SmsOrderExample smsOrderExample) {
        this.smsOrderExample = smsOrderExample;
    }
}
