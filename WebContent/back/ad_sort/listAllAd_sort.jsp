<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.goodhouse.ad_sort.model.*" %>

<%
 Ad_sortService ad_sortSvc = new Ad_sortService();
 List<Ad_sortVO> list = ad_sortSvc.getAll();
 pageContext.setAttribute("list",list);
%>
<jsp:useBean id="adSvc" scope="page" class="com.goodhouse.ad_sort.model.Ad_sortService"/>
<html>
<head>
<meta charset="UTF-8">

</head>
<body bgcolor='white'>

<jsp:include page="/BackHeaderFooter/Header.jsp" />	
<c:if test="${not empty errorMsgs}">
	<font style="color:red">以下欄位錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table class="table table-hover">
  <thead>
    <tr>
		<th scope="row">廣告類型</th>
		<th scope="row">廣告費用型態</th>
		<th scope="row">費用</th>
	</tr>
	</thead>
<tbody>	
	<c:forEach var="ad_sorVO" items="${list}">
		<tr>
			<td>${ad_sorVO.ad_forfree}</td>
			<td>${ad_sorVO.ad_chargetype}</td>
			<td>${ad_sorVO.ad_charge}</td>
		<td>
		<form method="post" action="<%=request.getContextPath() %>/back/ad_sort/ad_sor.do" style="margin-bottom: 0px;">
		<input type="submit" class="btn btn-secondary" value="修改">
		<input type="hidden" name="ad_sort_id" value="${ad_sorVO.ad_sort_id}">
		<input type="hidden" name="requestURL" value="<%=request.getServletPath() %>">
		<input type="hidden" name="action" value="getOne_For_Update">
		</form>
		</td>
		</tr>
	</c:forEach>
	</tbody>	
</table>
<jsp:include page="/BackHeaderFooter/Footer.jsp" />	
</body>
</html>