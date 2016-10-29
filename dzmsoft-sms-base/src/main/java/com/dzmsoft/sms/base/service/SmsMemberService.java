package com.dzmsoft.sms.base.service;

import java.util.List;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.dzmsoft.sms.base.pojo.SmsMember;
import com.dzmsoft.sms.base.pojo.SmsMemberExample;
import com.dzmsoft.sms.base.pojo.SmsMemberAddress;

/**
 * Copyright (C), dzmsoft Co., Ltd
 * @author 
 * @date 
 *
 * @version 
 */
public interface SmsMemberService {
    /**
     * 更改会员头像
     * @param ucsId
     * @param headerPic
     * @return
     */
    int updateMemberHead(String ucsId , String headerPic); 
    /**
     * 判断会员是否存在
     * @param ucsId
     * @return
     */
    boolean isExist(String ucsId);

	/**
     * 查询符合条件的记录数量
     * @dzmsoftgenerated 
     */
	int countByExample(SmsMemberExample example);
	
	/**
     * 根据条件插入记录
     * @dzmsoftgenerated 
     */
	int insertSelective(SmsMember record);
	
	/**
     * 根据条件插入包含明细数据
     * @dzmsoftgenerated 
     */
	int insertContainDetails(SmsMember record
			,List<SmsMemberAddress> smsMemberAddress
		);
	
	/**
     * 根据条件更新包含明细数据
     * @dzmsoftgenerated 
     */
	int updateContainDetails(SmsMember record
			,List<SmsMemberAddress> smsMemberAddress
		);
	
	/**
     * 根据主键查询记录
     * @dzmsoftgenerated 
     */
	SmsMember selectByPrimaryKey(String id);
	
	/**
     * 分页查询记录
     * @dzmsoftgenerated 
     */
	PageList<SmsMember> selectByExample(SmsMemberExample example,PageBounds pageBounds);
	
	/**
     * 根据条件查询记录
     * @dzmsoftgenerated 
     */
	List<SmsMember> selectByExample(SmsMemberExample example);
	
	/**
     * 根据主键更新记录
     * @dzmsoftgenerated 
     */
	int updateByPrimaryKeySelective(SmsMember record);
	
	/**
     * 根据查询条件更新记录
     * @dzmsoftgenerated 
     */
	int updateByExampleSelective(SmsMember record,
			SmsMemberExample example);
	
	/**
     * 根据主键生成记录
     * @dzmsoftgenerated 
     */
	int deleteByPrimaryKey(String id);
	
	/**
	 * 根据条件删除字段信息
	 * @dzmsoftgenerated 
	 * @param example
	 * @return
	 */
	int deleteByExample(SmsMemberExample example);
	
	}