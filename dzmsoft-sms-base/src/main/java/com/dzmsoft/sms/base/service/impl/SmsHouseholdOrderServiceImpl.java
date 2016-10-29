package com.dzmsoft.sms.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dzmsoft.framework.base.util.CheckEmptyUtil;
import com.dzmsoft.framework.base.util.RandomUtil;
import com.dzmsoft.framework.base.util.StringUtil;
import com.dzmsoft.sms.base.common.SmsBaseConstant;
import com.dzmsoft.sms.base.dao.SmsHouseholdOrderMapper;
import com.dzmsoft.sms.base.dto.Order;
import com.dzmsoft.sms.base.dto.SmsHouseholdOrderDto;
import com.dzmsoft.sms.base.pojo.SmsHouseholdBag;
import com.dzmsoft.sms.base.pojo.SmsHouseholdOrder;
import com.dzmsoft.sms.base.pojo.SmsHouseholdOrderExample;
import com.dzmsoft.sms.base.pojo.SmsOrder;
import com.dzmsoft.sms.base.pojo.udf.SmsHouseholdOrderUdfExample;
import com.dzmsoft.sms.base.service.OrderService;
import com.dzmsoft.sms.base.service.SmsHouseholdBagService;
import com.dzmsoft.sms.base.service.SmsHouseholdOrderService;
import com.dzmsoft.sms.base.service.SmsHouseholdRequireService;
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
@Service(value="orderService00")
@Transactional(readOnly = true)
public class SmsHouseholdOrderServiceImpl implements SmsHouseholdOrderService, OrderService{
    @Value("${clean.household}")
    private String clean_household;
	@Autowired
	private SmsHouseholdOrderMapper smsHouseholdOrderMapper;
	@Autowired
	private SmsOrderService smsOrderService;
	@Autowired
	private SmsHouseholdBagService smsHouseholdBagService;
	@Autowired
	private SmsHouseholdRequireService smsHouseholdRequireService;
	
	@Transactional(readOnly = false)
	@Override
	public int send(String orderId, String id, String cleaner) {
	    // 
	    int flag = smsOrderService.send(orderId, cleaner);
	    //
	    return flag;
	}
	
	@Transactional(readOnly = false)
	@Override
	public int genSmsHouseholdOrder(SmsOrder smsOrder, SmsHouseholdOrder smsHouseholdOrder,
	        List<SmsHouseholdBag> smsHouseholdBagList) {
	    // 保存统一订单
	    String id = RandomUtil.genSnByDate();
	    smsOrder.setId(id);
	    smsOrder.setOrderType(SmsBaseConstant.OrderType.SmsHouseholdOrder);
	    smsOrderService.insertSelective(smsOrder);
	    // 保存家庭保洁订单
	    smsHouseholdOrder.setOrderId(id);
	    int flag = insertSelective(smsHouseholdOrder);
	    // 保存清洁包
	    smsHouseholdBagService.insertBatch(smsHouseholdOrder.getId(), smsHouseholdBagList);
	    return flag;
	}
	
	@Override
	public PageList<SmsHouseholdOrderDto> selectByExample(SmsHouseholdOrderUdfExample example,
	        PageBounds pageBounds) {
	    return smsHouseholdOrderMapper.selectByUdfExample(example, pageBounds);
	}
	
	/**
     * 查询符合条件的记录数量
     * @dzmsoftgenerated 2016-05-21 14:14:45
     */
    @Override
	public int countByExample(SmsHouseholdOrderExample example){
		return smsHouseholdOrderMapper.countByExample(example);
	}

	/**
     * 根据条件插入记录
     * @dzmsoftgenerated 2016-05-21 14:14:45
     */
    @Transactional(readOnly = false)
    @Override
	public int insertSelective(SmsHouseholdOrder record){
        record.setId(StringUtil.getUuidString());
		return smsHouseholdOrderMapper.insertSelective(record);
	}
	
	/**
     * 根据主键查询记录
     * @dzmsoftgenerated 2016-05-21 14:14:45
     */
    @Override
	public SmsHouseholdOrder selectByPrimaryKey(String id){
		return smsHouseholdOrderMapper.selectByPrimaryKey(id);
	}
	
	/**
     * 分页查询记录
     * @dzmsoftgenerated 2016-05-21 14:14:45
     */
    @Override
	public PageList<SmsHouseholdOrder> selectByExample(SmsHouseholdOrderExample example,PageBounds pageBounds){
		return smsHouseholdOrderMapper.selectByExample(example, pageBounds);
	}
	
	/**
     * 根据条件查询记录
     * @dzmsoftgenerated 2016-05-21 14:14:45
     */
    @Override
	public List<SmsHouseholdOrder> selectByExample(SmsHouseholdOrderExample example){
		return smsHouseholdOrderMapper.selectByExample(example);
	}
	
	/**
     * 根据主键更新记录
     * @dzmsoftgenerated 2016-05-21 14:14:45
     */
    @Transactional(readOnly = false)
    @Override
	public int updateByPrimaryKeySelective(SmsHouseholdOrder record){
		return smsHouseholdOrderMapper.updateByPrimaryKeySelective(record);
	}
	
	/**
     * 根据查询条件更新记录
     * @dzmsoftgenerated 2016-05-21 14:14:45
     */
    @Transactional(readOnly = false)
    @Override
	public int updateByExampleSelective(SmsHouseholdOrder record,
			SmsHouseholdOrderExample example){
		return smsHouseholdOrderMapper.updateByExampleSelective(record, example);
	}
	
	/**
     * 根据主键生成记录
     * @dzmsoftgenerated 2016-05-21 14:14:45
     */
    @Transactional(readOnly = false)
    @Override
	public int deleteByPrimaryKey(String id){
		return smsHouseholdOrderMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 根据条件删除字段信息
	 * @dzmsoftgenerated 2016-05-21 14:14:45
	 * @param example
	 * @return
	 */
	@Transactional(readOnly = false)
	@Override
	public int deleteByExample(SmsHouseholdOrderExample example){
		return smsHouseholdOrderMapper.deleteByExample(example);
	}

    @Override
    public Order selectByOrderId(String orderId) {
        SmsHouseholdOrderExample example = new SmsHouseholdOrderExample();
        SmsHouseholdOrderExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdEqualTo(orderId);
        List<SmsHouseholdOrder> smsHouseholdOrders = selectByExample(example);
        return CheckEmptyUtil.isEmpty(smsHouseholdOrders)?null:smsHouseholdOrders.get(0);
    }
}