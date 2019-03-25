<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.house.model.*"%>
<%@ page import="java.util.List"%>

<jsp:useBean id="list" scope="session" type="java.util.List<HouseVO>" />
<!-- 於EL此行可省略 -->
<jsp:useBean id="houSvc" scope="page"
	class="com.goodhouse.house.model.HouseService" />

<html>

<head>
<!-- Required meta tags -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<script src="<%=request.getContextPath()%>/file/jquery-1.12.4.min.js"></script>
<!-- Bootstrap CSS start-->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/File/all.css"
	integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr"
	crossorigin="anonymous">
<!-- Bootstrap CSS end-->
<title></title>


</head>
<body>

	<jsp:include page="/BackHeaderFooter/Header.jsp" />
	<div class="container-fluid">
		<div class="row justify-content-center">

			<div class="col-10">
				<div class="card">

					<div class="card-header">
						<h3>房屋列表</h3>
					</div>

					<div class="card-body">
						<div class="table-responsive">
							<table
								class="table table-bordered table-hover mb-0 text-nowrap text-center">
								<tbody>
									<tr style="background-color: #EDF9DE;">
										<!-- 			      	<th scope="col">帳單編號</th> -->
										<th scope="col">房屋名稱</th>
										<th scope="col">房屋類別</th>
										<th scope="col">房屋審核</th>
										<th scope="col">是否可烹飪</th>
										<th scope="col">地區</th>
										<th scope="col"></th>
									</tr>
									<%@ include file="pages/page1.file"%>
									<c:forEach var="houVO" items="${list}" begin="<%=pageIndex%>"
										end="<%=pageIndex+rowsPerPage-1 %>">
										<tr align='center' valign='middle'
											${(houVO.hou_id == param.hou_id) ? 'bgcolor=#CCCCFF' : ''}>
											<td>${houVO.hou_name}</td>
											<td>${houVO.hou_type}</td>
											<td>${houVO.hou_parkspace}</td>
											<td>${houVO.hou_cook}</td>
											<td>${houVO.hou_address}</td>
											<td>
												<form method="post"
													action="<%=request.getContextPath()%>/back/house/hou.do"
													style="margin-bottom: 0px;">
													<input type="submit" value="去看看"> <input
														type="hidden" name="hou_id" value="${houVO.hou_id}">
													<input type="hidden" name="requestURL"
														value="<%=request.getServletPath()%>"> <input
														type="hidden" name="whichPage" value="<%=whichPage%>">
													<input type="hidden" name="action"
														value="getOne_For_Display">
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
	<script
		src="<%=request.getContextPath()%>/bootstrap/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/popper.min.js"
		integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
		crossorigin="anonymous"></script>
	<script
		src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
	<!-- jQuery first, then Popper.js, then Bootstrap JS end-->

	<jsp:include page="/BackHeaderFooter/Footer.jsp" />
</body>

</html>