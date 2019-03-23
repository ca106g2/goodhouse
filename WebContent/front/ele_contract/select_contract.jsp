<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.contract.model.*" %>

<!doctype html>
<html lang="en">
<head>
</head>
<body>
<%-- 	<jsp:include page="/FrontHeaderFooter/Header.jsp" /> --%>

	<!-- 工作區開始 -->
<div class="container">
		<div class="row">
<%-- 			<p>回首頁<a href="lan_select_page.jsp"><img src="<%=request.getContextPath()%>/share_pic/back1.gif" width="100" height="30 !important" ></a></p> --%>
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
<!-- 		<div class="row" > -->
			<div class="">
				
				<form method="post" action="ele_contract.do" name="form1" style="" class="text-center">
				
					<jsp:useBean id="conSvc" scope="page" class="com.goodhouse.contract.model.ContractService"/>
						
							<div class="form-group">
								<label for="exampleFormControlSelect1"></label>
								<select size="1" name="con_id" style="overflow:hidden; text-overflow:ellipsis;white-space:nowrap;width:210px;" class="form-control" id="exampleFormControlSelect1">
									<c:forEach var="conVO" items="${conSvc.all}">
										<option value="${conVO.con_id}" ${(conVO.con_id == conVO.con_id)?'selected':''}" class=" form-control btn btn-light"/>
										${conVO.con_name}
									</c:forEach>
								</select><br>
							</div>
							
							<input type="hidden" name="action" value="select_contract">
							<input type="submit" value="送出" class="btn btn-outline-success btn-lg">
				</form>
				
				
			</div>
<!-- 		</div> -->
</div>
	
	
	<!-- 工作區結束 -->
<%-- 	<jsp:include page="/FrontHeaderFooter/Footer.jsp" /> --%>


</body>
</html>