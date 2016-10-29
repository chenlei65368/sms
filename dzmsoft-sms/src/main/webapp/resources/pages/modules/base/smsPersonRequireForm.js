/**
 * Copyright (C), dzmsoft Co., Ltd
 */
/**
 * 初始化绑定事件
 */
function initActions(){
	$('.addSmsPersonRequireLine').linkbutton({'onClick':function(){addSmsPersonRequireLine();}});		
	$('.removeSmsPersonRequireLine').linkbutton({'onClick':function(){removeSmsPersonRequireLine();}});
	initSmsPersonRequireLine();
	loadSmsPersonRequireLine();
	DateRange.initDateBox($('#effectiveDate'), $('#expirationDate'));
	}
/**
 * 初始化方案明细
 */
	//
	var editIndexSmsPersonRequireLine=undefined;
	var SmsPersonRequireLineGrid;
		// 初始化明细
	var initSmsPersonRequireLine = function(){
				var action = $('#action').val();
		SmsPersonRequireLineGrid = $('#SmsPersonRequireLineGrid').datagrid({  
		idField : 'id',singleSelect:true,
	    fit:true, fitColumns : true,border : false,striped:true,
		pagination:true,rownumbers:true,pageNumber:1,pageSize : 20,pageList : [ 20, 30, 50 ],
	    columns:[[    
    			{field:'id',title:'ID',hidden:true}
	    			,{field:'requirement',title:'个性需求',sortable:true,width:100,align:'left',halign:'center',editor:{type:'textbox',options:{required:true} } }
	    			    		    ]],
	    	    toolbar:'SmsPersonRequireLine_tb',
	    enableHeaderClickMenu: false,
	    enableHeaderContextMenu: false,
	    autoEditing:(action == 'view')?false:true,
	    extEditing:(action == 'view')?false:true,
	    onClickCell:onClickCellSmsPersonRequireLine
		});
	};
	// 加载数据
	var loadSmsPersonRequireLine = function(){
		var action = $('#action').val();
		if (action == 'edit' || action == 'view'){
			var datasSmsPersonRequireLine = $("#datasSmsPersonRequireLine").val();
			if (!$.string.isNullOrEmpty(datasSmsPersonRequireLine)){
				var datasJsonSmsPersonRequireLine = $.parseJSON(datasSmsPersonRequireLine);
								SmsPersonRequireLineGrid.datagrid('loadData',datasJsonSmsPersonRequireLine);
			}
		} 
	}
	/**
	 * 选择单元格
	 * @param index
	 * @param field
	 */
	var onClickCellSmsPersonRequireLine = function (index, field){
	    if (editIndexSmsPersonRequireLine != index){
	        if (endEditingSmsPersonRequireLine()){
	        	SmsPersonRequireLineGrid
	        	.datagrid('selectRow', index).datagrid('beginEdit', index);
	            var ed = SmsPersonRequireLineGrid
	            .datagrid('getEditor', {index:index,field:field});
	            if (ed){
	                ($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
	            }
	            editIndexSmsPersonRequireLine = index;
	        } else {
	            setTimeout(function(){
	            	SmsPersonRequireLineGrid
	            	.datagrid('selectRow', editIndexSmsPersonRequireLine);
	            },0);
	        }
	    }
	}
	/**
	 * 结束编辑
	 * @returns {Boolean}
	 */
	var endEditingSmsPersonRequireLine = function(){
		if (editIndexSmsPersonRequireLine == undefined){return true}
	    if (SmsPersonRequireLineGrid
	    	.datagrid('validateRow', editIndexSmsPersonRequireLine)){
	    	SmsPersonRequireLineGrid
	    	.datagrid('endEdit', editIndexSmsPersonRequireLine);
	        editIndexSmsPersonRequireLine = undefined;
	        return true;
	    } else {
	        return false;
	    }
	}
	/**
	 * 新增
	 */
	var addSmsPersonRequireLine = function(){
		if (endEditingSmsPersonRequireLine()){
			SmsPersonRequireLineGrid
			.datagrid('appendRow',{id:Math.guid()});
	        editIndexSmsPersonRequireLine = SmsPersonRequireLineGrid
	        .datagrid('getRows').length-1;
	        SmsPersonRequireLineGrid
	        .datagrid('selectRow', editIndexSmsPersonRequireLine)
	                .datagrid('beginEdit', editIndexSmsPersonRequireLine);
		}
		
	}
	/**
	 * 删除
	 */
	var removeSmsPersonRequireLine = function (){
		if (editIndexSmsPersonRequireLine == undefined){return}
		SmsPersonRequireLineGrid
		.datagrid('cancelEdit', editIndexSmsPersonRequireLine)
	    	.datagrid('deleteRow', editIndexSmsPersonRequireLine);
		editIndexSmsPersonRequireLine = undefined;
	}

/**
 * 初始加载
 */
$(function(){
	initActions();
});


