package com.dzmsoft.sms.base.pojo.udf;

import com.dzmsoft.sms.base.pojo.SmsEmployeeExample;
import com.dzmsoft.sms.base.pojo.SmsEmployeeScheduleExample;

public class SmsEmployeeScheduleUdfExample {
    private SmsEmployeeScheduleExample smsEmployeeScheduleExample;
    private SmsEmployeeExample smsEmployeeExample;
    
    public SmsEmployeeScheduleUdfExample(){
        this.smsEmployeeExample = new SmsEmployeeExample();
        this.smsEmployeeScheduleExample = new SmsEmployeeScheduleExample();
    }
    
    public SmsEmployeeScheduleExample getSmsEmployeeScheduleExample() {
        return smsEmployeeScheduleExample;
    }
    public void setSmsEmployeeScheduleExample(SmsEmployeeScheduleExample smsEmployeeScheduleExample) {
        this.smsEmployeeScheduleExample = smsEmployeeScheduleExample;
    }
    public SmsEmployeeExample getSmsEmployeeExample() {
        return smsEmployeeExample;
    }
    public void setSmsEmployeeExample(SmsEmployeeExample smsEmployeeExample) {
        this.smsEmployeeExample = smsEmployeeExample;
    }
    
    
}
