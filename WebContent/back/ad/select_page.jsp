<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.ad.model.*"%>

	<jsp:useBean id="houSvc" scope="page" class="com.goodhouse.house.model.HouseService"/>
	<jsp:useBean id="adSvc" scope="page" class="com.goodhouse.ad.model.AdService"/>
	<jsp:useBean id="ad_sortSvc" scope="page" class="com.goodhouse.ad_sort.model.Ad_sortService"/>

<html>
<head>
</head>
<body>


<ul>

	
 <table>
 	<td>
		<form method="post" action="<%=request.getContextPath()%>/back/ad/ad.do" name="form1">
		<b><font color=blue>廣告查詢</font></b><br>
			<br>
	
			<b>請選擇廣告種類</b><br>
				<select size="1" name="ad_sort_id" class="form-control" id="exampleFormControlSelect1">
						<option value=""></option>
					<c:forEach var="ad_sortVO" items="${ad_sortSvc.all}">
						<option value="${ad_sortVO.ad_sort_id}">${ad_sortVO.ad_chargetype}</option>
					</c:forEach>		
				</select>
<!--  (後臺用)-->	<b>廣告狀態</b><br>
				<select size="1" name="ad_status" class="form-control" id="exampleFormControlSelect1">
					<option value="">
					<option value="上架">上架</option>
					<option value="審核中">審核中</option>
					<option value="下架">下架</option>
				</select><br>
				
			<button type="submit" value="送出" class="btn btn-outline-secondary">送出</button>
			<input type="hidden" name="action" value="listAd_ByCompositeQuery">
				
		</form>
	</td>
</table>
	
</ul>
</body>
</html>