<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.goodhouse.pointgoods.model.*" %>
<%
	String good_id = request.getParameter("good_id");
	PointgoodsService pointgoodsSvc = new PointgoodsService();
	PointgoodsVO pointgoodsVO = pointgoodsSvc.getOnePointgoods(good_id);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List One Pointgoods</title>
</head>
<body>
<jsp:include page="/FrontHeaderFooter/Header.jsp" />

<div class="container">
	<div class="row">
		<div class="col-4">
			<jsp:include page="/FrontHeaderFooter/LeftList.jsp" />
		</div>
		<div class="col-8">
			<div class="row">
				<div class="col-5">
					<div class="item">
						<img src="<%=request.getContextPath() %>/PointgoodsServlet?good_id=<%=pointgoodsVO.getGood_id() %>" width="400px" height="400px" class="img-thumbnail">
					</div>
				</div>
				<div class="col-7">
					<div class="item">
						<p class="font-weight-bold">
							積分商品名稱 : <%=pointgoodsVO.getGood_nam() %>
		<%-- 					${pointgoodsVO.good_nam} --%>
						</p>
						<p class="font-weight-bold">
							積分商品描述 : <%=pointgoodsVO.getGood_dsc() %>
		<%-- 					${pointgoodsVO.good_dsc} --%>
						</p>
						<p class="font-weight-bold">
							積分商品存量 : <%=pointgoodsVO.getGood_amo() %>
		<%-- 					${pointgoodsVO.good_amo} --%>
						</p>
						<p class="font-weight-bold">
							積分商品價格 : <%=pointgoodsVO.getGood_pri() %>
		<%-- 					${pointgoodsVO.good_pri} --%>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>


<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
</body>
</html>