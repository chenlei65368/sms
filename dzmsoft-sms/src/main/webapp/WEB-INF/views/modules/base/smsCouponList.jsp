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
<div id="tb" style="padding:5px;height:auto">
        <div>
        	<form id="searchFrom" action="">
				<input type="text" name="filter_eqc_faceValue" class="easyui-numberbox" data-options="width:150,prompt: '面值',precision:'0'"/>
				<input id="effectiveDate_start" class="easyui-textbox" name="filter_ged_effectiveDate" data-options="width:150,prompt: '生效起始时间'"/>
				<input id="effectiveDate_end" class="easyui-textbox" name="filter_led_effectiveDate" data-options="width:150,prompt: '生效截止时间'"/>
				<input id="expirationDate_start" class="easyui-textbox" name="filter_ged_expirationDate" data-options="width:150,prompt: '失效起始时间'"/>
				<input id="expirationDate_end" class="easyui-textbox" name="filter_led_expirationDate" data-options="width:150,prompt: '失效截止时间'"/>
				<shiro:hasPermission name="smsCoupon:find">
				<a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" id="searchFrom_find">查询</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true" id="searchFrom_reset">重置</a>
				</shiro:hasPermission>
			</form>
			<table cellpadding="0" cellspacing="0">
				<tr>
					<shiro:hasPermission name="smsCoupon:add">
						<td>
						<a href="javascript:void(0)" class="easyui-linkbutton add" iconCls="icon-add" plain="true">新增</a>
						</td>
						<td>
	       				<span class="toolbar-item dialog-tool-separator"></span>
	       				</td>
	       			</shiro:hasPermission>
	       			<shiro:hasPermission name="smsCoupon:edit">
	       				<td>
						<a href="javascript:void(0)" class="easyui-linkbutton edit" iconCls="icon-edit" plain="true">编辑</a>
						</td>
						<td>
	       				<span class="toolbar-item dialog-tool-separator"></span>
	       				</td>
	       			</shiro:hasPermission>
	       			<shiro:hasPermission name="smsCoupon:remove">
	       				<td>
						<a href="javascript:void(0)" class="easyui-linkbutton remove" iconCls="icon-remove" plain="true">删除</a>
						</td>
						<td>
	       				<span class="toolbar-item dialog-tool-separator"></span>
	       				</td>
	       			</shiro:hasPermission>
	       			<shiro:hasPermission name="smsCoupon:enable">
	       				<td>
						<a href="javascript:void(0)" class="easyui-linkbutton enable" iconCls="icon-ok" plain="true">启用</a>
						</td>
						<td>
	       				<span class="toolbar-item dialog-tool-separator"></span>
	       				</td>
	       			</shiro:hasPermission>
	       			<shiro:hasPermission name="smsCoupon:disable">
	       				<td>
						<a href="javascript:void(0)" class="easyui-linkbutton disable" iconCls="icon-no" plain="true">禁用</a>
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
<script src="${ctxResources}/pages/modules/base/smsCouponList.js"></script>
</body>
</html>