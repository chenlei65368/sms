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
	<form id="mainform" action="${ctx}/smsRegion/${action}" method="post">
		<table class="formTable" width="100%" border="0" cellspacing="0" cellpadding="5" >
												<tr>
				<td>
					<input type="hidden" name="id" value="${smsRegion.id}"/>
				</td>
			</tr>
												<tr>
				<td style="width:25%;text-align:right">上级区域：</td>
				<td width="75%">
				<input id="pid" name="pid" style="width:90%" value="${smsRegion.pid}">
				</td>
			</tr>
												<tr>
				<td style="width:25%;text-align:right">省：</td>
				<td width="75%">
					<input id="province" name="province" style="width:90%" class=" easyui-textbox " data-options="required:true ,validType:'length[6,6]'" value="${smsRegion.province}"> 
				</td>
			</tr>
												<tr>
				<td style="width:25%;text-align:right">市：</td>
				<td width="75%">
					<input id="city" name="city" style="width:90%" class=" easyui-textbox " data-options="required:true ,validType:'length[6,6]'" value="${smsRegion.city}"> 
				</td>
			</tr>
												<tr>
				<td style="width:25%;text-align:right">区县：</td>
				<td width="75%">
					<input id="district" name="district" style="width:90%" class=" easyui-textbox " data-options="" value="${smsRegion.district}"> 
				</td>
			</tr>
												<tr>
				<td style="width:25%;text-align:right">区域名称：</td>
				<td width="75%">
					<input id="name" name="name" style="width:90%" class=" easyui-textbox " data-options="required:true ,validType:'length[40,40]'" value="${smsRegion.name}"> 
				</td>
			</tr>
												<tr>
				<td style="width:25%;text-align:right">创建者：</td>
				<td width="75%">
					<input id="creator" name="creator" style="width:90%" class=" easyui-textbox " data-options="required:true ,validType:'length[32,32]'" value="${smsRegion.creator}"> 
				</td>
			</tr>
												<tr>
				<td style="width:25%;text-align:right">创建者：</td>
				<td width="75%">
					<input id="createTime" name="createTime" style="width:90%" class="easyui-datetimebox" data-options="required:true " value="${smsRegion.createTime}"> 
				</td>
			</tr>
												<tr>
				<td style="width:25%;text-align:right">供应商：</td>
				<td width="75%">
					<input id="supplier" name="supplier" style="width:90%" class=" easyui-textbox " data-options="required:true ,validType:'length[9,9]'" value="${smsRegion.supplier}"> 
				</td>
			</tr>
												<tr>
				<td style="width:25%;text-align:right">描述：</td>
				<td width="75%">
					<input id="description" name="description" style="width:90%" class=" easyui-textbox " data-options=" height:100,multiline:true " value="${smsRegion.description}"> 
				</td>
			</tr>
								</table>
	</form>
	<script type="text/javascript">
	$(function(){
		getParent();
	});
	function getParent(){
		var parentArray = new ComboBoxTreeBean(ctx+'/smsRegion/find');
		parentArray.getFields();
		parentArray.initCombobox($('#pid'));
	}
	</script>
</body>
</html>	