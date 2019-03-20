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
	
		<div class="row">
		  <div class="col-12">
		    <div class="card">
		      <div class="card-body">
		        <h5 class="card-title">會員/員工管理</h5>
		        <a href="#" class="btn btn-primary">會員</a>
		        <a href="#" class="btn btn-primary">員工</a>
		      </div>
		    </div>
		  </div>
<!-- 		  <div class="col-sm-6"> -->
<!-- 		    <div class="card"> -->
<!-- 		      <div class="card-body"> -->
<!-- 		        <h5 class="card-title"></h5> -->
<!-- 		      </div> -->
<!-- 		    </div> -->
<!-- 		  </div> -->
		</div>
		
		<div class="row">
		  <div class="col-sm-6">
		    <div class="card">
		      <div class="card-body">
		        <h5 class="card-title"></h5>
		        <a href="<%=request.getContextPath()%>/back/house/listAllHouse.jsp" class="btn btn-primary">房屋管理</a>
		      </div>
		    </div>
		  </div>
		  <div class="col-sm-6">
		    <div class="card">
		      <div class="card-body">
		        <h5 class="card-title"></h5>
		        <a href="<%=request.getContextPath()%>/back/ad/listAllAd.jsp" class="btn btn-primary">廣告管理</a>
		      </div>
		    </div>
		  </div>
		</div>
		
		<div class="row">
			  <div class="col-sm-6">
			    <div class="card">
			      <div class="card-body">
			        <h5 class="card-title"></h5>
			        
				        <div class="accordion" id="accordionExample">
						  <div class="card">
						    <div class="card-header" id="headingOne">
						      <h2 class="mb-0">
						        <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
						          	電子合約管理
						        </button>
						      </h2>
						    </div> 
						    <div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordionExample">
						      <div class="card-body">
						      		<a class="dropdown-item" href="<%=request.getContextPath()%>/back/contract/select_page.jsp">合約分類管理</a>
							        <a class="dropdown-item" href="<%=request.getContextPath()%>/back/ele_contract/select_page.jsp">電子合約列表</a>
							        <a class="dropdown-item" href="<%=request.getContextPath()%>/back/bill/back_select_page.jsp">租金帳單管理</a>
						      </div>
						    </div>
						  </div>
						</div>
					
			      </div>
			    </div>
			  </div>
		  
			  <div class="col-sm-6">
			    <div class="card">
			      <div class="card-body">
			        <h5 class="card-title"></h5>
					   
					  <div class="accordion" id="accordionExample2">
						  <div class="card">
						    <div class="card-header" id="headingOne2">
						      <h2 class="mb-0">
						        <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapseOne2" aria-expanded="true" aria-controls="collapseOne2">
						          	積分商城管理
						        </button>
						      </h2>
						    </div> 
						    <div id="collapseOne2" class="collapse show" aria-labelledby="headingOne2" data-parent="#accordionExample2">
						      <div class="card-body">
						      	  <a class="dropdown-item" href="<%=request.getContextPath()%>/back/pointgoods/listAllPointgoods.jsp">積分商品列表</a>
						          <a class="dropdown-item" href="<%=request.getContextPath()%>/back/pointgoods/addPointgoods.jsp">新增積分商品</a>
						          <a class="dropdown-item" href="<%=request.getContextPath()%>/back/pointgoods/good_ordManage.jsp">訂單管理</a>
						      </div>
						    </div>
						  </div>
					</div>
					  
			      </div>
			    </div>
			    
			  </div>
		  
		</div>
	</div>
	
	<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
</body>
</html>