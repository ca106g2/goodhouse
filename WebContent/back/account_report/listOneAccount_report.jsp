<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.goodhouse.account_report.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	Account_reportVO acreVO = (Account_reportVO) request.getAttribute("acreVO");
%>

<html>
<head>
<title></title>

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
	width: 600px;
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

	<h4>此頁暫練習採用 Script 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3></h3>
				<h4>
					<a href="select_page.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

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
		<tr>
			<td>${acreVO.acc_rep_id}</td>
			<td>${acreVO.emp_id}</td>
			<td>${acreVO.mem_id}</td>
			<td>${acreVO.lan_id}</td>
			<td>${acreVO.acc_rep_status}</td>
			<td>${acreVO.acc_rep_reason}</td>
			<td>${acreVO.acc_rep_date}</td>

		</tr>
	</table>

</body>
</html>