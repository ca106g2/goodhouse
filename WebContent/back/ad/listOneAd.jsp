<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.house.model.*"%>
<%@ page import="com.goodhouse.ad_sort.model.*"%>
<%@ page import="com.goodhouse.ad.model.*"%>
<%@ page import="com.goodhouse.member.model.*"%>
<%@ page import="com.goodhouse.landlord.model.*"%>
<%
	AdVO adVO = (AdVO) request.getAttribute("adVO");
%>
<%-- HouseVO物件--%>
<%
	HouseService houSvc = new HouseService();
	HouseVO houVO = houSvc.getOneHouse(adVO.getHou_id());
%>
<%-- Ad_sortVO物件--%>
<%
	Ad_sortService ad_sorSvc = new Ad_sortService();
	Ad_sortVO ad_sorVO = ad_sorSvc.getOneAd_sort(adVO.getAd_sort_id());
%>

<%-- lan_sortVO物件--%>
<%
	LanService lanSvc = new LanService();
	LanVO lanVO = lanSvc.getOneLan(adVO.getLan_id());
%>

<%-- memV_sortVO物件--%>
<%
	MemService memSvc = new MemService();
	MemVO memVO = memSvc.getOneMem(lanVO.getMem_id());
%>

<html>
<head>

<title>Insert title here</title>
</head>
<body bgcolor='white'>
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
					<div class="card-body">
						<div class="table-responsive">
							<table
								class="table table-bordered table-hover mb-0 text-nowrap text-center">
								<tbody>
									<tr style="background-color: #EDF9DE;">
										<!-- 			      	<th scope="col">帳單編號</th> -->
										<th scope="col">廣告類別</th>
										<!--廣告分類編號 AD_SORTU_ID-->
										<!-- 			<th scope="col">廣告編號</th>廣告編號 AD_ID -->
										<th scope="col">房東名子</th>
										<!--房東姓名 LAN_NAME-->
										<th scope="col">房屋名稱</th>
										<!--房屋名稱 HOU_NAME-->
										<th scope="col">廣告備註</th>
										<!--廣告備註 AD_FORFREE-->
										<th scope="col">繳費狀態</th>
										<!--繳費狀態(已收,未收)AD_STATUE-->
										<th scope="col">付款方式</th>
										<!--付款方式 AD_PAYMETHODS-->
										<th scope="col">廣告刊登日</th>
										<!--廣告刊登日 AD_DATE-->
										<th scope="col">廣告狀態</th>
										<!--(上架,下架)AD_STATUS -->
										<th scope="col"></th>
									</tr>
									<tr>
										<td><%=ad_sorVO.getAd_chargetype()%></td>
										<%-- 			<td><%=adVO.getAd_id()%></td> --%>
										<td><%=memVO.getMem_name()%></td>
										<td><%=houVO.getHou_name()%></td>
										<td><%=adVO.getAd_forfree()%></td>
										<td><%=adVO.getAd_statue()%></td>
										<td><%=adVO.getAd_paymethod()%></td>
										<td><%=adVO.getAd_date()%></td>
										<td><%=adVO.getAd_status()%></td>
										<td>
											<form method="post"
												action="<%=request.getContextPath()%>/back/ad/ad.do"
												style="margin-bottom: 0px;">
												<input type="submit" class="btn btn-outline-success"
													value="修改"> <input type="hidden" name="ad_id"
													value="${adVO.ad_id}"> <input type="hidden"
													name="hou_id" value="${adVO.hou_id}"> <input
													type="hidden" name="lan_id" value="${adVO.lan_id}">
												<input type="hidden" name="ad_sort_id"
													value="${adVO.ad_sort_id}">
												<%-- 						<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"> --%>
												<%-- 						<input type="hidden" name="whichPage" value="<%=whichPage%>"> --%>
												<input type="hidden" name="action" value="getOne_For_Update">
											</form>
										</td>



									</tr>
								</tbody>
							</table>


							<jsp:include page="/BackHeaderFooter/Footer.jsp" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>