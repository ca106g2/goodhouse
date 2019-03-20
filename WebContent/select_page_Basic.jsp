<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="javax.naming.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.goodhouse.appointment.model.*" %>

<%-- <% --%>
<!-- // AppointService appointSvc = new AppointService(); -->
<!-- // List<AppointVO> list = appointSvc.getAll(); -->
<!-- // pageContext.setAttribute("list",list); -->
<%-- %> --%>
<%-- <jsp:useBean id="memSvc" scope="page" class="com.goodhouse.member.model.MemService" /> --%>
<%-- <jsp:useBean id="lanSvc" scope="page" class="com.goodhouse.landlord.model.LanService" /> --%>
<%-- <jsp:useBean id="houSvc" class="com.goodhouse.house.model.HouseService" /> --%>



<HTML>
<HEAD>
<TITLE> 查詢員工資料 </TITLE>
<style>

a:visited, a:link {
	color: blue;
	text-decoration: none;
}
a:hover, a:active {
	color: red;
	text-decoration: none;
}
</style>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/font-awesome-4.5.0/css/font-awesome.css">
</HEAD>
<BODY>
<table>
	<tr>
	<td>選擇預約日期</td>
	</tr>
</table>
</BODY>
</HTML>