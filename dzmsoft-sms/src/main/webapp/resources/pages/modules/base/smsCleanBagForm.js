/**
 * Copyright (C), dzmsoft Co., Ltd
 */

/**
 * 初始化绑定事件
 */
function initActions() {
	$('.addSmsCleanBagLine').linkbutton({
		'onClick' : function() {
			addSmsCleanBagLine();
		}
	});
	$('.removeSmsCleanBagLine').linkbutton({
		'onClick' : function() {
			removeSmsCleanBagLine();
		}
	});
	initSmsCleanBagLine();
	loadSmsCleanBagLine();
	DateRange.initDateBox($('#effectiveDate'), $('#expirationDate'));
}
/**
 * 初始化方案明细
 */
//
var editIndexSmsCleanBagLine = undefined;
// 初始化明细
var initSmsCleanBagLine = function() {
	var action = $('#action').val();
	SmsCleanBagLineGrid = $('#SmsCleanBagLineGrid').datagrid({
		idField : 'id',
		singleSelect : true,
		fit : true,
		fitColumns : true,
		border : false,
		striped : true,
		pagination : true,
		rownumbers : true,
		pageNumber : 1,
		pageSize : 20,
		pageList : [ 20, 30, 50 ],
		columns : [ [ {
			field : 'id',
			title : 'ID',
			hidden : true
		}, {
			field : 'item',
			title : '物品名称',
			sortable : true,
			width : 100,
			align : 'left',
			halign : 'center',
			editor : {type:'textbox',options:{required:true} }
		}, {
			field : 'price',
			title : '价格',
			sortable : true,
			width : 100,
			align : 'right',
			halign : 'center',
			formatter : DzmFrame.EasyUI.fmtMoney,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					precision : 2
				}
			}
		} ] ],
		toolbar : 'SmsCleanBagLine_tb',
		enableHeaderClickMenu : false,
		enableHeaderContextMenu : false,
		autoEditing : (action == 'view') ? false : true,
		extEditing : (action == 'view') ? false : true,
		onClickCell : onClickCellSmsCleanBagLine
	});
};
// 加载数据
var loadSmsCleanBagLine = function() {
	var action = $('#action').val();
	if (action == 'edit' || action == 'view') {
		var datasSmsCleanBagLine = $("#datasSmsCleanBagLine").val();
		if (!$.string.isNullOrEmpty(datasSmsCleanBagLine)) {
			var datasJsonSmsCleanBagLine = $.parseJSON(datasSmsCleanBagLine);
			SmsCleanBagLineGrid.datagrid('loadData', datasJsonSmsCleanBagLine);
		}
	}
}
/**
 * 选择单元格
 * 
 * @param index
 * @param field
 */
var onClickCellSmsCleanBagLine = function(index, field) {
	if (editIndexSmsCleanBagLine != index) {
		if (endEditingSmsCleanBagLine()) {
			SmsCleanBagLineGrid.datagrid('selectRow', index).datagrid(
					'beginEdit', index);
			var ed = SmsCleanBagLineGrid.datagrid('getEditor', {
				index : index,
				field : field
			});
			if (ed) {
				($(ed.target).data('textbox') ? $(ed.target).textbox('textbox')
						: $(ed.target)).focus();
			}
			editIndexSmsCleanBagLine = index;
		} else {
			setTimeout(function() {
				SmsCleanBagLineGrid.datagrid('selectRow', editIndexSmsCleanBagLine);
			}, 0);
		}
	}
}
/**
 * 结束编辑
 * 
 * @returns {Boolean}
 */
var endEditingSmsCleanBagLine = function() {
	if (editIndexSmsCleanBagLine == undefined) {
		return true
	}
	if (SmsCleanBagLineGrid.datagrid('validateRow', editIndexSmsCleanBagLine)) {
		SmsCleanBagLineGrid.datagrid('endEdit', editIndexSmsCleanBagLine);
		editIndexSmsCleanBagLine = undefined;
		return true;
	} else {
		return false;
	}
}
/**
 * 新增
 */
var addSmsCleanBagLine = function() {
	if (endEditingSmsCleanBagLine()) {
		SmsCleanBagLineGrid.datagrid('appendRow', {
			id : Math.guid()
		});
		editIndexSmsCleanBagLine = SmsCleanBagLineGrid.datagrid('getRows').length - 1;
		SmsCleanBagLineGrid.datagrid('selectRow', editIndexSmsCleanBagLine)
				.datagrid('beginEdit', editIndexSmsCleanBagLine);
	}

}
/**
 * 删除
 */
var removeSmsCleanBagLine = function() {
	if (editIndexSmsCleanBagLine == undefined) {
		return 
	}
	SmsCleanBagLineGrid.datagrid('cancelEdit', editIndexSmsCleanBagLine).datagrid('deleteRow',
			editIndexSmsCleanBagLine);
	editIndexSmsCleanBagLine = undefined;
}

/**
 * 初始加载
 */
$(function() {
	initActions();
});
