package com.dzmsoft.sms.base.test.common;

import org.junit.Test;

import com.dzmsoft.framework.base.util.security.ca.ExportCertFormKeystore;

public class DbTest {

    @Test
    public void genPassword_test(){
        String userpwd = "root";
        String jarPath = "D:/Program Files (x86)/apache-maven-3.1.1/repository/com/alibaba/druid/1.0.16/druid-1.0.16.jar";
        new ExportCertFormKeystore().genDruidPwd(userpwd,jarPath);
    }
    
    @Test
    public void test1(){
        System.out.println(8%2<<3);
    }
}
