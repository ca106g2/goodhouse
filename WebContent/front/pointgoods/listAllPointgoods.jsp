<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.stream.Collector"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>
<%@ page import="com.goodhouse.pointgoods.model.*" %>
<%@ page import="com.goodhouse.member.model.*" %>
<%@ page import="com.goodhouse.member.model.*" %>

<%
	PointgoodsService pointgoodsSvc = new PointgoodsService();
	List<PointgoodsVO> list = pointgoodsSvc.getAll();
	List<PointgoodsVO> checklist = new LinkedList<PointgoodsVO>();
	List<PointgoodsVO> buylist = (List<PointgoodsVO>) session.getAttribute("shoppingcart");
	//判斷是否為上架商品
	for(PointgoodsVO pointgoodsVO : list) {
		if(pointgoodsVO.getGood_sta().equals("P001")) {
			checklist.add(pointgoodsVO);
		}
	}
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	pageContext.setAttribute("memVO", memVO);
	pageContext.setAttribute("checklist",checklist);
	
	if(buylist != null) {
		pageContext.setAttribute("buylist", buylist);
		int countCar = buylist.stream().collect(Collectors.summingInt(PointgoodsVO::getGood_nee));
		pageContext.setAttribute("countCar", countCar);
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pointgoods</title>
<style type="text/css">
	.page-item:first-child .page-link,
    .page-item:last-child .page-link{
        border-radius: 0;
    }
    .page-link{
        margin-left: 6px;
        margin-right: 6px;
    }
    .list-group-item{
    	padding-top: 30px;
    	padding-bottom: 30px;
    }
    .CarCar{
        position: fixed;
    right: 4%;
    top: 16%;
    z-index: 994;
    }
   body .modal-backdrop {
    	z-index: 10000;
    }
</style>
<link href="select2/pe-icon-7-stroke.css" rel="stylesheet" />
<!-- <link href="select2/ct-navbar.css" rel="stylesheet" /> -->
</head>
<body>
<jsp:include page="/FrontHeaderFooter/Header.jsp" />
<%@ include file="page1.file" %>
<div class="container">
	<div class="row" style="float: right;">
		<div class="CarCar">
		<a href="goc.do?action=carCheck" style="margin-left: 120px;margin-top: 30px;">
		    <img src="<%=request.getContextPath() %>/front/pointgoods/images/Shopping_cart.gif" style="">
		</a>
<%-- 		<c:if test="${not empty countCar}"> --%>
			<i style="position: absolute;right: -10%;top: -15%;z-index: 994;background-color: #e94b35;border-radius: 999em;width: 37px;height: 37px;">
				 <span class="label" style="margin-left: 5px;color: #ffffff;font-size: 1.5rem;font-weight: bold;" id="carCount">${countCar}</span>
			</i>		
<%-- 		</c:if> --%>
		</div>
	</div>
	<div class="row">
<!-- 		<div class="col-4"> -->
<%-- 			<jsp:include page="/FrontHeaderFooter/LeftList.jsp" /> --%>
<%-- 				<a href="goc.do?action=carCheck" style="margin-left: 120px;margin-top: 30px;" class="btn btn-success">前往購物車<img src="<%=request.getContextPath() %>/front/pointgoods/images/Shopping_cart.gif"></a> --%>
<!-- 		</div> -->
		<div class="col-12">
		<c:forEach var="pointgoodsVO" varStatus="s" items="${checklist}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<c:if test="${(s.count%4-1) == 0 }">
			<div class="row">
		</c:if>
			<div class="col-3">
				<div class="card">
					<img src="<%=request.getContextPath() %>/PointgoodsServlet?good_id=${pointgoodsVO.good_id}" class="card-img-top img-thumbnail" style="height:253.02px;" >
				</div>
				<div class="card-body" style="background-color: white !important;">
					<jsp:useBean id="favorite_goodSvc" scope="page" class="com.goodhouse.favorite_good.model.Favorite_goodService"></jsp:useBean>
					<c:if test="${memVO != null}">
						<img src=" ${favorite_goodSvc.getOneFavorite_good(memVO.mem_id, pointgoodsVO.good_id)!= null?'images/heart_red.png':'images/heart_white.png'}"
							class='heart' title="${favorite_goodSvc.getOneFavorite_good(memVO.mem_id, pointgoodsVO.good_id) != null ? '取消收藏' : '加入收藏' }"
							alt="${favorite_goodSvc.getOneFavorite_good(memVO.mem_id, pointgoodsVO.good_id) != null ? 'favorite' : 'unfavorite' }"
							id="${pointgoodsVO.good_id}" style="float:right" width="25px" height="22px" >
						<input type="hidden" name="good_id" value="${pointgoodsVO.good_id}">
						<input type="hidden" name="mem_id" value="${memVO.mem_id}">
					</c:if>					
					<h5 class="card-title">${pointgoodsVO.good_nam}</h5>
					<p class="card-text">$${pointgoodsVO.good_pri}</p>
					<a href="listOnePointgoods.jsp?good_id=${pointgoodsVO.good_id}" class="btn btn-outline-success">查看詳情</a>
					<form method="post" action="pgc.do" id="carpost">
						<div class="row" style="padding:10px;">
								<input type='button' value='-' class="qtyminus btn btn-outline-success" field='good_nee${pointgoodsVO.good_id}' style="width:36.91px;" />
						    	<input id="good_nee${s.index}" type='text' name='good_nee${pointgoodsVO.good_id}' value='1' class="form-control" readonly="readonly" style="width: 110px;text-align: center;" />
							    <c:choose>
							    	<c:when test="${buylist != null}">
							    		<c:choose>
							    			<c:when test="${buylist.size() != 0 }">
							    				<c:choose>
								    				<c:when test="${buylist.indexOf(pointgoodsVO) >= 0}">
								    					<c:choose>
															<c:when test="${buylist.get(buylist.indexOf(pointgoodsVO)).good_nee <= buylist.get(buylist.indexOf(pointgoodsVO)).good_amo}">
																<input type='button' value='+' class="qtyplus btn btn-outline-success" field='good_nee${pointgoodsVO.good_id}' max='${buylist.get(buylist.indexOf(pointgoodsVO)).good_amo - buylist.get(buylist.indexOf(pointgoodsVO)).good_nee}'/>
															</c:when>
															<c:otherwise>
																<input type='button' value='+' class="qtyplus btn btn-outline-success" field='good_nee${pointgoodsVO.good_id}' max='${pointgoodsVO.good_amo}'/>
															</c:otherwise>
														</c:choose>
								    				</c:when>
								    				<c:otherwise>
									    				<input type='button' value='+' class="qtyplus btn btn-outline-success" field='good_nee${pointgoodsVO.good_id}' max='${pointgoodsVO.good_amo}'/>
									    			</c:otherwise>
						    					</c:choose>
							    			</c:when>
							    			<c:otherwise>
							    				<input type='button' value='+' class="qtyplus btn btn-outline-success" field='good_nee${pointgoodsVO.good_id}' max='${pointgoodsVO.good_amo}'/>
							    			</c:otherwise>
							    		</c:choose>
							    	</c:when>
							    	<c:otherwise>
									    <input type='button' value='+' class="qtyplus btn btn-outline-success" field='good_nee${pointgoodsVO.good_id}' max='${pointgoodsVO.good_amo}'/>
							    	</c:otherwise>
							    </c:choose>
<!-- 					    當購物車數量已經達到庫存量的時候要將加入購物車鎖住 -->
							<c:choose>
							    <c:when test="${buylist != null }">
							    	<c:choose>
							    		<c:when test="${buylist.size() != 0 }">
							    			<c:choose>
							    				<c:when test="${buylist.indexOf(pointgoodsVO) >= 0}">
							    					<c:choose>
														<c:when test="${buylist.get(buylist.indexOf(pointgoodsVO)).good_nee == buylist.get(buylist.indexOf(pointgoodsVO)).good_amo}">
															<img src="<%=request.getContextPath() %>/front/pointgoods/images/sCar_full.png" style="width: 40px;height: 40px;" title="購物車已滿" class="car" id="${s.index}" name="car${pointgoodsVO.good_id}">
														</c:when>
														<c:otherwise>
															<img src="<%=request.getContextPath() %>/front/pointgoods/images/sCar.png" style="width: 40px;height: 40px;" title="加入購物車" class="car" id="${s.index}" name="car${pointgoodsVO.good_id}">
														</c:otherwise>
													</c:choose>
							    				</c:when>
							    				<c:otherwise>
							    					<img src="<%=request.getContextPath() %>/front/pointgoods/images/sCar.png" style="width: 40px;height: 40px;" title="加入購物車" class="car" id="${s.index}" name="car${pointgoodsVO.good_id}">
								    			</c:otherwise>
							    			</c:choose>
								    	</c:when>
								    	<c:otherwise>
								    		<img src="<%=request.getContextPath() %>/front/pointgoods/images/sCar.png" style="width: 40px;height: 40px;" title="加入購物車" class="car" id="${s.index}" name="car${pointgoodsVO.good_id}">
								    	</c:otherwise>
							    	</c:choose>
								</c:when>
								<c:otherwise>
									<img src="<%=request.getContextPath() %>/front/pointgoods/images/sCar.png" style="width: 40px;height: 40px;" title="加入購物車" class="car" id="${s.index}" name="car${pointgoodsVO.good_id}">
								</c:otherwise>
							</c:choose>
						    <input type="hidden" name="good_id" value="${pointgoodsVO.good_id}">
						    <input type="hidden" name="whichPage" value="<%=whichPage%>">
					    </div>
					    
					</form>
				</div>
			</div>
		<c:if test="${s.count%4 == 0 || s.last}">
			</div>
			<div style="height:10px;"></div>
		</c:if>
		</c:forEach>
		<%@ include file="page2.file" %>
		</div>
	</div>
</div>
<c:if test="${openModal!=null}">
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" style="z-index: 10001;">
  <div class="modal-dialog" role="document" style="max-width:939px;">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">購物清單</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <jsp:include page="cart.jsp" />
      </div>
<!--       <div class="modal-footer"> -->
      	
<!--       </div> -->
    </div>
  </div>
</div>

<script>
	$("#exampleModal").modal('show');
</script>
</c:if>
<script type="text/javascript">
	// 我的最愛
	$(".heart").click(function(){
			var element = $(this);
		if($(this).attr("alt") == "unfavorite") {
			$.ajax({
				type: "POST",
				url: "fg.do",
				data: {
					"good_id":$(this).next().attr('value'),
					"action":"insert",
					"mem_id":$(this).next().next().attr("value")
					},
				dataType: "json",
				success: function(){
					good_id = element.attr('id');
					$('img[id=' + good_id + ']').attr({
						"src":"images/heart_red.png",
						"title": "取消收藏",
						"alt": "favorite"
					});
					swal("完成","成功加入收藏","success");
				},
				error: function(){alert("AJAX發生錯誤")}
			});
		} else if ($(this).attr("alt") == "favorite") {
			
			$.ajax({
				type: "POST",
				url: "fg.do",
				data: {
					"good_id":$(this).next().attr('value'),
					"action":"delete",
					"mem_id":$(this).next().next().attr("value")
					},
				dataType: "json",
				success: function(){
					good_id = element.attr('id');
					$('img[id=' + good_id + ']').attr({
						"src": "images/heart_white.png",
						"title": "加入收藏",
						"alt": "unfavorite"						
					});
					swal("完成","成功取消收藏","error");
				},
				error: function(){alert("AJAX發生錯誤")}
			});
		}
	});
	//加入購物車
	$(".car").click(function(){
		var element = $(this);
		var carCount;
		if($("#carCount").text() === "") {
			carCount = 0;
		} else {
			carCount = parseInt($("#carCount").text());
		}
		var amount = parseInt($('#good_nee'+element.attr('id')).val());
// 		console.log($("#carCount").text());
// 		console.log(carCount);
		$.ajax({
			type: "POST",
			url: "pgc.do",
			data: {
				"amount":$('#good_nee'+element.attr('id')).val(),
				"good_id":$(this).next().attr('value'),
				"action":"addCart"
				},
			dataType: "json",
			success: function(response){
				if(response.zero == "true") {
					$('img[id=' + element.attr('id') + ']').attr({
						"src": "images/sCar_full.png",
						"title": "商品已滿",
						"class": "none"
					});
					swal("完成","成功加入購物車","success");
					$('#good_nee'+element.attr('id')).val(1);
					element.prev().attr('max', 1);
					carCount = carCount + amount;
// 					console.log(carCount);
					$("#carCount").text(carCount);
				}
				if(response.max > 0) {
					element.prev().attr('max', response.max);
					swal("完成","成功加入購物車","success");
					$('#good_nee'+element.attr('id')).val(1);
					carCount = carCount + amount;
// 					console.log(carCount);
					$("#carCount").text(carCount);
				}
				if(response.full == "true") {
					swal("滿了","購物車已滿","error");
				}
			},
			error: function (data, textStatus, jqXHR) {
				console.log('Ajax Error');
				console.log('data: ' + data);
		    	console.log('textStatus: ' + textStatus);
		        console.log('jqXHR: ' + jqXHR); }
		});
	});
	
	// 刪除購物車
	$(".cardel").click(function(){
		var element = $(this);
		var carCount = parseInt($("#carCount").text());
		$.ajax({
			type: "POST",
			url: "pgc.do",
			data: {
				"del":$(this).next().attr('value'),
				"removegood_id":$(this).next().next().attr('value'),
				"action":"deletecart"
				},
			dataType: "json",
			success: function(response){
				good_idcar = element.attr('id');
				console.log(response.carZero);
				if(response.carZero == "true") {
					$('input[id=' + good_idcar + ']').parent().parent().parent().remove();
					$('#shopcartb').before("尚無選購商品");
					$('#checkoutcar').remove();			
				}
				carCount = carCount - parseInt(response.amount);
				$("#carCount").text(carCount);
				$('input[id=' + good_idcar + ']').parent().parent().remove();
				//將商城圖片以及數量修改回原本的樣子
				$('img[name=car'+ response.removegood_id +']').attr({
					"src": "images/sCar.png",
					"title": "加入購物車",
					"class": "car"
				});
				$('input[field=good_nee'+ response.removegood_id +']').attr({
					"max":response.removeMax
				});
			},
			error: function (data, textStatus, jqXHR) {
				console.log('Ajax Error');
				console.log('data: ' + data);
		    	console.log('textStatus: ' + textStatus);
		        console.log('jqXHR: ' + jqXHR); 
		    }
		});
	});
	
	$(function() {
	  // This button will increment the value
	  $('.qtyplus').click(function(e) {
	    // Stop acting like a button
	    e.preventDefault();
	    // Get the field name
	    fieldName = $(this).attr('field');
	    max = $(this).attr('max');
	    // Get its current value
	    var currentVal = parseInt($('input[name=' + fieldName + ']').val());
	    // If is not undefined
	    if (!isNaN(currentVal) && currentVal < max) {
	      // Increment
	      $('input[name=' + fieldName + ']').val(currentVal + 1);
	    } else {
	      // Otherwise put a 0 there
	      $('input[name=' + fieldName + ']').val(1);
	    }
	  });
	  // This button will decrement the value till 0
	  $(".qtyminus").click(function(e) {
	    // Stop acting like a button
	    e.preventDefault();
	    // Get the field name
	    fieldName = $(this).attr('field');
	    min = 1;
	    // Get its current value
	    var currentVal = parseInt($('input[name=' + fieldName + ']').val());
	    // If it isn't undefined or its greater than 0
	    if (!isNaN(currentVal) && currentVal > min) {
	      // Decrement one
	      $('input[name=' + fieldName + ']').val(currentVal - 1);
	    } else {
	      // Otherwise put a 0 there
	      $('input[name=' + fieldName + ']').val(1);
	    }
	  });
	});
</script>
<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
<script src="select2/ct-navbar.js"></script>
</body>
</html>