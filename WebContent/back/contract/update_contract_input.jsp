<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.contract.model.*"%>
<jsp:useBean id="conSvc" scope="page"
	class="com.goodhouse.contract.model.ContractService" />

<%
	ContractVO conVO = (ContractVO) request.getAttribute("conVO");
	pageContext.setAttribute("conVO", conVO);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<jsp:include page="/BackHeaderFooter/Header.jsp" />

	<div class="container">
		<div class="row justify-content-center">
			<div class="col-2">
				<h4>
					<a
						href="<%=request.getContextPath()%>/back/contract/listAll_contract.jsp">
						<img src="<%=request.getContextPath()%>/images/back.png"
						width="100" height="100" border="0">
					</a>
				</h4>
				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<c:forEach var="message" items="${errorMsgs}">
						<p style="color: red">${message}</p>
						<br>
					</c:forEach>
				</c:if>
			</div>
			<div class="col-10">
				<div class="card">


					<div class="card-body">
						<div class="table-responsive">
							<form name="the_form" method="post" action="contract.do">
								<table
									class="table table-bordered table-hover mb-0 text-nowrap text-center">
									<tbody>
										<tr>
											<td>合約分類名稱</td>
											<br>
											<td>
												<input type="text" name="con_name" value="${conVO.con_name}" class="btn btn-light">
											</td>
										</tr>
										<tr>
											<td>合約使用狀態</td>
											<br>
												<c:forEach var="con_status" items="${Con_statusList}">
													<c:if test="${con_status.status_no_name eq conVO.con_status}">
														<c:choose>
															<c:when test="${con_status.status_no_name eq 'S1啟用'}">
																<td style="color: #3498DB">${con_status.status_name}</td>
															</c:when>
															
															<c:when test="${con_status.status_no_name eq 'S2停用' }">
																<td style="color: #E74C3C">${con_status.status_name}</td>
																</c:when>
														</c:choose>
													</c:if>
												</c:forEach>
										</tr>
<!-- 												<textarea name="con_content" value="" class="btn btn-light" rows="5" cols="150" placeholder="輸入其他特別協議"></textarea> -->
									</tbody>
								</table>
								<input type="hidden" name="action" value="update"> 
								<input type="hidden" name="con_id" value="${conVO.con_id}"> 
								<input type="hidden" name="con_content" value="${conVO.con_content}" >
								<input type="hidden" name="con_status" value="${conVO.con_status}">
								<input type="submit" value="送出修改">
							</form>
						</div>

					</div>
				</div>

			</div>
			
			<div class="" style="margin-top:15px">
			<textarea name="con_content" value="" class="btn btn-light" rows="50" cols="150" placeholder="輸入其他特別協議">
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			</textarea>
			
			</div>
		</div>
		<jsp:include page="/BackHeaderFooter/Footer.jsp" />
</body>
</html>