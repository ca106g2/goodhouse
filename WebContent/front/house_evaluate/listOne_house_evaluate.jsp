<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.goodhouse.house_evaluate.model.*"%>


<%
  House_EvaluateVO heVO = (House_EvaluateVO) request.getAttribute("House_EvaluateVO"); //House_EvaluateServlet.java(Concroller), 存入req的empVO物件
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>房屋評價資料 - listOneHouse_Evaluate.jsp</title>
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
  
   table {
	width: 800px;
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

	<table id="table-1">
		<tr><td>
			 <h3>房屋評價資料 - listOneHouse_Evaluate.jsp</h3>
			 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
		</td></tr>
	</table>

	<table>
		<tr>
			<th>房屋評價編號</th>
			<th>評價者會員編號</th>
			<th>被評價房屋編號</th>
			<th>評價等級</th>
			<th>評價內容</th>
		</tr>
		<tr>
			<td><%=heVO.getHou_eva_id()%></td>
			<td><%=heVO.getMem_id()%></td>
			<td><%=heVO.getHou_id()%></td>
			<td><%=heVO.getHou_eva_grade()%></td>
			<td><%=heVO.getHou_eva_content()%></td>
		</tr>

	
	</table>
	
</body>
</html>