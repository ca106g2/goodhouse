<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:useBean id="adSvc" scope="page" class="com.goodhouse.ad.model.AdService"/>
<jsp:useBean id="ad_sorSvc" scope="page" class="com.goodhouse.ad_sort.model.Ad_sortService"/>
<jsp:useBean id="houSvc" scope="page" class="com.goodhouse.house.model.HouseService"/>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>


.carousel .carousel-item img {
  height: 50%;
  width : 50%;
  
}
</style>
</head>
<body >

<jsp:include page="/FrontHeaderFooter/Header.jsp"/>

<div class="container">
   <div class="carousel slide" data-ride="carousel" id="carousel-demo">

        <div class="carousel-inner">
        	        <div class="carousel-item active carousel-item-left">
         			 <img class="d-block w-100" src="images/45.jpg" alt="">
        			</div>
        			<div class="carousel-item carousel-item-next carousel-item-left">
         			 <img class="d-block w-100" src="images/46.jpg" alt="">
        			</div>
	        <c:forEach var="adVO" varStatus="s" items="${adSvc.all}">
				<c:forEach var="hou_VO" items="${houSvc.all}">
					<c:if test="${adVO.ad_sort_id eq 'ADS0000002' and adVO.ad_status eq '上架'}">
							<c:if test="${adVO.hou_id eq hou_VO.hou_id}">
									<div class="carousel-item">
		          					<img class="d-block w-100" src="<%=request.getContextPath() %>/HouseServlet?hou_id=${adVO.hou_id}&photo=1" alt="">
		          				 </div>
	   					</c:if>
	   				</c:if>
	          	</c:forEach>
	        </c:forEach>
	        
	        <a href="#carousel-demo" class="carousel-control-prev" data-slide="prev">
	        <span class="carousel-control-prev-icon"></span>
	        </a>
	        <a href="#carousel-demo" class="carousel-control-next" data-slide="next">
	         <span class="carousel-control-next-icon"></span>
	        </a>
        </div>

      
    </div>
</div>

<div style="text-align:center">

<jsp:include page="listAllHouse.jsp"/>

</div>
<jsp:include page="/FrontHeaderFooter/Footer.jsp"/>

</body>

</html>