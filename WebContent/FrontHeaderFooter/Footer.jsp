<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
/* 	footer{ */
/* 		position: fixed; */
/* 	    width: 100%; */
/* 	    z-index:999; */
/*  	    margin-top: -60px;  */
/* /*    		background-color: red; */
/* 	} */
.footer {
	position: fixed;
	height: 55px;
	bottom: 0;
	color: #180602;
	padding-top: 20px;
	width: 100%;
/* 	background: linear-gradient(to right,; */
	text-align: center;
	z-index: 9990;
	margin-top: -60px;
	box-shadow: 1rem 0.5rem rgba(0,0,0,.15)!important;
}

.navbar-fixed-bottom {
	bottom: 0;
	margin-bottom: 0;
	border-width: 1px 0 0;
}

.card-img {
	position: fixed;
	top: 0;
	left: 0;
	bottom: 0 px;
	right: 0;
	z-index: -999;
	min-height: 100%;
	width: 100%;
}
</style>
</head>
<body>
	<div style="margin-top: 100px"></div>

	<script>
		///////////////////////////慈慈推撥/////////////////////////////////
		// 		webSocket功能

		var eleConDone = "/EleConDoneWebSocket";
		var host1 = window.location.host;
		var path1 = window.location.pathname;
		var webCtx1 = path1.substring(0, path1.indexOf('/', 1));
		var endPointURL = "ws://" + host1 + webCtx1 + eleConDone;

		var webSocket1;

		function connect_bill() {
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

		function connect_poigoo() {
			// 新增積分商品
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

		function connect_hou() {

			// 建立 websocket 物件
			webSocket2 = new WebSocket(endPointURL2);

			webSocket2.onopen = function(event) {
			};

			webSocket2.onmessage = function(event) {
				var jsonObj = JSON.parse(event.data);
				swal(jsonObj.houMsgs);
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
	<!-- 	<div class="footer-banner__navi"> -->
	<div style="margin-top:20px"></div>
	<div>
		<img src="<%=request.getContextPath()%>/images/background2.jpg"
			class="card-img" alt="..." style="">
	</div>
	
	<footer class="footer text-center navbar-fixed-bottom"
		style="margin-bottom: 20px ; margin-top:10px" >
		<nav class="navbar navbar-light bg-light  bg-white rounded">
		<!-- 		<nav class="navbar navbar-expand-lg navbar-light bg-light shadow p-3 mb-5 bg-white rounded text-center"> -->
		<div class="container">
			<p>網站使用規範 | 服務免責聲明條款 | 租屋新聞連結</p>
			<br>
			<p style="margin-top: 0">© JAVA-CA106G2 平安住好宅</p>

		</div>
		<!-- 	    </nav> -->
		</nav>
	</footer>
	<!--     	</div> -->









	<!-- 	<div class="footer-banner__navi"> -->
	<!-- 	 		<footer class="container-fluid navbar-fixed-bottom " style="padding-bottom:10px"> -->
	<!-- <!-- 	 	<nav class="navbar navbar-expand-lg navbar-light bg-light shadow p-3 mb-5 bg-white rounded"> -->
	<!-- 	            <div class=" text-center" style="margin-bottom:0"> -->
	<!-- 	                <div class="row mt-3 dark-grey-text justify-content-center"> -->
	<!-- 	                    Grid column -->
	<!-- 	                    <div class=""> -->
	<!-- 	                    </div> -->
	<!-- 	                    <div class="col-3"> -->
	<!-- 	                        Content -->
	<!-- 	                        <h6 class="text-uppercase font-weight-bold" style="font-color:#808080">GOODHOUSE</h6> -->
	<!-- 	                        <p class="text-left text-secondary">Here you can use rows and columns here to organize your footer content. Lorem ipsum dolor sit amet, consectetur -->
	<!-- 	                            adipisicing elit.</p> -->
	<!-- 	                    </div> -->

	<!-- 	                    <div class="col-2" style="padding:0">  -->
	<!-- 						  <h6 class="text-uppercase font-weight-bold">ABOUT</h6> -->
	<!-- 				          <p><a class="text-left text-secondary" href="#!">網站使用規範</a></p> -->
	<!-- 				          <p><a class="text-left text-secondary" href="#!">服務免責聲明條款</a></p> -->
	<!-- 				          <p><a class="text-left text-secondary" href="#!">租屋新聞連結</a></p> -->

	<!-- 				        </div> -->

	<!-- 	                    <div class="col-2"> -->
	<!-- 	                        Links -->
	<!-- 	                        <h6 class="text-uppercase font-weight-bold">Contact</h6> -->
	<!-- 	                        <p><i class="text-left text-secondary"></i>Tibame</p> -->
	<!-- 	                        <p><i class="text-left text-secondary"></i> info@example.com</p> -->
	<!-- 	                        <p><i class="text-left text-secondary"></i> + 01 234 567 88</p> -->
	<!-- 	                    </div> -->
	<!-- 	                    <div class=""> -->

	<!-- 	                    </div> -->
	<!-- 	                    Grid column -->
	<!-- 	                </div> -->
	<!-- 	            </div> -->
	<!-- 	            <div class="footer-copyright text-center text-black-50 ">© JAVA-CA106G2 Copyright: -->
	<!-- 			      <a class="dark-grey-text" href="https://mdbootstrap.com/education/bootstrap/"> MDBootstrap.com</a> -->
	<!-- 			    </div> -->
	<!-- <!--         </nav> -->
	<!--         	</footer> -->
	<!-- 	</div> -->
	<!-- Optional JavaScript -->
	<script src="<%=request.getContextPath()%>/bootstrap/popper.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.js"
		type="text/javascript"></script>

	<!-- jQuery first, then Popper.js, then Bootstrap JS end-->
	<!-- Footer尾 -->


</body>
</html>