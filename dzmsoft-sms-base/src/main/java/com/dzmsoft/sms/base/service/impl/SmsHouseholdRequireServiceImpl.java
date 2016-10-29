package com.dzmsoft.sms.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.dzmsoft.framework.base.util.CheckEmptyUtil;
import com.dzmsoft.framework.base.util.StringUtil;
import com.dzmsoft.sms.base.pojo.SmsHouseholdRequire;
import com.dzmsoft.sms.base.pojo.SmsHouseholdRequireExample;
import com.dzmsoft.sms.base.dao.SmsHouseholdRequireMapper;
import com.dzmsoft.sms.base.service.SmsHouseholdRequireService;
/**
 * Copyright (C), dzmsoft Co., Ltd
 * @author dzmsoft
 * @date 2016-06-09 17:00:07
 *
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class SmsHouseholdRequireServiceImpl implements SmsHouseholdRequireService{

	@Autowired
	private SmsHouseholdRequireMapper smsHouseholdRequireMapper;
	
	@Transactional(readOnly = false)
	@Override
	public int insertBatch(String orderId, List<SmsHouseholdRequire> smsHouseholdRequireList) {
	    if (!CheckEmptyUtil.isEmpty(smsHouseholdRequireList)){
	        for (SmsHouseholdRequire smsHouseholdRequire:smsHouseholdRequireList){
	            smsHouseholdRequire.setOrderId(orderId);
	            insertSelective(smsHouseholdRequire);
	        }
	    }
	    return 1;
	}
	
	/**
     * 查询符合条件的记录数量
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
    @Override
	public int countByExample(SmsHouseholdRequireExample example){
		return smsHouseholdRequireMapper.countByExample(example);
	}

	/**
     * 根据条件插入记录
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
    @Transactional(readOnly = false)
    @Override
	public int insertSelective(SmsHouseholdRequire record){
        record.setId(StringUtil.getUuidString());
		return smsHouseholdRequireMapper.insertSelective(record);
	}
	
	/**
     * 根据主键查询记录
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
    @Override
	public SmsHouseholdRequire selectByPrimaryKey(String id){
		return smsHouseholdRequireMapper.selectByPrimaryKey(id);
	}
	
	/**
     * 分页查询记录
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
    @Override
	public PageList<SmsHouseholdRequire> selectByExample(SmsHouseholdRequireExample example,PageBounds pageBounds){
		return smsHouseholdRequireMapper.selectByExample(example, pageBounds);
	}
	
	/**
     * 根据条件查询记录
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
    @Override
	public List<SmsHouseholdRequire> selectByExample(SmsHouseholdRequireExample example){
		return smsHouseholdRequireMapper.selectByExample(example);
	}
	
	/**
     * 根据主键更新记录
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
    @Transactional(readOnly = false)
    @Override
	public int updateByPrimaryKeySelective(SmsHouseholdRequire record){
		return smsHouseholdRequireMapper.updateByPrimaryKeySelective(record);
	}
	
	/**
     * 根据查询条件更新记录
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
    @Transactional(readOnly = false)
    @Override
	public int updateByExampleSelective(SmsHouseholdRequire record,
			SmsHouseholdRequireExample example){
		return smsHouseholdRequireMapper.updateByExampleSelective(record, example);
	}
	
	/**
     * 根据主键生成记录
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
    @Transactional(readOnly = false)
    @Override
	public int deleteByPrimaryKey(String id){
		return smsHouseholdRequireMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 根据条件删除字段信息
	 * @dzmsoftgenerated 2016-06-09 17:00:07
	 * @param example
	 * @return
	 */
	@Transactional(readOnly = false)
	@Override
	public int deleteByExample(SmsHouseholdRequireExample example){
		return smsHouseholdRequireMapper.deleteByExample(example);
	}
}