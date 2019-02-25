<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Good_ord</title>
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

<h3>資料查詢 :</h3>

<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs }">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
	<li><a = href="listAllGood_ord.jsp">List</a> all Good_ord. <br></li>
</ul>
<h3>積分商品訂單新增</h3>

<ul>
	<li><a href="addGood_ord.jsp">Add</a> a new Good_ord.</li>
</ul>
<h3>積分商品訂單查詢</h3>

<ul>
	<li>
		<form method="post" action="go.do">
			<b>請輸入商品訂單編號(如:O000000001)</b>
			<input type="text" name="good_ord_id">
			<input type="hidden" name="action" value="getOne_For_Display">
			<input type="submit" value="送出">
		</form>
	</li>
</ul>

<jsp:useBean id="good_ordSvc" scope="page" class="com.goodhouse.good_ord.model.Good_ordService"/>
<ul>
	<li>
		<form action="go.do" method="post">
			<b>請選擇積分訂單編號:</b>
			<select size="1" name="good_ord_id">
				<c:forEach var="good_ordVO" items="${good_ordSvc.all}">
					<option value="${good_ordVO.good_ord_id}">${good_ordVO.good_ord_id}
				</c:forEach>
			</select>
			<input type="hidden" name="action" value="getOne_For_Display">
			<input type="submit" value="送出">
		</form>
	</li>
</ul>



</body>
</html>