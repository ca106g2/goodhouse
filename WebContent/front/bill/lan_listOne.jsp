<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.bill.model.*"%> 

<%
	BillVO billVO = (BillVO) request.getAttribute("billVO");
%>

<!doctype html>
<html lang="en">
<head>

</head>
<body>
<%-- 	<jsp:include page="/FrontHeaderFooter/Header.jsp" /> --%>

	<!-- 工作區開始 -->
	<div class="container">
		<div class="row justfy-content-center">
			<div class="row col-12">
				<table >
					<tr>
						<td>帳單編號</td>
						<td>${billVO.bill_id}</td>
					</tr>
					<jsp:useBean id="eleConSvc" scope="page" class="com.goodhouse.ele_contract.model.Ele_ContractService"/>
					<jsp:useBean id="memSvc" scope="page" class="com.goodhouse.member.model.MemService"/>
					<tr>
						<td>付款人姓名</td>
						<td>${memSvc.getOneMem(eleConSvc.getOneEC(billVO.ele_con_id).mem_id).mem_name}</td>
					</tr>
					<tr>
						<td>繳交費用</td>
						<td>${billVO.bill_pay}</td>
					</tr>
					<tr>
						<td>繳交日期</td>
						<td>${billVO.bill_date}</td>
					</tr>
					<tr>
						<td>帳單產生時間</td>
						<td>${billVO.bill_producetime}</td>
					</tr>
					<tr>
						<td>帳單繳費狀態</td>
						<td>${billVO.bill_status}</td>
					</tr>
					<tr>
						<td>付款方式</td>
						<td>${billVO.bill_paymethod}</td>
					</tr>
					<tr>
						<td>繳費型態</td>
						<td>${billVO.bill_paymenttype}</td>
					</tr>
					
				</table>
			</div>
		</div>
	</div>






	<!-- 工作區結束 -->

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS start-->
	<script
		src="<%=request.getContextPath()%>/bootstrap/jquery-3.3.1.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/popper.min.js"
		integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
		crossorigin="anonymous"></script>
	<script
		src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
	<!-- jQuery first, then Popper.js, then Bootstrap JS end-->

</body>
</html>