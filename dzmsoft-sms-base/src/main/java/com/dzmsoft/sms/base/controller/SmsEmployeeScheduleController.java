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
import com.dzmsoft.framework.base.web.mvc.view.BaseResponse;
import com.dzmsoft.framework.base.web.mvc.view.ComboBoxData;
import com.dzmsoft.framework.base.web.mvc.view.Condition;
import com.dzmsoft.sms.base.common.SmsBaseConstant;
import com.dzmsoft.sms.base.dto.SmsEmployeeScheduleArrangeDto;
import com.dzmsoft.sms.base.pojo.SmsEmployee;
import com.dzmsoft.sms.base.pojo.SmsEmployeeSchedule;
import com.dzmsoft.sms.base.pojo.SmsEmployeeScheduleExample;
import com.dzmsoft.sms.base.pojo.SmsShift;
import com.dzmsoft.sms.base.service.SmsEmployeeScheduleService;
import com.dzmsoft.sms.base.service.SmsShiftService;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Copyright (C), dzmsoft Co., Ltd
 * @author dzmsoft
 * @dzmsoftgenerated 2016-05-15 09:42:23
 *
 * @version 1.0
 */
@Controller
@RequestMapping("smsEmployeeSchedule")
public class SmsEmployeeScheduleController extends BaseController{
	@Autowired
	private SmsEmployeeScheduleService smsEmployeeScheduleService;
	@Autowired
    private SmsShiftService smsShiftService;
	@Autowired
	private Gson gson;

	/**
	 * 显示主列表页面
	 * @dzmsoftgenerated 2016-05-15 09:42:23
	 * @return
	 */
	@RequiresPermissions("smsEmployeeSchedule:find")
	@RequestMapping(value = "show", method = RequestMethod.GET)
	public String show(){
		return "modules/base/smsEmployeeScheduleList";
	}
	
	@RequestMapping(value = "showDialog", method = RequestMethod.GET)
	public String showDialog(Model model, HttpServletRequest request){
	    if (!CheckEmptyUtil.isEmpty(request.getParameter("appointmentTime"))){
	        model.addAttribute("appointmentTime", request.getParameter("appointmentTime"));
	    }
	    if (!CheckEmptyUtil.isEmpty(request.getParameter("times"))){
            model.addAttribute("times", request.getParameter("times"));
        }
	    return "modules/base/smsEmployeeScheduleDialog";
	}
	
	/**
	 * 打开排班的form
	 * @return
	 */
	@RequiresPermissions("smsEmployeeSchedule:arrange")
    @RequestMapping(value = "showArrangeForm", method = RequestMethod.GET)
	public String showArrangeForm(Model model){
	    model.addAttribute("smsEmployeeScheduleArrangeDto", new SmsEmployeeScheduleArrangeDto());
	    return "modules/base/smsEmployeeScheduleArrangeForm";
	}
	
	@RequiresPermissions("smsEmployeeSchedule:arrange")
	@RequestMapping(value = "arrange", method = RequestMethod.POST)
    @ResponseBody
	public BaseResponse arrange(SmsEmployeeScheduleArrangeDto smsEmployeeScheduleArrangeDto, String empDetail, String dateDetail){
	    SmsShift smsShift = smsShiftService.selectByPrimaryKey(smsEmployeeScheduleArrangeDto.getShift());
	    if (smsShift == null){
	        return new BaseResponse(false, "新增失败，班次没有维护");
	    }
	    empDetail = StringUtil.unescapeHtml(empDetail);
	    List<SmsEmployee> smsEmployees = gson.fromJson(empDetail, new TypeToken<List<SmsEmployee>>() {}.getType());
	    if (CheckEmptyUtil.isEmpty(smsEmployees)){
	        return new BaseResponse(false, "新增失败，没有排班的员工");
	    }
	    dateDetail = StringUtil.unescapeHtml(dateDetail);
	    List<ComboBoxData> dateBoxDatas = gson.fromJson(dateDetail, new TypeToken<List<ComboBoxData>>() {}.getType());
	    if (CheckEmptyUtil.isEmpty(dateBoxDatas)){
	        return new BaseResponse(false, "新增失败，没有选择排班的日期");
	    }
	    int flag = smsEmployeeScheduleService.arrange(smsEmployees, dateBoxDatas, smsShift);
	    BaseResponse baseResponse = flag>0?new BaseResponse(true,"新增成功"):new BaseResponse(false, "新增失败");
        return baseResponse;
	}
	
	/**
	 * 主列表中查询
	 * @dzmsoftgenerated 2016-05-15 09:42:23
	 * @return
	 */
	@RequiresPermissions("smsEmployeeSchedule:find")
	@RequestMapping(value = "find", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> find(HttpServletRequest request){
		List<Condition> conditions = Condition.buildFromHttpRequest(request);
		getEmployeeScheduleDialogCondtion(request, conditions);//  排班人员对话框的查询条件
		EasyUIPage easyUIPage = new EasyUIPage(request);
		MybatisExample mybatisExample = MybatisExample.getInstance();
		SmsEmployeeScheduleExample example = (SmsEmployeeScheduleExample)mybatisExample.buildExampleByCondition(conditions, easyUIPage, SmsEmployeeScheduleExample.class.getName());
		PageList<SmsEmployeeSchedule> smsEmployeeSchedules = smsEmployeeScheduleService.selectByExample(example, new PageBounds(easyUIPage.getPage(),
                easyUIPage.getRows()));
		return getEasyUIGrid(smsEmployeeSchedules);
	}
	
	/**
	 * 排班人员对话框的查询条件
	 * @param request
	 * @param conditions
	 */
	private void getEmployeeScheduleDialogCondtion(HttpServletRequest request,List<Condition> conditions ){
	    if (!CheckEmptyUtil.isEmpty(request.getParameter("appointmentTime"))){
            String[] timeStrs = request.getParameter("appointmentTime").split(" ");
            conditions.add(new Condition("eqd_day", timeStrs[0]));
            if (!CheckEmptyUtil.isEmpty(request.getParameter("times"))){
                String[] times = timeStrs[1].split(":");
                Integer appointmentTime = Integer.valueOf(times[0]);
                Condition condition = getHourCondition(appointmentTime);
                if (condition != null){
                    conditions.add(condition);
                }
                // 根据
                appointmentTime += Integer.parseInt(request.getParameter("times"));
                condition = getHourCondition(appointmentTime);
                if (condition != null){
                    conditions.add(condition);
                }
            }
        }
	}
	
	private Condition getHourCondition(Integer appointmentTime){
	    Condition condition = null;
	    switch(appointmentTime){
        case 1:
            break;
        case 2:
            break;
        case 3:
            break;
        case 4:
            break;
        case 5:
            break;
        case 6:
            condition = new Condition("eqs_six",SmsBaseConstant.Shift.IDEL);
            break;
        case 7:
            condition = new Condition("eqs_seven",SmsBaseConstant.Shift.IDEL);
            break;
        case 8:
            condition = new Condition("eqs_eight",SmsBaseConstant.Shift.IDEL);
            break;
        case 9:
            condition = new Condition("eqs_nine",SmsBaseConstant.Shift.IDEL);
            break;
        case 10:
            condition = new Condition("eqs_ten",SmsBaseConstant.Shift.IDEL);
            break;
        case 11:
            condition = new Condition("eqs_eleven",SmsBaseConstant.Shift.IDEL);
            break;
        case 12:
            condition = new Condition("eqs_twelve",SmsBaseConstant.Shift.IDEL);
            break;
        case 13:
            condition = new Condition("eqs_fhirteen",SmsBaseConstant.Shift.IDEL);
            break;
        case 14:
            condition = new Condition("eqs_fourteen",SmsBaseConstant.Shift.IDEL);
            break;
        case 15:
            condition = new Condition("eqs_fifteen",SmsBaseConstant.Shift.IDEL);
            break;
        case 16:
            condition = new Condition("eqs_sixteen",SmsBaseConstant.Shift.IDEL);
            break;
        case 17:
            condition = new Condition("eqs_seventeen",SmsBaseConstant.Shift.IDEL);
            break;
        case 18:
            condition = new Condition("eqs_eighteen",SmsBaseConstant.Shift.IDEL);
            break;
        case 19:
            condition = new Condition("eqs_nineteen",SmsBaseConstant.Shift.IDEL);
            break;
        case 20:
            condition = new Condition("eqs_twenty",SmsBaseConstant.Shift.IDEL);
            break;
        case 21:
            condition = new Condition("eqs_twentyOne",SmsBaseConstant.Shift.IDEL);
            break;
        case 22:
            condition = new Condition("eqs_twentyTwo",SmsBaseConstant.Shift.IDEL);
            break;
        case 23:
            condition = new Condition("eqs_twentyThree",SmsBaseConstant.Shift.IDEL);
            break;
        case 24:
            break;
          default:
              break;
        }
	    return condition;
	}
	
	/**
	 * 跳转到新增页面
	 * @dzmsoftgenerated 2016-05-15 09:42:23
	 * @return
	 */
	@RequiresPermissions("smsEmployeeSchedule:add")
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String addForm(Model model){
		model.addAttribute("smsEmployeeSchedule", new SmsEmployeeSchedule());
		model.addAttribute("action", "add");
		return "modules/base/smsEmployeeScheduleForm";
	}
	
	/**
	 * 新增记录
	 * @dzmsoftgenerated 2016-05-15 09:42:23
	 * @return
	 */
	@RequiresPermissions("smsEmployeeSchedule:add")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse add(SmsEmployeeSchedule smsEmployeeSchedule){
		int flag = smsEmployeeScheduleService.insertSelective(smsEmployeeSchedule);
		BaseResponse baseResponse = flag>0?new BaseResponse(true,"新增成功"):new BaseResponse(false, "新增失败");
		return baseResponse;
	}
	
	/**
	 * 跳转到编辑页面
	 * @dzmsoftgenerated 2016-05-15 09:42:23
	 * @return
	 */
	@RequiresPermissions("smsEmployeeSchedule:edit")
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String editForm(@PathVariable("id") String id,Model model){
		SmsEmployeeSchedule smsEmployeeSchedule = smsEmployeeScheduleService.selectByPrimaryKey(id);
		model.addAttribute("smsEmployeeSchedule", smsEmployeeSchedule);
		model.addAttribute("action", "edit");
		return "modules/base/smsEmployeeScheduleForm";
	}
	
	/**
	 * 跳转到查看页面
	 * @dzmsoftgenerated 2016-05-15 09:42:23
	 * @return
	 */
	@RequiresPermissions("smsEmployeeSchedule:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String viewForm(@PathVariable("id") String id,Model model){
		SmsEmployeeSchedule smsEmployeeSchedule = smsEmployeeScheduleService.selectByPrimaryKey(id);
		model.addAttribute("smsEmployeeSchedule", smsEmployeeSchedule);
		model.addAttribute("action", "view");
		return "modules/base/smsEmployeeScheduleForm";
	}
	
	/**
	 * 编辑记录
	 * @dzmsoftgenerated 2016-05-15 09:42:23
	 * @return
	 */
	@RequiresPermissions("smsEmployeeSchedule:edit")
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse edit(SmsEmployeeSchedule smsEmployeeSchedule){
		int flag = smsEmployeeScheduleService.updateByPrimaryKeySelective(smsEmployeeSchedule);
		BaseResponse baseResponse = flag>0?new BaseResponse(true,"编辑成功"):new BaseResponse(false, "编辑失败");
		return baseResponse;
	}
	
	/**
	 * 删除记录
	 * @dzmsoftgenerated 2016-05-15 09:42:23
	 * @return
	 */
	@RequiresPermissions("smsEmployeeSchedule:remove")
	@RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse remove(@PathVariable("id") String id){
		int flag = smsEmployeeScheduleService.deleteByPrimaryKey(id);
		BaseResponse baseResponse = flag>=0?new BaseResponse(true,"删除成功"):new BaseResponse(false, "删除失败");
		return baseResponse;
	}
}
