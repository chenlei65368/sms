package com.dzmsoft.sms.rest.base.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dzmsoft.framework.base.util.CheckEmptyUtil;
import com.dzmsoft.framework.base.util.SpringContextHolder;
import com.dzmsoft.framework.base.web.mvc.view.GenericResponse;
import com.dzmsoft.sms.base.common.SmsBaseConstant;
import com.dzmsoft.sms.base.dto.Order;
import com.dzmsoft.sms.base.dto.SmsOrderDto;
import com.dzmsoft.sms.base.pojo.SmsEmployee;
import com.dzmsoft.sms.base.pojo.SmsOrder;
import com.dzmsoft.sms.base.service.OrderService;
import com.dzmsoft.sms.base.service.SmsEmployeeService;
import com.dzmsoft.sms.base.service.SmsOrderService;
import com.dzmsoft.sms.rest.base.common.SmsRestBaseConstant;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

@RestController
@RequestMapping("rest")
public class SmsRestCleanerController {
    @Autowired
    private SmsEmployeeService smsEmployeeService;
    @Autowired
    private SmsOrderService smsOrderService;

    /**
     * 获取保洁员信息
     * 
     * @param ucsId
     * @return
     */
    @RequestMapping(value = "cleaner01/{ucsId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public GenericResponse<SmsEmployee> getSmsEmployee(@PathVariable String ucsId) {
        SmsEmployee smsEmployee = smsEmployeeService.selectByUcsId(ucsId);
        if (smsEmployee == null) {
            return new GenericResponse<SmsEmployee>(false, "保洁员信息不存在");
        }
        GenericResponse<SmsEmployee> genericResponse = new GenericResponse<SmsEmployee>(true);
        genericResponse.setData(smsEmployee);
        return genericResponse;
    }

    /**
     * 获取管家
     * 
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "cleaner02/{cityId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public GenericResponse<List<SmsEmployee>> getStewardes(@PathVariable String cityId,
            Integer page, Integer rows) {
        return null;
    }

    /**
     * 获取管家详情
     * @param cityId
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "cleaner03/{cityId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public GenericResponse<List<SmsEmployee>> getSteward(@PathVariable String cityId, Integer page,
            Integer rows) {
           return null;
    }
    
    /**
     * 获取订单信息
     * @param request
     * @return
     */
    @RequestMapping(value = "cleaner04/{cleaner}/{type}/{city}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public GenericResponse<PageList<SmsOrderDto<Order>>> getSmsOrder(@PathVariable String cleaner, @PathVariable String type, Integer page, Integer rows){
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
        List<SmsOrder> smsOrders = smsOrderService.selectByMember(cleaner, statusList, new PageBounds(page, rows));
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
}
