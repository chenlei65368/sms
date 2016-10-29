package com.dzmsoft.sms.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dzmsoft.sms.base.pojo.SmsEmployee;
import com.dzmsoft.sms.base.service.SmsEmployeeService;
import com.dzmsoft.sms.base.service.SmsSupplierSerivice;
import com.dzmsoft.sms.oim.pojo.OimSupplier;
import com.dzmsoft.sms.oim.service.OimSupplierService;

@Service
@Transactional(readOnly = true)
public class SmsSupplierSeriviceImpl implements SmsSupplierSerivice{
    @Autowired
    private OimSupplierService oimSupplierService;
    @Autowired
    private SmsEmployeeService smsEmployeeService;

    @Cacheable(value="supplierCache", key = "#ucsId")
    @Override
    public OimSupplier selectByUcsId(String ucsId) {
        // 先查管理用户
        OimSupplier oimSupplier = oimSupplierService.selectByUcsId(ucsId);
        if (oimSupplier == null){
            SmsEmployee smsEmployee = smsEmployeeService.selectByUcsId(ucsId);
            oimSupplier = oimSupplierService.selectByPrimaryKey(smsEmployee.getId());
        }
        return oimSupplier;
    }
}
