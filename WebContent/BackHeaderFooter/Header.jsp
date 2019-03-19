<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<script src="<%=request.getContextPath()%>/bootstrap/jquery-3.3.1.min.js"></script>
<!-- Bootstrap CSS start-->
<link rel="stylesheet" href="<%=request.getContextPath()%>/back/css/page.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/all.css">
<!-- Bootstrap CSS end-->

</head>
<body>
<!-- Feader頭 -->
	<header class="container-fluid" style="position:fixed;z-index:999;">
		<div class="navbar navbar-expand-lg navbar-light bg-light" style="background-color: #fffddd !important;border-radius: 0 !important;">
           <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
             <span class="navbar-toggler-icon"></span>
           </button>
           <img src="<%=request.getContextPath()%>/share_pic/logo_color.png" width="55px" height="55px">
           <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
               <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                   <li class="nav-item active">
                       <a class="nav-link" href="#">房屋總管 <span class="sr-only">(current)</span></a>
                   </li>

 <!-- 房屋 --> 
                   	<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					房屋管理</a>
					        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
					          <a class="dropdown-item" href="<%=request.getContextPath()%>/back/house/listAllHouse.jsp">房屋列表</a>
					          <a class="dropdown-item" href="<%=request.getContextPath()%>">房屋維修</a>
					          <a class="dropdown-item" href="<%=request.getContextPath()%>"></a>
					          <a class="dropdown-item" href="<%=request.getContextPath()%>">我的評價紀錄</a>
					          <a class="dropdown-item" href="<%=request.getContextPath()%>">我的最愛追蹤</a>
					        </div>
                       </li>
  <!-- 廣告 --> 
  				    <li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					廣告管理</a>
					        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
					          <a class="dropdown-item" href="<%=request.getContextPath()%>/back/ad/listAllAd.jsp">廣告列表</a>
					          <a class="dropdown-item" href="<%=request.getContextPath()%>">廣告審核</a>
					          <a class="dropdown-item" href="<%=request.getContextPath()%>"></a>
					          <a class="dropdown-item" href="<%=request.getContextPath()%>"></a>
					          <a class="dropdown-item" href="<%=request.getContextPath()%>"></a>
					        </div>
                       </li>

  <!-- 合約 --> 
     				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					電子合約管理</a>
					        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
					          <a class="dropdown-item" href="<%=request.getContextPath()%>">電子合約列表</a>
					          <a class="dropdown-item" href="<%=request.getContextPath()%>">電子合約審核</a>
					          <a class="dropdown-item" href="<%=request.getContextPath()%>"></a>
					          <a class="dropdown-item" href="<%=request.getContextPath()%>"></a>
					          <a class="dropdown-item" href="<%=request.getContextPath()%>"></a>
					        </div>
                       </li>
   
  <!-- 積分 --> 
    		     	<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					積分商城管理</a>
					        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
					          <a class="dropdown-item" href="<%=request.getContextPath()%>/back/ad/listAllAd.jsp">積分商品列表</a>
					          <a class="dropdown-item" href="<%=request.getContextPath()%>">訂單審核</a>
					          <a class="dropdown-item" href="<%=request.getContextPath()%>">訂單管理</a>
					          <a class="dropdown-item" href="<%=request.getContextPath()%>"></a>
					          <a class="dropdown-item" href="<%=request.getContextPath()%>"></a>
					        </div>
                       </li> 
  <!-- 檢舉 --> 
     
     
     
     
     
  <!-- 房屋 --> 
               </ul>

           </div>
    	</div>
	</header>
	<div style="height:80px;"></div>
<!-- Feader尾-->

</body>
</html>