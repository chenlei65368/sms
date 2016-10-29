package com.dzmsoft.sms.base.common;

public abstract class SmsBaseConstant {
    
    /**
     * 订单状态
     * @author dzm
     */
    public interface OrderStatus{
        /**
         * 新订单
         */
        public static final String INIT = "00";
        /**
         * 已抢单
         */
        public static final String GRAB = "01";
        /**
         * 已派单
         */
        public static final String SEND = "02";
        /**
         * 上门中
         */
        public static final String VISIT = "03";
        /**
         * 已评价
         */
        public static final String EVALUATE = "04";
        /**
         * 已取消
         */
        public static final String CANCEL = "05";
    }
    /**
     * 员工状态
     * @author dzm
     */
    public interface EmployeeStatus{
        /**
         * 在岗
         */
        public static final String IN_JOB = "01";
        /**
         * 离职
         */
        public static final String OUT_JOB = "00";
    }
    
    /**
     * 订单类型
     * @author dzm
     */
    public interface OrderType{
        /**
         * 家庭保洁
         */
        public static final String SmsHouseholdOrder = "00";
        /**
         * 新居开荒
         */
        public static final String SmsNewhomeOrder = "01";
        /**
         * 商品订单
         */
        public static final String SmsCommodityOrder = "02";
    }
    
    /**
     * 商品状态
     * @author dzm
     */
    public interface CommodityStatus{
        /**
         * 初始
         */
        public static final String INIT = "00";
        /**
         * 上线
         */
        public static final String ONLINE = "01";
        /**
         * 下线
         */
        public static final String OFFLINE = "02";
    }
    
    /**
     * 排班状态
     * @author dzm
     */
    public interface ScheduleStatus{
        /**
         * 休息
         */
        public static final String REST = "00";
        /**
         * 工作
         */
        public static final String WORK = "01";
    }
    
    /**
     * 用户类型
     * @author dzm
     */
    public interface UserType{
        /**
         * 保洁员
         */
        public static String CLEANER = "13";
    }
    
    /**
     * 班
     * @author dzm
     */
    public interface Shift{
        /**
         * 休
         */
        public static final String REST = "0";
        /**
         * 忙
         */
        public static final String BUSY = "2";
        /**
         * 闲
         */
        public static final String IDEL = "1";
    }
    
    /**
     * 会员状态
     * @author dzm
     */
    public interface MemberStatus{
        /**
         * 注销
         */
        public static final String CANCEL = "00";
        /**
         * 有效
         */
        public static final String VALID = "01";
    }
    /**
     * 商品订单状态
     * @author dzm
     */
    public interface CommodityOrderStatus{
        /**
         * 待发货
         */
        public static final String WAIT_SEND = "00";
        /**
         * 已发货
         */
        public static final String IN_SEND = "01";
    }
}
