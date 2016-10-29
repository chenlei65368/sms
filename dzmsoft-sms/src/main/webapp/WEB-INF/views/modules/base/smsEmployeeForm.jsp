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

	<form id="mainform" method="post" action="${ctx }/smsEmployee/${action}" enctype="multipart/form-data">
		<table class="formTable" width="100%" border="0" cellspacing="0"
			cellpadding="5">
			<tr>
				<td><input type="hidden" name="id" value="${smsEmployee.id}" />
				</td>
			</tr>
			<tr>
				<td width="15%" style="text-align: right">姓名：</td>
				<td width="35%"><input id="name" name="name" style="width: 90%"
					class=" easyui-textbox "
					data-options="required:true ,validType:'length[1,20]'"
					value="${smsEmployee.name}"></td>
				<td width="15%" style="text-align: right">性别：</td>
				<td width="35%"><input id="sex" name="sex" style="width: 90%"
					class=" easyui-textbox "
					data-options="required:true"
					value="${smsEmployee.sex}"></td>
			</tr>
			<tr>
				<td width="15%" style="text-align: right">出生日期：</td>
				<td width="35%"><input id="birthday" name="birthday"
					style="width: 90%" class="easyui-datebox"
					data-options="required:true " value="${smsEmployee.birthday}">
				</td>
				<td width="15%" style="text-align: right">用户类型：</td>
				<td width="25%"><input id="userType" name="userType"
					style="width: 90%"
					data-options="required:true"
					value="${smsEmployee.userType}"></td>
			</tr>
			<tr>
				<td width="15%" style="text-align: right">手机号：</td>
				<td width="35%"><input id="mobile" name="mobile"
					style="width: 90%" class=" easyui-textbox "
					data-options="required:true ,validType:'mobile'"
					value="${smsEmployee.mobile}"></td>
				<td width="15%" style="text-align: right">身份证号：</td>
				<td width="35%"><input id="idCard" name="idCard"
					style="width: 90%" class=" easyui-textbox " data-options="required:true ,validType:'idCard'"
					value="${smsEmployee.idCard}"></td>
				
			</tr>
			<tr>
				<td width="15%" style="text-align: right">经验值(小时)：</td>
				<td width="35%"><input id="experienceValue"
					name="experienceValue" style="width: 90%" class="easyui-numberspinner"
					data-options="required:true,min:1" value="${smsEmployee.experienceValue}"></td>
				<td width="15%" style="text-align: right">好评率：</td>
				<td width="35%"><input id="goodRatio" name="goodRatio" readonly
					style="width: 90%" class=" easyui-textbox " data-options=""
					value="${smsEmployee.goodRatio}"></td>
			</tr>
			<tr>
				<td width="15%" style="text-align: right">好评数：</td>
				<td width="35%"><input id="goodCount" name="goodCount" readonly
					style="width: 90%" class=" easyui-numberbox " data-options=""
					value="${smsEmployee.goodCount}"></td>
				<td width="15%" style="text-align: right">差评数：</td>
				<td width="35%"><input id="badCount" name="badCount" readonly
					style="width: 90%" class=" easyui-numberbox " data-options=""
					value="${smsEmployee.badCount}"></td>
			</tr>
			<tr>
				<td width="15%" style="text-align: right">健康证有效截止日期：</td>
				<td width="35%"><input id="healthEndDate" name="healthEndDate"
					style="width: 90%" class="easyui-datebox" data-options=""
					value="${smsEmployee.healthEndDate}"></td>
				<td width="50%" colspan="2">
				</td>
			</tr>
			<tr>
				<td width="15%" style="text-align: right">个人头像：</td>
				<td width="35%">
					<div class="updateimg">
						<a href="javascript:void(0);"> <c:choose>
								<c:when
									test="${action == 'edit' && not empty smsEmployee.headPortraitPic}">
									<img src="${ctx }/upload/${smsEmployee.headPortraitPic}" />
								</c:when>
								<c:otherwise>
									<img src="../resources/images/add_img.jpg" />
								</c:otherwise>
							</c:choose>
						</a> <input class="file" type="file" id="headPortraitPicId"
							name="headPortraitPicName" accept="image/*">
					</div>
				</td>
				<td width="15%" style="text-align: right">健康证：</td>
				<td width="35%">
					<div class="updateimg">
						<a href="javascript:void(0);"> <c:choose>
								<c:when
									test="${action == 'edit' && not empty smsEmployee.healthCertificatePic}">
									<img src="${ctx }/upload/${smsEmployee.healthCertificatePic}" />
								</c:when>
								<c:otherwise>
									<img src="../resources/images/add_img.jpg" />
								</c:otherwise>
							</c:choose>
						</a> <input class="file" type="file" id="healthCertificatePicId"
							name="healthCertificatePicName" accept="image/*">
					</div>
				</td>
			</tr>
			<tr>
				<td width="15%" style="text-align: right">员工简介：</td>
				<td width="85%" colspan="3"><textarea id="description" name="description"
						style="width: 90%; height: 100px;">${smsEmployee.description}</textarea></td>
			</tr>
		</table>
	</form>
	<script type="text/javascript">
		$(function() {
			initActions();
			getEmpType();
			getSex();
		});
		function getSex(){
			var sexArray = new DataDictionary(DICTIONARY_FIELD.SEX);
			sexArray.getFields();
			sexArray.initCombobox($('#sex'));
		}
		function getEmpType(){
			var empTypeArray = new DataDictionary(DICTIONARY_FIELD.CLEAN_EMP_TYPE);
			empTypeArray.getFields();
			empTypeArray.initCombobox($('#userType'));
		}
		function initActions() {
			$('#headPortraitPicId').on('change', function() {
				DzmFrame.File.previewImage($("#headPortraitPicId")[0]);
			});
			$('#healthCertificatePicId').on('change', function() {
				DzmFrame.File.previewImage($("#healthCertificatePicId")[0]);
			});
		}
	</script>
</body>
</html>
