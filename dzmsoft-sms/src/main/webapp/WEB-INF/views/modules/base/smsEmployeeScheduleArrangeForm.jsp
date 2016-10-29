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
	<div class="easyui-layout" style="width: 730px; height: 520px;">
		<div data-options="region:'north',border:false" style="height: 100px;">
			<form id="arrangeForm" action="${ctx}/smsEmployeeSchedule/arrange"
				method="post">
				<input type="hidden" id="empDetail" name="empDetail"/>
				<input type="hidden" id="dateDetail" name="dateDetail"/>
				<table class="formTable" width="100%" border="0" cellspacing="0"
					cellpadding="5">
					<tr>
						<td width="25%" style="text-align: right">班次：</td>
						<td width="75%"><input id="shift" name="shift" style="width: 90%"
							 data-options="required:true"
							value="${smsEmployeeScheduleArrangeDto.shift}"></td>
					</tr>
					<tr>
						<td width="25%" style="text-align: right">排班月份：</td>
						<td width="75%">
							<input type="text" style="width: 90%"  id="month" name="month" class="Wdate" onfocus="WdatePicker({minDate:'2016-07-01',el:'month', lang:'zh-cn',dateFmt:'yyyy-MM',onpicked:loadDates})"
						data-options="width:150,prompt: '排班年月'" />
						</td>
					</tr>
					</tr>
				</table>
			</form>
		</div>
		<div data-options="region:'center',border:false">
			<div class="easyui-layout" data-options="fit:true">
				<div data-options="region:'center',fit:true">
					<div title="待排班的员工" style="padding: 5px;">
						<div id="empLine_tb" style="padding: 5px; height: auto">
							<table cellpadding="0" cellspacing="0">
								<td><a href="javascript:void(0)"
										class="easyui-linkbutton removeEmpLine"
										iconCls="icon-remove" plain="true">删除</a></td>
									<td><span class="toolbar-item dialog-tool-separator"></span>
									</td>
							</table>
						</div>
						<table id="empGrid"></table>
					</div>
				</div>
				<div data-options="region:'east',split:true" width="150px" title="排班日期">
					<div id="dateList"></div>
				</div>
			</div>
		</div>

		<script src="${ctxResources}/pages/modules/base/smsEmployeeScheduleArrangeForm.js"></script>
</body>
</html>
