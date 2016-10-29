package com.dzmsoft.sms.base.service;

import java.util.List;

import com.dzmsoft.sms.base.dto.SmsHouseholdOrderDto;
import com.dzmsoft.sms.base.pojo.SmsHouseholdBag;
import com.dzmsoft.sms.base.pojo.SmsHouseholdOrder;
import com.dzmsoft.sms.base.pojo.SmsHouseholdOrderExample;
import com.dzmsoft.sms.base.pojo.SmsOrder;
import com.dzmsoft.sms.base.pojo.udf.SmsHouseholdOrderUdfExample;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * Copyright (C), dzmsoft Co., Ltd
 * @author dzmsoft
 * @date 2016-05-21 14:14:45
 *
 * @version 1.0
 */
public interface SmsHouseholdOrderService {
    /**
     * 系统派单
     * @param orderId
     * @param id
     * @param cleaner
     * @return
     */
    int send(String orderId, String id, String cleaner); 
    /**
     * 产生家庭保洁订单
     * @param smsOrder 统一订单
     * @param smsHouseholdOrder 家庭保洁订单
     * @param smsHouseholdBagList 清洁包
     * @param smsHouseholdRequireList 个性需求
     * @return
     */
    int genSmsHouseholdOrder(SmsOrder smsOrder, SmsHouseholdOrder smsHouseholdOrder, List<SmsHouseholdBag> smsHouseholdBagList);
    
    public PageList<SmsHouseholdOrderDto> selectByExample(SmsHouseholdOrderUdfExample example,PageBounds pageBounds);

	/**
     * 查询符合条件的记录数量
     * @dzmsoftgenerated 2016-05-21 14:14:45
     */
	int countByExample(SmsHouseholdOrderExample example);
	
	/**
     * 根据条件插入记录
     * @dzmsoftgenerated 2016-05-21 14:14:45
     */
	int insertSelective(SmsHouseholdOrder record);
	
	/**
     * 根据主键查询记录
     * @dzmsoftgenerated 2016-05-21 14:14:45
     */
	SmsHouseholdOrder selectByPrimaryKey(String id);
	
	/**
     * 分页查询记录
     * @dzmsoftgenerated 2016-05-21 14:14:45
     */
	PageList<SmsHouseholdOrder> selectByExample(SmsHouseholdOrderExample example,PageBounds pageBounds);
	
	/**
     * 根据条件查询记录
     * @dzmsoftgenerated 2016-05-21 14:14:45
     */
	List<SmsHouseholdOrder> selectByExample(SmsHouseholdOrderExample example);
	
	/**
     * 根据主键更新记录
     * @dzmsoftgenerated 2016-05-21 14:14:45
     */
	int updateByPrimaryKeySelective(SmsHouseholdOrder record);
	
	/**
     * 根据查询条件更新记录
     * @dzmsoftgenerated 2016-05-21 14:14:45
     */
	int updateByExampleSelective(SmsHouseholdOrder record,
			SmsHouseholdOrderExample example);
	
	/**
     * 根据主键生成记录
     * @dzmsoftgenerated 2016-05-21 14:14:45
     */
	int deleteByPrimaryKey(String id);
	
	/**
	 * 根据条件删除字段信息
	 * @dzmsoftgenerated 2016-05-21 14:14:45
	 * @param example
	 * @return
	 */
	int deleteByExample(SmsHouseholdOrderExample example);
	
	
}