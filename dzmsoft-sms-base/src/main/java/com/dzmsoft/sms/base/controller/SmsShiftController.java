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

import com.dzmsoft.framework.base.common.BaseConstant;
import com.dzmsoft.framework.base.web.mvc.controller.BaseController;
import com.dzmsoft.framework.base.web.mvc.dao.MybatisExample;
import com.dzmsoft.framework.base.web.mvc.shiro.UserUtil;
import com.dzmsoft.framework.base.web.mvc.view.BaseResponse;
import com.dzmsoft.framework.base.web.mvc.view.Condition;
import com.dzmsoft.framework.base.web.mvc.pojo.EasyUIPage;
import com.dzmsoft.framework.base.web.mvc.pojo.ShiroUser;
import com.dzmsoft.sms.base.pojo.SmsShift;
import com.dzmsoft.sms.base.pojo.SmsShiftExample;
import com.dzmsoft.sms.base.service.SmsShiftService;
import com.dzmsoft.sms.base.service.SmsSupplierSerivice;
import com.dzmsoft.sms.oim.pojo.OimSupplier;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * Copyright (C), dzmsoft Co., Ltd
 * @author dzmsoft
 * @dzmsoftgenerated 2016-05-14 22:26:11
 *
 * @version 1.0
 */
@Controller
@RequestMapping("smsShift")
public class SmsShiftController extends BaseController{
	@Autowired
	private SmsShiftService smsShiftService;
	@Autowired
	private SmsSupplierSerivice smsSupplierSerivice;

	/**
	 * 显示主列表页面
	 * @dzmsoftgenerated 2016-05-14 22:26:11
	 * @return
	 */
	@RequiresPermissions("smsShift:find")
	@RequestMapping(value = "show", method = RequestMethod.GET)
	public String show(){
		return "modules/base/smsShiftList";
	}
	
	/**
	 * 主列表中查询
	 * @dzmsoftgenerated 2016-05-14 22:26:11
	 * @return
	 */
	@RequiresPermissions("smsShift:find")
	@RequestMapping(value = "find", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> find(HttpServletRequest request){
		List<Condition> conditions = Condition.buildFromHttpRequest(request);
		EasyUIPage easyUIPage = new EasyUIPage(request);
		MybatisExample mybatisExample = MybatisExample.getInstance();
		SmsShiftExample example = (SmsShiftExample)mybatisExample.buildExampleByCondition(conditions, easyUIPage, SmsShiftExample.class.getName());
		PageList<SmsShift> smsShifts = smsShiftService.selectByExample(example, new PageBounds(easyUIPage.getPage(),
                easyUIPage.getRows()));
		return getEasyUIGrid(smsShifts);
	}
	
	/**
     * 主列表中查询
     * @dzmsoftgenerated 2016-05-14 22:26:11
     * @return
     */
    @RequestMapping(value = "find", method = RequestMethod.GET)
    @ResponseBody
    public List<SmsShift> findList(HttpServletRequest request){
        List<Condition> conditions = Condition.buildFromHttpRequest(request);
        conditions.add(new Condition("eqs_status",BaseConstant.Status.ENABLE));
        MybatisExample mybatisExample = MybatisExample.getInstance();
        SmsShiftExample example = (SmsShiftExample)mybatisExample.buildExampleByCondition(conditions, null, SmsShiftExample.class.getName());
       List<SmsShift> smsShifts = smsShiftService.selectByExample(example);
        return smsShifts;
    }
	
	/**
	 * 跳转到新增页面
	 * @dzmsoftgenerated 2016-05-14 22:26:11
	 * @return
	 */
	@RequiresPermissions("smsShift:add")
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String addForm(Model model){
		model.addAttribute("smsShift", new SmsShift());
		model.addAttribute("action", "add");
		return "modules/base/smsShiftForm";
	}
	
	/**
	 * 新增记录
	 * @dzmsoftgenerated 2016-05-14 22:26:11
	 * @return
	 */
	@RequiresPermissions("smsShift:add")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse add(SmsShift smsShift){
	    ShiroUser user=UserUtil.getCurrentShiroUser();
        OimSupplier oimSupplier = smsSupplierSerivice.selectByUcsId(user.getId());
        if (oimSupplier == null){
            return new BaseResponse(false,"新增失败，当前用户不存在对应的供应商");
        }
        smsShift.setSupplier(oimSupplier.getId());
		int flag = smsShiftService.insertSelective(smsShift);
		BaseResponse baseResponse = flag>0?new BaseResponse(true,"新增成功"):new BaseResponse(false, "新增失败");
		return baseResponse;
	}
	
	/**
	 * 跳转到编辑页面
	 * @dzmsoftgenerated 2016-05-14 22:26:11
	 * @return
	 */
	@RequiresPermissions("smsShift:edit")
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String editForm(@PathVariable("id") String id,Model model){
		SmsShift smsShift = smsShiftService.selectByPrimaryKey(id);
		model.addAttribute("smsShift", smsShift);
		model.addAttribute("action", "edit");
		return "modules/base/smsShiftForm";
	}
	
	/**
	 * 跳转到查看页面
	 * @dzmsoftgenerated 2016-05-14 22:26:11
	 * @return
	 */
	@RequiresPermissions("smsShift:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String viewForm(@PathVariable("id") String id,Model model){
		SmsShift smsShift = smsShiftService.selectByPrimaryKey(id);
		model.addAttribute("smsShift", smsShift);
		model.addAttribute("action", "view");
		return "modules/base/smsShiftForm";
	}
	
	/**
	 * 编辑记录
	 * @dzmsoftgenerated 2016-05-14 22:26:11
	 * @return
	 */
	@RequiresPermissions("smsShift:edit")
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse edit(SmsShift smsShift){
		int flag = smsShiftService.updateByPrimaryKeySelective(smsShift);
		BaseResponse baseResponse = flag>0?new BaseResponse(true,"编辑成功"):new BaseResponse(false, "编辑失败");
		return baseResponse;
	}
	
	/**
	 * 删除记录
	 * @dzmsoftgenerated 2016-05-14 22:26:11
	 * @return
	 */
	@RequiresPermissions("smsShift:remove")
	@RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse remove(@PathVariable("id") String id){
		int flag = smsShiftService.deleteByPrimaryKey(id);
		BaseResponse baseResponse = flag>=0?new BaseResponse(true,"删除成功"):new BaseResponse(false, "删除失败");
		return baseResponse;
	}
	
	/**
	 * 启用
	 * @param id
	 * @return
	 */
	@RequiresPermissions("smsShift:enable")
    @RequestMapping(value = "enable/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public BaseResponse enable(@PathVariable("id") String id){
        int flag = smsShiftService.updateStatus(id, BaseConstant.Status.ENABLE);
        BaseResponse baseResponse = flag>=0?new BaseResponse(true,"启用成功"):new BaseResponse(false, "启用失败");
        return baseResponse;
    }
	
	/**
	 * 禁用
	 * @param id
	 * @return
	 */
	@RequiresPermissions("smsShift:disable")
    @RequestMapping(value = "disable/{id}", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse disable(@PathVariable("id") String id){
        int flag = smsShiftService.updateStatus(id, BaseConstant.Status.DISABLED);
        BaseResponse baseResponse = flag>=0?new BaseResponse(true,"禁用成功"):new BaseResponse(false, "禁用失败");
        return baseResponse;
    }
}
