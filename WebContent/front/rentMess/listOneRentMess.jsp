<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.goodhouse.rental_message.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  RentMessVO rentMessVO = (RentMessVO) request.getAttribute("rentMessVO"); //RentMessServlet.java(Controller), 存入req的rentMessVO物件
%>

<html>
<head>
<title>問題 - listOneRentMess.jsp</title>

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
		 <h3>員工資料 - ListOneRentMess.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>租屋留言編號</th>
		<th>房屋編號</th>
		<th>會員編號</th>
		<th>房東編號</th>
		<th>留言時間</th>
		<th>提問內容</th>
		<th>回覆內容</th>
	</tr>
	<tr>
		<td><%=rentMessVO.getRen_mes_id()%></td>
		<td><%=rentMessVO.getHou_id()%></td>
		<td><%=rentMessVO.getMem_id()%></td>
		<td><%=rentMessVO.getLan_id()%></td>
		<td><%=rentMessVO.getRen_mes_time()%></td>
		<td><%=rentMessVO.getRen_mes_request()%></td>
		<td><%=rentMessVO.getRen_mes_response()%></td>
	</tr>
</table>

</body>

</html>