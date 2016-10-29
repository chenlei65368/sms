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
import com.dzmsoft.sms.base.pojo.SmsCleanBagLine;
import com.dzmsoft.sms.base.pojo.SmsCleanBagLineExample;
import com.dzmsoft.sms.base.service.SmsCleanBagLineService;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * Copyright (C), dzmsoft Co., Ltd
 * @author dzmsoft
 * @dzmsoftgenerated 2016-06-13 13:10:38
 *
 * @version 1.0
 */
@Controller
@RequestMapping("smsCleanBagLine")
public class SmsCleanBagLineController extends BaseController{
	@Autowired
	private SmsCleanBagLineService smsCleanBagLineService;

	/**
	 * 显示主列表页面
	 * @dzmsoftgenerated 2016-06-13 13:10:38
	 * @return
	 */
	@RequiresPermissions("smsCleanBagLine:find")
	@RequestMapping(value = "show", method = RequestMethod.GET)
	public String show(){
		return "modules/base/smsCleanBagLineList";
	}
	
	/**
	 * 主列表中查询
	 * @dzmsoftgenerated 2016-06-13 13:10:38
	 * @return
	 */
	@RequiresPermissions("smsCleanBagLine:find")
	@RequestMapping(value = "find", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> find(HttpServletRequest request){
		List<Condition> conditions = Condition.buildFromHttpRequest(request);
		EasyUIPage easyUIPage = new EasyUIPage(request);
		MybatisExample mybatisExample = MybatisExample.getInstance();
		SmsCleanBagLineExample example = (SmsCleanBagLineExample)mybatisExample.buildExampleByCondition(conditions, easyUIPage, SmsCleanBagLineExample.class.getName());
		PageList<SmsCleanBagLine> smsCleanBagLines = smsCleanBagLineService.selectByExample(example, new PageBounds(easyUIPage.getPage(),
                easyUIPage.getRows()));
		return getEasyUIGrid(smsCleanBagLines);
	}
	
	/**
	 * 跳转到新增页面
	 * @dzmsoftgenerated 2016-06-13 13:10:38
	 * @return
	 */
	@RequiresPermissions("smsCleanBagLine:add")
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String addForm(Model model){
		model.addAttribute("smsCleanBagLine", new SmsCleanBagLine());
		model.addAttribute("action", "add");
		return "modules/base/smsCleanBagLineForm";
	}
	
	/**
	 * 新增记录
	 * @dzmsoftgenerated 2016-06-13 13:10:38
	 * @return
	 */
	@RequiresPermissions("smsCleanBagLine:add")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse add(SmsCleanBagLine smsCleanBagLine){
		int flag = smsCleanBagLineService.insertSelective(smsCleanBagLine);
		BaseResponse baseResponse = flag>0?new BaseResponse(true,"新增成功"):new BaseResponse(false, "新增失败");
		return baseResponse;
	}
	
	/**
	 * 跳转到编辑页面
	 * @dzmsoftgenerated 2016-06-13 13:10:38
	 * @return
	 */
	@RequiresPermissions("smsCleanBagLine:edit")
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String editForm(@PathVariable("id") String id,Model model){
		SmsCleanBagLine smsCleanBagLine = smsCleanBagLineService.selectByPrimaryKey(id);
		model.addAttribute("smsCleanBagLine", smsCleanBagLine);
		model.addAttribute("action", "edit");
		return "modules/base/smsCleanBagLineForm";
	}
	
	/**
	 * 跳转到查看页面
	 * @dzmsoftgenerated 2016-06-13 13:10:38
	 * @return
	 */
	@RequiresPermissions("smsCleanBagLine:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String viewForm(@PathVariable("id") String id,Model model){
		SmsCleanBagLine smsCleanBagLine = smsCleanBagLineService.selectByPrimaryKey(id);
		model.addAttribute("smsCleanBagLine", smsCleanBagLine);
		model.addAttribute("action", "view");
		return "modules/base/smsCleanBagLineForm";
	}
	
	/**
	 * 编辑记录
	 * @dzmsoftgenerated 2016-06-13 13:10:38
	 * @return
	 */
	@RequiresPermissions("smsCleanBagLine:edit")
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse edit(SmsCleanBagLine smsCleanBagLine){
		int flag = smsCleanBagLineService.updateByPrimaryKeySelective(smsCleanBagLine);
		BaseResponse baseResponse = flag>0?new BaseResponse(true,"编辑成功"):new BaseResponse(false, "编辑失败");
		return baseResponse;
	}
	
	/**
	 * 删除记录
	 * @dzmsoftgenerated 2016-06-13 13:10:38
	 * @return
	 */
	@RequiresPermissions("smsCleanBagLine:remove")
	@RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse remove(@PathVariable("id") String id){
		int flag = smsCleanBagLineService.deleteByPrimaryKey(id);
		BaseResponse baseResponse = flag>=0?new BaseResponse(true,"删除成功"):new BaseResponse(false, "删除失败");
		return baseResponse;
	}
}
