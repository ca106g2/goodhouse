<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.goodhouse.permission.model.*"%>

<%
PerService perSvc = new PerService();
List<PerVO> list = perSvc.getAll();
pageContext.setAttribute("list",list);
%>



<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<table id="table-1">
	<tr><td>
		<h3>所有權限資料 </h3>
		<h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>

	</td></tr>
</table>

<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>權限編號</th>
		<th>權限姓名</th>
		
    </tr>
	<%@ include file="page1.file" %>
	<c:forEach var="perVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

	<tr>
		<td>${perVO.per_id}</td>
		<td>${perVO.per_name}</td>
		
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back/permission/per.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="per_id"  value="${empVO.emp_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back/permission/per.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="per_id"  value="${empVO.emp_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
		</td>	
	</tr>
</c:forEach>
</table>

<%@ include file="page2.file" %>
</body>
</html>