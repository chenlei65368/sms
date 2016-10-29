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
	
			<form id="mainform" action="${ctx}/smsCommodityOrder/${action}" method="post" enctype="multipart/form-data">
					<table class="formTable" width="100%" border="0" cellspacing="0" cellpadding="5" >
												<tr>
				<td>
					<input type="hidden" name="id" value="${smsCommodityOrder.id}"/>
				</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">订单号：</td>
				<td width="75%">
											<input id="orderId" name="orderId" style="width:90%" class=" easyui-textbox " data-options="" value="${smsCommodityOrder.orderId}">
									</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">商品ID：</td>
				<td width="75%">
											<input id="commodityId" name="commodityId" style="width:90%" class=" easyui-textbox " data-options="required:true ,validType:'length[16,16]'" value="${smsCommodityOrder.commodityId}">
									</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">商品名称：</td>
				<td width="75%">
											<input id="commodityName" name="commodityName" style="width:90%" class=" easyui-textbox " data-options="required:true ,validType:'length[1,40]'" value="${smsCommodityOrder.commodityName}">
									</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">单价：</td>
				<td width="75%">
											<input id="price" name="price" style="width:90%" class=" easyui-textbox " data-options="required:true " value="${smsCommodityOrder.price}">
									</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">数量：</td>
				<td width="75%">
											<input id="quantity" name="quantity" style="width:90%" class=" easyui-textbox " data-options="required:true " value="${smsCommodityOrder.quantity}">
									</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">金额：</td>
				<td width="75%">
											<input id="amount" name="amount" style="width:90%" class=" easyui-textbox " data-options="required:true " value="${smsCommodityOrder.amount}">
									</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">状态：</td>
				<td width="75%">
											<input id="status" name="status" style="width:90%" class=" easyui-textbox " data-options="required:true ,validType:'length[2,2]'" value="${smsCommodityOrder.status}">
									</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">规格：</td>
				<td width="75%">
											<input id="spec" name="spec" style="width:90%" class=" easyui-textbox " data-options="" value="${smsCommodityOrder.spec}">
									</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">型号：</td>
				<td width="75%">
											<input id="model" name="model" style="width:90%" class=" easyui-textbox " data-options="" value="${smsCommodityOrder.model}">
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