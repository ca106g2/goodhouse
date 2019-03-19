<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.goodhouse.ad.model.*" %>
<%
	AdService adSvc = new AdService();
	List<AdVO> list = adSvc.getAll();
	pageContext.setAttribute("list",list);
%>
<jsp:useBean id="ad_sorSvc" scope="page" class="com.goodhouse.ad_sort.model.Ad_sortService"/>
<jsp:useBean id="lanSvc" scope="page" class="com.goodhouse.landlord.model.LanService"/>
<jsp:useBean id="houSvc" scope="page" class="com.goodhouse.house.model.HouseService"/>
<jsp:useBean id="memSvc" scope="page" class="com.goodhouse.member.model.MemService"/>
<html>
<head>

<style>
 #table1 { 

  font-family: 微軟正黑體; 
  font-size:16px; 
  width:1500px;
  border:2px solid #000;
  text-align:center;
  border-collapse:collapse;
  margin-left:auto; 
  margin-right:auto;
} 
 #table1 th { 
  background-color: #009FCC;
  padding:10px;

  color:#fff;
  border:2px solid #000;
} 
 #table1 td { 
  border:1px solid #000;
  padding:5px;
} 


</style>

</head>
<div margent=left;>

</div>


<jsp:include page="/BackHeaderFooter/Header.jsp" />	
	<body> 

<div class="container-fluid">
<div class="row">
<div class="col-2">
 <jsp:include page="/back/ad/select_page.jsp" />
    
</div>
 <div class="col-10">
  	<h4><a href="">回首頁</a></h4>
	<c:if test="${not empty errorMsgs}">
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
	</c:if>

	<table id="table1">
		<tr class="table-black">
			<td>廣告類別</td><!--廣告分類編號 AD_SORTU_ID-->
<!--			<td>廣告編號</td>--><!--廣告編號 AD_ID-->
			<td>房東名子</td><!--房東姓名 LAN_id-->
			<td>房屋名稱</td><!--房屋名稱 HOU_id-->
			<td>廣告備註</td><!--廣告備註 AD_FORFREE-->
			<td>繳費狀態</td><!--繳費狀態(已收,未收)AD_STATUE-->
			<td>付款方式</td><!--付款方式 AD_PAYMETHODS-->
			<td>廣告刊登日</td><!--廣告刊登日 AD_DATE-->
			<td>廣告狀態</td><!--廣告刊登日 AD_DATE-->		
			<td>修改</td>
		</tr>
		<%@ include file="pages/page1.file" %>
		<c:forEach var="adVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1 %>">
			<tr class="table-info" align='center'>
				<td><c:forEach var="ad_sorVO" items="${ad_sorSvc.all}">
					<c:if test="${adVO.ad_sort_id eq ad_sorVO.ad_sort_id }">
					${ad_sorVO.ad_chargetype}	
					</c:if>
				</c:forEach></td>
				<!--<td><%-- ${adVO.ad_id}--%></td>-->
				<td><c:forEach var="memVO" items="${memSvc.all}">
						<c:forEach var="lanVO" items="${lanSvc.all}">
							<c:if test="${lanVO.mem_id eq memVO.mem_id}">
								<c:if test="${adVO.lan_id eq lanVO.lan_id}">
									${memVO.mem_name}
								</c:if>
							</c:if>
						</c:forEach>
				</c:forEach></td>
				<td><c:forEach var="houVO" items="${houSvc.all}">
					<c:if test="${adVO.hou_id eq houVO.hou_id}">
					${houVO.hou_name}
					</c:if>	
				</c:forEach></td>
				<td>${adVO.ad_forfree}</td>
				<td>${adVO.ad_statue}</td>
				<td>${adVO.ad_paymethod}</td>
				<td>${adVO.ad_date}</td>
				<td>${adVO.ad_status}</td>
				<td>
					<form method="post" action="<%=request.getContextPath()%>/back/ad/ad.do" style="margin-bottom: 0px;">
						<input type="submit" value="修改">
						<input type="hidden" name="ad_id" value="${adVO.ad_id}">
						<input type="hidden" name="hou_id" value="${adVO.hou_id}">
						<input type="hidden" name="lan_id" value="${adVO.lan_id}">
						<input type="hidden" name="ad_sort_id" value="${adVO.ad_sort_id}">
						<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
						<input type="hidden" name="whichPage" value="<%=whichPage%>">
						<input type="hidden" name="action" value="getOne_For_Update">
					</form>
				</td>	
			</tr>
		</c:forEach>
	</table>
	<%@ include file="pages/page2.file" %>
	</div>
</div>
</div>
	<jsp:include page="/FrontHeaderFooter/Footer.jsp" />	
</body>

</html>