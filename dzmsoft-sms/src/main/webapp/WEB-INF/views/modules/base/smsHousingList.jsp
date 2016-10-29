<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/meta.jsp"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>
</head>
<body class="easyui-layout">
	<div data-options="region:'west',width:150,split:true" title="区县">
		<table id="regionDg"></table>
	</div>
	<div data-options="region:'center'" title="房源信息" id="leftDgPanel">
	<div id="tb" style="padding:5px;height:auto">
        <div>
        	<input type="hidden" id="city" value="${city }"/>
        	<form id="searchFrom" action="">
				<input type="text" id="filter_likes_name" name="filter_likes_name" class="easyui-combobox" data-options="width:150,prompt: '房源名称',mode:'remote',valueField:'id',textField:'text',loader:mapLoader"/>
				<shiro:hasPermission name="smsHousing:find">
				<a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" id="searchFrom_find">查询</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true" id="searchFrom_reset">重置</a>
				</shiro:hasPermission>
			</form>
			<table cellpadding="0" cellspacing="0">
				<tr>
					<shiro:hasPermission name="smsHousing:add">
						<td>
						<a href="javascript:void(0)" class="easyui-linkbutton add" iconCls="icon-add" plain="true">新增</a>
						</td>
						<td>
	       				<span class="toolbar-item dialog-tool-separator"></span>
	       				</td>
	       			</shiro:hasPermission>
	       			<shiro:hasPermission name="smsHousing:edit">
	       				<td>
						<a href="javascript:void(0)" class="easyui-linkbutton edit" iconCls="icon-edit" plain="true">编辑</a>
						</td>
						<td>
	       				<span class="toolbar-item dialog-tool-separator"></span>
	       				</td>
	       			</shiro:hasPermission>
	       			<shiro:hasPermission name="smsHousing:remove">
	       				<td>
						<a href="javascript:void(0)" class="easyui-linkbutton remove" iconCls="icon-remove" plain="true">删除</a>
						</td>
						<td>
	       				<span class="toolbar-item dialog-tool-separator"></span>
	       				</td>
	       			</shiro:hasPermission>
				</tr>
			</table>
        </div> 
  </div>
	<table id="leftDg"></table> 
</div>
	<div data-options="region:'east',split:true,title:'楼栋信息'" style="width:500px;" id="rightListPanel">
		<div id="right_tb" style="padding:5px;height:auto">
			<table cellpadding="0" cellspacing="0">
				<tr>
					<shiro:hasPermission name="smsHouseBuilding:add">
						<td>
						<a href="javascript:void(0)" class="easyui-linkbutton addHouseBuilding" iconCls="icon-add" plain="true">新增</a>
						</td>
						<td>
	       				<span class="toolbar-item dialog-tool-separator"></span>
	       				</td>
	       			</shiro:hasPermission>
	       			<shiro:hasPermission name="smsHouseBuilding:remove">
	       				<td>
						<a href="javascript:void(0)" class="easyui-linkbutton removeHouseBuilding" iconCls="icon-remove" plain="true">删除</a>
						</td>
						<td>
	       				<span class="toolbar-item dialog-tool-separator"></span>
	       				</td>
	       			</shiro:hasPermission>
				</tr>
			</table>
		</div>
		<table id="rightDg"></table>
	</div>
<div id="dlg"></div>  
<script src="${ctxResources}/pages/modules/base/smsHousingList.js"></script>
</body>
</html>