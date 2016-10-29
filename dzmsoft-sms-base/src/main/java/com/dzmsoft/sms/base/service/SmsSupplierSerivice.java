package com.dzmsoft.sms.base.service;

import com.dzmsoft.sms.oim.pojo.OimSupplier;

public interface SmsSupplierSerivice {
    /**
     * 根据ucsId获取供应商信息
     * @param ucsId
     * @return
     */
    OimSupplier selectByUcsId(String ucsId);
}
