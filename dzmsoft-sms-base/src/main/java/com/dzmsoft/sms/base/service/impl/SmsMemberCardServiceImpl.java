package com.dzmsoft.sms.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.dzmsoft.sms.base.pojo.SmsMemberCard;
import com.dzmsoft.sms.base.pojo.SmsMemberCardExample;
import com.dzmsoft.sms.base.dao.SmsMemberCardMapper;
import com.dzmsoft.sms.base.service.SmsMemberCardService;
/**
 * Copyright (C), dzmsoft Co., Ltd
 * @author dzmsoft
 * @date 2016-06-09 17:00:07
 *
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class SmsMemberCardServiceImpl implements SmsMemberCardService{

	@Autowired
	private SmsMemberCardMapper smsMemberCardMapper;
	
	/**
     * 查询符合条件的记录数量
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
    @Override
	public int countByExample(SmsMemberCardExample example){
		return smsMemberCardMapper.countByExample(example);
	}

	/**
     * 根据条件插入记录
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
    @Transactional(readOnly = false)
    @Override
	public int insertSelective(SmsMemberCard record){
		return smsMemberCardMapper.insertSelective(record);
	}
	
	/**
     * 根据主键查询记录
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
    @Override
	public SmsMemberCard selectByPrimaryKey(String id){
		return smsMemberCardMapper.selectByPrimaryKey(id);
	}
	
	/**
     * 分页查询记录
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
    @Override
	public PageList<SmsMemberCard> selectByExample(SmsMemberCardExample example,PageBounds pageBounds){
		return smsMemberCardMapper.selectByExample(example, pageBounds);
	}
	
	/**
     * 根据条件查询记录
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
    @Override
	public List<SmsMemberCard> selectByExample(SmsMemberCardExample example){
		return smsMemberCardMapper.selectByExample(example);
	}
	
	/**
     * 根据主键更新记录
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
    @Transactional(readOnly = false)
    @Override
	public int updateByPrimaryKeySelective(SmsMemberCard record){
		return smsMemberCardMapper.updateByPrimaryKeySelective(record);
	}
	
	/**
     * 根据查询条件更新记录
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
    @Transactional(readOnly = false)
    @Override
	public int updateByExampleSelective(SmsMemberCard record,
			SmsMemberCardExample example){
		return smsMemberCardMapper.updateByExampleSelective(record, example);
	}
	
	/**
     * 根据主键生成记录
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
    @Transactional(readOnly = false)
    @Override
	public int deleteByPrimaryKey(String id){
		return smsMemberCardMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 根据条件删除字段信息
	 * @dzmsoftgenerated 2016-06-09 17:00:07
	 * @param example
	 * @return
	 */
	@Transactional(readOnly = false)
	@Override
	public int deleteByExample(SmsMemberCardExample example){
		return smsMemberCardMapper.deleteByExample(example);
	}
}