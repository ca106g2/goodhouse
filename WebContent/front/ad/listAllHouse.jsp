<%@ page import="com.goodhouse.house.model.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.goodhouse.house.model.*"%>
<%@ page import="java.sql.*, javax.sql.*"%>

<%
	HouseService houSvc = new HouseService();
	List<HouseVO> list = houSvc.getAll();
	pageContext.setAttribute("list", list);
%>



<html>
<head>

<meta name="viewport" http-equiv="X-UA-Compativble"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
 <style type="text/css">
    	.item{
    		background-color: #ffa;
    		padding: 6px;
    	}
    	.item img{
    		width: 100%;
    	}
    </style>

<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css">
<!-- Bootstrap CSS end-->

 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">


  
</head>
<body bgcolor='white'>

	<c:if test="${not empty errorMsgs }">
		<font style="color: red">請修正一下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<%@ include file="page1.file"%>
<div class="container">
		<div class="row">	
		<c:forEach var="houVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">
		<div class="item">
		<div class="card" style="width: 15rem;">
  		<img class="card-img-top" src="<%=request.getContextPath() %>/HouseServlet?hou_id=${houVO.hou_id}&photo=1"height="250" alt="Card image cap">
			<h5 style="font-family:Microsoft JhengHei;"><b>房屋名稱:</b></h5>
			<p>${houVO.hou_name}</p>
			<p><b>房屋型別:</b> ${houVO.hou_size}</p>
			<p><b>備註: </b>${houVO.hou_note}</p>
				</div>
			</div>
		</c:forEach>	
		</div>	
	</div>
<%@ include file="page2.file"%>

	
<script src="<%=request.getContextPath()%>/bootstrap/jquery-3.3.1.slim.min.js"></script>
<script src="<%=request.getContextPath()%>/bootstrap/popper.min.js"></script>
<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>

</body>
</html>