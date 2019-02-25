<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ page import="java.util.*" %>
<%@ page import="com.goodhouse.ad_report.model.*" %>
<%
Ad_reportService ad_repSvc = new Ad_reportService();
List<Ad_reportVO> list = ad_repSvc.getAll();
pageContext.setAttribute("list",list);
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
  table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body>
<table id="table-1">
	<tr><td>
		<h3>檢舉總表</h3>
		<h4><a href="select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤</font>
	<ul>
	<c:forEach var="message" items="${errorMsgs}">
		<li style="color:red">${message}</li>
	</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>廣告檢舉編號</th>
		<th>被檢舉廣告</th>
		<th>檢舉房客</th>
		<th>處理員工</th>
		<th>檢舉內容</th>
		<th>檢舉狀態</th>
		<th>檢舉日期</th>
	</tr>
	<%@ include file="page1.file" %>
	<c:forEach var="ad_repVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	
	<tr>
		<td>${ad_repVO.ad_rep_id}</td>
		<td>${ad_repVO.ad_id}</td>
		<td>${ad_repVO.mem_id}</td>
		<td>${ad_repVO.emp_id}</td>
		<td>${ad_repVO.ad_rep_reason}</td>
		<td>${ad_repVO.ad_rep_status}</td>
		<td>${ad_repVO.ad_rep_date}</td>
		<td>
		
		<form method="post" action="<%=request.getContextPath() %>/back/ad_report/ad_rep.do" style="margin-bottom: 0px;">
		 <input type="submit" value="修改">
		 <input type="hidden" name="ad_rep_id" value="${ad_repVO.ad_rep_id}">
		 <input type="hidden" name="action"value="getOne_For_Update">
		</form>
		</td>
	</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
</body>
</html>