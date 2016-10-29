package com.dzmsoft.sms.rest.base.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dzmsoft.framework.base.util.CheckEmptyUtil;
import com.dzmsoft.framework.base.util.StringUtil;
import com.dzmsoft.framework.base.web.mvc.controller.BaseController;
import com.dzmsoft.framework.base.web.mvc.controller.validate.ValidateField;
import com.dzmsoft.framework.base.web.mvc.controller.validate.ValidateGroup;
import com.dzmsoft.framework.base.web.mvc.view.GenericResponse;
import com.dzmsoft.sms.base.pojo.SmsCommodityOrder;
import com.dzmsoft.sms.base.pojo.SmsOrder;
import com.dzmsoft.sms.base.service.SmsCommodityOrderService;
import com.dzmsoft.sms.base.service.SmsOrderService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RestController
@RequestMapping("rest")
public class SmsRestCommodityController extends BaseController{
    @Autowired
    Gson gson = new Gson();
    @Autowired
    private SmsOrderService smsOrderService;
    @Autowired
    private SmsCommodityOrderService smsCommodityOrderService;
    
    @RequestMapping(value = "commodity01", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ValidateGroup(
            fileds ={
                    @ValidateField(index=0 ,notNull=true ),
                    @ValidateField(index=1 ,notNull=true)
            }
    )
    public GenericResponse<String> genSmsCommodityOrder(String smsOrder, String smsCommodityOrders){
        List<SmsCommodityOrder> smsCommodityOrderList = null;
        if (!CheckEmptyUtil.isEmpty(smsCommodityOrders)){
            smsCommodityOrders = StringUtil.unescapeHtml(smsCommodityOrders);
            smsCommodityOrderList = gson.fromJson(smsCommodityOrders, new TypeToken<List<SmsCommodityOrder>>() {}.getType());
        }
        smsOrder = StringUtil.unescapeHtml(smsOrder);
        SmsOrder smsOrderObj = gson.fromJson(smsOrder, new TypeToken<SmsOrder>() {}.getType());
        int flag = smsCommodityOrderService.genSmsCommodityOrder(smsOrderObj, smsCommodityOrderList);
        return flag>0?new GenericResponse<String>(true, "下单成功"):new GenericResponse<String>(true, "下单失败,请重新提交");
    }
}
