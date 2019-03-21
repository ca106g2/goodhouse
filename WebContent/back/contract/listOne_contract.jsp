<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.goodhouse.contract.model.*"%>

<%
//ContractServlet.java(Concroller), 存入req的conVO物件
  ContractVO conVO = (ContractVO) request.getAttribute("conVO"); 
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<jsp:include page="/BackHeaderFooter/Header.jsp" />

	<div class="container">
		<div class="row col-12">
			<h4><a href="<%=request.getContextPath()%>/back/contract/listAll_contract.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回合約分類列表</a></h4>
		</div>
		<div class="row col-12">
			<table class="table table-hover">
	<!-- 			<thead> -->
	<!-- 			</thead> -->
				<tbody>
					<tr>
						<th scope="row">合約分類編號</th>
						<td><%=conVO.getCon_id()%></td>
					</tr>
					<tr>
						<th>合約分類名稱</th>
						<td><%=conVO.getCon_name()%></td>
					</tr>
					<tr>
						<th>合約分類狀態</th>
						<td><%=conVO.getCon_status()%></td>
					</tr>
					<tr>
						<th>合約內容</th>
						<td><%=conVO.getCon_content()%></td>
					</tr>
				</tbody>
			</table>
		</div>
	
	</div>
<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
</body>
</html>