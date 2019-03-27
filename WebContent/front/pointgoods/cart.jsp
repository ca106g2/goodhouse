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
<div class="container">
	<div class="row">
		<div class="col-lg-12">
			<div class="card">
				<div class="card-header">
					<h4>我的最愛積分列表</h4>
				</div>
				<%if(buylist != null && (buylist.size() > 0)) {%>
					<div class="card-body p-0">
						<div class="table-responsive">
							<table class="table table-striped mb-0">
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
										PointgoodsVO pointgoodsVOcar = buylist.get(i);
								%>
								<tr>
									<td><%=pointgoodsVOcar.getGood_nam() %></td>
									<td style="width:342.5px;"><%=pointgoodsVOcar.getGood_dsc() %></td>
									<td><%=pointgoodsVOcar.getGood_pri() %></td>
									<td><%=pointgoodsVOcar.getGood_nee() %></td>
									<td><img src="<%=request.getContextPath() %>/PointgoodsServlet?good_id=<%=pointgoodsVOcar.getGood_id() %>" width="100px" height="100px"></td>
									<td>
				<!-- 						<form method="post" action="pgc.do"> -->
											<input type="button" value="移除" class="btn btn-primary cardel" id="<%=pointgoodsVOcar.getGood_id() %>">
											<input type="hidden" name="del" value="<%=pointgoodsVOcar.getGood_id() %>">
											<input type="hidden" name="removegood_id" value="<%=pointgoodsVOcar.getGood_id() %>">
				<!-- 						</form> -->
									</td>
								</tr>
								<%}%>
							</table>
						</div>
					</div>
				<%} else { %>
					<h4>尚無選購商品</h4>
				<% } %>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">繼續購物</button>
		      	<%if(buylist != null && (buylist.size() > 0)) {%>
					<form action="pgc.do" method="post" id="checkoutcar">
						<input type="hidden" name="action" value="checkout">
						<input type="submit" value="付款結帳" class="btn btn-success" >
					</form>
				<%} %>
			</div>
		</div>
		</div>
	</div>
</div>
</body>
</html>