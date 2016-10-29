package com.dzmsoft.sms.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dzmsoft.acs.inner.service.UcsService;
import com.dzmsoft.framework.base.util.CheckEmptyUtil;
import com.dzmsoft.framework.base.web.mvc.pojo.ShiroUser;
import com.dzmsoft.framework.base.web.mvc.shiro.UserUtil;
import com.dzmsoft.sms.base.common.SmsBaseConstant;
import com.dzmsoft.sms.base.dao.SmsEmployeeMapper;
import com.dzmsoft.sms.base.pojo.SmsEmployee;
import com.dzmsoft.sms.base.pojo.SmsEmployeeExample;
import com.dzmsoft.sms.base.pojo.SmsServiceEmployeeExample;
import com.dzmsoft.sms.base.pojo.udf.ServiceCleanersUdfExample;
import com.dzmsoft.sms.base.pojo.udf.SmsEmployeeScheduleUdfExample;
import com.dzmsoft.sms.base.service.SmsEmployeeService;
import com.dzmsoft.sms.base.service.SmsSupplierSerivice;
import com.dzmsoft.sms.oim.pojo.OimSupplier;
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
public class SmsEmployeeServiceImpl implements SmsEmployeeService{
    @Autowired
    private UcsService ucsService;
	@Autowired
	private SmsEmployeeMapper smsEmployeeMapper;
	@Autowired
    private SmsSupplierSerivice smsSupplierSerivice;
	
	@Override
	public List<SmsEmployee> selectServiceCleaners(String member, PageBounds pageBounds) {
	    ServiceCleanersUdfExample example = new ServiceCleanersUdfExample();
	    // 保洁员查询条件
	    SmsEmployeeExample.Criteria smsEmployeeCriteria = example.getSmsEmployeeExample().createCriteria();
	    smsEmployeeCriteria.andStatusEqualTo(SmsBaseConstant.EmployeeStatus.IN_JOB);
	    smsEmployeeCriteria.andUserTypeEqualTo(SmsBaseConstant.UserType.CLEANER);
	    // 会员查询条件
	    SmsServiceEmployeeExample.Criteria smsServiceEmployeeCriteria = example.getSmsServiceEmployeeExample().createCriteria();
	    smsServiceEmployeeCriteria.andMemberEqualTo(member);
	    return smsEmployeeMapper.selectServiceCleaners(example, pageBounds);
	}
	
	@Override
	public PageList<SmsEmployee> selectCleanersByCity(String city, PageBounds pageBounds) {
	    SmsEmployeeExample example = new SmsEmployeeExample();
        SmsEmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andCityEqualTo(city);
        criteria.andStatusEqualTo(SmsBaseConstant.EmployeeStatus.IN_JOB);
        criteria.andUserTypeEqualTo(SmsBaseConstant.UserType.CLEANER);
	    return selectByExample(example, pageBounds);
	}
	
	@Override
	public SmsEmployee selectByUcsId(String ucsId) {
	    SmsEmployeeExample example = new SmsEmployeeExample();
	    SmsEmployeeExample.Criteria criteria = example.createCriteria();
	    criteria.andUcsIdEqualTo(ucsId);
	    List<SmsEmployee> smsEmployees = selectByExample(example);
	    return CheckEmptyUtil.isEmpty(smsEmployees)?null:smsEmployees.get(0);
	}
	
	@Override
	public PageList<SmsEmployee> selectNoSchedule(SmsEmployeeScheduleUdfExample example,
	        PageBounds pageBounds) {
	    return smsEmployeeMapper.selectNoSchedule(example, pageBounds);
	}
	
	@Override
	public String selectTopIndex(String prefix) {
	    SmsEmployeeExample example = new SmsEmployeeExample();
	    String sn = smsEmployeeMapper.selectTopIndex(example);
	    StringBuffer sb = new StringBuffer("");
	    if (!CheckEmptyUtil.isEmpty(prefix)){
	        sb.append(new String(prefix.substring(0, 4)));
	    }
	    sb.append(sn);
	    return sb.toString();
	}
	
	@Transactional(readOnly = false)
	@Override
	public int quit(String id) {
	    SmsEmployee smsEmployee = new SmsEmployee();
	    smsEmployee.setId(id);
	    smsEmployee.setStatus(SmsBaseConstant.EmployeeStatus.OUT_JOB);
	    return updateByPrimaryKeySelective(smsEmployee);
	}
	
	/**
     * 查询符合条件的记录数量
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
    @Override
	public int countByExample(SmsEmployeeExample example){
		return smsEmployeeMapper.countByExample(example);
	}

	/**
     * 根据条件插入记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
    @Transactional(readOnly = false)
    @Override
	public int insertSelective(SmsEmployee record){
        ShiroUser user=UserUtil.getCurrentShiroUser();
        OimSupplier oimSupplier = smsSupplierSerivice.selectByUcsId(user.getId());
        if (oimSupplier == null){
            return -1;
        }
        record.setId(selectTopIndex(oimSupplier.getRegCity()));
        record.setSupplier(oimSupplier.getId());
        record.setProvince(oimSupplier.getRegProvince());
        record.setCity(oimSupplier.getRegCity());
        record.setDivision(oimSupplier.getRegDistrict());
        record.setStatus(SmsBaseConstant.EmployeeStatus.IN_JOB);
		return smsEmployeeMapper.insertSelective(record);
	}
	
	/**
     * 根据主键查询记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
    @Override
	public SmsEmployee selectByPrimaryKey(String id){
		return smsEmployeeMapper.selectByPrimaryKey(id);
	}
	
	/**
     * 分页查询记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
    @Override
	public PageList<SmsEmployee> selectByExample(SmsEmployeeExample example,PageBounds pageBounds){
		return smsEmployeeMapper.selectByExample(example, pageBounds);
	}
	
	/**
     * 根据条件查询记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
    @Override
	public List<SmsEmployee> selectByExample(SmsEmployeeExample example){
		return smsEmployeeMapper.selectByExample(example);
	}
	
	/**
     * 根据主键更新记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
    @Transactional(readOnly = false)
    @Override
	public int updateByPrimaryKeySelective(SmsEmployee record){
        if (!record.getUserType().equals(record.getUserType())){
            // 改变用户类型时，需要修改用户中心中的角色、权限
            ucsService.changeUserType(record.getMobile(), record.getUserType(), record.getUserType());
        }
		return smsEmployeeMapper.updateByPrimaryKeySelective(record);
	}
	
	/**
     * 根据查询条件更新记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
    @Transactional(readOnly = false)
    @Override
	public int updateByExampleSelective(SmsEmployee record,
			SmsEmployeeExample example){
		return smsEmployeeMapper.updateByExampleSelective(record, example);
	}
	
	/**
     * 根据主键生成记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     */
    @Transactional(readOnly = false)
    @Override
	public int deleteByPrimaryKey(String id){
		return smsEmployeeMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 根据条件删除字段信息
	 * @dzmsoftgenerated 2016-05-04 17:10:32
	 * @param example
	 * @return
	 */
	@Transactional(readOnly = false)
	@Override
	public int deleteByExample(SmsEmployeeExample example){
		return smsEmployeeMapper.deleteByExample(example);
	}
}