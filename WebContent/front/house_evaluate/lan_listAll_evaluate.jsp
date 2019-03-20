<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.goodhouse.house_evaluate.model.*"%>
<%@ page import="com.goodhouse.member.model.*"%>

<%
    House_EvaluateService heSvc = new House_EvaluateService();
    List<House_EvaluateVO> list = heSvc.getAll();
    MemVO memVO = (MemVO)session.getAttribute("memVO");
    pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>房屋評價-listAllHouse_Evaluate</title>
<style>
 
</style>


</head>
<body>
<jsp:include page="/FrontHeaderFooter/Header.jsp" />
	<div class="container">
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color:red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color:red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		
		<table class="table table-hover">
		  	<thead>
			    <tr>
			      	<th scope="col">評論者</th>
			      	<th scope="col">被評價房屋地址</th>
			      	<th scope="col">評價等級</th>
			      	<th scope="col">評價內容</th>
			    </tr>
		  	</thead>
			<jsp:useBean id="houSvc" scope="page" class="com.goodhouse.house.model.HouseService" />
			<jsp:useBean id="mSvc" scope="page" class="com.goodhouse.member.model.MemService" />
			<jsp:useBean id="lanSvc" scope="page" class="com.goodhouse.landlord.model.LanService" />
<%-- 		<%@ include file="page1.file" %> --%>
		  	<tbody>
				<c:forEach var="House_EvaluateVO" items="${list}" >
			    <tr>
					<c:if test="${House_EvaluateVO.hou_id eq houSvc.getOneByLanId(lanSvc.getOneLanByMemId(memVO.mem_id).lan_id).hou_id}">
					<td>${mSvc.getOneMem(House_EvaluateVO.mem_id).mem_name}</td>
					<td>${houSvc.getOneHouse(House_EvaluateVO.hou_id).hou_address}</td>
					<td>${House_EvaluateVO.hou_eva_grade}</td>
					<td>${House_EvaluateVO.hou_eva_content}</td>
					</c:if>
			    </tr>
				</c:forEach>
		  	</tbody>
		</table>
<%-- 	<%@ include file="page2.file" %> --%>
	</div>
<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
</body>
</html>