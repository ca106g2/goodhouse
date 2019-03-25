<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.goodhouse.ele_contract.model.*"%>
<%
	Ele_ContractVO eleConVO = (Ele_ContractVO) session.getAttribute("eleConVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

</head>
<body>
	<jsp:include page="/FrontHeaderFooter/Header.jsp" />

	<div class="container-fluid">
	
		<div class="row">
		
			<div class="col-xs-12">
				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
			</div>

			<jsp:useBean id="memSvc" scope="page"
				class="com.goodhouse.member.model.MemService"></jsp:useBean>
			<jsp:useBean id="houSvc" scope="page"
				class="com.goodhouse.house.model.HouseService"></jsp:useBean>
		
			<div class="container">
				<div class="row">
				
<!-- 					<div class="card"> -->
<!-- 						<div class="card-header"> -->
							<h3>填寫繳費資料</h3>
<!-- 						</div> -->
					
<!-- 						<div class="card-body"> -->
						
							<div class="col" style="font-size:18px">
								<form method="post" action="bill.do">
			
									<div>
										<b><p>會員姓名</p></b>
										<p style="color:#3498DB" class="text-center">${memSvc.getOneMem(eleConVO.mem_id).mem_name}</p>
									</div>
			
									<div>
										<b ><p>房屋地址</p></b>
										<p style="color:#3498DB">${houSvc.getOneHouse(eleConVO.hou_id).hou_address}</p>
									</div>
			
									<div>
										<b><p>第一期繳交費用(租金+押金)</p></b>
										<p style="color:#3498DB">${eleConVO.ele_rent_money + eleConVO.ele_deposit_money}</p>
									</div>
									
									<div>
										<b><p>繳費型態</p></b>
										<c:forEach var="Bill_PaymentType" items="${Bill_PaymentTypeMap}">
											<c:if
												test="${Bill_PaymentType.key eq eleConVO.bill_paymenttype}">
												<p style="color:#3498DB">${Bill_PaymentType.value.type_name}</p>
											</c:if>
										</c:forEach>
									</div>
									
									<div class="small-6 ">
										<b>請輸入信用卡號碼</b><br> 
										<input id="cn1" style="height: 50px" class="btn btn-light"
											type="text" name="card_number1" onblur="checkCreditCard(this)"
											size="4" maxlength="4" placeholder="XXXX" tabindex="3">
										- 
										<input id="cn2" style="height: 50px" type="text" class="btn btn-light"
											name="card_number2" onblur="checkCreditCard(this)" size="4"
											maxlength="4" placeholder="XXXX" tabindex="3"> 
										- 
										<input id="cn3" style="height: 50px" type="text" name="card_number3"
											onblur="checkCreditCard(this)" size="4" maxlength="4"
											placeholder="XXXX" tabindex="3" class="btn btn-light">
										- 
										<input id="cn4" style="height: 50px" type="text" name="card_number4"
											onblur="checkCreditCard(this)" size="4" maxlength="4"
											placeholder="XXXX" tabindex="3" class="btn btn-light">
									</div>
									
									<div class="small-3 ">
										<b>請輸入有效日期</b><br> 
										<input id="vm" type="text" name="mm" min="01"
											max="12" size="2" maxlength="2" tabindex="3" value=""
											placeholder="MM" class="btn btn-light"> 
										/ 
										<input id="vy" type="text" name="yy"
											class="tt1 btn btn-light" min="17" max="27" size="2" maxlength="2"
											tabindex="3" value="" placeholder="YY" >
									</div>
			
									<div class="small-3 ">
										<b>請輸入背面卡號 </b>
										<input type="text" id="switch" name="cvc" size="36"
											class="bcn form-control input-lg btn btn-light" tabindex="3" value=""
											placeholder="CVC">
									</div>
									
									<input class="btn btn-outline-success" type="submit" value="確認付款" >
									
								</div>
			
								<div class="col" id="container" >
									<div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-2" style="display:inline;">
										<img id="image1" src="<%=request.getContextPath()%>/images/postive.png" style="position:absolute;"/> 
										<img id="image2" src="<%=request.getContextPath()%>/images/negitive.png" style="position:absolute;"/>
									</div>
								</div>
			
			
								<%
									java.sql.Date bill_date = new java.sql.Date(System.currentTimeMillis());
									java.sql.Date bill_producetime = new java.sql.Date(System.currentTimeMillis());
								%>
								
								<input type="hidden" name="action" value="creatFirstBill">
								
								<input type="hidden" name="ele_con_id"
									value="${eleConVO.ele_con_id}"> <input type="hidden"
									name="bill_pay"
									value="${eleConVO.ele_rent_money + eleConVO.ele_deposit_money}">
									
								<input type="hidden" name="bill_date" value="<%=bill_date%>">
								<input type="hidden" name="bill_producetime" value="<%=bill_producetime%>"> 
								<input type="hidden" name="bill_status" value="s3"> 
								<input type="hidden" name="bill_paymethod" value="VISACard"> 
								<input type="hidden" name="bill_paymenttype" value="${eleConVO.bill_paymenttype}"> 
								
								
							</form>
<!-- 						</div> -->
					
<!-- 					</div> -->
				</div>
				
			</div>

		</div>
	</div>
	<script>
		
	</script>

	<jsp:include page="/FrontHeaderFooter/Footer.jsp"></jsp:include>


</body>

<script>

	var margin = $("#image1").width() / 2;
	var width = $("#image1").width();
	var height = $("#image1").height();
	
	$("#image2").css({
		width : '0px',
		height : height + 'px',
		marginLeft : margin + 'px',
		opacity : '0.5'
	});
	
	$(document).ready(function() {
		
		$('#switch').focus(function() {
			
			$("#image1").stop().animate({
				width : '0px',
				height : height + 'px',
				marginLeft : margin + 'px',
				opacity : '0.5'
				
			}, 500);
			
			window.setTimeout(function() {
				
				$("#image2").stop().animate({
					width : width + 'px',
					height : height + 'px',
					marginLeft : '0px',
					opacity : '1'
				}, 500);
			}, 500)
		})
		
		$('#switch').blur(function() {
			
			$("#image2").stop().animate({
				width : '0px',
				height : height + 'px',
				marginLeft : margin + 'px',
				opacity : '0.5'
			}, 500);
			
			window.setTimeout(function() {
				
				$("#image1").stop().animate({
					width : width + 'px',
					height : height + 'px',
					marginLeft : '0px',
					opacity : '1'
				}, 500);
			}, 500);
			
		})
	});

	//驗證信用卡
	function checkCreditCard(control) {
		
		re2 = /^\d{4}$/;
		if (!re2.test(control.value))
			swal('Oops !', '你輸入的信用卡號有誤', 'error');
	}

</script>
</html>