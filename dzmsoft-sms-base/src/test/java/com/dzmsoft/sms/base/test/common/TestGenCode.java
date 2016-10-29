package com.dzmsoft.sms.base.test.common;

import org.junit.Test;

import com.dzmsoft.framework.base.util.StringUtil;
import com.dzmsoft.framework.gencode.common.GenCodeConstant;
import com.dzmsoft.framework.gencode.handle.GenCodeHandler;
import com.dzmsoft.framework.gencode.param.GenCodeCfg;
import com.dzmsoft.framework.gencode.param.VmParams;
import com.dzmsoft.framework.gencode.param.dto.CodeType;
import com.dzmsoft.framework.gencode.param.dto.JDBCConnectionConfiguration;
import com.dzmsoft.framework.gencode.param.dto.StrategyType;
import com.dzmsoft.sms.base.pojo.SmsEmployee;
import com.dzmsoft.sms.base.pojo.SmsEmployeeSchedule;
import com.dzmsoft.sms.base.pojo.SmsEmployeeShift;
import com.dzmsoft.sms.base.pojo.SmsEmployeeTeam;
import com.dzmsoft.sms.base.pojo.SmsHouseBuilding;
import com.dzmsoft.sms.base.pojo.SmsHousing;
import com.dzmsoft.sms.base.pojo.SmsMember;
import com.dzmsoft.sms.base.pojo.SmsMemberAddress;

public class TestGenCode {

	
    public JDBCConnectionConfiguration getJdbcConfig() {
        JDBCConnectionConfiguration config = new JDBCConnectionConfiguration();
        config.setConnectionURL("jdbc:mysql://localhost:3306/walle");
        config.setDriverClass("com.mysql.jdbc.Driver");
        config.setUserId("root");
        config.setPassword("root");
        return config;
    }

    //js文件地址
    private static final String jsPath = "F:/dzmsoftwork/dzmsoft-sms/src/main/webapp/resources/pages/modules";
    //jsp文件地址
    private static final String jspPath = "F:/dzmsoftwork/dzmsoft-sms/src/main/webapp/WEB-INF/views/modules";
    //service和controller的所在包名
    private static final String packagePath = "com.dzmsoft.sms.base";
    //生成js和jsp文件时的文件夹名
    private static final String modulePath = "base";
    
    @Test
    public void genCommon(){
        // 模板参数
        VmParams vmParams = new VmParams.Builder()
            .setJsPath(jsPath).setJspPath(jspPath).setModulePath(modulePath).setPackagePath(packagePath)
            .setJdbcConfig(getJdbcConfig())
            .addBeanName(SmsEmployeeShift.class.getName())
//            .addExtra(GenCodeConstant.EXTRA_ENABLE_DISABLE, true)
//            .addExtra(GenCodeConstant.EXTRA_TAB_RICHTEXT, true)
            .build();
        // 生成代码的配置
        GenCodeCfg genCodeCfg = new GenCodeCfg.Builder()
            .setStrategyType(StrategyType.Common)
            .setVmParams(vmParams)
//            .addAllFlat()
            .addCodeType(CodeType.FlatController)
            .addCodeType(CodeType.FlatService)
            .addCodeType(CodeType.FlatServiceImpl)
//            .addCodeType(CodeType.FlatListJs)
//            .addCodeType(CodeType.FlatFormJsp)
            .build();
        // 生成代码
        GenCodeHandler handler = GenCodeHandler.getInstance();
        handler.genCode(genCodeCfg);
    }
    
    @Test
    public void genDialog(){
        // 模板参数
        VmParams vmParams = new VmParams.Builder()
            .setJsPath(jsPath).setJspPath(jspPath).setModulePath(modulePath).setPackagePath(packagePath)
            .setJdbcConfig(getJdbcConfig())
            .addBeanName(SmsEmployee.class.getName())
            .addBeanName(SmsEmployeeSchedule.class.getName())
            .build();
        // 生成代码的配置
        GenCodeCfg genCodeCfg = new GenCodeCfg.Builder()
            .setStrategyType(StrategyType.Common)
            .setVmParams(vmParams)
            .addCodeType(CodeType.FlatDialogJs)
            .addCodeType(CodeType.FlatDialogJsp)
            .build();
        // 生成代码
        GenCodeHandler handler = GenCodeHandler.getInstance();
        handler.genCode(genCodeCfg);
    }
    
    @Test
    public void genTree(){
        // 模板参数
        VmParams vmParams = new VmParams.Builder()
            .setJsPath(jsPath).setJspPath(jspPath).setModulePath(modulePath).setPackagePath(packagePath).setJdbcConfig(getJdbcConfig())
            .addBeanName(SmsEmployeeTeam.class.getName())
            .addExtra(GenCodeConstant.EXTRA_ENABLE_DISABLE, true)
            .build();
        // 生成代码的配置
        GenCodeCfg genCodeCfg = new GenCodeCfg.Builder()
            .setStrategyType(StrategyType.Common)
            .setVmParams(vmParams)
            .addAllTree()
            .build();
        // 生成代码
        GenCodeHandler handler = GenCodeHandler.getInstance();
        handler.genCode(genCodeCfg);
    }
    
    @Test
    public void genFlat(){
        // 模板参数
        VmParams vmParams = new VmParams.Builder()
            .setJsPath(jsPath).setJspPath(jspPath).setModulePath(modulePath).setPackagePath(packagePath).setJdbcConfig(getJdbcConfig())
//            .addBeanName(SmsEmployeeHour.class.getName())
//            .addExtra(GenCodeConstant.EXTRA_ENABLE_DISABLE, true)
            .build();
        // 生成代码的配置
        GenCodeCfg genCodeCfg = new GenCodeCfg.Builder()
            .setStrategyType(StrategyType.Common)
            .setVmParams(vmParams)
//            .addAllFlat()
            .addCodeType(CodeType.FlatService)
             .addCodeType(CodeType.FlatServiceImpl)
            .build();
        // 生成代码
        GenCodeHandler handler = GenCodeHandler.getInstance();
        handler.genCode(genCodeCfg);
    }
    
    @Test
    public void genLR(){
     // 模板参数
        VmParams vmParams = new VmParams.Builder()
            .setJsPath(jsPath).setJspPath(jspPath).setModulePath(modulePath).setPackagePath(packagePath).setJdbcConfig(getJdbcConfig())
            .setLeft(SmsHousing.class.getName())
            .setRight(SmsHouseBuilding.class.getName())
            .addExtra(GenCodeConstant.EXTRA_RIGHT_TITLE, "楼栋信息")
            .addExtra(GenCodeConstant.EXTRA_MAIN, "housingId")
//            .addBeanName(SmsEmployeeHour.class.getName())
            .addExtra(GenCodeConstant.EXTRA_ENABLE_DISABLE, true)
            .build();
        // 生成代码的配置
        GenCodeCfg genCodeCfg = new GenCodeCfg.Builder()
            .setStrategyType(StrategyType.LeftRight)
            .setVmParams(vmParams)
           .addAllLR()
            .build();
        // 生成代码
        GenCodeHandler handler = GenCodeHandler.getInstance();
        handler.genCode(genCodeCfg);
    }
    
    @Test
    public void genOrderItem(){
        // 模板参数
        VmParams vmParams = new VmParams.Builder()
            .setJsPath(jsPath).setJspPath(jspPath).setModulePath(modulePath).setPackagePath(packagePath)
            .setJdbcConfig(getJdbcConfig())
            .setMainBean(SmsMember.class.getName())
            .addSubBeanName(SmsMemberAddress.class.getName())
//            .addExtra(GenCodeConstant.EXTRA_ENABLE_DISABLE, true)
             .addExtra(GenCodeConstant.EXTRA_MAIN, StringUtil.toUpperCaseFirstOne("ucsId"))
//            .addExtra(GenCodeConstant.EXTRA_TAB_RICHTEXT, true)
            .build();
        // 生成代码的配置
        GenCodeCfg genCodeCfg = new GenCodeCfg.Builder()
            .setStrategyType(StrategyType.OrderItem)
            .setVmParams(vmParams)
            .addAllOrderItemCodeType()
            .build();
        // 生成代码
        GenCodeHandler handler = GenCodeHandler.getInstance();
        handler.genCode(genCodeCfg);
    }
}
