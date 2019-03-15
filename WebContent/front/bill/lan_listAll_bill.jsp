<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.bill.model.*"%>
<%@ page import="java.util.*"%>

<%
	BillService billSvc = new BillService();
	List<BillVO> list = (List<BillVO>) session.getAttribute("billVOList");
	pageContext.setAttribute("list",list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

</head>
<body>
	<jsp:include page="/FrontHeaderFooter/Header.jsp" />
	<div style="padding-top:30px"></div>
	<div class="container">
		<div class="row ">
			<div class="row col-2">
				<p>回首頁<a href="lan_select_page.jsp"><img src="<%=request.getContextPath()%>/share_pic/back1.gif" width="100	" height="30 !important" ></a></p><br>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color:red">${message}</li>
					</c:forEach>
				</ul>
				<div class="row col-10">
					<table>
						<tr>
							<th>帳單編號</th>
							<th>電子合約編號</th>
							<th>繳交費用</th>
							<th>繳交日期</th>
							<th>帳單產生日期</th>
							<th>帳單繳費狀態</th>
							<th>付款方式</th>
							<th>繳費型態</th>
						</tr>	
						<%@ include file="page1.file" %>
						<c:forEach var="billVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" >
						<tr>
							<td>${billVO.bill_id}</td>
							<td>${billVO.ele_con_id}</td>
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
	</div>
</body>
</html>