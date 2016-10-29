package com.dzmsoft.sms.base.service;

import com.dzmsoft.sms.base.dto.Order;

public interface OrderService {
    public Order selectByOrderId(String orderId);
}
