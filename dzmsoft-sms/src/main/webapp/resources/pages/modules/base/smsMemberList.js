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
	method: "post",url:ctx+'/smsMember/find', idField : 'id',singleSelect:true,
    fit : true,fitColumns : true,border : false,striped:true,
	pagination:true,rownumbers:true,pageNumber:1,pageSize : 20,pageList : [ 20, 30, 50 ],
    columns:[[    
    	    	    		    			{field:'id',title:'ID',hidden:true}
    		    	    		    			,{field:'name',title:'姓名',sortable:true,width:100,align:'left',halign:'center' }
    		    	    		    			,{field:'sex',title:'性别',sortable:true,width:100,align:'left',halign:'center' }
    		    	    		    			,{field:'mobile',title:'手机号',sortable:true,width:100,align:'left',halign:'center' }
    		    	    		    			,{field:'ucsId',title:'用户ID',sortable:true,width:100,align:'left',halign:'center' }
    		    	    		    			,{field:'createTime',title:'创建时间',sortable:true,width:100,align:'left',halign:'center' ,formatter:DzmFrame.EasyUI.fmtDatetime}
    		    	    		    			,{field:'city',title:'',sortable:true,width:100,align:'left',halign:'center' }
    		    	    		    			,{field:'province',title:'省',sortable:true,width:100,align:'left',halign:'center' }
    		    	    		    			,{field:'division',title:'区县',sortable:true,width:100,align:'left',halign:'center' }
    		    	    		    			,{field:'headerPic',title:'头像',sortable:true,width:100,align:'left',halign:'center' }
    		    	    ]],
    sortName:'name',sortOrder:'asc',
    enableHeaderClickMenu: false,
    enableHeaderContextMenu: false,
    toolbar:'#tb',
    onClickRow:function(index,row){
    	    }
	});
}
/**
 * 新增
 */
function add(){
	dForm=$('#dlg').dialog({title:'新增',width: 380, height: 380,modal:true
		,href:ctx+'/smsMember/add',
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
		,href:ctx+'/smsMember/edit/'+row.id,
		buttons:[
			{text:'提交',handler:function(){submit();}}
			,{text:'返回',handler:function(){dForm.panel('close');}}
		]
	});
}
/**
 * 查看
 */
function view(){
	var row = dgList.datagrid('getSelected');
	if(rowIsNull(row)) return;
	dForm=$('#dlg').dialog({title:'查看',width: 380, height: 380,modal:true
		,href:ctx+'/smsMember/view/'+row.id,
		buttons:[
			{text:'返回',handler:function(){dForm.panel('close');}}
		]
	});
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
				url:ctx+"/smsMember/remove/"+row.id,
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
																																																																																																							var datasSmsMemberAddress = $('#SmsMemberAddressGrid').datagrid('getData');
										var datasJsonSmsMemberAddress = JSON.stringify(datasSmsMemberAddress.rows)
					$('#datasSmsMemberAddress').val(datasJsonSmsMemberAddress);
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
