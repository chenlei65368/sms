package com.dzmsoft.sms.base.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dzmsoft.framework.base.common.BaseConstant;
import com.dzmsoft.framework.base.web.mvc.pojo.ShiroUser;
import com.dzmsoft.framework.base.web.mvc.shiro.UserUtil;
import com.dzmsoft.sms.base.dao.SmsMallBrandMapper;
import com.dzmsoft.sms.base.pojo.SmsMallBrand;
import com.dzmsoft.sms.base.pojo.SmsMallBrandExample;
import com.dzmsoft.sms.base.service.SmsMallBrandService;
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
public class SmsMallBrandServiceImpl implements SmsMallBrandService{

	@Autowired
	private SmsMallBrandMapper smsMallBrandMapper;
	
	@Override
	public String selectTopIndex(String category) {
	    SmsMallBrandExample example = new SmsMallBrandExample();
	    SmsMallBrandExample.Criteria criteria = example.createCriteria();
	    criteria.andCategoryEqualTo(category);
	    StringBuffer sn = new StringBuffer(category);
        String topIndex = smsMallBrandMapper.selectTopIndex(example);
        sn.append(topIndex);
        return sn.toString();
	}
	
	/**
     * 查询符合条件的记录数量
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
    @Override
	public int countByExample(SmsMallBrandExample example){
		return smsMallBrandMapper.countByExample(example);
	}

	/**
     * 根据条件插入记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
    @Transactional(readOnly = false)
    @Override
    @CacheEvict(value="brandCache",allEntries=true)
	public int insertSelective(SmsMallBrand record){
        record.setId(selectTopIndex(record.getCategory()));
        ShiroUser shiroUser = UserUtil.getCurrentShiroUser();
        record.setCreator(shiroUser.getId());
        record.setCreateTime(new Date());
        record.setStatus(BaseConstant.Status.ENABLE);
		return smsMallBrandMapper.insertSelective(record);
	}
	
	/**
     * 根据主键查询记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
    @Cacheable(value="brandCache", key = "#id")
    @Override
	public SmsMallBrand selectByPrimaryKey(String id){
		return smsMallBrandMapper.selectByPrimaryKey(id);
	}
	
	/**
     * 分页查询记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
    @Override
	public PageList<SmsMallBrand> selectByExample(SmsMallBrandExample example,PageBounds pageBounds){
		return smsMallBrandMapper.selectByExample(example, pageBounds);
	}
	
	/**
     * 根据条件查询记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
    @Override
	public List<SmsMallBrand> selectByExample(SmsMallBrandExample example){
		return smsMallBrandMapper.selectByExample(example);
	}
	
	/**
     * 根据主键更新记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
    @Transactional(readOnly = false)
    @Override
    @CacheEvict(value="brandCache",allEntries=true)
	public int updateByPrimaryKeySelective(SmsMallBrand record){
		return smsMallBrandMapper.updateByPrimaryKeySelective(record);
	}
	
	/**
     * 根据查询条件更新记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
    @Transactional(readOnly = false)
    @Override
    @CacheEvict(value="brandCache",allEntries=true)
	public int updateByExampleSelective(SmsMallBrand record,
			SmsMallBrandExample example){
		return smsMallBrandMapper.updateByExampleSelective(record, example);
	}
	
	/**
     * 根据主键生成记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
    @Transactional(readOnly = false)
    @Override
    @CacheEvict(value="brandCache",allEntries=true)
	public int deleteByPrimaryKey(String id){
		return smsMallBrandMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 根据条件删除字段信息
	 * @dzmsoftgenerated 2016-05-04 17:10:32
	 * @param example
	 * @return
	 */
	@Transactional(readOnly = false)
	@Override
	@CacheEvict(value="brandCache",allEntries=true)
	public int deleteByExample(SmsMallBrandExample example){
		return smsMallBrandMapper.deleteByExample(example);
	}
}