<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.ad.model.*" %>
<%@ page import="java.util.List"%>

<jsp:useBean id="list" scope="session" type="java.util.List<AdVO>" /> <!-- 於EL此行可省略 -->
<jsp:useBean id="adSvc" scope="page" class="com.goodhouse.ad.model.AdService"/>
<jsp:useBean id="ad_sorSvc" scope="page" class="com.goodhouse.ad_sort.model.Ad_sortService"/>
<jsp:useBean id="lanSvc" scope="page" class="com.goodhouse.landlord.model.LanService"/>
<jsp:useBean id="memSvc" scope="page" class="com.goodhouse.member.model.MemService"/>
<jsp:useBean id="houSvc" scope="page" class="com.goodhouse.house.model.HouseService"/>
<html>
<head>

<style>
 table { 

  font-family: 微軟正黑體; 
  font-size:16px; 
  width:1500px;
  border:2px solid #000;
  text-align:center;
  border-collapse:collapse;
  margin-left:auto; 
  margin-right:auto;
} 
th { 
  background-color: #009FCC;
  padding:10px;

  color:#fff;
  border:2px solid #000;
} 
td { 
  border:1px solid #000;
  padding:5px;
} 

div{
	margin:0px auto;
}
</style>

</head>
<body bdcolor="white">

<jsp:include page="/BackHeaderFooter/Header.jsp"/>

<h4></h4>
	<font color=red></font>
	
	<table id="table-1">
	<tr><td>
		<h3>查詢結果</h3>
		<h4><a href="<%=request.getContextPath()%>/back/ad/select_page.jsp">回首頁</a></h4>
	</td></tr>
	</table>

<table>
	<tr class="table-black">
		<th>房東名子</th>
		<th>房屋名稱</th>
		<th>廣告分類</th>
		<th>廣告備註</th>
		<th>繳費狀態</th>
		<th>付款方式</th>
		<th></th>	
	</tr>
		<%@ include file="pages/page1.file"%>
	<c:forEach var="adVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1 %>">
	 	<tr class="table-info" align='center'>
	 		<td>${memSvc.getOneMem(lanSvc.getOneLan(adVO.lan_id).mem_id).mem_name}</td>
	 		<td>${houSvc.getOneHouse(adVO.hou_id).hou_name}</td>
	 		<td>${ad_sorSvc.getOneAd_sort(adVO.ad_sort_id).ad_forfree}</td>
	 		<td>${adVO.ad_forfree}</td>
	 		<td>${adVO.ad_statue}</td>
	 		<td>${adVO.ad_paymethod}</td>
			<td>
			<form method="post" action="<%=request.getContextPath()%>/back/ad/ad.do" style="margin-bottom: 0px;">
			<input type="submit" value="去看看">
			<input type="hidden" name="ad_id" value="${adVO.ad_id}">
			<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
			<input type="hidden" name="whichPage" value="<%=whichPage%>">
			<input type="hidden" name="action" value="back_ad_search">
			</form>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2.file" %>
<jsp:include page="/FrontHeaderFooter/Footer.jsp"/>
</body>
</html>