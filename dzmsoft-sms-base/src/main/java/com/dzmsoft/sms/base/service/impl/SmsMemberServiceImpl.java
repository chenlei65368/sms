package com.dzmsoft.sms.base.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dzmsoft.framework.base.util.CheckEmptyUtil;
import com.dzmsoft.sms.base.common.SmsBaseConstant;
import com.dzmsoft.sms.base.dao.SmsMemberMapper;
import com.dzmsoft.sms.base.pojo.SmsMember;
import com.dzmsoft.sms.base.pojo.SmsMemberAddress;
import com.dzmsoft.sms.base.pojo.SmsMemberExample;
import com.dzmsoft.sms.base.service.SmsMemberAddressService;
import com.dzmsoft.sms.base.service.SmsMemberService;
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
public class SmsMemberServiceImpl implements SmsMemberService {

    @Autowired
    private SmsMemberMapper smsMemberMapper;

    @Autowired
    private SmsMemberAddressService smsMemberAddressService;
    
    @Transactional(readOnly = false)
    @Override
    public int updateMemberHead(String ucsId, String headerPic) {
        SmsMember smsMember = new SmsMember();
        smsMember.setId(ucsId);
        smsMember.setHeaderPic(headerPic);
        return updateByPrimaryKeySelective(smsMember);
    }
    
    @Override
    public boolean isExist(String ucsId) {
        SmsMemberExample example = new SmsMemberExample();
        SmsMemberExample.Criteria criteria = example.createCriteria();
        criteria.andUcsIdEqualTo(ucsId);
        int count = countByExample(example);
        return count>0?true:false;
    }

    /**
     * 根据条件插入包含明细数据
     * 
     * @dzmsoftgenerated
     */
    @Transactional(readOnly = false)
    @Override
    public int insertContainDetails(SmsMember record, List<SmsMemberAddress> smsMemberAddress) {
        int flag = -1;
        // 保存头部信息
        flag = insertSelective(record);
        // 保存明细信息
        saveSmsMemberAddress(record, smsMemberAddress);
        return flag;
    }

    private void saveSmsMemberAddress(SmsMember record, List<SmsMemberAddress> smsMemberAddress) {
        if (!CheckEmptyUtil.isEmpty(smsMemberAddress)) {
            // 保存方案明细
            for (SmsMemberAddress detail : smsMemberAddress) {
                detail.setUcsId(record.getId());
                smsMemberAddressService.insertSelective(detail);
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
    public int updateContainDetails(SmsMember record, List<SmsMemberAddress> smsMemberAddress) {

        // 先删掉明细数据
        smsMemberAddressService.deleteByMain(record.getId());
        // 保存明细信息
        saveSmsMemberAddress(record, smsMemberAddress);
        // 保存头部信息
        return updateByPrimaryKeySelective(record);
    }

    /**
     * 查询符合条件的记录数量
     * 
     * @dzmsoftgenerated
     */
    @Override
    public int countByExample(SmsMemberExample example) {
        return smsMemberMapper.countByExample(example);
    }

    /**
     * 根据条件插入记录
     * 
     * @dzmsoftgenerated
     */
    @Transactional(readOnly = false)
    @Override
    public int insertSelective(SmsMember record) {
        record.setCreateTime(new Date());
        record.setStatus(SmsBaseConstant.MemberStatus.VALID);
        if (CheckEmptyUtil.isEmpty(record.getName())){
            record.setName(record.getMobile());
        }
        return smsMemberMapper.insertSelective(record);
    }

    /**
     * 根据主键查询记录
     * 
     * @dzmsoftgenerated
     */
    @Override
    public SmsMember selectByPrimaryKey(String id) {
        return smsMemberMapper.selectByPrimaryKey(id);
    }

    /**
     * 分页查询记录
     * 
     * @dzmsoftgenerated
     */
    @Override
    public PageList<SmsMember> selectByExample(SmsMemberExample example, PageBounds pageBounds) {
        return smsMemberMapper.selectByExample(example, pageBounds);
    }

    /**
     * 根据条件查询记录
     * 
     * @dzmsoftgenerated
     */
    @Override
    public List<SmsMember> selectByExample(SmsMemberExample example) {
        return smsMemberMapper.selectByExample(example);
    }

    /**
     * 根据主键更新记录
     * 
     * @dzmsoftgenerated
     */
    @Transactional(readOnly = false)
    @Override
    public int updateByPrimaryKeySelective(SmsMember record) {
        return smsMemberMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据查询条件更新记录
     * 
     * @dzmsoftgenerated
     */
    @Transactional(readOnly = false)
    @Override
    public int updateByExampleSelective(SmsMember record, SmsMemberExample example) {
        return smsMemberMapper.updateByExampleSelective(record, example);
    }

    /**
     * 根据主键生成记录
     * 
     * @dzmsoftgenerated
     */
    @Transactional(readOnly = false)
    @Override
    public int deleteByPrimaryKey(String id) {
        return smsMemberMapper.deleteByPrimaryKey(id);
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
    public int deleteByExample(SmsMemberExample example) {
        return smsMemberMapper.deleteByExample(example);
    }

}