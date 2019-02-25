<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>Pointgoods</title>
<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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
</head>
<body bgcolor='white'>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
	<li><a href='listAllPointgoods.jsp'>List</a> all Product.  <br><br></li>
</ul>
<h3>積分商品新增</h3>

<ul>
  <li><a href='addPointgoods.jsp'>Add</a> a new Product.</li>
</ul>
<h3>積分商品查詢</h3>

<ul>
	<li>
		<form method="post" action="pg.do">
			<b>請輸入商品編號(如P000000001)</b>
			<input type="text" name="good_id">
			<input type="hidden" name="action" value="getOne_For_Display">
			<input type="submit" value="送出">
		</form>
	</li>
</ul>

<jsp:useBean id="pointgoodsSvc" scope="page" class="com.goodhouse.pointgoods.model.PointgoodsService"/>
<ul>
  <li>
  	<form action="pg.do" method="post">
  		<b>請選擇積分商品編號</b>
  		<select size="1" name="good_id">
  			<c:forEach var="pointgoodsVO" items="${pointgoodsSvc.all}">
  				<option value="${pointgoodsVO.good_id}">${pointgoodsVO.good_id}
  			</c:forEach>
  		</select>
  		<input type="hidden" name="action" value="getOne_For_Display">
  		<input type="submit" value="送出">
  	</form>
  </li>
</ul>
</body>
</html>