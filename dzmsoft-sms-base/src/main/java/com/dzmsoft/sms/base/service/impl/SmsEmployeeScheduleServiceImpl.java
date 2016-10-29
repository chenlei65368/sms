package com.dzmsoft.sms.base.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dzmsoft.framework.base.util.CheckEmptyUtil;
import com.dzmsoft.framework.base.util.DateUtil;
import com.dzmsoft.framework.base.util.StringUtil;
import com.dzmsoft.framework.base.web.mvc.pojo.ShiroUser;
import com.dzmsoft.framework.base.web.mvc.shiro.UserUtil;
import com.dzmsoft.framework.base.web.mvc.view.ComboBoxData;
import com.dzmsoft.sms.base.common.SmsBaseConstant;
import com.dzmsoft.sms.base.dao.SmsEmployeeScheduleMapper;
import com.dzmsoft.sms.base.pojo.SmsEmployee;
import com.dzmsoft.sms.base.pojo.SmsEmployeeSchedule;
import com.dzmsoft.sms.base.pojo.SmsEmployeeScheduleExample;
import com.dzmsoft.sms.base.pojo.SmsShift;
import com.dzmsoft.sms.base.service.SmsEmployeeScheduleService;
import com.dzmsoft.sms.base.service.SmsSupplierSerivice;
import com.dzmsoft.sms.oim.pojo.OimSupplier;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
/**
 * Copyright (C), dzmsoft Co., Ltd
 * @author dzmsoft
 * @date 2016-05-15 09:42:23
 *
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class SmsEmployeeScheduleServiceImpl implements SmsEmployeeScheduleService{

	@Autowired
	private SmsEmployeeScheduleMapper smsEmployeeScheduleMapper;
	@Autowired
    private SmsSupplierSerivice smsSupplierSerivice;
	
	@Override
	public boolean isExist(Date day, String employee) {
	    SmsEmployeeScheduleExample example = new SmsEmployeeScheduleExample();
        SmsEmployeeScheduleExample.Criteria criteria = example.createCriteria();
        criteria.andDayEqualTo(day);
        criteria.andEmployeeEqualTo(employee);
        int count = countByExample(example);
	    return count>0?true:false;
	}
	
	@Transactional(readOnly = false)
	@Override
	public int arrange(List<SmsEmployee> smsEmployees, List<ComboBoxData> dateBoxDatas, SmsShift smsShift) {
	    int flag = 0;
	    if (!CheckEmptyUtil.isEmpty(smsEmployees)){
	        SmsEmployeeSchedule smsEmployeeSchedule = null;
	        Date day = null;
	        ShiroUser user=UserUtil.getCurrentShiroUser();
	        OimSupplier oimSupplier = smsSupplierSerivice.selectByUcsId(user.getId());
	        for (SmsEmployee smsEmployee: smsEmployees){
	            for (ComboBoxData comboBoxData:dateBoxDatas ){
	                day = DateUtil.parseDate(comboBoxData.getValueField());
	                if (!isExist(day, smsEmployee.getId())){
	                    smsEmployeeSchedule = new SmsEmployeeSchedule();
	                    smsEmployeeSchedule.setSupplier(oimSupplier.getId());
	                    smsEmployeeSchedule.setId(StringUtil.getUuidString());
	                    smsEmployeeSchedule.setDay(day); // 日期
	                    smsEmployeeSchedule.setEmployee(smsEmployee.getId());
	                    // 根据排班，设置时间点
	                    setScheduleByShift(smsShift, smsEmployeeSchedule);
	                    flag = insertSelective(smsEmployeeSchedule);
	                }
	            }
	        }
	    }
	    return flag;
	}
	
	/**
	 * 根据排班，设置时间点
	 * @param smsShift
	 * @param smsEmployeeSchedule
	 */
	private void setScheduleByShift(SmsShift smsShift, SmsEmployeeSchedule smsEmployeeSchedule){
	    Integer begin = Integer.valueOf(smsShift.getBeginTime());
	    Integer end = Integer.valueOf(smsShift.getEndTime());
	    for (int i=begin; i<=end; i++){
	        switch(i){
	        case 1:
	            break;
	        case 2:
	            break;
	        case 3:
	            break;
	        case 4:
	            break;
	        case 5:
	            break;
	        case 6:
	            smsEmployeeSchedule.setSix(SmsBaseConstant.Shift.IDEL);
	            break;
	        case 7:
	            smsEmployeeSchedule.setSeven(SmsBaseConstant.Shift.IDEL);
	            break;
	        case 8:
	            smsEmployeeSchedule.setEight(SmsBaseConstant.Shift.IDEL);
	            break;
	        case 9:
	            smsEmployeeSchedule.setNine(SmsBaseConstant.Shift.IDEL);
	            break;
	        case 10:
	            smsEmployeeSchedule.setTen(SmsBaseConstant.Shift.IDEL);
	            break;
	        case 11:
	            smsEmployeeSchedule.setEleven(SmsBaseConstant.Shift.IDEL);
	            break;
	        case 12:
	            smsEmployeeSchedule.setTwelve(SmsBaseConstant.Shift.IDEL);
	            break;
	        case 13:
	            smsEmployeeSchedule.setThirteen(SmsBaseConstant.Shift.IDEL);
	            break;
	        case 14:
	            smsEmployeeSchedule.setFourteen(SmsBaseConstant.Shift.IDEL);
	            break;
	        case 15:
	            smsEmployeeSchedule.setFifteen(SmsBaseConstant.Shift.IDEL);
	            break;
	        case 16:
	            smsEmployeeSchedule.setSixteen(SmsBaseConstant.Shift.IDEL);
	            break;
	        case 17:
	            smsEmployeeSchedule.setSeventeen(SmsBaseConstant.Shift.IDEL);
	            break;
	        case 18:
	            smsEmployeeSchedule.setEighteen(SmsBaseConstant.Shift.IDEL);
	            break;
	        case 19:
	            smsEmployeeSchedule.setNineteen(SmsBaseConstant.Shift.IDEL);
	            break;
	        case 20:
	            smsEmployeeSchedule.setTwenty(SmsBaseConstant.Shift.IDEL);
	            break;
	        case 21:
	            smsEmployeeSchedule.setTwentyOne(SmsBaseConstant.Shift.IDEL);
	            break;
	        case 22:
	            smsEmployeeSchedule.setTwentyTwo(SmsBaseConstant.Shift.IDEL);
	            break;
	        case 23:
	            smsEmployeeSchedule.setTwentyThree(SmsBaseConstant.Shift.IDEL);
	            break;
	        case 24:
	            break;
	          default:
	              break;
	        }
	    }
	}
	
	
	@Override
	public List<SmsEmployeeSchedule> selectNextFiveDays(String employee) {
	    SmsEmployeeScheduleExample example = new SmsEmployeeScheduleExample();
	    SmsEmployeeScheduleExample.Criteria criteria = example.createCriteria();
	    criteria.andEmployeeEqualTo(employee);
	    criteria.andStatusEqualTo(SmsBaseConstant.ScheduleStatus.WORK);
	    example.setOrderByClause(" day asc"); // 根据排班日期，升序排列
	    List<SmsEmployeeSchedule> smsEmployeeSchedules = selectByExample(example, new PageBounds(1, 5));
	    return smsEmployeeSchedules;
	}
	
	/**
     * 查询符合条件的记录数量
     * @dzmsoftgenerated 2016-05-15 09:42:23
     */
    @Override
	public int countByExample(SmsEmployeeScheduleExample example){
		return smsEmployeeScheduleMapper.countByExample(example);
	}

	/**
     * 根据条件插入记录
     * @dzmsoftgenerated 2016-05-15 09:42:23
     */
    @Transactional(readOnly = false)
    @Override
	public int insertSelective(SmsEmployeeSchedule record){
        record.setId(StringUtil.getUuidString());
        record.setCreateTime(new Date());
        ShiroUser user=UserUtil.getCurrentShiroUser();
        record.setCreator(user.getId());
		return smsEmployeeScheduleMapper.insertSelective(record);
	}
	
	/**
     * 根据主键查询记录
     * @dzmsoftgenerated 2016-05-15 09:42:23
     */
    @Override
	public SmsEmployeeSchedule selectByPrimaryKey(String id){
		return smsEmployeeScheduleMapper.selectByPrimaryKey(id);
	}
	
	/**
     * 分页查询记录
     * @dzmsoftgenerated 2016-05-15 09:42:23
     */
    @Override
	public PageList<SmsEmployeeSchedule> selectByExample(SmsEmployeeScheduleExample example,PageBounds pageBounds){
		return smsEmployeeScheduleMapper.selectByExample(example, pageBounds);
	}
	
	/**
     * 根据条件查询记录
     * @dzmsoftgenerated 2016-05-15 09:42:23
     */
    @Override
	public List<SmsEmployeeSchedule> selectByExample(SmsEmployeeScheduleExample example){
		return smsEmployeeScheduleMapper.selectByExample(example);
	}
	
	/**
     * 根据主键更新记录
     * @dzmsoftgenerated 2016-05-15 09:42:23
     */
    @Transactional(readOnly = false)
    @Override
	public int updateByPrimaryKeySelective(SmsEmployeeSchedule record){
		return smsEmployeeScheduleMapper.updateByPrimaryKeySelective(record);
	}
	
	/**
     * 根据查询条件更新记录
     * @dzmsoftgenerated 2016-05-15 09:42:23
     */
    @Transactional(readOnly = false)
    @Override
	public int updateByExampleSelective(SmsEmployeeSchedule record,
			SmsEmployeeScheduleExample example){
		return smsEmployeeScheduleMapper.updateByExampleSelective(record, example);
	}
	
	/**
     * 根据主键生成记录
     * @dzmsoftgenerated 2016-05-15 09:42:23
     */
    @Transactional(readOnly = false)
    @Override
	public int deleteByPrimaryKey(String id){
		return smsEmployeeScheduleMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 根据条件删除字段信息
	 * @dzmsoftgenerated 2016-05-15 09:42:23
	 * @param example
	 * @return
	 */
	@Transactional(readOnly = false)
	@Override
	public int deleteByExample(SmsEmployeeScheduleExample example){
		return smsEmployeeScheduleMapper.deleteByExample(example);
	}
}