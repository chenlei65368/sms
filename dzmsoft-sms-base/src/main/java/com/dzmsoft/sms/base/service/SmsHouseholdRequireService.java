package com.dzmsoft.sms.base.service;

import java.util.List;

import com.dzmsoft.sms.base.pojo.SmsHouseholdRequire;
import com.dzmsoft.sms.base.pojo.SmsHouseholdRequireExample;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * Copyright (C), dzmsoft Co., Ltd
 * @author dzmsoft
 * @date 2016-06-09 17:00:07
 *
 * @version 1.0
 */
public interface SmsHouseholdRequireService {
    /**
     * 批量插入个性需求
     * @param orderId
     * @param smsHouseholdRequireList
     * @return
     */
    int insertBatch(String orderId, List<SmsHouseholdRequire> smsHouseholdRequireList);

	/**
     * 查询符合条件的记录数量
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
	int countByExample(SmsHouseholdRequireExample example);
	
	/**
     * 根据条件插入记录
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
	int insertSelective(SmsHouseholdRequire record);
	
	/**
     * 根据主键查询记录
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
	SmsHouseholdRequire selectByPrimaryKey(String id);
	
	/**
     * 分页查询记录
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
	PageList<SmsHouseholdRequire> selectByExample(SmsHouseholdRequireExample example,PageBounds pageBounds);
	
	/**
     * 根据条件查询记录
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
	List<SmsHouseholdRequire> selectByExample(SmsHouseholdRequireExample example);
	
	/**
     * 根据主键更新记录
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
	int updateByPrimaryKeySelective(SmsHouseholdRequire record);
	
	/**
     * 根据查询条件更新记录
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
	int updateByExampleSelective(SmsHouseholdRequire record,
			SmsHouseholdRequireExample example);
	
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
	int deleteByExample(SmsHouseholdRequireExample example);
	
	
}