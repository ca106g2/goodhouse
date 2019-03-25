<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.goodhouse.ele_contract.model.*"%>
<%@ page import="com.goodhouse.member.model.*"%>
<%@ page import="com.goodhouse.landlord.model.*"%>

<%
	String mem_id = ((MemVO) session.getAttribute("memVO")).getMem_id();
	Ele_ContractService eleConSvc = new Ele_ContractService();
	LanService lanSvc = new LanService();

	List<Ele_ContractVO> list = eleConSvc.getAllForEle_ConByLan_id(lanSvc.getOneLanByMemId(mem_id).getLan_id());
	Collections.reverse(list);
	pageContext.setAttribute("list", list);
%>


<!doctype html>
<html lang="en">
<head>

<style>
.back {
	font-family: impact;
	font-color:;
}
</style>

</head>
<body>
	<jsp:include page="/FrontHeaderFooter/Header.jsp" />
	<jsp:useBean id="conSvc" scope="page"
		class="com.goodhouse.contract.model.ContractService"></jsp:useBean>
	<jsp:useBean id="memSvc" scope="page"
		class="com.goodhouse.member.model.MemService"></jsp:useBean>
	<jsp:useBean id="houSvc" scope="page"
		class="com.goodhouse.house.model.HouseService"></jsp:useBean>
	<jsp:useBean id="lanSvc1" scope="page"
		class="com.goodhouse.landlord.model.LanService"></jsp:useBean>

	<!-- 工作區開始 -->

	<div class="container">

		<div class="row">
			<div class="col-2">

				<form method="post"
					action="<%=request.getContextPath()%>/front/ele_contract/lan_select_page.jsp">
					<input type="submit" value="回電子合約管理"
						class="btn btn-outline-success ">
				</form>
				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red"></font>
					<c:forEach var="message" items="${errorMsgs}">
						<p style="color: red">${message}</p>
						<br>
					</c:forEach>
				</c:if>

			</div>

			<div class="col-6">
				<form method="post" action="ele_contract.do">
					<input type="text" name="mem_name" class="btn btn-light"
						placeholder="輸入姓名查找房客合約"> <input type="hidden"
						name="action" value="lanGetMemEle_ContractByName"> <input
						type="submit" value="送出" class="btn btn-outline-success"
						id="sendmem">
				</form>
			</div>

			<div class="col-4">
				<form method="post" action="ele_contract.do">
					<input type="hidden" name="action" value="jumpToSelectContract">
					<input type="submit" value="新增電子合約"
						class="btn btn-outline-success ">
				</form>
			</div>

		</div>

		<div style="margin-top: 15px">
			<div class="card">

				<div class="card-header">
					<h3>電子合約</h3>
				</div>

				<div class="card-body">
					<div class="table-responsive">

						<%@ include file="page1.file"%><br>
						<table
							class="table table-bordered table-hover mb-0 text-nowrap text-center">
							<tbody>
								<tr style="background-color: #EDF9DE;">
									<th scope="col">合約分類名稱</th>
									<th scope="col">房客姓名</th>
									<!-- 				        	<th scope="col">房東姓名</th> -->
									<th scope="col">房屋地址</th>
									<!-- 				        	<th scope="col">每期租金</th> -->
									<!-- 				        	<th scope="col">押金</th> -->
									<!-- 				        	<th scope="col">租賃期限</th> -->
									<th scope="col">租賃起訖日</th>
									<th scope="col">租賃結束日</th>
									<!-- 				        	<th scope="col">簽約日期</th> -->
									<th scope="col">合約狀態</th>
									<!-- 				        	<th scope="col">繳費型態</th> -->
									<th scope="col"></th>
								</tr>
								<c:forEach var="eleConVO" items="${list}" begin="<%=pageIndex%>"
									end="<%=pageIndex+rowsPerPage-1%>">
									<tr>
										<td>${conSvc.getOneCon(eleConVO.con_id).con_name}</td>
										<td>${memSvc.getOneMem(eleConVO.mem_id).mem_name}</td>
										<%-- 				      		<td>${memSvc.getOneMem(lanSvc1.getOneLan(eleConVO.lan_id).mem_id).mem_name}</td> --%>
										<td>${houSvc.getOneHouse(eleConVO.hou_id).hou_address}</td>
										<%-- 				      		<td>${eleConVO.ele_rent_money}</td> --%>
										<%-- 				      		<td>${eleConVO.ele_deposit_money}</td> --%>
										<%-- 				      		<td>${eleConVO.ele_rent_time}</td> --%>
										<td>${eleConVO.ele_rent_f_day}</td>
										<td>${eleConVO.ele_rent_l_day}</td>
										<%-- 				      		<td>${eleConVO.ele_singdate}</td> --%>

										<c:forEach var="Ele_con_status" items="${Ele_con_statusList}">
											<c:if
												test="${Ele_con_status.status_no eq eleConVO.ele_con_status}">
												<td>${Ele_con_status.status_name}</td>
											</c:if>
										</c:forEach>

										<%-- 				      		<c:forEach var="Bill_PaymentType" items="${Bill_PaymentTypeMap}"> --%>
										<%-- 								<c:if test="${Bill_PaymentType.key eq eleConVO.bill_paymenttype}"> --%>
										<%-- 									<td>${Bill_PaymentType.value.type_name}</td> --%>
										<%-- 								</c:if> --%>
										<%-- 							</c:forEach> --%>

										<td>
											<form method="post" action="ele_contract.do" style="margin-bottom: 0px;">
												<input type="hidden" name="ele_con_id" value="${eleConVO.ele_con_id}"> 
												<input type="hidden" name="action" value="getOne_For_Update">
												<input type="submit" value="修改" style='display:${(eleConVO.ele_con_status eq "s1") ? "" : "none"};font-weight:bold''>
											</form>
											
											<form method="post" action="ele_contract.do">
												<input type="hidden" name="ele_con_id" value="${eleConVO.ele_con_id}"> 
												<input type="hidden" name="action" value="getOne_For_look">
												<input type="submit" value="去看看" style='display:${(eleConVO.ele_con_status eq "s1") ? "none" : ""};font-weight:bold'
													class="btn btn-outline-success ">
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

	<!-- Flexbox container for aligning the toasts -->
	<div aria-live="polite" aria-atomic="true"
		class="d-flex justify-content-center align-items-center"
		style="min-height: 200px;">

		<!-- Then put toasts within -->
		<div class="toast" role="alert" aria-live="assertive"
			aria-atomic="true">
			<div class="toast-header">
				<img src="..." class="rounded mr-2" alt="..."> <strong
					class="mr-auto">Bootstrap</strong>
				<button type="button" class="ml-2 mb-1 close" data-dismiss="toast"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="toast-body">
				<%-- 錯誤表列 --%>

			</div>
		</div>
	</div>

	<!-- 工作區結束 -->


	<c:if test="${openModal!=null}">

		<div class="modal fade" id="basicModal" tabindex="-1" role="dialog"
			aria-labelledby="basicModal" aria-hidden="true">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">

					<div class="modal-header">
						<h5 class="modal-title">請選擇合約分類</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>

					<div class="modal-body">
						<!-- =========================================以下為原listOne.jsp的內容========================================== -->
						<jsp:include page="/front/ele_contract/select_contract.jsp" />
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

	<jsp:include page="/FrontHeaderFooter/Footer.jsp" />





</body>

<script>
	var errorMsgs = $
	{
		errorMsgs
	};
</script>
</html>