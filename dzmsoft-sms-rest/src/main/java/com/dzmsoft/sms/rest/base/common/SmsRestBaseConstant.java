package com.dzmsoft.sms.rest.base.common;

public abstract class SmsRestBaseConstant {
    /**
     * 订单类型
     * @author dzm
     */
    public interface OrderType{
        /**
         * 待上门
         */
        public static final String WAIT_DOOR = "00";
        /**
         * 进行中
         */
        public static final String UNDER_WAY = "01";
        /**
         * 已上门
         */
        public static final String FINISH = "02";
    }
}
