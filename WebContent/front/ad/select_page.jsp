<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.ad.model.*"%>

	<jsp:useBean id="houSvc" scope="page" class="com.goodhouse.house.model.HouseService"/>
	<jsp:useBean id="adSvc" scope="page" class="com.goodhouse.ad.model.AdService"/>
	<jsp:useBean id="ad_sortSvc" scope="page" class="com.goodhouse.ad_sort.model.Ad_sortService"/>

<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
<body>
<ul>

	
 <table>
 	<td>
		<form method="post" action="<%=request.getContextPath()%>/front/ad/ad.do" name="form1">
		<b><font color=blue>廣告查詢</font></b><br>
			<br>
	
			<b>請選擇廣告種類</b><br>
				<select size="1" name="ad_sort_id" class="form-control" id="exampleFormControlSelect1">
					<c:forEach var="ad_sortVO" items="${ad_sortSvc.all}">
						<option value=" ">
						<option value="${ad_sortVO.ad_sort_id}">${ad_sortVO.ad_chargetype}
					</c:forEach>		
				</select>
<!--  (後臺用)-->	<b>廣告狀態</b><br>
				<select size="1" name="ad_status" class="form-control" id="exampleFormControlSelect1">
					<option value="">
					<option value="ad_satus001"${(adVO.ad_status == "ad_satus001")? 'selected':'' }>上架
					<option value="ad_satus002"${(adVO.ad_status == "ad_satus002")? 'selected':'' }>下架	
				</select><br>
				
			<button type="submit" value="送出" class="btn btn-outline-secondary">送出</button>
			<input type="hidden" name="action" value="listAd_ByCompositeQuery">
				
		</form>
	</td>
</table>
	
</ul>
</body>
</html>