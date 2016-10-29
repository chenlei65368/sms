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

	<form id="mainform" action="${ctx}/smsCoupon/${action}" method="post"
		enctype="multipart/form-data">
		<table class="formTable" width="100%" border="0" cellspacing="0"
			cellpadding="5">
			<tr>
				<td><input type="hidden" name="id" value="${smsCoupon.id}" /></td>
			</tr>
			<tr>
				<td width="25%" style="text-align: right">面值：</td>
				<td width="75%"><input id="faceValue" name="faceValue"
					style="width: 90%" class="easyui-numberspinner "
					data-options="required:true,min:1 " value="${smsCoupon.faceValue}">
				</td>
			</tr>
			<tr>
				<td width="25%" style="text-align: right">生效日期：</td>
				<td width="75%"><input id="effectiveDate" name="effectiveDate"
					style="width: 90%" class="easyui-datebox"
					data-options="required:true " value="${smsCoupon.effectiveDate}">
				</td>
			</tr>
			<tr>
				<td width="25%" style="text-align: right">失效日期：</td>
				<td width="75%"><input id="expirationDate"
					name="expirationDate" style="width: 90%" class="easyui-datebox"
					data-options="required:true " value="${smsCoupon.expirationDate}">
				</td>
			</tr>
			<tr>
				<td width="25%" style="text-align: right">优惠券描述：</td>
				<td width="75%"><textarea id="description" name="description"
						style="width: 90%; height: 100px;">${smsCoupon.description}</textarea>
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
	</script>
</body>
</html>
