<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.bill.model.*"%>
<%@ page import="java.util.*"%>
<%
	BillService billSvc = new BillService();
	List<BillVO> list = billSvc.getAll();
	pageContext.setAttribute("list",list);
%>

<!doctype html>
<html lang="en">
<head>

<title></title>
</head>
<body>
	<jsp:include page="/FrontHeaderFooter/Header.jsp" />
	<h1></h1>

	<!-- 工作區開始 -->
	
	<div class="container">
		<div class="row justfy-content-center">
			<div class="row col-2">
				<table id="table-1">
					<p>回首頁<a href="back_select_page.jsp"><img src="<%=request.getContextPath()%>/share_pic/back1.gif" width="100	" height="30 !important" ></a></p>
					<tr>
						<td>
							所有帳單資料 - back_listAll_bill.jsp
						</td>
					</tr>
					<c:if test="${not empty errorMsgs}">
					<font style="color:red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color:red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
				</table>
			</div>
			<div class="row col-10" >
				<table>
					<tr>
						<td>帳單編號</td>
						<td>電子合約編號</td>
						<td>繳交費用</td>
						<td>繳交日期</td>
						<td>帳單產生日期</td>
						<td>帳單繳費狀態</td>
						<td>付款方式</td>
						<td>繳費型態</td>
					</tr>
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
								<form method="post" action="bill.do">
									<input type="hidden" name="action" value="payMoneyToLan">
									<input type="hidden" name="bill_id" value="${billVO.bill_id}">
									<input type="submit" name="" value="撥款"
											style='display:${(billVO.bill_status eq "s3") ? "" : "none"}' id="payMoneyToLan">
								</form>
							</td>
						</tr>
					</c:forEach>
				</table>
				<%@ include file="page2.file" %>
			</div>
		</div>
	</div>
	






	<!-- 工作區結束 -->

	<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
	

</body>
</html>