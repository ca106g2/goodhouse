<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.goodhouse.ele_contract.model.*"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="com.goodhouse.member.model.*"%>
<%@ page import="com.goodhouse.contract.model.*"%>
<%@ page import="com.goodhouse.house.model.*"%>
<%@ page import="com.goodhouse.landlord.model.*"%>
<jsp:useBean id="conSvc" scope="page" class="com.goodhouse.contract.model.ContractService"/>
<jsp:useBean id="memSvc" scope="page" class="com.goodhouse.member.model.MemService"/>
<jsp:useBean id="houSvc" scope="page" class="com.goodhouse.house.model.HouseService"/>
<jsp:useBean id="lanSvc" scope="page" class="com.goodhouse.landlord.model.LanService"/>

<%
List<Ele_ContractVO> list = (List<Ele_ContractVO>) session.getAttribute("list");
Collections.reverse(list);
pageContext.setAttribute("list", list);

%>

<!doctype html>
<html lang="en">
<head>
<style>
	#back{
		position:fixed;
		top: 20%;
		z-index: 994;
		left: 4%;
	}
</style>
</head>
<body>
	<jsp:include page="/BackHeaderFooter/Header.jsp" />

	<!-- 工作區開始 -->

	<div class="container">
		<div class="row">

			<div id="back">
				<a href="<%=request.getContextPath()%>/back/ele_contract/listAll_ele_contract.jsp">
					<img src="<%=request.getContextPath()%>/images/back.png" width="100	" height="100 !important">
				</a>
			</div>
				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<c:forEach var="message" items="${errorMsgs}">
						<ul style="color: red">${message}</ul>
						<br>
					</c:forEach>
				</c:if>
		</div>
		<div style="margin-top: 15px">
			<div class="card">

				<div class="card-header">
					<h3>電子合約</h3>
				</div>

				<div class="card-body">
					<div class="table-responsive">

						<table
							class="table table-bordered table-hover mb-0 text-nowrap text-center">
							<%@ include file="page1.file"%><br>
							<tbody>
								<tr style="background-color: #EDF9DE;">
									<!-- 									<th scope="col">電子合約編號</th> -->
									<th scope="col">合約分類名稱</th>
									<th scope="col">會員編號</th>
									<!-- 									<th scope="col">會員身份證字號</th> -->
									<th scope="col">房東編號</th>
									<!-- 									<th scope="col">房東身份證字號</th> -->
									<th scope="col">房屋編號</th>
									<th scope="col">每期租金</th>
									<!-- 									<th scope="col">押金</th> -->
									<!-- 									<th scope="col">租賃期限</th> -->
									<!-- 									<th scope="col">租賃起訖日</th> -->
									<!-- 									<th scope="col">租賃結束日</th> -->
									<th scope="col">簽約日期</th>
									<th scope="col">合約狀態</th>
<!-- 									<th scope="col">繳費型態</th> -->
									<!-- 									<th scope="col">備註</th> -->
									<th scope="col"></th>

								</tr>

								<c:forEach var="eleConVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
									<tr>
										<%-- 											<td><%=ele_con_id%></td> --%>
										<td>${conSvc.getOneCon(eleConVO.con_id).con_name}</td>
										<td>${memSvc.getOneMem(eleConVO.mem_id).mem_name}</td>
										<%-- 											<td><%=mem_idnumber%></td> --%>
										<td>${memSvc.getOneMem(lanSvc.getOneLan(eleConVO.lan_id).mem_id).mem_name}</td>
										<%-- 											<td><%=lan_idnumber%></td> --%>
										<td>${houSvc.getOneHouse(eleConVO.hou_id).hou_address}</td>
										<td>${eleConVO.ele_rent_money}</td>
										<%-- 											<td><%=ele_deposit_money%></td> --%>
										<%-- 											<td><%=ele_rent_time%></td> --%>
										<%-- 											<td><%=ele_rent_f_day%></td> --%>
										<%-- 											<td><%=ele_rent_l_day%></td> --%>
										<td>${eleConVO.ele_singdate}</td>
										<c:forEach var="Ele_con_status" items="${Ele_con_statusList}">
											<c:if
												test="${Ele_con_status.status_no eq eleConVO.ele_con_status}">
												<td>${Ele_con_status.status_name}</td>
											</c:if>
										</c:forEach>
										
<%-- 										<td><%=ele_con_status%></td> --%>
<%-- 										<td><%=bill_paymenttype%></td> --%>
										<%-- 											<td><%=ele_con_note%></td> --%>
	
										<td>
											<form method="post"
												action="<%=request.getContextPath()%>/back/ele_contract/ele_contract.do"
												style="margin-bottom: 0px;">
												<input type="submit" value="查看" style="font-weight:bold" class="btn btn-outline-info bt-lg" > 
												<input type="hidden" name="ele_con_id" value="${eleConVO.ele_con_id}"> 
												<input type="hidden" name="action" value="getOne_For_Display">
											</form>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<%@ include file="page2.file"%>
					</div>
				</div>

			</div>

		</div>

	</div>

	<!-- 工作區結束 -->
	<jsp:include page="/BackHeaderFooter/Footer.jsp" />
</body>
</html>