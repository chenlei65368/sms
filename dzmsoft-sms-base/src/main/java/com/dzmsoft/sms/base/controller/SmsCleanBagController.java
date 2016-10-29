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
import com.dzmsoft.sms.base.pojo.SmsCleanBag;
import com.dzmsoft.sms.base.pojo.SmsCleanBagExample;
import com.dzmsoft.sms.base.pojo.SmsCleanBagLine;
import com.dzmsoft.sms.base.service.SmsCleanBagLineService;
import com.dzmsoft.sms.base.service.SmsCleanBagService;
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
@RequestMapping("smsCleanBag")
public class SmsCleanBagController extends BaseController {
    @Autowired
    private SmsCleanBagService smsCleanBagService;

    @Autowired
    private SmsCleanBagLineService smsCleanBagLineService;
    @Autowired
    private SmsSupplierSerivice smsSupplierSerivice;

    /**
     * 显示主列表页面
     * 
     * @dzmsoftgenerated
     * @return
     */
    @RequiresPermissions("smsCleanBag:find")
    @RequestMapping(value = "show", method = RequestMethod.GET)
    public String show() {
        return "modules/base/smsCleanBagList";
    }

    /**
     * 主列表中查询
     * 
     * @dzmsoftgenerated
     * @return
     */
    @RequiresPermissions("smsCleanBag:find")
    @RequestMapping(value = "find", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> find(HttpServletRequest request) {
        List<Condition> conditions = Condition.buildFromHttpRequest(request);
        ShiroUser user=UserUtil.getCurrentShiroUser();
        OimSupplier oimSupplier = smsSupplierSerivice.selectByUcsId(user.getId());
        conditions.add(new Condition("eqs_supplier",oimSupplier!=null?oimSupplier.getId():"-1"));
        EasyUIPage easyUIPage = new EasyUIPage(request);
        MybatisExample mybatisExample = MybatisExample.getInstance();
        SmsCleanBagExample example = (SmsCleanBagExample) mybatisExample.buildExampleByCondition(
                conditions,
                easyUIPage,
                SmsCleanBagExample.class.getName());
        PageList<SmsCleanBag> smsCleanBags = smsCleanBagService.selectByExample(
                example,
                new PageBounds(easyUIPage.getPage(), easyUIPage.getRows()));
        return getEasyUIGrid(smsCleanBags);
    }

    /**
     * 跳转到新增页面
     * 
     * @dzmsoftgenerated
     * @return
     */
    @RequiresPermissions("smsCleanBag:add")
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addForm(Model model) {
        model.addAttribute("smsCleanBag", new SmsCleanBag());
        model.addAttribute("action", "add");
        return "modules/base/smsCleanBagForm";
    }

    /**
     * 新增记录
     * 
     * @dzmsoftgenerated
     * @return
     */
    @RequiresPermissions("smsCleanBag:add")
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse add(SmsCleanBag smsCleanBag, String datasSmsCleanBagLine) {
        Gson gson = new Gson();
        datasSmsCleanBagLine = StringUtil.unescapeHtml(datasSmsCleanBagLine);
        List<SmsCleanBagLine> itemsSmsCleanBagLine = gson.fromJson(
                datasSmsCleanBagLine,
                new TypeToken<List<SmsCleanBagLine>>() {
                }.getType());
        int flag = smsCleanBagService.insertContainDetails(smsCleanBag, itemsSmsCleanBagLine);
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
    @RequiresPermissions("smsCleanBag:edit")
    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String editForm(@PathVariable("id") String id, Model model) {
        SmsCleanBag smsCleanBag = smsCleanBagService.selectByPrimaryKey(id);
        model.addAttribute("smsCleanBag", smsCleanBag);
        model.addAttribute("action", "edit");
        List<SmsCleanBagLine> smsCleanBagLine = smsCleanBagLineService.selectByCleanBagId(id);
        if (!CheckEmptyUtil.isEmpty(smsCleanBagLine)) {
            Gson gson = new Gson();
            String datasSmsCleanBagLine = gson.toJson(smsCleanBagLine);
            model.addAttribute("datasSmsCleanBagLine", StringUtil.escapeHtml(datasSmsCleanBagLine));
        }
        return "modules/base/smsCleanBagForm";
    }

    /**
     * 跳转到查看页面
     * 
     * @dzmsoftgenerated
     * @return
     */
    @RequiresPermissions("smsCleanBag:view")
    @RequestMapping(value = "view/{id}", method = RequestMethod.GET)
    public String viewForm(@PathVariable("id") String id, Model model) {
        SmsCleanBag smsCleanBag = smsCleanBagService.selectByPrimaryKey(id);
        model.addAttribute("smsCleanBag", smsCleanBag);
        model.addAttribute("action", "view");
        List<SmsCleanBagLine> smsCleanBagLine = smsCleanBagLineService.selectByCleanBagId(id);
        if (!CheckEmptyUtil.isEmpty(smsCleanBagLine)) {
            Gson gson = new Gson();
            String datasSmsCleanBagLine = gson.toJson(smsCleanBagLine);
            model.addAttribute("datasSmsCleanBagLine", StringUtil.escapeHtml(datasSmsCleanBagLine));
        }
        return "modules/base/smsCleanBagForm";
    }

    /**
     * 编辑记录
     * 
     * @dzmsoftgenerated
     * @return
     */
    @RequiresPermissions("smsCleanBag:edit")
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse edit(SmsCleanBag smsCleanBag, String datasSmsCleanBagLine) {
        Gson gson = new Gson();
        datasSmsCleanBagLine = StringUtil.unescapeHtml(datasSmsCleanBagLine);
        List<SmsCleanBagLine> itemsSmsCleanBagLine = gson.fromJson(
                datasSmsCleanBagLine,
                new TypeToken<List<SmsCleanBagLine>>() {
                }.getType());
        int flag = smsCleanBagService.updateContainDetails(smsCleanBag, itemsSmsCleanBagLine);
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
    @RequiresPermissions("smsCleanBag:remove")
    @RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse remove(@PathVariable("id") String id) {
        int flag = smsCleanBagService.deleteByPrimaryKey(id);
        BaseResponse baseResponse = flag >= 0 ? new BaseResponse(true, "删除成功") : new BaseResponse(
                false, "删除失败");
        return baseResponse;
    }

    @RequiresPermissions("smsCleanBag:enable")
    @RequestMapping(value = "enable/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public BaseResponse enable(@PathVariable("id") String id) {
        int flag = smsCleanBagService.enable(id);
        BaseResponse baseResponse = flag >= 0 ? new BaseResponse(true, "启用成功") : new BaseResponse(
                false, "启用失败");
        return baseResponse;
    }

    @RequiresPermissions("smsCleanBag:disable")
    @RequestMapping(value = "disable/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public BaseResponse disable(@PathVariable("id") String id) {
        int flag = smsCleanBagService.disable(id);
        BaseResponse baseResponse = flag >= 0 ? new BaseResponse(true, "禁用成功") : new BaseResponse(
                false, "禁用失败");
        return baseResponse;
    }
}
