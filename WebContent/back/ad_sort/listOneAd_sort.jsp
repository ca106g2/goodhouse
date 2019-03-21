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
	
	<table class="table table-hover">
  <thead>
    <tr>
		<th scope="row">廣告類型</th>
		<th scope="row">廣告費用型態</th>
		<th scope="row">費用</th>
	</tr>
	</thead>
	<tbody>	
		<tr>
			<td><%=ad_sortVO.getAd_forfree() %></td>
			<td><%=ad_sortVO.getAd_chargetype() %></td>
			<td><%=ad_sortVO.getAd_charge() %></td>
		<td>
		</td>
		</tr>
	</tbody>	
</table>

	<jsp:include page="/BackHeaderFooter/Footer.jsp" />	
</body>
</html>