package com.dzmsoft.sms.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.dzmsoft.sms.base.pojo.SmsEmployee;
import com.dzmsoft.sms.base.pojo.SmsEmployeeShift;
import com.dzmsoft.sms.base.pojo.SmsEmployeeShiftExample;
import com.dzmsoft.sms.base.dao.SmsEmployeeShiftMapper;
import com.dzmsoft.sms.base.dto.SmsEmployeeScheduleArrangeDto;
import com.dzmsoft.sms.base.service.SmsEmployeeShiftService;
import com.dzmsoft.framework.base.util.CheckEmptyUtil;
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
public class SmsEmployeeShiftServiceImpl implements SmsEmployeeShiftService{

	@Autowired
	private SmsEmployeeShiftMapper smsEmployeeShiftMapper;
	
	@Transactional(readOnly = false)
	@Override
	public int arrage(List<SmsEmployee> smsEmployees,
	        SmsEmployeeScheduleArrangeDto smsEmployeeScheduleArrangeDto) {
	    if (!CheckEmptyUtil.isEmpty(smsEmployees)){
	        SmsEmployeeShift smsEmployeeShift = null;
	        for (SmsEmployee smsEmployee:smsEmployees){
	            smsEmployeeShift = new SmsEmployeeShift();
	            smsEmployeeShift.setShift(smsEmployeeScheduleArrangeDto.getShift());
	            //
	            insertSelective(smsEmployeeShift);
	        }
	    }
	    return 0;
	}
	
		
	/**
     * 查询符合条件的记录数量
     * @dzmsoftgenerated 
     */
    @Override
	public int countByExample(SmsEmployeeShiftExample example){
		return smsEmployeeShiftMapper.countByExample(example);
	}

	/**
     * 根据条件插入记录
     * @dzmsoftgenerated 
     */
    @Transactional(readOnly = false)
    @Override
	public int insertSelective(SmsEmployeeShift record){
				record.setId(StringUtil.getUuidString());
				return smsEmployeeShiftMapper.insertSelective(record);
	}
	
	/**
     * 根据主键查询记录
     * @dzmsoftgenerated 
     */
    @Override
	public SmsEmployeeShift selectByPrimaryKey(String id){
		return smsEmployeeShiftMapper.selectByPrimaryKey(id);
	}
	
	/**
     * 分页查询记录
     * @dzmsoftgenerated 
     */
    @Override
	public PageList<SmsEmployeeShift> selectByExample(SmsEmployeeShiftExample example,PageBounds pageBounds){
		return smsEmployeeShiftMapper.selectByExample(example, pageBounds);
	}
	
	/**
     * 根据条件查询记录
     * @dzmsoftgenerated 
     */
    @Override
	public List<SmsEmployeeShift> selectByExample(SmsEmployeeShiftExample example){
		return smsEmployeeShiftMapper.selectByExample(example);
	}
	
	/**
     * 根据主键更新记录
     * @dzmsoftgenerated 
     */
    @Transactional(readOnly = false)
    @Override
	public int updateByPrimaryKeySelective(SmsEmployeeShift record){
		return smsEmployeeShiftMapper.updateByPrimaryKeySelective(record);
	}
	
	/**
     * 根据查询条件更新记录
     * @dzmsoftgenerated 
     */
    @Transactional(readOnly = false)
    @Override
	public int updateByExampleSelective(SmsEmployeeShift record,
			SmsEmployeeShiftExample example){
		return smsEmployeeShiftMapper.updateByExampleSelective(record, example);
	}
	
	/**
     * 根据主键生成记录
     * @dzmsoftgenerated 
     */
    @Transactional(readOnly = false)
    @Override
	public int deleteByPrimaryKey(String id){
		return smsEmployeeShiftMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 根据条件删除字段信息
	 * @dzmsoftgenerated 
	 * @param example
	 * @return
	 */
	@Transactional(readOnly = false)
	@Override
	public int deleteByExample(SmsEmployeeShiftExample example){
		return smsEmployeeShiftMapper.deleteByExample(example);
	}
	
	}