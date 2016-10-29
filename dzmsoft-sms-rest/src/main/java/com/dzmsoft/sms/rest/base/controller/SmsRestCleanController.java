package com.dzmsoft.sms.rest.base.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dzmsoft.framework.base.util.CheckEmptyUtil;
import com.dzmsoft.framework.base.util.SpringContextHolder;
import com.dzmsoft.framework.base.util.StringUtil;
import com.dzmsoft.framework.base.web.mvc.controller.BaseController;
import com.dzmsoft.framework.base.web.mvc.controller.validate.ValidateField;
import com.dzmsoft.framework.base.web.mvc.controller.validate.ValidateGroup;
import com.dzmsoft.framework.base.web.mvc.dao.MybatisExample;
import com.dzmsoft.framework.base.web.mvc.pojo.EasyUIPage;
import com.dzmsoft.framework.base.web.mvc.view.Condition;
import com.dzmsoft.framework.base.web.mvc.view.GenericResponse;
import com.dzmsoft.sms.base.common.SmsBaseConstant;
import com.dzmsoft.sms.base.dto.Order;
import com.dzmsoft.sms.base.dto.SmsOrderDto;
import com.dzmsoft.sms.base.pojo.SmsCard;
import com.dzmsoft.sms.base.pojo.SmsCardExample;
import com.dzmsoft.sms.base.pojo.SmsEmployee;
import com.dzmsoft.sms.base.pojo.SmsEmployeeSchedule;
import com.dzmsoft.sms.base.pojo.SmsHouseholdBag;
import com.dzmsoft.sms.base.pojo.SmsHouseholdOrder;
import com.dzmsoft.sms.base.pojo.SmsMallCommodity;
import com.dzmsoft.sms.base.pojo.SmsMallCommodityExample;
import com.dzmsoft.sms.base.pojo.SmsNewhomeOrder;
import com.dzmsoft.sms.base.pojo.SmsOrder;
import com.dzmsoft.sms.base.service.OrderService;
import com.dzmsoft.sms.base.service.SmsCardService;
import com.dzmsoft.sms.base.service.SmsEmployeeScheduleService;
import com.dzmsoft.sms.base.service.SmsEmployeeService;
import com.dzmsoft.sms.base.service.SmsHouseholdOrderService;
import com.dzmsoft.sms.base.service.SmsMallCommodityService;
import com.dzmsoft.sms.base.service.SmsNewhomeOrderService;
import com.dzmsoft.sms.base.service.SmsOrderService;
import com.dzmsoft.sms.rest.base.common.SmsRestBaseConstant;
import com.dzmsoft.sms.rest.base.dto.SmsEmployeeScheduleDto;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RestController
@RequestMapping("rest")
public class SmsRestCleanController extends BaseController{
    @Autowired
    Gson gson = new Gson();
    @Autowired
    private SmsOrderService smsOrderService;
    @Autowired
    private SmsEmployeeService smsEmployeeService;
    @Autowired
    private SmsHouseholdOrderService smsHouseholdOrderService;
    @Autowired
    private SmsNewhomeOrderService smsNewhomeOrderService;
    @Autowired
    private SmsMallCommodityService smsMallCommodityService;
    @Autowired
    private SmsCardService smsCardService;
    @Autowired
    private SmsEmployeeScheduleService smsEmployeeScheduleService;
    /**
     * 获取订单信息
     * @param request
     * @return
     */
    @RequestMapping(value = "clean01/{member}/{type}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public GenericResponse<PageList<SmsOrderDto<Order>>> getSmsOrder(@PathVariable String member, @PathVariable String type, Integer page, Integer rows){
        List<String> statusList = new ArrayList<String>();
        switch(type){
        case SmsRestBaseConstant.OrderType.WAIT_DOOR:
            statusList.add(SmsBaseConstant.OrderStatus.INIT);
            break;
        case SmsRestBaseConstant.OrderType.UNDER_WAY:
            statusList.add(SmsBaseConstant.OrderStatus.GRAB);
            statusList.add(SmsBaseConstant.OrderStatus.SEND);
            statusList.add(SmsBaseConstant.OrderStatus.VISIT);
            break;
        case SmsRestBaseConstant.OrderType.FINISH:
            statusList.add(SmsBaseConstant.OrderStatus.CANCEL);
            statusList.add(SmsBaseConstant.OrderStatus.EVALUATE);
            break;
            default:
                break;
        }
        List<SmsOrder> smsOrders = smsOrderService.selectByMember(member, statusList, new PageBounds(page, rows));
        // 根据订单类型加载附属信息
        PageList<SmsOrderDto<Order>> smsOrderDtos = new PageList<>();
        if (!CheckEmptyUtil.isEmpty(smsOrders)){
            SmsOrderDto<Order> smsOrderDto = null;
            for (SmsOrder smsOrder: smsOrders){
                smsOrderDto = new SmsOrderDto<>();
                smsOrderDto.setSmsOrder(smsOrder);
                OrderService orderService = (OrderService)SpringContextHolder.getBean("orderService"+smsOrder.getOrderType());
                Order order = orderService.selectByOrderId(smsOrder.getId());
                smsOrderDto.setData(order);
                //
                smsOrderDtos.add(smsOrderDto);
            }
        }
        GenericResponse<PageList<SmsOrderDto<Order>>> genericResponse = new GenericResponse<>(true);
        genericResponse.setData(smsOrderDtos);
        return genericResponse;
    }
    
    /**
     * 会员取消订单
     * @param id
     * @return
     */
    @RequestMapping(value = "clean02/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public GenericResponse<String> cancelByMember(@PathVariable String id){
        int flag = smsOrderService.cancelByMember(id);
        GenericResponse<String> genericResponse = flag>0?new GenericResponse<String>(true, "取消成功"):new GenericResponse<String>(true, "取消失败");
        return genericResponse;
    }
    
    /**
     * 抢单
     * @param ucsId
     * @param id
     * @return
     */
    @RequestMapping(value = "clean03/{ucsId}/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public GenericResponse<String> grabHousehold(@PathVariable String ucsId, @PathVariable String id){
        boolean isGrab = smsOrderService.hasGrab(id);
        if (isGrab){
            return new GenericResponse<String>(true, "抢单失败,改单已被抢");
        }
        SmsEmployee smsEmployee = smsEmployeeService.selectByUcsId(ucsId);
        int flag = smsOrderService.grab(id, smsEmployee.getId());
        GenericResponse<String> genericResponse = flag>0?new GenericResponse<String>(true, "抢单成功"):new GenericResponse<String>(true, "抢单失败");
        return genericResponse;
    }
    
    /**
     * 获取保洁员详情，包括排班信息
     * @param id
     * @return
     */
    @RequestMapping(value = "clean04/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public GenericResponse<SmsEmployeeScheduleDto> getCleaner(@PathVariable String id){
        // 获取保洁员详情
        SmsEmployee smsEmployee = smsEmployeeService.selectByPrimaryKey(id);
        // 获取保洁员近5天的排班信息
        List<SmsEmployeeSchedule> smsEmployeeSchedules = smsEmployeeScheduleService.selectNextFiveDays(id);
        SmsEmployeeScheduleDto smsEmployeeScheduleDto = new SmsEmployeeScheduleDto();
        smsEmployeeScheduleDto.setSmsEmployee(smsEmployee);
        smsEmployeeScheduleDto.setSmsEmployeeSchedules(smsEmployeeSchedules);
        GenericResponse<SmsEmployeeScheduleDto> genericResponse = new GenericResponse<SmsEmployeeScheduleDto>(true);
        genericResponse.setData(smsEmployeeScheduleDto);
        return genericResponse;
    }
    
    /**
     * 获取商品清单
     * @param request
     * @return
     */
    @RequestMapping(value = "clean05/{cityId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public GenericResponse<List<SmsMallCommodity>> getSmsMallCommoditys(@PathVariable String cityId, Integer page, Integer rows){
        List<SmsMallCommodity>smsMallCommodities = smsMallCommodityService.selectByCity(cityId, new PageBounds(page, rows));
        GenericResponse<List<SmsMallCommodity>> genericResponse = new GenericResponse<List<SmsMallCommodity>>(true);
        genericResponse.setData(smsMallCommodities);
        return genericResponse;
    }
    
    /**
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "clean06", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public GenericResponse<PageList<SmsCard>> getSmsCard(HttpServletRequest request){
        List<Condition> conditions = Condition.buildFromHttpRequest(request);
        EasyUIPage easyUIPage = new EasyUIPage(request);
        MybatisExample mybatisExample = MybatisExample.getInstance();
        SmsCardExample example = (SmsCardExample)mybatisExample.buildExampleByCondition(conditions, easyUIPage, SmsMallCommodityExample.class.getName());
        PageList<SmsCard> smsCards = smsCardService.selectByExample(example, new PageBounds(easyUIPage.getPage(),
                easyUIPage.getRows()));
        GenericResponse<PageList<SmsCard>> genericResponse = new GenericResponse<PageList<SmsCard>>(true);
        genericResponse.setData(smsCards);
        return genericResponse;
    }
    
    /**
     * 获取商品详情
     * @param id
     * @return
     */
    @RequestMapping(value = "clean07/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public GenericResponse<SmsMallCommodity> getSmsMallCommodity(@PathVariable String id){
        SmsMallCommodity smsMallCommodity = smsMallCommodityService.selectByPrimaryKey(id);
        GenericResponse<SmsMallCommodity> genericResponse = new GenericResponse<SmsMallCommodity>(true);
        genericResponse.setData(smsMallCommodity);
        return genericResponse;
    }
    
    /**
     * 家庭保洁下单
     * @param smsOrder
     * @param smsHouseholdOrder
     * @param smsHouseholdBags
     * @param smsHouseholdRequires
     * @return
     */
    @RequestMapping(value = "clean08", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ValidateGroup(
            fileds ={
                    @ValidateField(index=0 ,notNull=true ),
                    @ValidateField(index=1 ,notNull=true)
            }
    )
    public GenericResponse<String> genHouseholdOrder(String smsOrder, String smsHouseholdOrder, String smsHouseholdBags){
        List<SmsHouseholdBag> smsHouseholdBagList = null;
        if (!CheckEmptyUtil.isEmpty(smsHouseholdBags)){
            smsHouseholdBags = StringUtil.unescapeHtml(smsHouseholdBags);
            smsHouseholdBagList = gson.fromJson(smsHouseholdBags, new TypeToken<List<SmsHouseholdBag>>() {}.getType());
        }
        smsOrder = StringUtil.unescapeHtml(smsOrder);
        smsHouseholdOrder = StringUtil.unescapeHtml(smsHouseholdOrder);
        SmsOrder smsOrderObj = gson.fromJson(smsOrder, new TypeToken<SmsOrder>() {}.getType());
        SmsHouseholdOrder smsHouseholdOrderObj = gson.fromJson(smsHouseholdOrder, new TypeToken<SmsHouseholdOrder>() {}.getType());
        int flag = smsHouseholdOrderService.genSmsHouseholdOrder(smsOrderObj, smsHouseholdOrderObj, smsHouseholdBagList);
        return flag>0?new GenericResponse<String>(true, "下单成功"):new GenericResponse<String>(true, "下单失败,请重新提交");
    }
    
    /**
     * 新增新居开荒订单
     * @param smsOrder
     * @param smsNewhomeOrder
     * @return
     */
    @RequestMapping(value = "clean09", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ValidateGroup(
            fileds ={
                    @ValidateField(index=0 ,notNull=true ),
                    @ValidateField(index=1 ,notNull=true)
            }
    )
    public GenericResponse<String> genNewhomeOrder(String smsOrder, String smsNewhomeOrder){
        smsOrder = StringUtil.unescapeHtml(smsOrder);
        smsNewhomeOrder = StringUtil.unescapeHtml(smsNewhomeOrder);
        SmsOrder smsOrderObj = gson.fromJson(smsOrder, new TypeToken<SmsOrder>() {}.getType());
        SmsNewhomeOrder smsNewhomeOrderObj = gson.fromJson(smsNewhomeOrder, new TypeToken<SmsNewhomeOrder>() {}.getType());
        // 校验当天是否存在对应的订单,防止会员重复下单
        
        //
        int flag = smsNewhomeOrderService.genSmsNewhomeOrder(smsOrderObj, smsNewhomeOrderObj);
        return flag>0?new GenericResponse<String>(true, "下单成功"):new GenericResponse<String>(true, "下单失败,请重新提交");
    }
    
    /**
     * 获取附近的保洁员
     * @param cityId
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "clean10/{cityId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public GenericResponse<Map<String, Object>> getNearbyCleaners(@PathVariable String cityId, Integer page, Integer rows){
        PageList<SmsEmployee> smsEmployees = smsEmployeeService.selectCleanersByCity(cityId, new PageBounds(page, rows));
        GenericResponse<Map<String, Object>> genericResponse = new GenericResponse<Map<String, Object>>(true);
        genericResponse.setData(getEasyUIGrid(smsEmployees));
        return genericResponse;
    }
    
    /**
     * 获取曾今服务过的保洁员
     * @param member
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "clean11/{member}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public GenericResponse<List<SmsEmployee>> getServiceCleaners(@PathVariable String member, Integer page, Integer rows){
        List<SmsEmployee> smsEmployees = smsEmployeeService.selectServiceCleaners(member, new PageBounds(page, rows));
        GenericResponse<List<SmsEmployee>> genericResponse = new GenericResponse<List<SmsEmployee>>(true);
        genericResponse.setData(smsEmployees);
        return genericResponse;
    }
    
    
}
