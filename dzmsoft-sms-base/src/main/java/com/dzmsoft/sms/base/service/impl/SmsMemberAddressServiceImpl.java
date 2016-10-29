package com.dzmsoft.sms.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.dzmsoft.sms.base.pojo.SmsMemberAddress;
import com.dzmsoft.sms.base.pojo.SmsMemberAddressExample;
import com.dzmsoft.sms.base.dao.SmsMemberAddressMapper;
import com.dzmsoft.sms.base.service.SmsMemberAddressService;
import com.dzmsoft.framework.base.common.BaseConstant;
import com.dzmsoft.framework.base.util.StringUtil;
/**
 * Copyright (C), dzmsoft Co., Ltd
 * @author 
 * @date 
 *
 * @version 
 */
@Service
@Transactional(readOnly = true)
public class SmsMemberAddressServiceImpl implements SmsMemberAddressService{

	@Autowired
	private SmsMemberAddressMapper smsMemberAddressMapper;
	
	@Transactional(readOnly = false)
	@Override
	public int setDefaultAddress(String id) {
	    SmsMemberAddress smsMemberAddress = new SmsMemberAddress();
	    smsMemberAddress.setId(id);
	    smsMemberAddress.setIsDefault(BaseConstant.Bool.TRUE);
	    return updateByPrimaryKeySelective(smsMemberAddress);
	}
	
		/**
     * 根据订单头id，删除订单明细
     * @dzmsoftgenerated 
     */
	@Override
	public List<SmsMemberAddress> selectByMain(String mainId) {
	    SmsMemberAddressExample example = new SmsMemberAddressExample();
	    SmsMemberAddressExample.Criteria criteria = example.createCriteria();
	    	    criteria.andUcsIdEqualTo(mainId);
	    return selectByExample(example);
	}
	
	/**
     * 根据订单头id，删除订单明细
     * @dzmsoftgenerated 
     */
	@Override
	public int deleteByMain(String mainId) {
	    SmsMemberAddressExample example = new SmsMemberAddressExample();
	    SmsMemberAddressExample.Criteria criteria = example.createCriteria();
	    	    criteria.andUcsIdEqualTo(mainId);
	    return deleteByExample(example);
	}
		
	/**
     * 查询符合条件的记录数量
     * @dzmsoftgenerated 
     */
    @Override
	public int countByExample(SmsMemberAddressExample example){
		return smsMemberAddressMapper.countByExample(example);
	}

	/**
     * 根据条件插入记录
     * @dzmsoftgenerated 
     */
    @Transactional(readOnly = false)
    @Override
	public int insertSelective(SmsMemberAddress record){
				record.setId(StringUtil.getUuidString());
				return smsMemberAddressMapper.insertSelective(record);
	}
	
	/**
     * 根据主键查询记录
     * @dzmsoftgenerated 
     */
    @Override
	public SmsMemberAddress selectByPrimaryKey(String id){
		return smsMemberAddressMapper.selectByPrimaryKey(id);
	}
	
	/**
     * 分页查询记录
     * @dzmsoftgenerated 
     */
    @Override
	public PageList<SmsMemberAddress> selectByExample(SmsMemberAddressExample example,PageBounds pageBounds){
		return smsMemberAddressMapper.selectByExample(example, pageBounds);
	}
	
	/**
     * 根据条件查询记录
     * @dzmsoftgenerated 
     */
    @Override
	public List<SmsMemberAddress> selectByExample(SmsMemberAddressExample example){
		return smsMemberAddressMapper.selectByExample(example);
	}
	
	/**
     * 根据主键更新记录
     * @dzmsoftgenerated 
     */
    @Transactional(readOnly = false)
    @Override
	public int updateByPrimaryKeySelective(SmsMemberAddress record){
		return smsMemberAddressMapper.updateByPrimaryKeySelective(record);
	}
	
	/**
     * 根据查询条件更新记录
     * @dzmsoftgenerated 
     */
    @Transactional(readOnly = false)
    @Override
	public int updateByExampleSelective(SmsMemberAddress record,
			SmsMemberAddressExample example){
		return smsMemberAddressMapper.updateByExampleSelective(record, example);
	}
	
	/**
     * 根据主键生成记录
     * @dzmsoftgenerated 
     */
    @Transactional(readOnly = false)
    @Override
	public int deleteByPrimaryKey(String id){
		return smsMemberAddressMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 根据条件删除字段信息
	 * @dzmsoftgenerated 
	 * @param example
	 * @return
	 */
	@Transactional(readOnly = false)
	@Override
	public int deleteByExample(SmsMemberAddressExample example){
		return smsMemberAddressMapper.deleteByExample(example);
	}
	
	}