package com.dzmsoft.sms.base.service;

import java.util.List;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.dzmsoft.sms.base.pojo.SmsHouseholdBag;
import com.dzmsoft.sms.base.pojo.SmsHouseholdBagExample;

/**
 * Copyright (C), dzmsoft Co., Ltd
 * @author dzmsoft
 * @date 2016-06-09 17:00:07
 *
 * @version 1.0
 */
public interface SmsHouseholdBagService {
    /**
     * 批量插入清洁包
     * @param orderId
     * @param smsHouseholdBagList
     * @return
     */
    int insertBatch(String orderId, List<SmsHouseholdBag> smsHouseholdBagList);

	/**
     * 查询符合条件的记录数量
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
	int countByExample(SmsHouseholdBagExample example);
	
	/**
     * 根据条件插入记录
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
	int insertSelective(SmsHouseholdBag record);
	
	/**
     * 根据主键查询记录
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
	SmsHouseholdBag selectByPrimaryKey(String id);
	
	/**
     * 分页查询记录
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
	PageList<SmsHouseholdBag> selectByExample(SmsHouseholdBagExample example,PageBounds pageBounds);
	
	/**
     * 根据条件查询记录
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
	List<SmsHouseholdBag> selectByExample(SmsHouseholdBagExample example);
	
	/**
     * 根据主键更新记录
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
	int updateByPrimaryKeySelective(SmsHouseholdBag record);
	
	/**
     * 根据查询条件更新记录
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
	int updateByExampleSelective(SmsHouseholdBag record,
			SmsHouseholdBagExample example);
	
	/**
     * 根据主键生成记录
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
	int deleteByPrimaryKey(String id);
	
	/**
	 * 根据条件删除字段信息
	 * @dzmsoftgenerated 2016-06-09 17:00:07
	 * @param example
	 * @return
	 */
	int deleteByExample(SmsHouseholdBagExample example);
	
	
}