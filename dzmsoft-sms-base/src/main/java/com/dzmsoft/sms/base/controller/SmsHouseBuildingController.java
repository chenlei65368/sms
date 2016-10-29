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
import com.dzmsoft.sms.base.pojo.SmsHouseBuilding;
import com.dzmsoft.sms.base.pojo.SmsHouseBuildingExample;
import com.dzmsoft.sms.base.service.SmsHouseBuildingService;
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
@RequestMapping("smsHouseBuilding")
public class SmsHouseBuildingController extends BaseController{
	@Autowired
	private SmsHouseBuildingService smsHouseBuildingService;

	/**
	 * 显示主列表页面
	 * @dzmsoftgenerated 
	 * @return
	 */
	@RequiresPermissions("smsHouseBuilding:find")
	@RequestMapping(value = "show", method = RequestMethod.GET)
	public String show(){
		return "modules/base/smsHouseBuildingList";
	}
	
	/**
	 * 主列表中查询
	 * @dzmsoftgenerated 
	 * @return
	 */
	@RequiresPermissions("smsHouseBuilding:find")
	@RequestMapping(value = "find", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> find(HttpServletRequest request){
		List<Condition> conditions = Condition.buildFromHttpRequest(request);
		EasyUIPage easyUIPage = new EasyUIPage(request);
		MybatisExample mybatisExample = MybatisExample.getInstance();
		SmsHouseBuildingExample example = (SmsHouseBuildingExample)mybatisExample.buildExampleByCondition(conditions, easyUIPage, SmsHouseBuildingExample.class.getName());
		PageList<SmsHouseBuilding> smsHouseBuildings = smsHouseBuildingService.selectByExample(example, new PageBounds(easyUIPage.getPage(),
                easyUIPage.getRows()));
		return getEasyUIGrid(smsHouseBuildings);
	}
	
	/**
	 * 跳转到新增页面
	 * @dzmsoftgenerated 
	 * @return
	 */
	@RequiresPermissions("smsHouseBuilding:add")
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String addForm(Model model){
		SmsHouseBuilding smsHouseBuilding = new SmsHouseBuilding();
				model.addAttribute("smsHouseBuilding", smsHouseBuilding);
		model.addAttribute("action", "add");
		return "modules/base/smsHouseBuildingForm";
	}
	
		/**
	 * 新增记录
	 * @dzmsoftgenerated 
	 * @return
	 */
	@RequiresPermissions("smsHouseBuilding:add")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse add(SmsHouseBuilding smsHouseBuilding){
	    boolean isExist = smsHouseBuildingService.isExist(smsHouseBuilding.getId());
	    int flag = -1;
	    BaseResponse baseResponse = null;
	    if (!isExist){
	        flag = smsHouseBuildingService.insertSelective(smsHouseBuilding);
	        baseResponse = flag>0?new BaseResponse(true,"新增成功"):new BaseResponse(false, "新增失败");
	    } else{
	        flag = smsHouseBuildingService.updateByPrimaryKeySelective(smsHouseBuilding);
	        baseResponse = flag>0?new BaseResponse(true,"编辑成功"):new BaseResponse(false, "编辑失败");
	    }
		return baseResponse;
	}
		
	/**
	 * 跳转到编辑页面
	 * @dzmsoftgenerated 
	 * @return
	 */
	@RequiresPermissions("smsHouseBuilding:edit")
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String editForm(@PathVariable("id") String id,Model model){
		SmsHouseBuilding smsHouseBuilding = smsHouseBuildingService.selectByPrimaryKey(id);
		model.addAttribute("smsHouseBuilding", smsHouseBuilding);
		model.addAttribute("action", "edit");
		return "modules/base/smsHouseBuildingForm";
	}
	
	/**
	 * 跳转到查看页面
	 * @dzmsoftgenerated 
	 * @return
	 */
	@RequiresPermissions("smsHouseBuilding:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String viewForm(@PathVariable("id") String id,Model model){
		SmsHouseBuilding smsHouseBuilding = smsHouseBuildingService.selectByPrimaryKey(id);
		model.addAttribute("smsHouseBuilding", smsHouseBuilding);
		model.addAttribute("action", "view");
		return "modules/base/smsHouseBuildingForm";
	}
	
		/**
	 * 编辑记录
	 * @dzmsoftgenerated 
	 * @return
	 */
	@RequiresPermissions("smsHouseBuilding:edit")
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse edit(SmsHouseBuilding smsHouseBuilding){
		int flag = smsHouseBuildingService.updateByPrimaryKeySelective(smsHouseBuilding);
		BaseResponse baseResponse = flag>0?new BaseResponse(true,"编辑成功"):new BaseResponse(false, "编辑失败");
		return baseResponse;
	}
		
	/**
	 * 删除记录
	 * @dzmsoftgenerated 
	 * @return
	 */
	@RequiresPermissions("smsHouseBuilding:remove")
	@RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse remove(@PathVariable("id") String id){
		int flag = smsHouseBuildingService.deleteByPrimaryKey(id);
		BaseResponse baseResponse = flag>=0?new BaseResponse(true,"删除成功"):new BaseResponse(false, "删除失败");
		return baseResponse;
	}
	
		@RequiresPermissions("smsHouseBuilding:enable")
	@RequestMapping(value = "enable/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public BaseResponse enable(@PathVariable("id") String id){
		int flag = smsHouseBuildingService.enable(id);
		BaseResponse baseResponse = flag>=0?new BaseResponse(true,"启用成功"):new BaseResponse(false, "启用失败");
		return baseResponse;
	}
	
	@RequiresPermissions("smsHouseBuilding:disable")
	@RequestMapping(value = "disable/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public BaseResponse disable(@PathVariable("id") String id){
		int flag = smsHouseBuildingService.disable(id);
		BaseResponse baseResponse = flag>=0?new BaseResponse(true,"禁用成功"):new BaseResponse(false, "禁用失败");
		return baseResponse;
	}
	}
