<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.goodhouse.good_ord.model.*" %>
<%
	Good_ordVO good_ordVO = (Good_ordVO) request.getAttribute("good_ordVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List One Good_ord</title>
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
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>
</head>
<body bgcolor="white">
<table>
	<tr>
		<td>
			<h3>訂單資訊</h3>
			<h4><a href="select_page.jsp"><img src="images/test1.jpg" width="100" height="100" border="0">回首頁</a></h4>
		</td>
	</tr>
</table>
<table>
	<tr>
		<td>積分商品訂單編號</td>
		<td>會員編號</td>
		<td>訂單日期時間</td>
		<td>訂單狀態</td>
		<td>收件人</td>
		<td>收貨地址</td>
	</tr>
	<tr>
		<td>${good_ordVO.good_ord_id}</td>
		<td>${good_ordVO.mem_id}</td>
		<td>${good_ordVO.good_ord_dat}</td>
		<td>${good_ordVO.good_ord_sta}</td>
		<td>${good_ordVO.good_ord_nam}</td>
		<td>${good_ordVO.good_ord_add}</td>
	</tr>
</table>

</body>
</html>