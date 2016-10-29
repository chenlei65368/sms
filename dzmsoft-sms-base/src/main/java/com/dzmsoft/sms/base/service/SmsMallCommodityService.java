package com.dzmsoft.sms.base.service;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.dzmsoft.sms.base.pojo.SmsMallCommodity;
import com.dzmsoft.sms.base.pojo.SmsMallCommodityExample;

/**
 * Copyright (C), dzmsoft Co., Ltd
 * @author dzmsoft
 * @date 2016-05-04 17:10:32
 *
 * @version 1.0
 */
public interface SmsMallCommodityService {
    /**
     * 根据城市获取商品清单
     * @param cityId
     * @param pageBounds
     * @return
     */
    List<SmsMallCommodity> selectByCity(String cityId, PageBounds pageBounds);
    /**
     * 根据品牌生成商品编号
     * @return
     */
    String selectTopIndex(String brandId);
    /**
     * 设置状态
     * @param id
     * @param status
     * @return
     */
    int setStatus(String id, String status);
    /**
     * 上架
     * @param id
     * @return
     */
    int online(String id);
    /**
     * 下架
     * @param id
     * @return
     */
    int offline(String id);

	/**
     * 查询符合条件的记录数量
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
	int countByExample(SmsMallCommodityExample example);
	
	/**
     * 根据条件插入记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
	int insertSelective(SmsMallCommodity record);
	
	/**
     * 根据主键查询记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
	SmsMallCommodity selectByPrimaryKey(String id);
	
	/**
     * 分页查询记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
	PageList<SmsMallCommodity> selectByExample(SmsMallCommodityExample example,PageBounds pageBounds);
	
	/**
     * 根据条件查询记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
	List<SmsMallCommodity> selectByExample(SmsMallCommodityExample example);
	
	/**
     * 根据主键更新记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
	int updateByPrimaryKeySelective(SmsMallCommodity record);
	
	/**
     * 根据查询条件更新记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
	int updateByExampleSelective(SmsMallCommodity record,
			SmsMallCommodityExample example);
	
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
	int deleteByExample(SmsMallCommodityExample example);
	
	
}