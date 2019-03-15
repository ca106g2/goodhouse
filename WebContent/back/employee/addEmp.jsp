<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.employee.model.*"%>   

<!DOCTYPE html>
<%
  EmpVO empVO = (EmpVO) request.getAttribute("empVO");
%>

<html>
<head>
<meta charset="UTF-8">
<title>員工資料新增 </title>
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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>
</head>
<body bgcolor='white'>
<jsp:include page="/FrontHeaderFooter/Header.jsp"/>
<table id="table-1">
	<tr><td>
		 <h3>員工資料新增 - addEmp.jsp</h3></td><td>
		 <h4><a href="select_page.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="emp.do" name="form1">
<table>
	<tr>
		<td>員工姓名:</td>
		<td><input type="TEXT" name="emp_name" size="45" 
			 value="<%= (empVO==null)? "鄭勝文" : empVO.getEmp_name()%>" /></td>
	</tr>
	<tr>
		<td>員工電話:</td>
		<td><input type="TEXT" name="emp_phone" size="45"
			 value="<%= (empVO==null)? "0987878787" : empVO.getEmp_phone()%>" /></td>
	</tr>
	<tr>
		<td>員工帳號:</td>
		<td><input type="TEXT"  name="emp_account" size="45"
		value="<%= (empVO==null)? "aaa888" : empVO.getEmp_account()%>"/></td>
		
	</tr>
	<tr>
		<td>員工密碼:</td>
		<td><input type="password" name="emp_password" size="45"
			 value="<%= (empVO==null)? "11111" : empVO.getEmp_password()%>" /></td>
	</tr>

	
	<tr>
		<td>員工狀態:</td>
		<td>
			<select name="emp_status">
				<option  value="1">離職</option>
				<option  value="2" selected>正職</option>
			</select>
		</td>
	</tr>
	
	

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>



<form method="post" action="<%=request.getContextPath()%>/BackLogoutHandler">
	<input type="submit" value="登出">
</form>


<jsp:include page="/FrontHeaderFooter/Footer.jsp"/>
</body>
</html>