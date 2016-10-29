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
import com.dzmsoft.sms.base.dao.SmsCouponMapper;
import com.dzmsoft.sms.base.pojo.SmsCoupon;
import com.dzmsoft.sms.base.pojo.SmsCouponExample;
import com.dzmsoft.sms.base.service.SmsCouponService;
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
public class SmsCouponServiceImpl implements SmsCouponService{

	@Autowired
	private SmsCouponMapper smsCouponMapper;
	
	/**
     * 查询符合条件的记录数量
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
    @Override
	public int countByExample(SmsCouponExample example){
		return smsCouponMapper.countByExample(example);
	}
    
    @Transactional(readOnly = false)
    @Override
    public int updateStatus(String id, String status) {
        SmsCoupon record = new SmsCoupon();
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
	public int insertSelective(SmsCoupon record){
        record.setStatus(BaseConstant.Status.INIT);
        ShiroUser shiroUser = UserUtil.getCurrentShiroUser();
        record.setCreator(shiroUser.getId());
        record.setCreateTime(new Date());
        record.setId(StringUtil.getUuidString());
		return smsCouponMapper.insertSelective(record);
	}
	
	/**
     * 根据主键查询记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
    @Override
	public SmsCoupon selectByPrimaryKey(String id){
		return smsCouponMapper.selectByPrimaryKey(id);
	}
	
	/**
     * 分页查询记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
    @Override
	public PageList<SmsCoupon> selectByExample(SmsCouponExample example,PageBounds pageBounds){
		return smsCouponMapper.selectByExample(example, pageBounds);
	}
	
	/**
     * 根据条件查询记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
    @Override
	public List<SmsCoupon> selectByExample(SmsCouponExample example){
		return smsCouponMapper.selectByExample(example);
	}
	
	/**
     * 根据主键更新记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
    @Transactional(readOnly = false)
    @Override
	public int updateByPrimaryKeySelective(SmsCoupon record){
		return smsCouponMapper.updateByPrimaryKeySelective(record);
	}
	
	/**
     * 根据查询条件更新记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
    @Transactional(readOnly = false)
    @Override
	public int updateByExampleSelective(SmsCoupon record,
			SmsCouponExample example){
		return smsCouponMapper.updateByExampleSelective(record, example);
	}
	
	/**
     * 根据主键生成记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
    @Transactional(readOnly = false)
    @Override
	public int deleteByPrimaryKey(String id){
		return smsCouponMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 根据条件删除字段信息
	 * @dzmsoftgenerated 2016-05-04 17:10:32
	 * @param example
	 * @return
	 */
	@Transactional(readOnly = false)
	@Override
	public int deleteByExample(SmsCouponExample example){
		return smsCouponMapper.deleteByExample(example);
	}
}