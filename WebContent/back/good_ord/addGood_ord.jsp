<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.goodhouse.good_ord.model.*" %>
<%@ page import="com.goodhouse.member.model.*" %>
<%
	Good_ordVO good_ordVO = (Good_ordVO) request.getAttribute("good_ordVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add new Good_ord</title>
<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>
</head>
<body bgcolor="white">

<table id="table-1">
	<tr>
		<td>
			<h3>新增商品訂單</h3>
		</td>
		<td>
			<h4><a href="select_page.jsp"><img src="images/test1.jpg" width="100" height="100" border="0">回首頁</a></h4>
		</td>
	</tr>
</table>
<h3>資料新增:</h3>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<form method="post" action="go.do">
<table>
	<jsp:useBean id="memSvc" scope="page" class="com.goodhouse.member.model.MemService"/>
	<tr>
		<td>會員姓名:<font color="red"><b>*</b></font></td>
		<td>
			<select size="1" name="mem_id">
				<c:forEach var="memVO" items="${memSvc.all}">
					<option value="${memVO.mem_id}" ${(good_ordVO == memVO.mem_id)? 'selected':'' }>${memVO.mem_name}
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td>訂單狀態</td>
		<td>
			<select name="good_ord_sta">
				<option value="GO001">處理中
				<option value="GO002">已出貨
			</select>
		</td>
	</tr>
	<tr>
		<td>收件人</td>
		<td><input type="text" name="good_ord_nam" size="45" value="<%= (good_ordVO==null)? "帥哥" : good_ordVO.getGood_ord_nam()  %>"></td>
	</tr>
	<tr>
		<td>收貨地址</td>
		<td><input type="text" name="good_ord_add" size="45" value="<%= (good_ordVO==null)? "320桃園市中壢區中大路300號" : good_ordVO.getGood_ord_add() %>"></td>
	</tr>
</table>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增">
</form>

</body>
</html>