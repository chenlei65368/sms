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
import com.dzmsoft.framework.base.web.mvc.pojo.ShiroUser;
import com.dzmsoft.framework.base.web.mvc.shiro.UserUtil;
import com.dzmsoft.framework.base.web.mvc.view.BaseResponse;
import com.dzmsoft.framework.base.web.mvc.view.Condition;
import com.dzmsoft.sms.base.pojo.SmsPersonRequire;
import com.dzmsoft.sms.base.pojo.SmsPersonRequireExample;
import com.dzmsoft.sms.base.pojo.SmsPersonRequireLine;
import com.dzmsoft.sms.base.service.SmsPersonRequireLineService;
import com.dzmsoft.sms.base.service.SmsPersonRequireService;
import com.dzmsoft.sms.base.service.SmsSupplierSerivice;
import com.dzmsoft.sms.oim.pojo.OimSupplier;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Copyright (C), dzmsoft Co., Ltd
 * 
 * @author
 * @dzmsoftgenerated
 * @version
 */
@Controller
@RequestMapping("smsPersonRequire")
public class SmsPersonRequireController extends BaseController {
    @Autowired
    private SmsPersonRequireService smsPersonRequireService;

    @Autowired
    private SmsPersonRequireLineService smsPersonRequireLineService;

    @Autowired
    private SmsSupplierSerivice smsSupplierSerivice;

    /**
     * 显示主列表页面
     * 
     * @dzmsoftgenerated
     * @return
     */
    @RequiresPermissions("smsPersonRequire:find")
    @RequestMapping(value = "show", method = RequestMethod.GET)
    public String show() {
        return "modules/base/smsPersonRequireList";
    }

    /**
     * 主列表中查询
     * 
     * @dzmsoftgenerated
     * @return
     */
    @RequiresPermissions("smsPersonRequire:find")
    @RequestMapping(value = "find", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> find(HttpServletRequest request) {
        List<Condition> conditions = Condition.buildFromHttpRequest(request);
        ShiroUser user = UserUtil.getCurrentShiroUser();
        OimSupplier oimSupplier = smsSupplierSerivice.selectByUcsId(user.getId());
        conditions.add(new Condition("eqs_supplier", oimSupplier != null ? oimSupplier.getId()
                                                                        : "-1"));
        EasyUIPage easyUIPage = new EasyUIPage(request);
        MybatisExample mybatisExample = MybatisExample.getInstance();
        SmsPersonRequireExample example = (SmsPersonRequireExample) mybatisExample
                .buildExampleByCondition(
                        conditions,
                        easyUIPage,
                        SmsPersonRequireExample.class.getName());
        PageList<SmsPersonRequire> smsPersonRequires = smsPersonRequireService.selectByExample(
                example,
                new PageBounds(easyUIPage.getPage(), easyUIPage.getRows()));
        return getEasyUIGrid(smsPersonRequires);
    }

    /**
     * 跳转到新增页面
     * 
     * @dzmsoftgenerated
     * @return
     */
    @RequiresPermissions("smsPersonRequire:add")
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addForm(Model model) {
        model.addAttribute("smsPersonRequire", new SmsPersonRequire());
        model.addAttribute("action", "add");
        return "modules/base/smsPersonRequireForm";
    }

    /**
     * 新增记录
     * 
     * @dzmsoftgenerated
     * @return
     */
    @RequiresPermissions("smsPersonRequire:add")
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse add(SmsPersonRequire smsPersonRequire, String datasSmsPersonRequireLine) {
        Gson gson = new Gson();
        datasSmsPersonRequireLine = StringUtil.unescapeHtml(datasSmsPersonRequireLine);
        List<SmsPersonRequireLine> itemsSmsPersonRequireLine = gson.fromJson(
                datasSmsPersonRequireLine,
                new TypeToken<List<SmsPersonRequireLine>>() {
                }.getType());
        int flag = smsPersonRequireService.insertContainDetails(
                smsPersonRequire,
                itemsSmsPersonRequireLine);
        BaseResponse baseResponse = flag > 0 ? new BaseResponse(true, "新增成功") : new BaseResponse(
                false, "新增失败");
        return baseResponse;
    }

    /**
     * 跳转到编辑页面
     * 
     * @dzmsoftgenerated
     * @return
     */
    @RequiresPermissions("smsPersonRequire:edit")
    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String editForm(@PathVariable("id") String id, Model model) {
        SmsPersonRequire smsPersonRequire = smsPersonRequireService.selectByPrimaryKey(id);
        model.addAttribute("smsPersonRequire", smsPersonRequire);
        model.addAttribute("action", "edit");
        List<SmsPersonRequireLine> smsPersonRequireLine = smsPersonRequireLineService.selectBySmsPersonRequire(id);
        if (!CheckEmptyUtil.isEmpty(smsPersonRequireLine)) {
            Gson gson = new Gson();
            String datasSmsPersonRequireLine = gson.toJson(smsPersonRequireLine);
            model.addAttribute(
                    "datasSmsPersonRequireLine",
                    StringUtil.escapeHtml(datasSmsPersonRequireLine));
        }
        return "modules/base/smsPersonRequireForm";
    }

    /**
     * 跳转到查看页面
     * 
     * @dzmsoftgenerated
     * @return
     */
    @RequiresPermissions("smsPersonRequire:view")
    @RequestMapping(value = "view/{id}", method = RequestMethod.GET)
    public String viewForm(@PathVariable("id") String id, Model model) {
        SmsPersonRequire smsPersonRequire = smsPersonRequireService.selectByPrimaryKey(id);
        model.addAttribute("smsPersonRequire", smsPersonRequire);
        model.addAttribute("action", "view");
        List<SmsPersonRequireLine> smsPersonRequireLine = null;
        if (!CheckEmptyUtil.isEmpty(smsPersonRequireLine)) {
            Gson gson = new Gson();
            String datasSmsPersonRequireLine = gson.toJson(smsPersonRequireLine);
            model.addAttribute(
                    "datasSmsPersonRequireLine",
                    StringUtil.escapeHtml(datasSmsPersonRequireLine));
        }
        return "modules/base/smsPersonRequireForm";
    }

    /**
     * 编辑记录
     * 
     * @dzmsoftgenerated
     * @return
     */
    @RequiresPermissions("smsPersonRequire:edit")
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse edit(SmsPersonRequire smsPersonRequire, String datasSmsPersonRequireLine) {
        Gson gson = new Gson();
        datasSmsPersonRequireLine = StringUtil.unescapeHtml(datasSmsPersonRequireLine);
        List<SmsPersonRequireLine> itemsSmsPersonRequireLine = gson.fromJson(
                datasSmsPersonRequireLine,
                new TypeToken<List<SmsPersonRequireLine>>() {
                }.getType());
        int flag = smsPersonRequireService.updateContainDetails(
                smsPersonRequire,
                itemsSmsPersonRequireLine);
        BaseResponse baseResponse = flag > 0 ? new BaseResponse(true, "编辑成功") : new BaseResponse(
                false, "编辑失败");
        return baseResponse;
    }

    /**
     * 删除记录
     * 
     * @dzmsoftgenerated
     * @return
     */
    @RequiresPermissions("smsPersonRequire:remove")
    @RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse remove(@PathVariable("id") String id) {
        int flag = smsPersonRequireService.deleteByPrimaryKey(id);
        BaseResponse baseResponse = flag >= 0 ? new BaseResponse(true, "删除成功") : new BaseResponse(
                false, "删除失败");
        return baseResponse;
    }

    @RequiresPermissions("smsPersonRequire:enable")
    @RequestMapping(value = "enable/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public BaseResponse enable(@PathVariable("id") String id) {
        int flag = smsPersonRequireService.enable(id);
        BaseResponse baseResponse = flag >= 0 ? new BaseResponse(true, "启用成功") : new BaseResponse(
                false, "启用失败");
        return baseResponse;
    }

    @RequiresPermissions("smsPersonRequire:disable")
    @RequestMapping(value = "disable/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public BaseResponse disable(@PathVariable("id") String id) {
        int flag = smsPersonRequireService.disable(id);
        BaseResponse baseResponse = flag >= 0 ? new BaseResponse(true, "禁用成功") : new BaseResponse(
                false, "禁用失败");
        return baseResponse;
    }
}
