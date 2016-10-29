/**
 * Copyright (C), dzmsoft Co., Ltd
 */
$(function(){
	initActions();
	createSmsMallBrandDg();
});
var smsMallBrandDialogList;
/**
 * 初始化绑定事件
 */
function initActions(){
	$('#smsMallBrandDialog_find').on('click',function(){find();});
	$('#smsMallBrandDialog_reset').on('click',function(){reset();});
}
/**
 * 查询
 */
function find(){
	var obj=$("#smsMallBrandDialog_searchFrom").serializeObject();
	smsMallBrandDialogList.datagrid('load',obj); 
}
/**
 * 重置
 */
function reset(){
	$('#smsMallBrandDialog_searchFrom').form('clear');
	find();
}
/**
 * 创建系统表格
 */
function createSmsMallBrandDg(){
	$('#smsMallBrandDialog_dg').datagrid({    
		method: "post",url:ctx+'/smsMallBrand/find', idField : 'id',singleSelect:true,
	    fit : true,fitColumns : true,border : false,striped:true,
		pagination:true,rownumbers:true,pageNumber:1,pageSize : 20,pageList : [ 20, 30, 50 ],
	    columns:[[    
	            		    		    			{field:'id',title:'ID',hidden:true}
    		    		    		    			,{field:'name',title:'品牌名称',sortable:true,width:100,align:'left',halign:'center'}
    		    		    		    			,{field:'category',title:'商品类别',hidden:true}
    		    		    		    			,{field:'url',title:'品牌链接网站',sortable:true,width:100,align:'left',halign:'center',formatter:DzmFrame.EasyUI.fmtUrl}
    		    		    		    			,{field:'remark',title:'备注',sortable:true,width:100,align:'left',halign:'center'}
    		    			    ]],
	    sortName:'name',sortOrder:'asc',
    	enableHeaderClickMenu: false,
    	enableHeaderContextMenu: false,
    	toolbar:'#smsMallBrandDialog_tb',
	    onDblClickRow:function(index,row){
	    	window.closeSmsMallBrandDg({id:row.id,name:row.name,category:row.category});
	    }
	});
}
