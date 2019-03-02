<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.goodhouse.house_noappointment.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    HouNoAppService houNoAppSvc = new HouNoAppService();
    List<HouNoAppVO> list = houNoAppSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有租屋訊息資料 - listAllHouNoApp.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
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
<body bgcolor='white'>

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有房屋不可預約資訊 - listAllHouNoApp.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
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
		<th>房屋不可預約編號</th>
		<th>房屋編號</th>
		<th>房東編號</th>
		<th>預約時段</th>
		<th>預約日期</th>
	</tr>
    <%@ include file="page1.file" %> 
	<c:forEach var="houNoAppVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${houNoAppVO.hou_noapp_id}</td>
			<td>${houNoAppVO.hou_id}</td>
			<td>${houNoAppVO.lan_id}</td>
			<td>${houNoAppVO.hou_noapp_time}</td>
			<td><fmt:formatDate value="${houNoAppVO.hou_noapp_date}" pattern="yyyy-MM-dd"/></td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back/houNoApp/houNoApp.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="hou_noapp_id"  value="${houNoAppVO.hou_noapp_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back/houNoApp/houNoApp.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="hou_noapp_id"  value="${houNoAppVO.hou_noapp_id}"><!-- hidden表示看不到 但因為submit的關係，所以按下按鈕後整個Form表單會一起被送出。 -->
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>