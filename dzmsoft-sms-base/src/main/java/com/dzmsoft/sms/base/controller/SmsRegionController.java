package com.dzmsoft.sms.base.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dzmsoft.framework.base.web.mvc.controller.BaseController;
import com.dzmsoft.framework.base.web.mvc.dao.MybatisExample;
import com.dzmsoft.framework.base.web.mvc.view.BaseResponse;
import com.dzmsoft.framework.base.web.mvc.view.Condition;
import com.dzmsoft.sms.base.pojo.SmsRegion;
import com.dzmsoft.sms.base.pojo.SmsRegionExample;
import com.dzmsoft.sms.base.service.SmsRegionService;

/**
 * Copyright (C), dzmsoft Co., Ltd
 * @author dzmsoft
 * @dzmsoftgenerated 2016-06-09 16:56:23
 *
 * @version 1.0
 */
@Controller
@RequestMapping("smsRegion")
public class SmsRegionController extends BaseController{
	@Autowired
	private SmsRegionService smsRegionService;

	/**
	 * 显示主列表页面
	 * @dzmsoftgenerated 2016-06-09 16:56:23
	 * @return
	 */
	@RequiresPermissions("smsRegion:find")
	@RequestMapping(value = "show", method = RequestMethod.GET)
	public String show(){
		return "modules/base/smsRegionList";
	}
	
	/**
	 * 主列表中查询
	 * @dzmsoftgenerated 2016-06-09 16:56:23
	 * @return
	 */
	@RequiresPermissions("smsRegion:find")
	@RequestMapping(value = "find")
	@ResponseBody
	public List<SmsRegion> findList(HttpServletRequest request){
		List<Condition> conditions = Condition.buildFromHttpRequest(request);
		MybatisExample mybatisExample = MybatisExample.getInstance();
		SmsRegionExample example = (SmsRegionExample)mybatisExample.buildExampleByCondition(conditions, SmsRegionExample.class.getName());
		List<SmsRegion> smsRegions = smsRegionService.selectByExample(example);
		return smsRegions;
	}
	
	/**
	 * 跳转到新增页面
	 * @dzmsoftgenerated 2016-06-09 16:56:23
	 * @return
	 */
	@RequiresPermissions("smsRegion:add")
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String addForm(Model model){
		model.addAttribute("smsRegion", new SmsRegion());
		model.addAttribute("action", "add");
		return "modules/base/smsRegionForm";
	}
	
	/**
	 * 新增记录
	 * @dzmsoftgenerated 2016-06-09 16:56:23
	 * @return
	 */
	@RequiresPermissions("smsRegion:add")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse add(SmsRegion smsRegion){
		int flag = smsRegionService.insertSelective(smsRegion);
		BaseResponse baseResponse = flag>0?new BaseResponse(true,"新增成功"):new BaseResponse(false, "新增失败");
		return baseResponse;
	}
	
	/**
	 * 跳转到编辑页面
	 * @dzmsoftgenerated 2016-06-09 16:56:23
	 * @return
	 */
	@RequiresPermissions("smsRegion:edit")
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String editForm(@PathVariable("id") String id,Model model){
		SmsRegion smsRegion = smsRegionService.selectByPrimaryKey(id);
		model.addAttribute("smsRegion", smsRegion);
		model.addAttribute("action", "edit");
		return "modules/base/smsRegionForm";
	}
	
	/**
	 * 编辑记录
	 * @dzmsoftgenerated 2016-06-09 16:56:23
	 * @return
	 */
	@RequiresPermissions("smsRegion:edit")
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse edit(SmsRegion smsRegion){
		int flag = smsRegionService.updateByPrimaryKeySelective(smsRegion);
		BaseResponse baseResponse = flag>0?new BaseResponse(true,"编辑成功"):new BaseResponse(false, "编辑失败");
		return baseResponse;
	}
	
	/**
	 * 删除记录
	 * @dzmsoftgenerated 2016-06-09 16:56:23
	 * @return
	 */
	@RequiresPermissions("smsRegion:remove")
	@RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse remove(@PathVariable("id") String id){
		int flag = smsRegionService.deleteByPrimaryKey(id);
		BaseResponse baseResponse = flag>=0?new BaseResponse(true,"删除成功"):new BaseResponse(false, "删除失败");
		return baseResponse;
	}
}
