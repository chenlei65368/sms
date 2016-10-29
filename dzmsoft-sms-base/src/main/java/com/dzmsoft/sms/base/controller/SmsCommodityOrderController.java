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
import com.dzmsoft.sms.base.dto.SmsCommodityOrderDto;
import com.dzmsoft.sms.base.pojo.SmsCommodityOrder;
import com.dzmsoft.sms.base.pojo.udf.SmsCommodityOrderUdfExample;
import com.dzmsoft.sms.base.service.SmsCommodityOrderService;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * Copyright (C), dzmsoft Co., Ltd
 * @author dzmsoft
 * @dzmsoftgenerated 2016-05-29 09:15:26
 *
 * @version 1.0
 */
@Controller
@RequestMapping("smsCommodityOrder")
public class SmsCommodityOrderController extends BaseController{
	@Autowired
	private SmsCommodityOrderService smsCommodityOrderService;

	/**
	 * 显示主列表页面
	 * @dzmsoftgenerated 2016-05-29 09:15:26
	 * @return
	 */
	@RequiresPermissions("smsCommodityOrder:find")
	@RequestMapping(value = "show", method = RequestMethod.GET)
	public String show(){
		return "modules/base/smsCommodityOrderList";
	}
	
	/**
	 * 主列表中查询
	 * @dzmsoftgenerated 2016-05-29 09:15:26
	 * @return
	 */
	@RequiresPermissions("smsCommodityOrder:find")
	@RequestMapping(value = "find", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> find(HttpServletRequest request){
		List<Condition> conditions = Condition.buildFromHttpRequest(request);
		EasyUIPage easyUIPage = new EasyUIPage(request);
		MybatisExample mybatisExample = MybatisExample.getInstance();
		SmsCommodityOrderUdfExample example = (SmsCommodityOrderUdfExample)mybatisExample.buildExampleByCondition(conditions, easyUIPage, SmsCommodityOrderUdfExample.class.getName());
		PageList<SmsCommodityOrderDto> smsCommodityOrders = smsCommodityOrderService.selectByExample(example, new PageBounds(easyUIPage.getPage(),
                easyUIPage.getRows()));
		return getEasyUIGrid(smsCommodityOrders);
	}
	
	/**
	 * 跳转到新增页面
	 * @dzmsoftgenerated 2016-05-29 09:15:26
	 * @return
	 */
	@RequiresPermissions("smsCommodityOrder:add")
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String addForm(Model model){
		model.addAttribute("smsCommodityOrder", new SmsCommodityOrder());
		model.addAttribute("action", "add");
		return "modules/base/smsCommodityOrderForm";
	}
	
	/**
	 * 新增记录
	 * @dzmsoftgenerated 2016-05-29 09:15:26
	 * @return
	 */
	@RequiresPermissions("smsCommodityOrder:add")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse add(SmsCommodityOrder smsCommodityOrder){
		int flag = smsCommodityOrderService.insertSelective(smsCommodityOrder);
		BaseResponse baseResponse = flag>0?new BaseResponse(true,"新增成功"):new BaseResponse(false, "新增失败");
		return baseResponse;
	}
	
	/**
	 * 跳转到编辑页面
	 * @dzmsoftgenerated 2016-05-29 09:15:26
	 * @return
	 */
	@RequiresPermissions("smsCommodityOrder:edit")
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String editForm(@PathVariable("id") String id,Model model){
		SmsCommodityOrder smsCommodityOrder = smsCommodityOrderService.selectByPrimaryKey(id);
		model.addAttribute("smsCommodityOrder", smsCommodityOrder);
		model.addAttribute("action", "edit");
		return "modules/base/smsCommodityOrderForm";
	}
	
	/**
	 * 跳转到查看页面
	 * @dzmsoftgenerated 2016-05-29 09:15:26
	 * @return
	 */
	@RequiresPermissions("smsCommodityOrder:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String viewForm(@PathVariable("id") String id,Model model){
		SmsCommodityOrder smsCommodityOrder = smsCommodityOrderService.selectByPrimaryKey(id);
		model.addAttribute("smsCommodityOrder", smsCommodityOrder);
		model.addAttribute("action", "view");
		return "modules/base/smsCommodityOrderForm";
	}
	
	/**
	 * 编辑记录
	 * @dzmsoftgenerated 2016-05-29 09:15:26
	 * @return
	 */
	@RequiresPermissions("smsCommodityOrder:edit")
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse edit(SmsCommodityOrder smsCommodityOrder){
		int flag = smsCommodityOrderService.updateByPrimaryKeySelective(smsCommodityOrder);
		BaseResponse baseResponse = flag>0?new BaseResponse(true,"编辑成功"):new BaseResponse(false, "编辑失败");
		return baseResponse;
	}
	
	/**
	 * 删除记录
	 * @dzmsoftgenerated 2016-05-29 09:15:26
	 * @return
	 */
	@RequiresPermissions("smsCommodityOrder:remove")
	@RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse remove(@PathVariable("id") String id){
		int flag = smsCommodityOrderService.deleteByPrimaryKey(id);
		BaseResponse baseResponse = flag>=0?new BaseResponse(true,"删除成功"):new BaseResponse(false, "删除失败");
		return baseResponse;
	}
	
	/**
     * 派单
     * @param id
     * @param cleaners
     * @return
     */
    @RequiresPermissions("smsHouseholdOrder:send")
    @RequestMapping(value = "send/{orderId}", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse send(@PathVariable String orderId,  String cleaner){
        int flag = smsCommodityOrderService.send(orderId,cleaner);
        BaseResponse baseResponse = flag>=0?new BaseResponse(true,"派单成功"):new BaseResponse(false, "派单失败");
        return baseResponse;
    }
}
