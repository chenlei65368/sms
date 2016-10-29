package com.dzmsoft.sms.base.pojo.udf;

import com.dzmsoft.sms.base.pojo.SmsEmployeeExample;
import com.dzmsoft.sms.base.pojo.SmsServiceEmployeeExample;

public class ServiceCleanersUdfExample {
    private SmsEmployeeExample smsEmployeeExample;
    private SmsServiceEmployeeExample smsServiceEmployeeExample;
    
    public ServiceCleanersUdfExample(){
        this.smsEmployeeExample = new SmsEmployeeExample();
        this.smsServiceEmployeeExample = new SmsServiceEmployeeExample();
    }
   
    public SmsEmployeeExample getSmsEmployeeExample() {
        return smsEmployeeExample;
    }
    public void setSmsEmployeeExample(SmsEmployeeExample smsEmployeeExample) {
        this.smsEmployeeExample = smsEmployeeExample;
    }
    public SmsServiceEmployeeExample getSmsServiceEmployeeExample() {
        return smsServiceEmployeeExample;
    }
    public void setSmsServiceEmployeeExample(SmsServiceEmployeeExample smsServiceEmployeeExample) {
        this.smsServiceEmployeeExample = smsServiceEmployeeExample;
    }
}
