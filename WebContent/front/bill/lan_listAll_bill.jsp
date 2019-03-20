<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.bill.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.goodhouse.member.model.*"%>
<%@ page import="com.goodhouse.ele_contract.model.*"%>
<%@ page import="com.goodhouse.landlord.model.*"%>

<%
	MemVO memVO = (MemVO)session.getAttribute("memVO");
	BillService billSvc = new BillService();
	Ele_ContractService eleConSvc = new Ele_ContractService();
	LanService lanSvc = new LanService();
	List<Ele_ContractVO> eleConVOList = (List<Ele_ContractVO>) eleConSvc.getAllForEle_ConByLan_id(lanSvc.getOneLanByMemId(memVO.getMem_id()).getLan_id());
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
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

</head>
<body>
	<jsp:include page="/FrontHeaderFooter/Header.jsp" />
	<div style="padding-top:30px"></div>
	<div class="container">
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color:red">${message}</li>
					</c:forEach>
				</ul>
		<table class="table table-hover">
		  	<thead>
		    	<tr>
			      	<th scope="col">帳單編號</th>
			      	<th scope="col">電子合約編號</th>
			      	<th scope="col">繳交費用</th>
			      	<th scope="col">繳交日期</th>
			      	<th scope="col">帳單產生日期</th>
			      	<th scope="col">帳單繳費狀態</th>
			      	<th scope="col">付款方式</th>
			      	<th scope="col">繳費型態</th>
		    	</tr>
		  	</thead>
		  	<tbody>
				<%@ include file="page1.file" %>
				<c:forEach var="billVO" items="${list}"  >
		    	<tr>
			      	<td>${billVO.bill_id}</td>
					<td>${billVO.ele_con_id}</td>
					<td>${billVO.bill_pay}</td>
					<td>${billVO.bill_date}</td>
					<td>${billVO.bill_producetime}</td>
					
					<c:forEach var="billStatus" items="${BillStatusList}">
						<c:if test="${billStatus.status_no eq billVO.bill_status}">
							<td>${billStatus.status_name}</td>
						</c:if>
					</c:forEach>
					
					<td>${billVO.bill_paymethod}</td>
					
					<c:forEach var="bill_PaymentType" items="${Bill_PaymentTypeMap}">
						<c:if test="${bill_PaymentType.key eq eleConVO.bill_paymenttype}">
							<td>${bill_PaymentType.value.type_name}</td>
						</c:if>
					</c:forEach>
		    	</tr>
				</c:forEach>		
		  	</tbody>
		</table>
				<%@ include file="page2.file" %>
	</div>
<jsp:include page="/FrontHeaderFooter/Footer.jsp"></jsp:include>
</body>
</html>