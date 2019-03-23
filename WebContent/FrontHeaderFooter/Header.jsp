<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.member.model.*" %> 
<%@ page import="com.goodhouse.landlord.model.*" %>
<%
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	pageContext.setAttribute("memVO", memVO);
	String mem_id = "";
	
	if (memVO != null){
		mem_id = memVO.getMem_id();
	}
	LanService lanSvc = new LanService();
	LanVO lanVO = lanSvc.getOneLanByMemId(mem_id);
	pageContext.setAttribute("lanVO", lanVO);
%>
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
/* 		background-color: #EDF9DE !important; */
		
		border-radius: 0 !important;
	}
	
</style>




</head>
<body>
<!-- Feader頭 -->
	<header class="container-fluid">
		<nav class="navbar navbar-expand-lg navbar-light bg-light shadow p-3 mb-5 bg-white rounded">
           <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
             <span class="navbar-toggler-icon"></span>
           </button>
           
           <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
               <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                   <li class="nav-item active">
                       <a class="nav-link" href="<%=request.getContextPath()%>/front/index.jsp">
                       <img src="<%=request.getContextPath()%>/share_pic/logo_color.png" style="width:40px ; height:37px;"/>
                       	首頁
                       	</a>
                   </li>
                   <c:if test="${empty memVO}">
                       <li class="nav-item active">
                           <a class="nav-link " href="<%=request.getContextPath()%>/front/member/addMem.jsp" tabindex="-1" aria-disabled="true"
                           	style="margin-top:4px">註冊</a>
                       </li>
                   </c:if>

				   <c:if test="${empty memVO}">
                       <li class="nav-item">
                           <a class="nav-link " href="<%=request.getContextPath()%>/front/frontLogin.jsp" tabindex="-1" aria-disabled="true"
                           	style="margin-top:4px">登入</a>
                       </li>
                   </c:if>
                   
                   <c:if test="${not empty memVO}">
                       <a class="nav-link " href="<%=request.getContextPath()%>/FrontLogoutHandler" tabindex="-1" aria-disabled="true"
                       		style="margin-top:4px">登出</a>
				   </c:if>
                   
                   <li class="nav-item">
                       <a class="nav-link" href="<%=request.getContextPath()%>/front/member/select_page.jsp"
                       	style="margin-top:4px">會員中心</a>
                   </li>
                   <li class="nav-item">
                       <!-- 下拉選單 -->
						<li class="nav-item dropdown">
					        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" 
					        	style="margin-top:4px"> 房客
					        </a>
					        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
					          <a class="dropdown-item" href="<%=request.getContextPath()%>/front/ele_contract/mem_listAll_ele_contract.jsp">我的合約列表</a>
					          <a class="dropdown-item" href="<%=request.getContextPath()%>/front/bill/mem_listAll_bill.jsp">我的帳單列表</a>
					          <a class="dropdown-item" href="<%=request.getContextPath()%>/front/pointgoods/good_record_check.jsp">我的積分紀錄</a>
					          <a class="dropdown-item" href="<%=request.getContextPath()%>/front/house_evaluate/mem_listAll_house_evaluate.jsp">我的評價紀錄</a>
					          <a class="dropdown-item" href="<%=request.getContextPath()%>/front/house_track/mem_house_track_listAll.jsp">我的最愛追蹤</a>
					          <a class="dropdown-item" href="<%=request.getContextPath()%>/front/appoint/listPart_memAppoint.jsp">預約看房行程表</a>
					        </div>
					    </li>
                   </li>
<%--                    <% if (lanVO != null && lanVO.getLan_accountstatus().equals("2")) {%> --%>
	                   <li class="nav-item">
	                       <li class="nav-item dropdown">
						        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
						        	style="margin-top:4px" >房東
						        </a>
						        <c:if test="${not empty lanVO }">
						        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
						          <a class="dropdown-item" href="<%=request.getContextPath()%>/front/ele_contract/lan_select_page.jsp">電子合約管理</a>
						          <a class="dropdown-item" href="<%=request.getContextPath()%>/front/house_evaluate/lan_listAll_evaluate.jsp">我的房屋評價</a>
						          <a class="dropdown-item" href="<%=request.getContextPath()%>/front/house/listAllHouse.jsp">我的所有房屋</a>
						          <a class="dropdown-item" href="<%=request.getContextPath()%>/front/house/addHouse.jsp">新增房屋</a>
						          <jsp:useBean id="houSvc" scope="page" class="com.goodhouse.house.model.HouseService" />
<%-- 						          <% if (houSvc.getOneByLanId(lanVO.getLan_id()) != null) --%>
<!-- 						          		{%> -->
						          <a class="dropdown-item" href="<%=request.getContextPath()%>/lanlordSetReserveDate_doGet.jsp">設定不可預約日期</a>
						          <a class="dropdown-item" href="<%=request.getContextPath()%>/front/houNoApp/listPart_lanHouNoApp.jsp">不可預約行程列表</a>
						          <a class="dropdown-item" href="<%=request.getContextPath()%>/front/appoint/listPart_lanAppoint.jsp">帶客看房行程表</a>
						          <a class="dropdown-item" href="<%=request.getContextPath()%>/front/rentMess/listPartRentMess.jsp">租屋問題回覆</a>
<%-- 						          <% } %> --%>
						        </div>
						        </c:if>
						    </li>
	                   </li>
<%-- 	                   <% } %> --%>
                   <li class="nav-item">
                       <li class="nav-item dropdown">
					        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" 
					        	style="margin-top:4px">積分商城
					        </a>
					        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
					          <a class="dropdown-item" href="<%=request.getContextPath()%>/front/pointgoods/listAllPointgoods.jsp">瀏覽積分商城</a>
					          <a class="dropdown-item" href="<%=request.getContextPath()%>/front/pointgoods/good_favorite_check.jsp">我的最愛積分商品</a>
					          <a class="dropdown-item" href="<%=request.getContextPath()%>/front/pointgoods/good_order_check.jsp">訂單查詢</a>
					        </div>
					    </li>
                   </li>
                   
 					<c:if test="${not empty memVO and empty lanVO}">
                       <li class="nav-item" style="margin-left:900px;margin-top:4px">
                           <p class="nav-link "  tabindex="-1" aria-disabled="true" >
	                           <img src="<%=request.getContextPath()%>/images/green_mem.png" style="width:25px;heigh:27px">
	                           <b style="color:#F74420 ; font-size:20px">${memVO.mem_name}</b>
                           </p>
                       </li>
                   	</c:if>  
                   	
                   	<c:if test="${not empty memVO and not empty lanVO}">
                       <li class="nav-item" style="margin-left:900px;margin-top:4px">
                           <p class=" "  tabindex="-1" aria-disabled="true">
	                           <img src="<%=request.getContextPath()%>/images/red_mem.png" style="width:25px;heigh:27px">
	                           <b style="color:#F74420 ; font-size:20px">${memVO.mem_name}</b>
                           </p>
                       </li>
                   	</c:if>                
                   
               </ul>
              
           </div>
	</header>
	<div style="height:120px;"></div>
<!-- Feader尾-->





</body>
</html>