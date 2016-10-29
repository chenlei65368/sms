<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/meta.jsp"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>
<link href="${ctxResources}/plugins/My97DatePicker/skin/WdatePicker.css" rel="stylesheet" type="text/css" />
<script src="${ctxResources}/plugins/My97DatePicker/WdatePicker.js"></script>
<script src="${ctxResources}/plugins/easyui/extensions/jquery.my97.js"></script>
</head>
<body class="easyui-layout">
	<div data-options="region:'west',border:false,split:true"
		title="未排班的员工" style="width:450px;">
		<div id="noScheduleDg_tb" style="padding: 5px; height: auto">
			<div>
				<form id="arrangeSearchFrom" action="">
					<input type="text" style="width: 90%"  id="monthCond" name="monthCond" class="Wdate" onfocus="WdatePicker({minDate:'2016-07-01',el:'monthCond', lang:'zh-cn',dateFmt:'yyyy-MM',onpicked:loadNoSchedule})" />
				</form>
				<table cellpadding="0" cellspacing="0">
					<tr>
						<shiro:hasPermission name="smsEmployeeSchedule:calendarSet">
							<td><a href="javascript:void(0)"
								class="easyui-linkbutton calendarSet" iconCls="icon-add" plain="true">设置排班日历</a>
							</td>
							<td><span class="toolbar-item dialog-tool-separator"></span>
							</td>
						</shiro:hasPermission>
						<shiro:hasPermission name="smsEmployeeSchedule:ruleSet">
							<td><a href="javascript:void(0)"
								class="easyui-linkbutton ruleSet" iconCls="icon-add" plain="true">设置排班规则</a>
							</td>
							<td><span class="toolbar-item dialog-tool-separator"></span>
							</td>
						</shiro:hasPermission>
						<shiro:hasPermission name="smsEmployeeSchedule:arrange">
							<td><a href="javascript:void(0)"
								class="easyui-linkbutton arrange" iconCls="icon-add" plain="true">排班</a>
							</td>
							<td><span class="toolbar-item dialog-tool-separator"></span>
							</td>
						</shiro:hasPermission>
					</tr>
				</table>
			</div>

		</div>
		<table id="noScheduleDg"></table>
	</div>
	<div data-options="region:'center',border:false,title:'时点管理'">
		<div id="tb" style="padding: 5px; height: auto">
			<div>
				<form id="searchFrom" action="">
					<input type="text" id="filter_eqd_day" class="easyui-datebox" name="filter_eqd_day" data-options="width:150,prompt: '排班日期'"/>
					<shiro:hasPermission name="smsEmployeeSchedule:find">
						<a href="#" class="easyui-linkbutton" iconCls="icon-search"
							plain="true" id="searchFrom_find">查询</a>
						<a href="#" class="easyui-linkbutton" iconCls="icon-reload"
							plain="true" id="searchFrom_reset">重置</a>
					</shiro:hasPermission>
				</form>
			</div>
	
		</div>
		<table id="dg"></table>
		<div id="dlg"></div>
	</div>
	<input type="hidden" id="selectEmpParams"  />
	<script
		src="${ctxResources}/pages/modules/base/smsEmployeeScheduleList.js"></script>
</body>
</html>