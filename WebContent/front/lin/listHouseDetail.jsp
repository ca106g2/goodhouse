<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.goodhouse.house.model.*"%>


<%
HouseVO houVO = (HouseVO) request.getAttribute("houVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HouseDetail</title>
<style type="text/css">

</style>
</head>
<body>
	<jsp:include page="/FrontHeaderFooter/Header.jsp" />
	<div class="container">
		<div class="row">
			<div class="col-12">
				<div class="row">
					<div class="col-sm-4">
						<div
							style="background-image: url(="<%=request.getContextPath() %>/HouseServlet?hou_id=${houVO.hou_id}&photo=1"); height: 300px; background-position: center; background-repeat: no-repeat; background-size: cover;">
						</div>
					</div>
					<div class="col-sm-4">
						<div
							style="background-image: url('house1.jpg'); height: 300px; background-position: center; background-repeat: no-repeat; background-size: cover;">
						</div>
					</div>
					<div class="col-sm-4">
						<div
							style="background-image: url('house1.jpg'); height: 300px; background-position: center; background-repeat: no-repeat; background-size: cover;">
						</div>
					</div>
				</div>
				<div class="row justify-content-end">
					<div class="col-sm-4"></div>
					<div class="col-sm-4" style="text-align: right;">
						<div class="price">
							<span>20,000</span><span>元/月</span>
						</div>
						<div class="addr">
							<span>桃園市中壢區五興路331巷43號</span>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-4" style="text-align: left;">
						<div class="addr">
							<span>房屋名稱：平價學生套房出租</span>
						</div>
						<div class="addr">
							<span>房屋編號：HOU0000001</span>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-3">
						<span class="lan">房東：</span> <span>中壢城伍</span>
					</div>
					<div class="col-sm-3"></div>
					<div class="col-sm-6">
						<div class="row">
							<div class="col-sm-6">
								<span class='type'>型別：</span> <span>套房</span>
							</div>
							<div class="col-sm-6">
								<span class='type'>坪數：</span> <span>30坪</span>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<span class='type'>產權：</span> <span>有</span>
							</div>
							<div class="col-sm-6">
								<span class='type'>車位：</span> <span>有</span>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<span class='type'>車位：</span> <span>有</span>
							</div>
							<div class="col-sm-6">
								<span class='type'>坪數：</span> <span>30坪</span>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12">
						<ul class="nav nav-tabs" role="tablist">
							<li class="nav-item"><a class="nav-link active"
								href="#profile" role="tab" data-toggle="tab" id="note">備註</a></li>
							<li class="nav-item"><a class="nav-link" href="#buzz"
								role="tab" data-toggle="tab">問與答</a></li>
							<li class="nav-item"><a class="nav-link" href="#references"
								role="tab" data-toggle="tab">預約行程</a></li>
						</ul>

						<!-- Tab panes -->
						<div class="tab-content">
							<div role="tabpanel" class="tab-pane fade in active" id="profile">
								<div class="myrow">限制學生入住</div>

							</div>
							<div role="tabpanel" class="tab-pane fade" id="buzz">問與答</div>
							<div role="tabpanel" class="tab-pane fade" id="references">
								預約行程</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">

	$(".change").click(function(){
		if(this.title == "加入收藏") {
			this.src = "<%=request.getContextPath()%>/images/heart_red.png";
			this.title = "取消收藏";
		} else {
				this.src = "<%=request.getContextPath()%>
		/images/heart_white.png";
								this.title = "加入收藏";
							}
						});

		$(function() {
			// This button will increment the value
			$('.qtyplus').click(
					function(e) {
						// Stop acting like a button
						e.preventDefault();
						// Get the field name
						fieldName = $(this).attr('field');
						// Get its current value
						var currentVal = parseInt($(
								'input[name=' + fieldName + ']').val());
						// If is not undefined
						if (!isNaN(currentVal)) {
							// Increment
							$('input[name=' + fieldName + ']').val(
									currentVal + 1);
						} else {
							// Otherwise put a 0 there
							$('input[name=' + fieldName + ']').val(0);
						}
					});
			// This button will decrement the value till 0
			$(".qtyminus").click(
					function(e) {
						// Stop acting like a button
						e.preventDefault();
						// Get the field name
						fieldName = $(this).attr('field');
						// Get its current value
						var currentVal = parseInt($(
								'input[name=' + fieldName + ']').val());
						// If it isn't undefined or its greater than 0
						if (!isNaN(currentVal) && currentVal > 0) {
							// Decrement one
							$('input[name=' + fieldName + ']').val(
									currentVal - 1);
						} else {
							// Otherwise put a 0 there
							$('input[name=' + fieldName + ']').val(0);
						}
					});
		});
	</script>
	<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
</body>
</html>