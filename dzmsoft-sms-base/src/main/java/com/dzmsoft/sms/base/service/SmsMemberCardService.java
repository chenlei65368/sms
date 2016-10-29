package com.dzmsoft.sms.base.service;

import java.util.List;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.dzmsoft.sms.base.pojo.SmsMemberCard;
import com.dzmsoft.sms.base.pojo.SmsMemberCardExample;

/**
 * Copyright (C), dzmsoft Co., Ltd
 * @author dzmsoft
 * @date 2016-06-09 17:00:07
 *
 * @version 1.0
 */
public interface SmsMemberCardService {

	/**
     * 查询符合条件的记录数量
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
	int countByExample(SmsMemberCardExample example);
	
	/**
     * 根据条件插入记录
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
	int insertSelective(SmsMemberCard record);
	
	/**
     * 根据主键查询记录
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
	SmsMemberCard selectByPrimaryKey(String id);
	
	/**
     * 分页查询记录
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
	PageList<SmsMemberCard> selectByExample(SmsMemberCardExample example,PageBounds pageBounds);
	
	/**
     * 根据条件查询记录
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
	List<SmsMemberCard> selectByExample(SmsMemberCardExample example);
	
	/**
     * 根据主键更新记录
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
	int updateByPrimaryKeySelective(SmsMemberCard record);
	
	/**
     * 根据查询条件更新记录
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
	int updateByExampleSelective(SmsMemberCard record,
			SmsMemberCardExample example);
	
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
	int deleteByExample(SmsMemberCardExample example);
	
	
}