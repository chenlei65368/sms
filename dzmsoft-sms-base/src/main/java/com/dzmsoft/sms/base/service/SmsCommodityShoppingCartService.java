package com.dzmsoft.sms.base.service;

import java.util.List;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.dzmsoft.sms.base.pojo.SmsCommodityShoppingCart;
import com.dzmsoft.sms.base.pojo.SmsCommodityShoppingCartExample;

/**
 * Copyright (C), dzmsoft Co., Ltd
 * @author dzmsoft
 * @date 2016-06-13 13:00:42
 *
 * @version 1.0
 */
public interface SmsCommodityShoppingCartService {

	/**
     * 查询符合条件的记录数量
     * @dzmsoftgenerated 2016-06-13 13:00:42
     */
	int countByExample(SmsCommodityShoppingCartExample example);
	
	/**
     * 根据条件插入记录
     * @dzmsoftgenerated 2016-06-13 13:00:42
     */
	int insertSelective(SmsCommodityShoppingCart record);
	
	/**
     * 根据主键查询记录
     * @dzmsoftgenerated 2016-06-13 13:00:42
     */
	SmsCommodityShoppingCart selectByPrimaryKey(String id);
	
	/**
     * 分页查询记录
     * @dzmsoftgenerated 2016-06-13 13:00:42
     */
	PageList<SmsCommodityShoppingCart> selectByExample(SmsCommodityShoppingCartExample example,PageBounds pageBounds);
	
	/**
     * 根据条件查询记录
     * @dzmsoftgenerated 2016-06-13 13:00:42
     */
	List<SmsCommodityShoppingCart> selectByExample(SmsCommodityShoppingCartExample example);
	
	/**
     * 根据主键更新记录
     * @dzmsoftgenerated 2016-06-13 13:00:42
     */
	int updateByPrimaryKeySelective(SmsCommodityShoppingCart record);
	
	/**
     * 根据查询条件更新记录
     * @dzmsoftgenerated 2016-06-13 13:00:42
     */
	int updateByExampleSelective(SmsCommodityShoppingCart record,
			SmsCommodityShoppingCartExample example);
	
	/**
     * 根据主键生成记录
     * @dzmsoftgenerated 2016-06-13 13:00:42
     */
	int deleteByPrimaryKey(String id);
	
	/**
	 * 根据条件删除字段信息
	 * @dzmsoftgenerated 2016-06-13 13:00:42
	 * @param example
	 * @return
	 */
	int deleteByExample(SmsCommodityShoppingCartExample example);
	
	
}