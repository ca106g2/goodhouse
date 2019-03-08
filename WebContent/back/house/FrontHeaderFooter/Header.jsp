<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<script src="<%=request.getContextPath()%>/bootstrap/jquery-3.3.1.min.js"></script>
<!-- Bootstrap CSS start-->
<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/all.css">
<!-- Bootstrap CSS end-->

</head>
<body>
<!-- Feader頭 -->
	<header class="container-fluid" style="position:fixed;z-index:999;">
		<div class="navbar navbar-expand-lg navbar-light bg-light" style="background-color: #EDF9DE !important;border-radius: 0 !important;">
           <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
             <span class="navbar-toggler-icon"></span>
           </button>
           <img src="<%=request.getContextPath()%>/share_pic/logo_color.png" width="55px" height="55px">
           <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
               <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                   <li class="nav-item active">
                       <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
                   </li>
                   <li class="nav-item">
                       <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">註冊</a>
                   </li>
                   <li class="nav-item">
                       <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">登入/登出</a>
                   </li>
                   <li class="nav-item">
                       <a class="nav-link" href="#">會員中心</a>
                   </li>
                   <li class="nav-item">
                       <a class="nav-link" href="#">房客</a>
                   </li>
                   <li class="nav-item">
                       <a class="nav-link" href="#">房東</a>
                   </li>
                   <li class="nav-item">
                       <a class="nav-link" href="#">積分商城</a>
                   </li>
                   
               </ul>
               <form class="form-inline my-2 my-lg-0">
                   <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                   <button class="btn btn-outline-success my-2 my-sm-0" type="submit">關鍵字搜尋</button>
               </form>
           </div>
    	</div>
	</header>
	<div style="height:80px;"></div>
<!-- Feader尾-->

</body>
</html>