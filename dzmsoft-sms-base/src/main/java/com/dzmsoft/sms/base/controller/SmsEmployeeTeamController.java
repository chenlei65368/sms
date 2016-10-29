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
import com.dzmsoft.sms.base.pojo.SmsEmployeeTeam;
import com.dzmsoft.sms.base.pojo.SmsEmployeeTeamExample;
import com.dzmsoft.sms.base.service.SmsEmployeeTeamService;

/**
 * Copyright (C), dzmsoft Co., Ltd
 * @author 
 * @dzmsoftgenerated 
 *
 * @version 
 */
@Controller
@RequestMapping("smsEmployeeTeam")
public class SmsEmployeeTeamController extends BaseController{
	@Autowired
	private SmsEmployeeTeamService smsEmployeeTeamService;

	/**
	 * 显示主列表页面
	 * @dzmsoftgenerated 
	 * @return
	 */
	@RequiresPermissions("smsEmployeeTeam:find")
	@RequestMapping(value = "show", method = RequestMethod.GET)
	public String show(){
		return "modules/base/smsEmployeeTeamList";
	}
	
	/**
	 * 主列表中查询
	 * @dzmsoftgenerated 
	 * @return
	 */
	@RequiresPermissions("smsEmployeeTeam:find")
	@RequestMapping(value = "find")
	@ResponseBody
	public List<SmsEmployeeTeam> findList(HttpServletRequest request){
		List<Condition> conditions = Condition.buildFromHttpRequest(request);
		MybatisExample mybatisExample = MybatisExample.getInstance();
		SmsEmployeeTeamExample example = (SmsEmployeeTeamExample)mybatisExample.buildExampleByCondition(conditions, SmsEmployeeTeamExample.class.getName());
		List<SmsEmployeeTeam> smsEmployeeTeams = smsEmployeeTeamService.selectByExample(example);
		return smsEmployeeTeams;
	}
	
	/**
	 * 跳转到新增页面
	 * @dzmsoftgenerated 
	 * @return
	 */
	@RequiresPermissions("smsEmployeeTeam:add")
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String addForm(Model model){
		model.addAttribute("smsEmployeeTeam", new SmsEmployeeTeam());
		model.addAttribute("action", "add");
		return "modules/base/smsEmployeeTeamForm";
	}
	
	/**
	 * 新增记录
	 * @dzmsoftgenerated 
	 * @return
	 */
	@RequiresPermissions("smsEmployeeTeam:add")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse add(SmsEmployeeTeam smsEmployeeTeam){
		int flag = smsEmployeeTeamService.insertSelective(smsEmployeeTeam);
		BaseResponse baseResponse = flag>0?new BaseResponse(true,"新增成功"):new BaseResponse(false, "新增失败");
		return baseResponse;
	}
	
	/**
	 * 跳转到编辑页面
	 * @dzmsoftgenerated 
	 * @return
	 */
	@RequiresPermissions("smsEmployeeTeam:edit")
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String editForm(@PathVariable("id") String id,Model model){
		SmsEmployeeTeam smsEmployeeTeam = smsEmployeeTeamService.selectByPrimaryKey(id);
		model.addAttribute("smsEmployeeTeam", smsEmployeeTeam);
		model.addAttribute("action", "edit");
		return "modules/base/smsEmployeeTeamForm";
	}
	
	/**
	 * 编辑记录
	 * @dzmsoftgenerated 
	 * @return
	 */
	@RequiresPermissions("smsEmployeeTeam:edit")
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse edit(SmsEmployeeTeam smsEmployeeTeam){
		int flag = smsEmployeeTeamService.updateByPrimaryKeySelective(smsEmployeeTeam);
		BaseResponse baseResponse = flag>0?new BaseResponse(true,"编辑成功"):new BaseResponse(false, "编辑失败");
		return baseResponse;
	}
	
	/**
	 * 删除记录
	 * @dzmsoftgenerated 
	 * @return
	 */
	@RequiresPermissions("smsEmployeeTeam:remove")
	@RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse remove(@PathVariable("id") String id){
		int flag = smsEmployeeTeamService.deleteByPrimaryKey(id);
		BaseResponse baseResponse = flag>=0?new BaseResponse(true,"删除成功"):new BaseResponse(false, "删除失败");
		return baseResponse;
	}
	
		@RequiresPermissions("smsEmployeeTeam:enable")
	@RequestMapping(value = "enable/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public BaseResponse enable(@PathVariable("id") String id){
		int flag = smsEmployeeTeamService.enable(id);
		BaseResponse baseResponse = flag>=0?new BaseResponse(true,"启用成功"):new BaseResponse(false, "启用失败");
		return baseResponse;
	}
	
	@RequiresPermissions("smsEmployeeTeam:disable")
	@RequestMapping(value = "disable/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public BaseResponse disable(@PathVariable("id") String id){
		int flag = smsEmployeeTeamService.disable(id);
		BaseResponse baseResponse = flag>=0?new BaseResponse(true,"禁用成功"):new BaseResponse(false, "禁用失败");
		return baseResponse;
	}
	}
