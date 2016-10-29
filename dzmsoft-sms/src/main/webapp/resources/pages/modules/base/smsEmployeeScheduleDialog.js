/**
 * Copyright (C), dzmsoft Co., Ltd
 */
var smsEmployeeScheduleDialogList;
var scheduleStatusArray = new DataDictionary(DICTIONARY_FIELD.SCHEDULE_STATUS);
$(function(){
	initActions();
	createSmsEmployeeScheduleDg();
});
/**
 * 初始化绑定事件
 */
function initActions(){
	$('#smsEmployeeScheduleDialog_find').on('click',function(){smsEmployeeScheduleDialog_find();});
	$('#smsEmployeeScheduleDialog_reset').on('click',function(){smsEmployeeScheduleDialog_reset();});
	scheduleStatusArray.getFields();
}
/**
 * 查询
 */
function smsEmployeeScheduleDialog_find(){
	var obj=$("#smsEmployeeScheduleDialog_searchFrom").serializeObject();
	smsEmployeeScheduleDialogList.datagrid('load',obj); 
}
/**
 * 重置
 */
function smsEmployeeScheduleDialog_reset(){
	$('#smsEmployeeScheduleDialog_searchFrom').form('clear');
	find();
}
/**
 * 创建系统表格
 */
function createSmsEmployeeScheduleDg(){
	var queryParams = {};
	var appointmentTime = $('#appointmentTimeDialog').val();
	if (appointmentTime){
		queryParams.appointmentTime = appointmentTime;
	}
	var times = $('#timesDialog').val();
	if (times){
		queryParams.times = times;
	}
	smsEmployeeScheduleDialogList = $('#smsEmployeeScheduleDialog_dg').datagrid({    
		method: "post",url:ctx+'/smsEmployeeSchedule/find',queryParams:queryParams, idField : 'id',singleSelect:true,
	    fit : true,fitColumns : false,border : false,striped:true,
		pagination:true,rownumbers:true,pageNumber:1,pageSize : 20,pageList : [ 20, 30, 50 ],
		frozenColumns:[[
		                {field:'employee',title:'员工',sortable:true,width:100,align:'left',halign:'center' }
		                ,{field:'day',title:'日期',sortable:true,width:80,align:'left',halign:'center',formatter:DzmFrame.EasyUI.fmtYyyyMMdd }         
		            ]],
	    columns:[[    
	            		    			    				{field:'id',title:'ID',hidden:true}
		    			,{field:'six',title:'6点',sortable:true,width:30,align:"left",halign:'center',styler:stylerHour,formatter:fmtHour }
		    			,{field:'seven',title:'7点',sortable:true,width:30,align:"left",halign:'center',styler:stylerHour,formatter:fmtHour }
		    			,{field:'eight',title:'8点',sortable:true,width:30,align:"left",halign:'center',styler:stylerHour,formatter:fmtHour }
		    			,{field:'nine',title:'9点',sortable:true,width:30,align:"left",halign:'center',styler:stylerHour,formatter:fmtHour }
		    			,{field:'ten',title:'10点',sortable:true,width:30,align:"left",halign:'center',styler:stylerHour,formatter:fmtHour }
		    			,{field:'eleven',title:'11点',sortable:true,width:30,align:"left",halign:'center',styler:stylerHour,formatter:fmtHour }
		    			,{field:'twelve',title:'12点',sortable:true,width:30,align:"left",halign:'center',styler:stylerHour,formatter:fmtHour }
		    			,{field:'thirteen',title:'13点',sortable:true,width:30,align:"left",halign:'center',styler:stylerHour,formatter:fmtHour }
		    			,{field:'fourteen',title:'14点',sortable:true,width:30,align:"left",halign:'center',styler:stylerHour,formatter:fmtHour }
		    			,{field:'fifteen',title:'15点',sortable:true,width:30,align:"left",halign:'center',styler:stylerHour,formatter:fmtHour }
		    			,{field:'sixteen',title:'16点',sortable:true,width:30,align:"left",halign:'center',styler:stylerHour,formatter:fmtHour }
		    			,{field:'seventeen',title:'17点',sortable:true,width:30,align:"left",halign:'center',styler:stylerHour,formatter:fmtHour }
		    			,{field:'eighteen',title:'18点',sortable:true,width:30,align:"left",halign:'center',styler:stylerHour,formatter:fmtHour }
		    			,{field:'nineteen',title:'19点',sortable:true,width:30,align:"left",halign:'center',styler:stylerHour,formatter:fmtHour }
		    			,{field:'twenty',title:'20点',sortable:true,width:30,align:"left",halign:'center',styler:stylerHour,formatter:fmtHour }
		    			,{field:'twentyOne',title:'21点',sortable:true,width:30,align:"left",halign:'center',styler:stylerHour,formatter:fmtHour }
		    			,{field:'twentyTwo',title:'22点',sortable:true,width:30,align:"left",halign:'center',styler:stylerHour,formatter:fmtHour }
		    			,{field:'twentyThree',title:'23点',sortable:true,width:30,align:"left",halign:'center',styler:stylerHour,formatter:fmtHour }
    		    			    ]],
	    sortName:'employee',sortOrder:'asc',
    	enableHeaderClickMenu: false,
    	enableHeaderContextMenu: false,
    	toolbar:'#smsEmployeeScheduleDialog_tb',
	    onDblClickRow:function(index,row){
	    	window.closeSmsEmployeeScheduleDg(row.id);
	    }
	});
}
function fmtHour(val){
	return scheduleStatusArray.showDisplay(val);
}
function stylerHour(val,row,index){
	if (val == 0){
		return 'background-color:#808080;color:#ffffff;'
	} else if (val == 2){
		return 'background-color:#ff0000;color:#ffffff;';
	} else{
		return '';
	}
}