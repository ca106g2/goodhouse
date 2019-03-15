<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.ele_contract.model.*"%>
<%
	String mem_name = (String)session.getAttribute("mem_name");
%>

<!doctype html>
<html lang="en">
<head>

<title></title>
<style>
</style>

</head>
<body>
	<jsp:include page="/FrontHeaderFooter/Header.jsp" />
	<!-- 工作區開始 -->
	<div class="container">
		<div class="row justfy-content-center">
			<div class="row col-12">
				<div>
					<%-- 錯誤表列 --%>
					<c:if test="${not empty errorMsgs}">
						<font style="color: red"></font>
							<c:forEach var="message" items="${errorMsgs}">
								<p style="color: red">${message}</p>
							</c:forEach>
					</c:if>
				</div>
				<div style="width: 50rem">
					<div class="card text-center" style="width: 50rem;">
						<div class="card-body">
							<form method="post" action="ele_contract.do">
								<input type="hidden" name="action" value="lan_listAll">
								<input type="submit" value="查看所有合約" class="btn btn-outline-success btn-lg btn-block">
							</form>
						</div>
					</div>
					<div class="card text-center" style="width: 50rem;">
						<div class="card-body">
							<form method="post" action="ele_contract.do">
								<b>輸入電子合約編號</b>
								<input type="text" name="ele_con_id" class="btn btn-light">
								<input type="hidden" name="action" value="getOne_front">
								<input type="submit" value="送出" class="btn btn-outline-success">
							</form>
						</div>
					</div>
					<div class="card text-center" style="width: 50rem;">
						<div class="card-body">
							<form method="post" action="ele_contract.do">
								<b>輸入姓名</b>
								<input type="text" name="mem_name" class="btn btn-light">
								<input type="hidden" name="action" value="lanGetMemEle_ContractByName">
								<input type="submit" value="送出" class="btn btn-outline-success">
							</form>
						</div>
					</div>
					<div class="card text-center" style="width: 50rem;">
						<div class="card-body">
							<form method="post" action="apply_conturct.do">
								<b>合約處理列表</b>
								<input type="hidden" name="action" value="lanApply_conturctListAll">
								<input type="submit" value="送出" class="btn btn-outline-success">
							</form>
						</div>
					</div>
					<div class="card text-center" style="width: 50rem;">
						<div class="card-body">
							<form method="post" action="<%=request.getContextPath()%>/front/ele_contract/select_contract.jsp">
								<input type="submit" value="新增電子合約" class="btn btn-outline-success btn-lg btn-block">
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>



	<!-- 工作區結束 -->

	<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS start-->
	<script
		src="<%=request.getContextPath()%>/bootstrap/jquery-3.3.1.slim.min.js"
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