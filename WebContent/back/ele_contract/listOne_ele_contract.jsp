<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.ele_contract.model.*" %>

<%
	Ele_ContractVO eleConVO = (Ele_ContractVO) request.getAttribute("eleConVO");
%>

<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<script src="<%=request.getContextPath()%>/file/jquery-1.12.4.min.js"></script>
<!-- Bootstrap CSS start-->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css">
<!-- Bootstrap CSS end-->


<title></title>
</head>
<body>
	<jsp:include page="/FrontHeaderFooter/Header.jsp" />
	<h1></h1>

	<!-- 工作區開始 -->
	<div class="container-fluid">
		<div class="row justfy-content-center">
			
		</div>
		<div class="row justfy-content-center">
			<div class="row col-3">
				<table id="table-1">
					<tr>
						<td>
							<h3>電子合約資料 - ListOne_ele_contract.jsp</h3>
							<h4><a href="select_page.jsp">回首頁</a></h4>
						</td>
					</tr>
				</table>
			</div>
			<div class="row col-9">
				<table>
					<tr>
						<td>電子合約編號</td>
						<td>${eleConVO.ele_con_id}</td>
					</tr>
					<tr>
						<td>合約分類編號</td>
						<td>${eleConVO.con_id}</td>
					</tr>
					<tr>
						<td>會員編號</td>
						<td>${eleConVO.mem_id}</td>
					</tr>
					<tr>
						<td>會員身分證字號</td>
						<td>${eleConVO.mem_idnumber}</td>
					</tr>
					<tr>
						<td>房東編號</td>
						<td>${eleConVO.lan_id}</td>
					</tr>
					<tr>
						<td>房東身分證字號</td>
						<td>${eleConVO.lan_idnumber}</td>
					</tr>
					<tr>
						<td>房屋編號</td>
						<td>${eleConVO.hou_id}</td>
					</tr>
					<tr>
						<td>每期租金</td>
						<td>${eleConVO.ele_rent_money}元</td>
					</tr>
					<tr>
						<td>押金</td>
						<td>${eleConVO.ele_deposit_money}元</td>
					</tr>
					<tr>
						<td>租賃期限</td>
						<td>${eleConVO.ele_rent_time}</td>
					</tr>
					<tr>
						<td>租賃起訖日</td>
						<td>${eleConVO.ele_rent_f_day}</td>
					</tr>
					<tr>
						<td>租賃結束日</td>
						<td>${eleConVO.ele_rent_l_day}</td>
					</tr>
					<tr>
						<td>簽約日期</td>
						<td>${eleConVO.ele_singdate}</td>
					</tr>
					<tr>
						<td>合約狀態</td>
						<td>${eleConVO.ele_con_status}</td>
					</tr>
					<tr>
						<td>繳費型態</td>
						<td>${eleConVO.bill_paymenttype}</td>
					</tr>
					<tr>
						<td>備註</td>
						<td>${eleConVO.ele_con_note}</td>
					</tr>	
				</table>
			</div>
		</div>
	</div>
	
	<!-- 工作區結束 -->
	
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS start-->
	<script src="<%=request.getContextPath()%>/bootstrap/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/popper.min.js"
		integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
		crossorigin="anonymous"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
	<!-- jQuery first, then Popper.js, then Bootstrap JS end-->

</body>
</html>