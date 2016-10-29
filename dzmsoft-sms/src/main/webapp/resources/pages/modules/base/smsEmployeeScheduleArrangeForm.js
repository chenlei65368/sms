/**
 * Copyright (C), dzmsoft Co., Ltd
 */
																																																																														/**
 * 初始加载
 */
$(function(){
	initActions();
});
var empGridDg;
var curIndex;
var dateList;
function initActions(){
	$('.removeEmpLine').linkbutton({'onClick':function(){removeEmpLine();}});
	createShiftGrid();
	initEmpDetail();
	loadEmpDetail();
	initDateList();
}
function initDateList(){
	dateList = $('#dateList').datalist({fit:true, plain:true, valueField:'valueField',textField:'textField',singleSelect:false});
}
function loadDates(){
	var year = $dp.cal.getP('y');
	var month = $dp.cal.getP('M');
	var day;
	var dates = [];
	for (var i=1; i<=28; i++){
		day = year + '-' + month + '-' + DzmFrame.EasyUI.pad(i,2);
		dates.push({valueField:day, textField:day});
	}
	switch(month){
	case '01':case '03':case '05':case '07': case '08':case '10': case '12':
		day = year + '-' + month + '-' + '29';
		dates.push({valueField:day, textField:day});
		day = year + '-' + month + '-' + '30';
		dates.push({valueField:day, textField:day});
		day = year + '-' + month + '-' + '31';
		dates.push({valueField:day, textField:day});
		break;
	case '04':case '06':case '09':case '11':
		day = year + '-' + month + '-' + '29';
		dates.push({valueField:day, textField:day});
		day = year + '-' + month + '-' + '30';
		dates.push({valueField:day, textField:day});
		break;
	case '02':
		if ($.date.isLeapYear(year)){
			day = year + '-' + month + '-' + '29';
			dates.push({valueField:day, textField:day});
		}
		break;
	}
	//
	dateList.datalist('loadData',dates);
}
/**
 * 班次
 */
function createShiftGrid(){
	$('#shift').combogrid({
		url:ctx+'/smsShift/find',method: 'get',
		idField:'id',textField:'name',fitColumns:true,panelWidth:450,
		columns:[[
			{field:'id',title:'ID',hidden:true}
			,{field:'name',title:'班次',sortable:true,width:100,align:'left',halign:'center' }
			,{field:'beginTime',title:'起始时间点',sortable:true,width:100,align:'left',halign:'center' }
			,{field:'endTime',title:'截止时间点',sortable:true,width:100,align:'left',halign:'center' }
			,{field:'description',title:'描述',sortable:true,width:100,align:'left',halign:'center' }   
		]]
	});
}
/**
 * 初始化表格
 */
function initEmpDetail(){
	empGridDg = $('#empGrid').datagrid({  
		idField : 'id',singleSelect:true,
	    fit:true, fitColumns : true,border : false,striped:true,
		pagination:true,rownumbers:true,pageNumber:1,pageSize : 20,pageList : [ 20, 30, 50 ],
	    columns:[[    
			{field:'id',title:'认证编号',sortable:true,width:100,align:'left',halign:'center'}
	    			,{field:'name',title:'姓名',sortable:true,width:100,align:'left',halign:'center' }
	    			,{field:'sex',title:'性别',sortable:true,width:100,align:'left',halign:'center',formatter:fmtSex }
	    			,{field:'mobile',title:'手机点',sortable:true,width:100,align:'left',halign:'center',formatter:DzmFrame.EasyUI.fmtMobile }
	    			,{field:'userType',title:'用户类型',sortable:true,width:100,align:'left',halign:'center',formatter:fmtEmpType }
	    			,{field:'experienceValue',title:'经验值',sortable:true,width:100,align:'right',halign:'center' }
	    			,{field:'goodRatio',title:'好评率',sortable:true,width:100,align:'right',halign:'center',formatter:function(val){return val + '%';} }
	    			,{field:'healthEndDate',title:'健康证有效截止日期',sortable:true,width:100,align:'left',halign:'center' ,formatter:DzmFrame.EasyUI.fmtYyyyMMdd}
	    			    		    ]],
	    	    toolbar:'empLine_tb',
	    enableHeaderClickMenu: false,
	    enableHeaderContextMenu: false,
	    onClickRow:function(index,row){
	    	curIndex = index;
	    }
		});
}
/**
 * 加载数据
 */
function loadEmpDetail(){
	var params = $('#selectEmpParams').val();
	var detailParams = $.parseJSON(params);
	empGridDg.datagrid('loadData',detailParams);
}
/**
 * 删除记录
 */
function removeEmpLine(){
	var row = empGridDg.datagrid('getSelected');
	if(rowIsNull(row)) return;
	$.messager.confirm('提示', '您确定要删除吗？', function(data){
		if (data){
			empGridDg.datagrid('deleteRow', curIndex);
		} 
	});
}