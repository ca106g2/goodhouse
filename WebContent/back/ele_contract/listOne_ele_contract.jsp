<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.ele_contract.model.*" %>

<%
	Ele_ContractVO eleConVO = (Ele_ContractVO) request.getAttribute("eleConVO");
%>

<!doctype html>
<html lang="en">
<head>
<title></title>
</head>
<body>
	<jsp:include page="/BackHeaderFooter/Header.jsp" />

	<!-- 工作區開始 -->
	<div class="container">
			<div class="row col-12">
				<table id="table-1">
					<p>回電子合約列表<a href="<%=request.getContextPath()%>/back/ele_contract/listAll_ele_contract.jsp">
					<img src="<%=request.getContextPath()%>/share_pic/back1.gif" width="100	" height="30 !important" ></a></p>
				</table>
			</div>
			<div class="row col-12">
			
				<table>
					<tr>
						<td>電子合約編號</td>
						<td>${eleConVO.ele_con_id}</td>
					</tr>
					<jsp:useBean id="conSvc" scope="page" class="com.goodhouse.contract.model.ContractService"></jsp:useBean>
					<tr>
						<td>合約分類名稱</td>
						<td>${conSvc.getOneCon(eleConVO.con_id).con_name}</td>
					</tr>
					<jsp:useBean id="memSvc" scope="page" class="com.goodhouse.member.model.MemService"></jsp:useBean>
					<tr>
						<td>房客姓名</td>
						<td>${memSvc.getOneMem(eleConVO.mem_id).mem_name}</td>
					</tr>
					<tr>
						<td>房客身分證字號</td>
						<td>${eleConVO.mem_idnumber}</td>
					</tr>
					<jsp:useBean id="lanSvc" scope="page" class="com.goodhouse.landlord.model.LanService"></jsp:useBean>
					<tr>
						<td>房東姓名</td>
						<td>${memSvc.getOneMem(lanSvc.getOneLan(eleConVO.lan_id).mem_id).mem_name}</td>
					</tr>
					<tr>
						<td>房東身分證字號</td>
						<td>${eleConVO.lan_idnumber}</td>
					</tr>
					<jsp:useBean id="houSvc" scope="page" class="com.goodhouse.house.model.HouseService"></jsp:useBean>
					<tr>
						<td>房屋名稱</td>
						<td>${houSvc.getOneHouse(eleConVO.hou_id).hou_name}</td>
					</tr>
					<tr>
						<td>每期租金</td>
						<td>${eleConVO.ele_rent_money}元</td>
					</tr>
					<tr>
						<td>押金</td>
						<td>${eleConVO.ele_deposit_money}元</td>
					</tr>
					<tr>
						<td>租賃期限</td>
						<td>${eleConVO.ele_rent_time}</td>
					</tr>
					<tr>
						<td>租賃起訖日</td>
						<td>${eleConVO.ele_rent_f_day}</td>
					</tr>
					<tr>
						<td>租賃結束日</td>
						<td>${eleConVO.ele_rent_l_day}</td>
					</tr>
					<tr>
						<td>簽約日期</td>
						<td>${eleConVO.ele_singdate}</td>
					</tr>
					<tr>
						<td>合約狀態</td>
						<td>${eleConVO.ele_con_status}</td>
					</tr>
					<tr>
						<td>繳費型態</td>
						<td>${eleConVO.bill_paymenttype}</td>
					</tr>
					<tr>
						<td>備註</td>
						<td>${eleConVO.ele_con_note}</td>
					</tr>	
				</table>
			</div>
	</div>
	
	<!-- 工作區結束 -->

	<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
</body>
</html>