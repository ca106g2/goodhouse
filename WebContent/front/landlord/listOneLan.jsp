<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.landlord.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sun.org.apache.xerces.internal.impl.dv.util.Base64"%>

<%
  LanVO lanVO = (LanVO) request.getAttribute("lanVO"); 
%>

<html>
<head>
<meta charset="UTF-8">
<title>房東資料-listOneLan</title>

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
  
  #bc{
  	width:100px;
  	height:100px;
  
  
  }
  
  
</style>

</head>
<body bgcolor='white'>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>員工資料 - ListOneLan.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>房東編號</th>
		<th>會員編號</th>
		<th>房東發票</th>
		<th>房東帳號</th>
		<th>房東狀態</th>
		<th>良民證</th>
	</tr>
	<tr>
		<td><%=lanVO.getLan_id()%></td>
		<td><%=lanVO.getMem_id()%></td>
		<td><%=lanVO.getLan_receipt()%></td>
		<td><%=lanVO.getLan_account()%></td>
		<td><%=lanVO.getLan_accountstatus()%></td>
		<%
				byte b[] = null;
				b = lanVO.getLan_ciziten();	
				String encoding = null;
				if(b != null){
				encoding = Base64.encode(b);
			%>
				<td><img id="bc" src="data:image/jpg;base64,<%=encoding %>"></td>
			<%
				}%>
		
		
		
	</tr>
</table>
</body>
</html>