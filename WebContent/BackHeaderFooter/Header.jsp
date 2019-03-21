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
		<div class="navbar navbar-expand-lg navbar-light bg-light" style="background-color: #EDF9DE	 !important;border-radius: 0 !important;">
           <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
             <span class="navbar-toggler-icon"></span>
           </button>
           <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
               <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
               
                   <li class="nav-item active">
                       <a class="nav-link" href="<%=request.getContextPath()%>/back/back_index.jsp">
                       <img src="<%=request.getContextPath()%>/share_pic/logo_color.png" width="40px" height="37px">
                       	後台首頁 <span class="sr-only"></span>
                       </a>
                   </li>
					
					<li class="nav-item dropdown" style="margin-left:100px">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					會員/員工管理</a>
					     <div class="dropdown-menu" aria-labelledby="navbarDropdown">
					          <a class="dropdown-item" href="<%=request.getContextPath()%>/back/employee/listAllMem_emp.jsp">會員</a>
					          <a class="dropdown-item" href="<%=request.getContextPath()%>/back/employee/select_page.jsp">員工</a>
					     </div>
                    </li>

 <!-- 房屋 --> 
                   	<li class="nav-item dropdown" style="margin-left:60px">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					房屋管理</a>
					        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
					          <a class="dropdown-item" href="<%=request.getContextPath()%>/back/house/listAllHouse.jsp">房屋列表</a>
					        </div>
                    </li>
  <!-- 廣告 --> 
  				    <li class="nav-item dropdown" style="margin-left:60px">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					廣告管理</a>
					        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
					          <a class="dropdown-item" href="<%=request.getContextPath()%>/back/ad_sort/listAllAd_sort.jsp">廣告類型列表</a>
					          <a class="dropdown-item" href="<%=request.getContextPath()%>/back/ad_sort/addAd_sort.jsp">廣告類型新增</a>  
					          <a class="dropdown-item" href="<%=request.getContextPath()%>/back/ad/listAllAd.jsp">廣告列表</a>
					          <a class="dropdown-item" href="<%=request.getContextPath()%>/back/ad_report/listAllAd_report.jsp">廣告檢舉</a>
					        </div>
                    </li>

  <!-- 合約 --> 
     				<li class="nav-item dropdown" style="margin-left:60px">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					電子合約管理</a>
					        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
					          <a class="dropdown-item" href="<%=request.getContextPath()%>/back/contract/listAll_contract.jsp">合約分類管理</a>
					          <a class="dropdown-item" href="<%=request.getContextPath()%>/back/ele_contract/listAll_ele_contract.jsp">電子合約列表</a>
					          <a class="dropdown-item" href="<%=request.getContextPath()%>/back/bill/back_listAll_bill.jsp">租金帳單管理</a>
					        </div>
                    </li>
   
  <!-- 積分 --> 
    		     	<li class="nav-item dropdown" style="margin-left:60px">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					積分商城管理</a>
					        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
					          <a class="dropdown-item" href="<%=request.getContextPath()%>/back/pointgoods/listAllPointgoods.jsp">積分商品列表</a>
					          <a class="dropdown-item" href="<%=request.getContextPath()%>/back/pointgoods/addPointgoods.jsp">新增積分商品</a>
					          <a class="dropdown-item" href="<%=request.getContextPath()%>/back/pointgoods/good_ordManage.jsp">訂單管理</a>
					        </div>
                    </li> 
  <!-- 登出 --> 		
  					<li class="nav-item active" style="margin-left:120px">
  						<a class="nav-link" href="<%=request.getContextPath()%>">登入</a>
                   </li>
  					<li class="nav-item active" style="margin-left:20px">
  						<a class="nav-link" href="<%=request.getContextPath()%>">登出</a>
                   </li>

               </ul>

           </div>
    	</div>
	</header>
	<div style="height:80px;"></div>
<!-- Feader尾-->

</body>
</html>