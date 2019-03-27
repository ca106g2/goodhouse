<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.goodhouse.member.model.*"%>
<%@ page import="com.goodhouse.house_track.model.*"%>


<%
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	House_TrackService houTraSvc = new House_TrackService();
	List<House_TrackVO> list = houTraSvc.getListByMemId(memVO.getMem_id());
	Collections.reverse(list);
	pageContext.setAttribute("list", list);
%>

<!doctype html>
<html lang="en">
<head>

</head>
<body>
	<jsp:include page="/FrontHeaderFooter/Header.jsp" />
	<!-- 工作區開始 -->
	<div class="container">
		<div class="row justify-content-center">

			<jsp:useBean id="houSvc" scope="page"
				class="com.goodhouse.house.model.HouseService" />

			<div class="card">

				<div class="card-header">
					<h3>我的最愛追蹤</h3>
				</div>

				<div class="card-body">
					<div class="table-responsive">
						<table
							class="table table-bordered table-hover mb-0 text-nowrap text-center"
							style="font-size: 20px">
							<tbody>
								<tr style="background-color: #EDF9DE;">
									<th scope="col"></th>
									<th scope="col">房屋名稱</th>
									<th scope="col">房屋地址</th>
									<th scope="col">房屋租金</th>
									<th scope="col"></th>
								</tr>
								<%@ include file="page1.file"%>
								<c:forEach var="House_TrackVO" items="${list}"
									begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
									<tr class="houTra" id="${list.indexOf(House_TrackVO)}">

										<td><img class="cancle"
											src="<%=request.getContextPath()%>/front/lin/heart_red.png"
											title="取消追蹤"></td>

										<td>${houSvc.getOneHouse(House_TrackVO.hou_id).hou_name}</td>
										<td>${houSvc.getOneHouse(House_TrackVO.hou_id).hou_address}</td>
										<td>${houSvc.getOneHouse(House_TrackVO.hou_id).hou_rent}</td>
										<td>
											<!-- 				      			<input type="submit" class="cancle" value="取消追蹤" class="btn btn-outline-success "> -->

											<input id="hou_id" type="hidden"
											value="${House_TrackVO.hou_id}"> <input id="mem_id"
											type="hidden" value="${House_TrackVO.mem_id}"> <input
											type="hidden" name="action" value="delete">

											<form method="post" action="house_track.do">
												<input type="hidden" name="hou_id"
													value="${House_TrackVO.hou_id}"> <input
													type="hidden" name="action" value="getOne_For_look">
												<input type="submit" value="去看看"
													class="btn btn-outline-success " style="font-weight: bold">
											</form>
										</td>


									</tr>
								</c:forEach>
							</tbody>
						</table>
								<%@ include file="page2.file"%>
					</div>
				</div>
			</div>

		</div>
	</div>



	<!-- 工作區結束 -->
	<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
	<script>
		$(".cancle").click(function() {
			// 		var element = $(this);
			$.ajax({
				type : "POST",
				url : "house_track.do",
				data : {
					"hou_id" : $("#hou_id").attr('value'),
					"action" : "delete",
					"mem_id" : $("#mem_id").attr("value")
				},
				dataType : "json",

				success : function() {
					var houTraRa = $(".houTra");
					var index = houTraRa.attr('id');

					$('tr[id=' + index + ']').remove();

					swal("完成", "成功取消追蹤", "error");
				},
				error : function() {
					alert("AJAX發生錯誤")
				}
			});

		});
	</script>

</body>
</html>