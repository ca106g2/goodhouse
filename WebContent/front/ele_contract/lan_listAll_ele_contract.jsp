<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.goodhouse.ele_contract.model.*"%>
<%@ page import="com.goodhouse.member.model.*"%>
<%@ page import="com.goodhouse.landlord.model.*"%>

<%
	String mem_id = ((MemVO)session.getAttribute("memVO")).getMem_id();
	Ele_ContractService eleConSvc = new Ele_ContractService();
	LanService lanSvc = new LanService();
	
	List<Ele_ContractVO> list = eleConSvc.getAllForEle_ConByLan_id(lanSvc.getOneLanByMemId(mem_id).getLan_id());
	pageContext.setAttribute("list",list);
%>


<!doctype html>
<html lang="en">
<head>

<style>

</style>

</head>
<body>
	<jsp:include page="/FrontHeaderFooter/Header.jsp" />
	<jsp:useBean id="conSvc" scope="page" class="com.goodhouse.contract.model.ContractService"></jsp:useBean>
	<jsp:useBean id="memSvc" scope="page" class="com.goodhouse.member.model.MemService"></jsp:useBean>
	<jsp:useBean id="houSvc" scope="page" class="com.goodhouse.house.model.HouseService"></jsp:useBean>
	<jsp:useBean id="lanSvc1" scope="page" class="com.goodhouse.landlord.model.LanService"></jsp:useBean>
					
	<!-- 工作區開始 -->

	<div class="container-fluid">
				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red"></font>
						<c:forEach var="message" items="${errorMsgs}">
							<p style="color: red">${message}</p><br>
						</c:forEach>
				</c:if>
				<%@ include file="page1.file"%><br>
				<table class="table table-hover">
					<thead>
				    	<tr>
				        	<th scope="col">合約分類名稱</th>
				        	<th scope="col">房客姓名</th>
				        	<th scope="col">房東姓名</th>
				        	<th scope="col">房屋名稱</th>
				        	<th scope="col">每期租金</th>
				        	<th scope="col">押金</th>
				        	<th scope="col">租賃期限</th>
				        	<th scope="col">租賃起訖日</th>
				        	<th scope="col">租賃結束日</th>
				        	<th scope="col">簽約日期</th>
				        	<th scope="col">合約狀態</th>
				        	<th scope="col">繳費型態</th>
				        	<th scope="col"></th>
				      	</tr>
				   	</thead>
				  	<tbody>
				  	<c:forEach var="eleConVO" items="${list}" begin="<%=pageIndex%>"
						end="<%=pageIndex+rowsPerPage-1%>">
				   		<tr>
				      		<td>${conSvc.getOneCon(eleConVO.con_id).con_name}</td>
				      		<td>${memSvc.getOneMem(eleConVO.mem_id).mem_name}</td>
				      		<td>${memSvc.getOneMem(lanSvc1.getOneLan(eleConVO.lan_id).mem_id).mem_name}</td>
				      		<td>${houSvc.getOneHouse(eleConVO.hou_id).hou_name}</td>
				      		<td>${eleConVO.ele_rent_money}</td>
				      		<td>${eleConVO.ele_deposit_money}</td>
				      		<td>${eleConVO.ele_rent_time}</td>
				      		<td>${eleConVO.ele_rent_f_day}</td>
				      		<td>${eleConVO.ele_rent_l_day}</td>
				      		<td>${eleConVO.ele_singdate}</td>
				      		
				      		<c:forEach var="Ele_con_status" items="${Ele_con_statusList}">
								<c:if test="${Ele_con_status.status_no eq eleConVO.ele_con_status}">
									<td>${Ele_con_status.status_name}</td>
								</c:if>
							</c:forEach>
							
				      		<c:forEach var="Bill_PaymentType" items="${Bill_PaymentTypeMap}">
								<c:if test="${Bill_PaymentType.key eq eleConVO.bill_paymenttype}">
									<td>${Bill_PaymentType.value.type_name}</td>
								</c:if>
							</c:forEach>
				      		
				      		<td>
								<form method="post" action="ele_contract.do" style="margin-bottom: 0px;">
									<input type="hidden" name="ele_con_id" value="${eleConVO.ele_con_id}"> 
									<input type="hidden" name="action" value="getOne_For_Update"> 
									<input type="submit" value="修改" 
											style='display:${(eleConVO.ele_con_status eq "s1") ? "" : "none"}'>
								</form>
								<form method="post" action="ele_contract.do">
									<input type="hidden" name="ele_con_id" value="${eleConVO.ele_con_id}"> 
									<input type="hidden" name="action" value="getOne_For_look"> 
									<input type="submit" value="查看" 
											style='display:${(eleConVO.ele_con_status eq "s1") ? "" : "none"}'>
								</form>
							</td>
				    	</tr>
				    </c:forEach>	
				  	</tbody>
				</table>
				<%@ include file="page2.file"%>
	</div>

	<!-- 工作區結束 -->

	<jsp:include page="/FrontHeaderFooter/Footer.jsp" />


</body>
</html>