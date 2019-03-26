<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.ad.model.*"%>
<%@ page import="java.util.List"%>

<jsp:useBean id="list" scope="session" type="java.util.List<AdVO>" />
<!-- 於EL此行可省略 -->
<jsp:useBean id="adSvc" scope="page"
	class="com.goodhouse.ad.model.AdService" />
<jsp:useBean id="ad_sorSvc" scope="page"
	class="com.goodhouse.ad_sort.model.Ad_sortService" />
<jsp:useBean id="lanSvc" scope="page"
	class="com.goodhouse.landlord.model.LanService" />
<jsp:useBean id="memSvc" scope="page"
	class="com.goodhouse.member.model.MemService" />
<jsp:useBean id="houSvc" scope="page"
	class="com.goodhouse.house.model.HouseService" />
<html>
<head>

<style>
</style>

</head>
<body>

	<jsp:include page="/BackHeaderFooter/Header.jsp" />
	<div class="container-fluid">
			<div>
			<a href="<%=request.getContextPath()%>/back/ad/listAllAd.jsp">
				<img src="<%=request.getContextPath()%>/images/back.png" width="100	" height="100 !important">
			</a>
		</div>
		<div class="row justify-content-center">

			<div class="col-10">
				<div class="card">

					<div class="card-header">
						<h3>廣告查詢列表</h3>
					</div>

					<div class="card-body">
						<div class="table-responsive">
							<table
								class="table table-bordered table-hover mb-0 text-nowrap text-center">
								<tbody>
									<tr style="background-color: #EDF9DE;">
										<!-- 			      	<th scope="col">帳單編號</th> -->
										<th scope="col">房東名子</th>
										<th scope="col">房屋名稱</th>
										<th scope="col">廣告分類</th>
										<!-- 		<th scope="col">廣告備註</th> -->
										<th scope="col">繳費狀態</th>
										<th scope="col">付款方式</th>
										<th scope="col"></th>
									</tr>
									<%@ include file="pages/page1.file"%>
									<c:forEach var="adVO" items="${list}" begin="<%=pageIndex%>"
										end="<%=pageIndex+rowsPerPage-1 %>">
										<tr>
											<td>${memSvc.getOneMem(lanSvc.getOneLan(adVO.lan_id).mem_id).mem_name}</td>
											<td>${houSvc.getOneHouse(adVO.hou_id).hou_name}</td>
											<td>${ad_sorSvc.getOneAd_sort(adVO.ad_sort_id).ad_forfree}</td>
											<%-- 	 		<td>${adVO.ad_forfree}</td> --%>
											<td>${adVO.ad_statue}</td>
											<td>${adVO.ad_paymethod}</td>
											<td>
												<form method="post"
													action="<%=request.getContextPath()%>/back/ad/ad.do"
													style="margin-bottom: 0px;">
													<input type="submit" class="btn btn-outline-success"
														value="去看看"> <input type="hidden" name="ad_id"
														value="${adVO.ad_id}"> <input type="hidden"
														name="requestURL" value="<%=request.getServletPath()%>">
													<input type="hidden" name="whichPage"
														value="<%=whichPage%>"> <input type="hidden"
														name="action" value="back_ad_search">
												</form>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<%@ include file="pages/page2.file"%>
							<jsp:include page="/BackHeaderFooter/Footer.jsp" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>