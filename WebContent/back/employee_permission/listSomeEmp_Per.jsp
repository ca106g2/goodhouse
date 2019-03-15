<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.goodhouse.employee_permission.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  //List<EmpAuthVO> list = (List<EmpAuthVO>)request.getAttribute("list");
%>
<jsp:useBean id="list" scope="session" type="java.util.List<Emp_PerVO>"/>
<!doctype html>
<html lang="zh-Hant-TW">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->

<title>員工權限資料 - listOneEmpAuth.jsp</title>

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
	<tr><td>
		 <h3>員工權限資料 - ListSomeEmp_Per.jsp</h3>
		 <h4><a href="../employee/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>員工ID</th>
		<th>權限ID</th>
		
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="emp_perVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr>
			<td>${emp_perVO.emp_id}</td>
			<td>${emp_perVO.per_id}</td>
		
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

  
</body>
</html>