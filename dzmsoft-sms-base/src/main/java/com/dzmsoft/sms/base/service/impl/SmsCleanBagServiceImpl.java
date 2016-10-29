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
import com.dzmsoft.sms.base.dao.SmsCleanBagMapper;
import com.dzmsoft.sms.base.pojo.SmsCleanBag;
import com.dzmsoft.sms.base.pojo.SmsCleanBagExample;
import com.dzmsoft.sms.base.pojo.SmsCleanBagLine;
import com.dzmsoft.sms.base.service.SmsCleanBagLineService;
import com.dzmsoft.sms.base.service.SmsCleanBagService;
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
public class SmsCleanBagServiceImpl implements SmsCleanBagService {

    @Autowired
    private SmsCleanBagMapper smsCleanBagMapper;

    @Autowired
    private SmsCleanBagLineService smsCleanBagLineService;
    @Autowired
    private SmsSupplierSerivice smsSupplierSerivice;

    /**
     * 根据条件插入包含明细数据
     * 
     * @dzmsoftgenerated
     */
    @Transactional(readOnly = false)
    @Override
    public int insertContainDetails(SmsCleanBag record, List<SmsCleanBagLine> smsCleanBagLine) {
        int flag = -1;
        // 保存头部信息
        flag = insertSelective(record);
        // 保存明细信息
        saveSmsCleanBagLine(record, smsCleanBagLine);
        return flag;
    }

    private void saveSmsCleanBagLine(SmsCleanBag record, List<SmsCleanBagLine> smsCleanBagLine) {
        if (!CheckEmptyUtil.isEmpty(smsCleanBagLine)) {
            // 保存方案明细
            for (SmsCleanBagLine detail : smsCleanBagLine) {
                detail.setCleanBagId(record.getId());
                smsCleanBagLineService.insertSelective(detail);
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
    public int updateContainDetails(SmsCleanBag record, List<SmsCleanBagLine> smsCleanBagLine) {
        // 先删掉明细数据
        smsCleanBagLineService.deleteByCleanBagId(record.getId());
        // 保存明细信息
        saveSmsCleanBagLine(record, smsCleanBagLine);
        // 保存头部信息
        return updateByPrimaryKeySelective(record);
    }

    /**
     * 查询符合条件的记录数量
     * 
     * @dzmsoftgenerated
     */
    @Override
    public int countByExample(SmsCleanBagExample example) {
        return smsCleanBagMapper.countByExample(example);
    }

    /**
     * 根据条件插入记录
     * 
     * @dzmsoftgenerated
     */
    @Transactional(readOnly = false)
    @Override
    public int insertSelective(SmsCleanBag record) {
        record.setId(StringUtil.getUuidString());
        ShiroUser shiroUser =  UserUtil.getCurrentShiroUser();
        record.setCreator(shiroUser.getId());
        record.setCreateTime(new Date());
        OimSupplier oimSupplier = smsSupplierSerivice.selectByUcsId(shiroUser.getId());
        record.setSupplier(oimSupplier.getId());
        record.setStatus(BaseConstant.Status.DISABLED);
        return smsCleanBagMapper.insertSelective(record);
    }

    /**
     * 根据主键查询记录
     * 
     * @dzmsoftgenerated
     */
    @Override
    public SmsCleanBag selectByPrimaryKey(String id) {
        return smsCleanBagMapper.selectByPrimaryKey(id);
    }

    /**
     * 分页查询记录
     * 
     * @dzmsoftgenerated
     */
    @Override
    public PageList<SmsCleanBag> selectByExample(SmsCleanBagExample example, PageBounds pageBounds) {
        return smsCleanBagMapper.selectByExample(example, pageBounds);
    }

    /**
     * 根据条件查询记录
     * 
     * @dzmsoftgenerated
     */
    @Override
    public List<SmsCleanBag> selectByExample(SmsCleanBagExample example) {
        return smsCleanBagMapper.selectByExample(example);
    }

    /**
     * 根据主键更新记录
     * 
     * @dzmsoftgenerated
     */
    @Transactional(readOnly = false)
    @Override
    public int updateByPrimaryKeySelective(SmsCleanBag record) {
        return smsCleanBagMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据查询条件更新记录
     * 
     * @dzmsoftgenerated
     */
    @Transactional(readOnly = false)
    @Override
    public int updateByExampleSelective(SmsCleanBag record, SmsCleanBagExample example) {
        return smsCleanBagMapper.updateByExampleSelective(record, example);
    }

    /**
     * 根据主键生成记录
     * 
     * @dzmsoftgenerated
     */
    @Transactional(readOnly = false)
    @Override
    public int deleteByPrimaryKey(String id) {
        return smsCleanBagMapper.deleteByPrimaryKey(id);
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
    public int deleteByExample(SmsCleanBagExample example) {
        return smsCleanBagMapper.deleteByExample(example);
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
        SmsCleanBagExample example = new SmsCleanBagExample();
        SmsCleanBagExample.Criteria criteria = example.createCriteria();
        criteria.andIdNotEqualTo(id);
        criteria.andStatusEqualTo(BaseConstant.Status.ENABLE);
        //
        SmsCleanBag record = new SmsCleanBag();
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
        SmsCleanBag record = new SmsCleanBag();
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
        SmsCleanBag record = new SmsCleanBag();
        record.setId(id);
        record.setStatus(BaseConstant.Status.DISABLED);
        return updateByPrimaryKeySelective(record);
    }
}