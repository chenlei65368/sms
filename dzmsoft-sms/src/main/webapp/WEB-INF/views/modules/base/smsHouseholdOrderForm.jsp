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
	
			<form id="mainform" action="${ctx}/smsHouseholdOrder/${action}" method="post" enctype="multipart/form-data">
					<table class="formTable" width="100%" border="0" cellspacing="0" cellpadding="5" >
												<tr>
				<td>
					<input type="hidden" name="id" value="${smsHouseholdOrder.id}"/>
				</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">订单号：</td>
				<td width="75%">
											<input id="orderId" name="orderId" style="width:90%" class=" easyui-textbox " data-options="" value="${smsHouseholdOrder.orderId}">
									</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">服务时长：</td>
				<td width="75%">
											<input id="times" name="times" style="width:90%" class="easyui-numberbox" data-options="" value="${smsHouseholdOrder.times}">
									</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">单价：</td>
				<td width="75%">
											<input id="price" name="price" style="width:90%" class=" easyui-textbox " data-options="" value="${smsHouseholdOrder.price}">
									</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">折扣价：</td>
				<td width="75%">
											<input id="discount" name="discount" style="width:90%" class=" easyui-textbox " data-options="" value="${smsHouseholdOrder.discount}">
									</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">金额：</td>
				<td width="75%">
											<input id="amount" name="amount" style="width:90%" class=" easyui-textbox " data-options="" value="${smsHouseholdOrder.amount}">
									</td>
			</tr>
								</table>
	</form>
	<script type="text/javascript">
	$(function(){
		initActions();
					});
		function initActions(){
			}
		</script>
</body>
</html>	