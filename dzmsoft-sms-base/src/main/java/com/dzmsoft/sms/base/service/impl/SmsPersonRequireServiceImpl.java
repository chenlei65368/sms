package com.dzmsoft.sms.base.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dzmsoft.framework.base.common.BaseConstant;
import com.dzmsoft.framework.base.util.CheckEmptyUtil;
import com.dzmsoft.framework.base.util.StringUtil;
import com.dzmsoft.framework.base.web.mvc.pojo.ShiroUser;
import com.dzmsoft.framework.base.web.mvc.shiro.UserUtil;
import com.dzmsoft.sms.base.dao.SmsPersonRequireMapper;
import com.dzmsoft.sms.base.pojo.SmsPersonRequire;
import com.dzmsoft.sms.base.pojo.SmsPersonRequireExample;
import com.dzmsoft.sms.base.pojo.SmsPersonRequireLine;
import com.dzmsoft.sms.base.service.SmsPersonRequireLineService;
import com.dzmsoft.sms.base.service.SmsPersonRequireService;
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
public class SmsPersonRequireServiceImpl implements SmsPersonRequireService {

    @Autowired
    private SmsPersonRequireMapper smsPersonRequireMapper;

    @Autowired
    private SmsPersonRequireLineService smsPersonRequireLineService;
    @Autowired
    private SmsSupplierSerivice smsSupplierSerivice;

    /**
     * 根据条件插入包含明细数据
     * 
     * @dzmsoftgenerated
     */
    @Transactional(readOnly = false)
    @Override
    public int insertContainDetails(SmsPersonRequire record,
            List<SmsPersonRequireLine> smsPersonRequireLine) {
        int flag = -1;
        // 保存头部信息
        flag = insertSelective(record);
        // 保存明细信息
        saveSmsPersonRequireLine(record, smsPersonRequireLine);
        return flag;
    }

    private void saveSmsPersonRequireLine(SmsPersonRequire record,
            List<SmsPersonRequireLine> smsPersonRequireLine) {
        if (!CheckEmptyUtil.isEmpty(smsPersonRequireLine)) {
            // 保存方案明细
            for (SmsPersonRequireLine detail : smsPersonRequireLine) {
                detail.setRequireId(record.getId());
                smsPersonRequireLineService.insertSelective(detail);
            }
        }
    }

    /**
     * 根据条件更新包含明细数据
     * 
     * @dzmsoftgenerated
     */
    @Transactional(readOnly = false)
    @Override
    public int updateContainDetails(SmsPersonRequire record,
            List<SmsPersonRequireLine> smsPersonRequireLine) {
        // 先删掉明细数据
        smsPersonRequireLineService.deleteBySmsPersonRequire(record.getId());
        // 保存明细信息
        saveSmsPersonRequireLine(record, smsPersonRequireLine);
        // 保存头部信息
        return updateByPrimaryKeySelective(record);
    }

    /**
     * 查询符合条件的记录数量
     * 
     * @dzmsoftgenerated
     */
    @Override
    public int countByExample(SmsPersonRequireExample example) {
        return smsPersonRequireMapper.countByExample(example);
    }

    /**
     * 根据条件插入记录
     * 
     * @dzmsoftgenerated
     */
    @Transactional(readOnly = false)
    @Override
    public int insertSelective(SmsPersonRequire record) {
        record.setId(StringUtil.getUuidString());
        ShiroUser shiroUser =  UserUtil.getCurrentShiroUser();
        record.setCreator(shiroUser.getId());
        record.setCreateTime(new Date());
        OimSupplier oimSupplier = smsSupplierSerivice.selectByUcsId(shiroUser.getId());
        record.setSupplier(oimSupplier.getId());
        record.setStatus(BaseConstant.Status.DISABLED);
        return smsPersonRequireMapper.insertSelective(record);
    }

    /**
     * 根据主键查询记录
     * 
     * @dzmsoftgenerated
     */
    @Override
    public SmsPersonRequire selectByPrimaryKey(String id) {
        return smsPersonRequireMapper.selectByPrimaryKey(id);
    }

    /**
     * 分页查询记录
     * 
     * @dzmsoftgenerated
     */
    @Override
    public PageList<SmsPersonRequire> selectByExample(SmsPersonRequireExample example,
            PageBounds pageBounds) {
        return smsPersonRequireMapper.selectByExample(example, pageBounds);
    }

    /**
     * 根据条件查询记录
     * 
     * @dzmsoftgenerated
     */
    @Override
    public List<SmsPersonRequire> selectByExample(SmsPersonRequireExample example) {
        return smsPersonRequireMapper.selectByExample(example);
    }

    /**
     * 根据主键更新记录
     * 
     * @dzmsoftgenerated
     */
    @Transactional(readOnly = false)
    @Override
    public int updateByPrimaryKeySelective(SmsPersonRequire record) {
        return smsPersonRequireMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据查询条件更新记录
     * 
     * @dzmsoftgenerated
     */
    @Transactional(readOnly = false)
    @Override
    public int updateByExampleSelective(SmsPersonRequire record, SmsPersonRequireExample example) {
        return smsPersonRequireMapper.updateByExampleSelective(record, example);
    }

    /**
     * 根据主键生成记录
     * 
     * @dzmsoftgenerated
     */
    @Transactional(readOnly = false)
    @Override
    public int deleteByPrimaryKey(String id) {
        return smsPersonRequireMapper.deleteByPrimaryKey(id);
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
    public int deleteByExample(SmsPersonRequireExample example) {
        return smsPersonRequireMapper.deleteByExample(example);
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
        SmsPersonRequireExample example = new SmsPersonRequireExample();
        SmsPersonRequireExample.Criteria criteria = example.createCriteria();
        criteria.andIdNotEqualTo(id);
        criteria.andStatusEqualTo(BaseConstant.Status.ENABLE);
        //
        SmsPersonRequire record = new SmsPersonRequire();
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
        SmsPersonRequire record = new SmsPersonRequire();
        record.setId(id);
        record.setStatus(status);
        return updateByPrimaryKeySelective(record);
    }
    
    /**
     * 禁用
     * 
     * @dzmsoftgenerated
     * @param id
     * @return
     */
    @Transactional(readOnly = false)
    @Override
    public int disable(String id) {
        SmsPersonRequire record = new SmsPersonRequire();
        record.setId(id);
        record.setStatus(BaseConstant.Status.DISABLED);
        return updateByPrimaryKeySelective(record);
    }
}