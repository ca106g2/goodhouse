<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ page import="java.util.*" %>
<%@ page import="com.goodhouse.ad_report.model.*" %>
<%
Ad_reportService ad_repSvc = new Ad_reportService();
List<Ad_reportVO> list = ad_repSvc.getAll();
pageContext.setAttribute("list",list);
%>
<jsp:useBean id="lanSvc" scope="page" class="com.goodhouse.landlord.model.LanService" />
<jsp:useBean id="memSvc" scope="page" class="com.goodhouse.member.model.MemService" />
<jsp:useBean id="houSvc" scope="page" class="com.goodhouse.house.model.HouseService" />
<jsp:useBean id="adSvc" scope="page" class="com.goodhouse.ad.model.AdService" />



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
<div class="container-fluid">
		<div class="row justify-content-center">

			<div col="col-2">
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤</font>
	<ul>
	<c:forEach var="message" items="${errorMsgs}">
		<li style="color:red">${message}</li>
	</c:forEach>
	</ul>
</c:if>
</div>

<div class="col-10">
<div class="card">

					<div class="card-header">
						<h3>廣告檢舉列表</h3>
					</div>

					<div class="card-body">
						<div class="table-responsive">
							<table class="table table-bordered table-hover mb-0 text-nowrap text-center">
								<tbody>
									<tr style="background-color: #EDF9DE;">
		<th scope="col">被檢舉廣告</th>
		<th scope="col">檢舉房客</th>
		<th scope="col">檢舉內容</th>
		<th scope="col">檢舉狀態</th>
		<th scope="col">檢舉日期</th>
		<th scope="col"></th>
	</tr>
	<%@ include file="page1.file" %>
	<c:forEach var="ad_repVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">	
	<tr>	
		<td>${houSvc.getOneHouse(adSvc.getOneAD(ad_repVO.ad_id).hou_id).hou_name}</td>
		<td>${memSvc.getOneMem(ad_repVO.mem_id).mem_name}</td>
		<td>${ad_repVO.ad_rep_reason}</td>
		<td>${ad_repVO.ad_rep_status}</td>
		<td>${ad_repVO.ad_rep_date}</td>
		<td>
		
		<form method="post" action="<%=request.getContextPath() %>/back/ad_report/ad_rep.do" style="margin-bottom: 0px;">
		 <input type="submit" class="btn btn-outline-success" value="修改">
		 <input type="hidden" name="ad_rep_id" value="${ad_repVO.ad_rep_id}">
		 <input type="hidden" name="action"value="getOne_For_Update">
		</form>
		</td>
	</tr>
	</c:forEach>
								</tbody>
							</table>
<%@ include file="page2.file" %>
					</div>
					</div>
				</div>
			</div>
	</div>
	</div>		
<jsp:include page="/BackHeaderFooter/Footer.jsp" />	
</body>	
</html>