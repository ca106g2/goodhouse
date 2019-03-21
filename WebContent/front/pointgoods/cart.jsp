<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.* , com.goodhouse.pointgoods.model.*" %>
<%
	List<PointgoodsVO> buylist = (List<PointgoodsVO>) session.getAttribute("shoppingcart");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart</title>
</head>
<body>
<jsp:include page="/FrontHeaderFooter/Header.jsp" />
<div class="container">
	<div class="row">
		<div class="col-4">
			<jsp:include page="/FrontHeaderFooter/LeftList.jsp" />
			<%if(buylist != null && (buylist.size() > 0)) {%>
				<form action="pgc.do" method="post" style="margin-left: 120px;margin-top: 30px;">
					<input type="hidden" name="action" value="checkout">
					<input type="submit" value="付款結帳" class="btn btn-success" >
				</form>
			<%} %>
			<a href="listAllPointgoods.jsp" class="btn btn-success" style="margin-left: 120px;margin-top: 30px;">繼續購物</a>
		</div>
		<div class="col-8">
		<%if(buylist != null && (buylist.size() > 0)) {%>
			<table border="1">
				<tr>
					<th>積分商品名稱</th>
					<th>積分商品描述</th>
					<th>積分商品價格</th>
					<th>數量</th>
					<th>圖片</th>
					<th>刪除</th>
				</tr>
				<%
					for(int i = 0; i < buylist.size(); i++) {
						PointgoodsVO pointgoodsVO = buylist.get(i);
				%>
				<tr>
					<td><%=pointgoodsVO.getGood_nam() %></td>
					<td><%=pointgoodsVO.getGood_dsc() %></td>
					<td><%=pointgoodsVO.getGood_pri() %></td>
					<td><%=pointgoodsVO.getGood_nee() %></td>
					<td><img src="<%=request.getContextPath() %>/PointgoodsServlet?good_id=<%=pointgoodsVO.getGood_id() %>" width="100px" height="100px"></td>
					<td>
						<form method="post" action="pgc.do">
							<input type="hidden" name="del" value="<%=i%>">
							<input type="hidden" name="action" value="deletecart">
							<input type="submit" value="刪除">
						</form>
					</td>
				</tr>
				<%}%>
			</table>
		<%} else { %>
			尚無選購商品
		<% } %>
		</div>
		
<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
</body>
</html>