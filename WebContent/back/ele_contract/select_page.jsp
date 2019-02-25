<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	
	<div class="container-fluid">
		<div class="row justfy-content-center">
			<div class="row col-2">
				<h3>電子合約</h3>
			</div>
			<div class="row col-8" >
				<table id="table-1">
					<td>
						<h3>資料查詢:</h3>
					</td>
				</table>
					
				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color:red">請修正以下錯誤:</font>
					<ul>
					    <c:forEach var="message" items="${errorMsgs}">
							<ul style="color:red">${message}</ul>
						</c:forEach>
					</ul>
				</c:if>
				<ul>
					<li>
					<a href="listAll_ele_contract.jsp">顯示全部電子合約</a>
					</li>
					<li>
						<form method="post" action="ele_contract.do">
							<b>輸入電子合約編號</b><br>
							<input type="text" name="ele_con_id">
							<input type="hidden" name="action" value="getOne_For_Display">
							<input type="submit" value="送出">
						</form>
					</li>
					<jsp:useBean id="eleConSvc" scope="page" class="com.goodhouse.ele_contract.model.Ele_ContractService"></jsp:useBean>
					<li>
						<form method="post" action="ele_contract.do">
							<b>輸入會員姓名</b><br>
							<input type="text" name="mem_name">
							<input type="hidden" name="action" value="getOne_For_Name">
							<input type="submit" value="送出">
						</form>
					</li>
				</ul>
				
				
			</div>
			<div class="row col-2">
			</div>	
		</div>
	</div>
	
	






	<!-- 工作區結束 -->
	
	<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
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