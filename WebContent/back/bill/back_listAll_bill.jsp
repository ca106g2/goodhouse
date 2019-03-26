<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.bill.model.*"%>
<%@ page import="java.util.*"%>
<%
	BillService billSvc = new BillService();
	List<BillVO> list = billSvc.getAll();
	Collections.reverse(list);
	pageContext.setAttribute("list",list);
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
					
					<a href="<%=request.getContextPath()%>/back/back_index.jsp">
						<img src="<%=request.getContextPath()%>/images/back.png" width="100" height="100" border="0">
					</a>
				</div>
					<c:if test="${not empty errorMsgs}">
						<c:forEach var="message" items="${errorMsgs}">
							<p style="color:red">${message}</p><br>
						</c:forEach>
					</c:if>
			</div>
			<div class="row">
				<div class="col-2"></div>
				<div class="col-5 text-right">
					<form method="post" action="bill.do">
						<b>查詢帳單</b>
						<input type="text" name="bill_id" class="btn btn-outline-info " placeholder="輸入帳單編號">
						<input type="hidden" name="action" value="backBillForLook">
						<input type="submit" value="送出" class="btn btn-outline-info bt-lg" style="font-weight:bold">
					</form>
				</div>
				<div class="col-5">
					<form method="post" action="bill.do">
						<input type="text" name="mem_name" class="btn btn-outline-info" placeholder="輸入姓名">
						<input type="hidden" name="action" value="back_getBy_mem_name">
						<input type="submit" value="送出" class="btn btn-outline-info" style="font-weight:bold">
					</form>
				</div>
				
			</div>
			
			<div class="" style="margin-top:15px">
				
				<div class="card">
				
					<div class="card-header">
						<h3>房租帳單</h3>
					</div>
					
					<div class="card-body">
						<div class="table-responsive">
						
				
							<table class="table table-bordered table-hover mb-0 text-center">
								<tbody>
									<tr style="background-color: #EDF9DE;">
										<th scope="col"></th>
										<th scope="col">帳單編號</th>
										<th scope="col">電子合約編號</th>
										<th scope="col">繳交費用</th>
										<th scope="col">繳交日期</th>
										<th scope="col">帳單產生日期</th>
										<th scope="col">帳單繳費狀態</th>
<!-- 										<th scope="col">付款方式</th> -->
<!-- 										<th scope="col">繳費型態</th> -->
										<th scope="col"></th>
									</tr>
									<%@ include file="page1.file" %>
									<c:forEach var="billVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" >
										<tr>
											<td>
												<form method="post" action="<%=request.getContextPath()%>/back/bill/bill.do">
													<input type="hidden" name="action" value="payMoneyToLan">
													<input type="hidden" name="bill_id" value="${billVO.bill_id}">
													<input type="submit" name="" value="撥款" class="btn btn-outline-danger"
															style='display:${(billVO.bill_status eq "s3") ? "" : "none"};font-weight: bold' >
												</form>
											</td>
											<td>${billVO.bill_id}</td>
											<td>${billVO.ele_con_id}</td>
											<td>${billVO.bill_pay}</td>
											<td>${billVO.bill_date}</td>
											<td>${billVO.bill_producetime}</td>
											
											<c:forEach var="BillStatus" items="${BillStatusList}">
												<c:if test="${BillStatus.status_no eq billVO.bill_status}">
												
												<c:choose>
													<c:when test="${BillStatus.status_name eq '待繳款'  or BillStatus.status_name eq '待確認待繳款'  }">
														<td id="billStatus" style="color:#A569BD">${BillStatus.status_name}</td>
													</c:when> 
													
													<c:when test="${BillStatus.status_name eq '已繳款待撥款' }">
														<td id="billStatus" style="color:#FF0000">${BillStatus.status_name}</td>
													</c:when> 
													
													<c:when test="${BillStatus.status_name eq '延期待繳款'  or BillStatus.status_name eq '逾期未繳款'  }">
														<td id="billStatus" style="color:#0000FF">${BillStatus.status_name}</td>
													</c:when>
													
													<c:when test="${BillStatus.status_name eq '已繳款已撥款'  }">
														<td id="billStatus" style="color:#717D7E">${BillStatus.status_name}</td>
													</c:when>
												</c:choose>
												
												</c:if>
											</c:forEach>
											
<%-- 											<td>${billVO.bill_paymethod}</td> --%>
											
<%-- 											<c:forEach var="Bill_PaymentType" items="${Bill_PaymentTypeMap}"> --%>
<%-- 												<c:if test="${Bill_PaymentType.value.type_no eq  billVO.bill_paymenttype}"> --%>
<%-- 													<td>${Bill_PaymentType.value.type_name}</td> --%>
<%-- 												</c:if> --%>
<%-- 											</c:forEach> --%>
											
											<td>
												
												
												<form method="post" action="bill.do" style="size:18px">
													<input type="hidden" name="action" value="backBillForLook">
													<input type="hidden" name="bill_id" value="${billVO.bill_id}">
													<input type="submit" name="" value="查看明細" class="btn btn-outline-info btn-lg" style="font-weight:bold">
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
	
	<jsp:include page="/BackHeaderFooter/Footer.jsp" />

	<!-- 工作區結束 -->
	
		<c:if test="${openModal!=null}">

		<div class="modal fade" id="basicModal" tabindex="-1" role="dialog"
			aria-labelledby="basicModal" aria-hidden="true">
			<div class="modal-dialog modal-m">
				<div class="modal-content">

					<div class="modal-header">
						<h5 class="modal-title">房租明細</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>

					<div class="modal-body">
						<!-- =========================================以下為原listOne.jsp的內容========================================== -->
						<jsp:include page="/back/bill/back_listOne.jsp" />
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
	

	

</body>
</html>