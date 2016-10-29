<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title></title>
	<%@ include file="/WEB-INF/views/include/meta.jsp"%>
	<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
	<%@ include file="/WEB-INF/views/include/easyui.jsp"%>
</head>
<body>
	<div id="smsEmployeeDialog_tb" style="padding:5px;height:auto">
        <div>
			<form id="smsEmployeeDialog_searchFrom" action="">
       	        <input type="text" name="filter_likes_name" class="easyui-textbox" data-options="width:150,prompt: '查询条件'"/>
				<a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" id="smsEmployeeDialog_find">查询</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true" id="smsEmployeeDialog_reset">重置</a>
			</form>
        </div> 
  	</div>
  	
	<table id="smsEmployeeDialog_dg"></table> 
	<script src="${ctxResources}/pages/modules/base/smsEmployeeDialog.js"></script>
</body>
</html>	