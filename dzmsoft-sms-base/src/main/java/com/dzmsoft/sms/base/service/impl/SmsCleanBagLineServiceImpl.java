package com.dzmsoft.sms.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.dzmsoft.sms.base.pojo.SmsCleanBagLine;
import com.dzmsoft.sms.base.pojo.SmsCleanBagLineExample;
import com.dzmsoft.sms.base.dao.SmsCleanBagLineMapper;
import com.dzmsoft.sms.base.service.SmsCleanBagLineService;
/**
 * Copyright (C), dzmsoft Co., Ltd
 * @author dzmsoft
 * @date 2016-06-13 13:10:38
 *
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class SmsCleanBagLineServiceImpl implements SmsCleanBagLineService{

	@Autowired
	private SmsCleanBagLineMapper smsCleanBagLineMapper;
	
	@Override
	public List<SmsCleanBagLine> selectByCleanBagId(String cleanBagId) {
	    SmsCleanBagLineExample example = new SmsCleanBagLineExample();
        SmsCleanBagLineExample.Criteria criteria = example.createCriteria();
        criteria.andCleanBagIdEqualTo(cleanBagId);
	    return selectByExample(example);
	}
	
	@Transactional(readOnly = false)
	@Override
	public int deleteByCleanBagId(String cleanBagId) {
	    SmsCleanBagLineExample example = new SmsCleanBagLineExample();
	    SmsCleanBagLineExample.Criteria criteria = example.createCriteria();
	    criteria.andCleanBagIdEqualTo(cleanBagId);
	    return deleteByExample(example);
	}
	
	/**
     * 查询符合条件的记录数量
     * @dzmsoftgenerated 2016-06-13 13:10:38
     */
    @Override
	public int countByExample(SmsCleanBagLineExample example){
		return smsCleanBagLineMapper.countByExample(example);
	}

	/**
     * 根据条件插入记录
     * @dzmsoftgenerated 2016-06-13 13:10:38
     */
    @Transactional(readOnly = false)
    @Override
	public int insertSelective(SmsCleanBagLine record){
		return smsCleanBagLineMapper.insertSelective(record);
	}
	
	/**
     * 根据主键查询记录
     * @dzmsoftgenerated 2016-06-13 13:10:38
     */
    @Override
	public SmsCleanBagLine selectByPrimaryKey(String id){
		return smsCleanBagLineMapper.selectByPrimaryKey(id);
	}
	
	/**
     * 分页查询记录
     * @dzmsoftgenerated 2016-06-13 13:10:38
     */
    @Override
	public PageList<SmsCleanBagLine> selectByExample(SmsCleanBagLineExample example,PageBounds pageBounds){
		return smsCleanBagLineMapper.selectByExample(example, pageBounds);
	}
	
	/**
     * 根据条件查询记录
     * @dzmsoftgenerated 2016-06-13 13:10:38
     */
    @Override
	public List<SmsCleanBagLine> selectByExample(SmsCleanBagLineExample example){
		return smsCleanBagLineMapper.selectByExample(example);
	}
	
	/**
     * 根据主键更新记录
     * @dzmsoftgenerated 2016-06-13 13:10:38
     */
    @Transactional(readOnly = false)
    @Override
	public int updateByPrimaryKeySelective(SmsCleanBagLine record){
		return smsCleanBagLineMapper.updateByPrimaryKeySelective(record);
	}
	
	/**
     * 根据查询条件更新记录
     * @dzmsoftgenerated 2016-06-13 13:10:38
     */
    @Transactional(readOnly = false)
    @Override
	public int updateByExampleSelective(SmsCleanBagLine record,
			SmsCleanBagLineExample example){
		return smsCleanBagLineMapper.updateByExampleSelective(record, example);
	}
	
	/**
     * 根据主键生成记录
     * @dzmsoftgenerated 2016-06-13 13:10:38
     */
    @Transactional(readOnly = false)
    @Override
	public int deleteByPrimaryKey(String id){
		return smsCleanBagLineMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 根据条件删除字段信息
	 * @dzmsoftgenerated 2016-06-13 13:10:38
	 * @param example
	 * @return
	 */
	@Transactional(readOnly = false)
	@Override
	public int deleteByExample(SmsCleanBagLineExample example){
		return smsCleanBagLineMapper.deleteByExample(example);
	}
}