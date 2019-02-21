<%@ page import="com.goodhouse.house.model.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.goodhouse.house.model.*"%>
<%@ page import="java.sql.*, javax.sql.*" %> 

<%
	HouseService houSvc = new HouseService();
	List<HouseVO> list = houSvc.getAll();
	pageContext.setAttribute("list",list);
%>



<html>
<head>
<meta http-equiv="X-UA-Compativble" cotent="IE=edge,chrome=1">
<title>Insert title here</title>

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
</head>
<body>
<table id="table-1">
	<tr><td>
			<h3>所有員工資料</h3>
			<h4><a href="select_page.jsp"><img src="image/back1.jpg" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<c:if test="${not empty errorMsgs }">
	<font style="color:red">請修正一下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

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
		<th>房東編號</th>
		<th>房屋租金</th>
		<th>備註</th>
		<th>圖片一</th>
		<th>圖片二</th>
		<th>圖片三</th>
	</tr>
	<%@ include file="page1.file" %>
	<c:forEach var="houVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" >
		<tr>
			<td>${houVO.hou_id}</td>
			<td>${houVO.hou_name}</td>
			<td>${houVO.hou_type}</td>
			<td>${houVO.hou_size}</td>
			<td>${houVO.hou_property}</td>
			<td>${houVO.hou_parkspace}</td>
			<td>${houVO.hou_cook}</td>
			<td>${houVO.hou_managefee}</td>
			<td>${houVO.hou_address}</td>
			<td>${houVO.lan_id}</td>
			<td>${houVO.hou_rent}</td>
			<td>${houVO.hou_note}</td>
			<td><img src="<%=request.getContextPath() %>/HouseServlet?hou_id=${houVO.hou_id}&photo=1"></td>
			<td><img src="<%=request.getContextPath() %>/HouseServlet?hou_id=${houVO.hou_id}&photo=2"></td>
			<td><img src="<%=request.getContextPath() %>/HouseServlet?hou_id=${houVO.hou_id}&photo=3"></td>
			<td>
					  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back/house/hou.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="hou_id"  value="${houVO.hou_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back/house/hou.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="hou_id"  value="${houVO.hou_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>