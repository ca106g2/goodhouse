<%@ page import="com.goodhouse.account_report.model.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*, javax.sql.*"%>

<%
	Account_reportService acreSvc = new Account_reportService();
	List<Account_reportVO> list = acreSvc.getAll();
	pageContext.setAttribute("list",list);
%>



<html>
<head>
<meta http-equiv="X-UA-Compativble" cotent="IE=edge,chrome=1">
<title>Insert title here</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>

</head>
<body bgcolor='white'>
</head>
<body>
	<table id="table-1">
		<tr>
			<td>
				<h3>所有檢舉資料</h3>
				<h4>
					<a href="select_page.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<c:if test="${not empty errorMsgs }">
		<font style="color: red">請修正一下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table>
		<tr>
			<th>檢舉編號</th>
			<th>員工編號</th>
			<th>房客編號</th>
			<th>房東編號</th>
			<th>檢舉狀態</th>
			<th>檢舉事由</th>
			<th>檢舉日期</th>
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="acreVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">
			<tr>



				<td>${acreVO.acc_rep_id}</td>
				<td>${acreVO.emp_id}</td>
				<td>${acreVO.mem_id}</td>
				<td>${acreVO.lan_id}</td>
				<td>${acreVO.acc_rep_status}</td>
				<td>${acreVO.acc_rep_reason}</td>
				<td>${acreVO.acc_rep_date}</td>

				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/back/account_report/acre.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="acc_rep_id" value="${acreVO.acc_rep_id}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/back/account_report/acre.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> 
						<input type="hidden"name="acc_rep_id" value="${acreVO.acc_rep_id}"> 
						<input type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>

</body>
</html>