package com.dzmsoft.sms.base.controller;

import java.util.List;
import java.util.Map;

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
import com.dzmsoft.framework.base.web.mvc.pojo.EasyUIPage;
import com.dzmsoft.sms.base.pojo.SmsHouseholdRequire;
import com.dzmsoft.sms.base.pojo.SmsHouseholdRequireExample;
import com.dzmsoft.sms.base.service.SmsHouseholdRequireService;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * Copyright (C), dzmsoft Co., Ltd
 * @author dzmsoft
 * @dzmsoftgenerated 2016-06-09 17:00:07
 *
 * @version 1.0
 */
@Controller
@RequestMapping("smsHouseholdRequire")
public class SmsHouseholdRequireController extends BaseController{
	@Autowired
	private SmsHouseholdRequireService smsHouseholdRequireService;

	/**
	 * 显示主列表页面
	 * @dzmsoftgenerated 2016-06-09 17:00:07
	 * @return
	 */
	@RequiresPermissions("smsHouseholdRequire:find")
	@RequestMapping(value = "show", method = RequestMethod.GET)
	public String show(){
		return "modules/base/smsHouseholdRequireList";
	}
	
	/**
	 * 主列表中查询
	 * @dzmsoftgenerated 2016-06-09 17:00:07
	 * @return
	 */
	@RequiresPermissions("smsHouseholdRequire:find")
	@RequestMapping(value = "find", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> find(HttpServletRequest request){
		List<Condition> conditions = Condition.buildFromHttpRequest(request);
		EasyUIPage easyUIPage = new EasyUIPage(request);
		MybatisExample mybatisExample = MybatisExample.getInstance();
		SmsHouseholdRequireExample example = (SmsHouseholdRequireExample)mybatisExample.buildExampleByCondition(conditions, easyUIPage, SmsHouseholdRequireExample.class.getName());
		PageList<SmsHouseholdRequire> smsHouseholdRequires = smsHouseholdRequireService.selectByExample(example, new PageBounds(easyUIPage.getPage(),
                easyUIPage.getRows()));
		return getEasyUIGrid(smsHouseholdRequires);
	}
	
	/**
	 * 跳转到新增页面
	 * @dzmsoftgenerated 2016-06-09 17:00:07
	 * @return
	 */
	@RequiresPermissions("smsHouseholdRequire:add")
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String addForm(Model model){
		model.addAttribute("smsHouseholdRequire", new SmsHouseholdRequire());
		model.addAttribute("action", "add");
		return "modules/base/smsHouseholdRequireForm";
	}
	
	/**
	 * 新增记录
	 * @dzmsoftgenerated 2016-06-09 17:00:07
	 * @return
	 */
	@RequiresPermissions("smsHouseholdRequire:add")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse add(SmsHouseholdRequire smsHouseholdRequire){
		int flag = smsHouseholdRequireService.insertSelective(smsHouseholdRequire);
		BaseResponse baseResponse = flag>0?new BaseResponse(true,"新增成功"):new BaseResponse(false, "新增失败");
		return baseResponse;
	}
	
	/**
	 * 跳转到编辑页面
	 * @dzmsoftgenerated 2016-06-09 17:00:07
	 * @return
	 */
	@RequiresPermissions("smsHouseholdRequire:edit")
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String editForm(@PathVariable("id") String id,Model model){
		SmsHouseholdRequire smsHouseholdRequire = smsHouseholdRequireService.selectByPrimaryKey(id);
		model.addAttribute("smsHouseholdRequire", smsHouseholdRequire);
		model.addAttribute("action", "edit");
		return "modules/base/smsHouseholdRequireForm";
	}
	
	/**
	 * 跳转到查看页面
	 * @dzmsoftgenerated 2016-06-09 17:00:07
	 * @return
	 */
	@RequiresPermissions("smsHouseholdRequire:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String viewForm(@PathVariable("id") String id,Model model){
		SmsHouseholdRequire smsHouseholdRequire = smsHouseholdRequireService.selectByPrimaryKey(id);
		model.addAttribute("smsHouseholdRequire", smsHouseholdRequire);
		model.addAttribute("action", "view");
		return "modules/base/smsHouseholdRequireForm";
	}
	
	/**
	 * 编辑记录
	 * @dzmsoftgenerated 2016-06-09 17:00:07
	 * @return
	 */
	@RequiresPermissions("smsHouseholdRequire:edit")
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse edit(SmsHouseholdRequire smsHouseholdRequire){
		int flag = smsHouseholdRequireService.updateByPrimaryKeySelective(smsHouseholdRequire);
		BaseResponse baseResponse = flag>0?new BaseResponse(true,"编辑成功"):new BaseResponse(false, "编辑失败");
		return baseResponse;
	}
	
	/**
	 * 删除记录
	 * @dzmsoftgenerated 2016-06-09 17:00:07
	 * @return
	 */
	@RequiresPermissions("smsHouseholdRequire:remove")
	@RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse remove(@PathVariable("id") String id){
		int flag = smsHouseholdRequireService.deleteByPrimaryKey(id);
		BaseResponse baseResponse = flag>=0?new BaseResponse(true,"删除成功"):new BaseResponse(false, "删除失败");
		return baseResponse;
	}
}
