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
	<input id="supplierType" type="hidden" name="supplierType"/>
	<form id="mainform" action="${ctx}/smsMallBrand/${action}"
		method="post" enctype="multipart/form-data">
		<table class="formTable" width="100%" border="0" cellspacing="0"
			cellpadding="5">
			<tr>
				<td><input type="hidden" name="id" value="${smsMallBrand.id}" />
				</td>
			</tr>
			<tr>
				<td width="25%" style="text-align: right">品牌名称：</td>
				<td width="75%"><input id="name" name="name" style="width: 90%"
					class=" easyui-textbox "
					data-options="required:true ,validType:'length[1,40]'"
					value="${smsMallBrand.name}"></td>
			</tr>
			<tr>
				<td width="25%" style="text-align: right">商品类别：</td>
				<td width="75%"><input id="category" name="category"
					style="width: 90%" 
					data-options="required:true"
					value="${smsMallBrand.category}"></td>
			</tr>
			<tr>
				<td width="25%" style="text-align: right">LOGO路径：</td>
				<td width="75%">
					<div class="updateimg">
						<a href="javascript:void(0);"> <c:choose>
								<c:when
									test="${action == 'edit' && not empty smsMallBrand.logoPic}">
									<img src="${ctx }/upload/${smsMallBrand.logoPic}" />
								</c:when>
								<c:otherwise>
									<img src="../resources/images/add_img.jpg" />
								</c:otherwise>
							</c:choose>
						</a> <input class="file" type="file" id="logoPicId" name="logoPicName"
							accept="image/*">
					</div>
				</td>
			</tr>
			<tr>
				<td width="25%" style="text-align: right">品牌链接网站：</td>
				<td width="75%">
				<input id="url" name="url"
					style="width: 90%" class=" easyui-textbox "
					data-options="validType:'length[1,100]'"
					value="${smsMallBrand.url}">
				</td>
			</tr>
			<tr>
				<td width="25%" style="text-align: right">备注：</td>
				<td width="75%"><textarea id="remark" name="remark"
						style="width: 90%; height: 100px;">${smsMallBrand.remark}</textarea>
				</td>
			</tr>
		</table>
	</form>
	<script type="text/javascript">
		$(function() {
			initActions();
			getCategory();
		});
		function initActions() {
			$('#logoPicId').on('change', function() {
				changePic();
			});
		}
		function changePic() {
			DzmFrame.File.previewImage($("#logoPicId")[0]);
		}
		function getCategory() {
			var supplierType = $('#supplierType').val();
			var categoryArray = new ComboBoxTreeBean(ctx
					+ '/oimMallCategory/find',null, {"filter_rlikes_supplierType":supplierType.substring(0,2)});
			categoryArray.getFields();
			categoryArray.initCombobox($('#category'));
		}
	</script>
</body>
</html>
