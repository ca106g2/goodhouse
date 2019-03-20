<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.* , com.goodhouse.pointgoods.model.*" %>
<%@ page import="com.goodhouse.member.model.*" %>
<%
	List<PointgoodsVO> buylist = (List<PointgoodsVO>) session.getAttribute("shoppingcart");
	pageContext.setAttribute("buylist",buylist);
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	String amount = (String) session.getAttribute("amount");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Checkout</title>
</head>
<body>
<jsp:include page="/FrontHeaderFooter/Header.jsp" />
<div class="container">
	<div class="row">
		<div class="col-4">
			<jsp:include page="/FrontHeaderFooter/LeftList.jsp" />
			<a href="listAllPointgoods.jsp" class="btn btn-success" style="margin-left: 120px;margin-top: 30px;">繼續購物</a>
		</div>
		<div class="col-8">
		<%if(buylist != null && (buylist.size() > 0)) {%>
		<form action="pgc.do" method="post">
			<table border="1">
				<tr>
					<th>積分商品名稱</th>
					<th>積分商品描述</th>
					<th>積分商品價格</th>
					<th>數量</th>
					<th>圖片</th>
				</tr>
				<c:forEach var="pointgoodsVO" items="${buylist}">
				
				<tr>
					<td>${pointgoodsVO.good_nam}</td>
					<td>${pointgoodsVO.good_dsc}</td>
					<td>${pointgoodsVO.good_pri}</td>
					<td>${pointgoodsVO.good_nee}</td>
					<td><img src="<%=request.getContextPath() %>/PointgoodsServlet?good_id=${pointgoodsVO.good_id}"width="100px" height="100px"></td>
				</tr>
				
				</c:forEach>
				<tr>
					<td>積分餘額 : </td>
					<td><%=memVO.getGood_total() %></td>
					<td><div align="center"><font color="red"><b>總積分 : </b></font></div></td>
					<td><font color="red"><b>$<%=amount %></b></font></td>
					<td>
<!-- 						<a href="order.jsp" class="btn btn-success">結帳</a> -->
						<input type="hidden" name="amo" value="<%=amount %>">
						<input type="hidden" name="tot" value="<%=memVO.getGood_total()%>">
						<input type="hidden" name="action" value="ordercheck">
						<input type="submit" value="結帳" class="btn btn-success">
					</td>
				</tr>
				<tr>
				<td></td><td></td><td></td><td></td>
				<td><font color="red"><b>${errorMsgs.overage}</b></font></td></tr>
			</table>
		</form>
		<%} %>
		</div>



<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
</body>
</html>