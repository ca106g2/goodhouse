<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.contract.model.*"%>

<%
  ContractVO conVO = (ContractVO) request.getAttribute("conVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>

</head>
<body bgcolor='white'>
<jsp:include page="/BackHeaderFooter/Header.jsp" />
	
	<div class="container">
	
		<div class="row col-12">
			<h4><a href="<%=request.getContextPath()%>/back/contract/listAll_contract.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">回合約分類列表</a></h4>
			<c:if test="${not empty errorMsgs}">
				<c:forEach var="message" items="${errorMsgs}">
					<p style="color:red">${message}</p><br>
				</c:forEach>
			</c:if>
		</div>
		
		<jsp:useBean id="conSvc" scope="page" class="com.goodhouse.contract.model.ContractService"/>
		<div class="row col-12">
			<form method="post" action="contract.do" name="form1">
				<table>
					<tr>
						<td size="45">合約分類名稱</td>
						<td>
							<input type="text" name="con_name" size="45" value="<%= (conVO == null)? "合約分類名稱" : conVO.getCon_name()%>" class="btn btn-light"/>
						</td>
					</tr>
					<tr>
						<td>合約使用狀態</td>
						<td>
							<select name="con_status">
								<c:forEach var="con_status" items="${Con_statusList}">
									<option value="${con_status.status_name}" ${(con_status.status_no_name == conVO.con_status)?'selected':''}>${con_status.status_name}
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td>合約內容</td>
						<td>
							<input type="text" name="con_content" size="45" value="<%= (conVO == null)?"合約內容" : conVO.getCon_content()%>"/>
								
						
						
						</td>
					</tr>
				</table>
				<input type="hidden" name="action" value="insert"/>
				<input type="submit" value="送出新增"/>
			</form>
		</div>

	</div>
<jsp:include page="/BackHeaderFooter/Footer.jsp" />
</body>
</html>