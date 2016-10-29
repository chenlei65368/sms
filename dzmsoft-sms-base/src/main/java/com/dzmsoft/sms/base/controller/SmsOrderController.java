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
import com.dzmsoft.sms.base.pojo.SmsOrder;
import com.dzmsoft.sms.base.pojo.SmsOrderExample;
import com.dzmsoft.sms.base.service.SmsOrderService;
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
@RequestMapping("smsOrder")
public class SmsOrderController extends BaseController{
	@Autowired
	private SmsOrderService smsOrderService;

	/**
	 * 显示主列表页面
	 * @dzmsoftgenerated 2016-05-04 17:10:32
	 * @return
	 */
	@RequiresPermissions("smsOrder:find")
	@RequestMapping(value = "show", method = RequestMethod.GET)
	public String show(){
		return "modules/base/smsOrderList";
	}
	
	/**
	 * 主列表中查询
	 * @dzmsoftgenerated 2016-05-04 17:10:32
	 * @return
	 */
	@RequiresPermissions("smsOrder:find")
	@RequestMapping(value = "find", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> find(HttpServletRequest request){
		List<Condition> conditions = Condition.buildFromHttpRequest(request);
		EasyUIPage easyUIPage = new EasyUIPage(request);
		MybatisExample mybatisExample = MybatisExample.getInstance();
		SmsOrderExample example = (SmsOrderExample)mybatisExample.buildExampleByCondition(conditions, easyUIPage, SmsOrderExample.class.getName());
		PageList<SmsOrder> smsOrders = smsOrderService.selectByExample(example, new PageBounds(easyUIPage.getPage(),
                easyUIPage.getRows()));
		return getEasyUIGrid(smsOrders);
	}
	
	/**
	 * 跳转到新增页面
	 * @dzmsoftgenerated 2016-05-04 17:10:32
	 * @return
	 */
	@RequiresPermissions("smsOrder:add")
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String addForm(Model model){
		model.addAttribute("smsOrder", new SmsOrder());
		model.addAttribute("action", "add");
		return "modules/base/smsOrderForm";
	}
	
	/**
	 * 新增记录
	 * @dzmsoftgenerated 2016-05-04 17:10:32
	 * @return
	 */
	@RequiresPermissions("smsOrder:add")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse add(SmsOrder smsOrder){
		int flag = smsOrderService.insertSelective(smsOrder);
		BaseResponse baseResponse = flag>0?new BaseResponse(true,"新增成功"):new BaseResponse(false, "新增失败");
		return baseResponse;
	}
	
	/**
	 * 跳转到编辑页面
	 * @dzmsoftgenerated 2016-05-04 17:10:32
	 * @return
	 */
	@RequiresPermissions("smsOrder:edit")
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String editForm(@PathVariable("id") String id,Model model){
		SmsOrder smsOrder = smsOrderService.selectByPrimaryKey(id);
		model.addAttribute("smsOrder", smsOrder);
		model.addAttribute("action", "edit");
		return "modules/base/smsOrderForm";
	}
	
	/**
	 * 跳转到查看页面
	 * @dzmsoftgenerated 2016-05-04 17:10:32
	 * @return
	 */
	@RequiresPermissions("smsOrder:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String viewForm(@PathVariable("id") String id,Model model){
		SmsOrder smsOrder = smsOrderService.selectByPrimaryKey(id);
		model.addAttribute("smsOrder", smsOrder);
		model.addAttribute("action", "view");
		return "modules/base/smsOrderForm";
	}
	
	/**
	 * 编辑记录
	 * @dzmsoftgenerated 2016-05-04 17:10:32
	 * @return
	 */
	@RequiresPermissions("smsOrder:edit")
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse edit(SmsOrder smsOrder){
		int flag = smsOrderService.updateByPrimaryKeySelective(smsOrder);
		BaseResponse baseResponse = flag>0?new BaseResponse(true,"编辑成功"):new BaseResponse(false, "编辑失败");
		return baseResponse;
	}
	
	/**
	 * 删除记录
	 * @dzmsoftgenerated 2016-05-04 17:10:32
	 * @return
	 */
	@RequiresPermissions("smsOrder:remove")
	@RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse remove(@PathVariable("id") String id){
		int flag = smsOrderService.deleteByPrimaryKey(id);
		BaseResponse baseResponse = flag>=0?new BaseResponse(true,"删除成功"):new BaseResponse(false, "删除失败");
		return baseResponse;
	}
}
