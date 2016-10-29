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

	<form id="mainform" action="${ctx}/smsCard/${action}" method="post"
		enctype="multipart/form-data">
		<table class="formTable" width="100%" border="0" cellspacing="0"
			cellpadding="5">
			<tr>
				<td><input type="hidden" name="id" value="${smsCard.id}" /></td>
			</tr>
			<tr>
				<td width="25%" style="text-align: right">名称：</td>
				<td width="75%"><input id="name" name="name" style="width: 90%"
					class=" easyui-textbox "
					data-options="required:true ,validType:'length[1,40]'"
					value="${smsCard.name}"></td>
			</tr>
			<tr>
				<td width="25%" style="text-align: right">面值：</td>
				<td width="75%"><input id="faceValue" name="faceValue"
					style="width: 90%" class="easyui-numberspinner"
					data-options="required:true,min:1" value="${smsCard.faceValue}">
				</td>
			</tr>
			<tr>
				<td width="25%" style="text-align: right">生效日期：</td>
				<td width="75%"><input id="effectiveDate" name="effectiveDate"
					style="width: 90%" class="easyui-datebox" data-options="required:true"
					value="${smsCard.effectiveDate}"></td>
			</tr>
			<tr>
				<td width="25%" style="text-align: right">失效日期：</td>
				<td width="75%"><input id="expierationDate"
					name="expierationDate" style="width: 90%" class="easyui-datebox"
					data-options="required:true" value="${smsCard.expierationDate}"></td>
			</tr>
			<tr>
				<td width="25%" style="text-align: right">背景图片：</td>
				<td width="75%">
					<div class="updateimg">
						<a href="javascript:void(0);"> <c:choose>
								<c:when
									test="${action == 'edit' && not empty smsCard.backgroundPic}">
									<img src="${ctx }/upload/${smsCard.backgroundPic}" />
								</c:when>
								<c:otherwise>
									<img src="../resources/images/add_img.jpg" />
								</c:otherwise>
							</c:choose>
						</a> <input class="file" type="file" id="backgroundPicId"
							name="backgroundPicName" accept="image/*">
					</div>
				</td>
			</tr>
			<tr>
				<td width="25%" style="text-align: right">描述：</td>
				<td width="75%"><textarea id="description" name="description"
						style="width: 90%; height: 100px;">${smsCard.description}</textarea>
				</td>
			</tr>
		</table>
	</form>
	<script type="text/javascript">
		$(function() {
			initActions();
		});
		function initActions() {
			$('#backgroundPicId').on('change', function() {
				changePic();
			});
		}
		function changePic() {
			DzmFrame.File.previewImage($("#backgroundPicId")[0]);
		}
	</script>
</body>
</html>
