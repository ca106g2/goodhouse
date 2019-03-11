<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.goodhouse.ad_sort.model.*" %>
<%
	Ad_sortVO ad_sortVO = (Ad_sortVO) request.getAttribute("ad_sortVO");
%>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
<style>

</style>
</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		<h4><a href="<%=request.getContextPath()%>/back/ad_sort/select_page.jsp">回首頁</a></h4>	
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
<form method="post" action="ad_sor.do" name="form1">
	<table>
	
		<tr>
			<td>廣告類別編號</td>
			<td><%=ad_sortVO.getAd_sort_id()%></td>
		</tr>

		<tr>
		
			<td>廣告類型</td>
			
			<td><input type="text" name="ad_forfree" size="45" value="<%=ad_sortVO.getAd_forfree()%>"></td>
			
		</tr>
		
		<tr>
		
			<td>廣告費用型態</td>
			
			<td><input type="text" name="ad_chargetype" size="45" value="<%=ad_sortVO.getAd_chargetype() %>"></td>
			
		</tr>
		
		<tr>
		
			<td>費用</td>
			
			<td><input type="text" name="ad_charge" size="45" value="<%=ad_sortVO.getAd_charge() %>"></td>
			
		</tr>
		
	</table>
	
	<input type="hidden" name="action" value="update">
	<input type="hidden" name="ad_sort_id" value="<%=ad_sortVO.getAd_sort_id() %>">
	<input type="submit" value="送出修改">
</form>
</body>
</html>