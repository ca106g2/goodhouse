<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.goodhouse.bill.model.*"%>
<%@ page import="javax.servlet.http.HttpSession" %>



<!doctype html>
<html lang="en">
<head>
	<!-- Required meta tags -->
	<meta name="viewport"
		content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<script src="<%=request.getContextPath()%>/File/jquery-1.12.4.min.js"></script>
	<!-- Bootstrap CSS start-->
	<link rel="stylesheet"
		href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css">
	<!-- Bootstrap CSS end-->
	<title></title>
	<style>
	  table#table-1 {
		background-color: #CCCCFF;
	    border: 2px solid black;
	    text-align: center;
	  }
	 #table-1 h4 {
	    color: red;
	    display: block;
	    margin-bottom: 1px;
	  }
	  h4 {
	    color: blue;
	    display: inline;
	  }
	  
	 
	</style>

</head>
<body>
	<jsp:include page="/FrontHeaderFooter/Header.jsp" />
	<h1></h1>

	<!-- 工作區開始 -->
	
	<div class="container">
		<div class="row justfy-content-center">
			<div class="row col-3">
				<table id="table-1">
					<p>回首頁<a href="back_select_page.jsp"><img src="<%=request.getContextPath()%>/share_pic/back1.gif" width="100	" height="30 !important" ></a></p>
					<tr>
						<td>
							某會員的所有帳單資料 - billList_for_oneMem.jsp
						</td>
					</tr>
				</table>
				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color:red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color:red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
			</div>
			<div class="row col-9" >
				<table>
					<tr>
						<td>帳單編號</td>
						<td>會員姓名</td>
						<td>員工編號</td>
						<td>繳交費用</td>
						<td>繳交日期</td>
						<td>帳單產生時間</td>
						<td>帳單繳費狀態</td>
						<td>付款方式</td>
						<td>繳費型態</td>
						
					</tr>
						<jsp:useBean id="mSvc" scope="page" class="com.goodhouse.member.model.MemService" />
						<jsp:useBean id="eleConSvc" scope="page" class="com.goodhouse.ele_contract.model.Ele_ContractService" />
						<%
							List<BillVO> billList = (List<BillVO>) request.getAttribute("billList");
							
							
							for(int i = 0 ; i < billList.size() ; i++){
								
								BillVO billVO = billList.get(i);
								
								String bill_id = billVO.getBill_id();
								String ele_con_id = billVO.getEle_con_id();
								String emp_id = billVO.getEmp_id();
								Integer bill_pay = billVO.getBill_pay();
								Date bill_date = billVO.getBill_date();
								Date bill_producetime = billVO.getBill_producetime();
								String bill_status = billVO.getBill_status();
								String bill_paymethod = billVO.getBill_paymethod();
								String bill_paymenttype = billVO.getBill_paymenttype();
						%>
							<tr>
								<td><%=bill_id%></td>
								<td><%=mSvc.getOneMem( eleConSvc.getOneEC(ele_con_id).getMem_id() ).getMem_name() %></td>
								<td><%=emp_id%></td>
								<td><%=bill_pay%></td>
								<td><%=bill_date%></td>
								<td><%=bill_producetime%></td>
								<td><%=bill_status%></td>
								<td><%=bill_paymethod%></td>
								<td><%=bill_paymenttype%></td>
								
							</tr>
						<% 
							}
						%>
				</table>
			</div>
				
		</div>
	</div>
	
	<!-- 工作區結束 -->
	
	<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS start-->
	<script src="<%=request.getContextPath()%>/bootstrap/jquery-3.3.1.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/popper.min.js"
		integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
		crossorigin="anonymous"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
	<!-- jQuery first, then Popper.js, then Bootstrap JS end-->

</body>
</html>