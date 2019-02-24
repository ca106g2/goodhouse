<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.member.model.*"%>   

<%
  MemVO memVO = (MemVO) request.getAttribute("memVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>


<html>
<head>

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>會員資料修改 - update_emp_input.jsp</title>
<meta charset="UTF-8">

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

<table id="table-1">
	<tr><td>
		 <h3>員工資料修改 - update_emp_input.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

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
		<td>會員編號:<font color=red><b>*</b></font></td>
		<td><%=memVO.getMem_id()%></td>
	</tr>
	<tr>
		<td>會員性別:<font color=red><b>*</b></font></td>
		<td><%=memVO.getMem_name()%></td>
	</tr>
	<tr>
		<td>會員生日:<font color=red><b>*</b></font></td>
		<td><%=memVO.getMem_birthday()%></td>
	</tr>
	<tr>
		<td>會員密碼:<font color=red><b>*</b></font></td>
		<td><%=memVO.getMem_password()%></td>
	</tr>
	<tr>
		<td>會員地址:<font color=red><b>*</b></font></td>
		<td><%=memVO.getMem_address()%></td>
	</tr>
	<tr>
		<td>會員郵遞區號:<font color=red><b>*</b></font></td>
		<td><%=memVO.getMem_zipcode()%></td>
	</tr>
	<tr>
		<td>會員電話:<font color=red><b>*</b></font></td>
		<td><%=memVO.getMem_telephone()%></td>
	</tr>
	<tr>
		<td>會員手機:<font color=red><b>*</b></font></td>
		<td><%=memVO.getMem_phone()%></td>
	</tr>
	<tr>
		<td>會員信箱:<font color=red><b>*</b></font></td>
		<td><%=memVO.getMem_email()%></td>
	</tr>
	<tr>
		<td>會員狀態:<font color=red><b>*</b></font></td>
		<td><%=memVO.getMem_status()%></td>
	</tr>
	<tr>
		<td>會員照片:<font color=red><b>*</b></font></td>
		<td><%=memVO.getMem_picture()%></td>
	</tr>
	<tr>
		<td>會員積分總分:<font color=red><b>*</b></font></td>
		<td><%=memVO.getGood_total()%></td>
	</tr>
	<tr>
		<td>會員性別:<font color=red><b>*</b></font></td>
		<td><%=memVO.getMem_sex()%></td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="mem_id" value="<%=memVO.getMem_id()%>">
<input type="submit" value="送出修改"></FORM>
</body>
</html>