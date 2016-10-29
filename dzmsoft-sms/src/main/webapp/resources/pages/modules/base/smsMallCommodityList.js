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
var statusArray = new DataDictionary(DICTIONARY_FIELD.COMMODITY_STATUS);
/**
 * 初始化绑定事件
 */
function initActions(){
	$('#searchFrom_find').on('click',function(){find();});
	$('#searchFrom_reset').on('click',function(){reset();});
	$('.add').linkbutton({'onClick':function(){add();}});
	$('.edit').linkbutton({'onClick':function(){edit();}});
	$('.remove').linkbutton({'onClick':function(){remove();}});
	$('.online').linkbutton({'onClick':function(){online();}});
	$('.offline').linkbutton({'onClick':function(){offline();}});
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
	method: "post",url:ctx+'/smsMallCommodity/find', idField : 'id',singleSelect:true,
    fit : true,fitColumns : false,border : false,striped:true,
	pagination:true,rownumbers:true,pageNumber:1,pageSize : 20,pageList : [ 20, 30, 50 ],
    columns:[[    
    	    	    		    			{field:'id',title:'商品编号',sortable:true,width:100,align:'left',halign:'center'}
    		    	    		    			,{field:'name',title:'商品名称',sortable:true,width:100,align:'left',halign:'center' }
    		    	    		    			,{field:'category',title:'品类',sortable:true,width:100,align:'left',halign:'center' }
    		    	    		    			,{field:'brandId',title:'品牌',sortable:true,width:100,align:'left',halign:'center' }
    		    	    		    			,{field:'spec',title:'规格',sortable:true,width:100,align:'left',halign:'center' }
    		    	    		    			,{field:'model',title:'型号',sortable:true,width:100,align:'left',halign:'center' }
    		    	    		    			,{field:'originalPrice',title:'原价',sortable:true,width:100,align:'left',halign:'center' }
    		    	    		    			,{field:'price',title:'单价',sortable:true,width:100,align:'left',halign:'center' }
    		    	    		    			,{field:'onlineTime',title:'上架时间',sortable:true,width:100,align:'left',halign:'center' ,formatter:DzmFrame.EasyUI.fmtDatetime}
    		    	    		    			,{field:'offlineTime',title:'下架时间',sortable:true,width:100,align:'left',halign:'center' ,formatter:DzmFrame.EasyUI.fmtDatetime}
    		    	    		    			,{field:'status',title:'状态',sortable:true,width:100,align:'left',halign:'center' ,formatter:fmtStatus}
    		    	    		    			,{field:'saleQuantity',title:'销售数量',sortable:true,width:100,align:'left',halign:'center' }
    		    	    ]],
    sortName:'name',sortOrder:'asc',
    enableHeaderClickMenu: false,
    enableHeaderContextMenu: false,
    toolbar:'#tb',
    onClickRow:function(index,row){
		switch(row.status){
    	case COMMODITY_STATUS.ONLINE: 
    		$('.online').linkbutton('disable');
    		$('.offline').linkbutton('enable');
    		$('.remove').linkbutton('disable');
    		break;
    	case COMMODITY_STATUS.OFFLINE: 
    		$('.online').linkbutton('enable');
    		$('.offline').linkbutton('disable');
    		$('.remove').linkbutton('enable');
    		break;
    	case COMMODITY_STATUS.INIT: 
    		$('.online').linkbutton('enable');
    		$('.offline').linkbutton('enable');
    		$('.remove').linkbutton('enable');
    		break;
		default:
			$('.online').linkbutton('disable');
			$('.offline').linkbutton('disable');
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
	var url = ctx+'/smsMallCommodity/add';
	window.top.addTab('新增商品',url);
}
/**
 * 编辑
 */
function edit(){
	var row = dgList.datagrid('getSelected');
	if(rowIsNull(row)) return;
	var url = ctx+'/smsMallCommodity/edit/'+row.id;
	window.top.addTab('编辑商品',url);
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
				url:ctx+"/smsMallCommodity/remove/"+row.id,
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
 * 上架
 */
function online(){
	var row = dgList.datagrid('getSelected');
	if(rowIsNull(row)) return;
	parent.$.messager.confirm('提示', '您确定上架此商品吗？', function(data){
		if (data){
			$.ajax({
				type:'put',
				url:ctx+"/smsMallCommodity/online/"+row.id,
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
 * 下架
 */
function offline(){
	var row = dgList.datagrid('getSelected');
	if(rowIsNull(row)) return;
	parent.$.messager.confirm('提示', '您确定下架此商品吗？', function(data){
		if (data){
			$.ajax({
				type:'put',
				url:ctx+"/smsMallCommodity/offline/"+row.id,
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
																																																																					$('#detail').val(UE.getEditor('detailId').getContent());
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
