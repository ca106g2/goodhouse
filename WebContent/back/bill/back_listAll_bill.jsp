<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.bill.model.*"%>
<%@ page import="java.util.*"%>
<%
	BillService billSvc = new BillService();
	List<BillVO> list = billSvc.getAll();
	Collections.reverse(list);
	pageContext.setAttribute("list",list);
%>

<!doctype html>
<html lang="en">
<head>

<title></title>
</head>
<body>
	<jsp:include page="/BackHeaderFooter/Header.jsp" />
	<!-- 工作區開始 -->
	
	<div class="container">
		<div class="row justfy-content-center">
			<div class="row col-12">
				<table >
					<c:if test="${not empty errorMsgs}">
						<c:forEach var="message" items="${errorMsgs}">
							<p style="color:red">${message}</p><br>
						</c:forEach>
				</c:if>
				</table>
				
			</div>
			<div class="row col-12">
				<div class="col-6">
					<form method="post" action="bill.do">
						<b>查詢帳單：輸入帳單編號</b><br> 
						<input type="text" name="bill_id" >
						<input type="hidden" name="action" value="back_getBy_bill_id">
						<input type="submit" value="送出">
					</form>
				</div>
				<div class="col-6">
					<form method="post" action="bill.do">
						<b>查詢帳單：輸入姓名</b><br>
						<input type="text" name="mem_name">
						<input type="hidden" name="action" value="back_getBy_mem_name">
						<input type="submit" value="送出">
					</form>
				</div>
				
			</div>
			<div class="row col-12" style="margin-top:10px">
				<table class="table table-hover">
					<thead>	
						<tr>
							<th scope="col">帳單編號</th>
							<th scope="col">電子合約編號</th>
							<th scope="col">繳交費用</th>
							<th scope="col">繳交日期</th>
							<th scope="col">帳單產生日期</th>
							<th scope="col">帳單繳費狀態</th>
							<th scope="col">付款方式</th>
							<th scope="col">繳費型態</th>
							<th scope="col"></th>
						</tr>
					</thead>
					<tbody>
						<%@ include file="page1.file" %>
						<c:forEach var="billVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" >
							<tr>
								<td>${billVO.bill_id}</td>
								<td>${billVO.ele_con_id}</td>
								<td>${billVO.bill_pay}</td>
								<td>${billVO.bill_date}</td>
								<td>${billVO.bill_producetime}</td>
								
								<c:forEach var="BillStatus" items="${BillStatusList}">
									<c:if test="${BillStatus.status_no eq billVO.bill_status}">
										<td>${BillStatus.status_name}</td>
									</c:if>
								</c:forEach>
								
								<td>${billVO.bill_paymethod}</td>
								
								<c:forEach var="Bill_PaymentType" items="${Bill_PaymentTypeMap}">
									<c:if test="${Bill_PaymentType.value.type_no eq  billVO.bill_paymenttype}">
										<td>${Bill_PaymentType.value.type_name}</td>
									</c:if>
								</c:forEach>
								
								<td>
									<form method="post" action="<%=request.getContextPath()%>/back/bill/bill.do">
										<input type="hidden" name="action" value="payMoneyToLan">
										<input type="hidden" name="bill_id" value="${billVO.bill_id}">
										<input type="submit" name="" value="撥款"
												style='display:${(billVO.bill_status eq "s3") ? "" : "none"}' >
									</form>
									
									<form method="post" action="bill.do">
										<input type="hidden" name="action" value="back_getBy_bill_id">
										<input type="hidden" name="bill_id" value="${billVO.bill_id}">
										<input type="submit" name="" value="查看">
									</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
				<%@ include file="page2.file" %>
		</div>
	</div>
	

	<!-- 工作區結束 -->

	<jsp:include page="/BackHeaderFooter/Footer.jsp" />
	

</body>
</html>