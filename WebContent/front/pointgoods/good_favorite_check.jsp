<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.* , com.goodhouse.member.model.*, com.goodhouse.favorite_good.model.*" %>
<%
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	String mem_id = memVO.getMem_id();
	Favorite_goodService favorite_goodSvc = new Favorite_goodService();
	List<Favorite_goodVO> favorite_list = favorite_goodSvc.getPart(mem_id);
	pageContext.setAttribute("favorite_list", favorite_list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的最愛</title>
<!--Style css-->
<link rel="stylesheet" href="<%=request.getContextPath()%>/front/pointgoods/select2/style.css">
</head>
<body>
<jsp:include page="/FrontHeaderFooter/Header.jsp" />
<div class="container">
	<div class="row">
		<div class="col-lg-12">
			<div class="card">
				<div class="card-header">
					<h4>我的最愛積分列表</h4>
				</div>
					<%if(favorite_list != null && (favorite_list.size() > 0)) {%>
						<div class="card-body p-0">
							<div class="table-responsive">
								<table class="table table-striped mb-0">
									<tr style="background-color:#EDF9DE">
										<td>積分商品名稱</td>
										<td>積分商品描述</td>
										<td>積分商品存量</td>
										<td>積分商品價格</td>
										<td>取消收藏</td>
									</tr>
									<jsp:useBean id="pointgoodsSvc" scope="page" class="com.goodhouse.pointgoods.model.PointgoodsService"/>
									<c:forEach var="favorite_goodVO" items="${favorite_list}">
					<!-- 				<form action="fg.do" method="post"> -->
										<tr>
											<td>${pointgoodsSvc.getOnePointgoods(favorite_goodVO.good_id).good_nam}</td>
											<td style="width:537.5px;">${pointgoodsSvc.getOnePointgoods(favorite_goodVO.good_id).good_dsc}</td>
											<td>${pointgoodsSvc.getOnePointgoods(favorite_goodVO.good_id).good_amo}</td>
											<td>${pointgoodsSvc.getOnePointgoods(favorite_goodVO.good_id).good_pri}</td>
											<td>
												<input type="button" value="移除" class="btn btn-primary heart" id="${favorite_goodVO.good_id}">
												<input type="hidden" name="good_id" value="${favorite_goodVO.good_id}">
												<input type="hidden" name="mem_id" value="${favorite_goodVO.mem_id}">
											</td>
					<!-- 						<input type="hidden" name="action" value="delete"> -->
										</tr>
					<!-- 				</form> -->
									</c:forEach>
								</table>
							</div>
						</div>
					<%} else { %>
					尚無我的最愛商品
					<% } %>
				</div>
			</div>
	</div>
</div>

<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
<script type="text/javascript">
	$(".heart").click(function(){
		var element = $(this);
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
				$('input[id=' + good_id + ']').parent().parent().remove();
			},
			error: function (data, textStatus, jqXHR) {
				console.log('Ajax Error');
				console.log('data: ' + data);
		    	console.log('textStatus: ' + textStatus);
		        console.log('jqXHR: ' + jqXHR); 
		    }
		});
	});
</script>
</body>
</html>