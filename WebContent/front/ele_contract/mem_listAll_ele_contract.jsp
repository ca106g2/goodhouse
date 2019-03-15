<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.goodhouse.ele_contract.model.*"%>
<%@ page import="com.goodhouse.member.model.*"%>

<%
    String mem_id = ((MemVO)session.getAttribute("mVO")).getMem_id();
	Ele_ContractService eleConSvc = new Ele_ContractService();
	List<Ele_ContractVO> list = eleConSvc.getAllForEle_ConByMem_id(mem_id);
	pageContext.setAttribute("list",list);
%>


<!doctype html>
<html lang="en">
<head>
<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>
	<script type="text/javascript">
	
		
	</script>

</head>
<body>
	<jsp:include page="/FrontHeaderFooter/Header.jsp" />
	<h1></h1>

	<!-- 工作區開始 -->

	<div class="container">
		<div class="row ">
			<div class="row col-12	">
				<div>
					<p>
						回首頁<a href="mem_select_page.jsp"><img
							src="<%=request.getContextPath()%>/share_pic/back1.gif"
							width="100" height="30 !important"></a>
					</p><br>
					<%-- 錯誤表列 --%>
					<c:if test="${not empty errorMsgs}">
						<font style="color: red"></font>
							<c:forEach var="message" items="${errorMsgs}">
								<p style="color: red">${message}</p><br>
							</c:forEach>
					</c:if>
				</div>
				<table>
					<tr>
						<td>電子合約編號</td>
						<td>合約分類名稱</td>
						<td>房客姓名</td>
						<td>房客身份證字號</td>
						<td>房東姓名</td>
						<td>房東身份證字號</td>
						<td>房屋名稱</td>
						<td>每期租金</td>
						<td>押金</td>
						<td>租賃期限</td>
						<td>租賃起訖日</td>
						<td>租賃結束日</td>
						<td>簽約日期</td>
						<td>合約狀態</td>
						<td>繳費型態</td>
						<td>備註</td>
						<td></td>
						<td></td>
					</tr>
					<jsp:useBean id="conSvc" scope="page" class="com.goodhouse.contract.model.ContractService"/>
					<jsp:useBean id="memSvc" scope="page" class="com.goodhouse.member.model.MemService"/>
					<jsp:useBean id="houSvc" scope="page" class="com.goodhouse.house.model.HouseService"/>
					<jsp:useBean id="lanSvc" scope="page" class="com.goodhouse.landlord.model.LanService"/>
					<%@ include file="page1.file"%>
					<c:forEach var="eleConVO" items="${list}" begin="<%=pageIndex%>"
						end="<%=pageIndex+rowsPerPage-1%>">
						
						<tr>
							<td>${eleConVO.ele_con_id}</td>
							<td>${conSvc.getOneCon(eleConVO.con_id).getCon_name()}</td>
							<td>${memSvc.getOneMem(eleConVO.mem_id).mem_name}</td>
							<td>${eleConVO.mem_idnumber}</td>
							<td>${memSvc.getOneMem(lanSvc.getOneLan(eleConVO.lan_id).mem_id).mem_name}</td>
							<td>${eleConVO.lan_idnumber}</td>
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
							
							<td>${eleConVO.ele_con_note}</td>
							<td>
								<form method="post" action="apply_conturct.do">
									<input type="hidden" name="action" value="apply_conturct">
									<input type="hidden" name="ele_con_id" value="${eleConVO.ele_con_id}">
									<input class="" type="submit" value="續約" id="renewORrelease" 
													style='display:${(eleConVO.ele_con_status eq "s2") ? "" : "none"}'>
								</form>
							</td>
							<td>
								<form method="post" action="apply_conturct.do">
									<input type="hidden" name="action" value="apply_conturct">
									<input type="hidden" name="ele_con_id" value="${eleConVO.ele_con_id}">
									<input class="" type="submit" value="解約" id="renewORrelease" 
													style='display:${(eleConVO.ele_con_status eq "s2") ? "" : "none"}'>
								</form>
							</td>
							<td>
								<form method="post" action="apply_conturct.do">
									<input type="hidden" name="action" value="eleConCheck">
									<input type="hidden" name="ele_con_id" value="${eleConVO.ele_con_id}">
									<input class="" type="submit" value="確認" id="eleConCheck" 
													style='display:${(eleConVO.ele_con_status eq "s1") ? "" : "none"}'>
								</form>
							</td>
						</tr>
					</c:forEach>
				</table>
				<%@ include file="page2.file"%>
			</div>
		</div>
	</div>
<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
	<!-- 工作區結束 -->
<script>



</script>

</body>
</html>