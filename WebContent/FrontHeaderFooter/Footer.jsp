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
<body >
	<div style="height:60px ;"  >
	</div>
	
 	<script> 
///////////////////////////慈慈推撥/////////////////////////////////
// 		webSocket功能

 		var eleConDone = "/EleConDoneWebSocket";
		var host1 = window.location.host;
 	    var path1 = window.location.pathname;
 	    var webCtx1 = path1.substring(0, path1.indexOf('/', 1));
	    var endPointURL = "ws://" + host1 + webCtx1 + eleConDone;
		
	    var webSocket1;

		function connect_bill(){
			console.log(endPointURL);
			// 建立 websocket 物件
			webSocket1 = new WebSocket(endPointURL);
			
			webSocket1.onopen = function(event) {
			};
			
			webSocket1.onmessage = function(event) {
		        var jsonObj = JSON.parse(event.data);
		        swal(jsonObj.eleConDoneMsgs);
			};

			webSocket1.onclose = function(event) {
			};
		}
	
 	</script> 
	
 	<script>
///////////////////////////冠錩推撥/////////////////////////////////
// 		webSocket功能

		var addPoiGooDone = "/PointgoodsWebSocket";
		var host3 = window.location.host;
	    var path3 = window.location.pathname;
	    var webCtx3 = path3.substring(0, path3.indexOf('/', 1));
	    var endPointURL3 = "ws://" + host3 + webCtx3 + addPoiGooDone;
		
	    var webSocket3;

		function connect_poigoo(){
			// 新增積分商品
			console.log(endPointURL3);
			webSocket3 = new WebSocket(endPointURL3);
			
			webSocket3.onopen = function(event) {
			};
			
			webSocket3.onmessage = function(event) {
		        var jsonObj = JSON.parse(event.data);
		        swal(jsonObj.poiMsgs);
			};

			webSocket3.onclose = function(event) {
			};
		}
	
	</script> 
		
	<script>
///////////////////////////TIM推撥/////////////////////////////////
		<!--webSocket功能-->

		var houSocket = "/HouseWebSocket";
		var host2 = window.location.host;
	    var path2 = window.location.pathname;
	    var webCtx2 = path2.substring(0, path2.indexOf('/', 1));
	    var endPointURL2 = "ws://" + host2 + webCtx2 + houSocket;

	    var webSocket2;

		function connect_hou(){

			// 建立 websocket 物件
			console.log(endPointURL2);
			webSocket2 = new WebSocket(endPointURL2);

			webSocket2.onopen = function(event) {
			};
			
			webSocket2.onmessage = function(event) {
		        var jsonObj = JSON.parse(event.data);
		        alert(jsonObj.houMsgs);
			};

			webSocket2.onclose = function(event) {
			};
		}
	
		 function init() {
      		connect_bill();
      		connect_hou();
      		connect_poigoo();
   		 }

		window.onload = init;
	</script>	  
	
	<!-- Footer頭 -->
	 <footer class="container-fluid" style="padding-bottom:10px">
	 
    </div>
            <div class=" text-center" style="margin-bottom:0">
                <div class="row mt-3 dark-grey-text justify-content-center">
                    <!-- Grid column -->
                    <div class="col-2 ">
                    </div>
                    <div class="col-3">
                        <!-- Content -->
                        <h6 class="text-uppercase font-weight-bold">GOODHOUSE</h6>
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
                        <p><i class="text-left text-secondary"></i>Tibame</p>
                        <p><i class="text-left text-secondary"></i> info@example.com</p>
                        <p><i class="text-left text-secondary"></i> + 01 234 567 88</p>
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