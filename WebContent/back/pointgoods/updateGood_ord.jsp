<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>
<%@ page import="com.goodhouse.good_ord.model.*, com.goodhouse.good_ord_list.model.*" %>
<%
	Good_ordVO good_ordVO = (Good_ordVO)request.getAttribute("good_ordVO");
	Good_ord_listService Good_ordSvc = new Good_ord_listService();
	List<Good_ord_listVO> list = Good_ordSvc.getPart(good_ordVO.getGood_ord_id());
	pageContext.setAttribute("good_ordVO", good_ordVO);
	pageContext.setAttribute("list", list);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改狀態</title>
</head>
<body>
<jsp:include page="/BackHeaderFooter/Header.jsp" />
<div class="container">
	<div class="row">
		<form action="go.do" method="post">
			<table>
				<thead>
					<th style="width:100px">收件人 :</th>
					<th style="width:100px">${good_ordVO.good_ord_nam}</th>
					<th style="width:100px">訂單狀態 :</th>
					<th style="width:100px">
						<select name="good_ord_sta">
							<option value="GO001" ${(good_ordVO.good_ord_sta eq 'GO001')?'selected':''}>備貨中</option>
							<option value="GO002" ${(good_ordVO.good_ord_sta eq 'GO002')?'selected':''}>出貨</option>
						</select>
					</th>
					<th style="width:100px">積分總額 :</th>
					<th style="width:100px">$${good_ordVO.good_ord_tot}</th>
				</thead>
			</table>
			<table border="1">
				<tr>
					<td>商品名稱</td>
					<td>商品描述</td>
					<td>商品價格</td>
					<td>商品數量</td>
				</tr>
				<jsp:useBean id="pointgoodsSvc" scope="page" class="com.goodhouse.pointgoods.model.PointgoodsService"/>
				<c:forEach var="good_ord_listVO" items="${list}">
					<tr>
						<td>${pointgoodsSvc.getOnePointgoods(good_ord_listVO.good_id).good_nam}</td>
						<td>${pointgoodsSvc.getOnePointgoods(good_ord_listVO.good_id).good_dsc}</td>
						<td>${pointgoodsSvc.getOnePointgoods(good_ord_listVO.good_id).good_pri}</td>
						<td>${good_ord_listVO.good_ord_amo}</td>
					</tr>
				</c:forEach>
			</table>
			<input type="hidden" name="action" value="update">
			<input type="hidden" name="good_ord_id" value="${good_ordVO.good_ord_id}">
			<input type="hidden" name="mem_id" value="${good_ordVO.mem_id}">
			<input type="hidden" name="good_ord_dat" value="${good_ordVO.good_ord_dat}">
			<input type="hidden" name="good_ord_nam" value="${good_ordVO.good_ord_nam}">
			<input type="hidden" name="good_ord_tot" value="${good_ordVO.good_ord_tot}">
			<input type="hidden" name="good_ord_add" value="${good_ordVO.good_ord_add}">
			<input type="submit" value="送出修改" class="btn btn-primary" style="float:right">
		</form>
	</div>
</div>


<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
</body>
</html>