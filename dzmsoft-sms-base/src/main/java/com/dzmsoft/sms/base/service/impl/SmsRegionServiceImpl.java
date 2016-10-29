package com.dzmsoft.sms.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.dzmsoft.sms.base.pojo.SmsRegion;
import com.dzmsoft.sms.base.pojo.SmsRegionExample;
import com.dzmsoft.sms.base.dao.SmsRegionMapper;
import com.dzmsoft.sms.base.service.SmsRegionService;
/**
 * Copyright (C), dzmsoft Co., Ltd
 * @author dzmsoft
 * @date 2016-06-09 16:56:23
 *
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class SmsRegionServiceImpl implements SmsRegionService{

	@Autowired
	private SmsRegionMapper smsRegionMapper;
	
	/**
     * 查询符合条件的记录数量
     * @dzmsoftgenerated 2016-06-09 16:56:23
     */
    @Override
	public int countByExample(SmsRegionExample example){
		return smsRegionMapper.countByExample(example);
	}

	/**
     * 根据条件插入记录
     * @dzmsoftgenerated 2016-06-09 16:56:23
     */
    @Transactional(readOnly = false)
    @Override
	public int insertSelective(SmsRegion record){
		return smsRegionMapper.insertSelective(record);
	}
	
	/**
     * 根据主键查询记录
     * @dzmsoftgenerated 2016-06-09 16:56:23
     */
    @Override
	public SmsRegion selectByPrimaryKey(String id){
		return smsRegionMapper.selectByPrimaryKey(id);
	}
	
	/**
     * 分页查询记录
     * @dzmsoftgenerated 2016-06-09 16:56:23
     */
    @Override
	public PageList<SmsRegion> selectByExample(SmsRegionExample example,PageBounds pageBounds){
		return smsRegionMapper.selectByExample(example, pageBounds);
	}
	
	/**
     * 根据条件查询记录
     * @dzmsoftgenerated 2016-06-09 16:56:23
     */
    @Override
	public List<SmsRegion> selectByExample(SmsRegionExample example){
		return smsRegionMapper.selectByExample(example);
	}
	
	/**
     * 根据主键更新记录
     * @dzmsoftgenerated 2016-06-09 16:56:23
     */
    @Transactional(readOnly = false)
    @Override
	public int updateByPrimaryKeySelective(SmsRegion record){
		return smsRegionMapper.updateByPrimaryKeySelective(record);
	}
	
	/**
     * 根据查询条件更新记录
     * @dzmsoftgenerated 2016-06-09 16:56:23
     */
    @Transactional(readOnly = false)
    @Override
	public int updateByExampleSelective(SmsRegion record,
			SmsRegionExample example){
		return smsRegionMapper.updateByExampleSelective(record, example);
	}
	
	/**
     * 根据主键生成记录
     * @dzmsoftgenerated 2016-06-09 16:56:23
     */
    @Transactional(readOnly = false)
    @Override
	public int deleteByPrimaryKey(String id){
		return smsRegionMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 根据条件删除字段信息
	 * @dzmsoftgenerated 2016-06-09 16:56:23
	 * @param example
	 * @return
	 */
	@Transactional(readOnly = false)
	@Override
	public int deleteByExample(SmsRegionExample example){
		return smsRegionMapper.deleteByExample(example);
	}
}