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
import com.dzmsoft.sms.base.pojo.SmsPersonRequireLine;
import com.dzmsoft.sms.base.pojo.SmsPersonRequireLineExample;
import com.dzmsoft.sms.base.service.SmsPersonRequireLineService;
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
@RequestMapping("smsPersonRequireLine")
public class SmsPersonRequireLineController extends BaseController{
	@Autowired
	private SmsPersonRequireLineService smsPersonRequireLineService;

	/**
	 * 显示主列表页面
	 * @dzmsoftgenerated 2016-06-09 17:00:07
	 * @return
	 */
	@RequiresPermissions("smsPersonRequireLine:find")
	@RequestMapping(value = "show", method = RequestMethod.GET)
	public String show(){
		return "modules/base/smsPersonRequireLineList";
	}
	
	/**
	 * 主列表中查询
	 * @dzmsoftgenerated 2016-06-09 17:00:07
	 * @return
	 */
	@RequiresPermissions("smsPersonRequireLine:find")
	@RequestMapping(value = "find", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> find(HttpServletRequest request){
		List<Condition> conditions = Condition.buildFromHttpRequest(request);
		EasyUIPage easyUIPage = new EasyUIPage(request);
		MybatisExample mybatisExample = MybatisExample.getInstance();
		SmsPersonRequireLineExample example = (SmsPersonRequireLineExample)mybatisExample.buildExampleByCondition(conditions, easyUIPage, SmsPersonRequireLineExample.class.getName());
		PageList<SmsPersonRequireLine> smsPersonRequireLines = smsPersonRequireLineService.selectByExample(example, new PageBounds(easyUIPage.getPage(),
                easyUIPage.getRows()));
		return getEasyUIGrid(smsPersonRequireLines);
	}
	
	/**
	 * 跳转到新增页面
	 * @dzmsoftgenerated 2016-06-09 17:00:07
	 * @return
	 */
	@RequiresPermissions("smsPersonRequireLine:add")
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String addForm(Model model){
		model.addAttribute("smsPersonRequireLine", new SmsPersonRequireLine());
		model.addAttribute("action", "add");
		return "modules/base/smsPersonRequireLineForm";
	}
	
	/**
	 * 新增记录
	 * @dzmsoftgenerated 2016-06-09 17:00:07
	 * @return
	 */
	@RequiresPermissions("smsPersonRequireLine:add")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse add(SmsPersonRequireLine smsPersonRequireLine){
		int flag = smsPersonRequireLineService.insertSelective(smsPersonRequireLine);
		BaseResponse baseResponse = flag>0?new BaseResponse(true,"新增成功"):new BaseResponse(false, "新增失败");
		return baseResponse;
	}
	
	/**
	 * 跳转到编辑页面
	 * @dzmsoftgenerated 2016-06-09 17:00:07
	 * @return
	 */
	@RequiresPermissions("smsPersonRequireLine:edit")
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String editForm(@PathVariable("id") String id,Model model){
		SmsPersonRequireLine smsPersonRequireLine = smsPersonRequireLineService.selectByPrimaryKey(id);
		model.addAttribute("smsPersonRequireLine", smsPersonRequireLine);
		model.addAttribute("action", "edit");
		return "modules/base/smsPersonRequireLineForm";
	}
	
	/**
	 * 跳转到查看页面
	 * @dzmsoftgenerated 2016-06-09 17:00:07
	 * @return
	 */
	@RequiresPermissions("smsPersonRequireLine:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String viewForm(@PathVariable("id") String id,Model model){
		SmsPersonRequireLine smsPersonRequireLine = smsPersonRequireLineService.selectByPrimaryKey(id);
		model.addAttribute("smsPersonRequireLine", smsPersonRequireLine);
		model.addAttribute("action", "view");
		return "modules/base/smsPersonRequireLineForm";
	}
	
	/**
	 * 编辑记录
	 * @dzmsoftgenerated 2016-06-09 17:00:07
	 * @return
	 */
	@RequiresPermissions("smsPersonRequireLine:edit")
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse edit(SmsPersonRequireLine smsPersonRequireLine){
		int flag = smsPersonRequireLineService.updateByPrimaryKeySelective(smsPersonRequireLine);
		BaseResponse baseResponse = flag>0?new BaseResponse(true,"编辑成功"):new BaseResponse(false, "编辑失败");
		return baseResponse;
	}
	
	/**
	 * 删除记录
	 * @dzmsoftgenerated 2016-06-09 17:00:07
	 * @return
	 */
	@RequiresPermissions("smsPersonRequireLine:remove")
	@RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse remove(@PathVariable("id") String id){
		int flag = smsPersonRequireLineService.deleteByPrimaryKey(id);
		BaseResponse baseResponse = flag>=0?new BaseResponse(true,"删除成功"):new BaseResponse(false, "删除失败");
		return baseResponse;
	}
}
