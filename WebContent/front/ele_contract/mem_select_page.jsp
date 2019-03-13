<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.ele_contract.model.*"%>

<!doctype html>
<html lang="en">
<head>

</head>
<body>
	<jsp:include page="/FrontHeaderFooter/Header.jsp" />
	<div style="margin-top:30px"></div>

	<!-- 工作區開始 -->

	<div class="container-fluid">
		<div class="row justfy-content-center">
			<div class="row col-4"></div>
			<div class="row col-8">
				<h4>房客電子合約管理</h4><br>
				<form method="post" action="ele_contract.do">
					<input type="hidden" name="action" value="front_getMemEle_Contract">
					<input type="submit" value="我的合約列表">
				</form>
				
				
				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
			</div>
		</div>
	</div>



	<!-- 工作區結束 -->

	<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
	

</body>
</html>