<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>AD_REKPORT(SELECT_PAGE)</title>
<style>

</style>
</head>
<body bgcolor='white' span style="font-family:Microsoft JhengHei;">
<table id="table-1">
<tr>
	<td><h3>廣告檢舉</h3>
		  <h4>M_V_C</h4></td>
	</tr>
</table>
<c:if test="${not empty errorMsgs }">
	<font style="color:red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color:red">${message}</li>
			</c:forEach>
		</ul>
		</c:if>
	<ul>
		<li><a href='listAllAd_report.jsp'>List</a> 全部廣告檢舉<br><br></li>
		<li>
			<form method="post" action="ad_rep.do">
			<b>請輸入檢舉編號</b>
			<input type="text" name="ad_rep_id">
			<input type="hidden"name="action" value="getOne_Ad_report">
			<input type="submit"value="送出查詢">
			</form>
		</li>
		
		<jsp:useBean id="ad_repSvc" scope="page" class="com.goodhouse.ad_report.model.Ad_reportService"/>
		<li>
			<form method="post" action="ad_rep.do">
				<b>檢舉狀態</b><select size="1" name="ad_rep_id">
				<c:forEach var="ad_repVO" items="${ad_repSvc.all}">
					<option value="${ad_repVO.ad_rep_id}">${ad_repVO.ad_rep_id}
				</c:forEach>
				</select><input type="hidden" name="action" value="getOne_Ad_report">
				<input type="submit" value="送出查詢">
			</form>
		</li>
	</ul>
<ul>
	<li><a href="addAd_report.jsp">舉報廣告不實</a></li>
</ul>
</body>
</html>