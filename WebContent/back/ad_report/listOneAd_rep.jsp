<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ page import="com.goodhouse.ad_report.model.*"%>
<%@ page import="com.goodhouse.member.model.*"%>
<%@ page import="com.goodhouse.ad.model.*"%>
<%@ page import="com.goodhouse.house.model.*"%>
<%
	Ad_reportVO ad_repVO = (Ad_reportVO) request.getAttribute("ad_repVO");
%>
<jsp:useBean id="adSvc" scope="page"
	class="com.goodhouse.ad.model.AdService" />
<jsp:useBean id="houSvc" scope="page"
	class="com.goodhouse.house.model.HouseService" />
<jsp:useBean id="memSvc" scope="page"
	class="com.goodhouse.member.model.MemService" />
<html>
<head>
<meta charset="UTF-8">


</head>
<jsp:include page="/BackHeaderFooter/Header.jsp" />
<body>
			<div>
			<a href="<%=request.getContextPath()%>/back/ad_report/listAllAd_report.jsp">
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
									<th scope="col">被檢舉廣告</th>
									<th scope="col">檢舉房客</th>
									<th scope="col">檢舉內容</th>
									<th scope="col">檢舉狀態</th>
									<th scope="col">檢舉日期</th>
								</tr>
								<tr>
									<%
										AdVO adVO = adSvc.getOneAD(ad_repVO.getAd_id());
										HouseVO houVO = houSvc.getOneHouse(adVO.getHou_id());
									%>
									<td><%=houVO.getHou_name()%></td>
									<%
										MemVO memVO = memSvc.getOneMem(ad_repVO.getMem_id());
									%>
									<td><%=memVO.getMem_name()%></td>
									<td><%=ad_repVO.getAd_rep_reason()%></td>
									<td><%=ad_repVO.getAd_rep_status()%></td>
									<td><%=ad_repVO.getAd_rep_date()%></td>
								</tr>
							</tbody>
						</table>
						<jsp:include page="/BackHeaderFooter/Footer.jsp" />
					</div>
				</div>
			</div>
		</div>
	</div>


</body>
</html>