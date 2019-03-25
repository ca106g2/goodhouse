<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.goodhouse.house.model.*"%>
<%@ page import="java.sql.*, javax.sql.*"%>
<%
	HouseService houSvc = new HouseService();
	List<HouseVO> list = houSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<jsp:useBean id="lanSvc" scope="page"
	class="com.goodhouse.landlord.model.LanService" />
<jsp:useBean id="memSvc" scope="page"
	class="com.goodhouse.member.model.MemService" />

<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

</head>
<body>
	<jsp:include page="/BackHeaderFooter/Header.jsp" />

	<div class="container-fluid">
		<div class="row justify-content-center">

			<div col="col-2">
				<jsp:include page="/back/house/select_page.jsp" />

				<c:if test="${not empty errorMsgs}">
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
			</div>
			<div class="col-10">
				<div class="card">

					<div class="card-header">
						<h3>房租帳單</h3>
					</div>

					<div class="card-body">
						<div class="table-responsive">
							<table
								class="table table-bordered table-hover mb-0 text-nowrap text-center">
								<tbody>
									<tr style="background-color: #EDF9DE;">
										<!-- 			      	<th scope="col">帳單編號</th> -->
										<th scope="col">房屋名稱</th>
										<th scope="col">房屋型別</th>
										<th scope="col">房屋坪數</th>
										<!-- 		<th scope="col">房屋狀態</th> -->
										<th scope="col">房屋審核</th>
										<!-- 		<th>是否可開火</th> -->
										<!-- 		<th>是否有管理費</th> -->
										<!-- 		<th scope="col">房屋地址</th> -->
										<th scope="col">房東名子</th>
										<!-- 		<th>房屋租金</th> -->
										<!-- 		<th>備註</th> -->
										<th scope="col">修改</th>
										<th scope="col">去看看</th>
									</tr>

									<%@ include file="pages/page1.file"%>
									<c:forEach var="houVO" items="${list}" begin="<%=pageIndex%>"
										end="<%=pageIndex+rowsPerPage-1 %>">
										<tr>
											<td>${houVO.hou_name}</td>
											<td>${houVO.hou_type}</td>
											<td>${houVO.hou_size}</td>
											<%-- 			<td>${houVO.hou_property}</td> --%>
											<td>${houVO.hou_parkspace}</td>
											<%-- 			<td>${houVO.hou_cook}</td> --%>
											<%-- 			<td>${houVO.hou_managefee}</td> --%>
											<%-- 			<td>${houVO.hou_address}</td> --%>
											<td>${memSvc.getOneMem(lanSvc.getOneLan(houVO.lan_id).mem_id).mem_name}</td>
											<%-- 			<td>${houVO.hou_rent}</td> --%>
											<%-- 			<td>${houVO.hou_note}</td> --%>
											<td>
												<FORM METHOD="post"
													ACTION="<%=request.getContextPath()%>/back/house/hou.do"
													style="margin-bottom: 0px;">
													<input type="submit" class="btn btn-outline-success"
														value="修改"> <input type="hidden" name="hou_id"
														value="${houVO.hou_id}"> <input type="hidden"
														name="action" value="getOne_For_Update">
												</FORM>
											</td>
											<td>
												<form method="post"
													action="<%=request.getContextPath()%>/back/house/hou.do"
													style="margin-bottom: 0px;">
													<input type="submit" class="btn btn-outline-success"
														value="去看看"> <input type="hidden" name="hou_id"
														value="${houVO.hou_id}"> <input type="hidden"
														name="requestURL" value="<%=request.getServletPath()%>">
													<input type="hidden" name="whichPage"
														value="<%=whichPage%>"> <input type="hidden"
														name="action" value="getOne_For_Display">
												</form>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<%@ include file="pages/page2.file"%>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="/BackHeaderFooter/Footer.jsp" />
</body>
</html>