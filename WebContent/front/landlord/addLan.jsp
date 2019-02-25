<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.landlord.model.*"%>       
  
<%
  LanVO lanVO = (LanVO) request.getAttribute("lanVO");
%>  
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>房東資料新增</title>
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
		 <h3>房東資料新增 - addLan.jsp</h3></td><td>
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

<FORM METHOD="post" ACTION="lan.do" name="form1">
<table>
	<tr>
		<td>會員姓名:</td>
		<td><input type="TEXT" name="mem_id" size="45" 
			 value="<%= (lanVO==null)? "" : lanVO.getMem_id()%>" /></td>
	</tr>
	<tr>
		<td>房東發票:</td>
		<td><input type="TEXT" name="lan_receipt" size="45"
			 value="<%= (lanVO==null)? "" : lanVO.getLan_receipt()%>" /></td>
	</tr>
	<tr>
		<td>房東帳號:</td>
		<td><input type="TEXT"  name="lan_account" size="45"
		value="<%= (lanVO==null)? "" : lanVO.getLan_account()%>"/></td>
		
	</tr>
	<tr>
		<td>房東狀態:</td>
		<td><input type="TEXT" name="lan_accountstatus" size="45"
			 value="<%= (lanVO==null)? "" : lanVO.getLan_accountstatus()%>" /></td>
	</tr>
	<tr>
		<td>良民證:</td>
		<td><input type="TEXT" name="lan_ciziten" size="45"
			 value="<%= (lanVO==null)? "1" : lanVO.getLan_ciziten()%>" /></td>
	</tr>

	
	

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>

</body>
</html>