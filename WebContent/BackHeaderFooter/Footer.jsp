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
		background-color: #FDFEFE;
	}
	
	.card-img{
            position: fixed;
            top: 0;
            left: 0;
            bottom: 0;
            right: 0;
            z-index: -999;
            min-height: 100%;
            width: 100%;
            opacity: 0.2;
	}
</style>
</head>
<body onload="connect();" onunload="disconnect();">
	<div style="height:60px ;"  >
	</div>
	
	<!-- Footer頭 -->
	 <footer class="container-fluid" style="padding-bottom:10px">
	 <div>
		<img src="<%=request.getContextPath()%>/images/background2.jpg"
			class="card-img" alt="..." style="">
	</div>
	 
    </div>
            <div class=" text-center" style="margin-bottom:0">
                <div class="row mt-3 dark-grey-text justify-content-center">
                    <!-- Grid column -->
                    <div class="col-2 ">
                    </div>
                    <div class="col-3">
                        <!-- Content -->
                        <h6 class="text-uppercase font-weight-bold">GOODHOUSE</h6>
                    </div>
                    
                    <div class="col-2">
					  <h6 class="text-uppercase font-weight-bold">ABOUT</h6>
			
			        </div>
                
                    <div class="col-2">
                        <!-- Links -->
                        <h6 class="text-uppercase font-weight-bold">Contact</h6>
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
			<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.js" type="text/javascript"></script>
		<!-- jQuery first, then Popper.js, then Bootstrap JS end-->
	 <!-- Footer尾 -->
	 

</body>
</html>