<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>

.carousel .carousel-item {
  height: 300px;
  
}

.carousel .carousel-item img {
  min-height: 300px;
  max-height: 400px;
  object-fit: cover;
}
</style>
</head>
<body >

<jsp:include page="/FrontHeaderFooter/Header.jsp"/>

<div class="container">
   <div class="carousel slide" data-ride="carousel" id="carousel-demo">
        <ol class="carousel-indicators">
          <li data-target="#carousel-demo" data-slide-to="0" class="active"></li>
          <li data-target="#carousel-demo" data-slide-to="1"></li>
          <li data-target="#carousel-demo" data-slide-to="2"></li>
        </ol>
      <div class="carousel-inner">
        <div class="carousel-item active">
          <img class="d-block w-100" src="images/1.jpg" alt="">
        </div>
        <div class="carousel-item">
          <img class="d-block w-100" src="images/10.jpg" alt="">
        </div>
        <div class="carousel-item">
          <img class="d-block w-100" src="images/11.jpg" alt="">
        </div>
      
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