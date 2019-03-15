<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.permission.model.*"%>

<%
  PerVO perVO = (PerVO) request.getAttribute("perVO"); //PerServlet.java (Concroller) 存入req的perVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>


<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>權限資料修改-update_per_input.jsp</title>
</head>
<body>

<table id="table-1">
	<tr><td>
		 <h3>員工資料修改 - update_per_input.jsp</h3>
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

<FORM METHOD="post" ACTION="per.do" name="form1">

<table>
	<tr>
		<td>權限編號:<font color=red><b>*</b></font></td>
		<td><%=perVO.getPer_id()%></td>
	</tr>


	<tr>
		<td>權限名稱:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="per_name" size="45" value="<%=perVO.getPer_name()%>"></td>
	</tr>
	
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="per_id" value="<%=perVO.getPer_id()%>">
<input type="submit" value="送出修改">
</FORM>	

	
	




</body>
</html>