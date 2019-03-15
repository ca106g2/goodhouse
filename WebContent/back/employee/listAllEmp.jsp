<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.goodhouse.employee.model.*"%>


<%
EmpService empSvc = new EmpService();
List<EmpVO> list = empSvc.getAll();
pageContext.setAttribute("list",list);
%>


<html>
<head>
<meta charset="UTF-8">
<title>所有員工資料  </title>


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
<body bgcolor="white">
<jsp:include page="/FrontHeaderFooter/Header.jsp"/>
<table id="table-1">
	<tr><td>
		<h3>所有員工資料 </h3>
		<h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>

	</td></tr>
</table>

<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>員工編號</th>
		<th>員工姓名</th>
		<th>員工電話</th>
		<th>員工帳號</th>
		<th>員工密碼</th>
		<th>員工狀態</th>
    </tr>
	<%@ include file="page1.file" %>
	<c:forEach var="empVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

	<tr>
		<td>${empVO.emp_id}</td>
		<td>${empVO.emp_name}</td>
		<td>${empVO.emp_phone}</td>
		<td>${empVO.emp_account}</td>
		<td>${empVO.emp_password}</td>
		<td>${empVO.emp_status}</td>
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back/employee/emp.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="emp_id"  value="${empVO.emp_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back/employee/emp.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="emp_id"  value="${empVO.emp_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
		</td>	
	</tr>
</c:forEach>
</table>

<%@ include file="page2.file" %>

<form method="post" action="<%=request.getContextPath()%>/BackLogoutHandler">
	<input type="submit" value="登出">
</form>

<jsp:include page="/FrontHeaderFooter/Footer.jsp"/>
</body>
</html>