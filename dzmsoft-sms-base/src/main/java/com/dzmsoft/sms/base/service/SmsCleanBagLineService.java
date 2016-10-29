package com.dzmsoft.sms.base.service;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.dzmsoft.sms.base.pojo.SmsCleanBagLine;
import com.dzmsoft.sms.base.pojo.SmsCleanBagLineExample;

/**
 * Copyright (C), dzmsoft Co., Ltd
 * @author dzmsoft
 * @date 2016-06-13 13:10:38
 *
 * @version 1.0
 */
public interface SmsCleanBagLineService {
    /**
     * 查询明细
     * @param cleanBagId
     * @return
     */
    List<SmsCleanBagLine> selectByCleanBagId(String cleanBagId);
    
    /**
     * 删除明细
     * @param cleanBagId
     * @return
     */
    int deleteByCleanBagId(String cleanBagId);

	/**
     * 查询符合条件的记录数量
     * @dzmsoftgenerated 2016-06-13 13:10:38
     */
	int countByExample(SmsCleanBagLineExample example);
	
	/**
     * 根据条件插入记录
     * @dzmsoftgenerated 2016-06-13 13:10:38
     */
	int insertSelective(SmsCleanBagLine record);
	
	/**
     * 根据主键查询记录
     * @dzmsoftgenerated 2016-06-13 13:10:38
     */
	SmsCleanBagLine selectByPrimaryKey(String id);
	
	/**
     * 分页查询记录
     * @dzmsoftgenerated 2016-06-13 13:10:38
     */
	PageList<SmsCleanBagLine> selectByExample(SmsCleanBagLineExample example,PageBounds pageBounds);
	
	/**
     * 根据条件查询记录
     * @dzmsoftgenerated 2016-06-13 13:10:38
     */
	List<SmsCleanBagLine> selectByExample(SmsCleanBagLineExample example);
	
	/**
     * 根据主键更新记录
     * @dzmsoftgenerated 2016-06-13 13:10:38
     */
	int updateByPrimaryKeySelective(SmsCleanBagLine record);
	
	/**
     * 根据查询条件更新记录
     * @dzmsoftgenerated 2016-06-13 13:10:38
     */
	int updateByExampleSelective(SmsCleanBagLine record,
			SmsCleanBagLineExample example);
	
	/**
     * 根据主键生成记录
     * @dzmsoftgenerated 2016-06-13 13:10:38
     */
	int deleteByPrimaryKey(String id);
	
	/**
	 * 根据条件删除字段信息
	 * @dzmsoftgenerated 2016-06-13 13:10:38
	 * @param example
	 * @return
	 */
	int deleteByExample(SmsCleanBagLineExample example);
	
	
}