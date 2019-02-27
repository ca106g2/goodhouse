<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FAQ首頁</title>
</head>
<body>

<table id="table-1">
   <tr><td><h3>Select FAQ</h3><h4>( MVC )</h4></td></tr>
</table>

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
	<li>
		<form method="post" action="<%= request.getContextPath() %>/faq/faqcontrol">
			<b>輸入FAQ編號: (FAQ000001)</b>
			<input type="text" name="faqno">
			<input type="hidden" name="action" value="getOne">
			<input type="submit" value="送出">
		</form>
	</li>
	
</ul>


<ul>
  <li><a href='<%= request.getContextPath() %>/CA106G6/frontend/faq/addfaq.jsp'>Add</a> a new faq.</li>
</ul>


<ul><li>
	<form method="post" action="<%= request.getContextPath() %>/faq/faqcontrol">
		<h2>FAQ種類</h2>
		<select size="1" name="faqstyle">
			<option value="0" name="faqstyle">商品代購
			<option value="1" name="faqstyle">套裝行程
			<option value="2" name="faqstyle">機加酒商品
			<option value="3" name="faqstyle">自由行程
		</select>	
		<input type="hidden" value="getstylefaq" name="action">
		<input type="submit" value="送出國家">
	</form>
</li></ul>

</body>
</html>