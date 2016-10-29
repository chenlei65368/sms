package com.dzmsoft.sms.base.test.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.servlet.DispatcherServlet;
import org.junit.Test;

import com.dzmsoft.framework.base.util.HttpUtil;

public class SmsRestBaseControllerTest {
    @Test
    public void getLatestApp(){
        String uri = "http://localhost:8097/sms/rest/base/base01";
        Map<String,String> params = new HashMap<String,String>();
        params.put("filter_eqs_member", "12");
        params.put("page", "1");
        params.put("rows", "20");
        String result = HttpUtil.doGet(uri, params, 10000);
        System.out.println(result);
    }
    
    @Test
    public void sms02_test(){
        String uri = "http://localhost:8097/sms/rest/clean/sms02/1";
        Map<String,String> params = new HashMap<String,String>();
        String result = HttpUtil.doPost(uri, params, 10000);
        System.out.println(result);
    }
}
