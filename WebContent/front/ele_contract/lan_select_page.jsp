<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.ele_contract.model.*"%>

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
		
				<div class="row col-12">
					<%-- 錯誤表列 --%>
					<c:if test="${not empty errorMsgs}">
						<font style="color: red"></font>
							<c:forEach var="message" items="${errorMsgs}">
								<p style="color: red">${message}</p>
							</c:forEach>
					</c:if>
				</div>
				
		<div clas="row col-12">
		
			<div class="col-5">
				
				<div class="card" style="width: 20rem;">
					<img src="..." class="card-img-top" alt="...">
					<div class="card-body">
					<h5 class="card-title">查詢合約</h5>
						    
						<form method="post" action="ele_contract.do">
							<b>輸入電子合約編號</b>
							<input type="text" name="ele_con_id" class="btn btn-light">
							<input type="hidden" name="action" value="getOne_front">
							<input type="submit" value="送出" class="btn btn-outline-success">
						</form>
							
						<form method="post" action="ele_contract.do">
							<b>輸入姓名</b>
							<input type="text" name="mem_name" class="btn btn-light">
							<input type="hidden" name="action" value="lanGetMemEle_ContractByName">
							<input type="submit" value="送出" class="btn btn-outline-success">
						</form>
							
					  </div>
				</div>
				
				<div class="card" style="width: 20rem;">
					<img src="..." class="card-img-top" alt="...">
					<div class="card-body">
						<h5 class="card-title">新增電子合約</h5>
						<form method="post" action="<%=request.getContextPath()%>/front/ele_contract/select_contract.jsp">
							<input type="submit" value="新增電子合約" class="btn btn-outline-success ">
						</form>
					</div>
				</div>
				
			</div>
			
			<div class="col-7">
				
				<div class="card" style="width: 20rem;">
					<img src="..." class="card-img-top" alt="...">
				  	<div class="card-body">
				    	<h5 class="card-title">查看所有合約</h5>
				    	<form method="post" action="<%=request.getContextPath()%>/front/ele_contract/lan_listAll_ele_contract.jsp">
							<input type="hidden" name="action" value="lan_listAll">
							<input type="submit" value="查看所有合約" class="btn btn-outline-success ">
						</form>
				  	</div>
				</div>
				
				<div class="card" style="width: 20rem;">
				  	<img src="..." class="card-img-top" alt="...">
				  	<div class="card-body">
				  		<h5 class="card-title">合約處理</h5>
					    <form method="post" action="<%=request.getContextPath()%>/front/ele_contract/lanListAll_Apply_conturct.jsp">
							<input type="submit" value="合約處理列表" class="btn btn-outline-success">
						</form>
				  	</div>
				</div>
				
				<div class="card" style="width: 20rem;">
				  	<img src="..." class="card-img-top" alt="...">
				  	<div class="card-body">
				  		<h5 class="card-title">帳單列表</h5>
					    <form method="post" action="<%=request.getContextPath()%>/front/bill/lan_listAll_bill.jsp">
							<input type="submit" value="帳單列表" class="btn btn-outline-success ">
						</form>
				  	</div>
				</div>
				
			</div>
							
		</div>
				
	</div>



	<!-- 工作區結束 -->

	<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
	<!-- Optional JavaScript -->
	

</body>
</html>