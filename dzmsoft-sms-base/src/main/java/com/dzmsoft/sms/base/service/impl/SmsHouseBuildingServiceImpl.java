package com.dzmsoft.sms.base.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dzmsoft.framework.base.common.BaseConstant;
import com.dzmsoft.framework.base.util.StringUtil;
import com.dzmsoft.framework.base.web.mvc.pojo.ShiroUser;
import com.dzmsoft.framework.base.web.mvc.shiro.UserUtil;
import com.dzmsoft.sms.base.dao.SmsHouseBuildingMapper;
import com.dzmsoft.sms.base.pojo.SmsHouseBuilding;
import com.dzmsoft.sms.base.pojo.SmsHouseBuildingExample;
import com.dzmsoft.sms.base.service.SmsHouseBuildingService;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * Copyright (C), dzmsoft Co., Ltd
 * 
 * @author
 * @date
 * @version
 */
@Service
@Transactional(readOnly = true)
public class SmsHouseBuildingServiceImpl implements SmsHouseBuildingService {

    @Autowired
    private SmsHouseBuildingMapper smsHouseBuildingMapper;
    
    @Override
    public boolean isExist(String id) {
        SmsHouseBuildingExample example = new SmsHouseBuildingExample();
        SmsHouseBuildingExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        int count = countByExample(example);
        return count>0?true:false;
    }

    /**
     * 查询符合条件的记录数量
     * 
     * @dzmsoftgenerated
     */
    @Override
    public int countByExample(SmsHouseBuildingExample example) {
        return smsHouseBuildingMapper.countByExample(example);
    }

    /**
     * 根据条件插入记录
     * 
     * @dzmsoftgenerated
     */
    @Transactional(readOnly = false)
    @Override
    public int insertSelective(SmsHouseBuilding record) {
        ShiroUser shiroUser = UserUtil.getCurrentShiroUser();
        record.setCreator(shiroUser.getId());
        record.setCreateTime(new Date());
        return smsHouseBuildingMapper.insertSelective(record);
    }

    /**
     * 根据主键查询记录
     * 
     * @dzmsoftgenerated
     */
    @Override
    public SmsHouseBuilding selectByPrimaryKey(String id) {
        return smsHouseBuildingMapper.selectByPrimaryKey(id);
    }

    /**
     * 分页查询记录
     * 
     * @dzmsoftgenerated
     */
    @Override
    public PageList<SmsHouseBuilding> selectByExample(SmsHouseBuildingExample example,
            PageBounds pageBounds) {
        return smsHouseBuildingMapper.selectByExample(example, pageBounds);
    }

    /**
     * 根据条件查询记录
     * 
     * @dzmsoftgenerated
     */
    @Override
    public List<SmsHouseBuilding> selectByExample(SmsHouseBuildingExample example) {
        return smsHouseBuildingMapper.selectByExample(example);
    }

    /**
     * 根据主键更新记录
     * 
     * @dzmsoftgenerated
     */
    @Transactional(readOnly = false)
    @Override
    public int updateByPrimaryKeySelective(SmsHouseBuilding record) {
        return smsHouseBuildingMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据查询条件更新记录
     * 
     * @dzmsoftgenerated
     */
    @Transactional(readOnly = false)
    @Override
    public int updateByExampleSelective(SmsHouseBuilding record, SmsHouseBuildingExample example) {
        return smsHouseBuildingMapper.updateByExampleSelective(record, example);
    }

    /**
     * 根据主键生成记录
     * 
     * @dzmsoftgenerated
     */
    @Transactional(readOnly = false)
    @Override
    public int deleteByPrimaryKey(String id) {
        return smsHouseBuildingMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据条件删除字段信息
     * 
     * @dzmsoftgenerated
     * @param example
     * @return
     */
    @Transactional(readOnly = false)
    @Override
    public int deleteByExample(SmsHouseBuildingExample example) {
        return smsHouseBuildingMapper.deleteByExample(example);
    }

    /**
     * 启用
     * 
     * @dzmsoftgenerated
     * @param id
     * @return
     */
    @Transactional(readOnly = false)
    @Override
    public int enable(String id) {
        return _setStatus(id, BaseConstant.Status.ENABLE);
    }

    /**
     * 设置单据状态
     * 
     * @param id
     * @param status
     * @return
     */
    private int _setStatus(String id, String status) {
        SmsHouseBuilding record = new SmsHouseBuilding();
        record.setId(id);
        record.setStatus(status);
        return updateByPrimaryKeySelective(record);
    }

    /**
     * 启用
     * 
     * @dzmsoftgenerated
     * @param id
     * @return
     */
    @Transactional(readOnly = false)
    @Override
    public int disable(String id) {
        return _setStatus(id, BaseConstant.Status.DISABLED);
    }
}