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
	pageContext.setAttribute("list",list);
%>



<!doctype html>
<html lang="en">
<head>
	
	<title></title>
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

</head>
<body>
	<jsp:include page="/BackHeaderFooter/Header.jsp" />
	<!-- 工作區開始 -->
	
	<div class="container-fluid">
		<div class="row justfy-content-center">
		
			<div class="row col-12">
				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color:red">請修正以下錯誤:</font>
						<c:forEach var="message" items="${errorMsgs}">
							<p style="color:red">${message}</p>
						</c:forEach>
				</c:if>
			</div>
			
			<jsp:useBean id="conSvc" scope="page" class="com.goodhouse.contract.model.ContractService"></jsp:useBean>
			<jsp:useBean id="memSvc" scope="page" class="com.goodhouse.member.model.MemService"></jsp:useBean>
			<jsp:useBean id="houSvc" scope="page" class="com.goodhouse.house.model.HouseService"></jsp:useBean>
			<jsp:useBean id="lanSvc" scope="page" class="com.goodhouse.landlord.model.LanService"></jsp:useBean>
			
			<div class="row col-12">
			
				<div class="row col-6">
					<form method="post" action="ele_contract.do">
						<b>輸入電子合約編號</b><br>
						<input type="text" name="ele_con_id">
						<input type="hidden" name="action" value="getOne_For_Display">
						<input type="submit" value="送出">
					</form>
				</div>
				
				<div class="row col-6">
					<form method="post" action="ele_contract.do">
						<b>輸入會員姓名</b><br>
						<input type="text" name="mem_name">
						<input type="hidden" name="action" value="getNameForEle_Contract">
						<input type="submit" value="送出">
					</form>
				</div>
				
			</div>
			
			<div class="row col-12" style="margin-top:15px">
				<table class="table table-hover">
					<thead>
						<tr>
							<th scope="col">電子合約編號</th>
							<th scope="col">合約分類名稱</th>
							<th scope="col">房客姓名</th>
							<th scope="col">房客身份證字號</th>
							<th scope="col">房東姓名</th>
							<th scope="col">房東身份證字號</th>
							<th scope="col">房屋名稱</th>
							<th scope="col">每期租金</th>
							<th scope="col">押金</th>
							<th scope="col">租賃期限</th>
							<th scope="col">租賃起訖日</th>
							<th scope="col">租賃結束日</th>
							<th scope="col">簽約日期</th>
							<th scope="col">合約狀態</th>
							<th scope="col">繳費型態</th>
							<th scope="col">備註</th>
							<th scope="col"></th>
							
						</tr>
					</thead>
					
					<tbody>
					
					<%@ include file="page1.file" %> 
					
						<c:forEach var="eleConVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
							<tr>
								<td>${eleConVO.ele_con_id}</td>
								<td>${conSvc.getOneCon(eleConVO.con_id).con_name}</td>
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
								<td>${eleConVO.ele_con_status}</td>
								<td>${eleConVO.bill_paymenttype}</td>
								<td>${eleConVO.ele_con_note}</td>
								
								<td>
									<form method="post" action="<%=request.getContextPath()%>/back/ele_contract/ele_contract.do" style="margin-bottom:0px;">
										<input type="submit" value="查看">
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
	
	<!-- 工作區結束 -->
	
	<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
</body>
</html>