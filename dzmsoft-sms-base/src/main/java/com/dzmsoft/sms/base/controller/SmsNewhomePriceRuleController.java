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

import com.dzmsoft.framework.base.util.StringUtil;
import com.dzmsoft.framework.base.web.mvc.controller.BaseController;
import com.dzmsoft.framework.base.web.mvc.dao.MybatisExample;
import com.dzmsoft.framework.base.web.mvc.pojo.EasyUIPage;
import com.dzmsoft.framework.base.web.mvc.pojo.ShiroUser;
import com.dzmsoft.framework.base.web.mvc.shiro.UserUtil;
import com.dzmsoft.framework.base.web.mvc.view.BaseResponse;
import com.dzmsoft.framework.base.web.mvc.view.Condition;
import com.dzmsoft.framework.base.web.mvc.view.GenericResponse;
import com.dzmsoft.sms.base.pojo.SmsNewhomePriceRule;
import com.dzmsoft.sms.base.pojo.SmsNewhomePriceRuleExample;
import com.dzmsoft.sms.base.service.SmsNewhomePriceRuleService;
import com.dzmsoft.sms.base.service.SmsSupplierSerivice;
import com.dzmsoft.sms.oim.pojo.OimSupplier;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * Copyright (C), dzmsoft Co., Ltd
 * @author 
 * @dzmsoftgenerated 
 *
 * @version 
 */
@Controller
@RequestMapping("smsNewhomePriceRule")
public class SmsNewhomePriceRuleController extends BaseController{
	@Autowired
	private SmsNewhomePriceRuleService smsNewhomePriceRuleService;
	@Autowired
	private SmsSupplierSerivice smsSupplierSerivice;

	/**
	 * 显示主列表页面
	 * @dzmsoftgenerated 
	 * @return
	 */
	@RequiresPermissions("smsNewhomePriceRule:find")
	@RequestMapping(value = "show", method = RequestMethod.GET)
	public String show(){
		return "modules/base/smsNewhomePriceRuleList";
	}
	
	/**
	 * 主列表中查询
	 * @dzmsoftgenerated 
	 * @return
	 */
	@RequiresPermissions("smsNewhomePriceRule:find")
	@RequestMapping(value = "find", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> find(HttpServletRequest request){
		List<Condition> conditions = Condition.buildFromHttpRequest(request);
		ShiroUser user=UserUtil.getCurrentShiroUser();
        OimSupplier oimSupplier = smsSupplierSerivice.selectByUcsId(user.getId());
        conditions.add(new Condition("eqs_supplier",oimSupplier!=null?oimSupplier.getId():"-1"));
		EasyUIPage easyUIPage = new EasyUIPage(request);
		MybatisExample mybatisExample = MybatisExample.getInstance();
		SmsNewhomePriceRuleExample example = (SmsNewhomePriceRuleExample)mybatisExample.buildExampleByCondition(conditions, easyUIPage, SmsNewhomePriceRuleExample.class.getName());
		PageList<SmsNewhomePriceRule> smsNewhomePriceRules = smsNewhomePriceRuleService.selectByExample(example, new PageBounds(easyUIPage.getPage(),
                easyUIPage.getRows()));
		return getEasyUIGrid(smsNewhomePriceRules);
	}
	
	/**
	 * 跳转到新增页面
	 * @dzmsoftgenerated 
	 * @return
	 */
	@RequiresPermissions("smsNewhomePriceRule:add")
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String addForm(Model model){
		SmsNewhomePriceRule smsNewhomePriceRule = new SmsNewhomePriceRule();
				smsNewhomePriceRule.setId(StringUtil.getUuidString());
				model.addAttribute("smsNewhomePriceRule", smsNewhomePriceRule);
		model.addAttribute("action", "add");
		return "modules/base/smsNewhomePriceRuleForm";
	}
	
		/**
	 * 新增记录
	 * @dzmsoftgenerated 
	 * @return
	 */
	@RequiresPermissions("smsNewhomePriceRule:add")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public GenericResponse<String> add(SmsNewhomePriceRule smsNewhomePriceRule){
		int flag = smsNewhomePriceRuleService.insertSelective(smsNewhomePriceRule);
		GenericResponse<String> genericResponse = null;
		if (flag > 0){
		    genericResponse = new GenericResponse<String>(true,"新增成功");
		    genericResponse.setData("edit");
		} else{
		    genericResponse = new GenericResponse<String>(true,"新增失败");
		}
		return genericResponse;
	}
		
	/**
	 * 跳转到编辑页面
	 * @dzmsoftgenerated 
	 * @return
	 */
	@RequiresPermissions("smsNewhomePriceRule:edit")
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String editForm(@PathVariable("id") String id,Model model){
		SmsNewhomePriceRule smsNewhomePriceRule = smsNewhomePriceRuleService.selectByPrimaryKey(id);
		model.addAttribute("smsNewhomePriceRule", smsNewhomePriceRule);
		model.addAttribute("action", "edit");
		return "modules/base/smsNewhomePriceRuleForm";
	}
	
	/**
	 * 跳转到查看页面
	 * @dzmsoftgenerated 
	 * @return
	 */
	@RequiresPermissions("smsNewhomePriceRule:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String viewForm(@PathVariable("id") String id,Model model){
		SmsNewhomePriceRule smsNewhomePriceRule = smsNewhomePriceRuleService.selectByPrimaryKey(id);
		model.addAttribute("smsNewhomePriceRule", smsNewhomePriceRule);
		model.addAttribute("action", "view");
		return "modules/base/smsNewhomePriceRuleForm";
	}
	
		/**
	 * 编辑记录
	 * @dzmsoftgenerated 
	 * @return
	 */
	@RequiresPermissions("smsNewhomePriceRule:edit")
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	@ResponseBody
	public GenericResponse<String> edit(SmsNewhomePriceRule smsNewhomePriceRule){
		int flag = smsNewhomePriceRuleService.updateByPrimaryKeySelective(smsNewhomePriceRule);
		GenericResponse<String> genericResponse = null;
		if (flag > 0){
            genericResponse = new GenericResponse<String>(true,"编辑成功");
            genericResponse.setData("edit");
        } else{
            genericResponse = new GenericResponse<String>(true,"编辑失败");
        }
		return genericResponse;
	}
		
	/**
	 * 删除记录
	 * @dzmsoftgenerated 
	 * @return
	 */
	@RequiresPermissions("smsNewhomePriceRule:remove")
	@RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse remove(@PathVariable("id") String id){
		int flag = smsNewhomePriceRuleService.deleteByPrimaryKey(id);
		BaseResponse baseResponse = flag>=0?new BaseResponse(true,"删除成功"):new BaseResponse(false, "删除失败");
		return baseResponse;
	}
	
		@RequiresPermissions("smsNewhomePriceRule:enable")
	@RequestMapping(value = "enable/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public BaseResponse enable(@PathVariable("id") String id){
		int flag = smsNewhomePriceRuleService.enable(id);
		BaseResponse baseResponse = flag>=0?new BaseResponse(true,"启用成功"):new BaseResponse(false, "启用失败");
		return baseResponse;
	}
	
	@RequiresPermissions("smsNewhomePriceRule:disable")
	@RequestMapping(value = "disable/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public BaseResponse disable(@PathVariable("id") String id){
		int flag = smsNewhomePriceRuleService.disable(id);
		BaseResponse baseResponse = flag>=0?new BaseResponse(true,"禁用成功"):new BaseResponse(false, "禁用失败");
		return baseResponse;
	}
	}
