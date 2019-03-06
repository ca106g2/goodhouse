<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.ad.model.*"%>
<%
	AdVO adVO = (AdVO) request.getAttribute("adVO");
%>
	<jsp:useBean id="houSvc" scope="page" class="com.goodhouse.house.model.HouseService"/>
	<jsp:useBean id="adSvc" scope="page" class="com.goodhouse.ad.model.AdService"/>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor='white' style="font-family:Microsoft JhengHei;">
	<h4>MVC</h4>
	<c:if test="${not empty errorMsgs}">
		<font style="color:red" >請修正以下錯誤</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color:red">message</li>
			</c:forEach>
		</ul>
	</c:if>
	<ul>
		<li><a href="listAllAd.jsp">list</a>all Ad<br><br></li>
		<li>
				<form method="post" action="ad.do">
					<b>輸入廣告編號</b> <input type="text" name="ad_id"> 
					<input type="hidden" name="action" value="front_ad_search">
					<input type="submit" value="送出">
				</form>
		</li>
	<li>	
		<form METHOD="post" ACTION="ad.do">					
			<b>輸入廣告名稱</b>
			<select name="ad_id">
				<c:forEach var="adVO" items="${adSvc.all}">
				<c:forEach var="houVO" items="${houSvc.all}">
					<c:if test="${adVO.hou_id eq houVO.hou_id}">
						<option value="${adVO.ad_id}">${houVO.hou_name}
					</c:if>
				</c:forEach>
				</c:forEach>
			</select>
			<input type="hidden" name="action" value="front_ad_search">
			<input type="submit" value="送出">
		</form>
	</li>
	</ul>
	
	<h3>檢舉管理</h3>
	<ul>
		<li><a href='addAd.jsp'>申請廣告</a></li>
	</ul>
</body>
</html>