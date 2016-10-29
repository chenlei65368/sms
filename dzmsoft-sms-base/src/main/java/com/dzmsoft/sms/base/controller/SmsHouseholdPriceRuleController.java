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
import com.dzmsoft.framework.base.web.mvc.shiro.UserUtil;
import com.dzmsoft.framework.base.web.mvc.view.BaseResponse;
import com.dzmsoft.framework.base.web.mvc.view.Condition;
import com.dzmsoft.framework.base.web.mvc.pojo.EasyUIPage;
import com.dzmsoft.framework.base.web.mvc.pojo.ShiroUser;
import com.dzmsoft.sms.base.pojo.SmsHouseholdPriceRule;
import com.dzmsoft.sms.base.pojo.SmsHouseholdPriceRuleExample;
import com.dzmsoft.sms.base.service.SmsHouseholdPriceRuleService;
import com.dzmsoft.sms.base.service.SmsSupplierSerivice;
import com.dzmsoft.sms.oim.pojo.OimSupplier;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.dzmsoft.framework.base.web.mvc.view.GenericResponse;

/**
 * Copyright (C), dzmsoft Co., Ltd
 * @author 
 * @dzmsoftgenerated 
 *
 * @version 
 */
@Controller
@RequestMapping("smsHouseholdPriceRule")
public class SmsHouseholdPriceRuleController extends BaseController{
	@Autowired
	private SmsHouseholdPriceRuleService smsHouseholdPriceRuleService;
	@Autowired
	private SmsSupplierSerivice smsSupplierSerivice;

	/**
	 * 显示主列表页面
	 * @dzmsoftgenerated 
	 * @return
	 */
	@RequiresPermissions("smsHouseholdPriceRule:find")
	@RequestMapping(value = "show", method = RequestMethod.GET)
	public String show(){
		return "modules/base/smsHouseholdPriceRuleList";
	}
	
	/**
	 * 主列表中查询
	 * @dzmsoftgenerated 
	 * @return
	 */
	@RequiresPermissions("smsHouseholdPriceRule:find")
	@RequestMapping(value = "find", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> find(HttpServletRequest request){
		List<Condition> conditions = Condition.buildFromHttpRequest(request);
		ShiroUser user=UserUtil.getCurrentShiroUser();
        OimSupplier oimSupplier = smsSupplierSerivice.selectByUcsId(user.getId());
        conditions.add(new Condition("eqs_supplier",oimSupplier!=null?oimSupplier.getId():"-1"));
		EasyUIPage easyUIPage = new EasyUIPage(request);
		MybatisExample mybatisExample = MybatisExample.getInstance();
		SmsHouseholdPriceRuleExample example = (SmsHouseholdPriceRuleExample)mybatisExample.buildExampleByCondition(conditions, easyUIPage, SmsHouseholdPriceRuleExample.class.getName());
		PageList<SmsHouseholdPriceRule> smsHouseholdPriceRules = smsHouseholdPriceRuleService.selectByExample(example, new PageBounds(easyUIPage.getPage(),
                easyUIPage.getRows()));
		return getEasyUIGrid(smsHouseholdPriceRules);
	}
	
	/**
	 * 跳转到新增页面
	 * @dzmsoftgenerated 
	 * @return
	 */
	@RequiresPermissions("smsHouseholdPriceRule:add")
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String addForm(Model model){
	    SmsHouseholdPriceRule smsHouseholdPriceRule = new SmsHouseholdPriceRule();
	    smsHouseholdPriceRule.setId(StringUtil.getUuidString());
		model.addAttribute("smsHouseholdPriceRule", smsHouseholdPriceRule);
		model.addAttribute("action", "add");
		return "modules/base/smsHouseholdPriceRuleForm";
	}
	
		/**
	 * 新增记录
	 * @dzmsoftgenerated 
	 * @return
	 */
	@RequiresPermissions("smsHouseholdPriceRule:add")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public GenericResponse<String> add(SmsHouseholdPriceRule smsHouseholdPriceRule){
		int flag = smsHouseholdPriceRuleService.insertSelective(smsHouseholdPriceRule);
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
	@RequiresPermissions("smsHouseholdPriceRule:edit")
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String editForm(@PathVariable("id") String id,Model model){
		SmsHouseholdPriceRule smsHouseholdPriceRule = smsHouseholdPriceRuleService.selectByPrimaryKey(id);
		model.addAttribute("smsHouseholdPriceRule", smsHouseholdPriceRule);
		model.addAttribute("action", "edit");
		return "modules/base/smsHouseholdPriceRuleForm";
	}
	
	/**
	 * 跳转到查看页面
	 * @dzmsoftgenerated 
	 * @return
	 */
	@RequiresPermissions("smsHouseholdPriceRule:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String viewForm(@PathVariable("id") String id,Model model){
		SmsHouseholdPriceRule smsHouseholdPriceRule = smsHouseholdPriceRuleService.selectByPrimaryKey(id);
		model.addAttribute("smsHouseholdPriceRule", smsHouseholdPriceRule);
		model.addAttribute("action", "view");
		return "modules/base/smsHouseholdPriceRuleForm";
	}
	
		/**
	 * 编辑记录
	 * @dzmsoftgenerated 
	 * @return
	 */
	@RequiresPermissions("smsHouseholdPriceRule:edit")
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	@ResponseBody
	public GenericResponse<String> edit(SmsHouseholdPriceRule smsHouseholdPriceRule){
		int flag = smsHouseholdPriceRuleService.updateByPrimaryKeySelective(smsHouseholdPriceRule);
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
	@RequiresPermissions("smsHouseholdPriceRule:remove")
	@RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse remove(@PathVariable("id") String id){
		int flag = smsHouseholdPriceRuleService.deleteByPrimaryKey(id);
		BaseResponse baseResponse = flag>=0?new BaseResponse(true,"删除成功"):new BaseResponse(false, "删除失败");
		return baseResponse;
	}
	
		@RequiresPermissions("smsHouseholdPriceRule:enable")
	@RequestMapping(value = "enable/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public BaseResponse enable(@PathVariable("id") String id){
		int flag = smsHouseholdPriceRuleService.enable(id);
		BaseResponse baseResponse = flag>=0?new BaseResponse(true,"启用成功"):new BaseResponse(false, "启用失败");
		return baseResponse;
	}
	
	@RequiresPermissions("smsHouseholdPriceRule:disable")
	@RequestMapping(value = "disable/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public BaseResponse disable(@PathVariable("id") String id){
		int flag = smsHouseholdPriceRuleService.disable(id);
		BaseResponse baseResponse = flag>=0?new BaseResponse(true,"禁用成功"):new BaseResponse(false, "禁用失败");
		return baseResponse;
	}
	}
