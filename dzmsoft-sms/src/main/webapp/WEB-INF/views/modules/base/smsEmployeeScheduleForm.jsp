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
	
			<form id="mainform" action="${ctx}/smsEmployeeSchedule/${action}" method="post" enctype="multipart/form-data">
					<table class="formTable" width="100%" border="0" cellspacing="0" cellpadding="5" >
												<tr>
				<td>
					<input type="hidden" name="id" value="${smsEmployeeSchedule.id}"/>
				</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">：</td>
				<td width="75%">
											<input id="employee" name="employee" style="width:90%" class=" easyui-textbox " data-options="required:true ,validType:'length[12,12]'" value="${smsEmployeeSchedule.employee}">
									</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">日期：</td>
				<td width="75%">
											<input id="month" name="month" style="width:90%" class=" easyui-textbox " data-options="required:true ,validType:'length[6,6]'" value="${smsEmployeeSchedule.month}">
									</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">1号：</td>
				<td width="75%">
											<input id="one" name="one" style="width:90%" class=" easyui-textbox " data-options="" value="${smsEmployeeSchedule.one}">
									</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">2号：</td>
				<td width="75%">
											<input id="two" name="two" style="width:90%" class=" easyui-textbox " data-options="" value="${smsEmployeeSchedule.two}">
									</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">3号：</td>
				<td width="75%">
											<input id="three" name="three" style="width:90%" class=" easyui-textbox " data-options="" value="${smsEmployeeSchedule.three}">
									</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">4号：</td>
				<td width="75%">
											<input id="four" name="four" style="width:90%" class=" easyui-textbox " data-options="" value="${smsEmployeeSchedule.four}">
									</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">5号：</td>
				<td width="75%">
											<input id="five" name="five" style="width:90%" class=" easyui-textbox " data-options="" value="${smsEmployeeSchedule.five}">
									</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">6号：</td>
				<td width="75%">
											<input id="six" name="six" style="width:90%" class=" easyui-textbox " data-options="" value="${smsEmployeeSchedule.six}">
									</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">7号：</td>
				<td width="75%">
											<input id="seven" name="seven" style="width:90%" class=" easyui-textbox " data-options="" value="${smsEmployeeSchedule.seven}">
									</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">8号：</td>
				<td width="75%">
											<input id="eight" name="eight" style="width:90%" class=" easyui-textbox " data-options="" value="${smsEmployeeSchedule.eight}">
									</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">9号：</td>
				<td width="75%">
											<input id="nine" name="nine" style="width:90%" class=" easyui-textbox " data-options="" value="${smsEmployeeSchedule.nine}">
									</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">10号：</td>
				<td width="75%">
											<input id="ten" name="ten" style="width:90%" class=" easyui-textbox " data-options="" value="${smsEmployeeSchedule.ten}">
									</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">11号：</td>
				<td width="75%">
											<input id="eleven" name="eleven" style="width:90%" class=" easyui-textbox " data-options="" value="${smsEmployeeSchedule.eleven}">
									</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">12号：</td>
				<td width="75%">
											<input id="twelve" name="twelve" style="width:90%" class=" easyui-textbox " data-options="" value="${smsEmployeeSchedule.twelve}">
									</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">13号：</td>
				<td width="75%">
											<input id="thirteen" name="thirteen" style="width:90%" class=" easyui-textbox " data-options="" value="${smsEmployeeSchedule.thirteen}">
									</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">14号：</td>
				<td width="75%">
											<input id="fourteen" name="fourteen" style="width:90%" class=" easyui-textbox " data-options="" value="${smsEmployeeSchedule.fourteen}">
									</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">15号：</td>
				<td width="75%">
											<input id="fifteen" name="fifteen" style="width:90%" class=" easyui-textbox " data-options="" value="${smsEmployeeSchedule.fifteen}">
									</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">16号：</td>
				<td width="75%">
											<input id="sixteen" name="sixteen" style="width:90%" class=" easyui-textbox " data-options="" value="${smsEmployeeSchedule.sixteen}">
									</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">17号：</td>
				<td width="75%">
											<input id="seventeen" name="seventeen" style="width:90%" class=" easyui-textbox " data-options="" value="${smsEmployeeSchedule.seventeen}">
									</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">18号：</td>
				<td width="75%">
											<input id="eighteen" name="eighteen" style="width:90%" class=" easyui-textbox " data-options="" value="${smsEmployeeSchedule.eighteen}">
									</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">19号：</td>
				<td width="75%">
											<input id="nineteen" name="nineteen" style="width:90%" class=" easyui-textbox " data-options="" value="${smsEmployeeSchedule.nineteen}">
									</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">20号：</td>
				<td width="75%">
											<input id="twenty" name="twenty" style="width:90%" class=" easyui-textbox " data-options="" value="${smsEmployeeSchedule.twenty}">
									</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">21号：</td>
				<td width="75%">
											<input id="twentyOne" name="twentyOne" style="width:90%" class=" easyui-textbox " data-options="" value="${smsEmployeeSchedule.twentyOne}">
									</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">22号：</td>
				<td width="75%">
											<input id="twentyTwo" name="twentyTwo" style="width:90%" class=" easyui-textbox " data-options="" value="${smsEmployeeSchedule.twentyTwo}">
									</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">23号：</td>
				<td width="75%">
											<input id="twentyThree" name="twentyThree" style="width:90%" class=" easyui-textbox " data-options="" value="${smsEmployeeSchedule.twentyThree}">
									</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">24号：</td>
				<td width="75%">
											<input id="twentyFour" name="twentyFour" style="width:90%" class=" easyui-textbox " data-options="" value="${smsEmployeeSchedule.twentyFour}">
									</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">25号：</td>
				<td width="75%">
											<input id="twentyFive" name="twentyFive" style="width:90%" class=" easyui-textbox " data-options="" value="${smsEmployeeSchedule.twentyFive}">
									</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">26号：</td>
				<td width="75%">
											<input id="twentySix" name="twentySix" style="width:90%" class=" easyui-textbox " data-options="" value="${smsEmployeeSchedule.twentySix}">
									</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">27号：</td>
				<td width="75%">
											<input id="twentySeven" name="twentySeven" style="width:90%" class=" easyui-textbox " data-options="" value="${smsEmployeeSchedule.twentySeven}">
									</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">28号：</td>
				<td width="75%">
											<input id="twentyEight" name="twentyEight" style="width:90%" class=" easyui-textbox " data-options="" value="${smsEmployeeSchedule.twentyEight}">
									</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">29号：</td>
				<td width="75%">
											<input id="twentyNine" name="twentyNine" style="width:90%" class=" easyui-textbox " data-options="" value="${smsEmployeeSchedule.twentyNine}">
									</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">30号：</td>
				<td width="75%">
											<input id="thirty" name="thirty" style="width:90%" class=" easyui-textbox " data-options="" value="${smsEmployeeSchedule.thirty}">
									</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">31号：</td>
				<td width="75%">
											<input id="thirtyOne" name="thirtyOne" style="width:90%" class=" easyui-textbox " data-options="" value="${smsEmployeeSchedule.thirtyOne}">
									</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">供应商：</td>
				<td width="75%">
											<input id="supplier" name="supplier" style="width:90%" class=" easyui-textbox " data-options="required:true ,validType:'length[9,9]'" value="${smsEmployeeSchedule.supplier}">
									</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">创建者：</td>
				<td width="75%">
											<input id="creator" name="creator" style="width:90%" class=" easyui-textbox " data-options="" value="${smsEmployeeSchedule.creator}">
									</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">创建时间：</td>
				<td width="75%">
											<input id="createTime" name="createTime" style="width:90%" class="easyui-datetimebox" data-options="" value="${smsEmployeeSchedule.createTime}">
									</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">：</td>
				<td width="75%">
											<input id="updator" name="updator" style="width:90%" class=" easyui-textbox " data-options="" value="${smsEmployeeSchedule.updator}">
									</td>
			</tr>
												<tr>
				<td width="25%" style="text-align:right">更新时间：</td>
				<td width="75%">
											<input id="updateTime" name="updateTime" style="width:90%" class="easyui-datetimebox" data-options="" value="${smsEmployeeSchedule.updateTime}">
									</td>
			</tr>
								</table>
	</form>
	<script type="text/javascript">
	$(function(){
		initActions();
					});
		function initActions(){
			}
		</script>
</body>
</html>	