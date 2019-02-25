<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.goodhouse.ele_contract.model.*"%>

<%
	Ele_ContractService eleConSvc = new Ele_ContractService();
	List<Ele_ContractVO> list = eleConSvc.getAll();
	pageContext.setAttribute("list",list);

%>


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
						<td>修改</td>
						<td>合約解除</td>
					</tr>
					<%@ include file="page1.file" %> 
					<c:forEach var="eleConVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
						<tr>
							<td>${eleConVO.ele_con_id}</td>
							<td>${eleConVO.con_id}</td>
							<td>${eleConVO.mem_id}</td>
							<td>${eleConVO.mem_idnumber}</td>
							<td>${eleConVO.lan_id}</td>
							<td>${eleConVO.lan_idnumber}</td>
							<td>${eleConVO.hou_id}</td>
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
								<form method="post" action="<%=request.getContextPath()%>/back/ele_contract/ele_contract.do" style="margin-bottom: 0px;" >
									<input type="submit" value="修改">
								</form>
							</td>
						</tr>
					</c:forEach>
				</table>
				<%@ include file="page2.file" %>
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