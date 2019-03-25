<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.goodhouse.contract.model.*"%>

<%
	ContractService conSvc = new ContractService();
	List<ContractVO> list = conSvc.getAll();
	//     Collections.reverse(list);
	pageContext.setAttribute("list", list);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>合約分類資料 - listAll_contract.jsp</title>
<style>
</style>

</head>
<body>
	<jsp:include page="/BackHeaderFooter/Header.jsp" />

	<div class="container">
		<div class="row">

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


			<div class="col-8">
				<form method="post"
					action="<%=request.getContextPath()%>/back/contract/add_contract.jsp">
					<input type="submit" value="新增合約分類" class="btn btn-outline-info ">
				</form>
				<%-- 				<a href='<%=request.getContextPath()%>/back/contract/add_contract.jsp'>Add</a>新增合約分類 --%>
			</div>
		</div>

		<div class="" style="margin-top: 15px">

			<div class="card">

				<div class="card-header">
					<h3>合約分類</h3>
				</div>

				<div class="card-body">
					<div class="table-responsive">

						<table class="table table-bordered table-hover mb-0 text-nowrap text-center">
							<tbody>
								<tr style="background-color: #EDF9DE;">
									<th scope="col">合約分類編號</th>
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
										<td>${conVO.con_id}</td>
										<td>${conVO.con_name}</td>
<%-- 										<td>${conVO.con_content}</td> --%>
										<td>${conVO.con_status}</td>

										<td>
											<form method="post"
												action="<%=request.getContextPath()%>/back/contract/contract.do"
												style="margin-bottom: 0px;">
												<input type="submit" value="修改" class="btn btn-outline-info " style="font-weight:bold"> 
												<input type="hidden" name="con_id" value="${conVO.con_id}">
												<input type="hidden" name="action" value="getOne_For_Update">
											</form>
										</td>

										<td>
											<form method="post"
												action="<%=request.getContextPath()%>/back/contract/contract.do"
												style="margin-bottom: 0px;">
												<input type="submit" value="停用" class="btn btn-outline-info " style="font-weight:bold"> 
												<input type="hidden" name="con_id" value="${conVO.con_id}">
												<input type="hidden" name="action" value="getOne_For_Stop">
											</form>
										</td>
										<td>
											<form method="post"
												action="<%=request.getContextPath()%>/back/contract/contract.do"
												style="margin-bottom: 0px;">
												<input type="submit" value="查看" class="btn btn-outline-info " style="font-weight:bold"> 
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