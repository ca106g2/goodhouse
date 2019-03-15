<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.goodhouse.permission.model.*"%>   

<%
  PerVO perVO = (PerVO) request.getAttribute("perVO"); //PerServlet.java(Concroller), 存入req的perVO物件
%>

<html>
<head>
<meta charset="UTF-8">
<title>權限資料</title>
</head>
<body>
<table id="table-1">
	<tr><td>
		 <h3>權限資料 - ListOnePer.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>權限編號</th>
		<th>權限姓名</th>
	
	</tr>
	<tr>
		<td><%=perVO.getPer_id()%></td>
		<td><%=perVO.getPer_name()%></td>
		
	</tr>
</table>
	

</body>
</html>