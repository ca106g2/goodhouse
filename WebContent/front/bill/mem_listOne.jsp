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
	<table>
		<tr>
			<th scope="col">帳單編號</th>
			<td>${billVO.bill_id}</td>
		</tr>
		<jsp:useBean id="eleConSvc" scope="page"
			class="com.goodhouse.ele_contract.model.Ele_ContractService" />
		<jsp:useBean id="memSvc" scope="page"
			class="com.goodhouse.member.model.MemService" />
		<tr>
			<th scope="col">付款人姓名</th>
			<td>${memSvc.getOneMem(eleConSvc.getOneEC(billVO.ele_con_id).mem_id).mem_name}</td>
		</tr>
		<tr>
			<th scope="col">繳交費用</th>
			<td>${billVO.bill_pay}</td>
		</tr>
		<tr>
			<th scope="col">繳交日期</th>
			<td>${billVO.bill_date}</td>
		</tr>
		<tr>
			<th scope="col">帳單產生時間</th>
			<td>${billVO.bill_producetime}</td>
		</tr>
		<tr>
			<th>帳單繳費狀態</th>
			<c:forEach var="billStatus" items="${BillStatusList}">
				<c:if test="${billStatus.status_no eq billVO.bill_status}">

					<c:choose>
						<c:when
							test="${billStatus.status_name eq '待繳款'  or billStatus.status_name eq '待確認待繳款' }">
							<td id="billStatus" style="color: #FF0000">${billStatus.status_name}</td>
						</c:when>

						<c:when
							test="${billStatus.status_name eq '延期待繳款'  or billStatus.status_name eq '逾期未繳款'  }">
							<td id="billStatus" style="color: #0000FF">${billStatus.status_name}</td>
						</c:when>

						<c:when
							test="${billStatus.status_name eq '已繳款待撥款'  or billStatus.status_name eq '已繳款已撥款'  }">
							<td id="billStatus" style="color: #AEB6BF">${billStatus.status_name}</td>
						</c:when>
					</c:choose>

				</c:if>
			</c:forEach>
		</tr>
		<tr>
			<th scope="col">付款方式</th>
			<td>${billVO.bill_paymethod}</td>
		</tr>
		<tr>
			<th scope="col">繳費型態</th>
			<c:forEach var="bill_PaymentType" items="${Bill_PaymentTypeMap}">
				<c:if
					test="${bill_PaymentType.value.type_no eq billVO.bill_paymenttype}">
					<td>${bill_PaymentType.value.type_name}</td>
				</c:if>
			</c:forEach>
		</tr>

	</table>






	<!-- 工作區結束 -->

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS start-->
	<script
		src="<%=request.getContextPath()%>/bootstrap/jquery-3.3.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/popper.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
	<!-- jQuery first, then Popper.js, then Bootstrap JS end-->

</body>
</html>