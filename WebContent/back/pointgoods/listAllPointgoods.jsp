<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>
<%@ page import="com.goodhouse.pointgoods.model.*" %>

<%
	PointgoodsService pointgoodsSvc = new PointgoodsService();
	List<PointgoodsVO> list = pointgoodsSvc.getAll();
	pageContext.setAttribute("list",list);
%>
<!DOCTYPE html>
<html>
<head>
<title>所有積分商品</title>
</head>
<body>
<jsp:include page="/BackHeaderFooter/Header.jsp" />
<div class="container">
	<div class="row">
		<div class="col-lg-12">
			<div class="card">
				<div class="card-header">
					<h4>積分商品管理</h4>
				</div>
				<div class="card-body p-0">
					<div class="table-responsive">
						<table class="table table-striped mb-0">
							<tr>
								<td>積分商品編號</td>
								<td>積分商品名稱</td>
								<td>積分商品描述</td>
								<td>積分商品存量</td>
								<td>積分商品價格</td>
								<td>積分商品狀態</td>
								<td>積分商品圖片</td>
								<td>修改</td>
								<td>刪除</td>
							</tr>
							<%@ include file="page1.file" %>
							<c:forEach var="pointgoodsVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
							<tr>
								<td>${pointgoodsVO.good_id}</td>
								<td>${pointgoodsVO.good_nam}</td>
								<td>${pointgoodsVO.good_dsc}</td>
								<td>${pointgoodsVO.good_amo}</td>
								<td>${pointgoodsVO.good_pri}</td>
								<td>
									<c:if test="${(pointgoodsVO.good_sta == 'P001')}">上架</c:if>
									<c:if test="${(pointgoodsVO.good_sta == 'P002')}">下架</c:if>
								</td>
								<td><img src="<%=request.getContextPath() %>/PointgoodsServlet?good_id=${pointgoodsVO.good_id}" width="100" height="100"></td>
								<td>
									<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back/pointgoods/pg.do" style="margin-bottom: 0px;">
								    	<input type="submit" value="修改">
								    	<input type="hidden" name="good_id"  value="${pointgoodsVO.good_id}">
								    	<input type="hidden" name="requestURL" value="<%=request.getServletPath() %>">
								    	<input type="hidden" name="whichPage" value="<%=whichPage %>">
								    	<input type="hidden" name="action"	value="getOne_For_Update">
								    </FORM>
								</td>
								<td>
									<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back/pointgoods/pg.do" style="margin-bottom: 0px;">
										<input type="submit" value="刪除">
										<input type="hidden" name="good_id"  value="${pointgoodsVO.good_id}">
										<input type="hidden" name="requestURL" value="<%=request.getServletPath() %>">
								    	<input type="hidden" name="whichPage" value="<%=whichPage %>">
										<input type="hidden" name="action" value="delete">
									</FORM>
								</td>
							</tr>
							</c:forEach>
						</table>
						<%@ include file="page2.file" %>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- <div class="container"> -->
<!-- 	<div class="row"> -->
<!-- 		<div class="col-12"> -->
<!-- 			<table border="1"> -->
<!-- 				<tr> -->
<!-- 					<td>積分商品編號</td> -->
<!-- 					<td>積分商品名稱</td> -->
<!-- 					<td>積分商品描述</td> -->
<!-- 					<td>積分商品存量</td> -->
<!-- 					<td>積分商品價格</td> -->
<!-- 					<td>積分商品狀態</td> -->
<!-- 					<td>積分商品圖片</td> -->
<!-- 					<td>修改</td> -->
<!-- 					<td>刪除</td> -->
<!-- 				</tr> -->
<%-- 				<%@ include file="page1.file" %> --%>
<%-- 				<c:forEach var="pointgoodsVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"> --%>
<!-- 				<tr> -->
<%-- 					<td>${pointgoodsVO.good_id}</td> --%>
<%-- 					<td>${pointgoodsVO.good_nam}</td> --%>
<%-- 					<td>${pointgoodsVO.good_dsc}</td> --%>
<%-- 					<td>${pointgoodsVO.good_amo}</td> --%>
<%-- 					<td>${pointgoodsVO.good_pri}</td> --%>
<!-- 					<td> -->
<%-- 						<c:if test="${(pointgoodsVO.good_sta == 'P001')}">上架</c:if> --%>
<%-- 						<c:if test="${(pointgoodsVO.good_sta == 'P002')}">下架</c:if> --%>
<!-- 					</td> -->
<%-- 					<td><img src="<%=request.getContextPath() %>/PointgoodsServlet?good_id=${pointgoodsVO.good_id}" width="100" height="100"></td> --%>
<!-- 					<td> -->
<%-- 						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back/pointgoods/pg.do" style="margin-bottom: 0px;"> --%>
<!-- 					    	<input type="submit" value="修改"> -->
<%-- 					    	<input type="hidden" name="good_id"  value="${pointgoodsVO.good_id}"> --%>
<%-- 					    	<input type="hidden" name="requestURL" value="<%=request.getServletPath() %>"> --%>
<%-- 					    	<input type="hidden" name="whichPage" value="<%=whichPage %>"> --%>
<!-- 					    	<input type="hidden" name="action"	value="getOne_For_Update"> -->
<!-- 					    </FORM> -->
<!-- 					</td> -->
<!-- 					<td> -->
<%-- 						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back/pointgoods/pg.do" style="margin-bottom: 0px;"> --%>
<!-- 							<input type="submit" value="刪除"> -->
<%-- 							<input type="hidden" name="good_id"  value="${pointgoodsVO.good_id}"> --%>
<%-- 							<input type="hidden" name="requestURL" value="<%=request.getServletPath() %>"> --%>
<%-- 					    	<input type="hidden" name="whichPage" value="<%=whichPage %>"> --%>
<!-- 							<input type="hidden" name="action" value="delete"> -->
<!-- 						</FORM> -->
<!-- 					</td> -->
<!-- 				</tr> -->
<%-- 				</c:forEach> --%>
<!-- 			</table> -->
<%-- 			<%@ include file="page2.file" %> --%>
<!-- 		</div> -->
<!-- 	</div> -->
<!-- </div> -->
<jsp:include page="/BackHeaderFooter/Footer.jsp" />
</body>
</html>