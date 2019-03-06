<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
<body>
<ul>


 <table>
 	<td>
		<form method="post" action="<%=request.getContextPath()%>/front/house/hou.do" name="form1">
			<b><font color=blue>租屋查詢</font></b><br>
			<br>
			<br>
			<b>房屋名稱</b>
			<input type="text" name="hou_name" value="限女性"  class="form-control-plaintext" id="staticEmail2">
			<br>
			<b>房屋類別</b>
			<select value="1" name="hou_type"class="form-control" id="exampleFormControlSelect5">
				<option value="">
				<option value="公寓">公寓
				<option value="套房">套房
			</select>
			<b>是否有停車位</b>
			<select value="1" name="hou_parkspace" class="form-control" id="exampleFormControlSelect1">
				<option value="">
				<option value="有">有
				<option value="無">無
			</select>
			<b>是否可烹飪</b>
			<select value="1" name="hou_cook" class="form-control" id="exampleFormControlSelect1">
				<option value="">
				<option value="是">是
				<option value="否">否
			</select>	
			<b>地區收尋</b>
			<input type="text" name="hou_address" value="台北"  class="form-control-plaintext" id="staticEmail2">
			<button type="submit" value="送出" class="btn btn-outline-secondary">送出</button>
			<input type="hidden" name="action" value="listHou_ByCompositeQuery">
		</form>
	</td>
</table>
	
</ul>
</body>
</html>