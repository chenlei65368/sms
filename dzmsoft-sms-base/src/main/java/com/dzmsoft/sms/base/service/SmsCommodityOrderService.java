package com.dzmsoft.sms.base.service;

import java.util.List;

import com.dzmsoft.sms.base.dto.SmsCommodityOrderDto;
import com.dzmsoft.sms.base.pojo.SmsCommodityOrder;
import com.dzmsoft.sms.base.pojo.SmsCommodityOrderExample;
import com.dzmsoft.sms.base.pojo.SmsOrder;
import com.dzmsoft.sms.base.pojo.udf.SmsCommodityOrderUdfExample;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * Copyright (C), dzmsoft Co., Ltd
 * @author dzmsoft
 * @date 2016-05-29 09:15:25
 *
 * @version 1.0
 */
public interface SmsCommodityOrderService {
    /**
     * 创建商品订单
     * @param smsOrder
     * @param smsCommodityOrderList
     * @return
     */
    int genSmsCommodityOrder(SmsOrder smsOrder, List<SmsCommodityOrder> smsCommodityOrderList);
    /**
     * 派单
     * @param orderId
     * @param id
     * @param cleaner
     * @return
     */
    int send(String orderId, String cleaner);

	/**
     * 查询符合条件的记录数量
     * @dzmsoftgenerated 2016-05-29 09:15:25
     */
	int countByExample(SmsCommodityOrderExample example);
	
	/**
     * 根据条件插入记录
     * @dzmsoftgenerated 2016-05-29 09:15:25
     */
	int insertSelective(SmsCommodityOrder record);
	
	/**
     * 根据主键查询记录
     * @dzmsoftgenerated 2016-05-29 09:15:25
     */
	SmsCommodityOrder selectByPrimaryKey(String id);
	
	/**
     * 分页查询记录
     * @dzmsoftgenerated 2016-05-29 09:15:25
     */
	PageList<SmsCommodityOrderDto> selectByExample(SmsCommodityOrderUdfExample example,PageBounds pageBounds);
	
	/**
     * 根据条件查询记录
     * @dzmsoftgenerated 2016-05-29 09:15:25
     */
	List<SmsCommodityOrder> selectByExample(SmsCommodityOrderExample example);
	
	/**
     * 根据主键更新记录
     * @dzmsoftgenerated 2016-05-29 09:15:25
     */
	int updateByPrimaryKeySelective(SmsCommodityOrder record);
	
	/**
     * 根据查询条件更新记录
     * @dzmsoftgenerated 2016-05-29 09:15:25
     */
	int updateByExampleSelective(SmsCommodityOrder record,
			SmsCommodityOrderExample example);
	
	/**
     * 根据主键生成记录
     * @dzmsoftgenerated 2016-05-29 09:15:25
     */
	int deleteByPrimaryKey(String id);
	
	/**
	 * 根据条件删除字段信息
	 * @dzmsoftgenerated 2016-05-29 09:15:25
	 * @param example
	 * @return
	 */
	int deleteByExample(SmsCommodityOrderExample example);
	
	
}