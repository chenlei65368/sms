package com.dzmsoft.sms.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.dzmsoft.sms.base.pojo.SmsEmployeeTeam;
import com.dzmsoft.sms.base.pojo.SmsEmployeeTeamExample;
import com.dzmsoft.sms.base.dao.SmsEmployeeTeamMapper;
import com.dzmsoft.sms.base.service.SmsEmployeeTeamService;
import com.dzmsoft.framework.base.common.BaseConstant;
/**
 * Copyright (C), dzmsoft Co., Ltd
 * @author 
 * @date 
 *
 * @version 
 */
@Service
@Transactional(readOnly = true)
public class SmsEmployeeTeamServiceImpl implements SmsEmployeeTeamService{

	@Autowired
	private SmsEmployeeTeamMapper smsEmployeeTeamMapper;
	
	/**
     * 查询符合条件的记录数量
     * @dzmsoftgenerated 
     */
    @Override
	public int countByExample(SmsEmployeeTeamExample example){
		return smsEmployeeTeamMapper.countByExample(example);
	}

	/**
     * 根据条件插入记录
     * @dzmsoftgenerated 
     */
    @Transactional(readOnly = false)
    @Override
	public int insertSelective(SmsEmployeeTeam record){
		return smsEmployeeTeamMapper.insertSelective(record);
	}
	
	/**
     * 根据主键查询记录
     * @dzmsoftgenerated 
     */
    @Override
	public SmsEmployeeTeam selectByPrimaryKey(String id){
		return smsEmployeeTeamMapper.selectByPrimaryKey(id);
	}
	
	/**
     * 分页查询记录
     * @dzmsoftgenerated 
     */
    @Override
	public PageList<SmsEmployeeTeam> selectByExample(SmsEmployeeTeamExample example,PageBounds pageBounds){
		return smsEmployeeTeamMapper.selectByExample(example, pageBounds);
	}
	
	/**
     * 根据条件查询记录
     * @dzmsoftgenerated 
     */
    @Override
	public List<SmsEmployeeTeam> selectByExample(SmsEmployeeTeamExample example){
		return smsEmployeeTeamMapper.selectByExample(example);
	}
	
	/**
     * 根据主键更新记录
     * @dzmsoftgenerated 
     */
    @Transactional(readOnly = false)
    @Override
	public int updateByPrimaryKeySelective(SmsEmployeeTeam record){
		return smsEmployeeTeamMapper.updateByPrimaryKeySelective(record);
	}
	
	/**
     * 根据查询条件更新记录
     * @dzmsoftgenerated 
     */
    @Transactional(readOnly = false)
    @Override
	public int updateByExampleSelective(SmsEmployeeTeam record,
			SmsEmployeeTeamExample example){
		return smsEmployeeTeamMapper.updateByExampleSelective(record, example);
	}
	
	/**
     * 根据主键生成记录
     * @dzmsoftgenerated 
     */
    @Transactional(readOnly = false)
    @Override
	public int deleteByPrimaryKey(String id){
		return smsEmployeeTeamMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 根据条件删除字段信息
	 * @dzmsoftgenerated 
	 * @param example
	 * @return
	 */
	@Transactional(readOnly = false)
	@Override
	public int deleteByExample(SmsEmployeeTeamExample example){
		return smsEmployeeTeamMapper.deleteByExample(example);
	}
	
		/**
	 * 启用
	 * @dzmsoftgenerated 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = false)
	@Override
	public int enable(String id){
		SmsEmployeeTeam record = new SmsEmployeeTeam();
		record.setId(id);
		record.setStatus(BaseConstant.Status.ENABLE);
		return updateByPrimaryKeySelective(record);
	}
	
	/**
	 * 启用
	 * @dzmsoftgenerated 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = false)
	@Override
	public int disable(String id){
		SmsEmployeeTeam record = new SmsEmployeeTeam();
		record.setId(id);
		record.setStatus(BaseConstant.Status.DISABLED);
		return updateByPrimaryKeySelective(record);
	}
	}