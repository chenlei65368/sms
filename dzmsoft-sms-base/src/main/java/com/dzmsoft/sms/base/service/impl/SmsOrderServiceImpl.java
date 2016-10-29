package com.dzmsoft.sms.base.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dzmsoft.framework.base.util.CheckEmptyUtil;
import com.dzmsoft.framework.base.util.StringUtil;
import com.dzmsoft.sms.base.common.SmsBaseConstant;
import com.dzmsoft.sms.base.dao.SmsOrderMapper;
import com.dzmsoft.sms.base.listener.event.SmsOrderLogEvent;
import com.dzmsoft.sms.base.pojo.SmsMember;
import com.dzmsoft.sms.base.pojo.SmsOrder;
import com.dzmsoft.sms.base.pojo.SmsOrderExample;
import com.dzmsoft.sms.base.service.SmsMemberService;
import com.dzmsoft.sms.base.service.SmsOrderService;
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
public class SmsOrderServiceImpl implements SmsOrderService  {

	@Autowired
	private SmsOrderMapper smsOrderMapper;
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	@Autowired
	private SmsMemberService smsMemberService;
	
	@Override
	public List<SmsOrder> selectNoGrabOrders(String city, PageBounds pageBounds) {
	    SmsOrderExample example = new SmsOrderExample();
        SmsOrderExample.Criteria criteria = example.createCriteria();
        criteria.andEmployeesIsNull();
        criteria.andStatusEqualTo(SmsBaseConstant.OrderStatus.INIT);
        criteria.andCityEqualTo(city);
        return selectByExample(example, pageBounds);
	}
	
	@Override
	public List<SmsOrder> selectByCleaner(String cleaner, List<String> statusList,
	        PageBounds pageBounds) {
	    SmsOrderExample example = new SmsOrderExample();
        SmsOrderExample.Criteria criteria = example.createCriteria();
        criteria.andEmployeesLike(StringUtil.genLikeWhere(cleaner));
        criteria.andStatusIn(statusList);
	    return selectByExample(example, pageBounds);
	}
	
	@Override
	public List<SmsOrder> selectByMember(String member, List<String> statusList,
	        PageBounds pageBounds) {
	    SmsOrderExample example = new SmsOrderExample();
        SmsOrderExample.Criteria criteria = example.createCriteria();
        criteria.andMemberEqualTo(member);
        if (!CheckEmptyUtil.isEmpty(statusList)){
            criteria.andStatusIn(statusList);
        }
	    return selectByExample(example, pageBounds);
	}
	
	@Override
	public boolean hasGrab(String id) {
	    SmsOrderExample example = new SmsOrderExample();
	    SmsOrderExample.Criteria criteria = example.createCriteria();
	    criteria.andIdEqualTo(id);
	    List<String> statusList = new ArrayList<String>();
	    statusList.add(SmsBaseConstant.OrderStatus.SEND);
	    statusList.add(SmsBaseConstant.OrderStatus.GRAB);
	    criteria.andStatusIn(statusList);
	    int count = countByExample(example);
	    return count>0?true:false;
	}
	
	@Transactional(readOnly = false)
    @Override
    public int send(String id, String director) {
	    SmsOrder record = new SmsOrder();
        record.setId(id);
        record.setDirector(director);
        record.setStatus(SmsBaseConstant.OrderStatus.SEND);
        // 写入订单日志
        SmsOrderLogEvent smsOrderLogEvent = new SmsOrderLogEvent(this, id, SmsOrderLogEvent.SEND);
        applicationEventPublisher.publishEvent(smsOrderLogEvent);
        // 推送消息到手机客户端
        return updateByPrimaryKeySelective(record);
    }
	
	@Transactional(readOnly = false)
	@Override
	public int grab(String id, String director) {
	    SmsOrder record = new SmsOrder();
        record.setId(id);
        record.setDirector(director);
        record.setStatus(SmsBaseConstant.OrderStatus.GRAB);
        // 写入订单日志
        SmsOrderLogEvent smsOrderLogEvent = new SmsOrderLogEvent(this, id, SmsOrderLogEvent.GRAB);
        applicationEventPublisher.publishEvent(smsOrderLogEvent);
        // 推送消息到手机客户端
	    return updateByPrimaryKeySelective(record);
	}
	
	@Transactional(readOnly = false)
	@Override
	public int cancelByMember(String id) {
	    SmsOrder record = new SmsOrder();
	    record.setId(id);
	    record.setStatus(SmsBaseConstant.OrderStatus.CANCEL);
	    // 写入订单日志表
	    SmsOrderLogEvent smsOrderLogEvent = new SmsOrderLogEvent(this, id, SmsOrderLogEvent.CANCELBYMEMBER);
	    applicationEventPublisher.publishEvent(smsOrderLogEvent);
	    // 
	    return updateByPrimaryKeySelective(record);
	}
	
	/**
     * 查询符合条件的记录数量
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
    @Override
	public int countByExample(SmsOrderExample example){
		return smsOrderMapper.countByExample(example);
	}

	/**
     * 根据条件插入记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
    @Transactional(readOnly = false)
    @Override
	public int insertSelective(SmsOrder record){
        record.setOrderTime(new Date());
        if (!CheckEmptyUtil.isEmpty(record.getMember())){
            SmsMember smsMember = smsMemberService.selectByPrimaryKey(record.getMember());
            record.setMemberPhone(smsMember.getMobile());
            record.setMemberName(smsMember.getName());
        }
		return smsOrderMapper.insertSelective(record);
	}
	
	/**
     * 根据主键查询记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
    @Override
	public SmsOrder selectByPrimaryKey(String id){
		return smsOrderMapper.selectByPrimaryKey(id);
	}
	
	/**
     * 分页查询记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
    @Override
	public PageList<SmsOrder> selectByExample(SmsOrderExample example,PageBounds pageBounds){
		return smsOrderMapper.selectByExample(example, pageBounds);
	}
	
	/**
     * 根据条件查询记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
    @Override
	public List<SmsOrder> selectByExample(SmsOrderExample example){
		return smsOrderMapper.selectByExample(example);
	}
	
	/**
     * 根据主键更新记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
    @Transactional(readOnly = false)
    @Override
	public int updateByPrimaryKeySelective(SmsOrder record){
		return smsOrderMapper.updateByPrimaryKeySelective(record);
	}
	
	/**
     * 根据查询条件更新记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
    @Transactional(readOnly = false)
    @Override
	public int updateByExampleSelective(SmsOrder record,
			SmsOrderExample example){
		return smsOrderMapper.updateByExampleSelective(record, example);
	}
	
	/**
     * 根据主键生成记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
    @Transactional(readOnly = false)
    @Override
	public int deleteByPrimaryKey(String id){
		return smsOrderMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 根据条件删除字段信息
	 * @dzmsoftgenerated 2016-05-04 17:10:32
	 * @param example
	 * @return
	 */
	@Transactional(readOnly = false)
	@Override
	public int deleteByExample(SmsOrderExample example){
		return smsOrderMapper.deleteByExample(example);
	}
}