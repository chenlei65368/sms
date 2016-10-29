package com.dzmsoft.sms.base.service;

import java.util.List;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.dzmsoft.sms.base.pojo.SmsPersonRequire;
import com.dzmsoft.sms.base.pojo.SmsPersonRequireExample;
import com.dzmsoft.sms.base.pojo.SmsPersonRequireLine;

/**
 * Copyright (C), dzmsoft Co., Ltd
 * @author 
 * @date 
 *
 * @version 
 */
public interface SmsPersonRequireService {

	

	/**
     * 查询符合条件的记录数量
     * @dzmsoftgenerated 
     */
	int countByExample(SmsPersonRequireExample example);
	
	/**
     * 根据条件插入记录
     * @dzmsoftgenerated 
     */
	int insertSelective(SmsPersonRequire record);
	
	/**
     * 根据条件插入包含明细数据
     * @dzmsoftgenerated 
     */
	int insertContainDetails(SmsPersonRequire record
			,List<SmsPersonRequireLine> smsPersonRequireLine
		);
	
	/**
     * 根据条件更新包含明细数据
     * @dzmsoftgenerated 
     */
	int updateContainDetails(SmsPersonRequire record
			,List<SmsPersonRequireLine> smsPersonRequireLine
		);
	
	/**
     * 根据主键查询记录
     * @dzmsoftgenerated 
     */
	SmsPersonRequire selectByPrimaryKey(String id);
	
	/**
     * 分页查询记录
     * @dzmsoftgenerated 
     */
	PageList<SmsPersonRequire> selectByExample(SmsPersonRequireExample example,PageBounds pageBounds);
	
	/**
     * 根据条件查询记录
     * @dzmsoftgenerated 
     */
	List<SmsPersonRequire> selectByExample(SmsPersonRequireExample example);
	
	/**
     * 根据主键更新记录
     * @dzmsoftgenerated 
     */
	int updateByPrimaryKeySelective(SmsPersonRequire record);
	
	/**
     * 根据查询条件更新记录
     * @dzmsoftgenerated 
     */
	int updateByExampleSelective(SmsPersonRequire record,
			SmsPersonRequireExample example);
	
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
	int deleteByExample(SmsPersonRequireExample example);
	
		/**
	 * 启用
	 * @dzmsoftgenerated 
	 * @param id
	 * @return
	 */
	int enable(String id);
	
	/**
	 * 启用
	 * @dzmsoftgenerated 
	 * @param id
	 * @return
	 */
	int disable(String id);
	}