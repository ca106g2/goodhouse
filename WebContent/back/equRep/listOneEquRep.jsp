<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.goodhouse.equipment_repair.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  EquRepVO equRepVO = (EquRepVO) request.getAttribute("equRepVO"); //EquRepServlet.java(Controller), 存入req的equRepVO物件
  /*equRepVO.setEqu_rep_id(null);*/									//將回傳回來的Equ_rep_id設為null;	(測試用)
%>

<html>
<head>
<title>設備報修資料 - listOneEquRep.jsp</title>

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
		 <h3>設備報修資料 - ListOneEquRep.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>設備報修編號</th>
		<th>房屋編號</th>
		<th>會員編號</th>
		<th>房東編號</th>
		<th>維修申請時間</th>
		<th>維修廠商</th>
		<th>維修廠商電話</th>
		<th>維修設備</th>
		<th>維修設備照片</th>
		<th>維修說明</th>
		<th>維修狀態</th>
		<th>預定維修時間</th>
		<th>維修完成時間</th>
	</tr>
	<tr>
		<td><%=equRepVO.getEqu_rep_id()%></td>
		<td><%=equRepVO.getHou_id()%></td>
		<td><%=equRepVO.getMem_id()%></td>
		<td><%=equRepVO.getLan_id()%></td>
		<td><%=equRepVO.getEqu_rep_accetime()%></td>
		<td><%=equRepVO.getEqu_rep_staff()%></td>
		<td><%=equRepVO.getEqu_rep_staffphone()%></td>
		<td><%=equRepVO.getEqu_rep_event()%></td>
		<td><img src= "<%=request.getContextPath()%>/back/equRep/equRep.do?equ_rep_id=${equRepVO.equ_rep_id}"></td>
		<!-- {equRepVO.equ_rep_id必須和第6行一致才取的到物件，原本打成EquRepVO.equ_rep_id出現第83行錯誤，阿蟲判斷request.getContextPath()不會出錯，
			     如果是equ_rep_id出錯則取不到值，可能為null但會變成空字串還是可以進行操作，所以判斷是equRepVO的錯誤。} -->
		<td><%=equRepVO.getEqu_rep_descri()%></td>
		<td><%=equRepVO.getEqu_rep_status()%></td>
		<td><%=equRepVO.getEqu_rep_expectime()%></td>
		<td><%=equRepVO.getEqu_rep_finish()%></td>
	</tr>
</table>

</body>

</html>