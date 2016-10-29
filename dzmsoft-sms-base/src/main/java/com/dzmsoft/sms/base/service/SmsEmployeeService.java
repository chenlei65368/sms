package com.dzmsoft.sms.base.service;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.dzmsoft.sms.base.pojo.SmsEmployee;
import com.dzmsoft.sms.base.pojo.SmsEmployeeExample;
import com.dzmsoft.sms.base.pojo.udf.SmsEmployeeScheduleUdfExample;

/**
 * Copyright (C), dzmsoft Co., Ltd
 * @author dzmsoft
 * @date 2016-05-04 17:10:32
 *
 * @version 1.0
 */
public interface SmsEmployeeService {
    /**
     * 获取服务过的保洁员
     * @param member
     * @param pageBounds
     * @return
     */
    List<SmsEmployee> selectServiceCleaners(String member, PageBounds pageBounds);
    /**
     * 查询当前城市的保洁员
     * @param city
     * @param pageBounds
     * @return
     */
    PageList<SmsEmployee> selectCleanersByCity(String city, PageBounds pageBounds);
    /**
     * 根据ucsId获取员工信息
     * @param ucsId
     * @return
     */
    SmsEmployee selectByUcsId(String ucsId);
    /**
     * 查询没有排班的员工
     * @param example
     * @param pageBounds
     * @return
     */
    PageList<SmsEmployee> selectNoSchedule(SmsEmployeeScheduleUdfExample example, PageBounds pageBounds);
    
    /**
     * 根据前置获取最新的编号
     * @param prefix
     * @return
     */
    String selectTopIndex(String prefix);
    /**
     * 离职
     * @param id
     * @return
     */
    int quit(String id);

	/**
     * 查询符合条件的记录数量
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
	int countByExample(SmsEmployeeExample example);
	
	/**
     * 根据条件插入记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
	int insertSelective(SmsEmployee record);
	
	/**
     * 根据主键查询记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
	SmsEmployee selectByPrimaryKey(String id);
	
	/**
     * 分页查询记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
	PageList<SmsEmployee> selectByExample(SmsEmployeeExample example,PageBounds pageBounds);
	
	/**
     * 根据条件查询记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
	List<SmsEmployee> selectByExample(SmsEmployeeExample example);
	
	/**
     * 根据主键更新记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
	int updateByPrimaryKeySelective(SmsEmployee record);
	
	/**
     * 根据查询条件更新记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
	int updateByExampleSelective(SmsEmployee record,
			SmsEmployeeExample example);
	
	/**
     * 根据主键生成记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
	int deleteByPrimaryKey(String id);
	
	/**
	 * 根据条件删除字段信息
	 * @dzmsoftgenerated 2016-05-04 17:10:32
	 * @param example
	 * @return
	 */
	int deleteByExample(SmsEmployeeExample example);
	
	
}