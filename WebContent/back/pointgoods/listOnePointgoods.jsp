<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.goodhouse.pointgoods.model.*" %>

<%
	PointgoodsVO pointgoodsVO = (PointgoodsVO) request.getAttribute("pointgoodsVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List One Product</title>

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
<body bgcolor='white'>
<table id="table-1">
	<tr><td>
		 <h3>商品資訊</h3>
		 <h4><a href="select_page.jsp"><img src="images/test1.jpg" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<td>積分商品編號</td>
		<td>積分商品名稱</td>
		<td>積分商品描述</td>
		<td>積分商品存量</td>
		<td>積分商品價格</td>
		<td>積分商品狀態</td>
		<td>積分商品圖片</td>
	</tr>
	<tr>
		<td>${pointgoodsVO.good_id}</td>
		<td>${pointgoodsVO.good_nam}</td>
		<td>${pointgoodsVO.good_dsc}</td>
		<td>${pointgoodsVO.good_amo}</td>
		<td>${pointgoodsVO.good_pri}</td>
		<td>${pointgoodsVO.good_sta}</td>
		<td><img src="<%=request.getContextPath() %>/PointgoodsServlet?good_id=${pointgoodsVO.good_id}" width="100" height="100"></td>
	</tr>
</table>

</body>
</html>