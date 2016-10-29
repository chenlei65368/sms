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
import com.dzmsoft.sms.base.pojo.SmsCoupon;
import com.dzmsoft.sms.base.pojo.SmsCouponExample;
import com.dzmsoft.sms.base.service.SmsCouponService;
import com.dzmsoft.sms.base.service.SmsSupplierSerivice;
import com.dzmsoft.sms.oim.pojo.OimSupplier;
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
@RequestMapping("smsCoupon")
public class SmsCouponController extends BaseController{
	@Autowired
	private SmsCouponService smsCouponService;
	@Autowired
	private SmsSupplierSerivice smsSupplierSerivice;

	/**
	 * 显示主列表页面
	 * @dzmsoftgenerated 2016-05-04 17:10:32
	 * @return
	 */
	@RequiresPermissions("smsCoupon:find")
	@RequestMapping(value = "show", method = RequestMethod.GET)
	public String show(){
		return "modules/base/smsCouponList";
	}
	
	/**
	 * 主列表中查询
	 * @dzmsoftgenerated 2016-05-04 17:10:32
	 * @return
	 */
	@RequiresPermissions("smsCoupon:find")
	@RequestMapping(value = "find", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> find(HttpServletRequest request){
		List<Condition> conditions = Condition.buildFromHttpRequest(request);
		ShiroUser user=UserUtil.getCurrentShiroUser();
        OimSupplier oimSupplier = smsSupplierSerivice.selectByUcsId(user.getId());
        conditions.add(new Condition("eqs_supplier",oimSupplier!=null?oimSupplier.getId():"-1"));
		EasyUIPage easyUIPage = new EasyUIPage(request);
		MybatisExample mybatisExample = MybatisExample.getInstance();
		SmsCouponExample example = (SmsCouponExample)mybatisExample.buildExampleByCondition(conditions, easyUIPage, SmsCouponExample.class.getName());
		PageList<SmsCoupon> smsCoupons = smsCouponService.selectByExample(example, new PageBounds(easyUIPage.getPage(),
                easyUIPage.getRows()));
		return getEasyUIGrid(smsCoupons);
	}
	
	/**
	 * 跳转到新增页面
	 * @dzmsoftgenerated 2016-05-04 17:10:32
	 * @return
	 */
	@RequiresPermissions("smsCoupon:add")
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String addForm(Model model){
		model.addAttribute("smsCoupon", new SmsCoupon());
		model.addAttribute("action", "add");
		return "modules/base/smsCouponForm";
	}
	
	/**
	 * 新增记录
	 * @dzmsoftgenerated 2016-05-04 17:10:32
	 * @return
	 */
	@RequiresPermissions("smsCoupon:add")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse add(SmsCoupon smsCoupon){
	    ShiroUser user=UserUtil.getCurrentShiroUser();
        OimSupplier oimSupplier = smsSupplierSerivice.selectByUcsId(user.getId());
        if (oimSupplier == null){
            return new BaseResponse(false,"新增失败，当前用户不存在对应的供应商");
        }
        smsCoupon.setSupplier(oimSupplier.getId());
		int flag = smsCouponService.insertSelective(smsCoupon);
		BaseResponse baseResponse = flag>0?new BaseResponse(true,"新增成功"):new BaseResponse(false, "新增失败");
		return baseResponse;
	}
	
	/**
	 * 跳转到编辑页面
	 * @dzmsoftgenerated 2016-05-04 17:10:32
	 * @return
	 */
	@RequiresPermissions("smsCoupon:edit")
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String editForm(@PathVariable("id") String id,Model model){
		SmsCoupon smsCoupon = smsCouponService.selectByPrimaryKey(id);
		model.addAttribute("smsCoupon", smsCoupon);
		model.addAttribute("action", "edit");
		return "modules/base/smsCouponForm";
	}
	
	/**
	 * 跳转到查看页面
	 * @dzmsoftgenerated 2016-05-04 17:10:32
	 * @return
	 */
	@RequiresPermissions("smsCoupon:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String viewForm(@PathVariable("id") String id,Model model){
		SmsCoupon smsCoupon = smsCouponService.selectByPrimaryKey(id);
		model.addAttribute("smsCoupon", smsCoupon);
		model.addAttribute("action", "view");
		return "modules/base/smsCouponForm";
	}
	
	/**
	 * 编辑记录
	 * @dzmsoftgenerated 2016-05-04 17:10:32
	 * @return
	 */
	@RequiresPermissions("smsCoupon:edit")
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse edit(SmsCoupon smsCoupon){
		int flag = smsCouponService.updateByPrimaryKeySelective(smsCoupon);
		BaseResponse baseResponse = flag>0?new BaseResponse(true,"编辑成功"):new BaseResponse(false, "编辑失败");
		return baseResponse;
	}
	
	/**
	 * 删除记录
	 * @dzmsoftgenerated 2016-05-04 17:10:32
	 * @return
	 */
	@RequiresPermissions("smsCoupon:remove")
	@RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse remove(@PathVariable("id") String id){
		int flag = smsCouponService.deleteByPrimaryKey(id);
		BaseResponse baseResponse = flag>=0?new BaseResponse(true,"删除成功"):new BaseResponse(false, "删除失败");
		return baseResponse;
	}
	
	/**
     * 启用记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     * @return
     */
    @RequiresPermissions("smsCoupon:enable")
    @RequestMapping(value = "enable/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public BaseResponse enable(@PathVariable("id") String id){
        int flag = smsCouponService.updateStatus(id, BaseConstant.Status.ENABLE);
        BaseResponse baseResponse = flag>=0?new BaseResponse(true,"启用成功"):new BaseResponse(false, "启用失败");
        return baseResponse;
    }
    
    /**
     * 禁用记录
     * @dzmsoftgenerated 2016-05-04 17:10:32
     * @return
     */
    @RequiresPermissions("smsCoupon: disable")
    @RequestMapping(value = " disable/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public BaseResponse disable(@PathVariable("id") String id){
        int flag = smsCouponService.updateStatus(id, BaseConstant.Status.DISABLED);
        BaseResponse baseResponse = flag>=0?new BaseResponse(true,"禁用成功"):new BaseResponse(false, "禁用失败");
        return baseResponse;
    }
}
