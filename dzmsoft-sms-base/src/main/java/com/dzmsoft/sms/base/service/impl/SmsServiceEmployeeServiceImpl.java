package com.dzmsoft.sms.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.dzmsoft.sms.base.pojo.SmsServiceEmployee;
import com.dzmsoft.sms.base.pojo.SmsServiceEmployeeExample;
import com.dzmsoft.sms.base.dao.SmsServiceEmployeeMapper;
import com.dzmsoft.sms.base.service.SmsServiceEmployeeService;
import com.dzmsoft.framework.base.util.StringUtil;
/**
 * Copyright (C), dzmsoft Co., Ltd
 * @author 
 * @date 
 *
 * @version 
 */
@Service
@Transactional(readOnly = true)
public class SmsServiceEmployeeServiceImpl implements SmsServiceEmployeeService{

	@Autowired
	private SmsServiceEmployeeMapper smsServiceEmployeeMapper;
	
		
	/**
     * 查询符合条件的记录数量
     * @dzmsoftgenerated 
     */
    @Override
	public int countByExample(SmsServiceEmployeeExample example){
		return smsServiceEmployeeMapper.countByExample(example);
	}

	/**
     * 根据条件插入记录
     * @dzmsoftgenerated 
     */
    @Transactional(readOnly = false)
    @Override
	public int insertSelective(SmsServiceEmployee record){
				record.setId(StringUtil.getUuidString());
				return smsServiceEmployeeMapper.insertSelective(record);
	}
	
	/**
     * 根据主键查询记录
     * @dzmsoftgenerated 
     */
    @Override
	public SmsServiceEmployee selectByPrimaryKey(String id){
		return smsServiceEmployeeMapper.selectByPrimaryKey(id);
	}
	
	/**
     * 分页查询记录
     * @dzmsoftgenerated 
     */
    @Override
	public PageList<SmsServiceEmployee> selectByExample(SmsServiceEmployeeExample example,PageBounds pageBounds){
		return smsServiceEmployeeMapper.selectByExample(example, pageBounds);
	}
	
	/**
     * 根据条件查询记录
     * @dzmsoftgenerated 
     */
    @Override
	public List<SmsServiceEmployee> selectByExample(SmsServiceEmployeeExample example){
		return smsServiceEmployeeMapper.selectByExample(example);
	}
	
	/**
     * 根据主键更新记录
     * @dzmsoftgenerated 
     */
    @Transactional(readOnly = false)
    @Override
	public int updateByPrimaryKeySelective(SmsServiceEmployee record){
		return smsServiceEmployeeMapper.updateByPrimaryKeySelective(record);
	}
	
	/**
     * 根据查询条件更新记录
     * @dzmsoftgenerated 
     */
    @Transactional(readOnly = false)
    @Override
	public int updateByExampleSelective(SmsServiceEmployee record,
			SmsServiceEmployeeExample example){
		return smsServiceEmployeeMapper.updateByExampleSelective(record, example);
	}
	
	/**
     * 根据主键生成记录
     * @dzmsoftgenerated 
     */
    @Transactional(readOnly = false)
    @Override
	public int deleteByPrimaryKey(String id){
		return smsServiceEmployeeMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 根据条件删除字段信息
	 * @dzmsoftgenerated 
	 * @param example
	 * @return
	 */
	@Transactional(readOnly = false)
	@Override
	public int deleteByExample(SmsServiceEmployeeExample example){
		return smsServiceEmployeeMapper.deleteByExample(example);
	}
	
	}