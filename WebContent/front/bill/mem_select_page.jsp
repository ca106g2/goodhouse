<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String mem_name = (String)session.getAttribute("mem_name");
%>

<!doctype html>
<html lang="en">
<head>


</head>
<body>
	<jsp:include page="/FrontHeaderFooter/Header.jsp" />

	<!-- 工作區開始 -->
	<div style="padding-top:30px"></div>
	<div class="container">
		<div class="row justfy-content-center">
			<div class="row col-12">
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
						<form method="post" action="bill.do">
							<b>(房客)我的帳單列表</b>
							<input type="hidden" name="action" value="billForMemListAll">
							<input type="submit" value="送出">
						</form>
					</li>
					<li>
						<form method="post" action="bill.do">
							<b>(房客)要繳交房租</b>
							<input type="hidden" name="action" value="billForMemListAll">
							<input type="submit" value="送出">
						</form>
					</li>
				</ul>
			</div>
		</div>
	</div>


	<!-- 工作區結束 -->

<jsp:include page="/FrontHeaderFooter/Footer.jsp" />

</body>
</html>