<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.goodhouse.member.model.*"%>
<%@ page import="com.goodhouse.apply_conturct.model.*"%>
<%@ page import="com.goodhouse.landlord.model.*"%>
<%@ page import="com.goodhouse.house.model.*"%>
<jsp:useBean id="eleConSvc" scope="page"
	class="com.goodhouse.ele_contract.model.Ele_ContractService" />
<jsp:useBean id="billSvc" scope="page"
	class="com.goodhouse.bill.model.BillService" />
<jsp:useBean id="houSvc" scope="page"
	class="com.goodhouse.house.model.HouseService" />

<%
	MemVO mVO = (MemVO) session.getAttribute("memVO");
	LanService lanSvc = new LanService();
	Apply_ConturctService appConSvc = new Apply_ConturctService();

	List<Apply_ConturctVO> list = new ArrayList();

	List<HouseVO> houList = houSvc.getAllFor_Hou_Lan_id(lanSvc.getOneLanByMemId(mVO.getMem_id()).getLan_id());

	String hou_id = null;

	for (HouseVO houVO : houList) {

		hou_id = houVO.getHou_id();
		List<Apply_ConturctVO> houAppConlist = appConSvc.getApplyListByHou_id(hou_id);

		Iterator appConObj = houAppConlist.iterator();
		while (appConObj.hasNext()) {
			Apply_ConturctVO appConVO = (Apply_ConturctVO) appConObj.next();
			list.add(appConVO);
		}
	}

	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style >
	#back{
		position:fixed;
		top: 20%;
		z-index: 994;
		left: 4%;
	}
</style>


</head>
<body>
	<jsp:include page="/FrontHeaderFooter/Header.jsp" />
	<div class="container">

		<div class="row">
			
			<div id="back">
				<a href="<%=request.getContextPath()%>/front/ele_contract/lan_select_page.jsp">
					<img src="<%=request.getContextPath()%>/images/back.png" width="100" height="100" border="0">
				</a>
			</div>

		</div>
		<div lass="row">
			<div col="col-2"></div>
			<div col="col-10" style="margin-top:15px">
				<div class="card">
					<div class="card-header">
						<h3>合約處理</h3>
					</div>
					<div class="card-body">
						<div class="table-responsive">
							<table class="table table-bordered table-hover mb-0 text-nowrap text-center">
								<tbody>
									<tr style="background-color: #EDF9DE;">
										<th scope="col"></th>
										<th scope="col">申請人</th>
										<th scope="col">房屋名稱</th>
										<th scope="col">申請選項</th>
										<th scope="col">申請備註</th>
										<th scope="col"></th>
									</tr>
									<jsp:useBean id="conSvc" scope="page"
										class="com.goodhouse.contract.model.ContractService"></jsp:useBean>
									<jsp:useBean id="memSvc" scope="page"
										class="com.goodhouse.member.model.MemService"></jsp:useBean>
									<%@ include file="page1.file"%>
									<c:forEach var="appConVO" items="${list}"
										begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
										<tr>
											<%-- 				     <c:forEach var="AppConStatus" items="${Apply_ConturctStatusMap}"> --%>
											<c:if test="${appConVO.app_con_status eq 's1'}">
												<td><img
													src="<%=request.getContextPath()%>/front/ele_contract/images/1.png"
													style="weight: 40px; height: 40px"></td>
											</c:if>
											<%-- 					</c:forEach> --%>

											<c:if test="${appConVO.app_con_status eq 's2'}">
												<td><img
													src="<%=request.getContextPath()%>/front/ele_contract/images/2.png"
													style="weight: 40px; height: 40px"></td>
											</c:if>

											<td>${houSvc.getOneHouse(appConVO.hou_id).hou_name}</td>
											<td>${memSvc.getOneMem(appConVO.mem_id).mem_name}</td>

											<c:forEach var="AppConChoose"
												items="${Apply_ConturctChooseMap}">
												<c:if test="${AppConChoose.key eq appConVO.app_con_content}">
													<td>${AppConChoose.value.choose_name}</td>
												</c:if>
											</c:forEach>


											<td>${appConVO.app_con_other}</td>
											<td>
												<form method="post" action="apply_conturct.do">
													<input type="hidden" name="action" value="checkOK">
													<input type="hidden" name="app_con_id"
														value="${appConVO.app_con_id}"> <input
														type="submit" value="確認處理"
														style='display:${(appConVO.app_con_status == "s1") ? "" : "none"}'>
												</form>
											</td>
										</tr>
									</c:forEach>
								</tbody>
								<%@ include file="page2.file"%>
							</table>
						</div>
					</div>
				</div>
			</div>
			
		</div>
	</div>
	<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
</body>
</html>