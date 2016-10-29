package com.dzmsoft.sms.base.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.dzmsoft.framework.base.web.mvc.pojo.ShiroUser;
import com.dzmsoft.framework.base.web.mvc.shiro.UserUtil;
import com.dzmsoft.sms.base.listener.event.SmsOrderLogEvent;
import com.dzmsoft.sms.base.pojo.SmsOrder;
import com.dzmsoft.sms.base.pojo.SmsOrderLog;
import com.dzmsoft.sms.base.service.SmsOrderLogService;
import com.dzmsoft.sms.base.service.SmsOrderService;

@Async
@Component
public class SmsOrderLogListener implements ApplicationListener<SmsOrderLogEvent>{
    
    @Autowired
    private SmsOrderLogService smsOrderLogService;
    @Autowired
    private SmsOrderService smsOrderService;

    @Override
    public void onApplicationEvent(SmsOrderLogEvent event) {
        SmsOrderLogEvent smsOrderLogEvent = (SmsOrderLogEvent) event;
        SmsOrder smsOrder = smsOrderService.selectByPrimaryKey(smsOrderLogEvent.getSmsOrderId());
        SmsOrderLog smsOrderLog = genSmsOrderLog(smsOrder, smsOrderLogEvent.getAction());
        smsOrderLogService.insertSelective(smsOrderLog);
    }
    
    /**
     * 产生订单日志
     * @param smsOrder
     * @param action
     * @param director
     * @return
     */
    private SmsOrderLog genSmsOrderLog(SmsOrder smsOrder, String action){
        SmsOrderLog smsOrderLog = new SmsOrderLog();
        smsOrderLog.setOrderId(smsOrder.getId());
        smsOrderLog.setMember(smsOrder.getMember());
        smsOrderLog.setOpt(action);
        // 操作内容
        String optContent = genOptContent(action);
        smsOrderLog.setOptContent(optContent);
        // 操作负责人
        String director = genDirector(action, smsOrder);
        smsOrderLog.setDirector(director);
        return smsOrderLog;
    }
    
    /**
     * 产生操作内容
     * @param action
     * @return
     */
    private String genOptContent(String action){
        String optContent = null;
        switch(action){
        case  SmsOrderLogEvent.CANCELBYMEMBER:
            optContent = "会员取消订单";
            break;
        case SmsOrderLogEvent.GRAB:
            optContent = "抢单成功";
            default:
                break;
        }
        return optContent;
    }
    
    /**
     * 产生操作负责人
     * @return
     */
    private String genDirector(String action, SmsOrder smsOrder){
        String director = null;
        switch(action){
        case  SmsOrderLogEvent.CANCELBYMEMBER:
            director = smsOrder.getMember();
            break;
        case  SmsOrderLogEvent.GRAB:
            director = smsOrder.getDirector();
            break;
            default:
                ShiroUser shiroUser = UserUtil.getCurrentShiroUser();
                director = shiroUser.getId();
                break;
        }
        return director;
    }
}
