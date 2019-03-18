<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Required meta tags -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS start-->
<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css">
<!-- Bootstrap CSS end-->
<title>房屋評價</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>
	<jsp:include page="/FrontHeaderFooter/Header.jsp" />
	
	<div class="container">
		<div class="row justfy-content-center">
			<div class="row col-12">
			
				<div>
					<%-- 錯誤表列 --%>
					<c:if test="${not empty errorMsgs}">
						<font style="color: red"></font>
							<c:forEach var="message" items="${errorMsgs}">
								<p style="color: red">${message}</p>
							</c:forEach>
					</c:if>
				</div>
				
				<div>
					<form method="post" action="listAll_house_evaluate.jsp">
						<input type="hidden" name="action" value="listAll_house_evaluate">
						<input type="submit" value="查看所有評價" class="btn btn-outline-success btn-lg btn-block">
					</form>
				</div>
				
			</div>
		</div>
	
	</div>
	
	<ul>
	
	  <jsp:useBean id="houEvaSvc" scope="page" class="com.goodhouse.house_evaluate.model.House_EvaluateService" />

	</ul>

<ul>
  <li><a href='add_house_evaluate.jsp'>Add</a> 新增評價 </li>
</ul>


<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
</body>
</html>