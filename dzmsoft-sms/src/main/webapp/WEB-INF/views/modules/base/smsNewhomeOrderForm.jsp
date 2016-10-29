<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
																			<head>
	<title></title>
	<%@ include file="/WEB-INF/views/include/meta.jsp"%>
	<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
	<%@ include file="/WEB-INF/views/include/easyui.jsp"%>
			<%@ include file="/WEB-INF/views/include/ueditor.jsp"%>
	</head>
<body>
	
			<input type="hidden" id="action" value="${action}" />
		<form id="mainform" method="post" enctype="multipart/form-data">
			<table class="formTable" width="100%" border="0" cellspacing="0" cellpadding="5" >
												<tr>
				<td>
					<input type="hidden" name="id" value="${smsNewhomeOrder.id}"/>
				</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">订单号：</td>
				<td width="75%">
											<input id="orderId" name="orderId" style="width:90%" class=" easyui-textbox " data-options="" value="${smsNewhomeOrder.orderId}">
									</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">面积：</td>
				<td width="75%">
											<input id="area" name="area" style="width:90%" class=" easyui-textbox " data-options="" value="${smsNewhomeOrder.area}">
									</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">金额：</td>
				<td width="75%">
											<input id="amount" name="amount" style="width:90%" class=" easyui-textbox " data-options="" value="${smsNewhomeOrder.amount}">
									</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">折扣面积：</td>
				<td width="75%">
											<input id="diacount" name="diacount" style="width:90%" class=" easyui-textbox " data-options="" value="${smsNewhomeOrder.diacount}">
									</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">保洁员：</td>
				<td width="75%">
											<input id="cleaners" name="cleaners" type="hidden" value="${smsNewhomeOrder.cleaners}"/>
						<script id="cleanersId" type="text/plain" style="width:100%;height:200px;" class="">
						</script>
									</td>
			</tr>
								</table>
	</form>
	<script type="text/javascript">
	$(function(){
		initActions();
					initUeditor();
					});
		function initActions(){
			}
			function initUeditor(){
			var action = $('#action').val();
																																															UE.getEditor('cleanersId');
										if (action == 'edit'){
																																																												UE.getEditor('cleanersId').addListener("ready", function () {
					        UE.getEditor('cleanersId').setContent($('#cleaners').val());
						});
												}
		}
		</script>
</body>
</html>	