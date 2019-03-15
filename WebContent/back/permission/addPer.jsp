<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.permission.model.*"%>

<%
  PerVO perVO = (PerVO) request.getAttribute("perVO");
%>


<html>
<head>
<meta charset="UTF-8">
<title>權限資料新增</title>
</head>
<body>

<table id="table-1">
	<tr><td>
		 <h3>權限資料新增 - addEmp.jsp</h3></td><td>
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

<FORM METHOD="post" ACTION="per.do" name="form1">





<table>
	<tr>
		<td>權限姓名:</td>
		<td><input type="TEXT" name="per_name" size="45" 
			 value="<%= (perVO==null)? "" : perVO.getPer_name()%>" /></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>

</body>
</html>