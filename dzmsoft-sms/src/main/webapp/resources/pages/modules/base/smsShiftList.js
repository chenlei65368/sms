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
var statusArray = new DataDictionary(DICTIONARY_FIELD.STATUS);
/**
 * 初始化绑定事件
 */
function initActions(){
	$('#searchFrom_find').on('click',function(){find();});
	$('#searchFrom_reset').on('click',function(){reset();});
	$('.add').linkbutton({'onClick':function(){add();}});
	$('.edit').linkbutton({'onClick':function(){edit();}});
	$('.remove').linkbutton({'onClick':function(){remove();}});
	$('.enable').linkbutton({'onClick':function(){enable();}});
	$('.disable').linkbutton({'onClick':function(){disable();}});
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
	find();
}
/*
 * 创建表格
 */
function createDg(){
	dgList=$('#dg').datagrid({    
	method: "post",url:ctx+'/smsShift/find', idField : 'id',singleSelect:true,
    fit : true,fitColumns : true,border : false,striped:true,
	pagination:true,rownumbers:true,pageNumber:1,pageSize : 20,pageList : [ 20, 30, 50 ],
    columns:[[    
    	    	    		    			{field:'id',title:'ID',hidden:true}
    		    	    		    			,{field:'name',title:'班次',sortable:true,width:100,align:'left',halign:'center' }
    		    	    		    			,{field:'beginTime',title:'起始时间点',sortable:true,width:100,align:'left',halign:'right' }
    		    	    		    			,{field:'endTime',title:'截止时间点',sortable:true,width:100,align:'left',halign:'right' }
    		    	    		    			,{field:'status',title:'状态',sortable:true,width:100,align:'left',halign:'center',formatter:fmtStatus }
    		    	    		    			,{field:'effectiveDate',title:'生效日期',sortable:true,width:100,align:'left',halign:'center' ,formatter:DzmFrame.EasyUI.fmtYyyyMMdd}
    		    	    		    			,{field:'expirationDate',title:'失效日期',sortable:true,width:100,align:'left',halign:'center' ,formatter:DzmFrame.EasyUI.fmtYyyyMMdd}
    		    	    		    			,{field:'description',title:'描述',sortable:true,width:100,align:'left',halign:'center' }
    		    	    ]],
    sortName:'name',sortOrder:'asc',
    enableHeaderClickMenu: false,
    enableHeaderContextMenu: false,
    toolbar:'#tb',
    onClickRow:function(index,row){
		switch(row.status){
    	case DATA_STATUS.ENABLE: 
    		$('.enable').linkbutton('disable');
    		$('.disable').linkbutton('enable');
    		$('.remove').linkbutton('disable');
    		break;
    	case DATA_STATUS.DISABLED:
    		$('.enable').linkbutton('enable');
    		$('.disable').linkbutton('disable');
    		$('.remove').linkbutton('enable');
    		break;
    	case DATA_STATUS.INIT:
    		$('.enable').linkbutton('enable');
    		$('.disable').linkbutton('enable');
    		$('.remove').linkbutton('enable');
    		break;
		default:
			$('.enable').linkbutton('disable');
			$('.disable').linkbutton('disable');
			$('.remove').linkbutton('disable');
			break;
		}
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
	dForm=$('#dlg').dialog({title:'新增',width: 380, height: 380,modal:true
		,href:ctx+'/smsShift/add',
		buttons:[
			{text:'提交',handler:function(){submit();}}
			,{text:'返回',handler:function(){dForm.panel('close');}}
		]
	});
}
/**
 * 编辑
 */
function edit(){
	var row = dgList.datagrid('getSelected');
	if(rowIsNull(row)) return;
	dForm=$('#dlg').dialog({title:'编辑',width: 380, height: 380,modal:true
		,href:ctx+'/smsShift/edit/'+row.id,
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
				url:ctx+"/smsShift/remove/"+row.id,
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
 * 启用
 */
function enable(){
	var row = dgList.datagrid('getSelected');
	if(rowIsNull(row)) return;
	parent.$.messager.confirm('提示', '您确定启用此班次吗？', function(data){
		if (data){
			$.ajax({
				type:'put',
				url:ctx+"/smsShift/enable/"+row.id,
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
 * 禁用
 */
function disable(){
	var row = dgList.datagrid('getSelected');
	if(rowIsNull(row)) return;
	parent.$.messager.confirm('提示', '您确定禁用此班次吗？', function(data){
		if (data){
			$.ajax({
				type:'put',
				url:ctx+"/smsShift/disable/"+row.id,
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
