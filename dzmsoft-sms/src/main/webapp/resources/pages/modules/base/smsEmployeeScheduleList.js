/**
 * Copyright (C), dzmsoft Co., Ltd
 */
/**
 * 变量区
 */
var dgList; // 列表
var dateList;
var dForm;// 对话框
var noScheduleDg;// 未排班列表
var sexArray = new DataDictionary(DICTIONARY_FIELD.SEX);
var statusArray = new DataDictionary(DICTIONARY_FIELD.EMP_STATUS);
var empTypeArray = new DataDictionary(DICTIONARY_FIELD.CLEAN_EMP_TYPE);
var scheduleStatusArray = new DataDictionary(DICTIONARY_FIELD.SCHEDULE_STATUS);
var arrangeForm;// 排班form
/**
 * 初始加载
 */
$(function(){
	initActions();
	createDg();
	createNoScheduleDg();
});
function today(){
	var today = new Date();
	var y = today.getFullYear();
	var m = today.getMonth()+1;
	return y + '-' + DzmFrame.EasyUI.pad(m,2);
}
/**
 * 初始化绑定事件
 */
function initActions(){
	$('#searchFrom_find').on('click',function(){find();});
	$('#searchFrom_reset').on('click',function(){reset();});
	$('.add').linkbutton({'onClick':function(){add();}});
	$('.edit').linkbutton({'onClick':function(){edit();}});
	$('.remove').linkbutton({'onClick':function(){remove();}});
	$('.arrange').linkbutton({'onClick':function(){arrange();}});
	statusArray.getFields();
	sexArray.getFields();
	empTypeArray.getFields();
	scheduleStatusArray.getFields();
	$('#monthCond').val(today());
	$('#filter_eqd_day').datebox('setValue',DzmFrame.EasyUI.fmtYyyyMMdd(new Date()));
}
/**
 * 查询
 */
function find(){
	var obj=$("#searchFrom").serializeObject();
	dgList.datagrid('load',obj); 
}
/**
 * 重置
 */
function reset(){
	$('#searchFrom').form('clear');
	find();
}
function loadNoSchedule(){
	var year = $dp.cal.getP('y');
	var month = $dp.cal.getP('M');
	var startDay = year + '-' + month + '-01';
	var endDay = getEndDay(year, month);
	noScheduleDg.datagrid('load',{filter_ged_day__SmsEmployeeScheduleExample:startDay,filter_led_day__SmsEmployeeScheduleExample:endDay}); 
}
function getEndDay(year, month){
	var endDay = year + '-' + month;
	switch(month){
	case '01':case '03':case '05':case '07': case '08':case '10': case '12':
		endDay = endDay + '-31';
		break;
	case '04':case '06':case '09':case '11':
		endDay = endDay + '-30';
		break;
	case '02':
		if ($.date.isLeapYear(year)){
			endDay = endDay + '-29';
		} else{
			endDay = endDay + '-28';
		}
		break;
	}
	return endDay;
}
/**
 * 查询未排班的员工
 */
function createNoScheduleDg(){
	// 组装默认查询条件
	var monthCond = $('#monthCond').val().split('-');
	var startDay = monthCond[0] + '-' + monthCond[1] + '-01';
	var endDay = getEndDay(monthCond[0], monthCond[1]);
	//
	noScheduleDg=$('#noScheduleDg').datagrid({    
		method: "post",url:ctx+'/smsEmployee/findNoSchedule',queryParams:{filter_ged_day__SmsEmployeeScheduleExample:startDay,filter_led_day__SmsEmployeeScheduleExample:endDay}
		, idField : 'id',singleSelect:false,
	    fit : true,fitColumns : false,border : false,striped:true,
		pagination:true,rownumbers:true,pageNumber:1,pageSize : 20,pageList : [ 20, 30, 50 ],
		frozenColumns:[[
		                {field:'id',title:'认证编号',sortable:true,width:100,align:'left',halign:'center'}
	    			,{field:'name',title:'姓名',sortable:true,width:50,align:'left',halign:'center' }
	    			,{field:'sex',title:'性别',sortable:true,width:30,align:'left',halign:'center',formatter:fmtSex }       
		            ]],
	    columns:[[    
	    			{field:'mobile',title:'手机点',sortable:true,width:100,align:'left',halign:'center',formatter:DzmFrame.EasyUI.fmtMobile }
	    			,{field:'userType',title:'用户类型',sortable:true,width:100,align:'left',halign:'center',formatter:fmtEmpType }
	    			,{field:'experienceValue',title:'经验值',sortable:true,width:100,align:'right',halign:'center' }
	    			,{field:'goodRatio',title:'好评率',sortable:true,width:100,align:'right',halign:'center',formatter:function(val){return val + '%';} }
	    			,{field:'healthEndDate',title:'健康证有效截止日期',sortable:true,width:100,align:'left',halign:'center' ,formatter:DzmFrame.EasyUI.fmtYyyyMMdd}
	    		    	    ]],
//	    sortName:'name',sortOrder:'asc',
	    enableHeaderClickMenu: false,
	    enableHeaderContextMenu: false,
	    enableRowContextMenu: true,
	    pagingMenu: { submenu: false } ,
	    autoBindDblClickRow: false,
	    toolbar:'#noScheduleDg_tb'
		});
}
/*
 * 创建表格
 */
function createDg(){
	dgList=$('#dg').datagrid({    
	method: "post",url:ctx+'/smsEmployeeSchedule/find', idField : 'id',singleSelect:true,queryParams:{filter_eqd_day:$('#filter_eqd_day').datebox('getValue')},
    fit : true,fitColumns : false,border : false,striped:true,
	pagination:true,rownumbers:true,pageNumber:1,pageSize : 20,pageList : [ 20, 30, 50 ],
	frozenColumns:[[
        {field:'employee',title:'员工',sortable:true,width:100,align:'left',halign:'center' }
        ,{field:'day',title:'日期',sortable:true,width:80,align:'left',halign:'center',formatter:DzmFrame.EasyUI.fmtYyyyMMdd }         
    ]],
    columns:[[    
		{field:'id',title:'ID',hidden:true}
			,{field:'six',title:'6点',sortable:true,width:30,align:'left',halign:'center',styler:stylerHour,formatter:fmtHour }
			,{field:'seven',title:'7点',sortable:true,width:30,align:'left',halign:'center',styler:stylerHour,formatter:fmtHour }
			,{field:'eight',title:'8点',sortable:true,width:30,align:'left',halign:'center',styler:stylerHour,formatter:fmtHour }
			,{field:'nine',title:'9点',sortable:true,width:30,align:'left',halign:'center',styler:stylerHour,formatter:fmtHour }
			,{field:'ten',title:'10点',sortable:true,width:30,align:'left',halign:'center',styler:stylerHour ,formatter:fmtHour}
			,{field:'eleven',title:'11点',sortable:true,width:30,align:'left',halign:'center',styler:stylerHour,formatter:fmtHour }
			,{field:'twelve',title:'12点',sortable:true,width:30,align:'left',halign:'center',styler:stylerHour,formatter:fmtHour }
			,{field:'thirteen',title:'13点',sortable:true,width:30,align:'left',halign:'center',styler:stylerHour,formatter:fmtHour }
			,{field:'fourteen',title:'14点',sortable:true,width:30,align:'left',halign:'center',styler:stylerHour,formatter:fmtHour }
			,{field:'fifteen',title:'15点',sortable:true,width:30,align:'left',halign:'center',styler:stylerHour,formatter:fmtHour }
			,{field:'sixteen',title:'16点',sortable:true,width:30,align:'left',halign:'center',styler:stylerHour,formatter:fmtHour }
			,{field:'seventeen',title:'17点',sortable:true,width:30,align:'left',halign:'center',styler:stylerHour,formatter:fmtHour }
			,{field:'eighteen',title:'18点',sortable:true,width:30,align:'left',halign:'center',styler:stylerHour,formatter:fmtHour }
			,{field:'nineteen',title:'19点',sortable:true,width:30,align:'left',halign:'center',styler:stylerHour,formatter:fmtHour }
			,{field:'twenty',title:'20点',sortable:true,width:30,align:'left',halign:'center',styler:stylerHour,formatter:fmtHour }
			,{field:'twentyOne',title:'21点',sortable:true,width:30,align:'left',halign:'center',styler:stylerHour,formatter:fmtHour }
			,{field:'twentyTwo',title:'22点',sortable:true,width:30,align:'left',halign:'center',styler:stylerHour,formatter:fmtHour }
			,{field:'twentyThree',title:'23点',sortable:true,width:30,align:'left',halign:'center',styler:stylerHour,formatter:fmtHour }
    		    	    ]],
//    sortName:'employee',sortOrder:'asc',
    enableHeaderClickMenu: false,
    enableHeaderContextMenu: false,
    toolbar:'#tb'
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
function fmtSex(val){
	return sexArray.showDisplay(val);
}
function fmtEmpType(val){
	return empTypeArray.showDisplay(val);
}
function fmtStatus(val){
	return statusArray.showDisplay(val);
}
/**
 * 新增
 */
function add(){
	dForm=$('#dlg').dialog({title:'新增',width: 380, height: 380,modal:true
		,href:ctx+'/smsEmployeeSchedule/add',
		buttons:[
			{text:'提交',handler:function(){submit();}}
			,{text:'返回',handler:function(){dForm.panel('close');}}
		]
	});
}
/**
 * 排班
 */
function arrange(){
	var rows = noScheduleDg.datagrid('getSelections');
	if ($.array.isNullOrEmpty(rows)){
		$.messager.alert('系统提示','请选择至少一条数据','error');
		return ;
	}
	$('#selectEmpParams').val(JSON.stringify(rows));
	arrangeForm = $('#dlg').dialog({title:'新增',width: 750, height: 600,modal:true
		,href:ctx+'/smsEmployeeSchedule/showArrangeForm',
		buttons:[
			{text:'提交',handler:function(){submitArrange();}}
			,{text:'返回',handler:function(){arrangeForm.panel('close');}}
		]
	});
}
/**
 * 排班提交
 */
function submitArrange(){
	$('#arrangeForm').form(
			'submit',
			{
				onSubmit : function() {
					var isValid = $(this).form('enableValidation').form(
							'validate');
					var dateDetail = $('#dateList').datalist('getSelections');
					if ($.array.isNullOrEmpty(dateDetail)){
						$.messager.alert('系统提示','请选择至少一条日期数据','error');
						return false;
					}
					if (isValid) {
						var empGrid = $('#empGrid').datagrid('getData');
						var empRows = [];
						empGrid.rows.forEach(function(row, index){
							empRows.push({id:row.id, name:row.name});
						})
						var datasJsonDetail = JSON.stringify(empRows);
						$('#empDetail').val(datasJsonDetail);
						//
						var dateJsonDetail = JSON.stringify(dateDetail);
						$('#dateDetail').val(dateJsonDetail);
						$.messager.progress();
					}
					return isValid; // 返回false终止表单提交
				},
				success : function(result) {
					$.messager.progress('close');
					//
					var data = $.parseJSON(result);
					if (data.success) {
						$.messager.alert('系统提示', data.msg, 'info', function() {
							arrangeForm.panel('close');
							find();
						});
					} else {
						$.messager.alert('系统提示', data.msg, 'error');
					}
				}
			});
}
/**
 * 编辑
 */
function edit(){
	var row = dgList.datagrid('getSelected');
	if(rowIsNull(row)) return;
	dForm=$('#dlg').dialog({title:'编辑',width: 380, height: 380,modal:true
		,href:ctx+'/smsEmployeeSchedule/edit/'+row.id,
		buttons:[
			{text:'提交',handler:function(){submit();}}
			,{text:'返回',handler:function(){dForm.panel('close');}}
		]
	});
}
/**
 * 删除用户
 */
function remove(){
	var row = dgList.datagrid('getSelected');
	if(rowIsNull(row)) return;
	parent.$.messager.confirm('提示', '删除后无法恢复您确定要删除？', function(data){
		if (data){
			$.ajax({
				type:'get',
				url:ctx+"/smsEmployeeSchedule/remove/"+row.id,
				success: function(data){
					if (data.success){
						$.messager.alert('系统提示',data.msg,'info',function(){
							find();
						});
					} else{
						$.messager.alert('系统提示',data.msg,'error');
					}
				}
			});
		} 
	});
}
/**
 * 提交
 */
function submit(){
	$('#mainform').form('submit',{
		onSubmit:function(){
			var isValid = $(this).form('enableValidation').form('validate');
			if (isValid){
																																																																																																																																																																																																																																																																																																																																																																							$.messager.progress();
			}
			return isValid;	// 返回false终止表单提交
		},
		success:function(result){
			$.messager.progress('close');
			//
			var data = $.parseJSON(result);
			if (data.success){
				$.messager.alert('系统提示',data.msg,'info',function(){
					dForm.panel('close');
					find();
				});
			} else{
				$.messager.alert('系统提示',data.msg,'error');
			}
		}
	});
}
