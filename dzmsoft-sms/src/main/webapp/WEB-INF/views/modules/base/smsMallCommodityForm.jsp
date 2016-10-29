<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/meta.jsp"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>
<%@ include file="/WEB-INF/views/include/ueditor.jsp"%>
</head>
<body>
	<input type="hidden" id="action" value="${action}" />
	<form id="mainform" method="post" enctype="multipart/form-data">
		<table class="formTable" width="100%" border="0" cellspacing="0"
			cellpadding="5">
			<tr>
				<td><input type="hidden" name="id"
					value="${smsMallCommodity.id}" />
					<input type="hidden" id="category" name="category" value="${smsMallCommodity.category}"/>
					<input type="hidden" id="brandId" name="brandId" value="${smsMallCommodity.brandId}"/>
					</td>
			</tr>
			<tr>
				<td width="15%" style="text-align: right">商品名称：</td>
				<td width="35%"><input id="name" name="name" style="width: 90%"
					class=" easyui-textbox "
					data-options="required:true ,validType:'length[1,40]'"
					value="${smsMallCommodity.name}"></td>
				<td width="15%" style="text-align: right">品牌：</td>
				<td width="35%"><input id="brandName" name="brandName"
					style="width: 90%" class=" easyui-searchbox "
					data-options="required:true,editable:false,searcher:selectBrand"
					value="${brandName}"></td>
			</tr>
			<tr>
				<td width="15%" style="text-align: right">规格：</td>
				<td width="35%"><input id="spec" name="spec" style="width: 90%"
					class=" easyui-textbox " data-options=""
					value="${smsMallCommodity.spec}"></td>
				<td width="15%" style="text-align: right">型号：</td>
				<td width="35%"><input id="model" name="model"
					style="width: 90%" class=" easyui-textbox " data-options=""
					value="${smsMallCommodity.model}"></td>
			</tr>
			<tr>
				<td width="15%" style="text-align: right">原价：</td>
				<td width="35%"><input id="originalPrice" name="originalPrice"
					style="width: 90%" class=" easyui-numberbox "
					data-options="required:true,precision:2,groupSeparator:',' "
					value="${smsMallCommodity.originalPrice}"></td>
				<td width="15%" style="text-align: right">现价：</td>
				<td width="35%"><input id="price" name="price"
					style="width: 90%" class=" easyui-numberbox "
					data-options="required:true,precision:2,groupSeparator:',' " value="${smsMallCommodity.price}">
				</td>
			</tr>
			<tr>
				<td width="15%" style="text-align: right">服务承诺：</td>
				<td width="35%"><input id="servicePromise"
					name="servicePromise" style="width: 90%" class=" easyui-textbox "
					data-options="" value="${smsMallCommodity.servicePromise}">
				</td>
				<td width="15%" style="text-align: right">宣传语：</td>
				<td width="35%"><input id="slogans" name="slogans"
					style="width: 90%" class=" easyui-textbox " data-options=""
					value="${smsMallCommodity.slogans}"></td>
			</tr>
			<tr>
				<td width="15%" style="text-align: right">商品图片：</td>
				<td width="35%">
					<div class="updateimg">
						<a href="javascript:void(0);"> <c:choose>
								<c:when
									test="${action == 'edit' && not empty smsMallCommodity.logoPic}">
									<img src="${ctx }/upload/${smsMallCommodity.logoPic}" />
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
				<td width="15%" style="text-align: right">商品详情：</td>
				<td width="85%" colspan="3"><input id="detail" name="detail" type="hidden"
					value="${smsMallCommodity.detail}" /> <script id="detailId"
						type="text/plain" style="width: 100%; height: 200px;" class="">
						</script></td>
			</tr>
			
		</table>
		<div style="text-align:center;padding:5px">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
		</div>
	</form>
	<div id="brandDialog"></div>
	<script type="text/javascript">
		var brandDialog;
		$(function() {
			initActions();
			initUeditor();
		});
		function selectBrand(value){
			brandDialog = $('#brandDialog').dialog({title:'品牌',width: 800, height: 600,modal:true
				,href:ctx+'/smsMallBrand/showDialog'
			});
		}
		function closeSmsMallBrandDg(data){
			$('#brandId').val(data.id);
			$('#category').val(data.category);
			$('#brandName').searchbox('setValue', data.name);
			brandDialog.panel('close');
		}
		function initActions() {
			$('#logoPicId').on('change', function() {
				changePic();
			});
		}
		function changePic() {
			DzmFrame.File.previewImage($("#logoPicId")[0]);
		}
		function initUeditor() {
			var action = $('#action').val();
			UE.getEditor('detailId');
			if (action == 'edit') {
				UE.getEditor('detailId').addListener("ready", function() {
					UE.getEditor('detailId').setContent($('#detail').val());
				});
			}
		}
		function submitForm(){
			var action = $('#action').val();
			var url = ctx + '/smsMallCommodity/' + action;
			$('#mainform').form('submit',{
				url:url,
				onSubmit:function(){
					var isValid = $(this).form('enableValidation').form('validate');
					if (isValid){
						$.messager.progress(); 
						$('#detail').val(UE.getEditor('detailId').getContent());
					}
					return isValid;	// 返回false终止表单提交
				},
				success:function(result){
					$.messager.progress('close');
					var data = $.parseJSON(result);
					if (data.success){
						$('#action').val(data.data);
						$.messager.alert('系统提示',data.msg,'info');
					} else{
						$.messager.alert('系统提示',data.msg,'error');
					}
				}
			});
		}
	</script>
</body>
</html>
