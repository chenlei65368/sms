package com.dzmsoft.sms.base.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dzmsoft.framework.base.common.BaseConstant;
import com.dzmsoft.framework.base.web.mvc.pojo.ShiroUser;
import com.dzmsoft.framework.base.web.mvc.shiro.UserUtil;
import com.dzmsoft.sms.base.dao.SmsHousingMapper;
import com.dzmsoft.sms.base.pojo.SmsHousing;
import com.dzmsoft.sms.base.pojo.SmsHousingExample;
import com.dzmsoft.sms.base.service.SmsHousingService;
import com.dzmsoft.sms.base.service.SmsSupplierSerivice;
import com.dzmsoft.sms.oim.pojo.OimSupplier;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
/**
 * Copyright (C), dzmsoft Co., Ltd
 * @author 
 * @date 
 *
 * @version 
 */
@Service
@Transactional(readOnly = true)
public class SmsHousingServiceImpl implements SmsHousingService{

	@Autowired
	private SmsHousingMapper smsHousingMapper;
	@Autowired
    private SmsSupplierSerivice smsSupplierSerivice;
	
	@Override
	public String selectTopIndex(String supplier) {
	    SmsHousingExample example = new SmsHousingExample();
	    SmsHousingExample.Criteria criteria = example.createCriteria();
	    criteria.andSupplierEqualTo(supplier);
	    StringBuffer sn = new StringBuffer(supplier);
        String topIndex = smsHousingMapper.selectTopIndex(example);
        sn.append(topIndex);
        return sn.toString();
	}
	
		
	/**
     * 查询符合条件的记录数量
     * @dzmsoftgenerated 
     */
    @Override
	public int countByExample(SmsHousingExample example){
		return smsHousingMapper.countByExample(example);
	}

	/**
     * 根据条件插入记录
     * @dzmsoftgenerated 
     */
    @Transactional(readOnly = false)
    @Override
	public int insertSelective(SmsHousing record){
				ShiroUser shiroUser = UserUtil.getCurrentShiroUser();
				OimSupplier oimSupplier = smsSupplierSerivice.selectByUcsId(shiroUser.getId());
		        if (oimSupplier == null){
		            return -1;
		        }
		        record.setSupplier(oimSupplier.getId());
		        record.setCity(oimSupplier.getRegCity());
		        record.setProvince(oimSupplier.getRegProvince());
		        record.setId(selectTopIndex(oimSupplier.getId()));
				record.setCreator(shiroUser.getId());
				record.setCreateTime(new Date());
				record.setStatus(BaseConstant.Status.ENABLE);
				return smsHousingMapper.insertSelective(record);
	}
	
	/**
     * 根据主键查询记录
     * @dzmsoftgenerated 
     */
    @Override
	public SmsHousing selectByPrimaryKey(String id){
		return smsHousingMapper.selectByPrimaryKey(id);
	}
	
	/**
     * 分页查询记录
     * @dzmsoftgenerated 
     */
    @Override
	public PageList<SmsHousing> selectByExample(SmsHousingExample example,PageBounds pageBounds){
		return smsHousingMapper.selectByExample(example, pageBounds);
	}
	
	/**
     * 根据条件查询记录
     * @dzmsoftgenerated 
     */
    @Override
	public List<SmsHousing> selectByExample(SmsHousingExample example){
		return smsHousingMapper.selectByExample(example);
	}
	
	/**
     * 根据主键更新记录
     * @dzmsoftgenerated 
     */
    @Transactional(readOnly = false)
    @Override
	public int updateByPrimaryKeySelective(SmsHousing record){
		return smsHousingMapper.updateByPrimaryKeySelective(record);
	}
	
	/**
     * 根据查询条件更新记录
     * @dzmsoftgenerated 
     */
    @Transactional(readOnly = false)
    @Override
	public int updateByExampleSelective(SmsHousing record,
			SmsHousingExample example){
		return smsHousingMapper.updateByExampleSelective(record, example);
	}
	
	/**
     * 根据主键生成记录
     * @dzmsoftgenerated 
     */
    @Transactional(readOnly = false)
    @Override
	public int deleteByPrimaryKey(String id){
		return smsHousingMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 根据条件删除字段信息
	 * @dzmsoftgenerated 
	 * @param example
	 * @return
	 */
	@Transactional(readOnly = false)
	@Override
	public int deleteByExample(SmsHousingExample example){
		return smsHousingMapper.deleteByExample(example);
	}
	
		/**
	 * 启用
	 * @dzmsoftgenerated 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = false)
	@Override
	public int enable(String id){
		return _setStatus(id, BaseConstant.Status.ENABLE);
	}
	
	 /**
     * 设置单据状态
     * @param id
     * @param status
     * @return
     */
    private int _setStatus(String id, String status){
        SmsHousing record = new SmsHousing();
        record.setId(id);
        record.setStatus(status);
        return updateByPrimaryKeySelective(record);
    }
	
	/**
	 * 启用
	 * @dzmsoftgenerated 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = false)
	@Override
	public int disable(String id){
		return _setStatus(id, BaseConstant.Status.DISABLED);
	}
	}