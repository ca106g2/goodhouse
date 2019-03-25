<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.goodhouse.ele_contract.model.*"%>
<%@ page import="com.goodhouse.member.model.*"%>
<%@ page import="com.goodhouse.contract.model.*"%>
<%@ page import="com.goodhouse.house.model.*"%>
<%@ page import="com.goodhouse.landlord.model.*"%>

<%
	Ele_ContractService eleConSvc = new Ele_ContractService();
	List<Ele_ContractVO> list = eleConSvc.getAll();
	Collections.reverse(list);
	pageContext.setAttribute("list",list);
%>



<!doctype html>
<html lang="en">
<head>
	 

</head>
<body>
	<jsp:include page="/BackHeaderFooter/Header.jsp" />
	<!-- 工作區開始 -->
	
	<div class="container">
		
		<div class="row">
			<div class="col-2">
				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color:red">請修正以下錯誤:</font>
						<c:forEach var="message" items="${errorMsgs}">
							<p style="color:red">${message}</p><br>
						</c:forEach>
				</c:if>
				<a href="<%=request.getContextPath()%>/back/back_index.jsp">
					<img src="<%=request.getContextPath()%>/images/back.png" width="100" height="100" border="0">
				</a>
			</div>
			
			<jsp:useBean id="conSvc" scope="page" class="com.goodhouse.contract.model.ContractService"></jsp:useBean>
			<jsp:useBean id="memSvc" scope="page" class="com.goodhouse.member.model.MemService"></jsp:useBean>
			<jsp:useBean id="houSvc" scope="page" class="com.goodhouse.house.model.HouseService"></jsp:useBean>
			<jsp:useBean id="lanSvc" scope="page" class="com.goodhouse.landlord.model.LanService"></jsp:useBean>
			
<!-- 			<div class="col-10"> -->
			
				<div class="col-5 text-right" >
					<form method="post" action="ele_contract.do">
						<b>查詢合約</b>
						<input type="text" name="ele_con_id" class="btn btn-outline-info" placeholder="輸入電子合約編號">
						<input type="hidden" name="action" value="getOne_For_Display">
						<input type="submit" value="送出" class="btn btn-outline-info">
					</form>
				</div>
				
					<div class="col-5">
						<form method="post" action="ele_contract.do">
							<input type="text" name="mem_name" class="btn btn-outline-info" placeholder="輸入會員姓名">
							<input type="hidden" name="action" value="getNameForEle_Contract">
							<input type="submit" value="送出" class="btn btn-outline-info">
						</form>
					</div>
<!-- 				</div> -->
				
			</div>
			
			<div class="" style="margin-top:15px">
				<div class="card">
				
					<div class="card-header">
						<h3>電子合約</h3>
					</div>
					
					<div class="card-body">
						<div class="table-responsive">
						
							<table class="table table-bordered table-hover mb-0 text-center">
								<tbody>
									<tr style="background-color: #EDF9DE;">
			<!-- 							<th scope="col">電子合約編號</th> -->
										<th scope="col">合約分類名稱</th>
										<th scope="col">房客姓名</th>
			<!-- 							<th scope="col">房客身份證字號</th> -->
										<th scope="col">房東姓名</th>
			<!-- 							<th scope="col">房東身份證字號</th> -->
										<th scope="col">房屋地址</th>
										<th scope="col">每期租金</th>
										<th scope="col">押金</th>
			<!-- 							<th scope="col">租賃期限</th> -->
			<!-- 							<th scope="col">租賃起訖日</th> -->
			<!-- 							<th scope="col">租賃結束日</th> -->
										<th scope="col">簽約日期</th>
										<th scope="col">合約狀態</th>
			<!-- 							<th scope="col">繳費型態</th> -->
			<!-- 							<th scope="col">備註</th> -->
										<th scope="col"></th>
										
									</tr>
								
								
								<%@ include file="page1.file" %> 
								
									<c:forEach var="eleConVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
										<tr>
			<%-- 								<td>${eleConVO.ele_con_id}</td> --%>
											<td>${conSvc.getOneCon(eleConVO.con_id).con_name}</td>
											<td>${memSvc.getOneMem(eleConVO.mem_id).mem_name}</td>
			<%-- 								<td>${eleConVO.mem_idnumber}</td> --%>
											<td>${memSvc.getOneMem(lanSvc.getOneLan(eleConVO.lan_id).mem_id).mem_name}</td>
			<%-- 								<td>${eleConVO.lan_idnumber}</td> --%>
											<td>${houSvc.getOneHouse(eleConVO.hou_id).hou_address}</td>
											<td>${eleConVO.ele_rent_money}</td>
											<td>${eleConVO.ele_deposit_money}</td>
			<%-- 								<td>${eleConVO.ele_rent_time}</td> --%>
			<%-- 								<td>${eleConVO.ele_rent_f_day}</td> --%>
			<%-- 								<td>${eleConVO.ele_rent_l_day}</td> --%>
											<td>${eleConVO.ele_singdate}</td>
<%-- 											<td>${eleConVO.ele_con_status}</td> --%>
											<c:forEach var="Ele_con_status" items="${Ele_con_statusList}">
												<c:if
													test="${Ele_con_status.status_no eq eleConVO.ele_con_status}">
													<td>${Ele_con_status.status_name}</td>
												</c:if>
											</c:forEach>
			<%-- 								<td>${eleConVO.bill_paymenttype}</td> --%>
			<%-- 								<td>${eleConVO.ele_con_note}</td> --%>
											
											<td>
												<form method="post" action="<%=request.getContextPath()%>/back/ele_contract/ele_contract.do" style="margin-bottom:0px;">
													<input type="submit" value="查看" class="btn btn-outline-info">
													<input type="hidden" name="ele_con_id" value="${eleConVO.ele_con_id}">
													<input type="hidden" name="action" value="getOne_For_Display">
												</form>
											</td>
										</tr>
									</c:forEach>
								
								</tbody>
								
							</table>
							<%@ include file="page2.file" %>
						</div>
					</div>
					
				</div>
			</div>	
	</div>
	
	<!-- 工作區結束 -->
	
	<jsp:include page="/BackHeaderFooter/Footer.jsp" />
</body>
</html>