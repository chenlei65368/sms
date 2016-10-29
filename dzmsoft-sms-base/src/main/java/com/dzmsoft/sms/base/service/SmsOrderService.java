package com.dzmsoft.sms.base.service;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.dzmsoft.sms.base.pojo.SmsOrder;
import com.dzmsoft.sms.base.pojo.SmsOrderExample;

/**
 * Copyright (C), dzmsoft Co., Ltd
 * @author dzmsoft
 * @date 2016-05-04 17:10:32
 *
 * @version 1.0
 */
public interface SmsOrderService {
    /**
     * 根据城市获取未抢单的订单清单
     * @param city
     * @param pageBounds
     * @return
     */
    List<SmsOrder> selectNoGrabOrders(String city, PageBounds pageBounds);
    /**
     * 获取保洁员的订单
     * @param cleaner
     * @param statusList
     * @param pageBounds
     * @return
     */
    List<SmsOrder> selectByCleaner(String cleaner,List<String> statusList, PageBounds pageBounds);
    /**
     * 根据会员、状态查询订单清单
     * @param member
     * @param statusList
     * @param pageBounds
     * @return
     */
    List<SmsOrder> selectByMember(String member, List<String> statusList, PageBounds pageBounds);
    /**
     * 判断是否已抢单
     * @param id
     * @return
     */
    boolean hasGrab(String id);
    /**
     * 派单
     * @param id
     * @param director
     * @return
     */
    int send(String id, String director);
    /**
     * 抢单
     * @param id
     * @return
     */
    int grab(String id, String director);
    /**
     * 取消订单
     * @param id
     * @return
     */
    int cancelByMember(String id);

	/**
     * 查询符合条件的记录数量
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
	int countByExample(SmsOrderExample example);
	
	/**
     * 根据条件插入记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
	int insertSelective(SmsOrder record);
	
	/**
     * 根据主键查询记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
	SmsOrder selectByPrimaryKey(String id);
	
	/**
     * 分页查询记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
	PageList<SmsOrder> selectByExample(SmsOrderExample example,PageBounds pageBounds);
	
	/**
     * 根据条件查询记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
	List<SmsOrder> selectByExample(SmsOrderExample example);
	
	/**
     * 根据主键更新记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
	int updateByPrimaryKeySelective(SmsOrder record);
	
	/**
     * 根据查询条件更新记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
	int updateByExampleSelective(SmsOrder record,
			SmsOrderExample example);
	
	/**
     * 根据主键生成记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
	int deleteByPrimaryKey(String id);
	
	/**
	 * 根据条件删除字段信息
	 * @dzmsoftgenerated 2016-05-04 17:10:32
	 * @param example
	 * @return
	 */
	int deleteByExample(SmsOrderExample example);
	
	
}