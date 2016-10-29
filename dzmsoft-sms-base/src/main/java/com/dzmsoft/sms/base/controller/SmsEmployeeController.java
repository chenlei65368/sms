package com.dzmsoft.sms.base.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dzmsoft.acs.inner.dto.RegisterUserDto;
import com.dzmsoft.acs.inner.service.UcsService;
import com.dzmsoft.framework.base.exception.FileException;
import com.dzmsoft.framework.base.web.mvc.controller.BaseController;
import com.dzmsoft.framework.base.web.mvc.dao.MybatisExample;
import com.dzmsoft.framework.base.web.mvc.pojo.EasyUIPage;
import com.dzmsoft.framework.base.web.mvc.pojo.ShiroUser;
import com.dzmsoft.framework.base.web.mvc.shiro.UserUtil;
import com.dzmsoft.framework.base.web.mvc.view.BaseResponse;
import com.dzmsoft.framework.base.web.mvc.view.Condition;
import com.dzmsoft.framework.file.util.FileUtil;
import com.dzmsoft.sms.base.common.SmsBaseConstant;
import com.dzmsoft.sms.base.pojo.SmsEmployee;
import com.dzmsoft.sms.base.pojo.SmsEmployeeExample;
import com.dzmsoft.sms.base.pojo.udf.SmsEmployeeScheduleUdfExample;
import com.dzmsoft.sms.base.service.SmsEmployeeService;
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
@RequestMapping("smsEmployee")
public class SmsEmployeeController extends BaseController{
    
    private static final Logger logger = LoggerFactory.getLogger(SmsEmployeeController.class);
    
    @Value("${upload.path}")
    private String uploadPath;
    @Value("${image.maxsize}")
    private int image_maxsize;
    @Value("${img.ratio.three}")
    private double img_ratio_three;
	@Autowired
	private SmsEmployeeService smsEmployeeService;
	@Autowired
	private UcsService ucsService;
	@Autowired
	private SmsSupplierSerivice smsSupplierSerivice;

	/**
	 * 显示主列表页面
	 * @dzmsoftgenerated 2016-05-04 17:10:32
	 * @return
	 */
	@RequiresPermissions("smsEmployee:find")
	@RequestMapping(value = "show", method = RequestMethod.GET)
	public String show(){
		return "modules/base/smsEmployeeList";
	}
	
	@RequestMapping(value = "showDialog", method = RequestMethod.GET)
	public String showDialog(){
	    return "modules/base/smsEmployeeDialog";
	}
	
	/**
	 * 显示多选员工
	 * @return
	 */
	@RequestMapping(value = "showMultiEmployeeDialog", method = RequestMethod.GET)
	public String showMultiEmployeeDialog(){
	    return "modules/base/multiEmployeeDialog";
	}
	
	/**
	 * 查询未排班的员工
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "findNoSchedule", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> findNoSchedule(HttpServletRequest request){
        List<Condition> conditions = Condition.buildFromHttpRequest(request);
        // 员工状态为在岗
        conditions.add(new Condition("eqs_status__SmsEmployeeExample", SmsBaseConstant.EmployeeStatus.IN_JOB));
        // 供应商查询条件
        ShiroUser user=UserUtil.getCurrentShiroUser();
        OimSupplier oimSupplier = smsSupplierSerivice.selectByUcsId(user.getId());
        conditions.add(new Condition("eqs_supplier__SmsEmployeeExample",oimSupplier!=null?oimSupplier.getId():"-1"));
        //
        EasyUIPage easyUIPage = new EasyUIPage(request);
        MybatisExample mybatisExample = MybatisExample.getInstance();
        SmsEmployeeScheduleUdfExample example = (SmsEmployeeScheduleUdfExample)mybatisExample.buildExampleByCondition(conditions, easyUIPage, SmsEmployeeScheduleUdfExample.class.getName());
        example.getSmsEmployeeScheduleExample().createCriteria().andIdIsNull(); // 排班记录为空
        PageList<SmsEmployee> smsEmployees = smsEmployeeService.selectNoSchedule(example, new PageBounds(easyUIPage.getPage(),
                easyUIPage.getRows()));
        return getEasyUIGrid(smsEmployees);
    }
	
	/**
	 * 主列表中查询
	 * @dzmsoftgenerated 2016-05-04 17:10:32
	 * @return
	 */
	@RequiresPermissions("smsEmployee:find")
	@RequestMapping(value = "find", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> find(HttpServletRequest request){
		List<Condition> conditions = Condition.buildFromHttpRequest(request);
		ShiroUser user=UserUtil.getCurrentShiroUser();
        OimSupplier oimSupplier = smsSupplierSerivice.selectByUcsId(user.getId());
		conditions.add(new Condition("eqs_supplier",oimSupplier!=null?oimSupplier.getId():"-1"));
		EasyUIPage easyUIPage = new EasyUIPage(request);
		MybatisExample mybatisExample = MybatisExample.getInstance();
		SmsEmployeeExample example = (SmsEmployeeExample)mybatisExample.buildExampleByCondition(conditions, easyUIPage, SmsEmployeeExample.class.getName());
		PageList<SmsEmployee> smsEmployees = smsEmployeeService.selectByExample(example, new PageBounds(easyUIPage.getPage(),
                easyUIPage.getRows()));
		return getEasyUIGrid(smsEmployees);
	}
	
	/**
	 * 查询
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "find", method = RequestMethod.GET)
    @ResponseBody
	public List<SmsEmployee> findList(HttpServletRequest request){
	    List<Condition> conditions = Condition.buildFromHttpRequest(request);
        ShiroUser user=UserUtil.getCurrentShiroUser();
        OimSupplier oimSupplier = smsSupplierSerivice.selectByUcsId(user.getId());
        conditions.add(new Condition("eqs_supplier",oimSupplier!=null?oimSupplier.getId():"-1"));
        EasyUIPage easyUIPage = new EasyUIPage(request);
        MybatisExample mybatisExample = MybatisExample.getInstance();
        SmsEmployeeExample example = (SmsEmployeeExample)mybatisExample.buildExampleByCondition(conditions, easyUIPage, SmsEmployeeExample.class.getName());
        PageList<SmsEmployee> smsEmployees = smsEmployeeService.selectByExample(example, new PageBounds(easyUIPage.getPage(),
                easyUIPage.getRows()));
        return smsEmployees;
	}
	
	/**
	 * 跳转到新增页面
	 * @dzmsoftgenerated 2016-05-04 17:10:32
	 * @return
	 */
	@RequiresPermissions("smsEmployee:add")
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String addForm(Model model){
		model.addAttribute("smsEmployee", new SmsEmployee());
		model.addAttribute("action", "add");
		return "modules/base/smsEmployeeForm";
	}
	
	/**
	 * 新增记录
	 * @dzmsoftgenerated 2016-05-04 17:10:32
	 * @return
	 */
	@RequiresPermissions("smsEmployee:add")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse add(SmsEmployee smsEmployee,MultipartFile headPortraitPicName , MultipartFile healthCertificatePicName){
	    ShiroUser user=UserUtil.getCurrentShiroUser();
        OimSupplier oimSupplier = smsSupplierSerivice.selectByUcsId(user.getId());
        if (oimSupplier == null){
            return new BaseResponse(false,"新增失败，当前用户不存在对应的供应商");
        }
	 // 通过手机号向UCS发起注册账号请求
        RegisterUserDto registerUserDto = ucsService.commonRegister(
                smsEmployee.getMobile(),
                smsEmployee.getUserType(),
                smsEmployee.getName(),
                smsEmployee.getMobile());
        if (registerUserDto.isSuccess()){
            try {
                saveFile(smsEmployee, headPortraitPicName, healthCertificatePicName);
            } catch (FileException e) {
                return new BaseResponse(false, e.getMessage());
            }
            smsEmployee.setUcsId(registerUserDto.getUcsId());
            int flag = smsEmployeeService.insertSelective(smsEmployee);
            BaseResponse baseResponse = flag>0?new BaseResponse(true,"新增成功"):new BaseResponse(false, "新增失败");
            return baseResponse;
        } else{
            return new BaseResponse(false, registerUserDto.getMsg());
        }
	}
	
	/**
	 * 跳转到编辑页面
	 * @dzmsoftgenerated 2016-05-04 17:10:32
	 * @return
	 */
	@RequiresPermissions("smsEmployee:edit")
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String editForm(@PathVariable("id") String id,Model model){
		SmsEmployee smsEmployee = smsEmployeeService.selectByPrimaryKey(id);
		model.addAttribute("smsEmployee", smsEmployee);
		model.addAttribute("action", "edit");
		return "modules/base/smsEmployeeForm";
	}
	
	/**
	 * 跳转到查看页面
	 * @dzmsoftgenerated 2016-05-04 17:10:32
	 * @return
	 */
	@RequiresPermissions("smsEmployee:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String viewForm(@PathVariable("id") String id,Model model){
		SmsEmployee smsEmployee = smsEmployeeService.selectByPrimaryKey(id);
		model.addAttribute("smsEmployee", smsEmployee);
		model.addAttribute("action", "view");
		return "modules/base/smsEmployeeForm";
	}
	
	/**
	 * 编辑记录
	 * @dzmsoftgenerated 2016-05-04 17:10:32
	 * @return
	 */
	@RequiresPermissions("smsEmployee:edit")
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse edit(SmsEmployee smsEmployee,MultipartFile headPortraitPicName , MultipartFile healthCertificatePicName){
	    try {
            saveFile(smsEmployee, headPortraitPicName, healthCertificatePicName);
        } catch (FileException e) {
            return new BaseResponse(false, e.getMessage());
        }
		int flag = smsEmployeeService.updateByPrimaryKeySelective(smsEmployee);
		BaseResponse baseResponse = flag>0?new BaseResponse(true,"编辑成功"):new BaseResponse(false, "编辑失败");
		return baseResponse;
	}
	
	private void saveFile(SmsEmployee smsEmployee , MultipartFile headPortraitPicName , MultipartFile healthCertificatePicName) throws FileException {
        if (headPortraitPicName != null && headPortraitPicName.getSize() > 0) {
            String picName = headPortraitPicName.getOriginalFilename();
            String fileName = FileUtil.spliceFileName(picName, true, true);
            FileUtil.uploadImage(headPortraitPicName, uploadPath, fileName, image_maxsize,img_ratio_three);
            smsEmployee.setHeadPortraitPic(fileName);
        }
        if (healthCertificatePicName != null && healthCertificatePicName.getSize() > 0) {
            String picName = healthCertificatePicName.getOriginalFilename();
            String fileName = FileUtil.spliceFileName(picName, true, true);
            FileUtil.uploadImage(healthCertificatePicName, uploadPath, fileName, image_maxsize,img_ratio_three);
            smsEmployee.setHealthCertificatePic(fileName);
        }
    }
	
	/**
	 * 删除记录
	 * @dzmsoftgenerated 2016-05-04 17:10:32
	 * @return
	 */
	@RequiresPermissions("smsEmployee:remove")
	@RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse remove(@PathVariable("id") String id){
		int flag = smsEmployeeService.deleteByPrimaryKey(id);
		BaseResponse baseResponse = flag>=0?new BaseResponse(true,"删除成功"):new BaseResponse(false, "删除失败");
		return baseResponse;
	}
	
	/**
	 * 离职
	 * @param id
	 * @return
	 */
	@RequiresPermissions("smsEmployee:quit")
    @RequestMapping(value = "quit/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public BaseResponse quit(@PathVariable("id") String id){
        int flag = smsEmployeeService.quit(id);
        BaseResponse baseResponse = flag>=0?new BaseResponse(true,"离职成功"):new BaseResponse(false, "离职失败");
        return baseResponse;
    }
}
