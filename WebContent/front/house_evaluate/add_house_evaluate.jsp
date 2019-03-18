<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.house_evaluate.model.*"%>
<%@ page import="com.goodhouse.member.model.*"%>
<%
	House_EvaluateVO heVO = (House_EvaluateVO) request.getAttribute("House_EvaluateVO");
	MemVO memVO = (MemVO)session.getAttribute("memVO");
%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta charset="UTF-8">
<title>新增房屋評價</title>
</head>
<body>
	<jsp:include page="/FrontHeaderFooter/Header.jsp" />
	<div class="container">
		<div class="row justfy-content-center">
			<div class="row col-12 ">
			
				<div>
					<a href="select_page.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">回首頁</a>
					<%-- 錯誤表列 --%>
					<c:if test="${not empty errorMsgs}">
						<font style="color:red">請修正以下錯誤:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color:red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>
				</div>
					<form method="post" action="house_evaluate.do" name="form1">
					<jsp:useBean id="houSvc" scope="page" class="com.goodhouse.house.model.HouseService" />
						<dl class="row ">
							<dt class="col-sm-3">請選擇你的房屋名稱</dt>
							<dd class="col-sm-9">
								<select name="hou_id" >
									<c:forEach var="HouseVO" items="${houSvc.all}" > 
								    <option  value="${HouseVO.hou_id}">${HouseVO.hou_name}
								    </c:forEach>
								</select>
							</dd>
							
							<dt class="col-sm-3">請選擇評價等級</dt>
							<dd class="col-sm-9">
								<div class="custom-control custom-radio">
								  <input type="radio" id="customRadio1" name="hou_eva_grade" class="custom-control-input" value="G1非常不好">
								  <label class="custom-control-label" for="customRadio1">非常不好</label>
								</div>
								<div class="custom-control custom-radio">
								  <input type="radio" id="customRadio2" name="hou_eva_grade" class="custom-control-input" value="G2不好">
								  <label class="custom-control-label" for="customRadio2">不好</label>
								</div>
								<div class="custom-control custom-radio">
								  <input type="radio" id="customRadio3" name="hou_eva_grade" class="custom-control-input" value="G3普通">
								  <label class="custom-control-label" for="customRadio3">普通</label>
								</div>
								<div class="custom-control custom-radio">
								  <input type="radio" id="customRadio4" name="hou_eva_grade" class="custom-control-input" value="G4好">
								  <label class="custom-control-label" for="customRadio4">好</label>
								</div>
								<div class="custom-control custom-radio">
								  <input type="radio" id="customRadio5" name="hou_eva_grade" class="custom-control-input" value="G5非常好">
								  <label class="custom-control-label" for="customRadio5">非常好</label>
								</div>
							</dd>
							
							<dt class="col-sm-3">請寫下評論</dt>
							<dd class="col-sm-9">
								<textarea name="hou_eva_content" rows="3" cols=50></textarea>
							</dd>
						</dl>
					
						<input type="hidden" name="action" value="insert">
						<input type="submit" value="送出" class="btn btn-outline-secondary">
					</form>	
			</div>
		</div>
	</div>
	
	<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
</body>
</html>