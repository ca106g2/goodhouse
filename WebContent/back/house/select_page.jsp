	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<html>
	<head>
	</head>
	<body>
				
	 <table>
	 	<td>
			<form method="post" action="<%=request.getContextPath()%>/back/house/hou.do" name="form1">
				<br><br>
				<b><font color=blue>租屋查詢</font></b>
				<br>
				<br>
				<b>房屋名稱</b>
				<input type="text" name="hou_name"  class="form-control-plaintext" id="staticEmail2">
				<br>
				<b>房屋類別</b>
				<select size="1" name="hou_type"class="form-control" id="exampleFormControlSelect5">
					<option value="">
					<option value="公寓">公寓
					<option value="套房">套房
				</select>
				<b>房屋審核</b>
				<select size="1" name="hou_parkspace" class="form-control" id="exampleFormControlSelect1">
					<option value="">
					<option value="未審核">未審核
					<option value="已審核">已審核
				</select>	
				<b>地區收尋</b>
				<input type="text" name="hou_address" class="form-control-plaintext" id="staticEmail2">
				<button type="submit" value="送出" class="btn btn-outline-secondary">送出</button>
				<input type="hidden" name="action" value="listHou_ByCompositeQuery">
			</form>
		</td>
	</table>
	</body>
	</html>