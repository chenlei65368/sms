package com.dzmsoft.sms.base.service;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.dzmsoft.sms.base.dto.SmsEmployeeScheduleArrangeDto;
import com.dzmsoft.sms.base.pojo.SmsEmployee;
import com.dzmsoft.sms.base.pojo.SmsEmployeeShift;
import com.dzmsoft.sms.base.pojo.SmsEmployeeShiftExample;

/**
 * Copyright (C), dzmsoft Co., Ltd
 * @author 
 * @date 
 *
 * @version 
 */
public interface SmsEmployeeShiftService {
	/**
	 * 根据排班的人员、排班条件，进行排班
	 * @param smsEmployees
	 * @param smsEmployeeScheduleArrangeDto
	 * @return
	 */
    int arrage(List<SmsEmployee> smsEmployees, SmsEmployeeScheduleArrangeDto smsEmployeeScheduleArrangeDto);
	/**
     * 查询符合条件的记录数量
     * @dzmsoftgenerated 
     */
	int countByExample(SmsEmployeeShiftExample example);
	
	/**
     * 根据条件插入记录
     * @dzmsoftgenerated 
     */
	int insertSelective(SmsEmployeeShift record);
	
	/**
     * 根据主键查询记录
     * @dzmsoftgenerated 
     */
	SmsEmployeeShift selectByPrimaryKey(String id);
	
	/**
     * 分页查询记录
     * @dzmsoftgenerated 
     */
	PageList<SmsEmployeeShift> selectByExample(SmsEmployeeShiftExample example,PageBounds pageBounds);
	
	/**
     * 根据条件查询记录
     * @dzmsoftgenerated 
     */
	List<SmsEmployeeShift> selectByExample(SmsEmployeeShiftExample example);
	
	/**
     * 根据主键更新记录
     * @dzmsoftgenerated 
     */
	int updateByPrimaryKeySelective(SmsEmployeeShift record);
	
	/**
     * 根据查询条件更新记录
     * @dzmsoftgenerated 
     */
	int updateByExampleSelective(SmsEmployeeShift record,
			SmsEmployeeShiftExample example);
	
	/**
     * 根据主键生成记录
     * @dzmsoftgenerated 
     */
	int deleteByPrimaryKey(String id);
	
	/**
	 * 根据条件删除字段信息
	 * @dzmsoftgenerated 
	 * @param example
	 * @return
	 */
	int deleteByExample(SmsEmployeeShiftExample example);
	
		
	
}