package com.dzmsoft.sms.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.dzmsoft.framework.base.util.CheckEmptyUtil;
import com.dzmsoft.framework.base.util.StringUtil;
import com.dzmsoft.sms.base.pojo.SmsHouseholdBag;
import com.dzmsoft.sms.base.pojo.SmsHouseholdBagExample;
import com.dzmsoft.sms.base.dao.SmsHouseholdBagMapper;
import com.dzmsoft.sms.base.service.SmsHouseholdBagService;
/**
 * Copyright (C), dzmsoft Co., Ltd
 * @author dzmsoft
 * @date 2016-06-09 17:00:07
 *
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class SmsHouseholdBagServiceImpl implements SmsHouseholdBagService{

	@Autowired
	private SmsHouseholdBagMapper smsHouseholdBagMapper;
	
	@Transactional(readOnly = false)
	@Override
	public int insertBatch(String orderId, List<SmsHouseholdBag> smsHouseholdBagList) {
	    if (!CheckEmptyUtil.isEmpty(smsHouseholdBagList)){
	        for (SmsHouseholdBag smsHouseholdBag:smsHouseholdBagList){
	            smsHouseholdBag.setOrderId(orderId);
	            insertSelective(smsHouseholdBag);
	        }
	    }
	    return 1;
	}
	
	/**
     * 查询符合条件的记录数量
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
    @Override
	public int countByExample(SmsHouseholdBagExample example){
		return smsHouseholdBagMapper.countByExample(example);
	}

	/**
     * 根据条件插入记录
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
    @Transactional(readOnly = false)
    @Override
	public int insertSelective(SmsHouseholdBag record){
        record.setId(StringUtil.getUuidString());
		return smsHouseholdBagMapper.insertSelective(record);
	}
	
	/**
     * 根据主键查询记录
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
    @Override
	public SmsHouseholdBag selectByPrimaryKey(String id){
		return smsHouseholdBagMapper.selectByPrimaryKey(id);
	}
	
	/**
     * 分页查询记录
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
    @Override
	public PageList<SmsHouseholdBag> selectByExample(SmsHouseholdBagExample example,PageBounds pageBounds){
		return smsHouseholdBagMapper.selectByExample(example, pageBounds);
	}
	
	/**
     * 根据条件查询记录
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
    @Override
	public List<SmsHouseholdBag> selectByExample(SmsHouseholdBagExample example){
		return smsHouseholdBagMapper.selectByExample(example);
	}
	
	/**
     * 根据主键更新记录
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
    @Transactional(readOnly = false)
    @Override
	public int updateByPrimaryKeySelective(SmsHouseholdBag record){
		return smsHouseholdBagMapper.updateByPrimaryKeySelective(record);
	}
	
	/**
     * 根据查询条件更新记录
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
    @Transactional(readOnly = false)
    @Override
	public int updateByExampleSelective(SmsHouseholdBag record,
			SmsHouseholdBagExample example){
		return smsHouseholdBagMapper.updateByExampleSelective(record, example);
	}
	
	/**
     * 根据主键生成记录
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
    @Transactional(readOnly = false)
    @Override
	public int deleteByPrimaryKey(String id){
		return smsHouseholdBagMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 根据条件删除字段信息
	 * @dzmsoftgenerated 2016-06-09 17:00:07
	 * @param example
	 * @return
	 */
	@Transactional(readOnly = false)
	@Override
	public int deleteByExample(SmsHouseholdBagExample example){
		return smsHouseholdBagMapper.deleteByExample(example);
	}
}