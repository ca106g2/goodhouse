<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.goodhouse.keyword.model.*"%>
<!DOCTYPE html>
<html>
<head>
<title>關鍵字列出 - listAllKW.jsp</title>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>
<table id="table-1">
	<tr><td>
		 <h3>列出所有關鍵字- listAllKW.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

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
		<th>關鍵字</th>
	</tr>
</table>

</body>
</html>