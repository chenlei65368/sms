package com.dzmsoft.sms.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.dzmsoft.sms.base.pojo.SmsMemberCoupon;
import com.dzmsoft.sms.base.pojo.SmsMemberCouponExample;
import com.dzmsoft.sms.base.dao.SmsMemberCouponMapper;
import com.dzmsoft.sms.base.service.SmsMemberCouponService;
/**
 * Copyright (C), dzmsoft Co., Ltd
 * @author dzmsoft
 * @date 2016-06-09 17:00:07
 *
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class SmsMemberCouponServiceImpl implements SmsMemberCouponService{

	@Autowired
	private SmsMemberCouponMapper smsMemberCouponMapper;
	
	/**
     * 查询符合条件的记录数量
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
    @Override
	public int countByExample(SmsMemberCouponExample example){
		return smsMemberCouponMapper.countByExample(example);
	}

	/**
     * 根据条件插入记录
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
    @Transactional(readOnly = false)
    @Override
	public int insertSelective(SmsMemberCoupon record){
		return smsMemberCouponMapper.insertSelective(record);
	}
	
	/**
     * 根据主键查询记录
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
    @Override
	public SmsMemberCoupon selectByPrimaryKey(String id){
		return smsMemberCouponMapper.selectByPrimaryKey(id);
	}
	
	/**
     * 分页查询记录
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
    @Override
	public PageList<SmsMemberCoupon> selectByExample(SmsMemberCouponExample example,PageBounds pageBounds){
		return smsMemberCouponMapper.selectByExample(example, pageBounds);
	}
	
	/**
     * 根据条件查询记录
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
    @Override
	public List<SmsMemberCoupon> selectByExample(SmsMemberCouponExample example){
		return smsMemberCouponMapper.selectByExample(example);
	}
	
	/**
     * 根据主键更新记录
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
    @Transactional(readOnly = false)
    @Override
	public int updateByPrimaryKeySelective(SmsMemberCoupon record){
		return smsMemberCouponMapper.updateByPrimaryKeySelective(record);
	}
	
	/**
     * 根据查询条件更新记录
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
    @Transactional(readOnly = false)
    @Override
	public int updateByExampleSelective(SmsMemberCoupon record,
			SmsMemberCouponExample example){
		return smsMemberCouponMapper.updateByExampleSelective(record, example);
	}
	
	/**
     * 根据主键生成记录
     * @dzmsoftgenerated 2016-06-09 17:00:07
     */
    @Transactional(readOnly = false)
    @Override
	public int deleteByPrimaryKey(String id){
		return smsMemberCouponMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 根据条件删除字段信息
	 * @dzmsoftgenerated 2016-06-09 17:00:07
	 * @param example
	 * @return
	 */
	@Transactional(readOnly = false)
	@Override
	public int deleteByExample(SmsMemberCouponExample example){
		return smsMemberCouponMapper.deleteByExample(example);
	}
}