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
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.css">
<!-- Bootstrap CSS end-->

<style>
	header{
		position:fixed;
		z-index:999;
		
	}
	.navbar{
		background-color: #EDF9DE !important;
		
		border-radius: 0 !important;
	}
	
</style>




</head>
<body>
<!-- Feader頭 -->
	<header class="container-fluid">
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
           <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
             <span class="navbar-toggler-icon"></span>
           </button>
           
           <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
               <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                   <li class="nav-item active">
                       <a class="nav-link" href="<%=request.getContextPath()%>/front/lin/houseBrowse.jsp"><img src="<%=request.getContextPath()%>/share_pic/logo_color.png" style="width:40px ; height:37px;"/>首頁</a>
                   </li>
                   <li class="nav-item">
                       <a class="nav-link " href="#" tabindex="-1" aria-disabled="true">註冊</a>
                   </li>
                   <li class="nav-item">
                       <a class="nav-link " href="<%=request.getContextPath()%>/front/ele_contract/TestLogin.html" tabindex="-1" aria-disabled="true">登入</a>
                   </li>
                   <li class="nav-item">
                       <a class="nav-link" href="#">會員中心</a>
                   </li>
                   <li class="nav-item">
                       <!-- 下拉選單 -->
						<li class="nav-item dropdown">
					        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					          房客
					        </a>
					        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
					          <a class="dropdown-item" href="<%=request.getContextPath()%>/front/ele_contract/mem_listAll_ele_contract.jsp">我的合約列表</a>
					          <a class="dropdown-item" href="<%=request.getContextPath()%>/front/bill/mem_listAll_bill.jsp">我的帳單列表</a>
					          <a class="dropdown-item" href="<%=request.getContextPath()%>">我的積分紀錄</a>
					          <a class="dropdown-item" href="<%=request.getContextPath()%>/front/house_evaluate/mem_listAll_house_evaluate.jsp">我的評價紀錄</a>
					          <a class="dropdown-item" href="<%=request.getContextPath()%>/front/house_track/mem_house_track_listAll.jsp">我的最愛追蹤</a>
					        </div>
					    </li>
                   </li>
                   <li class="nav-item">
                       <li class="nav-item dropdown">
					        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					          房東
					        </a>
					        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
					          <a class="dropdown-item" href="<%=request.getContextPath()%>/front/ele_contract/lan_select_page.jsp">電子合約管理</a>
					          <a class="dropdown-item" href="<%=request.getContextPath()%>/front/house_evaluate/lan_listAll_evaluate.jsp">我的房屋評價</a>
					          <a class="dropdown-item" href="<%=request.getContextPath()%>">新增房屋</a>
					        </div>
					    </li>
                   </li>
                   <li class="nav-item">
                       <li class="nav-item dropdown">
					        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					          積分商城
					        </a>
					        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
					          <a class="dropdown-item" href="<%=request.getContextPath()%>">我的最愛積分商品</a>
					          <a class="dropdown-item" href="<%=request.getContextPath()%>">訂單查詢</a>
					        </div>
					    </li>
                   </li>
                   
               </ul>
              
           </div>
	</header>
	<div style="height:80px;"></div>
<!-- Feader尾-->





</body>
</html>