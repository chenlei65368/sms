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
import com.dzmsoft.sms.base.pojo.SmsMemberCardConsume;
import com.dzmsoft.sms.base.pojo.SmsMemberCardConsumeExample;
import com.dzmsoft.sms.base.service.SmsMemberCardConsumeService;
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
@RequestMapping("smsMemberCardConsume")
public class SmsMemberCardConsumeController extends BaseController{
	@Autowired
	private SmsMemberCardConsumeService smsMemberCardConsumeService;

	/**
	 * 显示主列表页面
	 * @dzmsoftgenerated 2016-06-09 17:00:07
	 * @return
	 */
	@RequiresPermissions("smsMemberCardConsume:find")
	@RequestMapping(value = "show", method = RequestMethod.GET)
	public String show(){
		return "modules/base/smsMemberCardConsumeList";
	}
	
	/**
	 * 主列表中查询
	 * @dzmsoftgenerated 2016-06-09 17:00:07
	 * @return
	 */
	@RequiresPermissions("smsMemberCardConsume:find")
	@RequestMapping(value = "find", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> find(HttpServletRequest request){
		List<Condition> conditions = Condition.buildFromHttpRequest(request);
		EasyUIPage easyUIPage = new EasyUIPage(request);
		MybatisExample mybatisExample = MybatisExample.getInstance();
		SmsMemberCardConsumeExample example = (SmsMemberCardConsumeExample)mybatisExample.buildExampleByCondition(conditions, easyUIPage, SmsMemberCardConsumeExample.class.getName());
		PageList<SmsMemberCardConsume> smsMemberCardConsumes = smsMemberCardConsumeService.selectByExample(example, new PageBounds(easyUIPage.getPage(),
                easyUIPage.getRows()));
		return getEasyUIGrid(smsMemberCardConsumes);
	}
	
	/**
	 * 跳转到新增页面
	 * @dzmsoftgenerated 2016-06-09 17:00:07
	 * @return
	 */
	@RequiresPermissions("smsMemberCardConsume:add")
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String addForm(Model model){
		model.addAttribute("smsMemberCardConsume", new SmsMemberCardConsume());
		model.addAttribute("action", "add");
		return "modules/base/smsMemberCardConsumeForm";
	}
	
	/**
	 * 新增记录
	 * @dzmsoftgenerated 2016-06-09 17:00:07
	 * @return
	 */
	@RequiresPermissions("smsMemberCardConsume:add")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse add(SmsMemberCardConsume smsMemberCardConsume){
		int flag = smsMemberCardConsumeService.insertSelective(smsMemberCardConsume);
		BaseResponse baseResponse = flag>0?new BaseResponse(true,"新增成功"):new BaseResponse(false, "新增失败");
		return baseResponse;
	}
	
	/**
	 * 跳转到编辑页面
	 * @dzmsoftgenerated 2016-06-09 17:00:07
	 * @return
	 */
	@RequiresPermissions("smsMemberCardConsume:edit")
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String editForm(@PathVariable("id") String id,Model model){
		SmsMemberCardConsume smsMemberCardConsume = smsMemberCardConsumeService.selectByPrimaryKey(id);
		model.addAttribute("smsMemberCardConsume", smsMemberCardConsume);
		model.addAttribute("action", "edit");
		return "modules/base/smsMemberCardConsumeForm";
	}
	
	/**
	 * 跳转到查看页面
	 * @dzmsoftgenerated 2016-06-09 17:00:07
	 * @return
	 */
	@RequiresPermissions("smsMemberCardConsume:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String viewForm(@PathVariable("id") String id,Model model){
		SmsMemberCardConsume smsMemberCardConsume = smsMemberCardConsumeService.selectByPrimaryKey(id);
		model.addAttribute("smsMemberCardConsume", smsMemberCardConsume);
		model.addAttribute("action", "view");
		return "modules/base/smsMemberCardConsumeForm";
	}
	
	/**
	 * 编辑记录
	 * @dzmsoftgenerated 2016-06-09 17:00:07
	 * @return
	 */
	@RequiresPermissions("smsMemberCardConsume:edit")
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse edit(SmsMemberCardConsume smsMemberCardConsume){
		int flag = smsMemberCardConsumeService.updateByPrimaryKeySelective(smsMemberCardConsume);
		BaseResponse baseResponse = flag>0?new BaseResponse(true,"编辑成功"):new BaseResponse(false, "编辑失败");
		return baseResponse;
	}
	
	/**
	 * 删除记录
	 * @dzmsoftgenerated 2016-06-09 17:00:07
	 * @return
	 */
	@RequiresPermissions("smsMemberCardConsume:remove")
	@RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse remove(@PathVariable("id") String id){
		int flag = smsMemberCardConsumeService.deleteByPrimaryKey(id);
		BaseResponse baseResponse = flag>=0?new BaseResponse(true,"删除成功"):new BaseResponse(false, "删除失败");
		return baseResponse;
	}
}
