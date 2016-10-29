package com.dzmsoft.sms.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.dzmsoft.sms.base.pojo.SmsCommodityShoppingCart;
import com.dzmsoft.sms.base.pojo.SmsCommodityShoppingCartExample;
import com.dzmsoft.sms.base.dao.SmsCommodityShoppingCartMapper;
import com.dzmsoft.sms.base.service.SmsCommodityShoppingCartService;
/**
 * Copyright (C), dzmsoft Co., Ltd
 * @author dzmsoft
 * @date 2016-06-13 13:00:43
 *
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class SmsCommodityShoppingCartServiceImpl implements SmsCommodityShoppingCartService{

	@Autowired
	private SmsCommodityShoppingCartMapper smsCommodityShoppingCartMapper;
	
	/**
     * 查询符合条件的记录数量
     * @dzmsoftgenerated 2016-06-13 13:00:43
     */
    @Override
	public int countByExample(SmsCommodityShoppingCartExample example){
		return smsCommodityShoppingCartMapper.countByExample(example);
	}

	/**
     * 根据条件插入记录
     * @dzmsoftgenerated 2016-06-13 13:00:43
     */
    @Transactional(readOnly = false)
    @Override
	public int insertSelective(SmsCommodityShoppingCart record){
		return smsCommodityShoppingCartMapper.insertSelective(record);
	}
	
	/**
     * 根据主键查询记录
     * @dzmsoftgenerated 2016-06-13 13:00:43
     */
    @Override
	public SmsCommodityShoppingCart selectByPrimaryKey(String id){
		return smsCommodityShoppingCartMapper.selectByPrimaryKey(id);
	}
	
	/**
     * 分页查询记录
     * @dzmsoftgenerated 2016-06-13 13:00:43
     */
    @Override
	public PageList<SmsCommodityShoppingCart> selectByExample(SmsCommodityShoppingCartExample example,PageBounds pageBounds){
		return smsCommodityShoppingCartMapper.selectByExample(example, pageBounds);
	}
	
	/**
     * 根据条件查询记录
     * @dzmsoftgenerated 2016-06-13 13:00:43
     */
    @Override
	public List<SmsCommodityShoppingCart> selectByExample(SmsCommodityShoppingCartExample example){
		return smsCommodityShoppingCartMapper.selectByExample(example);
	}
	
	/**
     * 根据主键更新记录
     * @dzmsoftgenerated 2016-06-13 13:00:43
     */
    @Transactional(readOnly = false)
    @Override
	public int updateByPrimaryKeySelective(SmsCommodityShoppingCart record){
		return smsCommodityShoppingCartMapper.updateByPrimaryKeySelective(record);
	}
	
	/**
     * 根据查询条件更新记录
     * @dzmsoftgenerated 2016-06-13 13:00:43
     */
    @Transactional(readOnly = false)
    @Override
	public int updateByExampleSelective(SmsCommodityShoppingCart record,
			SmsCommodityShoppingCartExample example){
		return smsCommodityShoppingCartMapper.updateByExampleSelective(record, example);
	}
	
	/**
     * 根据主键生成记录
     * @dzmsoftgenerated 2016-06-13 13:00:43
     */
    @Transactional(readOnly = false)
    @Override
	public int deleteByPrimaryKey(String id){
		return smsCommodityShoppingCartMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 根据条件删除字段信息
	 * @dzmsoftgenerated 2016-06-13 13:00:43
	 * @param example
	 * @return
	 */
	@Transactional(readOnly = false)
	@Override
	public int deleteByExample(SmsCommodityShoppingCartExample example){
		return smsCommodityShoppingCartMapper.deleteByExample(example);
	}
}