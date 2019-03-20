<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.house.model.*" %>
<%@ page import="java.util.List"%>

<jsp:useBean id="listHou_ByCompositeQueryForIndex" scope="request" type="java.util.List<HouseVO>" /> <!-- 於EL此行可省略 -->
<jsp:useBean id="lanSvc" scope="page" class="com.goodhouse.landlord.model.LanService"/>
<jsp:useBean id="memSvc" scope="page" class="com.goodhouse.member.model.MemService"/>

<html>
<head><title>複合查詢 - listHou_ByCompositeQueryForIndex.jsp</title>
<meta charset="UTF-8">
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
<body bdcolor="white">
<h4></h4>
	<font color=red></font>
	
	<table id="table-1">
	<tr><td>
		<h3>查詢結果</h3>
		<h4><a href="<%=request.getContextPath()%>/front/lin/houseBrowse.jsp">回首頁</a></h4>
	</td></tr>
	</table>

<table>
	<tr>
		<th>房屋編號</th>
		<th>房屋名稱</th>
		<th>房屋類別</th>
		<th>房屋坪數</th>
		<th>房東</th>
		<th>房屋租金</th>
		<th>是否可烹飪</th>
		<th>管理費</th>
		<th>地址</th>
		<th></th>	
	</tr>
		<%@ include file="pages/page1_ByCompositeQuery.file"%>
	<c:forEach var="houVO" items="${listHou_ByCompositeQueryForIndex}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1 %>">
		 	<tr align='center' valign='middle' ${(houVO.hou_id== param.hou_id) ? 'bgcolor=#CCCCFF' : ''}>
		 		<td>${houVO.hou_id}</td>
		 		<td>${houVO.hou_name}</td>
		 		<td>${houVO.hou_type}</td>
		 		<td>${houVO.hou_size }</td>
		 		<td><c:forEach var="lanVO" items="${lanSvc.all}">
		 				<c:forEach var="memVO" items="${memSvc.all }">
		 			   		<c:if test="${houVO.lan_id == lanVO.lan_id}">
		 						<c:if test="${lanVO.mem_id == memVO.mem_id }">
			 							${memVO.mem_name}
		 						</c:if>
		 					</c:if>
		 				</c:forEach>
		 			</c:forEach>
		 		</td>
		 		<td>${houVO.hou_rent }</td>
		 		<td>${houVO.hou_cook}</td>
		 		<td>${houVO.hou_managefee}</td>
		 		<td>${houVO.hou_address}</td>
				<td>
				<form method="post" action="<%=request.getContextPath()%>/back/house/hou.do" style="margin-bottom: 0px;">
				<input type="submit" value="查看詳情">
				<input type="hidden" name="hou_id" value="${houVO.hou_id}">
				<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
				<input type="hidden" name="whichPage" value="<%=whichPage%>">
				<input type="hidden" name="action" value="front_getOne_For_Display">
				</form>
				</td>
			</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2_ByCompositeQuery.file" %>

</body>
</html>