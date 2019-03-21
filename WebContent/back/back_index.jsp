<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<jsp:include page="/BackHeaderFooter/Header.jsp" />
	
	<div class="container">
	
		<div class="row" style="margin:15px">
		  <div class="col-6">
		    <div class="card">
		      <div class="card-body">
		        <h1 class="card-title">會員管理</h1>
		        <a href="<%=request.getContextPath()%>/back/member/listAllMem.jsp" class="btn btn-outline-success">會員列表</a>
		        <a href="<%=request.getContextPath()%>/back/landlord/listAllLan.jsp" class="btn btn-outline-success">房東列表</a>
		        <a href="<%=request.getContextPath()%>/back/landlord/listAllLan.jsp" class="btn btn-outline-success">審核房東資格</a>
		      </div>
		    </div>
		  </div>
 		  <div class="col-sm-6"> 
 		    <div class="card"> 
 		      <div class="card-body">
		        <h1 class="card-title">員工管理</h1> 
		        <a href="<%=request.getContextPath()%>/back/employee/listAllEmp.jsp" class="btn btn-outline-success display-1">員工列表</a>
		        <a href="<%=request.getContextPath()%>/back/employee/addEmp.jsp" class="btn btn-outline-success">新增員工</a>
 		      </div> 
 		    </div> 
 		  </div> 
		</div>
		
		<div class="row" style="margin:15px">
		  <div class="col-sm-6">
		    <div class="card">
		      <div class="card-body">
		        <h1 class="card-title">房屋管理</h1>
		        <a href="<%=request.getContextPath()%>/back/house/listAllHouse.jsp" class="btn btn-outline-success">房屋列表</a>
		      </div>
		    </div>
		  </div>
		  <div class="col-sm-6">
		    <div class="card">
		      <div class="card-body">
		        <h1 class="card-title">廣告管理</h1>
		        <a href="<%=request.getContextPath()%>/back/ad/listAllAd.jsp" class="btn btn-outline-success">廣告列表</a>
		      </div>
		    </div>
		  </div>
		</div>
		
		<div class="row" style="margin:15px">
			  <div class="col-sm-6">
			    <div class="card">
			      <div class="card-body">
			        <h1 class="card-title">電子合約管理</h1>
						<a href="<%=request.getContextPath()%>/back/contract/listAll_contract.jsp" class="btn btn-outline-success">合約分類管理</a>
						<a href="<%=request.getContextPath()%>/back/ele_contract/listAll_ele_contract.jsp" class="btn btn-outline-success">電子合約列表</a>
						<a href="<%=request.getContextPath()%>/back/bill/back_listAll_bill.jsp" class="btn btn-outline-success">租金帳單管理</a>
			      </div>
			    </div>
			  </div>
		  
			  <div class="col-sm-6">
			    <div class="card">
			      <div class="card-body">
			        <h1 class="card-title">積分商城管理</h1>
						<a href="<%=request.getContextPath()%>/back/pointgoods/listAllPointgoods.jsp" class="btn btn-outline-success">積分商品列表</a>
						<a href="<%=request.getContextPath()%>/back/pointgoods/addPointgoods.jsp" class="btn btn-outline-success">新增積分商品</a>
						<a href="<%=request.getContextPath()%>/back/pointgoods/good_ordManage.jsp" class="btn btn-outline-success">訂單管理</a>
					</div>
					  
			      </div>
			    </div>
			    
			</div>
		  
		</div>
	</div>
	
	<jsp:include page="/BackHeaderFooter/Footer.jsp" />
</body>
</html>