/**
 * Copyright (C), dzmsoft Co., Ltd
 */
													/**
 * 初始加载
 */
$(function(){
	initActions();
	createDg();
});
/**
 * 变量区
 */
var dgList; // 列表
var dForm;// 对话框
var dialog,curRow;
var statusArray = new DataDictionary(DICTIONARY_FIELD.ORDER_STATUS);
/**
 * 初始化绑定事件
 */
function initActions(){
	$('#searchFrom_find').on('click',function(){find();});
	$('#searchFrom_reset').on('click',function(){reset();});
	$('.add').linkbutton({'onClick':function(){add();}});
	$('.edit').linkbutton({'onClick':function(){edit();}});
	$('.remove').linkbutton({'onClick':function(){remove();}});
	$('.send').linkbutton({'onClick':function(){send();}});
	DateRange.initDateBox($('#appointmentTime_start'), $('#appointmentTime_end'));
	statusArray.getFields();
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
	DateRange.resetDateBox($('#appointmentTime_start'), $('#appointmentTime_end'));
	find();
}
/*
 * 创建表格
 */
function createDg(){
	dgList=$('#dg').datagrid({    
	method: "post",url:ctx+'/smsNewhomeOrder/find', idField : 'id',singleSelect:true,
    fit : true,fitColumns : false,border : false,striped:true,
	pagination:true,rownumbers:true,pageNumber:1,pageSize : 20,pageList : [ 20, 30, 50 ],
    columns:[[    
		{field:'smsNewhomeOrder.id',title:'ID',hidden:true}
			,{field:'smsNewhomeOrder.orderId',title:'订单号',sortable:true,width:100,align:'left',halign:'center' }
			,{field:'smsOrder.status',title:'状态',sortable:true,width:100,align:'left',halign:'center',formatter:fmtStatus }
			,{field:'smsOrder.appointmentTime',title:'预约服务时间',sortable:true,width:100,align:'left',halign:'center',formatter: DzmFrame.EasyUI.fmtDatetime}
			,{field:'smsOrder.memberName',title:'会员',sortable:true,width:100,align:'left',halign:'center' }
			,{field:'smsOrder.memberPhone',title:'会员手机号',sortable:true,width:100,align:'left',halign:'center' }
			,{field:'smsOrder.address',title:'服务地址',sortable:true,width:100,align:'left',halign:'center' }
			,{field:'smsNewhomeOrder.area',title:'面积',sortable:true,width:100,align:'right',halign:'center' }
			,{field:'smsNewhomeOrder.amount',title:'金额',sortable:true,width:100,align:'right',halign:'right',formatter:DzmFrame.EasyUI.fmtMoney }
			,{field:'smsNewhomeOrder.diacount',title:'折扣金额',sortable:true,width:100,align:'right',halign:'right',formatter:DzmFrame.EasyUI.fmtMoney }
			,{field:'smsNewhomeOrder.cleaners',title:'保洁员',sortable:true,width:200,align:'left',halign:'center' }
			,{field:'smsOrder.orderTime',title:'下单时间',sortable:true,width:100,align:'left',halign:'center',formatter: DzmFrame.EasyUI.fmtDatetime}
    		    	    ]],
    sortName:'smsNewhomeOrder.orderId',sortOrder:'asc',
    enableHeaderClickMenu: false,
    enableHeaderContextMenu: false,
    toolbar:'#tb',
    loadFilter:function(data){
        	return DzmFrame.EasyUI.dataGridLoadFilter(data);
        }
	});
}
function fmtStatus(val){
	return statusArray.showDisplay(val);
}
/**
 * 新增
 */
function add(){
	dForm=$('#dlg').dialog({title:'新增',width: 800, height: 600,modal:true
		,href:ctx+'/smsNewhomeOrder/add',
		buttons:[
			{text:'提交',handler:function(){submit();}}
			,{text:'返回',handler:function(){dForm.panel('close');}}
		]
	});
}
/**
 * 派单
 */
function send(){
	var row = dgList.datagrid('getSelected');
	if(rowIsNull(row)) return;
	curRow = row; // 当前选择的行记录
	dialog=$('#dlg').dialog({title:'选择保洁员',width: 350, height: 250,modal:true
		,href:ctx+'/smsEmployee/showMultiEmployeeDialog',
		buttons:[
			{text:'提交',handler:function(){submitSend();}}
			,{text:'返回',handler:function(){dialog.panel('close');}}
		]
	});
}
/**
 * 派单提交
 */
function submitSend(){
	var els = $('.text-tags').find('input');
	if (els.size() <= 0){
		$.messager.alert('系统提示','请至少指定一名保洁员','error');
	} else{
		var cleaners = '';
		els.each(function(index, el){
			if (index == 0){
				cleaners += el.value;
			} else{
				cleaners += ',' + el.value;
			}
		})
		$.ajax({
				type:'post',
				url:ctx+"/smsNewhomeOrder/send/"+curRow['smsNewhomeOrder.orderId']+ '/' + curRow['smsNewhomeOrder.id'],
				data:{cleaners:cleaners},
				success: function(data){
					if (data.success){
						$.messager.alert('系统提示',data.msg,'info',function(){
							dialog.panel('close');
							find();
						});
					} else{
						$.messager.alert('系统提示',data.msg,'error');
					}
				}
			});
	}
}
/**
 * 编辑
 */
function edit(){
	var row = dgList.datagrid('getSelected');
	if(rowIsNull(row)) return;
	dForm=$('#dlg').dialog({title:'编辑',width: 800, height: 600,modal:true
		,href:ctx+'/smsNewhomeOrder/edit/'+row.id,
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
				url:ctx+"/smsNewhomeOrder/remove/"+row.id,
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
																																																												$('#cleaners').val(UE.getEditor('cleanersId').getContent());
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
