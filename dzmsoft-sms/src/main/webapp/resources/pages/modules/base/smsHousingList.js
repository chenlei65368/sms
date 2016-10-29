/**
 * Copyright (C), dzmsoft Co., Ltd
 */
																		/**
 * 初始加载
 */
$(function(){
	initActions();
	createRegionDg();
	createDg();
});
/**
 * 变量区
 */
var leftDgList; // 左列表
var leftId;
var leftForm;// 对话框
var rightDgList; // 右列表
var regionList;
var editIndexHouseBuilding = undefined;
var statusArray = new DataDictionary(DICTIONARY_FIELD.STATUS); 
var curHousingRow;
/**
 * 初始化绑定事件
 */
function initActions(){
	$('#searchFrom_find').on('click',function(){find();});
	$('#searchFrom_reset').on('click',function(){reset();});
	$('.add').linkbutton({'onClick':function(){add();}});
	$('.edit').linkbutton({'onClick':function(){edit();}});
	$('.remove').linkbutton({'onClick':function(){remove();}});
	$('.addHouseBuilding').linkbutton({'onClick':function(){addHouseBuilding();}});
	$('.removeHouseBuilding').linkbutton({'onClick':function(){removeHouseBuilding();}});
	statusArray.getFields();
}
var mapLoader = function(param,success,error){
	var q = param.q || '';
	$.ajax({
		url:'http://restapi.amap.com/v3/place/text',
		method:'get',
		data:{key:'7d421ed9007905333fd1ef0f14dba3ad',s:'rsv3',platform:"JS",offset:5,page:1,keywords:q,city:'武汉'},
		success:function(data){
			var datas = [];
			$.each(data.pois,function(index,record){
				datas.push({id:record.name, text:record.name,division:record.adname,location:record.location});
			})
			success(datas);
		},
		error:function(){
			 error.apply(this, arguments);
		}
	});
}
/**
 * 查询
 */
function find(){
	var obj=$("#searchFrom").serializeObject();
	leftDgList.datagrid('load',obj); 
}
/**
 * 重置
 */
function reset(){
	$('#searchFrom').form('clear');
	find();
}
function createRegionDg(){
	regionList = $('#regionDg').datalist({fit:true, plain:true, valueField:'id',textField:'name',singleSelect:true,
		onClickRow:function(index,row){
			leftDgList.datagrid('reload',{filter_eqs_division:row.name}); 
	    	$('#leftDgPanel').panel({title:"房源信息" + '('+row.name+')'});
	    }	
	});
	loadRegions();
}
function loadRegions(){
	var regions = new ComboBoxBean(ccs_ctx+'ccs14/'+DICTIONARY_FIELD.ADMIN_REGION,{valueField:'id',textField:'name'},{pid:$('#city').val()});
	regions.getFields();
	regionList.datalist('loadData',regions.getDatas().data);
}
/*
 * 创建表格
 */
function createDg(){
	leftDgList=$('#leftDg').datagrid({    
	method: "post",url:ctx+'/smsHousing/find', idField : 'id',singleSelect:true,
    fit : true,fitColumns : true,border : false,striped:true,
	pagination:true,rownumbers:true,pageNumber:1,pageSize : 20,pageList : [ 20, 30, 50 ],
    columns:[[    
    	    	    		    			{field:'id',title:'房源编号',sortable:true,width:100,align:"left",halign:'center'}
			    			,{field:'name',title:'房源名称',sortable:true,width:100,align:"left",halign:'center' }
			    			,{field:'supplier',title:'供应商',hidden:true }
    		    	    ]],
    sortName:'id',sortOrder:'asc',
    enableHeaderClickMenu: false,
    enableHeaderContextMenu: false,
    toolbar:'#tb',
    onClickRow:function(index,row){
	    	leftId = row.id;
	    	curHousingRow = row;// 选中行
	    	rightDgList.datagrid('reload',{filter_eqs_housingId:leftId}); 
	    	$('#rightListPanel').panel({title:"楼栋信息" + '('+row.name+')'});
	    },
    onLoadSuccess:function(data){
		if (data && data.total>0){
			// 默认选中第一行
			$('#leftDg').datagrid('selectRow',0);
			var row = $('#leftDg').datagrid('getSelected');
			leftId = row.id;
			curHousingRow = row;// 默认选中第一行
			createRightDg(leftId);
			$('#rightListPanel').panel({title:"楼栋信息" + '('+row.name+')'});
		} else{
			createRightDg('-1');
		}
    }
	});
	
}
function createRightDg(leftId){
	rightDgList = $('#rightDg').datagrid({    
	method: "post",url:ctx+'/smsHouseBuilding/find',queryParams:{filter_eqs_housingId:leftId}, idField : 'id',singleSelect:true,
    fit : true,fitColumns : true,border : false,striped:true,
	pagination:true,rownumbers:true,pageNumber:1,pageSize : 20,pageList : [ 20, 30, 50 ],
    columns:[[    
	{field:'id',title:'楼栋编号',hidden:true}
	,{field:'supplier',title:'供应商',hidden:true }
	,{field:'housingId',title:'房源',hidden:true }
	,{field:'name',title:'楼栋名称',sortable:true,width:100,align:"left",halign:'center',editor : {type:'textbox',options:{required:true} }}
	,{field:'description',title:'描述',sortable:true,width:200,align:"left",halign:'center',editor : {type:'textbox'} }
	,{field:'status',title:'状态',sortable:true,width:100,align:"left",halign:'center',formatter:function(val){return statusArray.showDisplay(val);} }
    		    	    ]],
    sortName:'name',sortOrder:'asc',
    enableHeaderClickMenu: false,
    enableHeaderContextMenu: false,
    toolbar:'#right_tb',
    autoFocusField:'name',
    autoEditing :true,
	extEditing : true,
	onClickCell : onClickCellHouseBuilding,
	onAfterEdit:function(index,record){
		$.fn.datagrid.extensions.onAfterEdit.apply(this, arguments);
		if (record.name == ''){
			$.messager.alert('系统提示','楼盘名称不能为空','info');
			rightDgList.datagrid('selectRow', index).datagrid(
					'beginEdit', index);
			return ;
		}
		saveHouseBuilding(record);
	}
    });
}
/**
 * 保存楼盘信息
 * @param record
 */
function saveHouseBuilding(record){
	$.ajax({
		url:ctx+'/smsHouseBuilding/add',
		method:'post',
		data:record,
		success:function(data){
			if (data.success){
				$.messager.alert('系统提示',data.msg,'info');
			} else{
				$.messager.alert('系统提示',data.msg,'error');
			}
		}
	});
}
/**
 * 选择单元格
 * 
 * @param index
 * @param field
 */
var onClickCellHouseBuilding = function(index, field) {
	if (editIndexHouseBuilding != index) {
		if (endEditingHouseBuilding()) {
			rightDgList.datagrid('selectRow', index).datagrid(
					'beginEdit', index);
			var ed = rightDgList.datagrid('getEditor', {
				index : index,
				field : field
			});
			if (ed) {
				($(ed.target).data('textbox') ? $(ed.target).textbox('textbox')
						: $(ed.target)).focus();
			}
			editIndexHouseBuilding = index;
		} else {
			setTimeout(function() {
				rightDgList.datagrid('selectRow', editIndexHouseBuilding);
			}, 0);
		}
	}
}
/**
 * 结束编辑
 * 
 * @returns {Boolean}
 */
var endEditingHouseBuilding = function() {
	if (editIndexHouseBuilding == undefined) {
		return true
	}
	if (rightDgList.datagrid('validateRow', editIndexHouseBuilding)) {
		rightDgList.datagrid('endEdit', editIndexHouseBuilding);
		editIndexHouseBuilding = undefined;
		return true;
	} else {
		return false;
	}
}
function addHouseBuilding(){
	if (endEditingHouseBuilding()) {
		rightDgList.datagrid('appendRow', {
			id : Math.guid(),status:DATA_STATUS.ENABLE,supplier:curHousingRow.supplier,housingId:curHousingRow.id
		});
		editIndexHouseBuilding = rightDgList.datagrid('getRows').length - 1;
		rightDgList.datagrid('selectRow', editIndexHouseBuilding)
				.datagrid('beginEdit', editIndexHouseBuilding);
	}
}
function removeHouseBuilding(){
	if (editIndexHouseBuilding == undefined) {
		return 
	}
	var row = rightDgList.datagrid('getSelected');
	$.messager.confirm('提示', '删除后无法恢复您确定要删除？', function(data){
		if (data){
			$.ajax({
				type:'get',
				url:ctx+"/smsHouseBuilding/remove/"+row.id,
				success: function(data){
					if (data.success){
						$.messager.alert('系统提示',data.msg,'info',function(){
							rightDgList.datagrid('cancelEdit', editIndexHouseBuilding).datagrid('deleteRow',
									editIndexHouseBuilding);
							editIndexHouseBuilding = undefined;
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
 * 新增
 */
function add(){
	leftForm=$('#dlg').dialog({title:'新增',width: 380, height: 380,modal:true
		,href:ctx+'/smsHousing/add',
		buttons:[
			{text:'提交',handler:function(){submit();}}
			,{text:'返回',handler:function(){leftForm.panel('close');}}
		]
	});
}
/**
 * 编辑
 */
function edit(){
	var row = leftDgList.datagrid('getSelected');
	if(rowIsNull(row)) return;
	leftForm=$('#dlg').dialog({title:'编辑',width: 380, height: 380,modal:true
		,href:ctx+'/smsHousing/edit/'+row.id,
		buttons:[
			{text:'提交',handler:function(){submit();}}
			,{text:'返回',handler:function(){leftForm.panel('close');}}
		]
	});
}
/**
 * 删除用户
 */
function remove(){
	var row = leftDgList.datagrid('getSelected');
	if(rowIsNull(row)) return;
	parent.$.messager.confirm('提示', '删除后无法恢复您确定要删除？', function(data){
		if (data){
			$.ajax({
				type:'get',
				url:ctx+"/smsHousing/remove/"+row.id,
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
					leftForm.panel('close');
					find();
				});
			} else{
				$.messager.alert('系统提示',data.msg,'error');
			}
		}
	});
}
