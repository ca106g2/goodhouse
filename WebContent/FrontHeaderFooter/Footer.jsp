<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
	footer{
		z-index:999;
		bottom:0px;
	}
	.text-center{
		background-color: #EDF9DE;
	}
</style>
</head>
<body>
	<div style="height:60px ;"  >
	</div>
	<!-- Footer頭 -->
	 <footer class="container-fluid">
            <div class=" text-center" style="margin-bottom:0">
                <div class="row mt-3 dark-grey-text justify-content-center">
                    <!-- Grid column -->
                    <div class="col-2 ">
                    	
                    </div>
                    <div class="col-3">
                        <!-- Content -->
                        <h6 class="text-uppercase font-weight-bold">GOODHOUSE</h6>
                        <!-- <hr class="teal accent-3  d-inline-block mx-auto" style="width: 60px;"> -->
                        <p class="text-left text-secondary">Here you can use rows and columns here to organize your footer content. Lorem ipsum dolor sit amet, consectetur
                            adipisicing elit.</p>
                    </div>
                    
                    <div class="col-2">
					  <h6 class="text-uppercase font-weight-bold">ABOUT</h6>
			          <p><a class="text-left text-secondary" href="#!">網站使用規範</a></p>
			          <p><a class="text-left text-secondary" href="#!">服務免責聲明條款</a></p>
			          <p><a class="text-left text-secondary" href="#!">租屋新聞連結</a></p>
			
			        </div>
                
                    <div class="col-2">
                        <!-- Links -->
                        <h6 class="text-uppercase font-weight-bold">Contact</h6>
                        <!-- <hr class="teal accent-3  d-inline-block mx-auto" style="width: 60px;"> -->
                        <p><i class="text-left text-secondary"></i>Tibame</p>
                        <p><i class="text-left text-secondary"></i> info@example.com</p>
                        <p><i class="text-left text-secondary"></i> + 01 234 567 88</p>
                        <!-- <p><i class="fas fa-print mr-3"></i> + 01 234 567 89</p> -->
                    </div>
                    <div class="col-2">
                    	
                    </div>
                    <!-- Grid column -->
                </div>
            </div>
            <div class="footer-copyright text-center text-black-50 py-3">© JAVA-CA106G2 Copyright:
		      <a class="dark-grey-text" href="https://mdbootstrap.com/education/bootstrap/"> MDBootstrap.com</a>
		    </div>
        </footer>
        <!-- Optional JavaScript -->
			<script src="<%=request.getContextPath()%>/bootstrap/popper.min.js"></script>
			<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
		<!-- jQuery first, then Popper.js, then Bootstrap JS end-->
	 <!-- Footer尾 -->
</body>
</html>