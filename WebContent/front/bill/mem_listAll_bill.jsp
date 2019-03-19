<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.bill.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.goodhouse.member.model.*"%>
<%@ page import="com.goodhouse.ele_contract.model.*"%>

<%
	MemVO memVO = (MemVO)session.getAttribute("memVO");
	BillService billSvc = new BillService();
	Ele_ContractService eleConSvc = new Ele_ContractService();
	List<Ele_ContractVO> eleConVOList = (List<Ele_ContractVO>) eleConSvc.getAllForEle_ConByMem_id(memVO.getMem_id());
	List<BillVO> list = new ArrayList<BillVO>();
	String bill_id = null;
	
	for(Ele_ContractVO eleConVO : eleConVOList){
		Ele_ContractVO eleConVO1 = eleConVO;
		for(BillVO billVO : billSvc.getAll()){
			if(eleConVO.getEle_con_id().equals(billVO.getEle_con_id())) {
				bill_id = billVO.getBill_id();
				list.add(billVO);
			}
		}
	}
	pageContext.setAttribute("list",list);
%>

<!doctype html>
<html lang="en">
<head>
</head>
<body>
	<jsp:include page="/FrontHeaderFooter/Header.jsp" />
	<!-- 工作區開始 -->
	<div class="container">
					<c:if test="${not empty errorMsgs}">
					<font style="color:red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color:red">${message}</li>
						</c:forEach>
					</ul>
					</c:if>
			<table class="table table-hover">
			 	<thead>
			    	<tr>
				      	<th scope="col">帳單編號</th>
				      	<th scope="col">電子合約編號</th>
				      	<th scope="col">繳交費用</th>
				      	<th scope="col">繳交期限</th>
				      	<th scope="col">帳單產生日期</th>
				      	<th scope="col">帳單繳費狀態</th>
				      	<th scope="col">付款方式</th>
				      	<th scope="col">繳費型態</th>
			    	</tr>
			  	</thead>
			  	<tbody>
					<%@include file="page1.file"%>
					<c:forEach var="billVO" items="${list}"  begin="<%=pageIndex%>" 
						end="<%=pageIndex+rowsPerPage-1%>">
			    	<tr>
			      		<td>${billVO.bill_id}</td>
							<td>${billVO.ele_con_id}</td>
							<td>${billVO.bill_pay}</td>
							<td>${billVO.bill_date}</td>
							<td>${billVO.bill_producetime}</td>
							
							<c:forEach var="BillStatus" items="${BillStatusList}">
								<c:if test="${BillStatus.status_no eq billVO.bill_status}">
									<td>${BillStatus.status_name}</td>
								</c:if>							
							</c:forEach>
							
							<td>${billVO.bill_paymethod}</td>
							
							<c:forEach var="Bill_PaymentType" items="${Bill_PaymentTypeMap}">
								<c:if test="${Bill_PaymentType.value.type_no eq billVO.bill_paymenttype}">
									<td>${Bill_PaymentType.value.type_name}</td>
								</c:if>
							</c:forEach>
			    	</tr>
					</c:forEach>
			  	</tbody>
			</table>
				<%@ include file="page2.file" %>
	</div>
	





<jsp:include page="/FrontHeaderFooter/Footer.jsp"></jsp:include>
	<!-- 工作區結束 -->

</body>
</html>