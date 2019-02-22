<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.goodhouse.contract.model.*"%>

<%
//ContractServlet.java(Concroller), 存入req的conVO物件
  ContractVO conVO = (ContractVO) request.getAttribute("conVO"); 
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

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
<body>

	<table id="table-1">
		<tr><td>
			 <h3>員工資料 - ListOne_contract.jsp</h3>
			 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
		</td></tr>
	</table>

	<table>
		<tr>
			<th>合約分類編號</th>
			<th>合約分類名稱</th>
			<th>合約內容</th>
			<th>合約分類狀態</th>
		</tr>
		<tr>
			<td><%=conVO.getCon_id()%></td>
			<td><%=conVO.getCon_name()%></td>
			<td><%=conVO.getCon_content()%></td>
			<td><%=conVO.getCon_status()%></td>
		</tr>
	</table>

</body>
</html>