<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.goodhouse.contract.model.*"%>

<%
	ContractService conSvc = new ContractService();
	List<ContractVO> list = conSvc.getAll();
	    Collections.reverse(list);
	pageContext.setAttribute("list", list);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>合約分類資料 - listAll_contract.jsp</title>
<style>
	th{
	font-size:20px;
	}
	
	td{
	font-size:18px;
	}
	#back{
		position:fixed;
		top: 20%;
		z-index: 994;
		left: 4%;
	}

</style>

</head>
<body>
	<jsp:include page="/BackHeaderFooter/Header.jsp" />

	<div class="container">
		<div class="row">
			<div id="back">
				<h4> 
					<a href="<%=request.getContextPath()%>/back/back_index.jsp"><img
						src="<%=request.getContextPath()%>/images/back.png" width="100" height="100" border="0"></a>
				</h4>
			</div>
		</div>
		<div class="row">
			<div class="col-2"></div>
			<div class="col-4">
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
			</div>


			<div class="col-6 text-right">
			
				<form method="post"
					action="<%=request.getContextPath()%>/back/contract/add_contract.jsp">
					<input type="submit" value="新增合約分類" class="btn btn-outline-info btn-lg" style="font-weight:bold;margin-top:15px;font-size:20px">
				</form>
			</div>
		</div> 

		<div class="" style="margin-top: 15px">

			<div class="card">


				<div class="card-body">
					<div class="table-responsive">

						<table class="table table-bordered table-hover mb-0 text-nowrap text-center">
							<tbody>
								<tr style="background-color: #EDF9DE;">
<!-- 									<th scope="col">合約分類編號</th> -->
									<th scope="col">合約分類名稱</th>
<!-- 									<th scope="col">合約內容</th> -->
									<th scope="col">合約分類狀態</th>
									<th scope="col"></th>
									<th scope="col"></th>
									<th scope="col"></th>
								</tr>
								<%-- 			<%@ include file="page1.file" %>  --%>
								<c:forEach var="conVO" items="${list}">
									<tr>
<%-- 										<td>${conVO.con_id}</td> --%>
										<td>${conVO.con_name}</td>
<%-- 										<td>${conVO.con_content}</td> --%>
<%-- 										<td>${conVO.con_status}</td> --%>
										<c:forEach var="con_status" items="${Con_statusList}">
											<c:if test="${con_status.status_no_name eq conVO.con_status}">
												<c:choose>
													<c:when test="${con_status.status_no_name eq 'S1啟用' }">
														<td style="color: #3498DB">${con_status.status_name}</td>
													</c:when>
													
													<c:when test="${con_status.status_no_name eq 'S2停用' }">
														<td style="color: #E74C3C">${con_status.status_name}</td>
													</c:when>
												</c:choose>
											</c:if>
										</c:forEach>

										<td>
											<form method="post"
												action="<%=request.getContextPath()%>/back/contract/contract.do"
												style="margin-bottom: 0px;">
												<input type="submit" value="修改" class="btn btn-outline-info h3" style="font-weight:bold;margin-top:15px;font-size:20px"> 
												<input type="hidden" name="con_id" value="${conVO.con_id}">
												<input type="hidden" name="action" value="getOne_For_Update">
											</form>
										</td>
 
										<td>
											<form method="post"
												action="<%=request.getContextPath()%>/back/contract/contract.do"
												style="margin-bottom: 0px;">
												<input type="submit" value="啟用/停用" class="btn btn-outline-info btn-lg" style="font-weight:bold;margin-top:15px;font-size:20px"> 
												<input type="hidden" name="con_id" value="${conVO.con_id}">
												<input type="hidden" name="action" value="getOne_For_Stop">
											</form>
										</td>
										<td>
											<form method="post"
												action="<%=request.getContextPath()%>/back/contract/contract.do"
												style="margin-bottom: 0px;">
												<input type="submit" value="查看" class="btn btn-outline-info btn-lg" style="font-weight:bold;margin-top:15px;font-size:20px"> 
												<input type="hidden" name="con_id" value="${conVO.con_id}">
												<input type="hidden" name="action" value="getOne_For_Display">
											</form>
										</td>

									</tr>
								</c:forEach>
							</tbody>
						</table>
						<%-- 		<%@ include file="page2.file" %> --%>
					</div>
				</div>

			</div>
		</div>

	</div>
	<jsp:include page="/BackHeaderFooter/Footer.jsp" />
</body>
</html>