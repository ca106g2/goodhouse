<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.goodhouse.ele_contract.model.*"%>
<%@ page import="javax.servlet.http.HttpSession" %>



<!doctype html>
<html lang="en">
<head>
	<!-- Required meta tags -->
	<meta name="viewport"
		content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<script src="<%=request.getContextPath()%>/file/jquery-1.12.4.min.js"></script>
	<!-- Bootstrap CSS start-->
	<link rel="stylesheet"
		href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css">
	<!-- Bootstrap CSS end-->
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
							所有電子合約資料 - listSome_ele_contract.jsp
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
			<div class="row col-10	" >
				<table>
					<tr>
						<td>電子合約編號</td>
						<td>合約分類編號</td>
						<td>會員編號</td>
						<td>會員身份證字號</td>
						<td>房東編號</td>
						<td>房東身份證字號</td>
						<td>房屋編號</td>
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
						
						<%
							List<Ele_ContractVO> list = (List<Ele_ContractVO>) session.getAttribute("list");
							
							for(int i = 0 ; i < list.size() ; i++){
								Ele_ContractVO eleConVO = list.get(i);
								String ele_con_id = eleConVO.getEle_con_id();
								String con_id = eleConVO.getCon_id();
								String mem_id = eleConVO.getMem_id();
								String mem_idnumber = eleConVO.getMem_idnumber();
								String lan_id = eleConVO.getLan_id();
								String lan_idnumber = eleConVO.getLan_idnumber();
								String hou_id = eleConVO.getHou_id();
								Integer ele_rent_money = eleConVO.getEle_rent_money();
								Integer ele_deposit_money = eleConVO.getEle_deposit_money();
								Integer ele_rent_time = eleConVO.getEle_rent_time();
								Date ele_rent_f_day = eleConVO.getEle_rent_f_day();
								Date ele_rent_l_day = eleConVO.getEle_rent_l_day();
								Date ele_singdate = eleConVO.getEle_singdate();
								String ele_con_status = eleConVO.getEle_con_status();
								String bill_paymenttype = eleConVO.getBill_paymenttype();
								String ele_con_note = eleConVO.getEle_con_note();
								
						%>
							<tr>
								<td><%=ele_con_id%></td>
								<td><%=con_id%></td>
								<td><%=mem_id%></td>
								<td><%=mem_idnumber%></td>
								<td><%=lan_id%></td>
								<td><%=lan_idnumber%></td>
								<td><%=hou_id%></td>
								<td><%=ele_rent_money%></td>
								<td><%=ele_deposit_money%></td>
								<td><%=ele_rent_time%></td>
								<td><%=ele_rent_f_day%></td>
								<td><%=ele_rent_l_day%></td>
								<td><%=ele_singdate%></td>
								<td><%=ele_con_status%></td>
								<td><%=bill_paymenttype%></td>
								<td><%=ele_con_note%></td>
							</tr>
						<% 
							}
						%>
				</table>
			</div>
				
		</div>
	</div>
	
	<!-- 工作區結束 -->
	
	<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS start-->
	<script src="<%=request.getContextPath()%>/bootstrap/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/popper.min.js"
		integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
		crossorigin="anonymous"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
	<!-- jQuery first, then Popper.js, then Bootstrap JS end-->

</body>
</html>