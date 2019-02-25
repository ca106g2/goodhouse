<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.goodhouse.member.model.*"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%
  MemVO memVO = (MemVO) request.getAttribute("memVO"); 
%>

<html>
<head>
<meta charset="UTF-8">
<title>員工資料 - listOneMem.jsp</title>


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
		 <h3>員工資料 - ListOneMem.jsp</h3>
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
		<th>會員編號</th>
		<th>會員姓名</th>
		<th>會員生日</th>
		<th>會員密碼</th>
		<th>會員地址</th>
		<th>會員郵遞區號</th>
		<th>會員電話</th>
		<th>會員手機</th>
		<th>會員信箱</th>
		<th>會員狀態</th>
		<th>會員照片</th>
		<th>積分分數總合</th>
		<th>會員性別</th>
	</tr>
	<tr>
		<td><%=memVO.getMem_id()%></td>
		<td><%=memVO.getMem_name()%></td>
		<td><%=memVO.getMem_birthday()%></td>
		<td><%=memVO.getMem_password()%></td>
		<td><%=memVO.getMem_address()%></td>
		<td><%=memVO.getMem_zipcode()%></td>
		<td><%=memVO.getMem_telephone()%></td>
		<td><%=memVO.getMem_phone()%></td>
		<td><%=memVO.getMem_email()%></td>
		<td><%=memVO.getMem_status()%></td>
		<td><%=memVO.getMem_picture()%></td>
		<td><%=memVO.getGood_total()%></td>
		<td><%=memVO.getMem_sex()%></td>
	
	</tr>
</table>



</body>
</html>