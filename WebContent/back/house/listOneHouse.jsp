<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.goodhouse.house.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  HouseVO houVO = (HouseVO) request.getAttribute("houVO");
%>

<html>
<head>
<title></title>

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
		 <h3></h3>
		 <h4><a href="select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>房屋編號</th>
		<th>房屋名稱</th>
		<th>房屋型別</th>
		<th>房屋坪數</th>
		<th>產權證名</th>
		<th>是否含車位</th>
		<th>是否可開火</th>
		<th>是否有管理費</th>
		<th>房屋地址</th>
		<th>房屋租金</th>
		<th>備註</th>
		<th>圖片一</th>
		<th>圖片二</th>
		<th>圖片三</th>
	</tr>

		<tr>
			<td><%=houVO.getHou_id()%></td>
			<td><%=houVO.getHou_name()%></td>
			<td><%=houVO.getHou_type()%></td>
			<td><%=houVO.getHou_size()%></td>
			<td><%=houVO.getHou_property()%></td>
			<td><%=houVO.getHou_parkspace()%></td>
			<td><%=houVO.getHou_cook()%></td>
			<td><%=houVO.getHou_managefee()%></td>
			<td><%=houVO.getHou_address()%></td>
			<td><%=houVO.getHou_rent()%></td>
			<td><%=houVO.getHou_note()%></td>
			<td><img src="<%=request.getContextPath() %>/HouseServlet?hou_id=${houVO.hou_id}&photo=1"></td>
			<td><img src="<%=request.getContextPath() %>/HouseServlet?hou_id=${houVO.hou_id}&photo=2"></td>
			<td><img src="<%=request.getContextPath() %>/HouseServlet?hou_id=${houVO.hou_id}&photo=3"></td>

	</tr>
</table>

</body>
</html>