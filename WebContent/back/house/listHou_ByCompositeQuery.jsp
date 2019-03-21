<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.house.model.*" %>
<%@ page import="java.util.List"%>

<jsp:useBean id="list" scope="session" type="java.util.List<HouseVO>" /> <!-- 於EL此行可省略 -->
<jsp:useBean id="houSvc" scope="page" class="com.goodhouse.house.model.HouseService"/>

<html>

<head>
<!-- Required meta tags -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<script src="<%=request.getContextPath()%>/file/jquery-1.12.4.min.js"></script>
<!-- Bootstrap CSS start-->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/File/all.css"
	integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr"
	crossorigin="anonymous">
<!-- Bootstrap CSS end-->
<title></title>
<style>
table { 

  font-family: 微軟正黑體; 
  font-size:16px; 
  width:1200px;
  border:2px solid #000;
  text-align:center;
  border-collapse:collapse;
  margin-left:auto; 
  margin-right:auto;
} 
th { 
  background-color: #009FCC;
  padding:10px;

  color:#fff;
  border:2px solid #000;
} 
td { 
  border:1px solid #000;
  padding:5px;
} 

div{
	margin:0px auto;
}
</style>

</head>
<body>
 <div>
<jsp:include page="/BackHeaderFooter/Header.jsp" />
<br>
<br>

<table>
	<tr>
		<th>房屋名稱</th>
		<th>房屋類別</th>
		<th>房屋審核 </th>
		<th>是否可烹飪</th>
		<th>地區</th>
		<th></th>	
	</tr>
		<%@ include file="pages/page1.file"%>
	<c:forEach var="houVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1 %>">
	 	<tr align='center' valign='middle' ${(houVO.hou_id == param.hou_id) ? 'bgcolor=#CCCCFF' : ''}>
	 		<td>${houVO.hou_name}</td>
	 		<td>${houVO.hou_type}</td>
	 		<td>${houVO.hou_parkspace}</td>
	 		<td>${houVO.hou_cook}</td>
	 		<td>${houVO.hou_address}</td>
			<td>
			<form method="post" action="<%=request.getContextPath()%>/back/house/hou.do" style="margin-bottom: 0px;">
			<input type="submit" value="去看看">
			<input type="hidden" name="hou_id" value="${houVO.hou_id}">
			<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
			<input type="hidden" name="whichPage" value="<%=whichPage%>">
			<input type="hidden" name="action" value="getOne_For_Display">
			</form>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2.file" %>
	</div>
	<script
		src="<%=request.getContextPath()%>/bootstrap/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/popper.min.js"
		integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
		crossorigin="anonymous"></script>
	<script
		src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
	<!-- jQuery first, then Popper.js, then Bootstrap JS end-->

<jsp:include page="/BackHeaderFooter/Footer.jsp" />	
</body>

</html>