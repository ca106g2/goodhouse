<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.* , com.goodhouse.member.model.*, com.goodhouse.good_record.model.*" %>
<%
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	String mem_id = memVO.getMem_id();
	Good_recordService good_recordSvc = new Good_recordService();
	List<Good_recordVO> recordlist = good_recordSvc.getPart(mem_id);
	pageContext.setAttribute("recordlist", recordlist);
	pageContext.setAttribute("memVO", memVO);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>積分明細紀錄</title>
</head>
<body>
<jsp:include page="/FrontHeaderFooter/Header.jsp" />
<div class="container">
	<div class="row">
		<div class="col-4">
			<jsp:include page="/FrontHeaderFooter/LeftList.jsp" />
		</div>
		<div class="col-8">
			<table border="1">
				<tr>
					<td>會員姓名</td>
					<td>事由</td>
					<td>點數</td>
					<td>時間</td>
				</tr>
				<c:forEach var="good_recordVO" items="${recordlist}">
				<tr>
					<td>${memVO.mem_name}</td>
					<td>${good_recordVO.good_rec_des}</td>
					<c:if test="${good_recordVO.good_rec_des == '商城購物' }">
						<td><font color="red">-${good_recordVO.good_rec_poi}</font></td>
					</c:if>
					<c:if test="${good_recordVO.good_rec_des != '商城購物' }">
						<td><font color="green">${good_recordVO.good_rec_poi}</font></td>
					</c:if>
					<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${good_recordVO.good_rec_dat}"/></td>
				</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div>

<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
</body>
</html>