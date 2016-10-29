package com.dzmsoft.sms.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dzmsoft.framework.base.util.CheckEmptyUtil;
import com.dzmsoft.framework.base.util.RandomUtil;
import com.dzmsoft.framework.base.util.StringUtil;
import com.dzmsoft.sms.base.common.SmsBaseConstant;
import com.dzmsoft.sms.base.dao.SmsCommodityOrderMapper;
import com.dzmsoft.sms.base.dto.Order;
import com.dzmsoft.sms.base.dto.RestCommodityOrder;
import com.dzmsoft.sms.base.dto.SmsCommodityOrderDto;
import com.dzmsoft.sms.base.pojo.SmsCommodityOrder;
import com.dzmsoft.sms.base.pojo.SmsCommodityOrderExample;
import com.dzmsoft.sms.base.pojo.SmsOrder;
import com.dzmsoft.sms.base.pojo.udf.SmsCommodityOrderUdfExample;
import com.dzmsoft.sms.base.service.OrderService;
import com.dzmsoft.sms.base.service.SmsCommodityOrderService;
import com.dzmsoft.sms.base.service.SmsOrderService;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
/**
 * Copyright (C), dzmsoft Co., Ltd
 * @author dzmsoft
 * @date 2016-05-29 09:15:26
 *
 * @version 1.0
 */
@Service(value="orderService02")
@Transactional(readOnly = true)
public class SmsCommodityOrderServiceImpl implements SmsCommodityOrderService,OrderService{

	@Autowired
	private SmsCommodityOrderMapper smsCommodityOrderMapper;
	@Autowired
	private SmsOrderService smsOrderService;
	
	@Transactional(readOnly = false)
	@Override
	public int genSmsCommodityOrder(SmsOrder smsOrder, List<SmsCommodityOrder> smsCommodityOrderList) {
	 // 保存统一订单
        String id = RandomUtil.genSnByDate();
        smsOrder.setId(id);
        smsOrder.setOrderType(SmsBaseConstant.OrderType.SmsCommodityOrder);
        int flag = smsOrderService.insertSelective(smsOrder);
        // 批量保存商品订单
        insertBatch(smsOrder, smsCommodityOrderList);
	    return flag;
	}
	
	private void insertBatch(SmsOrder smsOrder,List<SmsCommodityOrder> smsCommodityOrderList){
	    if (!CheckEmptyUtil.isEmpty(smsCommodityOrderList)){
	        for (SmsCommodityOrder smsCommodityOrder:smsCommodityOrderList){
	            smsCommodityOrder.setOrderId(smsOrder.getId());
	            smsCommodityOrder.setStatus(SmsBaseConstant.CommodityOrderStatus.WAIT_SEND);
	            insertSelective(smsCommodityOrder);
	        }
	    }
	}
	
	@Transactional(readOnly = false)
	@Override
	public int send(String orderId, String cleaner) {
	    // 更新
	    smsOrderService.send(orderId, cleaner);
	    //
	    SmsCommodityOrder smsCommodityOrder = new SmsCommodityOrder();
	    smsCommodityOrder.setStatus(SmsBaseConstant.CommodityOrderStatus.IN_SEND);
	    SmsCommodityOrderExample example = new SmsCommodityOrderExample();
	    SmsCommodityOrderExample.Criteria criteria = example.createCriteria();
	    criteria.andOrderIdEqualTo(orderId);
	    return updateByExampleSelective(smsCommodityOrder, example);
	}
	
	/**
     * 查询符合条件的记录数量
     * @dzmsoftgenerated 2016-05-29 09:15:26
     */
    @Override
	public int countByExample(SmsCommodityOrderExample example){
		return smsCommodityOrderMapper.countByExample(example);
	}

	/**
     * 根据条件插入记录
     * @dzmsoftgenerated 2016-05-29 09:15:26
     */
    @Transactional(readOnly = false)
    @Override
	public int insertSelective(SmsCommodityOrder record){
        record.setId(StringUtil.getUuidString());
		return smsCommodityOrderMapper.insertSelective(record);
	}
	
	/**
     * 根据主键查询记录
     * @dzmsoftgenerated 2016-05-29 09:15:26
     */
    @Override
	public SmsCommodityOrder selectByPrimaryKey(String id){
		return smsCommodityOrderMapper.selectByPrimaryKey(id);
	}
	
	/**
     * 分页查询记录
     * @dzmsoftgenerated 2016-05-29 09:15:26
     */
    @Override
	public PageList<SmsCommodityOrderDto> selectByExample(SmsCommodityOrderUdfExample example,PageBounds pageBounds){
		return smsCommodityOrderMapper.selectWithCommodity(example, pageBounds);
	}
	
	/**
     * 根据条件查询记录
     * @dzmsoftgenerated 2016-05-29 09:15:26
     */
    @Override
	public List<SmsCommodityOrder> selectByExample(SmsCommodityOrderExample example){
		return smsCommodityOrderMapper.selectByExample(example);
	}
	
	/**
     * 根据主键更新记录
     * @dzmsoftgenerated 2016-05-29 09:15:26
     */
    @Transactional(readOnly = false)
    @Override
	public int updateByPrimaryKeySelective(SmsCommodityOrder record){
		return smsCommodityOrderMapper.updateByPrimaryKeySelective(record);
	}
	
	/**
     * 根据查询条件更新记录
     * @dzmsoftgenerated 2016-05-29 09:15:26
     */
    @Transactional(readOnly = false)
    @Override
	public int updateByExampleSelective(SmsCommodityOrder record,
			SmsCommodityOrderExample example){
		return smsCommodityOrderMapper.updateByExampleSelective(record, example);
	}
	
	/**
     * 根据主键生成记录
     * @dzmsoftgenerated 2016-05-29 09:15:26
     */
    @Transactional(readOnly = false)
    @Override
	public int deleteByPrimaryKey(String id){
		return smsCommodityOrderMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 根据条件删除字段信息
	 * @dzmsoftgenerated 2016-05-29 09:15:26
	 * @param example
	 * @return
	 */
	@Transactional(readOnly = false)
	@Override
	public int deleteByExample(SmsCommodityOrderExample example){
		return smsCommodityOrderMapper.deleteByExample(example);
	}

    @Override
    public Order selectByOrderId(String orderId) {
        SmsCommodityOrderExample example = new SmsCommodityOrderExample();
        SmsCommodityOrderExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdEqualTo(orderId);
        List<SmsCommodityOrder> smsCommodityOrders = selectByExample(example);
        RestCommodityOrder restCommodityOrder = new RestCommodityOrder();
        restCommodityOrder.setSmsCommodityOrders(smsCommodityOrders);
        return restCommodityOrder;
    }
}