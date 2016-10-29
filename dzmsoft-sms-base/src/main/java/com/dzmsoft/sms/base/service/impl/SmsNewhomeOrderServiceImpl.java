package com.dzmsoft.sms.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dzmsoft.framework.base.common.BaseConstant;
import com.dzmsoft.framework.base.util.CheckEmptyUtil;
import com.dzmsoft.framework.base.util.RandomUtil;
import com.dzmsoft.framework.base.util.StringUtil;
import com.dzmsoft.sms.base.common.SmsBaseConstant;
import com.dzmsoft.sms.base.dao.SmsNewhomeOrderMapper;
import com.dzmsoft.sms.base.dto.Order;
import com.dzmsoft.sms.base.dto.SmsNewhomeOrderDto;
import com.dzmsoft.sms.base.pojo.SmsNewhomeOrder;
import com.dzmsoft.sms.base.pojo.SmsNewhomeOrderExample;
import com.dzmsoft.sms.base.pojo.SmsOrder;
import com.dzmsoft.sms.base.pojo.udf.SmsNewhomeOrderUdfExample;
import com.dzmsoft.sms.base.service.OrderService;
import com.dzmsoft.sms.base.service.SmsMemberService;
import com.dzmsoft.sms.base.service.SmsNewhomeOrderService;
import com.dzmsoft.sms.base.service.SmsOrderService;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
/**
 * Copyright (C), dzmsoft Co., Ltd
 * @author dzmsoft
 * @date 2016-05-21 14:14:45
 *
 * @version 1.0
 */
@Service(value="orderService01")
@Transactional(readOnly = true)
public class SmsNewhomeOrderServiceImpl implements SmsNewhomeOrderService, OrderService{

	@Autowired
	private SmsNewhomeOrderMapper smsNewhomeOrderMapper;
	@Autowired
	private SmsOrderService smsOrderService;
	@Autowired
	private SmsMemberService smsMemberService;
	
	@Transactional(readOnly = false)
	@Override
	public int genSmsNewhomeOrder(SmsOrder smsOrder, SmsNewhomeOrder smsNewhomeOrder) {
	 // 保存统一订单
        String id = RandomUtil.genSnByDate();
        smsOrder.setId(id);
        smsOrder.setOrderType(SmsBaseConstant.OrderType.SmsNewhomeOrder);
        smsOrderService.insertSelective(smsOrder);
        // 保存新居开荒订单
        smsNewhomeOrder.setOrderId(smsOrder.getId());
        int flag = insertSelective(smsNewhomeOrder);
	    return flag;
	}
	
	@Transactional(readOnly = false)
	@Override
	public int send(String orderId, String id , String cleaners) {
	    // 更新订单状态
	    smsOrderService.send(orderId, new String(cleaners.substring(0, cleaners.indexOf(BaseConstant.Separate.COMMA))));
	    // 设置保洁员
	    SmsNewhomeOrder record = new SmsNewhomeOrder();
	    record.setId(id);
	    record.setCleaners(cleaners);
	    return updateByPrimaryKeySelective(record);
	}
	
	@Override
	public PageList<SmsNewhomeOrderDto> selectByExample(SmsNewhomeOrderUdfExample example,
	        PageBounds pageBounds) {
	    return smsNewhomeOrderMapper.selectByUdfExample(example, pageBounds);
	}
	
	/**
     * 查询符合条件的记录数量
     * @dzmsoftgenerated 2016-05-21 14:14:45
     */
    @Override
	public int countByExample(SmsNewhomeOrderExample example){
		return smsNewhomeOrderMapper.countByExample(example);
	}

	/**
     * 根据条件插入记录
     * @dzmsoftgenerated 2016-05-21 14:14:45
     */
    @Transactional(readOnly = false)
    @Override
	public int insertSelective(SmsNewhomeOrder record){
        record.setId(StringUtil.getUuidString());
		return smsNewhomeOrderMapper.insertSelective(record);
	}
	
	/**
     * 根据主键查询记录
     * @dzmsoftgenerated 2016-05-21 14:14:45
     */
    @Override
	public SmsNewhomeOrder selectByPrimaryKey(String id){
		return smsNewhomeOrderMapper.selectByPrimaryKey(id);
	}
	
	/**
     * 分页查询记录
     * @dzmsoftgenerated 2016-05-21 14:14:45
     */
    @Override
	public PageList<SmsNewhomeOrder> selectByExample(SmsNewhomeOrderExample example,PageBounds pageBounds){
		return smsNewhomeOrderMapper.selectByExample(example, pageBounds);
	}
	
	/**
     * 根据条件查询记录
     * @dzmsoftgenerated 2016-05-21 14:14:45
     */
    @Override
	public List<SmsNewhomeOrder> selectByExample(SmsNewhomeOrderExample example){
		return smsNewhomeOrderMapper.selectByExample(example);
	}
	
	/**
     * 根据主键更新记录
     * @dzmsoftgenerated 2016-05-21 14:14:45
     */
    @Transactional(readOnly = false)
    @Override
	public int updateByPrimaryKeySelective(SmsNewhomeOrder record){
		return smsNewhomeOrderMapper.updateByPrimaryKeySelective(record);
	}
	
	/**
     * 根据查询条件更新记录
     * @dzmsoftgenerated 2016-05-21 14:14:45
     */
    @Transactional(readOnly = false)
    @Override
	public int updateByExampleSelective(SmsNewhomeOrder record,
			SmsNewhomeOrderExample example){
		return smsNewhomeOrderMapper.updateByExampleSelective(record, example);
	}
	
	/**
     * 根据主键生成记录
     * @dzmsoftgenerated 2016-05-21 14:14:45
     */
    @Transactional(readOnly = false)
    @Override
	public int deleteByPrimaryKey(String id){
		return smsNewhomeOrderMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 根据条件删除字段信息
	 * @dzmsoftgenerated 2016-05-21 14:14:45
	 * @param example
	 * @return
	 */
	@Transactional(readOnly = false)
	@Override
	public int deleteByExample(SmsNewhomeOrderExample example){
		return smsNewhomeOrderMapper.deleteByExample(example);
	}

    @Override
    public Order selectByOrderId(String orderId) {
        SmsNewhomeOrderExample example = new SmsNewhomeOrderExample();
        SmsNewhomeOrderExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdEqualTo(orderId);
        List<SmsNewhomeOrder> smsNewhomeOrders = selectByExample(example);
        return CheckEmptyUtil.isEmpty(smsNewhomeOrders)?null:smsNewhomeOrders.get(0);
    }
}