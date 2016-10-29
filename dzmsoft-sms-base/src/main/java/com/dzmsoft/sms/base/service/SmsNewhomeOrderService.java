package com.dzmsoft.sms.base.service;

import java.util.List;

import com.dzmsoft.sms.base.dto.SmsNewhomeOrderDto;
import com.dzmsoft.sms.base.pojo.SmsNewhomeOrder;
import com.dzmsoft.sms.base.pojo.SmsNewhomeOrderExample;
import com.dzmsoft.sms.base.pojo.SmsOrder;
import com.dzmsoft.sms.base.pojo.udf.SmsNewhomeOrderUdfExample;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * Copyright (C), dzmsoft Co., Ltd
 * @author dzmsoft
 * @date 2016-05-21 14:14:45
 *
 * @version 1.0
 */
public interface SmsNewhomeOrderService {
    /**
     * 新建新居开荒订单
     * @param smsOrder
     * @param smsNewhomeOrder
     * @return
     */
    int genSmsNewhomeOrder(SmsOrder smsOrder, SmsNewhomeOrder smsNewhomeOrder);
    /**
     * 派单
     * @param orderId
     * @param cleaners
     * @return
     */
    int send(String orderId, String id , String cleaners);

	/**
     * 查询符合条件的记录数量
     * @dzmsoftgenerated 2016-05-21 14:14:45
     */
	int countByExample(SmsNewhomeOrderExample example);
	
	/**
     * 根据条件插入记录
     * @dzmsoftgenerated 2016-05-21 14:14:45
     */
	int insertSelective(SmsNewhomeOrder record);
	
	/**
     * 根据主键查询记录
     * @dzmsoftgenerated 2016-05-21 14:14:45
     */
	SmsNewhomeOrder selectByPrimaryKey(String id);
	
	/**
     * 分页查询记录
     * @dzmsoftgenerated 2016-05-21 14:14:45
     */
	PageList<SmsNewhomeOrder> selectByExample(SmsNewhomeOrderExample example,PageBounds pageBounds);
	
	PageList<SmsNewhomeOrderDto> selectByExample(SmsNewhomeOrderUdfExample example,PageBounds pageBounds);
	
	/**
     * 根据条件查询记录
     * @dzmsoftgenerated 2016-05-21 14:14:45
     */
	List<SmsNewhomeOrder> selectByExample(SmsNewhomeOrderExample example);
	
	/**
     * 根据主键更新记录
     * @dzmsoftgenerated 2016-05-21 14:14:45
     */
	int updateByPrimaryKeySelective(SmsNewhomeOrder record);
	
	/**
     * 根据查询条件更新记录
     * @dzmsoftgenerated 2016-05-21 14:14:45
     */
	int updateByExampleSelective(SmsNewhomeOrder record,
			SmsNewhomeOrderExample example);
	
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
	int deleteByExample(SmsNewhomeOrderExample example);
	
	
}