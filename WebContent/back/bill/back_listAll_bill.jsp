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
<!-- Required meta tags -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<script src="<%=request.getContextPath()%>/File/jquery-1.12.4.min.js"></script>
<!-- Bootstrap CSS start-->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/File/all.css"
	integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr"
	crossorigin="anonymous">
<!-- Bootstrap CSS end-->
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
						<td>員工編號</td>
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
							<td>${billVO.emp_id}</td>
							<td>${billVO.bill_pay}</td>
							<td>${billVO.bill_date}</td>
							<td>${billVO.bill_producetime}</td>
							<td>${billVO.bill_status}</td>
							<td>${billVO.bill_paymethod}</td>
							<td>${billVO.bill_paymenttype}</td>
						</tr>
					</c:forEach>
				</table>
				<%@ include file="page2.file" %>
			</div>
		</div>
	</div>
	






	<!-- 工作區結束 -->

	<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS start-->
	<script
		src="<%=request.getContextPath()%>/bootstrap/jquery-3.3.1s.min.js"
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