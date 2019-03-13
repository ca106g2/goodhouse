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
table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 3px ridge Gray;
	height: 80px;
	text-align: center;
}

table#table-1 h4 {
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
				<h4>房東電子合約管理</h4>
			</div>
			<div class="row col-6">

				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
				<ul>
					<li>
						<form method="post" action="ele_contract.do">
							<b>(房東自己的)查看所有合約</b>
							<input type="hidden" name="action" value="lan_listAll">
							<input type="submit" value="送出">
						</form>
					</li>

					<li>
						<form method="post" action="ele_contract.do">
							<b>輸入電子合約編號</b><br> 
							<input type="text" name="ele_con_id" >
							<input type="hidden" name="action" value="getOne_front">
							<input type="submit" value="送出">
						</form>
					</li>
					<li>
						<form method="post" action="ele_contract.do">
							<b>輸入姓名</b><br>
							<input type="text" name="mem_name" >
							<input type="hidden" name="action" value="lanGetMemEle_ContractByName">
							<input type="submit" value="送出">
						</form>
					</li>
					<li> <a href="select_contract.jsp">新增電子合約</a> </li>
					<li>
						<form method="post" action="apply_conturct.do">
							<b>合約處理列表</b>
							<input type="hidden" name="action" value="lanApply_conturctListAll">
							<input type="submit" value="送出">
						</form>
					</li>
				</ul>
			</div>
			<div class="row col-3"></div>
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