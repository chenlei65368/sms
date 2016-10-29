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

	<form id="mainform" action="${ctx}/smsHousing/${action}" method="post"
		enctype="multipart/form-data">
		<table class="formTable" width="100%" border="0" cellspacing="0"
			cellpadding="5">
			<tr>
				<td><input type="hidden" name="id" value="${smsHousing.id}" />
				<input type="hidden" id="location" name="location" value="${smsHousing.location}" />
				<input type="hidden" id="division" name="division" value="${smsHousing.division}" />
				</td>
			</tr>
			<tr>
				<td width="25%" style="text-align: right">房源名称：</td>
				<td width="75%"><input id="name" name="name" style="width: 90%"
					value="${smsHousing.name}" class="easyui-combobox"
					data-options="required:true,mode:'remote',valueField:'id',textField:'text',loader:mapLoader,onSelect:selectHousing"></td>
			</tr>
			<tr>
				<td width="25%" style="text-align: right">描述：</td>
				<td width="75%"><textarea id="description" name="description"
						style="width: 90%; height: 100px;">${smsHousing.description}</textarea>
				</td>
			</tr>
		</table>
	</form>
	<script type="text/javascript">
		$(function() {
			initActions();
		});
		function initActions() {
		}
		function selectHousing(record){
			$('#location').val(record.location);
			$('#division').val(record.division);
		}
	</script>
</body>
</html>
