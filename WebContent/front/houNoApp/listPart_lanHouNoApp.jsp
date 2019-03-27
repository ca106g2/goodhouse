<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.goodhouse.house_noappointment.model.*"%>
<%@ page import="com.goodhouse.member.model.*" %>
<%@ page import="com.goodhouse.landlord.model.*" %>
<%-- 此頁練習採用 EL 的寫法取值 --%>


<%
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	
	LanService lanSvc = new LanService();
	LanVO lanVO = lanSvc.getOneLanByMemId(memVO.getMem_id());
    HouNoAppService houNoAppSvc = new HouNoAppService();
    List<HouNoAppVO> list = houNoAppSvc.getPart(lanVO.getLan_id());
    pageContext.setAttribute("list",list);
%>

<jsp:useBean id="houSvc" scope="page" class="com.goodhouse.house.model.HouseService"/>


<html>
<head>
<title>所有租屋訊息資料 </title>

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
<body bgcolor='white'>
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
					<h3 style="color:red; display:inline;" ><%= memVO.getMem_name()%></h3><h3 style="display:inline;">房東的不可預約行程表 </h3>
				</div>

				<div class="card-body">
					<div class="table-responsive">

						<table
							class="table table-bordered table-hover mb-0 text-nowrap text-center">
							<tbody>
								<tr style="background-color: #EDF9DE;">
									<th scope="col">房屋編號</th>
									<th scope="col">房屋名稱</th>
									<th scope="col">房屋地址</th>
									<th scope="col">租金</th>
									<th scope="col">預約日期</th>
									<th scope="col">取消不可預約</th>
								</tr>
<%@ include file="page1.file" %>
	<c:forEach var="houNoAppVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
				<td>
					${houNoAppVO.hou_id}
				</td>
				
				<td>
					<c:forEach var="houVO" items="${houSvc.all}">
						<c:if test="${houNoAppVO.hou_id == houVO.hou_id}">
								${houVO.hou_name}
						</c:if>
					</c:forEach>
				</td>
				<td>
					<c:forEach var="houVO" items="${houSvc.all}">
						<c:if test="${houNoAppVO.hou_id == houVO.hou_id}">
								${houVO.hou_address}
						</c:if>
					</c:forEach>
				</td>
				<td>
					<c:forEach var="houVO" items="${houSvc.all}">
						<c:if test="${houNoAppVO.hou_id == houVO.hou_id}">
								${houVO.hou_rent}
						</c:if>
					</c:forEach>
				</td>
				<td><fmt:formatDate value="${houNoAppVO.hou_noapp_date}" pattern="yyyy-MM-dd"/></td>
				<td>
				  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/houNoApp/houNoApp.do" style="margin-bottom: 0px;">
				     <input type="submit" value="取消不可預約" class="btn btn-danger">
				     <input type="hidden" name="whichPage"	value="<%=whichPage%>">
				     <input type="hidden" name="hou_noapp_id"  value="${houNoAppVO.hou_noapp_id}"><!-- hidden表示看不到 但因為submit的關係，所以按下按鈕後整個Form表單會一起被送出。 -->
				     <input type="hidden" name="action" value="delete"></FORM>
				</td>
		</tr>
	</c:forEach>
	</tbody>
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






<!-- <h4>此頁練習採用 EL 的寫法取值:</h4> -->
<!-- <table id="table-1"> -->
<!-- 	<tr><td> -->
<%-- 		 <h3><%= memVO.getMem_name() %>房東的不可預約行程 - listPart_lanHouNoApp.jsp</h3> --%>
<%-- 		 <h4><a href="<%=request.getContextPath() %>/front/index.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4> --%>
<!-- 	</td></tr> -->
<!-- </table> -->


<!-- <table> -->
<!-- 	<tr> -->
<!-- 		<th>房屋編號</th> -->
<!-- 		<th>房屋名稱</th> -->
<!-- 		<th>房屋地址</th> -->
<!-- 		<th>租金</th> -->
<!-- 		<th>預約日期</th> -->
<!-- 		<th>取消不可預約</th> -->
<!-- 	</tr> -->
<%--     <%@ include file="page1.file" %>  --%>
<%-- 	<c:forEach var="houNoAppVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"> --%>
		
<!-- 		<tr> -->
<!-- 				<td> -->
<%-- 					${houNoAppVO.hou_id} --%>
<!-- 				</td> -->
				
<!-- 				<td> -->
<%-- 					<c:forEach var="houVO" items="${houSvc.all}"> --%>
<%-- 						<c:if test="${houNoAppVO.hou_id == houVO.hou_id}"> --%>
<%-- 								${houVO.hou_name} --%>
<%-- 						</c:if> --%>
<%-- 					</c:forEach> --%>
<!-- 				</td> -->
<!-- 				<td> -->
<%-- 					<c:forEach var="houVO" items="${houSvc.all}"> --%>
<%-- 						<c:if test="${houNoAppVO.hou_id == houVO.hou_id}"> --%>
<%-- 								${houVO.hou_address} --%>
<%-- 						</c:if> --%>
<%-- 					</c:forEach> --%>
<!-- 				</td> -->
<!-- 				<td> -->
<%-- 					<c:forEach var="houVO" items="${houSvc.all}"> --%>
<%-- 						<c:if test="${houNoAppVO.hou_id == houVO.hou_id}"> --%>
<%-- 								${houVO.hou_rent} --%>
<%-- 						</c:if> --%>
<%-- 					</c:forEach> --%>
<!-- 				</td> -->
<%-- 				<td><fmt:formatDate value="${houNoAppVO.hou_noapp_date}" pattern="yyyy-MM-dd"/></td> --%>
<!-- 				<td> -->
<%-- 				  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/houNoApp/houNoApp.do" style="margin-bottom: 0px;"> --%>
<!-- 				     <input type="submit" value="取消不可預約"> -->
<%-- 				     <input type="hidden" name="hou_noapp_id"  value="${houNoAppVO.hou_noapp_id}"><!-- hidden表示看不到 但因為submit的關係，所以按下按鈕後整個Form表單會一起被送出。 --> --%>
<!-- 				     <input type="hidden" name="action" value="delete"></FORM> -->
<!-- 				</td> -->
<!-- 		</tr> -->
<%-- 	</c:forEach> --%>
<!-- </table> -->
<%-- <%@ include file="page2.file" %> --%>
<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
</body>

</html>