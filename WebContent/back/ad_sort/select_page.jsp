<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta charset="UTF-8">

</head>
<body bgcolor='white' span style="font-family:Microsoft JhengHei;">

<c:if test="${not empty errorMsgs}">
	<font color='red'>查詢失敗</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
	<li><a href='listAllAd_sort.jsp'>目前擁有廣告種類</a></li>
	<jsp:useBean id="ad_sortSvc" scope="page" class="com.goodhouse.ad_sort.model.Ad_sortService"/>
	<li>
		<form method="post" action="ad_sor.do">
			<b>請選擇廣告種類</b>
				<select size="1" name="ad_sort_id">
					<option value="">
					<c:forEach var="ad_sortVO" items="${ad_sortSvc.all}">
						<option value="${ad_sortVO.ad_sort_id}">${ad_sortVO.ad_chargetype}
					</c:forEach>		
				</select>
				<input type="submit" value="送出">
				<input type="hidden" name="action" value="getOne_For_Ad_sort">
		</form>
	</li>
</ul>

<ul>
	<li><a href="addAd_sort.jsp">新增廣告類型</a></li>
</ul>


</body>
</html>