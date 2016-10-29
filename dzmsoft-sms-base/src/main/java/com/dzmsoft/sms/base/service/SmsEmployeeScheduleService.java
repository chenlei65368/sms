package com.dzmsoft.sms.base.service;

import java.util.Date;
import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.dzmsoft.framework.base.web.mvc.view.ComboBoxData;
import com.dzmsoft.sms.base.pojo.SmsEmployee;
import com.dzmsoft.sms.base.pojo.SmsEmployeeSchedule;
import com.dzmsoft.sms.base.pojo.SmsEmployeeScheduleExample;
import com.dzmsoft.sms.base.pojo.SmsShift;

/**
 * Copyright (C), dzmsoft Co., Ltd
 * @author dzmsoft
 * @date 2016-05-15 09:42:23
 *
 * @version 1.0
 */
public interface SmsEmployeeScheduleService {
    /**
     * 判断当前日期是否存在排班
     * @param day
     * @return
     */
    boolean isExist(Date day, String employee);
    
    /**
     * 排班
     * @param smsEmployees
     * @param dateBoxDatas
     * @param shift
     * @return
     */
    int arrange(List<SmsEmployee> smsEmployees, List<ComboBoxData> dateBoxDatas, SmsShift smsShift);
    
    /**
     * 获取近五天的排班
     * @param employee
     * @return
     */
    List<SmsEmployeeSchedule> selectNextFiveDays(String employee);

	/**
     * 查询符合条件的记录数量
     * @dzmsoftgenerated 2016-05-15 09:42:23
     */
	int countByExample(SmsEmployeeScheduleExample example);
	
	/**
     * 根据条件插入记录
     * @dzmsoftgenerated 2016-05-15 09:42:23
     */
	int insertSelective(SmsEmployeeSchedule record);
	
	/**
     * 根据主键查询记录
     * @dzmsoftgenerated 2016-05-15 09:42:23
     */
	SmsEmployeeSchedule selectByPrimaryKey(String id);
	
	/**
     * 分页查询记录
     * @dzmsoftgenerated 2016-05-15 09:42:23
     */
	PageList<SmsEmployeeSchedule> selectByExample(SmsEmployeeScheduleExample example,PageBounds pageBounds);
	
	/**
     * 根据条件查询记录
     * @dzmsoftgenerated 2016-05-15 09:42:23
     */
	List<SmsEmployeeSchedule> selectByExample(SmsEmployeeScheduleExample example);
	
	/**
     * 根据主键更新记录
     * @dzmsoftgenerated 2016-05-15 09:42:23
     */
	int updateByPrimaryKeySelective(SmsEmployeeSchedule record);
	
	/**
     * 根据查询条件更新记录
     * @dzmsoftgenerated 2016-05-15 09:42:23
     */
	int updateByExampleSelective(SmsEmployeeSchedule record,
			SmsEmployeeScheduleExample example);
	
	/**
     * 根据主键生成记录
     * @dzmsoftgenerated 2016-05-15 09:42:23
     */
	int deleteByPrimaryKey(String id);
	
	/**
	 * 根据条件删除字段信息
	 * @dzmsoftgenerated 2016-05-15 09:42:23
	 * @param example
	 * @return
	 */
	int deleteByExample(SmsEmployeeScheduleExample example);
	
	
}