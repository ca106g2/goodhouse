<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.contract.model.*"%>

<%
	ContractVO conVO = (ContractVO) request.getAttribute("conVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<style>
	#back{
		position:fixed;
		top: 20%;
		z-index: 994;
		left: 4%;
	}
</style>
</head>
<body bgcolor='white'>
	<jsp:include page="/BackHeaderFooter/Header.jsp" />

	<div class="container">
		<div class="row justify-content-center">

			<div class="col-2" id="back">
				<h4>
					<a
						href="<%=request.getContextPath()%>/back/contract/listAll_contract.jsp"><img
						src="<%=request.getContextPath()%>/images/back.png" width="100"
						height="100" border="0"></a>
				</h4>
			</div>
				
				<c:if test="${not empty errorMsgs}">
					<c:forEach var="message" items="${errorMsgs}">
						<p style="color: red">${message}</p>
						<br>
					</c:forEach>
				</c:if>
		</div>
			<jsp:useBean id="conSvc" scope="page"
				class="com.goodhouse.contract.model.ContractService" />
		<div class="row justify-content-center">
			<div class="col-2"></div>
			<div class="col-10"></div>
			<div class="col-10">
				<div class="card">
					<div class="card-body">
						<div class="table-responsive">
							<form method="post" action="contract.do" name="form1"
								class="text-center">
								<table
									class="table table-bordered table-hover mb-0 text-nowrap text-center h3">
									<tbody>
										<tr>
											<td size="45">合約分類名稱</td>
											<td><input type="text" name="con_name" size="45"
												value="<%=(conVO == null) ? "合約分類名稱" : conVO.getCon_name()%>"
												class="btn btn-light" style="font-size: 20px" /></td>
										</tr>
										<tr>
											<td>合約使用狀態</td>
											<td>
												<div class="form-group">
													<label for="exampleFormControlSelect1"> <select
														name="con_status"
														style="overflow: hidden; text-overflow: ellipsis; white-space: nowrap; color: #E74C3C; font-weight: bold; width: 100px;"
														class="form-control h3"
														id="exampleFormControlSelect1 conStatus"
														data-toggle="tooltip" data-placement="right">
															<c:forEach var="con_status" items="${Con_statusList}">
																<option value="${con_status.status_no_name}"
																	style="color: #E74C3C; font-weight: bold"
																	${(con_status.status_no_name == conVO.con_status)?'selected':''}>${con_status.status_name}
															</c:forEach>
													</select>
													</label>
											</td>
										</tr>
<!-- 										<tr> -->
<!-- 											<td>合約內容</td> -->
<!-- 											<td><input type="text" name="con_content" size="45" -->
<%-- 												value="<%=(conVO == null) ? "合約內容" : conVO.getCon_content()%>" /> --%>
<!-- 											</td> -->
<!-- 										</tr> -->
									</tbody>
								</table>
								<input type="hidden" name="action" value="insert" /> 
								<input type="hidden" name="con_content" id="con_content" value="" /> 
								<input type="submit" value="送出新增" class="btn btn-outline-info btn-lg"
									style="margin-top: 15px; font-weight: bold; font-size: 20px" />
							</form>
						</div>
					</div>
				</div>
			</div>
			<div class="justify-content-center" style="margin-top: 15px">
			<img src="<%=request.getContextPath()%>/images/magic.png" width="25" height="25" style="margin-bottom:10px" id="magic">
				<div class="card" style="background-color:#E8F8F5" >
					<div class="card-header text-center" style="">
						<h3>合約內容</h3>
					</div>
					<div class="card-body">
						<div class="form-group">
							<textarea name="con_content" value="" id="conContent"
								class="form-control btn btn-light text-left" rows="50" cols="180"
								placeholder="" id="conContent">
				
							</textarea>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
	<jsp:include page="/BackHeaderFooter/Footer.jsp" />
	
	<script type="text/javascript">
			
			$('#conContent').change(function(){
				
				$('#con_content').attr('value', $('#conContent').val());
				
			});
			
// 			$('#magic').click(function(){
// 				alert("我觸發了");
// 				$('#conContent').val(13213);
// 			});
	</script>
</body>
</html>