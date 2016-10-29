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
	<div id="smsEmployeeScheduleDialog_tb" style="padding:5px;height:auto">
        <div>
			<form id="smsEmployeeScheduleDialog_searchFrom" action="">
       	        <input type="text" name="filter_likes_name" class="easyui-textbox" data-options="width:150,prompt: '员工'"/>
       	        <input type="hidden" id="appointmentTimeDialog" name="appointmentTime" value="${appointmentTime }"/>
       	        <input type="hidden" id="timesDialog" name="times" value="${times }"/>
				<a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" id="smsEmployeeScheduleDialog_find">查询</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true" id="smsEmployeeScheduleDialog_reset">重置</a>
			</form>
        </div> 
  	</div>
  	
	<table id="smsEmployeeScheduleDialog_dg"></table> 
	<script src="${ctxResources}/pages/modules/base/smsEmployeeScheduleDialog.js"></script>
</body>
</html>	