<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.contract.model.*"%>

<%
	ContractVO conVO = (ContractVO) request.getAttribute("conVO");
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>合約分類修改</title>
</head>
<body>
<jsp:include page="/BackHeaderFooter/Header.jsp" />

	
	<div class="container">
		<div class="row col-12">
			<h4><a href="<%=request.getContextPath()%>/back/contract/listAll_contract.jsp">
			<img src="images/back1.gif" width="100" height="32" border="0">回合約分類列表</a></h4>
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
					<c:forEach var="message" items="${errorMsgs}">
						<p style="color:red">${message}</p><br>
					</c:forEach>
			</c:if>
		</div>
	
		<div class="row col-12">
		
		<form name="the_form" method="post" action="contract.do">
			<table>
				<tr>
					<td>合約分類名稱</td><br>
					<td>
						<p type="text" >${conVO.con_name}</p>
					</td>
				</tr>
				<tr>
					<td>合約內容</td><br>
					<td>
						<p type="text" >${conVO.con_content}</p>
					</td>
				</tr>
				<jsp:useBean id="conSvc" scope="page" class="com.goodhouse.contract.model.ContractService"/>
				<tr>
					<td>合約使用狀態</td><br>
					<td>
						<select name="con_status">
							<c:forEach var="con_status" items="${Con_statusList}">
								<option value="${con_status.status_no_name}" ${(con_status.status_no_name == conVO.con_status)?'selected':''}>${con_status.status_name}
							</c:forEach>
						</select>
					</td>
				</tr>
			</table>
			<input type="hidden" name="action" value="update" >
			<input type="hidden" name="con_id" value="${conVO.con_id}">
			<input type="hidden" name="con_name" value="${conVO.con_name}">
			<input type="hidden" name="con_content" value="${conVO.con_content}">
			<input type="submit" value="送出修改">
		
		</form>
		</div>
	</div>
<jsp:include page="/BackHeaderFooter/Footer.jsp" />
</body>
</html>