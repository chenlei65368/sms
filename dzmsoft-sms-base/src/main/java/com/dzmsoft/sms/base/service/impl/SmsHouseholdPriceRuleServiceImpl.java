package com.dzmsoft.sms.base.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dzmsoft.framework.base.common.BaseConstant;
import com.dzmsoft.framework.base.web.mvc.pojo.ShiroUser;
import com.dzmsoft.framework.base.web.mvc.shiro.UserUtil;
import com.dzmsoft.sms.base.dao.SmsHouseholdPriceRuleMapper;
import com.dzmsoft.sms.base.pojo.SmsHouseholdPriceRule;
import com.dzmsoft.sms.base.pojo.SmsHouseholdPriceRuleExample;
import com.dzmsoft.sms.base.service.SmsHouseholdPriceRuleService;
import com.dzmsoft.sms.base.service.SmsSupplierSerivice;
import com.dzmsoft.sms.oim.pojo.OimSupplier;
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
public class SmsHouseholdPriceRuleServiceImpl implements SmsHouseholdPriceRuleService {

    @Autowired
    private SmsHouseholdPriceRuleMapper smsHouseholdPriceRuleMapper;
    @Autowired
    private SmsSupplierSerivice smsSupplierSerivice;

    /**
     * 查询符合条件的记录数量
     * 
     * @dzmsoftgenerated
     */
    @Override
    public int countByExample(SmsHouseholdPriceRuleExample example) {
        return smsHouseholdPriceRuleMapper.countByExample(example);
    }

    /**
     * 根据条件插入记录
     * 
     * @dzmsoftgenerated
     */
    @Transactional(readOnly = false)
    @Override
    public int insertSelective(SmsHouseholdPriceRule record) {
        ShiroUser shiroUser =  UserUtil.getCurrentShiroUser();
        record.setCreator(shiroUser.getId());
        record.setCreateTime(new Date());
        OimSupplier oimSupplier = smsSupplierSerivice.selectByUcsId(shiroUser.getId());
        record.setSupplier(oimSupplier.getId());
        record.setStatus(BaseConstant.Status.DISABLED);
        return smsHouseholdPriceRuleMapper.insertSelective(record);
    }

    /**
     * 根据主键查询记录
     * 
     * @dzmsoftgenerated
     */
    @Override
    public SmsHouseholdPriceRule selectByPrimaryKey(String id) {
        return smsHouseholdPriceRuleMapper.selectByPrimaryKey(id);
    }

    /**
     * 分页查询记录
     * 
     * @dzmsoftgenerated
     */
    @Override
    public PageList<SmsHouseholdPriceRule> selectByExample(SmsHouseholdPriceRuleExample example,
            PageBounds pageBounds) {
        return smsHouseholdPriceRuleMapper.selectByExample(example, pageBounds);
    }

    /**
     * 根据条件查询记录
     * 
     * @dzmsoftgenerated
     */
    @Override
    public List<SmsHouseholdPriceRule> selectByExample(SmsHouseholdPriceRuleExample example) {
        return smsHouseholdPriceRuleMapper.selectByExample(example);
    }

    /**
     * 根据主键更新记录
     * 
     * @dzmsoftgenerated
     */
    @Transactional(readOnly = false)
    @Override
    public int updateByPrimaryKeySelective(SmsHouseholdPriceRule record) {
        return smsHouseholdPriceRuleMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据查询条件更新记录
     * 
     * @dzmsoftgenerated
     */
    @Transactional(readOnly = false)
    @Override
    public int updateByExampleSelective(SmsHouseholdPriceRule record,
            SmsHouseholdPriceRuleExample example) {
        return smsHouseholdPriceRuleMapper.updateByExampleSelective(record, example);
    }

    /**
     * 根据主键生成记录
     * 
     * @dzmsoftgenerated
     */
    @Transactional(readOnly = false)
    @Override
    public int deleteByPrimaryKey(String id) {
        return smsHouseholdPriceRuleMapper.deleteByPrimaryKey(id);
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
    public int deleteByExample(SmsHouseholdPriceRuleExample example) {
        return smsHouseholdPriceRuleMapper.deleteByExample(example);
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
     // 将其他版本设置为禁用
        SmsHouseholdPriceRuleExample example = new SmsHouseholdPriceRuleExample();
        SmsHouseholdPriceRuleExample.Criteria criteria = example.createCriteria();
        criteria.andIdNotEqualTo(id);
        criteria.andStatusEqualTo(BaseConstant.Status.ENABLE);
        //
        SmsHouseholdPriceRule record = new SmsHouseholdPriceRule();
        record.setStatus(BaseConstant.Status.DISABLED);
        updateByExampleSelective(record, example);
        return _setStatus(id, BaseConstant.Status.ENABLE);
    }
    
    /**
     * 设置单据状态
     * @param id
     * @param status
     * @return
     */
    private int _setStatus(String id, String status){
        SmsHouseholdPriceRule record = new SmsHouseholdPriceRule();
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
        SmsHouseholdPriceRule record = new SmsHouseholdPriceRule();
        record.setId(id);
        record.setStatus(BaseConstant.Status.DISABLED);
        return updateByPrimaryKeySelective(record);
    }
}