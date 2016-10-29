<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/meta.jsp"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>
<%@ include file="/WEB-INF/views/include/textext.jsp"%>
</head>
<body>

	<form id="multiEmployeeform" action=""
		method="post" >
		<table class="formTable" width="100%" border="0" cellspacing="0"
			cellpadding="5">
			<tr>
				<td width="100%">
					<textarea id="employees" name="employees"
						style="width:320px; height: 50px;"></textarea>
				</td>
			</tr>

		</table>
	</form>
	<script type="text/javascript">
		$(function() {
			initActions();
		});
		function initActions() {
			$('#employees').textext({
				plugins:'autocomplete tags ajax ',
				ajax:{
					url:ctx + '/smsEmployee/find',
					dataType:'json',
					data:{page:1,rows:5},
					dataCallback:function(query){return {filter_likes_name:query}},
					cacheResults:false
					
				},
				ext:{
					itemManager:{
						stringToItem:function(str){
							return str;
						},
						itemToString:function(item){
							return "<div style='height:25px;'><span>"+ item.name + "</span><input type='hidden' value='"+item.id+"'></div>"; 
							/* return item.name; */
						},
						itemContains:function(item, needle){
							return item.name.indexOf(needle) == 0;
						}
					}
				}
			});
		}
	</script>
</body>
</html>
