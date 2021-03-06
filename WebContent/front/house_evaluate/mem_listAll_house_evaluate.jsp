<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.goodhouse.house_evaluate.model.*"%>
<%@ page import="com.goodhouse.member.model.*"%>

<%
	House_EvaluateService heSvc = new House_EvaluateService();
	List<House_EvaluateVO> list = heSvc.getAll();
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	Collections.reverse(list);
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>房屋評價-listAllHouse_Evaluate</title>
<style>
</style>


</head>
<body>
	<jsp:include page="/FrontHeaderFooter/Header.jsp" />

	<div class="container">
		<div class="row justify-content-center">


			<jsp:useBean id="houSvc" scope="page"
				class="com.goodhouse.house.model.HouseService" />
			<jsp:useBean id="mSvc" scope="page"
				class="com.goodhouse.member.model.MemService" />

			<div class="card">

				<div class="card-header">
					<h3>我給過的評價</h3>
				</div>

				<div class="card-body">
					<div class="table-responsive">
						<table
							class="table table-bordered table-hover mb-0 text-nowrap text-center"
							style="font-size: 20px">
							<tbody>
								<tr style="background-color: #EDF9DE;">
									<!-- 		      		<th scope="col">房屋評價編號</th> -->
									<!-- 		      		<th scope="col">評價者會員姓名</th> -->
									<th scope="col">房屋名稱</th>
									<th scope="col">評價等級</th>
									<th scope="col">評價內容</th>
								</tr>
								<%-- 		<%@ include file="page1.file" %> --%>
								<c:forEach var="House_EvaluateVO" items="${list}">
									<c:if test="${House_EvaluateVO.mem_id eq memVO.mem_id}">
										<tr>
											<%-- 		      		<th>${House_EvaluateVO.hou_eva_id}</th> --%>
											<%-- 		      		<td>${mSvc.getOneMem(House_EvaluateVO.mem_id).mem_name}</td> --%>
											<td>${houSvc.getOneHouse(House_EvaluateVO.hou_id).hou_name}</td>

											<td id="grade">${House_EvaluateVO.hou_eva_grade.substring(2)}</td>
<%-- 											<img src="<%=request.getContextPath()%>/front/lin/star.png"> --%>
											<!-- 											<td id="grade"><img -->
											<%-- 												src="<%=request.getContextPath()%>/front/lin/heart_red.png"></td> --%>

											<script> 
		      		 
		      		var gradeImg = "<img>";
		      		gradeImg.src="/front/lin/star.png";
		      		var grade = "${House_EvaluateVO.hou_eva_grade}";
		      		var account = grade.substr(1,1);
		      		
		      		console.log(account);
		      		
		      		window.onload = function (){
		      			
<%-- 		      				$('#grade').append('<img src="'<%=request.getContextPath()%>'/front/lin/heart_red.png">'); --%>
// 		      			if(account == 1 ){
// 		      			}else if(account == 2){
// 		      				$('#grade').append(gradeImg,gradeImg);
// 		      			}else if( account == 3){
// 		      				$('#grade').append(gradeImg,gradeImg,gradeImg);
// 		      			}else if( account == 4){
// 		      				$('#grade').append(gradeImg,gradeImg,gradeImg,gradeImg);
// 		      			}else {
// 		      				$('#grade').append(gradeImg,gradeImg,gradeImg,gradeImg,gradeImg);
// 		      			}
		      		
		      		
		      		
		      		}
		      		
		      		</script>

											<td>${House_EvaluateVO.hou_eva_content}</td>
										</tr>
									</c:if>
								</c:forEach>
							</tbody>
						</table>
					</div>

				</div>
			</div>

		</div>
	</div>
	<%-- 	<%@ include file="page2.file" %> --%>
	<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
</body>
</html>