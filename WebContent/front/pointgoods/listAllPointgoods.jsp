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
</style>
</head>
<body>
<jsp:include page="/FrontHeaderFooter/Header.jsp" />
<%@ include file="page1.file" %>
<div class="container">
	<div class="row">
		<div class="col-4">
			<jsp:include page="/FrontHeaderFooter/LeftList.jsp" />
<!-- 			<form action="pg.do" method="post"> -->
<!-- 				<input type="hidden" name="action" value="cart"> -->
				<a href="cart.jsp" class="btn btn-success" style="margin-left: 120px;margin-top: 30px;">前往購物車</a>
<!-- 				<input type="submit" value="前往購物車" class="btn btn-success" style="margin-left: 120px;margin-top: 30px;"> -->
<!-- 			</form> -->
		</div>
		<div class="col-8">
		<c:forEach var="pointgoodsVO" varStatus="s" items="${checklist}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<c:if test="${(s.count%3-1) == 0 }">
			<div class="row">
		</c:if>
			<div class="col-4">
				<div class="card">
					<img src="<%=request.getContextPath() %>/PointgoodsServlet?good_id=${pointgoodsVO.good_id}" class="card-img-top img-thumbnail" style="height:221px;" >
				</div>
				<div class="card-body" style="background-color: #EDF9DE !important;">
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
					<a href="listOnePointgoods.jsp?good_id=${pointgoodsVO.good_id}" class="btn btn-primary">查看詳情</a>
					<form method="post" action="pgc.do" id="carpost">
						<div class="row" style="padding:10px;">
							<div class="col-4">
								<input type='button' value='-' class="qtyminus btn btn-primary" field='good_nee${pointgoodsVO.good_id}' />
							</div>
							<div class="col-4">
						    	<input id="good_nee${s.index}" type='text' name='good_nee${pointgoodsVO.good_id}' value='0' class="form-control" readonly="readonly" />
						    </div>
						    <div class="col-4">
<!-- 						    	判斷購物車數量不能比存量還多 -->
							    <c:choose>
							    	<c:when test="${buylist != null}">
							    		<c:choose>
							    			<c:when test="${buylist.size() != 0 }">
							    				<c:choose>
								    				<c:when test="${buylist.indexOf(pointgoodsVO) >= 0}">
								    					<c:choose>
															<c:when test="${buylist.get(buylist.indexOf(pointgoodsVO)).good_nee <= buylist.get(buylist.indexOf(pointgoodsVO)).good_amo}">
																<input type='button' value='+' class="qtyplus btn btn-primary" field='good_nee${pointgoodsVO.good_id}' max='${buylist.get(buylist.indexOf(pointgoodsVO)).good_amo - buylist.get(buylist.indexOf(pointgoodsVO)).good_nee}'/>
															</c:when>
															<c:otherwise>
																<input type='button' value='+' class="qtyplus btn btn-primary" field='good_nee${pointgoodsVO.good_id}' max='${pointgoodsVO.good_amo}'/>
															</c:otherwise>
														</c:choose>
								    				</c:when>
								    				<c:otherwise>
									    				<input type='button' value='+' class="qtyplus btn btn-primary" field='good_nee${pointgoodsVO.good_id}' max='${pointgoodsVO.good_amo}'/>
									    			</c:otherwise>
						    					</c:choose>
							    			</c:when>
							    			<c:otherwise>
							    				<input type='button' value='+' class="qtyplus btn btn-primary" field='good_nee${pointgoodsVO.good_id}' max='${pointgoodsVO.good_amo}'/>
							    			</c:otherwise>
							    		</c:choose>
							    	</c:when>
							    	<c:otherwise>
									    <input type='button' value='+' class="qtyplus btn btn-primary" field='good_nee${pointgoodsVO.good_id}' max='${pointgoodsVO.good_amo}'/>
							    	</c:otherwise>
							    </c:choose>
						    </div>
					    </div>
<!-- 					    當購物車數量已經達到庫存量的時候要將加入購物車鎖住 -->
					    <c:choose>
						    <c:when test="${buylist != null }">
						    	<c:choose>
						    		<c:when test="${buylist.size() != 0 }">
						    			<c:choose>
						    				<c:when test="${buylist.indexOf(pointgoodsVO) >= 0}">
						    					<c:choose>
													<c:when test="${buylist.get(buylist.indexOf(pointgoodsVO)).good_nee == buylist.get(buylist.indexOf(pointgoodsVO)).good_amo}">
														<input id="${s.index}" type="button" value="加入購物車" class="btn btn-primary car" disabled>
													</c:when>
													<c:otherwise>
														<input id="${s.index}" type="button" value="加入購物車" class="btn btn-primary car">
													</c:otherwise>
												</c:choose>
						    				</c:when>
						    				<c:otherwise>
							    				<input id="${s.index}" type="button" value="加入購物車" class="btn btn-primary car">
							    			</c:otherwise>
						    			</c:choose>
							    	</c:when>
							    	<c:otherwise>
							    		<input id="${s.index}" type="button" value="加入購物車" class="btn btn-primary car">
							    	</c:otherwise>
						    	</c:choose>
							</c:when>
							<c:otherwise>
								<input id="${s.index}" type="button" value="加入購物車" class="btn btn-primary car">
							</c:otherwise>
						</c:choose>
					    <input type="hidden" name="good_id" value="${pointgoodsVO.good_id}">
					    <input type="hidden" name="whichPage" value="<%=whichPage%>">
					</form>
				</div>
			</div>
		<c:if test="${s.count%3 == 0 || s.last}">
			</div>
			<div style="height:10px;"></div>
		</c:if>
		</c:forEach>
		<%@ include file="page2.file" %>
		</div>
	</div>
</div>
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
		console.log(element.prev().prev().children().children().attr('value'));
		console.log(element.prev().prev().children().next().children().attr('value'));
		console.log(element.prev().prev().children().next().next().children().attr('value'));
		$.ajax({
			type: "POST",
			url: "pgc.do",
			data: {
				//$("#carpost").serialize(),
				"amount":$('#good_nee'+element.attr('id')).val(),
				"good_id":$(this).next().attr('value'),
				"action":"addCart"
				},
			dataType: "json",
			success: function(response){
				console.log(response.zero);
				if(response.zero == "true") {
					element.prop("disabled",true);
// 					element.prev().prev().children().children().prop("disabled",true);
				}
				if(response.max > 0) {
					console.log(response.max);
					console.log(element.prev().children().next().next().children().val());
					element.prev().children().next().next().children().attr('max', response.max);
				}
				$('#good_nee'+element.attr('id')).val(0);
				
// 				alert($('#qq'+element.attr('id')).val());
				swal("完成","成功加入收藏","success");
			},
// 			error: function(){alert("AJAX發生錯誤")}
			error: function (data, textStatus, jqXHR) {
				console.log('Ajax Error');
				console.log('data: ' + data);
		    	console.log('textStatus: ' + textStatus);
		        console.log('jqXHR: ' + jqXHR); }
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
	      $('input[name=' + fieldName + ']').val(0);
	    }
	  });
	  // This button will decrement the value till 0
	  $(".qtyminus").click(function(e) {
	    // Stop acting like a button
	    e.preventDefault();
	    // Get the field name
	    fieldName = $(this).attr('field');
	    // Get its current value
	    var currentVal = parseInt($('input[name=' + fieldName + ']').val());
	    // If it isn't undefined or its greater than 0
	    if (!isNaN(currentVal) && currentVal > 0) {
	      // Decrement one
	      $('input[name=' + fieldName + ']').val(currentVal - 1);
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