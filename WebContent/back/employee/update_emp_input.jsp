<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.employee.model.*"%>

<%
  EmpVO empVO = (EmpVO) request.getAttribute("empVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>員工資料修改 </title>
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
<jsp:include page="/FrontHeaderFooter/Header.jsp"/>
<table id="table-1">
	<tr><td>
		 <h4><a href="select_page.jsp">回員工首頁</a></h4>
	</td></tr>
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

<FORM METHOD="post" ACTION="emp.do" name="form1">
<table id="table1">
	<tr>
		<td>員工編號:<font color=red><b>*</b></font></td>
		<td><%=empVO.getEmp_id()%></td>
	</tr>


	<tr>
		<td>員工姓名:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="emp_name" size="45" value="<%=empVO.getEmp_name()%>"></td>
	</tr>
	<tr>
		<td>員工電話:</td>
		<td><input type="TEXT" name="emp_phone" size="45" value="<%=empVO.getEmp_phone()%>" /></td>
	</tr>
	<tr>
		<td>員工帳號:</td>
		<td><input type="TEXT" name="emp_account" size="45"	value="<%=empVO.getEmp_account()%>" /></td>
	</tr>
	<tr>
		<td>員工密碼:</td>
		<td><input type="password" name="emp_password" size="45" value="<%=empVO.getEmp_password()%>" /></td>
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
	
	<tr>
		<td></td>
			<td>
			<input type="hidden" name="action" value="update">
			<input type="hidden" name="emp_id" value="<%=empVO.getEmp_id()%>">
			<input type="submit" class="btn btn-secondary" value="送出新增">
		</td>
	</tr>
</table>
<br>
</FORM>

<jsp:include page="/FrontHeaderFooter/Footer.jsp"/>
</body>
</html>