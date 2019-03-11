<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.ad.model.*" %>
<%@ page import="java.util.List"%>

<jsp:useBean id="listAd_ByCompositeQuery" scope="session" type="java.util.List<AdVO>" /> <!-- 於EL此行可省略 -->
<jsp:useBean id="adSvc" scope="page" class="com.goodhouse.ad.model.AdService"/>
<jsp:useBean id="ad_sorSvc" scope="page" class="com.goodhouse.ad_sort.model.Ad_sortService"/>
<jsp:useBean id="lanSvc" scope="page" class="com.goodhouse.landlord.model.LanService"/>
<jsp:useBean id="memSvc" scope="page" class="com.goodhouse.member.model.MemService"/>
<jsp:useBean id="houSvc" scope="page" class="com.goodhouse.house.model.HouseService"/>
<html>
<head>
<meta charset="UTF-8">
<style>
  table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>
</head>
<body bdcolor="white">

<h4></h4>
	<font color=red></font>
	
	<table id="table-1">
	<tr><td>
		<h3>查詢結果</h3>
		<h4><a href="<%=request.getContextPath()%>/front/ad/select_page.jsp">回首頁</a></h4>
	</td></tr>
	</table>

<table>
	<tr>
		<th>房東名子</th>
		<th>房屋名稱</th>
		<th>廣告分類</th>
		<th>廣告備註</th>
		<th>繳費狀態</th>
		<th>付款方式</th>
		<th></th>	
	</tr>
		<%@ include file="pages/page1.file"%>
	<c:forEach var="adVO" items="${listAd_ByCompositeQuery}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1 %>">
	 	<tr align='center' valign='middle' ${(adVO.ad_id == param.ad_id) ? 'bgcolor=#CCCCFF' : ''}>
	 		<td>${memSvc.getOneMem(lanSvc.getOneLan(adVO.lan_id).mem_id).mem_name}</td>
	 		<td>${houSvc.getOneHouse(adVO.hou_id).hou_name}</td>
	 		<td>${ad_sorSvc.getOneAd_sort(adVO.ad_sort_id).ad_forfree}</td>
	 		<td>${adVO.ad_forfree}</td>
	 		<td>${adVO.ad_statue}</td>
	 		<td>${adVO.ad_paymethod}</td>
			<td>
			<form method="post" action="<%=request.getContextPath()%>/front/ad/ad.do" style="margin-bottom: 0px;">
			<input type="submit" value="去看看">
			<input type="hidden" name="ad_id" value="${adVO.ad_id}">
			<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
			<input type="hidden" name="whichPage" value="<%=whichPage%>">
			<input type="hidden" name="action" value="front_ad_search">
			</form>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2.file" %>

</body>
</html>