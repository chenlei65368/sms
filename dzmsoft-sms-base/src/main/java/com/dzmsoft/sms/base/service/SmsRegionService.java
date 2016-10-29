package com.dzmsoft.sms.base.service;

import java.util.List;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.dzmsoft.sms.base.pojo.SmsRegion;
import com.dzmsoft.sms.base.pojo.SmsRegionExample;

/**
 * Copyright (C), dzmsoft Co., Ltd
 * @author dzmsoft
 * @date 2016-06-09 16:56:23
 *
 * @version 1.0
 */
public interface SmsRegionService {

	/**
     * 查询符合条件的记录数量
     * @dzmsoftgenerated 2016-06-09 16:56:23
     */
	int countByExample(SmsRegionExample example);
	
	/**
     * 根据条件插入记录
     * @dzmsoftgenerated 2016-06-09 16:56:23
     */
	int insertSelective(SmsRegion record);
	
	/**
     * 根据主键查询记录
     * @dzmsoftgenerated 2016-06-09 16:56:23
     */
	SmsRegion selectByPrimaryKey(String id);
	
	/**
     * 分页查询记录
     * @dzmsoftgenerated 2016-06-09 16:56:23
     */
	PageList<SmsRegion> selectByExample(SmsRegionExample example,PageBounds pageBounds);
	
	/**
     * 根据条件查询记录
     * @dzmsoftgenerated 2016-06-09 16:56:23
     */
	List<SmsRegion> selectByExample(SmsRegionExample example);
	
	/**
     * 根据主键更新记录
     * @dzmsoftgenerated 2016-06-09 16:56:23
     */
	int updateByPrimaryKeySelective(SmsRegion record);
	
	/**
     * 根据查询条件更新记录
     * @dzmsoftgenerated 2016-06-09 16:56:23
     */
	int updateByExampleSelective(SmsRegion record,
			SmsRegionExample example);
	
	/**
     * 根据主键生成记录
     * @dzmsoftgenerated 2016-06-09 16:56:23
     */
	int deleteByPrimaryKey(String id);
	
	/**
	 * 根据条件删除字段信息
	 * @dzmsoftgenerated 2016-06-09 16:56:23
	 * @param example
	 * @return
	 */
	int deleteByExample(SmsRegionExample example);
	
	
}