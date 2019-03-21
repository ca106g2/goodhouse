<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.goodhouse.bill.model.*"%>
<%@ page import="javax.servlet.http.HttpSession" %>



<!doctype html>
<html lang="en">
<head>

</head>
<body>
	<jsp:include page="/BackHeaderFooter/Header.jsp" />

	<!-- 工作區開始 -->
	
	<div class="container">
		<div class="row justfy-content-center">
			<div class="row col-12">
				<table >
					<p>回帳單列表<a href="<%=request.getContextPath()%>/back/bill/back_listAll_bill.jsp">
					<img src="<%=request.getContextPath()%>/share_pic/back1.gif" width="100	" height="30 !important" ></a></p>
				</table>
				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
						<c:forEach var="message" items="${errorMsgs}">
							<p style="color:red">${message}</p><br>
						</c:forEach>
				</c:if>
			</div>
			<div class="row col-12" >
				<table class="table table-hover">
					<thead>	
						<tr>
							<th scope="col">帳單編號</th>
							<th scope="col">會員姓名</th>
							<th scope="col">繳交費用</th>
							<th scope="col">繳交日期</th>
							<th scope="col">帳單產生時間</th>
							<th scope="col">帳單繳費狀態</th>
							<th scope="col">付款方式</th>
							<th scope="col">繳費型態</th>
							
						</tr>
					</thead>
					<tbody>
						<jsp:useBean id="mSvc" scope="page" class="com.goodhouse.member.model.MemService" />
						<jsp:useBean id="eleConSvc" scope="page" class="com.goodhouse.ele_contract.model.Ele_ContractService" />
						<%
							List<BillVO> billList = (List<BillVO>) request.getAttribute("billList");
							Collections.reverse(billList);
							
							for(int i = 0 ; i < billList.size() ; i++){
								
								BillVO billVO = billList.get(i);
								
								String bill_id = billVO.getBill_id();
								String ele_con_id = billVO.getEle_con_id();
								Integer bill_pay = billVO.getBill_pay();
								Date bill_date = billVO.getBill_date();
								Date bill_producetime = billVO.getBill_producetime();
								String bill_status = billVO.getBill_status();
								String bill_paymethod = billVO.getBill_paymethod();
								String bill_paymenttype = billVO.getBill_paymenttype();
						%>
						<tr>
							<td><%=bill_id%></td>
							<td><%=mSvc.getOneMem( eleConSvc.getOneEC(ele_con_id).getMem_id() ).getMem_name() %></td>
							<td><%=bill_pay%></td>
							<td><%=bill_date%></td>
							<td><%=bill_producetime%></td>
							<td><%=bill_status%></td>
							<td><%=bill_paymethod%></td>
							<td><%=bill_paymenttype%></td>
								
						</tr>
						<% 
							}
						%>
					</tbody>
				</table>
			</div>
				
		</div>
	</div>
	
	<!-- 工作區結束 -->
	<jsp:include page="/BackHeaderFooter/Footer.jsp" />
</body>
</html>