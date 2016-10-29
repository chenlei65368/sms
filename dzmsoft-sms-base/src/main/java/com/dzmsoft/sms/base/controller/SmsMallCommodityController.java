package com.dzmsoft.sms.base.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dzmsoft.framework.base.exception.FileException;
import com.dzmsoft.framework.base.util.StringUtil;
import com.dzmsoft.framework.base.web.mvc.controller.BaseController;
import com.dzmsoft.framework.base.web.mvc.dao.MybatisExample;
import com.dzmsoft.framework.base.web.mvc.pojo.EasyUIPage;
import com.dzmsoft.framework.base.web.mvc.pojo.ShiroUser;
import com.dzmsoft.framework.base.web.mvc.shiro.UserUtil;
import com.dzmsoft.framework.base.web.mvc.view.BaseResponse;
import com.dzmsoft.framework.base.web.mvc.view.Condition;
import com.dzmsoft.framework.file.util.FileUtil;
import com.dzmsoft.sms.base.pojo.SmsMallBrand;
import com.dzmsoft.sms.base.pojo.SmsMallCommodity;
import com.dzmsoft.sms.base.pojo.SmsMallCommodityExample;
import com.dzmsoft.sms.base.service.SmsMallBrandService;
import com.dzmsoft.sms.base.service.SmsMallCommodityService;
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
@RequestMapping("smsMallCommodity")
public class SmsMallCommodityController extends BaseController{
    @Value("${upload.path}")
    private String uploadPath;
    @Value("${image.maxsize}")
    private int image_maxsize;
    @Value("${img.ratio.three}")
    private double img_ratio_three;
	@Autowired
	private SmsMallCommodityService smsMallCommodityService;
	@Autowired
	private SmsSupplierSerivice smsSupplierSerivice;
	@Autowired
	private SmsMallBrandService smsMallBrandService;

	/**
	 * 显示主列表页面
	 * @dzmsoftgenerated 2016-05-04 17:10:32
	 * @return
	 */
	@RequiresPermissions("smsMallCommodity:find")
	@RequestMapping(value = "show", method = RequestMethod.GET)
	public String show(){
		return "modules/base/smsMallCommodityList";
	}
	
	/**
	 * 主列表中查询
	 * @dzmsoftgenerated 2016-05-04 17:10:32
	 * @return
	 */
	@RequiresPermissions("smsMallCommodity:find")
	@RequestMapping(value = "find", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> find(HttpServletRequest request){
		List<Condition> conditions = Condition.buildFromHttpRequest(request);
		ShiroUser user=UserUtil.getCurrentShiroUser();
        OimSupplier oimSupplier = smsSupplierSerivice.selectByUcsId(user.getId());
        conditions.add(new Condition("eqs_supplier",oimSupplier!=null?oimSupplier.getId():"-1"));
		EasyUIPage easyUIPage = new EasyUIPage(request);
		MybatisExample mybatisExample = MybatisExample.getInstance();
		SmsMallCommodityExample example = (SmsMallCommodityExample)mybatisExample.buildExampleByCondition(conditions, easyUIPage, SmsMallCommodityExample.class.getName());
		PageList<SmsMallCommodity> smsMallCommoditys = smsMallCommodityService.selectByExample(example, new PageBounds(easyUIPage.getPage(),
                easyUIPage.getRows()));
		return getEasyUIGrid(smsMallCommoditys);
	}
	
	/**
	 * 跳转到新增页面
	 * @dzmsoftgenerated 2016-05-04 17:10:32
	 * @return
	 */
	@RequiresPermissions("smsMallCommodity:add")
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String addForm(Model model){
		model.addAttribute("smsMallCommodity", new SmsMallCommodity());
		model.addAttribute("action", "add");
		return "modules/base/smsMallCommodityForm";
	}
	
	/**
	 * 新增记录
	 * @dzmsoftgenerated 2016-05-04 17:10:32
	 * @return
	 */
	@RequiresPermissions("smsMallCommodity:add")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse add(SmsMallCommodity smsMallCommodity,MultipartFile logoPicName){
	    ShiroUser user=UserUtil.getCurrentShiroUser();
        OimSupplier oimSupplier = smsSupplierSerivice.selectByUcsId(user.getId());
        if (oimSupplier == null){
            return new BaseResponse(false,"新增失败，当前用户不存在对应的供应商");
        }
        try {
            saveFile(smsMallCommodity,logoPicName);
        } catch (FileException e) {
            return new BaseResponse(false, e.getMessage());
        }
        smsMallCommodity.setDetail(StringUtil.unescapeHtml(smsMallCommodity.getDetail()));
        smsMallCommodity.setSupplier(oimSupplier.getId());
        smsMallCommodity.setCity(oimSupplier.getRegCity());
		int flag = smsMallCommodityService.insertSelective(smsMallCommodity);
		BaseResponse baseResponse = flag>0?new BaseResponse(true,"新增成功"):new BaseResponse(false, "新增失败");
		return baseResponse;
	}
	
	private void saveFile(SmsMallCommodity smsMallCommodity , MultipartFile logoPicName) throws FileException {
        if (logoPicName != null && logoPicName.getSize() > 0) {
            String picName = logoPicName.getOriginalFilename();
            String fileName = FileUtil.spliceFileName(picName, true, true);
            FileUtil.uploadImage(logoPicName, uploadPath, fileName, image_maxsize,img_ratio_three);
            smsMallCommodity.setLogoPic(fileName);
        }
    }
	
	/**
	 * 跳转到编辑页面
	 * @dzmsoftgenerated 2016-05-04 17:10:32
	 * @return
	 */
	@RequiresPermissions("smsMallCommodity:edit")
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String editForm(@PathVariable("id") String id,Model model){
		SmsMallCommodity smsMallCommodity = smsMallCommodityService.selectByPrimaryKey(id);
		smsMallCommodity.setDetail(StringUtil.escapeHtml(smsMallCommodity.getDetail()));
		model.addAttribute("smsMallCommodity", smsMallCommodity);
		model.addAttribute("action", "edit");
		// 品牌信息
		SmsMallBrand smsMallBrand = smsMallBrandService.selectByPrimaryKey(smsMallCommodity.getBrandId());
		model.addAttribute("brandName", smsMallBrand.getName());
		return "modules/base/smsMallCommodityForm";
	}
	
	/**
	 * 跳转到查看页面
	 * @dzmsoftgenerated 2016-05-04 17:10:32
	 * @return
	 */
	@RequiresPermissions("smsMallCommodity:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String viewForm(@PathVariable("id") String id,Model model){
		SmsMallCommodity smsMallCommodity = smsMallCommodityService.selectByPrimaryKey(id);
		model.addAttribute("smsMallCommodity", smsMallCommodity);
		model.addAttribute("action", "view");
		return "modules/base/smsMallCommodityForm";
	}
	
	/**
	 * 编辑记录
	 * @dzmsoftgenerated 2016-05-04 17:10:32
	 * @return
	 */
	@RequiresPermissions("smsMallCommodity:edit")
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse edit(SmsMallCommodity smsMallCommodity,MultipartFile logoPicName){
	    try {
            saveFile(smsMallCommodity,logoPicName);
        } catch (FileException e) {
            return new BaseResponse(false, e.getMessage());
        }
	    smsMallCommodity.setDetail(StringUtil.unescapeHtml(smsMallCommodity.getDetail()));
		int flag = smsMallCommodityService.updateByPrimaryKeySelective(smsMallCommodity);
		BaseResponse baseResponse = flag>0?new BaseResponse(true,"编辑成功"):new BaseResponse(false, "编辑失败");
		return baseResponse;
	}
	
	/**
	 * 删除记录
	 * @dzmsoftgenerated 2016-05-04 17:10:32
	 * @return
	 */
	@RequiresPermissions("smsMallCommodity:remove")
	@RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse remove(@PathVariable("id") String id){
		int flag = smsMallCommodityService.deleteByPrimaryKey(id);
		BaseResponse baseResponse = flag>=0?new BaseResponse(true,"删除成功"):new BaseResponse(false, "删除失败");
		return baseResponse;
	}
	
	/**
	 * 上架
	 * @param id
	 * @return
	 */
	@RequiresPermissions("smsMallCommodity:online")
    @RequestMapping(value = "online/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public BaseResponse online(@PathVariable("id") String id){
        int flag = smsMallCommodityService.online(id);
        BaseResponse baseResponse = flag>=0?new BaseResponse(true,"上架成功"):new BaseResponse(false, "上架失败");
        return baseResponse;
    }
	
	/**
     * 上架
     * @param id
     * @return
     */
    @RequiresPermissions("smsMallCommodity:offline")
    @RequestMapping(value = "offline/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public BaseResponse offline(@PathVariable("id") String id){
        int flag = smsMallCommodityService.offline(id);
        BaseResponse baseResponse = flag>=0?new BaseResponse(true,"下架成功"):new BaseResponse(false, "下架失败");
        return baseResponse;
    }
}
