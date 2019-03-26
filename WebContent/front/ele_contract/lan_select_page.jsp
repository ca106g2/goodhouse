<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.ele_contract.model.*"%>

<jsp:useBean id="lanSvc" scope="page" class="com.goodhouse.landlord.model.LanService"/>


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
								<p style="color: red">${message}</p><br>
							</c:forEach>
					</c:if>
				</div>
				
				<div class="card-deck">
				
				  <div class="card text-center ">
				    <img src="<%=request.getContextPath()%>/images/eleContract.png" class="card-img-top" alt="">
				    <div class="card-body">
						<form method="post" action="<%=request.getContextPath()%>/front/ele_contract/lan_listAll_ele_contract.jsp">
							<input type="hidden" name="action" value="lan_listAll">
							<input type="submit" value="電子合約" class="btn btn-outline-success btn-lg" style="font-weight:bold">
						</form>
				    </div>
				  </div>
				  
				  <div class="card text-center">
				    <img src="<%=request.getContextPath()%>/images/applyConturct.png" class="card-img-top" alt="...">
				    <div class="card-body">
						<form method="post" action="<%=request.getContextPath()%>/front/ele_contract/lanListAll_Apply_conturct.jsp">
							<input type="submit" value="合約處理" class="btn btn-outline-success btn-lg" style="font-weight:bold">
						</form>
				    </div>
				  </div>
				   
				  <div class="card text-center">
				    <img src="<%=request.getContextPath()%>/images/bill.png" class="card-img-top" alt="...">
				    <div class="card-body">
						<form method="post" action="<%=request.getContextPath()%>/front/bill/lan_listAll_bill.jsp">
							<input type="submit" value="房租帳單" class="btn btn-outline-success btn-lg" style="font-weight:bold">
						</form>
				    </div>
				  </div>
				  
				</div>
		
	</div>



	<!-- 工作區結束 -->

	<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
	<!-- Optional JavaScript -->
	

</body>
</html>