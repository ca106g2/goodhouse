<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.goodhouse.ad.model.*"%>
<%
	AdService adSvc= new AdService();
	List<AdVO> list = adSvc.getAll();
	pageContext.setAttribute("list",list);
%>
<html>
<head>
<meta charset="UTF-8">
<!-- Required meta tags -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS start-->
<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css">
<!-- Bootstrap CSS end-->

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">


    <style type="text/css">
    	.item{
    		background-color: #ffa;
    		padding: 6px;
    	}
    	.item img{
    		width: 100%;
    	}
    </style>
</head>
<body bgcolor='white'>



	<h3>資料查詢:</h3>
	
	<c:if test="${not empty errorMsgs}">
		<font style="color:red">請修正以下錯誤:</font>
		<ul>
		    <c:forEach var="message" items="${errorMsgs}">
				<li style="color:red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>


	<div class="container">
		<div class="row">
			<div class="col-12 col-md">
				<div class="item">
					<jsp:useBean id="houSvc" scope="page" class="com.goodhouse.house.model.HouseService"/>
					<p name="hou_id">
					
						<c:forEach var="houVO" items="${houSvc.all}">
								<c:if test="${houVO.hou_id == adVO.hou_id}">
									<img src="<%=request.getContextPath() %>/HouseServlet?hou_id=${houVO.hou_id}&photo=1" width="100" height="100">
				                 </c:if>
				        </c:forEach>

					</p>
					<h3>title</h3>
					<p>房別遠程改革一項果</p>
				</div>
			</div>
		</div>
	</div>



<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="<%=request.getContextPath()%>/bootstrap/jquery-3.3.1.slim.min.js"></script>
<script src="<%=request.getContextPath()%>/bootstrap/popper.min.js"></script>
<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>

</body>
</html>