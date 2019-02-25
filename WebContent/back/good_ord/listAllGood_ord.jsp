<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>
<%@ page import="com.goodhouse.good_ord.model.*" %>

<%
	Good_ordService good_ordSvc = new Good_ordService();
	List<Good_ordVO> list = good_ordSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>所有積分訂單</title>
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
	width: 800px;
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
<table id="table-1">
	<tr><td>
		 <h3>積分商品訂單列表</h3>
		 <h4><a href="select_page.jsp"><img src="images/test1.jpg" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<td>積分商品訂單編號</td>
		<td>會員編號</td>
		<td>訂單日期時間</td>
		<td>訂單狀態</td>
		<td>收件人</td>
		<td>收貨地址</td>
		<td>修改</td>
		<td>刪除</td>
	</tr>
	<%@ include file="page1.file" %>
	<c:forEach var="good_ordVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1 %>">
		<tr>
			<td>${good_ordVO.good_ord_id}</td>
			<td>${good_ordVO.mem_id}</td>
			<td>${good_ordVO.good_ord_dat}</td>
			<td>
				<c:if test="${(good_ordVO.good_ord_sta == 'GO001') }">備貨中</c:if>
				<c:if test="${(good_ordVO.good_ord_sta == 'GO002') }">出貨中</c:if>
			</td>
			<td>${good_ordVO.good_ord_nam}</td>
			<td>${good_ordVO.good_ord_add}</td>
			<td>
				<form method="post" action="<%=request.getContextPath()%>/back/good_ord/go.do" style="margin-bottom: 0px;">
					<input type="submit" value="修改">
					<input type="hidden" name="good_ord_id" value="${good_ordVO.good_ord_id}">
					<input type="hidden" name="action" value="getOne_For_Update">				
				</form>
			</td>
			<td>
				<form method="post" action="<%=request.getContextPath()%>/back/good_ord/go.do" style="margin-bottom: 0px;">
					<input type="submit" value="刪除">
					<input type="hidden" name="good_ord_id" value="${good_ordVO.good_ord_id}">
					<input type="hidden" name="action" value="delete">
				</form>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
</body>
</html>