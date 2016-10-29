/**
 * Copyright (C), dzmsoft Co., Ltd
 */
/**
 * 初始化绑定事件
 */
function initActions(){
		$('.addSmsMemberAddress').linkbutton({'onClick':function(){addSmsMemberAddress();}});		
	$('.removeSmsMemberAddress').linkbutton({'onClick':function(){removeSmsMemberAddress();}});
	initSmsMemberAddress();
	loadSmsMemberAddress();
	}
/**
 * 初始化方案明细
 */
	 /**
	  * 变量区
	  */
	 	 	var SmsMemberAddressGrid;
	 	var editIndexSmsMemberAddress=undefined;
		// 初始化明细
	var initSmsMemberAddress = function(){
				var action = $('#action').val();
		SmsMemberAddressGrid = $('#SmsMemberAddressGrid').datagrid({  
		idField : 'id',singleSelect:true,
	    fit:true, fitColumns : true,border : false,striped:true,
		pagination:true,rownumbers:true,pageNumber:1,pageSize : 20,pageList : [ 20, 30, 50 ],
	    columns:[[    
				    		    			    			{field:'id',title:'ID',hidden:true}
	    			    		    			    			,{field:'isDefault',title:'默认',sortable:true,width:100,align:'left',halign:'center' }
	    			    		    			    			,{field:'city',title:'市',sortable:true,width:100,align:'left',halign:'center' }
	    			    		    			    			,{field:'division',title:'区',sortable:true,width:100,align:'left',halign:'center' }
	    			    		    			    			,{field:'shortAddress',title:'短地址',sortable:true,width:100,align:'left',halign:'center' }
	    			    		    			    			,{field:'longAddress',title:'长地址',sortable:true,width:100,align:'left',halign:'center' }
	    			    		    			    			,{field:'ucsId',title:'会员',sortable:true,width:100,align:'left',halign:'center' }
	    			    		    			    			,{field:'province',title:'省',sortable:true,width:100,align:'left',halign:'center' }
	    			    		    			    			,{field:'cityName',title:'城市名称',sortable:true,width:100,align:'left',halign:'center' }
	    			    		    			    			,{field:'divisionName',title:'区县名称',sortable:true,width:100,align:'left',halign:'center' }
	    			    		    ]],
	    	    toolbar:'SmsMemberAddress_tb',
	    enableHeaderClickMenu: false,
	    enableHeaderContextMenu: false,
	    autoEditing:(action == 'view')?false:true,
	    extEditing:(action == 'view')?false:true,
	    onClickCell:onClickCellSmsMemberAddress
		});
	};
	// 加载数据
	var loadSmsMemberAddress = function(){
		var action = $('#action').val();
		if (action == 'edit' || action == 'view'){
			var datasSmsMemberAddress = $("#datasSmsMemberAddress").val();
			if (!$.string.isNullOrEmpty(datasSmsMemberAddress)){
				var datasJsonSmsMemberAddress = $.parseJSON(datasSmsMemberAddress);
								SmsMemberAddressGrid.datagrid('loadData',datasJsonSmsMemberAddress);
			}
		}
	}
	/**
	 * 选择单元格
	 * @param index
	 * @param field
	 */
	var onClickCellSmsMemberAddress = function (index, field){
	    if (editIndexSmsMemberAddress != index){
	        if (endEditingSmsMemberAddress()){
	        	SmsMemberAddressGrid
	        	.datagrid('selectRow', index).datagrid('beginEdit', index);
	            var ed = SmsMemberAddressGrid
	            .datagrid('getEditor', {index:index,field:field});
	            if (ed){
	                ($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
	            }
	            editIndexSmsMemberAddress = index;
	        } else {
	            setTimeout(function(){
	            	SmsMemberAddressGrid
	            	.datagrid('selectRow', editIndexSmsMemberAddress);
	            },0);
	        }
	    }
	}
	/**
	 * 结束编辑
	 * @returns {Boolean}
	 */
	var endEditingSmsMemberAddress = function(){
		if (editIndexSmsMemberAddress == undefined){return true}
	    if (SmsMemberAddressGrid
	    	.datagrid('validateRow', editIndexSmsMemberAddress)){
	    	SmsMemberAddressGrid
	    	.datagrid('endEdit', editIndexSmsMemberAddress);
	        editIndexSmsMemberAddress = undefined;
	        return true;
	    } else {
	        return false;
	    }
	}
	/**
	 * 新增
	 */
	var addSmsMemberAddress = function(){
		if (endEditingSmsMemberAddress()){
			SmsMemberAddressGrid
			.datagrid('appendRow',{id:Math.guid()});
	        editIndexSmsMemberAddress = SmsMemberAddressGrid
	        .datagrid('getRows').length-1;
	        SmsMemberAddressGrid
	        .datagrid('selectRow', editIndexSmsMemberAddress)
	                .datagrid('beginEdit', editIndexSmsMemberAddress);
		}
		
	}
	/**
	 * 删除
	 */
	var removeSmsMemberAddress = function (){
		if (editIndexSmsMemberAddress == undefined){return}
		SmsMemberAddressGrid
		.datagrid('cancelEdit', editIndexSmsMemberAddress)
	    	.datagrid('deleteRow', editIndexSmsMemberAddress);
		editIndexSmsMemberAddress = undefined;
	}

/**
 * 初始加载
 */
$(function(){
	initActions();
});


