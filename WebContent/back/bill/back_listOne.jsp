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
	<jsp:include page="/BackHeaderFooter/Header.jsp" />

	<!-- 工作區開始 -->
	<div class="container">
		<div class="row justfy-content-center">
			<div class="row col-12">
				<table id="table-1">
					<p>回帳單列表<a href="<%=request.getContextPath()%>/back/bill/back_listAll_bill.jsp">
					<img src="<%=request.getContextPath()%>/share_pic/back1.gif" width="100	" height="30 !important" ></a></p>
				</table>
			</div>
			
			<div class="row col-12">
				<table>
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
	<jsp:include page="/BackHeaderFooter/Footer.jsp" />
</body>
</html>