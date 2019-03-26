<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.bill.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.goodhouse.member.model.*"%>
<%@ page import="com.goodhouse.ele_contract.model.*"%>
<jsp:useBean id="eleConSvc" scope="page"
	class="com.goodhouse.ele_contract.model.Ele_ContractService" />
<jsp:useBean id="billSvc" scope="page"
	class="com.goodhouse.bill.model.BillService" />
<jsp:useBean id="houSvc" scope="page"
	class="com.goodhouse.house.model.HouseService" />

<%
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	List<Ele_ContractVO> eleConVOList = (List<Ele_ContractVO>) eleConSvc
			.getAllForEle_ConByMem_id(memVO.getMem_id());
	List<BillVO> list = new ArrayList<BillVO>();
	String bill_id = null;

	for (Ele_ContractVO eleConVO : eleConVOList) {
		Ele_ContractVO eleConVO1 = eleConVO;
		for (BillVO billVO : billSvc.getAll()) {
			if (eleConVO1.getEle_con_id().equals(billVO.getEle_con_id())) {
				bill_id = billVO.getBill_id();
				list.add(billVO);
			}
		}
	}
	Collections.reverse(list);
	pageContext.setAttribute("list", list);
%>

<!doctype html>
<html lang="en">
<head>

<style>
</style>


</head>
<body>
	<jsp:include page="/FrontHeaderFooter/Header.jsp" />
	<!-- 工作區開始 -->
	<div class="container-fluid">
		<div class="row justify-content-center">
			<div col="col-2">
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
			</div>
			<div class="col-10">
				<div class="card">

					<div class="card-header">
						<h3>應繳帳單</h3>
					</div>

					<div class="card-body">
						<div class="table-responsive">
							<table
								class="table table-bordered table-hover mb-0 text-nowrap text-center">
								<tbody>
									<tr style="background-color: #EDF9DE;">
										<!-- 				      	<th scope="col">帳單編號</th> -->
										<th scope="col">出租房屋地址</th>
										<th scope="col">繳交費用</th>
										<th scope="col">繳交期限</th>
										<th scope="col">帳單產生日期</th>
										<th scope="col">帳單繳費狀態</th>
										<!-- 				      	<th scope="col">付款方式</th> -->
										<th scope="col">繳費型態</th>
										<th scope="col"></th>
									</tr>
									<%@include file="page1.file"%>
									<c:forEach var="billVO" items="${list}" begin="<%=pageIndex%>"
										end="<%=pageIndex+rowsPerPage-1%>">
										<tr>
											<%-- 			      			<td>${billVO.bill_id}</td> --%>

											<td>${houSvc.getOneHouse(eleConSvc.getOneEC(billVO.ele_con_id).hou_id).hou_address}</td>
											<td>${billVO.bill_pay}</td>
											<td>${billVO.bill_date}</td>
											<td>${billVO.bill_producetime}</td>

											<c:forEach var="billStatus" items="${BillStatusList}">
												<c:if test="${billStatus.status_no eq billVO.bill_status}">

													<c:choose>
														<c:when
															test="${billStatus.status_name eq '待繳款'  or billStatus.status_name eq '待確認待繳款' }">
															<td id="billStatus" style="color: #FF0000">${billStatus.status_name}</td>
														</c:when>

														<c:when
															test="${billStatus.status_name eq '延期待繳款'  or billStatus.status_name eq '逾期未繳款'  }">
															<td id="billStatus" style="color: #0000FF">${billStatus.status_name}</td>
														</c:when>

														<c:when
															test="${billStatus.status_name eq '已繳款待撥款'  or billStatus.status_name eq '已繳款已撥款'  }">
															<td id="billStatus" style="color: #AEB6BF">${billStatus.status_name}</td>
														</c:when>
													</c:choose>

												</c:if>
											</c:forEach>

											<%-- 							<td>${billVO.bill_paymethod}</td> --%>

											<c:forEach var="bill_PaymentType"
												items="${Bill_PaymentTypeMap}">
												<c:if
													test="${bill_PaymentType.value.type_no eq billVO.bill_paymenttype}">
													<td>${bill_PaymentType.value.type_name}</td>
												</c:if>
											</c:forEach>

											<td>
												<form method="post" action="bill.do">
													<input type="hidden" name="bill_id"
														value="${billVO.bill_id}"> <input type="hidden"
														name="action" value="getOne_For_look"> <input
														type="submit" value="看明細" class="btn btn-outline-success"
														style="font-weight: bold">
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
	</div>

	<c:if test="${openModal!=null}">

		<div class="modal fade" id="basicModal" tabindex="-1" role="dialog"
			aria-labelledby="basicModal" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered modal-m">
				<div class="modal-content">

					<div class="modal-header">
						<h5 class="modal-title">帳單明細</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>

					<div class="modal-body">
						<!-- =========================================以下為原listOne.jsp的內容========================================== -->
						<jsp:include page="/front/bill/mem_listOne.jsp" />
						<!-- =========================================以上為原listOne.jsp的內容========================================== -->
					</div>

					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Close</button>
					</div>

				</div>
			</div>
		</div>

		<script>
			$("#basicModal").modal({
				show : true
			});
		</script>
	</c:if>


	<jsp:include page="/FrontHeaderFooter/Footer.jsp"></jsp:include>
	<!-- 工作區結束 -->

</body>
</html>