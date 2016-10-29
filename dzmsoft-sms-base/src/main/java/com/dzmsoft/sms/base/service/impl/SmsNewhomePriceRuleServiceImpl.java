package com.dzmsoft.sms.base.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dzmsoft.framework.base.common.BaseConstant;
import com.dzmsoft.framework.base.web.mvc.pojo.ShiroUser;
import com.dzmsoft.framework.base.web.mvc.shiro.UserUtil;
import com.dzmsoft.sms.base.dao.SmsNewhomePriceRuleMapper;
import com.dzmsoft.sms.base.pojo.SmsHouseholdPriceRule;
import com.dzmsoft.sms.base.pojo.SmsHouseholdPriceRuleExample;
import com.dzmsoft.sms.base.pojo.SmsNewhomePriceRule;
import com.dzmsoft.sms.base.pojo.SmsNewhomePriceRuleExample;
import com.dzmsoft.sms.base.service.SmsNewhomePriceRuleService;
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
public class SmsNewhomePriceRuleServiceImpl implements SmsNewhomePriceRuleService {

    @Autowired
    private SmsNewhomePriceRuleMapper smsNewhomePriceRuleMapper;
    @Autowired
    private SmsSupplierSerivice smsSupplierSerivice;

    /**
     * 查询符合条件的记录数量
     * 
     * @dzmsoftgenerated
     */
    @Override
    public int countByExample(SmsNewhomePriceRuleExample example) {
        return smsNewhomePriceRuleMapper.countByExample(example);
    }

    /**
     * 根据条件插入记录
     * 
     * @dzmsoftgenerated
     */
    @Transactional(readOnly = false)
    @Override
    public int insertSelective(SmsNewhomePriceRule record) {
        ShiroUser shiroUser =  UserUtil.getCurrentShiroUser();
        record.setCreator(shiroUser.getId());
        record.setCreateTime(new Date());
        OimSupplier oimSupplier = smsSupplierSerivice.selectByUcsId(shiroUser.getId());
        record.setSupplier(oimSupplier.getId());
        record.setStatus(BaseConstant.Status.DISABLED);
        return smsNewhomePriceRuleMapper.insertSelective(record);
    }

    /**
     * 根据主键查询记录
     * 
     * @dzmsoftgenerated
     */
    @Override
    public SmsNewhomePriceRule selectByPrimaryKey(String id) {
        return smsNewhomePriceRuleMapper.selectByPrimaryKey(id);
    }

    /**
     * 分页查询记录
     * 
     * @dzmsoftgenerated
     */
    @Override
    public PageList<SmsNewhomePriceRule> selectByExample(SmsNewhomePriceRuleExample example,
            PageBounds pageBounds) {
        return smsNewhomePriceRuleMapper.selectByExample(example, pageBounds);
    }

    /**
     * 根据条件查询记录
     * 
     * @dzmsoftgenerated
     */
    @Override
    public List<SmsNewhomePriceRule> selectByExample(SmsNewhomePriceRuleExample example) {
        return smsNewhomePriceRuleMapper.selectByExample(example);
    }

    /**
     * 根据主键更新记录
     * 
     * @dzmsoftgenerated
     */
    @Transactional(readOnly = false)
    @Override
    public int updateByPrimaryKeySelective(SmsNewhomePriceRule record) {
        return smsNewhomePriceRuleMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据查询条件更新记录
     * 
     * @dzmsoftgenerated
     */
    @Transactional(readOnly = false)
    @Override
    public int updateByExampleSelective(SmsNewhomePriceRule record,
            SmsNewhomePriceRuleExample example) {
        return smsNewhomePriceRuleMapper.updateByExampleSelective(record, example);
    }

    /**
     * 根据主键生成记录
     * 
     * @dzmsoftgenerated
     */
    @Transactional(readOnly = false)
    @Override
    public int deleteByPrimaryKey(String id) {
        return smsNewhomePriceRuleMapper.deleteByPrimaryKey(id);
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
    public int deleteByExample(SmsNewhomePriceRuleExample example) {
        return smsNewhomePriceRuleMapper.deleteByExample(example);
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
        SmsNewhomePriceRuleExample example = new SmsNewhomePriceRuleExample();
        SmsNewhomePriceRuleExample.Criteria criteria = example.createCriteria();
        criteria.andIdNotEqualTo(id);
        criteria.andStatusEqualTo(BaseConstant.Status.ENABLE);
        //
        SmsNewhomePriceRule record = new SmsNewhomePriceRule();
        record.setStatus(BaseConstant.Status.DISABLED);
        updateByExampleSelective(record, example);
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
        SmsNewhomePriceRule record = new SmsNewhomePriceRule();
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