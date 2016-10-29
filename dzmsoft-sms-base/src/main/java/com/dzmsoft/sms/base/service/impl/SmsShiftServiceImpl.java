package com.dzmsoft.sms.base.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dzmsoft.framework.base.common.BaseConstant;
import com.dzmsoft.framework.base.util.StringUtil;
import com.dzmsoft.framework.base.web.mvc.pojo.ShiroUser;
import com.dzmsoft.framework.base.web.mvc.shiro.UserUtil;
import com.dzmsoft.sms.base.dao.SmsShiftMapper;
import com.dzmsoft.sms.base.pojo.SmsShift;
import com.dzmsoft.sms.base.pojo.SmsShiftExample;
import com.dzmsoft.sms.base.service.SmsShiftService;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
/**
 * Copyright (C), dzmsoft Co., Ltd
 * @author dzmsoft
 * @date 2016-05-14 22:26:11
 *
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class SmsShiftServiceImpl implements SmsShiftService{

	@Autowired
	private SmsShiftMapper smsShiftMapper;
	
	@Override
	public List<SmsShift> getSmsShift(List<String> ids) {
	    SmsShiftExample example = new SmsShiftExample();
	    SmsShiftExample.Criteria criteria = example.createCriteria();
	    criteria.andIdIn(ids);
	    return selectByExample(example);
	}
	
	@Transactional(readOnly = false)
	@Override
	public int updateStatus(String id, String status) {
	    SmsShift record = new SmsShift();
	    record.setId(id);
	    record.setStatus(status);
	    return updateByPrimaryKeySelective(record);
	}
	
	/**
     * 查询符合条件的记录数量
     * @dzmsoftgenerated 2016-05-14 22:26:11
     */
    @Override
	public int countByExample(SmsShiftExample example){
		return smsShiftMapper.countByExample(example);
	}

	/**
     * 根据条件插入记录
     * @dzmsoftgenerated 2016-05-14 22:26:11
     */
    @Transactional(readOnly = false)
    @Override
	public int insertSelective(SmsShift record){
        ShiroUser shioUser = UserUtil.getCurrentShiroUser();
        record.setCreator(shioUser.getId());
        record.setCreateTime(new Date());
        record.setStatus(BaseConstant.Status.INIT);
        record.setId(StringUtil.getUuidString());
        // 关联供应商
        
        //
		return smsShiftMapper.insertSelective(record);
	}
	
	/**
     * 根据主键查询记录
     * @dzmsoftgenerated 2016-05-14 22:26:11
     */
    @Override
	public SmsShift selectByPrimaryKey(String id){
		return smsShiftMapper.selectByPrimaryKey(id);
	}
	
	/**
     * 分页查询记录
     * @dzmsoftgenerated 2016-05-14 22:26:11
     */
    @Override
	public PageList<SmsShift> selectByExample(SmsShiftExample example,PageBounds pageBounds){
		return smsShiftMapper.selectByExample(example, pageBounds);
	}
	
	/**
     * 根据条件查询记录
     * @dzmsoftgenerated 2016-05-14 22:26:11
     */
    @Override
	public List<SmsShift> selectByExample(SmsShiftExample example){
		return smsShiftMapper.selectByExample(example);
	}
	
	/**
     * 根据主键更新记录
     * @dzmsoftgenerated 2016-05-14 22:26:11
     */
    @Transactional(readOnly = false)
    @Override
	public int updateByPrimaryKeySelective(SmsShift record){
		return smsShiftMapper.updateByPrimaryKeySelective(record);
	}
	
	/**
     * 根据查询条件更新记录
     * @dzmsoftgenerated 2016-05-14 22:26:11
     */
    @Transactional(readOnly = false)
    @Override
	public int updateByExampleSelective(SmsShift record,
			SmsShiftExample example){
		return smsShiftMapper.updateByExampleSelective(record, example);
	}
	
	/**
     * 根据主键生成记录
     * @dzmsoftgenerated 2016-05-14 22:26:11
     */
    @Transactional(readOnly = false)
    @Override
	public int deleteByPrimaryKey(String id){
		return smsShiftMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 根据条件删除字段信息
	 * @dzmsoftgenerated 2016-05-14 22:26:11
	 * @param example
	 * @return
	 */
	@Transactional(readOnly = false)
	@Override
	public int deleteByExample(SmsShiftExample example){
		return smsShiftMapper.deleteByExample(example);
	}
}