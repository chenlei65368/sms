package com.dzmsoft.sms.base.service;

import java.util.List;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.dzmsoft.sms.base.pojo.SmsMallBrand;
import com.dzmsoft.sms.base.pojo.SmsMallBrandExample;

/**
 * Copyright (C), dzmsoft Co., Ltd
 * @author dzmsoft
 * @date 2016-05-04 17:10:32
 *
 * @version 1.0
 */
public interface SmsMallBrandService {
    /**
     * 根据品类生成品牌
     * @param category
     * @return
     */
    String selectTopIndex(String category);

	/**
     * 查询符合条件的记录数量
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
	int countByExample(SmsMallBrandExample example);
	
	/**
     * 根据条件插入记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
	int insertSelective(SmsMallBrand record);
	
	/**
     * 根据主键查询记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
	SmsMallBrand selectByPrimaryKey(String id);
	
	/**
     * 分页查询记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
	PageList<SmsMallBrand> selectByExample(SmsMallBrandExample example,PageBounds pageBounds);
	
	/**
     * 根据条件查询记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
	List<SmsMallBrand> selectByExample(SmsMallBrandExample example);
	
	/**
     * 根据主键更新记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
	int updateByPrimaryKeySelective(SmsMallBrand record);
	
	/**
     * 根据查询条件更新记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
	int updateByExampleSelective(SmsMallBrand record,
			SmsMallBrandExample example);
	
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
	int deleteByExample(SmsMallBrandExample example);
	
	
}