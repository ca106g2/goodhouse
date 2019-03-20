<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.goodhouse.ele_contract.model.*"%>
<%@ page import="com.goodhouse.member.model.*"%>

<%
    String mem_id = ((MemVO)session.getAttribute("memVO")).getMem_id();
	Ele_ContractService eleConSvc = new Ele_ContractService();
	List<Ele_ContractVO> list = eleConSvc.getAllForEle_ConByMem_id(mem_id);
	pageContext.setAttribute("list",list);
%>


<!doctype html>
<html lang="en">
<head>
<style>

</style>
<script type="text/javascript">
	
		
</script>

</head>
<body>
	<jsp:include page="/FrontHeaderFooter/Header.jsp" />
	<!-- 工作區開始 -->
	<div class="container-fluid">
				<div>
					<%-- 錯誤表列 --%>
					<c:if test="${not empty errorMsgs}">
						<font style="color: red"></font>
							<c:forEach var="message" items="${errorMsgs}">
								<p style="color: red">${message}</p><br>
							</c:forEach>
					</c:if>
				</div>
				<jsp:useBean id="conSvc" scope="page" class="com.goodhouse.contract.model.ContractService"/>
				<jsp:useBean id="memSvc" scope="page" class="com.goodhouse.member.model.MemService"/>
				<jsp:useBean id="houSvc" scope="page" class="com.goodhouse.house.model.HouseService"/>
				<jsp:useBean id="lanSvc" scope="page" class="com.goodhouse.landlord.model.LanService"/>
				
				<table class="table table-hover ">
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
				 	<%@ include file="page1.file"%>
				 	<c:forEach var="eleConVO" items="${list}" begin="<%=pageIndex%>"
						end="<%=pageIndex+rowsPerPage-1%>">
				   		<tr>
				      		<td>${conSvc.getOneCon(eleConVO.con_id).getCon_name()}</td>
				      		<td>${memSvc.getOneMem(eleConVO.mem_id).mem_name}</td>
				      		<td>${memSvc.getOneMem(lanSvc.getOneLan(eleConVO.lan_id).mem_id).mem_name}</td>
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
								<form method="post" action="<%=request.getContextPath()%>/front/ele_contract/apply_conturct.do">
									<input type="hidden" name="action" value="apply_conturct">
									<input type="hidden" name="ele_con_id" value="${eleConVO.ele_con_id}">
									<input type="hidden" name="appConChoose" value="a2">
									<input class="" type="submit" value="續約" id="renewORrelease" 
													style='display:${(eleConVO.ele_con_status eq "s2") ? "" : "none"}'>
								</form>
								<form method="post" action="<%=request.getContextPath()%>/front/ele_contract/apply_conturct.do">
									<input type="hidden" name="action" value="apply_conturct">
									<input type="hidden" name="ele_con_id" value="${eleConVO.ele_con_id}">
									<input type="hidden" name="appConChoose" value="a1">
									<input class="" type="submit" value="解約" id="renewORrelease" 
													style='display:${(eleConVO.ele_con_status eq "s2") ? "" : "none"}'>
								</form>
								<form method="post" action="<%=request.getContextPath()%>/front/ele_contract/apply_conturct.do">
									<input type="hidden" name="action" value="eleConCheck">
									<input type="hidden" name="ele_con_id" value="${eleConVO.ele_con_id}">
									<input class="" type="submit" value="確認" id="eleConCheck" 
													style='display:${(eleConVO.ele_con_status eq "s1") ? "" : "none"}'>
								</form>
								<form method="post" action="<%=request.getContextPath()%>/front/ele_contract/apply_conturct.do">
									<input type="hidden" name="action" value="eleConCancle">
									<input type="hidden" name="ele_con_id" value="${eleConVO.ele_con_id}">
									<input class="" type="submit" value="取消合約" id="eleConCancle" 
													style='display:${(eleConVO.ele_con_status eq "s1") ? "" : "none"}'>
								</form>
								<form method="post" action="<%=request.getContextPath()%>/front/ele_contract/ele_contract.do">
									<input type="hidden" name="action" value="getOne_For_look">
									<input type="hidden" name="ele_con_id" value="${eleConVO.ele_con_id}">
									<input class="" type="submit" value="查看" id="eleConCancle" 
													style='display:${(eleConVO.ele_con_status != "s1") ? "" : "none"}'>
								</form>
							</td>
				    	</tr>
				    </c:forEach>
				  	</tbody>
				</table>
<!-- 		********************					//TODO  建立房客取消  合約按鈕 -->
				<%@ include file="page2.file"%>
	</div>
<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
	<!-- 工作區結束 -->
<script>



</script>

</body>
</html>