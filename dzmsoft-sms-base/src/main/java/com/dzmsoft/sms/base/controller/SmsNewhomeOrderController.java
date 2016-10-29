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
import com.dzmsoft.framework.base.web.mvc.pojo.EasyUIPage;
import com.dzmsoft.framework.base.web.mvc.view.BaseResponse;
import com.dzmsoft.framework.base.web.mvc.view.Condition;
import com.dzmsoft.sms.base.dto.SmsNewhomeOrderDto;
import com.dzmsoft.sms.base.pojo.SmsNewhomeOrder;
import com.dzmsoft.sms.base.pojo.udf.SmsNewhomeOrderUdfExample;
import com.dzmsoft.sms.base.service.SmsNewhomeOrderService;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * Copyright (C), dzmsoft Co., Ltd
 * @author dzmsoft
 * @dzmsoftgenerated 2016-05-21 14:14:45
 *
 * @version 1.0
 */
@Controller
@RequestMapping("smsNewhomeOrder")
public class SmsNewhomeOrderController extends BaseController{
	@Autowired
	private SmsNewhomeOrderService smsNewhomeOrderService;

	/**
	 * 显示主列表页面
	 * @dzmsoftgenerated 2016-05-21 14:14:45
	 * @return
	 */
	@RequiresPermissions("smsNewhomeOrder:find")
	@RequestMapping(value = "show", method = RequestMethod.GET)
	public String show(){
		return "modules/base/smsNewhomeOrderList";
	}
	
	/**
	 * 主列表中查询
	 * @dzmsoftgenerated 2016-05-21 14:14:45
	 * @return
	 */
	@RequiresPermissions("smsNewhomeOrder:find")
	@RequestMapping(value = "find", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> find(HttpServletRequest request){
		List<Condition> conditions = Condition.buildFromHttpRequest(request);
		EasyUIPage easyUIPage = new EasyUIPage(request);
		MybatisExample mybatisExample = MybatisExample.getInstance();
		SmsNewhomeOrderUdfExample example = (SmsNewhomeOrderUdfExample)mybatisExample.buildExampleByCondition(conditions, easyUIPage, SmsNewhomeOrderUdfExample.class.getName());
		PageList<SmsNewhomeOrderDto> smsNewhomeOrders = smsNewhomeOrderService.selectByExample(example, new PageBounds(easyUIPage.getPage(),
                easyUIPage.getRows()));
		return getEasyUIGrid(smsNewhomeOrders);
	}
	
	/**
	 * 跳转到新增页面
	 * @dzmsoftgenerated 2016-05-21 14:14:45
	 * @return
	 */
	@RequiresPermissions("smsNewhomeOrder:add")
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String addForm(Model model){
		model.addAttribute("smsNewhomeOrder", new SmsNewhomeOrder());
		model.addAttribute("action", "add");
		return "modules/base/smsNewhomeOrderForm";
	}
	
	/**
	 * 新增记录
	 * @dzmsoftgenerated 2016-05-21 14:14:45
	 * @return
	 */
	@RequiresPermissions("smsNewhomeOrder:add")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse add(SmsNewhomeOrder smsNewhomeOrder){
		int flag = smsNewhomeOrderService.insertSelective(smsNewhomeOrder);
		BaseResponse baseResponse = flag>0?new BaseResponse(true,"新增成功"):new BaseResponse(false, "新增失败");
		return baseResponse;
	}
	
	/**
	 * 跳转到编辑页面
	 * @dzmsoftgenerated 2016-05-21 14:14:45
	 * @return
	 */
	@RequiresPermissions("smsNewhomeOrder:edit")
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String editForm(@PathVariable("id") String id,Model model){
		SmsNewhomeOrder smsNewhomeOrder = smsNewhomeOrderService.selectByPrimaryKey(id);
		model.addAttribute("smsNewhomeOrder", smsNewhomeOrder);
		model.addAttribute("action", "edit");
		return "modules/base/smsNewhomeOrderForm";
	}
	
	/**
	 * 跳转到查看页面
	 * @dzmsoftgenerated 2016-05-21 14:14:45
	 * @return
	 */
	@RequiresPermissions("smsNewhomeOrder:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String viewForm(@PathVariable("id") String id,Model model){
		SmsNewhomeOrder smsNewhomeOrder = smsNewhomeOrderService.selectByPrimaryKey(id);
		model.addAttribute("smsNewhomeOrder", smsNewhomeOrder);
		model.addAttribute("action", "view");
		return "modules/base/smsNewhomeOrderForm";
	}
	
	/**
	 * 编辑记录
	 * @dzmsoftgenerated 2016-05-21 14:14:45
	 * @return
	 */
	@RequiresPermissions("smsNewhomeOrder:edit")
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse edit(SmsNewhomeOrder smsNewhomeOrder){
		int flag = smsNewhomeOrderService.updateByPrimaryKeySelective(smsNewhomeOrder);
		BaseResponse baseResponse = flag>0?new BaseResponse(true,"编辑成功"):new BaseResponse(false, "编辑失败");
		return baseResponse;
	}
	
	/**
	 * 删除记录
	 * @dzmsoftgenerated 2016-05-21 14:14:45
	 * @return
	 */
	@RequiresPermissions("smsNewhomeOrder:remove")
	@RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse remove(@PathVariable("id") String id){
		int flag = smsNewhomeOrderService.deleteByPrimaryKey(id);
		BaseResponse baseResponse = flag>=0?new BaseResponse(true,"删除成功"):new BaseResponse(false, "删除失败");
		return baseResponse;
	}
	
	/**
	 * 派单
	 * @param orderId 订单编号
	 * @param id 新居开荒订单编号
	 * @param director 保洁负责人
	 * @param cleaners 保洁员
	 * @return
	 */
	@RequiresPermissions("smsNewhomeOrder:send")
    @RequestMapping(value = "send/{orderId}/{id}", method = RequestMethod.POST)
    @ResponseBody
	public BaseResponse send(@PathVariable String orderId, @PathVariable String id, String cleaners){
	    int flag = smsNewhomeOrderService.send(orderId, id, cleaners);
	    BaseResponse baseResponse = flag>=0?new BaseResponse(true,"派单成功"):new BaseResponse(false, "派单失败");
        return baseResponse;
	}
}
