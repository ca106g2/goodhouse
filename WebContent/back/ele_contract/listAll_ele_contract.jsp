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
	<jsp:include page="/FrontHeaderFooter/Header.jsp" />
	<h1></h1>

	<!-- 工作區開始 -->
	
	<div class="container-fluid">
		<div class="row justfy-content-center">
			<div class="row col-2">
				<table id="table-1">
					<p>回首頁<a href="select_page.jsp"><img src="<%=request.getContextPath()%>/share_pic/back1.gif" width="100	" height="30 !important" ></a></p>
					<tr>
						<td>
							所有電子合約資料 - listAll_ele_contract.jsp
						</td>
					</tr>
				</table>
				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color:red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color:red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
			</div>
			<div class="row col-10" >
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
						
					</tr>
					<%@ include file="page1.file" %> 
					<jsp:useBean id="conSvc" scope="page" class="com.goodhouse.contract.model.ContractService"></jsp:useBean>
					<jsp:useBean id="memSvc" scope="page" class="com.goodhouse.member.model.MemService"></jsp:useBean>
					<jsp:useBean id="houSvc" scope="page" class="com.goodhouse.house.model.HouseService"></jsp:useBean>
					<jsp:useBean id="lanSvc" scope="page" class="com.goodhouse.landlord.model.LanService"></jsp:useBean>
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
							
						</tr>
					</c:forEach>
				</table>
				<%@ include file="page2.file" %>
			</div>
				
		</div>
	</div>
	
	<!-- 工作區結束 -->
	

</body>
</html>