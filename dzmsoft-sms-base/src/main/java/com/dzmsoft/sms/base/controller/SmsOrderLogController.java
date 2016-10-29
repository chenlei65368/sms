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
import com.dzmsoft.sms.base.pojo.SmsOrderLog;
import com.dzmsoft.sms.base.pojo.SmsOrderLogExample;
import com.dzmsoft.sms.base.service.SmsOrderLogService;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * Copyright (C), dzmsoft Co., Ltd
 * @author dzmsoft
 * @dzmsoftgenerated 2016-05-04 17:10:32
 *
 * @version 1.0
 */
@Controller
@RequestMapping("smsOrderLog")
public class SmsOrderLogController extends BaseController{
	@Autowired
	private SmsOrderLogService smsOrderLogService;

	/**
	 * 显示主列表页面
	 * @dzmsoftgenerated 2016-05-04 17:10:32
	 * @return
	 */
	@RequiresPermissions("smsOrderLog:find")
	@RequestMapping(value = "show", method = RequestMethod.GET)
	public String show(){
		return "modules/base/smsOrderLogList";
	}
	
	/**
	 * 主列表中查询
	 * @dzmsoftgenerated 2016-05-04 17:10:32
	 * @return
	 */
	@RequiresPermissions("smsOrderLog:find")
	@RequestMapping(value = "find", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> find(HttpServletRequest request){
		List<Condition> conditions = Condition.buildFromHttpRequest(request);
		EasyUIPage easyUIPage = new EasyUIPage(request);
		MybatisExample mybatisExample = MybatisExample.getInstance();
		SmsOrderLogExample example = (SmsOrderLogExample)mybatisExample.buildExampleByCondition(conditions, easyUIPage, SmsOrderLogExample.class.getName());
		PageList<SmsOrderLog> smsOrderLogs = smsOrderLogService.selectByExample(example, new PageBounds(easyUIPage.getPage(),
                easyUIPage.getRows()));
		return getEasyUIGrid(smsOrderLogs);
	}
	
	/**
	 * 跳转到新增页面
	 * @dzmsoftgenerated 2016-05-04 17:10:32
	 * @return
	 */
	@RequiresPermissions("smsOrderLog:add")
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String addForm(Model model){
		model.addAttribute("smsOrderLog", new SmsOrderLog());
		model.addAttribute("action", "add");
		return "modules/base/smsOrderLogForm";
	}
	
	/**
	 * 新增记录
	 * @dzmsoftgenerated 2016-05-04 17:10:32
	 * @return
	 */
	@RequiresPermissions("smsOrderLog:add")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse add(SmsOrderLog smsOrderLog){
		int flag = smsOrderLogService.insertSelective(smsOrderLog);
		BaseResponse baseResponse = flag>0?new BaseResponse(true,"新增成功"):new BaseResponse(false, "新增失败");
		return baseResponse;
	}
	
	/**
	 * 跳转到编辑页面
	 * @dzmsoftgenerated 2016-05-04 17:10:32
	 * @return
	 */
	@RequiresPermissions("smsOrderLog:edit")
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String editForm(@PathVariable("id") String id,Model model){
		SmsOrderLog smsOrderLog = smsOrderLogService.selectByPrimaryKey(id);
		model.addAttribute("smsOrderLog", smsOrderLog);
		model.addAttribute("action", "edit");
		return "modules/base/smsOrderLogForm";
	}
	
	/**
	 * 跳转到查看页面
	 * @dzmsoftgenerated 2016-05-04 17:10:32
	 * @return
	 */
	@RequiresPermissions("smsOrderLog:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String viewForm(@PathVariable("id") String id,Model model){
		SmsOrderLog smsOrderLog = smsOrderLogService.selectByPrimaryKey(id);
		model.addAttribute("smsOrderLog", smsOrderLog);
		model.addAttribute("action", "view");
		return "modules/base/smsOrderLogForm";
	}
	
	/**
	 * 编辑记录
	 * @dzmsoftgenerated 2016-05-04 17:10:32
	 * @return
	 */
	@RequiresPermissions("smsOrderLog:edit")
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse edit(SmsOrderLog smsOrderLog){
		int flag = smsOrderLogService.updateByPrimaryKeySelective(smsOrderLog);
		BaseResponse baseResponse = flag>0?new BaseResponse(true,"编辑成功"):new BaseResponse(false, "编辑失败");
		return baseResponse;
	}
	
	/**
	 * 删除记录
	 * @dzmsoftgenerated 2016-05-04 17:10:32
	 * @return
	 */
	@RequiresPermissions("smsOrderLog:remove")
	@RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse remove(@PathVariable("id") String id){
		int flag = smsOrderLogService.deleteByPrimaryKey(id);
		BaseResponse baseResponse = flag>=0?new BaseResponse(true,"删除成功"):new BaseResponse(false, "删除失败");
		return baseResponse;
	}
}
