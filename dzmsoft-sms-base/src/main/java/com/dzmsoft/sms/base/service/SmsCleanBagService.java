package com.dzmsoft.sms.base.service;

import java.util.List;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.dzmsoft.sms.base.pojo.SmsCleanBag;
import com.dzmsoft.sms.base.pojo.SmsCleanBagExample;
import com.dzmsoft.sms.base.pojo.SmsCleanBagLine;

/**
 * Copyright (C), dzmsoft Co., Ltd
 * @author 
 * @date 
 *
 * @version 
 */
public interface SmsCleanBagService {

	/**
     * 查询符合条件的记录数量
     * @dzmsoftgenerated 
     */
	int countByExample(SmsCleanBagExample example);
	
	/**
     * 根据条件插入记录
     * @dzmsoftgenerated 
     */
	int insertSelective(SmsCleanBag record);
	
	/**
     * 根据条件插入包含明细数据
     * @dzmsoftgenerated 
     */
	int insertContainDetails(SmsCleanBag record
			,List<SmsCleanBagLine> smsCleanBagLine
		);
	
	/**
     * 根据条件更新包含明细数据
     * @dzmsoftgenerated 
     */
	int updateContainDetails(SmsCleanBag record
			,List<SmsCleanBagLine> smsCleanBagLine
		);
	
	/**
     * 根据主键查询记录
     * @dzmsoftgenerated 
     */
	SmsCleanBag selectByPrimaryKey(String id);
	
	/**
     * 分页查询记录
     * @dzmsoftgenerated 
     */
	PageList<SmsCleanBag> selectByExample(SmsCleanBagExample example,PageBounds pageBounds);
	
	/**
     * 根据条件查询记录
     * @dzmsoftgenerated 
     */
	List<SmsCleanBag> selectByExample(SmsCleanBagExample example);
	
	/**
     * 根据主键更新记录
     * @dzmsoftgenerated 
     */
	int updateByPrimaryKeySelective(SmsCleanBag record);
	
	/**
     * 根据查询条件更新记录
     * @dzmsoftgenerated 
     */
	int updateByExampleSelective(SmsCleanBag record,
			SmsCleanBagExample example);
	
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
	int deleteByExample(SmsCleanBagExample example);
	
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