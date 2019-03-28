<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.goodhouse.rental_message.model.*"%>
<%@ page import="com.goodhouse.member.model.*" %>
<%@ page import="com.goodhouse.landlord.model.*" %>
<%@ page import="com.goodhouse.house.model.*" %>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	LanService lanSvc = new LanService();
	LanVO lanVO = lanSvc.getOneLanByMemId(memVO.getMem_id());
	String lan_id = lanVO.getLan_id();
	
	HouseService houSvc = new HouseService();
	
	
	HouseVO houVO = houSvc.getOneByLanId(lan_id);
	String hou_id = "";%>
<%		if(houVO != null){
			hou_id = houVO.getHou_id();
		}else{%>
		<jsp:include page="/FrontHeaderFooter/Header.jsp" />
		<div class="container">
			<div class="row">
				<div class="col-sm-1"></div>
				<div class="col-sm-10">
					<div class="card">
						<div class="card-header">
							  	溫馨小提醒
						</div>
						<div class="card-body">
						    <h5 class="card-title">QQ...還未申請房屋!</h5>
						    <p class="card-text">請先完成新增房屋流程再使用本功能</p>
						    <a href="<%=request.getContextPath()%>/front/index.jsp" class="btn btn-primary">回到首頁</a>
						</div>
					</div>
				</div>
				<div class="col-sm-1"></div>
			</div>
		</div>
		
<% 		}%>
	
<%
    RentMessService rentMessSvc = new RentMessService();
    List<RentMessVO> list = rentMessSvc.getPart(hou_id);
    pageContext.setAttribute("list",list);
%>

<%if(houVO != null) {%>
<html>
<head>
<title>租屋問題列表</title>

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
					<h3 style="color:red"><%= memVO.getMem_name()%></h3><h3>房東的租屋問題列表 </h3>
				</div>

				<div class="card-body">
					<div class="table-responsive">

						<table
							class="table table-bordered table-hover mb-0 text-nowrap text-center">
							<tbody>
								<tr style="background-color: #EDF9DE;">
									<th scope="col">租屋問題編號</th>
									<th scope="col">會員編號</th>
									<th scope="col">留言日期</th>
									<th scope="col">提問內容</th>
									<th scope="col">回覆內容</th>
									<th scope="col">回覆</th>
									<th scope="col">刪除</th>
									
<%@ include file="page1.file" %>
	<c:forEach var="rentMessVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${rentMessVO.ren_mes_id}</td>
			<td>${rentMessVO.mem_id}</td>
			<td><fmt:formatDate value="${rentMessVO.ren_mes_time}" pattern="yyyy-MM-dd"/></td>
			<td>${rentMessVO.ren_mes_request}</td>
			<td>${rentMessVO.ren_mes_response}</td> 
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/rentMess/rentMess.do" style="margin-bottom: 0px;">
			     <input type="submit" value="回覆">
			     <input type="hidden" name="ren_mes_id"  value="${rentMessVO.ren_mes_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/rentMess/rentMess.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="ren_mes_id"  value="${rentMessVO.ren_mes_id}"><!-- hidden表示看不到 但因為submit的關係，所以按下按鈕後整個Form表單會一起被送出。 -->
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















<!-- <table> -->
<!-- 	<tr> -->
<!-- 		<th>租屋留言編號</th> -->
<!-- 		<th>會員編號</th> -->
<!-- 		<th>留言日期</th> -->
<!-- 		<th>提問內容</th> -->
<!-- 		<th>回覆內容</th> -->
<!-- 	</tr> -->
<%-- <%--     <%@ include file="page1.file" %>  --%>
<%-- 	<c:forEach var="rentMessVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"> --%>
		
<!-- 		<tr> -->
<%-- 			<td>${rentMessVO.ren_mes_id}</td> --%>
<%-- 			<td>${rentMessVO.mem_id}</td> --%>
<%-- 			<td><fmt:formatDate value="${rentMessVO.ren_mes_time}" pattern="yyyy-MM-dd"/></td> --%>
<%-- 			<td>${rentMessVO.ren_mes_request}</td> --%>
<%-- 			<td>${rentMessVO.ren_mes_response}</td>  --%>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/rentMess/rentMess.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="回覆"> -->
<%-- 			     <input type="hidden" name="ren_mes_id"  value="${rentMessVO.ren_mes_id}"> --%>
<!-- 			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
<!-- 			</td> -->
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/rentMess/rentMess.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="刪除"> -->
<%-- 			     <input type="hidden" name="ren_mes_id"  value="${rentMessVO.ren_mes_id}"><!-- hidden表示看不到 但因為submit的關係，所以按下按鈕後整個Form表單會一起被送出。 --> --%>
<!-- 			     <input type="hidden" name="action" value="delete"></FORM> -->
<!-- 			</td> -->
<!-- 		</tr> -->
<%-- 	</c:forEach> --%>
<!-- </table> -->
<%-- <%@ include file="page2.file" %> --%>
<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
</body>

</html>
<%}%>