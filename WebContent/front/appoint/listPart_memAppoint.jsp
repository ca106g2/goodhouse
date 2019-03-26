<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>
<%@ page import="com.goodhouse.appointment.model.*" %>
<%@ page import="com.goodhouse.member.model.*" %>

<%
    MemVO memVO = (MemVO) session.getAttribute("memVO");
	AppointService appointSvc = new AppointService();
    List<AppointVO> list = appointSvc.getPartMem(memVO.getMem_id());
// 	String mem_id = "M000000001";
//     List<AppointVO> list = appointSvc.getPartMem(mem_id);
    pageContext.setAttribute("list",list);
%>

<%-- <jsp:useBean id="list" scope="session" type="java.util.List<AppointVO>"/> --%>
<jsp:useBean id="memSvc" scope="page" class="com.goodhouse.member.model.MemService" />
<jsp:useBean id="lanSvc" scope="page" class="com.goodhouse.landlord.model.LanService" />
<jsp:useBean id="houSvc" class="com.goodhouse.house.model.HouseService" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List Part MemAppoint.</title>
<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>
</head>
<body bgcolor="white">
<jsp:include page="/FrontHeaderFooter/Header.jsp" />


<div class="container">

		<div class="row">
			<div class="col-2">

				<form method="post"
					action="<%=request.getContextPath() %>/front/index.jsp">
					<input type="submit" value="回到首頁"
						class="btn btn-outline-success ">
				</form>
			</div>
		</div>

		<div style="margin-top: 10px">
			<div class="card">

				<div class="card-header">
					<h3 style="color:red"><%= memVO.getMem_name()%></h3><h3>房客的預約看房行程表</h3>
				</div>

				<div class="card-body">
					<div class="table-responsive">

						<table
							class="table table-bordered table-hover mb-0 text-nowrap text-center">
							<tbody>
								<tr style="background-color: #EDF9DE;">
									<th scope="col">預約看房日期</th>
									<th scope="col">房東名稱</th>
									<th scope="col">房屋地址</th>
									<th scope="col">房租</th>
									<th scope="col">預約狀態</th>
									<th scope="col">取消預約</th>
									<th scope="col">完成預約</th>
									<th scope="col">地圖導覽</th>
								</tr>
								
								
<%@ include file="page1.file" %>
	<c:forEach var="appointVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	<tr>
		<td><fmt:formatDate value="${appointVO.hou_app_date}" pattern="yyyy-MM-dd"/></td>
			<td><c:forEach var="lanVO" items="${lanSvc.all}">
					<c:forEach var="memVO" items="${memSvc.all}">
                    	<c:if test="${appointVO.lan_id==lanVO.lan_id}">
                    		<c:if test="${lanVO.mem_id == memVO.mem_id}">
	                    		<c:if test="${memVO.mem_sex==1}">
	                    			${memVO.mem_name} - 先生
	                    		</c:if>
	                    		<c:if test="${memVO.mem_sex==2}">
	                    			${memVO.mem_name} - 女士
	                    		</c:if>
                    		</c:if>
                    	</c:if>
                    </c:forEach>
                </c:forEach>
			</td>
			<td>
				<c:forEach var="houVO" items="${houSvc.all}">
						<c:if test="${appointVO.hou_id == houVO.hou_id}">
								${houVO.hou_address}
						</c:if>
				</c:forEach>
			</td>
			
			<td>
				<c:forEach	var="houVO" items="${houSvc.all}">
					<c:if test="${appointVO.hou_id == houVO.hou_id }">
								${houVO.hou_rent}
					</c:if>
				</c:forEach>				
			</td>
			
			<td>
				<c:if test="${appointVO.app_status.equals('A0')}">
					已預約	
				</c:if>
				<c:if test="${appointVO.app_status.equals('A2')}">
					完成看房
				</c:if>
				<c:if test="${appointVO.app_status.equals('A3')}">
					未完成看房
				</c:if>
			</td>
			<td>
				<c:if test="${appointVO.app_status.equals('A0')}">
				  	 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/appoint/appoint.do" style="margin-bottom: 0px;">
				     <input type="submit" value="取消預約">
				     <input type="hidden" name="appoint_id"  value="${appointVO.appoint_id}"><!-- hidden表示看不到 但因為submit的關係，所以按下按鈕後整個Form表單會一起被送出。 -->
				     <input type="hidden" name="action" value="delete"></FORM>
				 </c:if>
				 <c:if test="${!appointVO.app_status.equals('A0')}">
				     <input type="button"  disabled="disabled" value="取消預約">
			    </c:if>
			</td>
			<td>
				<c:if test="${appointVO.app_status.equals('A0')}">
				  	 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/appoint/appoint.do" style="margin-bottom: 0px;">
				     <input type="submit" value="完成預約">
				     <input type="hidden" name="appoint_id"  value="${appointVO.appoint_id}"><!-- hidden表示看不到 但因為submit的關係，所以按下按鈕後整個Form表單會一起被送出。 -->
				     <input type="hidden" name="action" value="getOne_For_Update"></FORM>
				 </c:if>
				 <c:if test="${!appointVO.app_status.equals('A0')}">
				     <input type="button"  disabled="disabled" value="完成預約">
			    </c:if>
			</td>
			<td>
				<c:forEach	var="houVO" items="${houSvc.all}">
					<c:if test="${appointVO.hou_id == houVO.hou_id }">
					<iframe width="150" height="150"  frameborder="0" style="border:0" src="https://www.google.com/maps/embed/v1/directions?key=AIzaSyAS_rBXRnvCraOtQVhz5gQHPRQA4wLeWHE&zoom=10&origin=中央大學 &destination=${houVO.hou_address}">
 					</iframe>
					</c:if>
				</c:forEach>				
			</td>
			
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Flexbox container for aligning the toasts -->
	<div aria-live="polite" aria-atomic="true"
		class="d-flex justify-content-center align-items-center"
		style="min-height: 200px;">

		<!-- Then put toasts within -->
		<div class="toast" role="alert" aria-live="assertive"
			aria-atomic="true">
			<div class="toast-header">
				<img src="..." class="rounded mr-2" alt="..."> <strong
					class="mr-auto">Bootstrap</strong>
				<button type="button" class="ml-2 mb-1 close" data-dismiss="toast"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="toast-body">
				<%-- 錯誤表列 --%>

			</div>
		</div>
	</div>

	<!-- 工作區結束 -->














<!-- <table> -->
<!-- 	<tr> -->
<!-- 		<td> -->
<%-- 			<h3><%= memVO.getMem_name()%>房客的預約看房行程表</h3> --%>
<%-- 			<h4><a href="<%=request.getContextPath() %>/front/index.jsp"><img src="<%=request.getContextPath() %>/front/appoint/images/back1.gif" width="100" height="32" border="0">回首頁</a></h4> --%>
<!-- 		</td> -->
<!-- 	</tr> -->
<!-- </table> -->

<!-- <script> -->
		
<!-- // 		navigator.geolocation.getCurrentPosition((position) =>{ -->
<!-- // 			console.log(position.coords); -->
<!-- // 		    var pos = { -->
<!-- // 		        lat: position.coords.latitude, -->
<!-- // 		        lng: position.coords.longitude -->
<!-- // 		    }; -->
		
<!-- // 		}); -->


<!-- </script> -->

<!-- <table> -->
<!-- 	<tr> -->
<!-- 		<th>預約看房日期</th> -->
<!-- 		<th>房東名稱</th> -->
<!-- 		<th>房屋地址</th> -->
<!-- 		<th>房租</th> -->
<!-- 		<th>預約狀態</th> -->
<!-- 		<th>取消預約</th> -->
<!-- 		<th>地圖導覽</th> -->
<!-- 	</tr> -->
<%-- 	<%@ include file="page1.file" %> --%>
<%-- 	<c:forEach var="appointVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"> --%>
<!-- 	<tr> -->
<%-- 		<td><fmt:formatDate value="${appointVO.hou_app_date}" pattern="yyyy-MM-dd"/></td> --%>
<%-- 			<td><c:forEach var="lanVO" items="${lanSvc.all}"> --%>
<%-- 					<c:forEach var="memVO" items="${memSvc.all}"> --%>
<%--                     	<c:if test="${appointVO.lan_id==lanVO.lan_id}"> --%>
<%--                     		<c:if test="${lanVO.mem_id == memVO.mem_id}"> --%>
<%-- 	                    		<c:if test="${memVO.mem_sex==1}"> --%>
<%-- 	                    			${memVO.mem_name} - 先生 --%>
<%-- 	                    		</c:if> --%>
<%-- 	                    		<c:if test="${memVO.mem_sex==2}"> --%>
<%-- 	                    			${memVO.mem_name} - 女士 --%>
<%-- 	                    		</c:if> --%>
<%--                     		</c:if> --%>
<%--                     	</c:if> --%>
<%--                     </c:forEach> --%>
<%--                 </c:forEach> --%>
<!-- 			</td> -->
<!-- 			<td> -->
<%-- 				<c:forEach var="houVO" items="${houSvc.all}"> --%>
<%-- 						<c:if test="${appointVO.hou_id == houVO.hou_id}"> --%>
<%-- 								${houVO.hou_address} --%>
<%-- 						</c:if> --%>
<%-- 				</c:forEach> --%>
<!-- 			</td> -->
			
<!-- 			<td> -->
<%-- 				<c:forEach	var="houVO" items="${houSvc.all}"> --%>
<%-- 					<c:if test="${appointVO.hou_id == houVO.hou_id }"> --%>
<%-- 								${houVO.hou_rent} --%>
<%-- 					</c:if> --%>
<%-- 				</c:forEach>				 --%>
<!-- 			</td> -->
			
<!-- 			<td> -->
<%-- 				<c:if test="${appointVO.app_status.equals('A0')}"> --%>
<!-- 					已預約	 -->
<%-- 				</c:if> --%>
<%-- 				<c:if test="${appointVO.app_status.equals('A1')}"> --%>
<!-- 					取消預約 -->
<%-- 				</c:if> --%>
<!-- 			</td> -->
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/appoint/appoint.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="取消預約"> -->
<%-- 			     <input type="hidden" name="appoint_id"  value="${appointVO.appoint_id}"><!-- hidden表示看不到 但因為submit的關係，所以按下按鈕後整個Form表單會一起被送出。 --> --%>
<!-- 			     <input type="hidden" name="action" value="delete"></FORM> -->
<!-- 			</td> -->
<!-- 			<td> -->
<%-- 				<c:forEach	var="houVO" items="${houSvc.all}"> --%>
<%-- 					<c:if test="${appointVO.hou_id == houVO.hou_id }"> --%>
<%-- 					<iframe width="150" height="150"  frameborder="0" style="border:0" src="https://www.google.com/maps/embed/v1/directions?key=AIzaSyAS_rBXRnvCraOtQVhz5gQHPRQA4wLeWHE&zoom=10&origin=中央大學 &destination=${houVO.hou_address}"> --%>
<!--  </iframe> -->
<%-- 					</c:if> --%>
<%-- 				</c:forEach>				 --%>
<!-- 			</td> -->
			
<!-- 		</tr> -->
<%-- 	</c:forEach> --%>
<!-- </table> -->
<%-- <%@ include file="page2.file" %> --%>
<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
</body>
</html>