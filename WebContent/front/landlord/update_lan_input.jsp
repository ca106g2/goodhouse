<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.landlord.model.*"%>    

<%
  LanVO lanVO = (LanVO) request.getAttribute("lanVO"); 
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>房東資料修改 - update_lan_input.jsp</title>

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
		 <h3>房東資料修改 - update_emp_input.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back/employee/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回員工首頁</a></h4>
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

<FORM METHOD="post" ACTION="lan.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>房東編號:<font color=red><b>*</b></font></td>
		<td><%=lanVO.getLan_id()%></td>
	</tr>


	<tr>
		<td>會員編號:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="mem_id" size="45" value="<%=lanVO.getMem_id()%>"></td>
	</tr>
	<tr>
		<td>房東發票:</td>
		<td>
			<select name="lan_receipt">
				<option  value="1">慈善機構</option>
				<option  value="2" selected>自動兌獎(中獎匯款)</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>房東帳號:</td>
		<td><input type="TEXT" name="lan_account" size="45"	value="<%=lanVO.getLan_account()%>" /></td>
	</tr>

	
	<tr>
		<td>房東帳戶狀態:</td>
		<td>
			<select name="lan_accountstatus">
				<option  value="1" selected>審核中</option>
				<option  value="2" >已審核</option>
			</select>
		</td>
	</tr>
	
	<tr>
		<td>良民證:</td>
		<td><input type="file" name="Lan_ciziten" size="45"	/></td>
	</tr>
	

	
	

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="lan_id" value="<%=lanVO.getLan_id()%>">
<input type="submit" value="送出修改"></FORM>
<jsp:include page="/FrontHeaderFooter/Footer.jsp"/>
</body>
</html>