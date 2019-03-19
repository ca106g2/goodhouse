<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.house.model.*" %>
<%@ page import="com.goodhouse.ad_sort.model.*" %>
<%@ page import="com.goodhouse.ad.model.*" %>
<%@ page import="com.goodhouse.member.model.*" %>
<%@ page import="com.goodhouse.landlord.model.*" %>
<%
	AdVO adVO =(AdVO) request.getAttribute("adVO");
%>
<%-- HouseVO物件--%>
<%
	
	HouseService houSvc = new HouseService();
	HouseVO houVO = houSvc.getOneHouse(adVO.getHou_id());
%>
<%-- Ad_sortVO物件--%>	
<%		
	Ad_sortService ad_sorSvc = new Ad_sortService();
	Ad_sortVO ad_sorVO = ad_sorSvc.getOneAd_sort(adVO.getAd_sort_id());
%>

<%-- lan_sortVO物件--%>	
<%		
	LanService lanSvc = new LanService();
	LanVO lanVO = lanSvc.getOneLan(adVO.getLan_id());
%>

<%-- memV_sortVO物件--%>	
<%		
	MemService memSvc = new MemService();
	MemVO memVO = memSvc.getOneMem(lanVO.getMem_id());
%>

<html>
<head>

<style>
table {
	width:1800px;
	background-color: white;
	margin-top: 10px;
	margin-bottom: 10px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 20px;
	text-align: center;
}
</style>
<title>Insert title here</title>
</head>
<body bgcolor='white'>
<jsp:include page="/BackHeaderFooter/Header.jsp" />

	<h4><a href="<%=request.getContextPath()%>/back/ad/select_page.jsp">回首頁</a></h4>
	
	<table>
		<tr>
			<td>廣告類別</td><!--廣告分類編號 AD_SORTU_ID-->
			<td>廣告編號</td><!--廣告編號 AD_ID-->
			<td>房東名子</td><!--房東姓名 LAN_NAME-->
			<td>房屋名稱</td><!--房屋名稱 HOU_NAME-->
			<td>廣告備註</td><!--廣告備註 AD_FORFREE-->
			<td>繳費狀態</td><!--繳費狀態(已收,未收)AD_STATUE-->
			<td>付款方式</td><!--付款方式 AD_PAYMETHODS-->
			<td>廣告刊登日</td><!--廣告刊登日 AD_DATE-->
			<td>廣告狀態</td><!--(上架,下架)AD_STATUS -->
		</tr>
		<tr>
			<td><%=ad_sorVO.getAd_chargetype()%></td>
			<td><%=adVO.getAd_id()%></td>
			<td><%=memVO.getMem_name()%></td>
			<td><%=houVO.getHou_name()%></td>
			<td><%=adVO.getAd_forfree()%></td>
			<td><%=adVO.getAd_statue()%></td>
			<td><%=adVO.getAd_paymethod()%></td>
			<td><%=adVO.getAd_date()%></td>
			<td><%=adVO.getAd_status()%></td>
		</tr>
	
	</table>
	
	
<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
</body>
</html>