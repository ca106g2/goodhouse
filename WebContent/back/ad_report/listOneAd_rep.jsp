<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ page import="com.goodhouse.ad_report.model.*"%>
<%@ page import="com.goodhouse.member.model.*" %>
<%@ page import="com.goodhouse.ad.model.*" %>
<%@ page import="com.goodhouse.house.model.*" %>
<%
	Ad_reportVO ad_repVO = (Ad_reportVO) request.getAttribute("ad_repVO");
%>
<jsp:useBean id="adSvc" scope="page" class="com.goodhouse.ad.model.AdService"/>
<jsp:useBean id="houSvc" scope="page" class="com.goodhouse.house.model.HouseService"/>
<jsp:useBean id="memSvc" scope="page" class="com.goodhouse.member.model.MemService"/>
<html>
<head>
<meta charset="UTF-8">

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
<jsp:include page="/BackHeaderFooter/Header.jsp" />	
<body>

	<table id="table1">
		<tr>
		<th>被檢舉廣告</th>
		<th>檢舉房客</th>
		<th>檢舉內容</th>
		<th>檢舉狀態</th>
		<th>檢舉日期</th>
		</tr>
		<tr>
			<%
				AdVO adVO = adSvc.getOneAD(ad_repVO.getAd_id());
				HouseVO houVO =  houSvc.getOneHouse(adVO.getHou_id());
			%>
		<td><%=houVO.getHou_name() %></td>
			<%
				MemVO memVO = memSvc.getOneMem(ad_repVO.getMem_id());
			%>
		<td><%=memVO.getMem_name() %></td>
		<td><%=ad_repVO.getAd_rep_reason() %></td>
		<td><%=ad_repVO.getAd_rep_status() %></td>
		<td><%=ad_repVO.getAd_rep_date() %></td>
		</tr>
	</table>
	<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
</body>
</html>