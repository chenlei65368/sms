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
		<div data-options="region:'north',border:false" style="height: 230px;">
			<form id="mainform" action="${ctx}/smsPersonRequire/${action}"
				method="post">

				<table class="formTable" width="100%" border="0" cellspacing="0"
					cellpadding="5">
					<tr>
						<td><input type="hidden" name="id"
							value="${smsPersonRequire.id}" /> <input type="hidden"
							id="action" value="${action}" /> <input type="hidden"
							id="datasSmsPersonRequireLine" name="datasSmsPersonRequireLine"
							value="${datasSmsPersonRequireLine}" /></td>
					</tr>
					<tr>
						<td width="25%" style="text-align: right">需求名称：</td>
						<td width="75%"><input id="name" name="name"
							style="width: 90%"
							<c:if test="${action=='view' }">disabled</c:if>
							class=" easyui-textbox "
							data-options="required:true ,validType:'length[1,40]'"
							value="${smsPersonRequire.name}"></td>
					</tr>
					<tr>
						<td width="25%" style="text-align: right">生效日期：</td>
						<td width="75%"><input id="effectiveDate"
							name="effectiveDate" style="width: 90%"
							<c:if test="${action=='view' }">disabled</c:if>
							class="easyui-datebox"
							data-options="required:true,parser: DzmFrame.EasyUI.parseDate "
							value="${smsPersonRequire.effectiveDate}"></td>
					</tr>
					<tr>
						<td width="25%" style="text-align: right">失效日期：</td>
						<td width="75%"><input id="expirationDate"
							name="expirationDate" style="width: 90%"
							<c:if test="${action=='view' }">disabled</c:if>
							class="easyui-datebox"
							data-options="required:true,parser: DzmFrame.EasyUI.parseDate "
							value="${smsPersonRequire.expirationDate}"></td>
					</tr>
					<tr>
						<td width="25%" style="text-align: right">描述：</td>
						<td width="75%"><textarea id="description" name="description"
								style="width: 90%; height: 70px;"
								<c:if test="${action=='view' }">disabled</c:if>>${smsPersonRequire.description}</textarea>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div data-options="region:'center',border:false">
			<div class="easyui-tabs"
				data-options="fit:true,border:false,plain:true">
				<div title="个性需求明细" style="padding: 5px;">
					<div id="SmsPersonRequireLine_tb"
						style="padding: 5px; height: auto">
						<table cellpadding="0" cellspacing="0">
							<shiro:hasPermission name="SmsPersonRequireLine:add">
								<td><a href="javascript:void(0)"
									class="easyui-linkbutton addSmsPersonRequireLine"
									iconCls="icon-add" plain="true">新增</a></td>
								<td><span class="toolbar-item dialog-tool-separator"></span>
								</td>
							</shiro:hasPermission>
							<shiro:hasPermission name="SmsPersonRequireLine:remove">
								<td><a href="javascript:void(0)"
									class="easyui-linkbutton removeSmsPersonRequireLine"
									iconCls="icon-remove" plain="true">删除</a></td>
								<td><span class="toolbar-item dialog-tool-separator"></span>
								</td>
							</shiro:hasPermission>
						</table>
					</div>
					<table id="SmsPersonRequireLineGrid"></table>
				</div>
			</div>
		</div>

		<script
			src="${ctxResources}/pages/modules/base/smsPersonRequireForm.js"></script>
</body>
</html>
