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

	<form id="mainform" action="${ctx}/smsShift/${action}" method="post"
		enctype="multipart/form-data">
		<table class="formTable" width="100%" border="0" cellspacing="0"
			cellpadding="5">
			<tr>
				<td><input type="hidden" name="id" value="${smsShift.id}" /></td>
			</tr>
			<tr>
				<td width="25%" style="text-align: right">班次：</td>
				<td width="75%"><input id="name" name="name" style="width: 90%"
					class=" easyui-textbox "
					data-options="required:true ,validType:'length[1,30]'"
					value="${smsShift.name}"></td>
			</tr>
			<tr>
				<td width="25%" style="text-align: right">起始时间点：</td>
				<td width="75%"><input id="beginTime" name="beginTime"
					style="width: 90%" class="easyui-numberspinner"
					data-options="required:true ,min:0,max:24"
					value="${smsShift.beginTime}"></td>
			</tr>
			<tr>
				<td width="25%" style="text-align: right">截止时间点：</td>
				<td width="75%"><input id="endTime" name="endTime"
					style="width: 90%" class="easyui-numberspinner"
					data-options="required:true ,min:0,max:24"
					value="${smsShift.endTime}"></td>
			</tr>
			<tr>
				<td width="25%" style="text-align: right">生效日期：</td>
				<td width="75%"><input id="effectiveDate" name="effectiveDate"
					style="width: 90%" class="easyui-datebox"
					data-options="required:true " value="${smsShift.effectiveDate}">
				</td>
			</tr>
			<tr>
				<td width="25%" style="text-align: right">失效日期：</td>
				<td width="75%"><input id="expirationDate"
					name="expirationDate" style="width: 90%" class="easyui-datebox"
					data-options="required:true " value="${smsShift.expirationDate}">
				</td>
			</tr>
			<tr>
				<td width="25%" style="text-align: right">描述：</td>
				<td width="75%"><textarea id="description" name="description"
						style="width: 90%; height: 100px;">${smsShift.description}</textarea>
				</td>
			</tr>
		</table>
	</form>
	<script type="text/javascript">
		$(function() {
			initActions();
		});
		function initActions() {
			DateRange.initDateBox($('#effectiveDate'), $('#expirationDate'));
		}
	</script>
</body>
</html>
