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
		
		  <div class="col-sm-4">
		    <div class="card text-center border-light mb-3">
			    <div class="col-auto">
			    	<img class="card-img-left" src="<%=request.getContextPath()%>/images/back_mem.png" alt="Card image cap" style="width:50%">
	            </div>
		      <div class="card-body">
		        <h1 class="card-title">會員管理</h1>
		        <a href="<%=request.getContextPath()%>/back/landlord/listAllLan.jsp" class="btn btn-outline-success btn-lg" style="margin-top:5px;font-weight:bold">審核房東資格</a><br>
		        <a href="<%=request.getContextPath()%>/back/member/listAllMem.jsp" class="btn btn-outline-success btn-lg" style="margin-top:5px;font-weight:bold">會員列表</a><br>
		        <a href="<%=request.getContextPath()%>/back/landlord/listAllLan.jsp" class="btn btn-outline-success btn-lg" style="margin-top:5px;font-weight:bold">房東列表</a><br>
		      </div>
		    </div>
		  </div>
		  
 		  <div class="col-sm-4"> 
 		    <div class="card text-center border-light mb-3">
 		    	<div class="col-auto">
			    	<img class="card-img-left" src="<%=request.getContextPath()%>/images/back_emp.png" alt="Card image cap" style="width:50%">
	            </div> 
 		      <div class="card-body">
		        <h1 class="card-title">員工管理</h1> 
		        <a href="<%=request.getContextPath()%>/back/employee/listAllEmp.jsp" class="btn btn-outline-success display-1 btn-lg" style="font-weight:bold">員工列表</a>
		        <a href="<%=request.getContextPath()%>/back/employee/addEmp.jsp" class="btn btn-outline-success btn-lg" style="font-weight:bold">新增員工</a>
 		      </div> 
 		    </div> 
 		  </div> 
 		  
		  <div class="col-sm-4">
		    <div class="card text-center border-light mb-3">
		    	<div class="col-auto">
			    	<img class="card-img-left" src="<%=request.getContextPath()%>/images/house2.png" alt="Card image cap" style="width:50%">
	            </div> 
		      <div class="card-body">
		        <h1 class="card-title">房屋管理</h1>
		        <a href="<%=request.getContextPath()%>/back/house/listAllHouse.jsp" class="btn btn-outline-success btn-lg" style="font-weight:bold">房屋列表</a>
		      </div>
		    </div>
		  </div>
 		  
		</div>
		
		<div class="row" style="margin:15px">
		
		  <div class="col-sm-4">
		    <div class="card text-center border-light mb-3">
		    	<div class="col-auto">
			    	<img class="card-img-left" src="<%=request.getContextPath()%>/images/ad.png" alt="Card image cap" style="width:50%;">
	            </div> 
		      <div class="card-body">
		        <h1 class="card-title">廣告管理</h1>
		        <a href="<%=request.getContextPath()%>/back/ad_sort/listAllAd_sort.jsp" class="btn btn-outline-success btn-lg" style="font-weight:bold">廣告類型列表</a>
		        <a href="<%=request.getContextPath()%>/back/ad_sort/addAd_sort.jsp" class="btn btn-outline-success btn-lg" style="margin-top:5px;font-weight:bold">廣告類型新增</a><br>
		        <a href="<%=request.getContextPath()%>/back/ad/listAllAd.jsp" class="btn btn-outline-success btn-lg" style="margin-top:5px;font-weight:bold">廣告列表</a><br>
		        <a href="<%=request.getContextPath()%>/back/ad_report/listAllAd_report.jsp" class="btn btn-outline-success btn-lg" style="margin-top:5px;font-weight:bold">廣告檢舉</a>
		      </div>
		    </div>
		  </div>
		  
			  <div class="col-sm-4">
			    <div class="card text-center border-light mb-3"> 	
			    
			    	<div class="col-auto">
			    		<img class="card-img-left" src="<%=request.getContextPath()%>/images/eleContract.png" alt="Card image cap" style="width:50%">
	            	</div> 
			      <div class="card-body">
			        	<h1 class="card-title">合約管理</h1>
						<a href="<%=request.getContextPath()%>/back/contract/listAll_contract.jsp" class="btn btn-outline-success btn-lg" style="font-weight:bold">合約分類管理</a>
						<a href="<%=request.getContextPath()%>/back/ele_contract/listAll_ele_contract.jsp" class="btn btn-outline-success btn-lg" style="margin-top:5px;font-weight:bold">電子合約列表</a>
						<a href="<%=request.getContextPath()%>/back/bill/back_listAll_bill.jsp" class="btn btn-outline-success btn-lg" style="margin-top:5px;font-weight:bold">租金帳單管理</a>
			      </div>
			      
			    </div>
			  </div>
		  
			  <div class="col-sm-4">
			    	<div class="card text-center border-light mb-3">
				    	<div class="col-auto">
				    		<img class="card-img-left" src="<%=request.getContextPath()%>/images/shop.png" alt="Card image cap" style="width:50%">
		            	</div> 
			      	<div class="card-body">
			        	<h1 class="card-title">積分商城管理</h1> 
						<a href="<%=request.getContextPath()%>/back/pointgoods/listAllPointgoods.jsp" class="btn btn-outline-success btn-lg" style="font-weight:bold">積分商品列表</a><br> 
						<a href="<%=request.getContextPath()%>/back/pointgoods/addPointgoods.jsp" class="btn btn-outline-success btn-lg" style="margin-top:5px;font-weight:bold">新增積分商品</a><br>
						<a href="<%=request.getContextPath()%>/back/pointgoods/good_ordManage.jsp" class="btn btn-outline-success btn-lg" style="margin-top:5px;font-weight:bold">訂單管理</a><br>
					</div>
			      </div>
			   </div>
			   
			</div>
	</div>
	
	<jsp:include page="/BackHeaderFooter/Footer.jsp" />
</body>
</html>