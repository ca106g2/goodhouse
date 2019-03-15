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
  h3{
  	margin-left:auto; 
    margin-right:auto;
  }
  table {
	width: 1500px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
	margin-left:auto; 
    margin-right:auto;
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
<jsp:include page="/FrontHeaderFooter/Header.jsp" />

<table>
	<tr>
		<tr><th>房屋名稱 : <td><%=houVO.getHou_name()%></td></th></tr>
		<tr><th>房屋型別 : <td><%=houVO.getHou_type()%></td></th></tr>
		<tr><th>房屋坪數 : <td><%=houVO.getHou_size()%></td></th></tr>
		<tr><th>房屋狀態 : <td><%=houVO.getHou_property()%></td></th></tr>
		<tr><th>房屋審核 : <td><%=houVO.getHou_parkspace()%></td></th></tr>
		<tr><th>是否可開火 : <td><%=houVO.getHou_cook()%></td></th></tr>
		<tr><th>是否有管理費 : <td><%=houVO.getHou_managefee()%></td></th></tr>
		<tr><th>房屋地址 : <td><%=houVO.getHou_address()%></td></th></tr>
		<tr><th>房屋租金 : <td><%=houVO.getHou_rent()%></td></th></tr>
		<tr><th>備註 : <td><%=houVO.getHou_note()%></td></th></tr>
		<tr><th>圖片一 : <td><img src="<%=request.getContextPath() %>/HouseServlet?hou_id=${houVO.hou_id}&photo=1"></td></th></tr>
		<tr><th>圖片二 : <td><img src="<%=request.getContextPath() %>/HouseServlet?hou_id=${houVO.hou_id}&photo=2"></td></th></tr>
		<th>圖片三 : <td><img src="<%=request.getContextPath() %>/HouseServlet?hou_id=${houVO.hou_id}&photo=3"></td></th>
	</tr>

</table>
<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
</body>
</html>