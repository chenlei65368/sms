package com.dzmsoft.sms.rest.base.dto;

import java.util.List;

import com.dzmsoft.sms.base.pojo.SmsEmployee;
import com.dzmsoft.sms.base.pojo.SmsEmployeeSchedule;

public class SmsEmployeeScheduleDto {
    SmsEmployee smsEmployee;
    List<SmsEmployeeSchedule> smsEmployeeSchedules;
    public SmsEmployee getSmsEmployee() {
        return smsEmployee;
    }
    public void setSmsEmployee(SmsEmployee smsEmployee) {
        this.smsEmployee = smsEmployee;
    }
    public List<SmsEmployeeSchedule> getSmsEmployeeSchedules() {
        return smsEmployeeSchedules;
    }
    public void setSmsEmployeeSchedules(List<SmsEmployeeSchedule> smsEmployeeSchedules) {
        this.smsEmployeeSchedules = smsEmployeeSchedules;
    }
}
