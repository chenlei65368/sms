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
	
			<form id="mainform" action="${ctx}/smsHouseBuilding/${action}" method="post" enctype="multipart/form-data">
					<table class="formTable" width="100%" border="0" cellspacing="0" cellpadding="5" >
												<tr>
				<td>
					<input type="hidden" name="id" value="${smsHouseBuilding.id}"/>
				</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">楼栋名称：</td>
				<td width="75%">
											<input id="name" name="name" style="width:90%" value="${smsHouseBuilding.name}"
																																										class="easyui-textbox"
												data-options="required:true,validType:'length[1,40]'" >
															</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">状态：</td>
				<td width="75%">
											<input id="status" name="status" style="width:90%" value="${smsHouseBuilding.status}"
																																										class="easyui-textbox"
												data-options="required:false,validType:'length[2,2]'" >
															</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">描述：</td>
				<td width="75%">
											<textarea id="description" name="description" style="width:90%;height:100px;">${smsHouseBuilding.description}</textarea>
									</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">供应商：</td>
				<td width="75%">
											<input id="supplier" name="supplier" style="width:90%" value="${smsHouseBuilding.supplier}"
																																										class="easyui-textbox"
												data-options="required:false,validType:'length[9,9]'" >
															</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">创建时间：</td>
				<td width="75%">
											<input id="createTime" name="createTime" style="width:90%" value="${smsHouseBuilding.createTime}"
																																										class="easyui-datetimebox"
												data-options="required:false,validType:'',formatter:DzmFrame.EasyUI.fmtDatetime,parser: DzmFrame.EasyUI.parseDate" >
															</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">创建者：</td>
				<td width="75%">
											<input id="creator" name="creator" style="width:90%" value="${smsHouseBuilding.creator}"
																																										class="easyui-textbox"
												data-options="required:false,validType:'length[32,32]'" >
															</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">房源：</td>
				<td width="75%">
											<input id="housingId" name="housingId" style="width:90%" value="${smsHouseBuilding.housingId}"
																																										class="easyui-textbox"
												data-options="required:false,validType:'length[15,15]'" >
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