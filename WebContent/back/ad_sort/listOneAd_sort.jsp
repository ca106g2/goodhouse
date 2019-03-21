<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.goodhouse.ad_sort.model.*" %>

<%
	Ad_sortVO ad_sortVO = (Ad_sortVO) request.getAttribute("ad_sortVO");
%>
<html>
<head>
<meta charset="UTF-8">

</head>
<body>
	<jsp:include page="/BackHeaderFooter/Header.jsp" />	
	<table>
	<tr>
		<th>廣告分類編號</th>
		<th>廣告類型</th>
		<th>廣告費用型態</th>
		<th>費用</th>
	</tr>
	<tr>
	<td><%=ad_sortVO.getAd_sort_id() %></td>
	<td><%=ad_sortVO.getAd_forfree() %></td>
	<td><%=ad_sortVO.getAd_chargetype() %></td>
	<td><%=ad_sortVO.getAd_charge() %></td>
	</tr>
	</table>
	<jsp:include page="/BackHeaderFooter/Footer.jsp" />	
</body>
</html>