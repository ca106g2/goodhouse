<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.goodhouse.house.model.*"%>
<%@ page import="com.goodhouse.landlord.model.*"%>
<%@ page import="com.goodhouse.member.model.*"%>
<%@ page import="java.sql.*, javax.sql.*"%>

<%
	System.out.println(session.getAttribute("memVO"));
	String mem_id = ((MemVO) session.getAttribute("memVO")).getMem_id();
	LanService lanSvc = new LanService();
	HouseService houSvc = new HouseService();
	List<HouseVO> listHou_ByCompositeQuery = houSvc
			.getAllFor_Hou_Lan_id(lanSvc.getOneLanByMemId(mem_id).getLan_id());
	pageContext.setAttribute("listHou_ByCompositeQuery", listHou_ByCompositeQuery);
%>


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
	<jsp:include page="/FrontHeaderFooter/Header.jsp" />
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
								<th scope="col">房屋名稱</th>
								<th scope="col">房屋類別</th>
								<th scope="col">房屋審核</th>
								<th scope="col">是否可烹飪</th>
								<th scope="col">地區</th>
								<th scope="col">房屋遊覽</th>
								<th scope="col">申請廣告</th>
							</tr>

							<%@ include file="pages/page1.file"%>
							<c:forEach var="houVO" items="${listHou_ByCompositeQuery}"
								begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1 %>">
								<tr>
									<td>${houVO.hou_name}</td>
									<td>${houVO.hou_type}</td>
									<td>${houVO.hou_parkspace}</td>
									<td>${houVO.hou_cook}</td>
									<td>${houVO.hou_address}</td>
									<td><input type="hidden" name="mem_id"
										value="${memVO.mem_id}">
										<form method="post"
											action="<%=request.getContextPath()%>/front/house/hou.do"
											style="margin-bottom: 0px;">
											<input type="submit" class="btn btn-success" value="去看看">
											<input type="hidden" name="hou_id" value="${houVO.hou_id}">
											<input type="hidden" name="requestURL"
												value="<%=request.getServletPath()%>"> <input
												type="hidden" name="whichPage" value="<%=whichPage%>">
											<input type="hidden" name="action"
												value="front_getOne_For_Display">
										</form></td>

									<td><jsp:useBean id="adSvc" scope="page"
											class="com.goodhouse.ad.model.AdService" /> <c:if
											test="${houVO.hou_parkspace eq '已審核' }">
											<c:if
												test="${adSvc.getOneAdByHou(houVO.hou_id).ad_status ne '上架' and adSvc.getOneAdByHou(houVO.hou_id).ad_status ne '下架' }">

												<form method="post"
													action="<%=request.getContextPath()%>/front/ad/addAd.jsp"
													style="margin-bottom: 0px;">
													<input type="submit" class="btn btn-success" value="申請廣告">
													<input type="hidden" name="hou_id" value="${houVO.hou_id}">
													<input type="hidden" name="hou_name"
														value="${houVO.hou_name}"> <input type="hidden"
														name="requestURL" value="<%=request.getServletPath()%>">
													<input type="hidden" name="whichPage"
														value="<%=whichPage%>">
												</form>
											</c:if>
										</c:if></td>

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

</body>

<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
</html>