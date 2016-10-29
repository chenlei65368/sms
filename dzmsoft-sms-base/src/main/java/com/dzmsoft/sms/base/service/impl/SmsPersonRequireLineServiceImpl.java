package com.dzmsoft.sms.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.dzmsoft.sms.base.pojo.SmsPersonRequireLine;
import com.dzmsoft.sms.base.pojo.SmsPersonRequireLineExample;
import com.dzmsoft.sms.base.dao.SmsPersonRequireLineMapper;
import com.dzmsoft.sms.base.service.SmsPersonRequireLineService;
/**
 * Copyright (C), dzmsoft Co., Ltd
 * @author dzmsoft
 * @date 2016-06-09 17:00:07
 *
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class SmsPersonRequireLineServiceImpl implements SmsPersonRequireLineService{

	@Autowired
	private SmsPersonRequireLineMapper smsPersonRequireLineMapper;
	
	@Override
	public List<SmsPersonRequireLine> selectBySmsPersonRequire(String requireId) {
	    SmsPersonRequireLineExample example = new SmsPersonRequireLineExample();
        SmsPersonRequireLineExample.Criteria criteria = example.createCriteria();
        criteria.andRequireIdEqualTo(requireId);
	    return selectByExample(example);
	}
	
	@Transactional(readOnly = false)
	@Override
	public int deleteBySmsPersonRequire(String requireId) {
	    SmsPersonRequireLineExample example = new SmsPersonRequireLineExample();
	    SmsPersonRequireLineExample.Criteria criteria = example.createCriteria();
	    criteria.andRequireIdEqualTo(requireId);
	    return deleteByExample(example);
	}
	
	/**
     * 查询符合条件的记录数量
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
    @Override
	public int countByExample(SmsPersonRequireLineExample example){
		return smsPersonRequireLineMapper.countByExample(example);
	}

	/**
     * 根据条件插入记录
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
    @Transactional(readOnly = false)
    @Override
	public int insertSelective(SmsPersonRequireLine record){
		return smsPersonRequireLineMapper.insertSelective(record);
	}
	
	/**
     * 根据主键查询记录
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
    @Override
	public SmsPersonRequireLine selectByPrimaryKey(String id){
		return smsPersonRequireLineMapper.selectByPrimaryKey(id);
	}
	
	/**
     * 分页查询记录
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
    @Override
	public PageList<SmsPersonRequireLine> selectByExample(SmsPersonRequireLineExample example,PageBounds pageBounds){
		return smsPersonRequireLineMapper.selectByExample(example, pageBounds);
	}
	
	/**
     * 根据条件查询记录
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
    @Override
	public List<SmsPersonRequireLine> selectByExample(SmsPersonRequireLineExample example){
		return smsPersonRequireLineMapper.selectByExample(example);
	}
	
	/**
     * 根据主键更新记录
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
    @Transactional(readOnly = false)
    @Override
	public int updateByPrimaryKeySelective(SmsPersonRequireLine record){
		return smsPersonRequireLineMapper.updateByPrimaryKeySelective(record);
	}
	
	/**
     * 根据查询条件更新记录
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
    @Transactional(readOnly = false)
    @Override
	public int updateByExampleSelective(SmsPersonRequireLine record,
			SmsPersonRequireLineExample example){
		return smsPersonRequireLineMapper.updateByExampleSelective(record, example);
	}
	
	/**
     * 根据主键生成记录
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
    @Transactional(readOnly = false)
    @Override
	public int deleteByPrimaryKey(String id){
		return smsPersonRequireLineMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 根据条件删除字段信息
	 * @dzmsoftgenerated 2016-06-09 17:00:07
	 * @param example
	 * @return
	 */
	@Transactional(readOnly = false)
	@Override
	public int deleteByExample(SmsPersonRequireLineExample example){
		return smsPersonRequireLineMapper.deleteByExample(example);
	}
}