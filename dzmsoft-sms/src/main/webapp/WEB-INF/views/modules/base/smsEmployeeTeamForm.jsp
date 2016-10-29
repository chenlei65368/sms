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
	<form id="mainform" action="${ctx}/smsEmployeeTeam/${action}" method="post">
		<table class="formTable" width="100%" border="0" cellspacing="0" cellpadding="5" >
												<tr>
				<td>
					<input type="hidden" name="id" value="${smsEmployeeTeam.id}"/>
				</td>
			</tr>
												<tr>
				<td style="width:25%;text-align:right">名称：</td>
				<td width="75%">
					<input id="name" name="name" style="width:90%" class=" easyui-textbox " data-options="required:true ,validType:'length[1,40]'" value="${smsEmployeeTeam.name}"> 
				</td>
			</tr>
												<tr>
				<td style="width:25%;text-align:right">状态：</td>
				<td width="75%">
					<input id="status" name="status" style="width:90%" class=" easyui-textbox " data-options="required:true ,validType:'length[2,2]'" value="${smsEmployeeTeam.status}"> 
				</td>
			</tr>
												<tr>
				<td style="width:25%;text-align:right">描述：</td>
				<td width="75%">
					<input id="description" name="description" style="width:90%" class=" easyui-textbox " data-options=" height:100,multiline:true " value="${smsEmployeeTeam.description}"> 
				</td>
			</tr>
												<tr>
				<td style="width:25%;text-align:right">上级员工组：</td>
				<td width="75%">
				<input id="pid" name="pid" style="width:90%" value="${smsEmployeeTeam.pid}">
				</td>
			</tr>
												<tr>
				<td style="width:25%;text-align:right">创建时间：</td>
				<td width="75%">
					<input id="createTime" name="createTime" style="width:90%" class="easyui-datetimebox" data-options="required:true " value="${smsEmployeeTeam.createTime}"> 
				</td>
			</tr>
												<tr>
				<td style="width:25%;text-align:right">创建者：</td>
				<td width="75%">
					<input id="creator" name="creator" style="width:90%" class=" easyui-textbox " data-options="required:true ,validType:'length[32,32]'" value="${smsEmployeeTeam.creator}"> 
				</td>
			</tr>
												<tr>
				<td style="width:25%;text-align:right">负责人：</td>
				<td width="75%">
					<input id="leader" name="leader" style="width:90%" class=" easyui-textbox " data-options="" value="${smsEmployeeTeam.leader}"> 
				</td>
			</tr>
												<tr>
				<td style="width:25%;text-align:right">供应商：</td>
				<td width="75%">
					<input id="supplier" name="supplier" style="width:90%" class=" easyui-textbox " data-options="required:true ,validType:'length[9,9]'" value="${smsEmployeeTeam.supplier}"> 
				</td>
			</tr>
								</table>
	</form>
	<script type="text/javascript">
	$(function(){
		getParent();
	});
	function getParent(){
		var parentArray = new ComboBoxTreeBean(ctx+'/smsEmployeeTeam/find');
		parentArray.getFields();
		parentArray.initCombobox($('#pid'));
	}
	</script>
</body>
</html>	