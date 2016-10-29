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
import com.dzmsoft.sms.base.dao.SmsCardMapper;
import com.dzmsoft.sms.base.pojo.SmsCard;
import com.dzmsoft.sms.base.pojo.SmsCardExample;
import com.dzmsoft.sms.base.service.SmsCardService;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
/**
 * Copyright (C), dzmsoft Co., Ltd
 * @author dzmsoft
 * @date 2016-05-04 17:10:32
 *
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class SmsCardServiceImpl implements SmsCardService{

	@Autowired
	private SmsCardMapper smsCardMapper;
	
	/**
     * 查询符合条件的记录数量
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
    @Override
	public int countByExample(SmsCardExample example){
		return smsCardMapper.countByExample(example);
	}
    
    @Transactional(readOnly = false)
    @Override
    public int updateStatus(String id, String status) {
        SmsCard record = new SmsCard();
        record.setId(id);
        record.setStatus(status);
        return updateByPrimaryKeySelective(record);
    }

	/**
     * 根据条件插入记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
    @Transactional(readOnly = false)
    @Override
	public int insertSelective(SmsCard record){
        record.setStatus(BaseConstant.Status.INIT);
        ShiroUser shiroUser = UserUtil.getCurrentShiroUser();
        record.setCreator(shiroUser.getId());
        record.setCreateTime(new Date());
        record.setId(StringUtil.getUuidString());
		return smsCardMapper.insertSelective(record);
	}
	
	/**
     * 根据主键查询记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
    @Override
	public SmsCard selectByPrimaryKey(String id){
		return smsCardMapper.selectByPrimaryKey(id);
	}
	
	/**
     * 分页查询记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
    @Override
	public PageList<SmsCard> selectByExample(SmsCardExample example,PageBounds pageBounds){
		return smsCardMapper.selectByExample(example, pageBounds);
	}
	
	/**
     * 根据条件查询记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
    @Override
	public List<SmsCard> selectByExample(SmsCardExample example){
		return smsCardMapper.selectByExample(example);
	}
	
	/**
     * 根据主键更新记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
    @Transactional(readOnly = false)
    @Override
	public int updateByPrimaryKeySelective(SmsCard record){
		return smsCardMapper.updateByPrimaryKeySelective(record);
	}
	
	/**
     * 根据查询条件更新记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
    @Transactional(readOnly = false)
    @Override
	public int updateByExampleSelective(SmsCard record,
			SmsCardExample example){
		return smsCardMapper.updateByExampleSelective(record, example);
	}
	
	/**
     * 根据主键生成记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
    @Transactional(readOnly = false)
    @Override
	public int deleteByPrimaryKey(String id){
		return smsCardMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 根据条件删除字段信息
	 * @dzmsoftgenerated 2016-05-04 17:10:32
	 * @param example
	 * @return
	 */
	@Transactional(readOnly = false)
	@Override
	public int deleteByExample(SmsCardExample example){
		return smsCardMapper.deleteByExample(example);
	}
}