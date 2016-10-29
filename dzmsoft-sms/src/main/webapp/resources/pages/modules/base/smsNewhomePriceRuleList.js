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
/**
 * 初始化绑定事件
 */
function initActions(){
	$('#searchFrom_find').on('click',function(){find();});
	$('#searchFrom_reset').on('click',function(){reset();});
	$('.add').linkbutton({'onClick':function(){add();}});
	$('.edit').linkbutton({'onClick':function(){edit();}});
	$('.remove').linkbutton({'onClick':function(){remove();}});
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
	method: "post",url:ctx+'/smsNewhomePriceRule/find', idField : 'id',singleSelect:true,
    fit : true,fitColumns : true,border : false,striped:true,
	pagination:true,rownumbers:true,pageNumber:1,pageSize : 20,pageList : [ 20, 30, 50 ],
    columns:[[    
    	    	    		    			{field:'id',title:'ID',hidden:true}
    		    	    		    			    			    			    			    			,{field:'name',title:'名称',sortable:true,width:100,align:"left",halign:'center' }
    		    	    		    			    			    			    			,{field:'status',title:'状态',sortable:true,width:100,align:"left",halign:'center' }
    		    	    		    			    			    			    			,{field:'description',title:'描述',sortable:true,width:100,align:"left",halign:'center' }
    		    	    		    			    			    			    			,{field:'price',title:'每平米单价',sortable:true,width:100,align:"right",halign:'center' ,formatter:DzmFrame.EasyUI.fmtMoney}
    		    	    		    			    			    			    			,{field:'creator',title:'创建者',sortable:true,width:100,align:"left",halign:'center' }
    		    	    		    			    			    			    			,{field:'createTime',title:'创建时间',sortable:true,width:100,align:"left",halign:'center' ,formatter:DzmFrame.EasyUI.fmtDatetime}
    		    	    		    			    			    			    			,{field:'detail',title:'详情',sortable:true,width:100,align:"left",halign:'center' }
    		    	    		    			    			    			    			,{field:'effectiveDate',title:'生效日期',sortable:true,width:100,align:"left",halign:'center' ,formatter:DzmFrame.EasyUI.fmtYyyyMMdd}
    		    	    		    			    			    			    			,{field:'expirationDate',title:'失效日期',sortable:true,width:100,align:"left",halign:'center' ,formatter:DzmFrame.EasyUI.fmtYyyyMMdd}
    		    	    		    			    			    			    			,{field:'deployTime',title:'发布时间',sortable:true,width:100,align:"left",halign:'center' ,formatter:DzmFrame.EasyUI.fmtDatetime}
    		    	    		    			    			    			    			,{field:'supplier',title:'供应商',sortable:true,width:100,align:"left",halign:'center' }
    		    	    ]],
    sortName:'name',sortOrder:'asc',
    enableHeaderClickMenu: false,
    enableHeaderContextMenu: false,
    toolbar:'#tb'
	});
}
/**
 * 新增
 */
function add(){
		var url = ctx+'/smsNewhomePriceRule/add';
	window.top.addTab('新增新居开荒计价规则',url);
	}
/**
 * 编辑
 */
function edit(){
	var row = dgList.datagrid('getSelected');
	if(rowIsNull(row)) return;
		var url = ctx+'/smsNewhomePriceRule/edit/'+row.id;
	window.top.addTab('编辑新居开荒计价规则',url);
	}
/**
 * 删除
 */
function remove(){
	var row = dgList.datagrid('getSelected');
	if(rowIsNull(row)) return;
	parent.$.messager.confirm('提示', '删除后无法恢复您确定要删除？', function(data){
		if (data){
			$.ajax({
				type:'get',
				url:ctx+"/smsNewhomePriceRule/remove/"+row.id,
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
	parent.$.messager.confirm('提示', '您确定要启用吗？', function(data){
		if (data){
			$.ajax({
				type:'put',
				url:ctx+"/smsNewhomePriceRule/enable/"+row.id,
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
	parent.$.messager.confirm('提示', '您确定要禁用？', function(data){
		if (data){
			$.ajax({
				type:'put',
				url:ctx+"/smsNewhomePriceRule/disable/"+row.id,
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
