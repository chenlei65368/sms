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

import com.dzmsoft.framework.base.util.CheckEmptyUtil;
import com.dzmsoft.framework.base.util.StringUtil;
import com.dzmsoft.framework.base.web.mvc.controller.BaseController;
import com.dzmsoft.framework.base.web.mvc.dao.MybatisExample;
import com.dzmsoft.framework.base.web.mvc.pojo.EasyUIPage;
import com.dzmsoft.framework.base.web.mvc.view.BaseResponse;
import com.dzmsoft.framework.base.web.mvc.view.Condition;
import com.dzmsoft.sms.base.pojo.SmsMember;
import com.dzmsoft.sms.base.pojo.SmsMemberAddress;
import com.dzmsoft.sms.base.pojo.SmsMemberExample;
import com.dzmsoft.sms.base.service.SmsMemberAddressService;
import com.dzmsoft.sms.base.service.SmsMemberService;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


/**
 * Copyright (C), dzmsoft Co., Ltd
 * @author 
 * @dzmsoftgenerated 
 *
 * @version 
 */
@Controller
@RequestMapping("smsMember")
public class SmsMemberController extends BaseController{
	@Autowired
	private SmsMemberService smsMemberService;
		@Autowired
		private SmsMemberAddressService smsMemberAddressService;
	
	/**
	 * 显示主列表页面
	 * @dzmsoftgenerated 
	 * @return
	 */
	@RequiresPermissions("smsMember:find")
	@RequestMapping(value = "show", method = RequestMethod.GET)
	public String show(){
		return "modules/base/smsMemberList";
	}
	
	/**
	 * 主列表中查询
	 * @dzmsoftgenerated 
	 * @return
	 */
	@RequiresPermissions("smsMember:find")
	@RequestMapping(value = "find", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> find(HttpServletRequest request){
		List<Condition> conditions = Condition.buildFromHttpRequest(request);
		EasyUIPage easyUIPage = new EasyUIPage(request);
		MybatisExample mybatisExample = MybatisExample.getInstance();
		SmsMemberExample example = (SmsMemberExample)mybatisExample.buildExampleByCondition(conditions, easyUIPage, SmsMemberExample.class.getName());
		PageList<SmsMember> smsMembers = smsMemberService.selectByExample(example, new PageBounds(easyUIPage.getPage(),
                easyUIPage.getRows()));
		return getEasyUIGrid(smsMembers);
	}
	
	/**
	 * 跳转到新增页面
	 * @dzmsoftgenerated 
	 * @return
	 */
	@RequiresPermissions("smsMember:add")
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String addForm(Model model){
		model.addAttribute("smsMember", new SmsMember());
		model.addAttribute("action", "add");
		return "modules/base/smsMemberForm";
	}
	
	/**
	 * 新增记录
	 * @dzmsoftgenerated 
	 * @return
	 */
	@RequiresPermissions("smsMember:add")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse add(SmsMember smsMember
			,String datasSmsMemberAddress
		){
		Gson gson = new Gson();
				datasSmsMemberAddress = StringUtil.unescapeHtml(datasSmsMemberAddress);
		List<SmsMemberAddress> itemsSmsMemberAddress =  gson.fromJson(datasSmsMemberAddress, new TypeToken<List<SmsMemberAddress>>(){}.getType());
				int flag = smsMemberService.insertContainDetails(smsMember
							,itemsSmsMemberAddress
					);
		BaseResponse baseResponse = flag>0?new BaseResponse(true,"新增成功"):new BaseResponse(false, "新增失败");
		return baseResponse;
	}
	
	/**
	 * 跳转到编辑页面
	 * @dzmsoftgenerated 
	 * @return
	 */
	@RequiresPermissions("smsMember:edit")
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String editForm(@PathVariable("id") String id,Model model){
		SmsMember smsMember = smsMemberService.selectByPrimaryKey(id);
		model.addAttribute("smsMember", smsMember);
		model.addAttribute("action", "edit");
				List<SmsMemberAddress> smsMemberAddress = null;
		if (!CheckEmptyUtil.isEmpty(smsMemberAddress)){
		    Gson gson = new Gson();
		    String datasSmsMemberAddress = gson.toJson(smsMemberAddress);
		    model.addAttribute("datasSmsMemberAddress", StringUtil.escapeHtml(datasSmsMemberAddress));
		} 
				return "modules/base/smsMemberForm";
	}
	
	/**
	 * 跳转到查看页面
	 * @dzmsoftgenerated 
	 * @return
	 */
	@RequiresPermissions("smsMember:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String viewForm(@PathVariable("id") String id,Model model){
		SmsMember smsMember = smsMemberService.selectByPrimaryKey(id);
		model.addAttribute("smsMember", smsMember);
		model.addAttribute("action", "view");
				List<SmsMemberAddress> smsMemberAddress = null;
		if (!CheckEmptyUtil.isEmpty(smsMemberAddress)){
		    Gson gson = new Gson();
		    String datasSmsMemberAddress = gson.toJson(smsMemberAddress);
		    model.addAttribute("datasSmsMemberAddress", StringUtil.escapeHtml(datasSmsMemberAddress));
		} 
				return "modules/base/smsMemberForm";
	}
	
	/**
	 * 编辑记录
	 * @dzmsoftgenerated 
	 * @return
	 */
	@RequiresPermissions("smsMember:edit")
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse edit(SmsMember smsMember
			,String datasSmsMemberAddress
		){
		Gson gson = new Gson();
				datasSmsMemberAddress = StringUtil.unescapeHtml(datasSmsMemberAddress);
		List<SmsMemberAddress> itemsSmsMemberAddress =  gson.fromJson(datasSmsMemberAddress, new TypeToken<List<SmsMemberAddress>>(){}.getType());
				int flag = smsMemberService.updateContainDetails(smsMember
							,itemsSmsMemberAddress
					);
		BaseResponse baseResponse = flag>0?new BaseResponse(true,"编辑成功"):new BaseResponse(false, "编辑失败");
		return baseResponse;
	}
	
	/**
	 * 删除记录
	 * @dzmsoftgenerated 
	 * @return
	 */
	@RequiresPermissions("smsMember:remove")
	@RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse remove(@PathVariable("id") String id){
		int flag = smsMemberService.deleteByPrimaryKey(id);
		BaseResponse baseResponse = flag>=0?new BaseResponse(true,"删除成功"):new BaseResponse(false, "删除失败");
		return baseResponse;
	}
	
	}
