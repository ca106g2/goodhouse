<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>
<%@ page import="com.goodhouse.good_ord.model.*" %>
<%
	Good_ordService good_ordSvc = new Good_ordService();
	List<Good_ordVO> list = good_ordSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>訂單管理</title>
</head>
<body>
<jsp:include page="/BackHeaderFooter/Header.jsp" />
<div class="container">
	<div class="row">
		<div class="col-4">
			<jsp:include page="/BackHeaderFooter/LeftList.jsp" />
		</div>
		<div class="col-8">
		<table border="1">
			<tr>
				<td>訂單編號</td>
				<td>收件人</td>
				<td>積分價格</td>
				<td>地址</td>
				<td>訂單日期</td>
				<td>訂單狀態</td>
				<td>修改</td>
			</tr>
			<%@ include file="page1.file" %>
			<c:forEach var="good_ordVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			<tr>
				<td>${good_ordVO.good_ord_id}</td>
				<td>${good_ordVO.good_ord_nam}</td>
				<td>${good_ordVO.good_ord_tot}</td>
				<td>${good_ordVO.good_ord_add}</td>
				<td>${good_ordVO.good_ord_dat}</td>
				<td>
					<c:if test="${good_ordVO.good_ord_sta eq 'GO001'}">
						<font color="red"><b>備貨中</b></font>
					</c:if>
					<c:if test="${good_ordVO.good_ord_sta eq 'GO002'}">
						<font color="green"><b>已出貨</b></font>
					</c:if>
				</td>
				<td>
					<form action="go.do" method="post">
						<input type="hidden" name="action" value="getOne_For_Update">
						<input type="hidden" name="good_ord_id" value="${good_ordVO.good_ord_id}">
						<input type="submit" value="修改狀態">
					</form>
				</td>
			</tr>
			</c:forEach>
		</table>
		<%@ include file="page2.file" %>
		</div>
	</div>
</div>
<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
</body>
</html>