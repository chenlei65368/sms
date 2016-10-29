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
import com.dzmsoft.framework.base.web.mvc.controller.BaseController;
import com.dzmsoft.framework.base.web.mvc.dao.MybatisExample;
import com.dzmsoft.framework.base.web.mvc.pojo.EasyUIPage;
import com.dzmsoft.framework.base.web.mvc.pojo.ShiroUser;
import com.dzmsoft.framework.base.web.mvc.shiro.UserUtil;
import com.dzmsoft.framework.base.web.mvc.view.BaseResponse;
import com.dzmsoft.framework.base.web.mvc.view.Condition;
import com.dzmsoft.framework.file.util.FileUtil;
import com.dzmsoft.sms.base.pojo.SmsMallBrand;
import com.dzmsoft.sms.base.pojo.SmsMallBrandExample;
import com.dzmsoft.sms.base.service.SmsMallBrandService;
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
@RequestMapping("smsMallBrand")
public class SmsMallBrandController extends BaseController{
    @Value("${upload.path}")
    private String uploadPath;
    @Value("${image.maxsize}")
    private int image_maxsize;
    @Value("${img.ratio.three}")
    private double img_ratio_three;
	@Autowired
	private SmsMallBrandService smsMallBrandService;
	@Autowired
	private SmsSupplierSerivice smsSupplierSerivice;
	/**
	 * 显示主列表页面
	 * @dzmsoftgenerated 2016-05-04 17:10:32
	 * @return
	 */
	@RequiresPermissions("smsMallBrand:find")
	@RequestMapping(value = "show", method = RequestMethod.GET)
	public String show(){
		return "modules/base/smsMallBrandList";
	}
	
	@RequestMapping(value = "showDialog", method = RequestMethod.GET)
	public String showDialog(){
	    return "modules/base/smsMallBrandDialog";
	}
	
	/**
	 * 主列表中查询
	 * @dzmsoftgenerated 2016-05-04 17:10:32
	 * @return
	 */
	@RequiresPermissions("smsMallBrand:find")
	@RequestMapping(value = "find", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> find(HttpServletRequest request){
		List<Condition> conditions = Condition.buildFromHttpRequest(request);
		ShiroUser user=UserUtil.getCurrentShiroUser();
        OimSupplier oimSupplier = smsSupplierSerivice.selectByUcsId(user.getId());
        conditions.add(new Condition("eqs_supplier",oimSupplier!=null?oimSupplier.getId():"-1"));
		EasyUIPage easyUIPage = new EasyUIPage(request);
		MybatisExample mybatisExample = MybatisExample.getInstance();
		SmsMallBrandExample example = (SmsMallBrandExample)mybatisExample.buildExampleByCondition(conditions, easyUIPage, SmsMallBrandExample.class.getName());
		PageList<SmsMallBrand> smsMallBrands = smsMallBrandService.selectByExample(example, new PageBounds(easyUIPage.getPage(),
                easyUIPage.getRows()));
		return getEasyUIGrid(smsMallBrands);
	}
	
	/**
	 * 跳转到新增页面
	 * @dzmsoftgenerated 2016-05-04 17:10:32
	 * @return
	 */
	@RequiresPermissions("smsMallBrand:add")
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String addForm(Model model){
	    ShiroUser user=UserUtil.getCurrentShiroUser();
        OimSupplier oimSupplier = smsSupplierSerivice.selectByUcsId(user.getId());
        if (oimSupplier != null){
            model.addAttribute("supplierType", oimSupplier.getSupplierType());
        }
		model.addAttribute("smsMallBrand", new SmsMallBrand());
		model.addAttribute("action", "add");
		return "modules/base/smsMallBrandForm";
	}
	
	/**
	 * 新增记录
	 * @dzmsoftgenerated 2016-05-04 17:10:32
	 * @return
	 */
	@RequiresPermissions("smsMallBrand:add")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse add(SmsMallBrand smsMallBrand , MultipartFile logoPicName){
	    ShiroUser user=UserUtil.getCurrentShiroUser();
        OimSupplier oimSupplier = smsSupplierSerivice.selectByUcsId(user.getId());
        if (oimSupplier == null){
            return new BaseResponse(false,"新增失败，当前用户不存在对应的供应商");
        }
        smsMallBrand.setSupplier(oimSupplier.getId());
        try {
            saveFile(smsMallBrand, logoPicName);
        } catch (FileException e) {
            return new BaseResponse(false, e.getMessage());
        }
		int flag = smsMallBrandService.insertSelective(smsMallBrand);
		BaseResponse baseResponse = flag>0?new BaseResponse(true,"新增成功"):new BaseResponse(false, "新增失败");
		return baseResponse;
	}
	
	private void saveFile(SmsMallBrand smsMallBrand , MultipartFile logoPicName) throws FileException {
        if (logoPicName != null && logoPicName.getSize() > 0) {
            String picName = logoPicName.getOriginalFilename();
            String fileName = FileUtil.spliceFileName(picName, true, true);
            FileUtil.uploadImage(logoPicName, uploadPath, fileName, image_maxsize,img_ratio_three);
            smsMallBrand.setLogoPic(fileName);
        }
    }
	
	/**
	 * 跳转到编辑页面
	 * @dzmsoftgenerated 2016-05-04 17:10:32
	 * @return
	 */
	@RequiresPermissions("smsMallBrand:edit")
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String editForm(@PathVariable("id") String id,Model model){
	    ShiroUser user=UserUtil.getCurrentShiroUser();
        OimSupplier oimSupplier = smsSupplierSerivice.selectByUcsId(user.getId());
        if (oimSupplier != null){
            model.addAttribute("supplierType", oimSupplier.getSupplierType());
        }
		SmsMallBrand smsMallBrand = smsMallBrandService.selectByPrimaryKey(id);
		model.addAttribute("smsMallBrand", smsMallBrand);
		model.addAttribute("action", "edit");
		return "modules/base/smsMallBrandForm";
	}
	
	/**
	 * 跳转到查看页面
	 * @dzmsoftgenerated 2016-05-04 17:10:32
	 * @return
	 */
	@RequiresPermissions("smsMallBrand:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String viewForm(@PathVariable("id") String id,Model model){
		SmsMallBrand smsMallBrand = smsMallBrandService.selectByPrimaryKey(id);
		model.addAttribute("smsMallBrand", smsMallBrand);
		model.addAttribute("action", "view");
		return "modules/base/smsMallBrandForm";
	}
	
	/**
	 * 编辑记录
	 * @dzmsoftgenerated 2016-05-04 17:10:32
	 * @return
	 */
	@RequiresPermissions("smsMallBrand:edit")
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse edit(SmsMallBrand smsMallBrand, MultipartFile logoPicName){
	    try {
            saveFile(smsMallBrand, logoPicName);
        } catch (FileException e) {
            return new BaseResponse(false, e.getMessage());
        }
		int flag = smsMallBrandService.updateByPrimaryKeySelective(smsMallBrand);
		BaseResponse baseResponse = flag>0?new BaseResponse(true,"编辑成功"):new BaseResponse(false, "编辑失败");
		return baseResponse;
	}
	
	/**
	 * 删除记录
	 * @dzmsoftgenerated 2016-05-04 17:10:32
	 * @return
	 */
	@RequiresPermissions("smsMallBrand:remove")
	@RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse remove(@PathVariable("id") String id){
		int flag = smsMallBrandService.deleteByPrimaryKey(id);
		BaseResponse baseResponse = flag>=0?new BaseResponse(true,"删除成功"):new BaseResponse(false, "删除失败");
		return baseResponse;
	}
}
