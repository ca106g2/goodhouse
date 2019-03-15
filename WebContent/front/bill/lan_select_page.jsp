<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String mem_name = (String)session.getAttribute("mem_name");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

</head>
<body>
<jsp:include page="/FrontHeaderFooter/Header.jsp" />
<div style="padding-top:30px"></div>
	<!-- 工作區開始 -->
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
							<b>(房東)查詢所有房租帳單</b>
							<input type="hidden" name="mem_name" value="<%=mem_name%>">
							<input type="hidden" name="action" value="billForLanListAll">
							<input type="submit" value="送出">
						</form>
					</li>

					<li>
						<form method="post" action="bill.do">
							<b>查詢單一房租帳單(輸入帳單編號)</b><br> 
							<input type="text" name="bill_id" >
							<input type="hidden" name="action" value="bill_getOne_mem">
							<input type="submit" value="送出">
						</form>
					</li>
					<li>
						<form method="post" action="bill.do">
							<b>查詢單一房租帳單(輸入房客姓名)</b><br>
							<input type="text" name="mem_name">
							<input type="hidden" name="action" value="bill_getBy_mem_name">
							<input type="submit" value="送出">							
						</form>
					</li>
					
				</ul>
		</div>
	</div>
</div>
	<!-- 工作區結束 -->
</body>
</html>