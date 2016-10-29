<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/meta.jsp"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>
<script src="${ctxResources}/plugins/easyui/extensions/jquery-easyui-datagridview/datagrid-detailview.js"></script>
</head>
<body>
<div id="tb" style="padding:5px;height:auto">
        <div>
        	<form id="searchFrom" action="">
				<input type="text" name="filter_likes_memberName__SmsOrderExample" class="easyui-textbox" data-options="width:150,prompt: '会员'"/>
				<input type="text" name="filter_likes_address__SmsOrderExample" class="easyui-textbox" data-options="width:150,prompt: '会员手机号'"/>
				<input type="text" name="filter_likes_address__SmsOrderExample" class="easyui-textbox" data-options="width:150,prompt: '服务地址'"/>
				<shiro:hasPermission name="smsCommodityOrder:find">
				<a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" id="searchFrom_find">查询</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true" id="searchFrom_reset">重置</a>
				</shiro:hasPermission>
			</form>
			<table cellpadding="0" cellspacing="0">
				<tr>
	       			<shiro:hasPermission name="smsCommodityOrder:send">
						<td>
						<a href="javascript:void(0)" class="easyui-linkbutton send" iconCls="icon-add" plain="true">派单</a>
						</td>
						<td>
	       				<span class="toolbar-item dialog-tool-separator"></span>
	       				</td>
	       			</shiro:hasPermission>
				</tr>
			</table>
        </div> 
        
  </div>
<table id="dg"></table> 
<div id="dlg"></div>  
<script src="${ctxResources}/pages/modules/base/smsCommodityOrderList.js"></script>
</body>
</html>