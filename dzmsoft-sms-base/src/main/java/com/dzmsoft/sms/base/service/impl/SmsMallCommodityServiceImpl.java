package com.dzmsoft.sms.base.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dzmsoft.framework.base.web.mvc.pojo.ShiroUser;
import com.dzmsoft.framework.base.web.mvc.shiro.UserUtil;
import com.dzmsoft.sms.base.common.SmsBaseConstant;
import com.dzmsoft.sms.base.dao.SmsMallCommodityMapper;
import com.dzmsoft.sms.base.pojo.SmsMallCommodity;
import com.dzmsoft.sms.base.pojo.SmsMallCommodityExample;
import com.dzmsoft.sms.base.service.SmsMallCommodityService;
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
public class SmsMallCommodityServiceImpl implements SmsMallCommodityService{

	@Autowired
	private SmsMallCommodityMapper smsMallCommodityMapper;
	
	@Override
	public List<SmsMallCommodity> selectByCity(String cityId, PageBounds pageBounds) {
	    SmsMallCommodityExample example = new SmsMallCommodityExample();
        SmsMallCommodityExample.Criteria criteria = example.createCriteria();
        criteria.andCityEqualTo(cityId);
        criteria.andStatusEqualTo(SmsBaseConstant.CommodityStatus.ONLINE);
	    return selectByExample(example, pageBounds);
	}
	
	@Override
	public String selectTopIndex(String brandId) {
	    SmsMallCommodityExample example = new SmsMallCommodityExample();
	    SmsMallCommodityExample.Criteria criteria = example.createCriteria();
	    criteria.andBrandIdEqualTo(brandId);
	    StringBuffer sn = new StringBuffer(brandId);
	    String topIndex = smsMallCommodityMapper.selectTopIndex(example);
	    sn.append(topIndex);
	    return sn.toString();
	}
	
	 @Transactional(readOnly = false)
	@Override
	public int online(String id) {
	     SmsMallCommodity smsMallCommodity = new SmsMallCommodity();
	        smsMallCommodity.setId(id);
	        smsMallCommodity.setStatus(SmsBaseConstant.CommodityStatus.ONLINE);
	        smsMallCommodity.setOnlineTime(new Date());
	        return updateByPrimaryKeySelective(smsMallCommodity);
	}
	
	 @Transactional(readOnly = false)
	@Override
	public int offline(String id) {
	     SmsMallCommodity smsMallCommodity = new SmsMallCommodity();
         smsMallCommodity.setId(id);
         smsMallCommodity.setStatus(SmsBaseConstant.CommodityStatus.OFFLINE);
         smsMallCommodity.setOfflineTime(new Date());
         return updateByPrimaryKeySelective(smsMallCommodity);
	}
	 
	 @Transactional(readOnly = false)
	 @Override
	public int setStatus(String id, String status) {
	    SmsMallCommodity smsMallCommodity = new SmsMallCommodity();
	    smsMallCommodity.setId(id);
	    smsMallCommodity.setStatus(status);
	    return updateByPrimaryKeySelective(smsMallCommodity);
	}
	
	/**
     * 查询符合条件的记录数量
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
    @Override
	public int countByExample(SmsMallCommodityExample example){
		return smsMallCommodityMapper.countByExample(example);
	}

	/**
     * 根据条件插入记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
    @Transactional(readOnly = false)
    @Override
	public int insertSelective(SmsMallCommodity record){
        ShiroUser shiroUser = UserUtil.getCurrentShiroUser();
        record.setCreator(shiroUser.getId());
        record.setCreateTime(new Date());
        record.setId(selectTopIndex(record.getBrandId()));
        record.setStatus(SmsBaseConstant.CommodityStatus.INIT);
		return smsMallCommodityMapper.insertSelective(record);
	}
	
	/**
     * 根据主键查询记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
    @Override
	public SmsMallCommodity selectByPrimaryKey(String id){
		return smsMallCommodityMapper.selectByPrimaryKey(id);
	}
	
	/**
     * 分页查询记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
    @Override
	public PageList<SmsMallCommodity> selectByExample(SmsMallCommodityExample example,PageBounds pageBounds){
		return smsMallCommodityMapper.selectByExample(example, pageBounds);
	}
	
	/**
     * 根据条件查询记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
    @Override
	public List<SmsMallCommodity> selectByExample(SmsMallCommodityExample example){
		return smsMallCommodityMapper.selectByExample(example);
	}
	
	/**
     * 根据主键更新记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
    @Transactional(readOnly = false)
    @Override
	public int updateByPrimaryKeySelective(SmsMallCommodity record){
		return smsMallCommodityMapper.updateByPrimaryKeySelective(record);
	}
	
	/**
     * 根据查询条件更新记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
    @Transactional(readOnly = false)
    @Override
	public int updateByExampleSelective(SmsMallCommodity record,
			SmsMallCommodityExample example){
		return smsMallCommodityMapper.updateByExampleSelective(record, example);
	}
	
	/**
     * 根据主键生成记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
    @Transactional(readOnly = false)
    @Override
	public int deleteByPrimaryKey(String id){
		return smsMallCommodityMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 根据条件删除字段信息
	 * @dzmsoftgenerated 2016-05-04 17:10:32
	 * @param example
	 * @return
	 */
	@Transactional(readOnly = false)
	@Override
	public int deleteByExample(SmsMallCommodityExample example){
		return smsMallCommodityMapper.deleteByExample(example);
	}
}