/**
 * Copyright (C), dzmsoft Co., Ltd
 */
var smsEmployeeDialogList;
var sexArray = new DataDictionary(DICTIONARY_FIELD.SEX);
$(function(){
	initActions();
	createSmsEmployeeDg();
});
/**
 * 初始化绑定事件
 */
function initActions(){
	$('#smsEmployeeDialog_find').on('click',function(){find();});
	$('#smsEmployeeDialog_reset').on('click',function(){reset();});
	sexArray.getFields();
}
/**
 * 查询
 */
function find(){
	var obj=$("#smsEmployeeDialog_searchFrom").serializeObject();
	obj.filter_eqs_userType = USER_TYPE.CLEANER;
	obj.filter_eqs_status = EMP_STATUS.IN_JOB;
	smsEmployeeDialogList.datagrid('load',obj); 
}
/**
 * 重置
 */
function reset(){
	$('#smsEmployeeDialog_searchFrom').form('clear');
	find();
}
/**
 * 创建系统表格
 */
function createSmsEmployeeDg(){
	$('#smsEmployeeDialog_dg').datagrid({    
		method: "post",url:ctx+'/smsEmployee/find',queryParams:{filter_eqs_userType:USER_TYPE.CLEANER,filter_eqs_status:EMP_STATUS.IN_JOB}, idField : 'id',singleSelect:true,
	    fit : true,fitColumns : false,border : false,striped:true,
		pagination:true,rownumbers:true,pageNumber:1,pageSize : 20,pageList : [ 20, 30, 50 ],
	    columns:[[    
	    				{field:'id',title:'认证编号',sortable:true,width:100,align:"left",halign:'center' }
	    			    			,{field:'name',title:'姓名',sortable:true,width:100,align:"left",halign:'center' }
    			,{field:'sex',title:'性别',sortable:true,width:30,align:"left",halign:'center',formatter:fmtSex }
    			,{field:'mobile',title:'手机号',sortable:true,width:100,align:"left",halign:'center' }
    			,{field:'experienceValue',title:'经验值',sortable:true,width:100,align:"right",halign:'center' ,formatter:DzmFrame.EasyUI.fmtMoney}
    			,{field:'goodRatio',title:'好评率',sortable:true,width:100,align:"right",halign:'center' }
    			,{field:'birthday',title:'出生日期',sortable:true,width:100,align:"left",halign:'center' ,formatter:DzmFrame.EasyUI.fmtYyyyMMdd}
    			,{field:'idCard',title:'身份证号',sortable:true,width:100,align:"left",halign:'center',formatter:DzmFrame.EasyUI.fmtIdCard }
    			,{field:'healthEndDate',title:'健康证有效截止日期',sortable:true,width:100,align:"left",halign:'center' ,formatter:DzmFrame.EasyUI.fmtYyyyMMdd}
    			,{field:'description',title:'员工简介',sortable:true,width:200,align:"left",halign:'center' }
    		    			    ]],
	    sortName:'name',sortOrder:'asc',
    	enableHeaderClickMenu: false,
    	enableHeaderContextMenu: false,
    	toolbar:'#smsEmployeeDialog_tb',
	    onDblClickRow:function(index,row){
	    	window.closeSmsEmployeeDg(row.id);
	    }
	});
}
function fmtSex(val){
	return sexArray.showDisplay(val);
}