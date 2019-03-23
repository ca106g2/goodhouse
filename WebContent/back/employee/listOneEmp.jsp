<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.goodhouse.employee.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  EmpVO empVO = (EmpVO) request.getAttribute("empVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>員工資料 - listOneEmp.jsp</title>

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
<jsp:include page="/BackHeaderFooter/Header.jsp"/>

<table class="table table-hover">
	<tr>
		<th>員工編號</th>
		<th>員工姓名</th>
		<th>員工電話</th>
		<th>員工帳號</th>
		<th>員工密碼</th>
		<th>員工狀態</th>
	</tr>
	<tr>
		<td><%=empVO.getEmp_id()%></td>
		<td><%=empVO.getEmp_name()%></td>
		<td><%=empVO.getEmp_phone()%></td>
		<td><%=empVO.getEmp_account()%></td>
		<td><%=empVO.getEmp_password()%></td>
		<%
			if(empVO.getEmp_status().equals("1")){
		%>
			<td>離職</td>				
		<%}else{%>
			<td>在職</td>		
		<%}%>
	</tr>
</table>
<jsp:include page="/BackHeaderFooter/Footer.jsp"/>
</body>
</html>