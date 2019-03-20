<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.goodhouse.member.model.*"%>
<%@ page import="com.goodhouse.house_track.model.*"%>


<%
	MemVO memVO = (MemVO)session.getAttribute("memVO");
	House_TrackService houTraSvc = new House_TrackService();
	List<House_TrackVO> list = houTraSvc.getListByMemId(memVO.getMem_id());
	pageContext.setAttribute("list",list);
%>

<!doctype html>
<html lang="en">
<head>

</head>
<body>
<jsp:include page="/FrontHeaderFooter/Header.jsp" />
	<!-- 工作區開始 -->
	<div class="container">
		<div class="row">
			<div class="row col-12">
				<c:if test="${not empty errorMsgs}">
					<font style="color: red"></font>
						<c:forEach var="message" items="${errorMsgs}">
							<p style="color: red">${message}</p><br>
						</c:forEach>
				</c:if>
				<table class="table table-hover">
				  	<jsp:useBean id="houSvc" scope="page" class="com.goodhouse.house.model.HouseService"/>
					<thead>
				    	<tr>
				      		<th scope="col">房屋名稱</th>
				      		<th scope="col">房屋地址</th>
				      		<th scope="col">房屋租金</th>
				      		<th scope="col"></th>
				    	</tr>
				  	</thead>
					<tbody>	
					<%@ include file="page1.file" %>
						<c:forEach var="House_TrackVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				    	<tr class="houTra" id="${list.indexOf(House_TrackVO)}" >
				      		<td>${houSvc.getOneHouse(House_TrackVO.hou_id).hou_name}</td>
				      		<td>${houSvc.getOneHouse(House_TrackVO.hou_id).hou_address}</td>
				      		<td>${houSvc.getOneHouse(House_TrackVO.hou_id).hou_rent}</td>
				      		<td>
				      			<input type="submit" class="cancle" value="取消追蹤">
				      			<input type="hidden" name="hou_id"  value="${House_TrackVO.hou_id}">
				      			<input type="hidden" name="mem_id"  value="${House_TrackVO.mem_id}">
				     			<input type="hidden" name="action" value="delete">
				      		</td>
				    	</tr>	
				    	</c:forEach>
				    <%@ include file="page2.file" %>
				  	</tbody>
				</table>
			</div>
		</div>
	</div>



	<!-- 工作區結束 -->
<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
<script>
	$(".cancle").click(function(){
		var element = $(this);
		$.ajax({
			type: "POST",
			url: "house_track.do",
			data: {
				"hou_id":$(this).next().attr('value'),
				"action":"delete",
				"mem_id":$(this).next().next().attr("value")
				},
			dataType: "json",
			
			success: function(){
				var houTraRa = $(".houTra");
				var index = houTraRa.attr('id');
				
				$('tr[id=' + index + ']').remove();
				
				swal("完成","成功取消追蹤","error");
			},
			error: function(){alert("AJAX發生錯誤")}
		});
		
	});


</script>

</body>
</html>