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
	dgList.treegrid('load',obj); 
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
	dgList=$('#dg').treegrid({    
	method: "post",url:ctx+'/smsEmployeeTeam/find', 
	idField : 'id',treeField:'name',parentField : 'pid',dataPlain:true,singleSelect:true,fit : true,fitColumns : true,border : false,striped:true,rownumbers:true,
    columns:[[    
    	    	    		    			    				{field:'id',title:'员工组编号',hidden:true}
    			    		    	    		    			,{field:'name',title:'名称',sortable:true,width:100,align:'left',halign:'center'  }
    		    	    		    			,{field:'status',title:'状态',sortable:true,width:100,align:'left',halign:'center' }
    		    	    		    			,{field:'description',title:'描述',sortable:true,width:100,align:'left',halign:'center' }
    		    	    		    			    				,{field:'pid',title:'上级员工组',hidden:true}
    			    		    	    		    			,{field:'createTime',title:'创建时间',sortable:true,width:100,align:'left',halign:'center' ,formatter:fmtDatetime}
    		    	    		    			,{field:'creator',title:'创建者',sortable:true,width:100,align:'left',halign:'center' }
    		    	    		    			,{field:'leader',title:'负责人',sortable:true,width:100,align:'left',halign:'center' }
    		    	    		    			,{field:'supplier',title:'供应商',sortable:true,width:100,align:'left',halign:'center' }
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
	dForm=$('#dlg').dialog({title:'新增',width: 380, height: 380,modal:true
		,href:ctx+'/smsEmployeeTeam/add',
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
	var row = dgList.treegrid('getSelected');
	if(rowIsNull(row)) return;
	dForm=$('#dlg').dialog({title:'编辑',width: 380, height: 380,modal:true
		,href:ctx+'/smsEmployeeTeam/edit/'+row.id,
		buttons:[
			{text:'提交',handler:function(){submit();}}
			,{text:'返回',handler:function(){dForm.panel('close');}}
		]
	});
}
/**
 * 删除
 */
function remove(){
	var row = dgList.treegrid('getSelected');
	if(rowIsNull(row)) return;
	parent.$.messager.confirm('提示', '删除后无法恢复您确定要删除？', function(data){
		if (data){
			$.ajax({
				type:'get',
				url:ctx+"/smsEmployeeTeam/remove/"+row.id,
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
