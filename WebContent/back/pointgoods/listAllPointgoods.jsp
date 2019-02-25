<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>
<%@ page import="com.goodhouse.pointgoods.model.*" %>

<%
	PointgoodsService pointgoodsSvc = new PointgoodsService();
	List<PointgoodsVO> list = pointgoodsSvc.getAll();
	pageContext.setAttribute("list",list);
%>
<!DOCTYPE html>
<html>
<head>
<title>所有積分商品</title>
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
<body bgcolor='white'>
<table id="table-1">
	<tr><td>
		 <h3>積分商品列表</h3>
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
		<td>積分商品編號</td>
		<td>積分商品名稱</td>
		<td>積分商品描述</td>
		<td>積分商品存量</td>
		<td>積分商品價格</td>
		<td>積分商品狀態</td>
		<td>積分商品圖片</td>
		<td>修改</td>
		<td>刪除</td>
	</tr>
	<%@ include file="page1.file" %>
	<c:forEach var="pointgoodsVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	<tr>
		<td>${pointgoodsVO.good_id}</td>
		<td>${pointgoodsVO.good_nam}</td>
		<td>${pointgoodsVO.good_dsc}</td>
		<td>${pointgoodsVO.good_amo}</td>
		<td>${pointgoodsVO.good_pri}</td>
		<td>
			<c:if test="${(pointgoodsVO.good_sta == 'P001')}">上架</c:if>
			<c:if test="${(pointgoodsVO.good_sta == 'P002')}">下架</c:if>
		</td>
		<td><img src="<%=request.getContextPath() %>/PointgoodsServlet?good_id=${pointgoodsVO.good_id}" width="100" height="100"></td>
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back/pointgoods/pg.do" style="margin-bottom: 0px;">
		    	<input type="submit" value="修改">
		    	<input type="hidden" name="good_id"  value="${pointgoodsVO.good_id}">
		    	<input type="hidden" name="action"	value="getOne_For_Update">
		    </FORM>
		</td>
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back/pointgoods/pg.do" style="margin-bottom: 0px;">
				<input type="submit" value="刪除">
				<input type="hidden" name="good_id"  value="${pointgoodsVO.good_id}">
				<input type="hidden" name="action" value="delete">
			</FORM>
		</td>
	</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>