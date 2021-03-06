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
 #table1 { 

  font-family: 微軟正黑體; 
  font-size:16px; 
  width:500px;
  text-align:center;
  margin-left:auto; 
  margin-right:auto;
} 
 #table1 th { 
  background-color: #009FCC;
  padding:10px;

  color:#fff;
} 
 #table1 td { 
  padding:5px;
} 


</style>
</head>
<body bgcolor='white'>
<jsp:include page="/BackHeaderFooter/Header.jsp"/>


<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="emp.do" name="form1">
<table id="table1">
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
				<option  value="2" selected>正職</option>
			</select>
		</td>
	</tr>
		<tr>
		<td></td>
		<td>
			<input type="hidden" name="action" value="insert">
			<input type="submit" class="btn btn-secondary  value="送出新增"></FORM>
		</td>
	</tr>


</table >
<br>


<jsp:include page="/BackHeaderFooter/Footer.jsp"/>
</body>
</html>