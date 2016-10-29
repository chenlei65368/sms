package com.dzmsoft.sms.base.service;

import java.util.List;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.dzmsoft.sms.base.pojo.SmsShift;
import com.dzmsoft.sms.base.pojo.SmsShiftExample;

/**
 * Copyright (C), dzmsoft Co., Ltd
 * @author dzmsoft
 * @date 2016-05-14 22:26:11
 *
 * @version 1.0
 */
public interface SmsShiftService {
    /**
     * 根据id清单获取班次
     * @param ids
     * @return
     */
    List<SmsShift> getSmsShift(List<String> ids);
    /**
     * 更新班次状态
     * @param id
     * @param status
     * @return
     */
    int updateStatus(String id, String status );

	/**
     * 查询符合条件的记录数量
     * @dzmsoftgenerated 2016-05-14 22:26:11
     */
	int countByExample(SmsShiftExample example);
	
	/**
     * 根据条件插入记录
     * @dzmsoftgenerated 2016-05-14 22:26:11
     */
	int insertSelective(SmsShift record);
	
	/**
     * 根据主键查询记录
     * @dzmsoftgenerated 2016-05-14 22:26:11
     */
	SmsShift selectByPrimaryKey(String id);
	
	/**
     * 分页查询记录
     * @dzmsoftgenerated 2016-05-14 22:26:11
     */
	PageList<SmsShift> selectByExample(SmsShiftExample example,PageBounds pageBounds);
	
	/**
     * 根据条件查询记录
     * @dzmsoftgenerated 2016-05-14 22:26:11
     */
	List<SmsShift> selectByExample(SmsShiftExample example);
	
	/**
     * 根据主键更新记录
     * @dzmsoftgenerated 2016-05-14 22:26:11
     */
	int updateByPrimaryKeySelective(SmsShift record);
	
	/**
     * 根据查询条件更新记录
     * @dzmsoftgenerated 2016-05-14 22:26:11
     */
	int updateByExampleSelective(SmsShift record,
			SmsShiftExample example);
	
	/**
     * 根据主键生成记录
     * @dzmsoftgenerated 2016-05-14 22:26:11
     */
	int deleteByPrimaryKey(String id);
	
	/**
	 * 根据条件删除字段信息
	 * @dzmsoftgenerated 2016-05-14 22:26:11
	 * @param example
	 * @return
	 */
	int deleteByExample(SmsShiftExample example);
	
	
}