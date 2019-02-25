<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.goodhouse.good_ord.model.*" %>
<%@ page import="com.goodhouse.member.model.*" %>
<%
	Good_ordVO good_ordVO = (Good_ordVO)request.getAttribute("good_ordVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update</title>
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
<body bgcolor='white'>
<table id="table-1">
	<tr><td>
		 <h3>積分商品訂單修改</h3>
		 <h4><a href="select_page.jsp"><img src="images/test1.jpg" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>
<%--錯誤列表 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<form action="go.do" method="post">
<table>
	<tr>
		<td>積分商品訂單編號:<font color=red><b>*</b></font></td>
		<td>${good_ordVO.good_ord_id}</td>
	</tr>
	<jsp:useBean id="memSvc" scope="page" class="com.goodhouse.member.model.MemService" />
	<tr>
		<td>會員姓名:</td>
		<td>
			<% 
				MemVO memVO = memSvc.getOneMem(good_ordVO.getMem_id());
			%>
			<%=memVO.getMem_name() %>
		</td>
	</tr>
	<tr>
		<td>下訂日期時間:</td>
		<td>${good_ordVO.good_ord_dat}</td>
	</tr>
	<tr>
		<td>訂單狀態:</td>
		<td>
			<select name ="good_ord_sta">
				<option value="GO001" ${(good_ordVO.good_ord_sta == "GO001")? 'selected':'' }>處理中
				<option value="GO002" ${(good_ordVO.good_ord_sta == "GO002")? 'selected':'' }>已出貨
			</select>
		</td>
	</tr>
	<tr>
		<td>收件人姓名:</td>
		<td><input type="text" name="good_ord_nam" size="45" value="${good_ordVO.good_ord_nam}"></td>
	</tr>
	<tr>
		<td>收件地址:</td>
		<td><input type="text" name="good_ord_add" size="45" value="${good_ordVO.good_ord_add}" ></td>
	</tr>
</table>
<input type="hidden" name="good_ord_id" value="${good_ordVO.good_ord_id}">
<input type="hidden" name="mem_id" value="${good_ordVO.mem_id}">
<input type="hidden" name="good_ord_dat" value="${good_ordVO.good_ord_dat}">
<input type="hidden" name="action" value="update">
<input type="submit" value="送出修改">
</form>

</body>
</html>