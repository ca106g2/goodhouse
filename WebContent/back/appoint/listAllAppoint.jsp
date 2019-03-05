<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.goodhouse.appointment.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    AppointService appointSvc = new AppointService();
    List<AppointVO> list = appointSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<jsp:useBean id="memSvc" scope="page" class="com.goodhouse.member.model.MemService" />


<html>
<head>
<title>所有租屋訊息資料 - listAllAppoint.jsp</title>

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
		 <h3>所有房預約行程資訊 - listAllAppoint.jsp</h3>
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
		<th>預約行程編號</th>
		<th>會員編號</th>
		<th>房東編號</th>
		<th>房屋編號</th>
		<th>預約時段</th>
		<th>預約日期</th>
		<th>預約狀態</th>
		<th>提醒看房</th>
	</tr>
    <%@ include file="page1.file" %> 
	<c:forEach var="appointVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${appointVO.appoint_id}</td>
			<td>${appointVO.mem_id}</td>
			<td><c:forEach var="memVO" items="${memSvc.all}">
                    <c:if test="${appointVO.mem_id==memVO.mem_id}">
	                    ${memVO.mem_id}【${memVO.mem_name} - ${memVO.mem_sex}】
                    </c:if>
                </c:forEach>
			</td>
			<td>${appointVO.lan_id}</td>
			<td>${appointVO.hou_id}</td>
			<td>${appointVO.hou_app_time}</td>
			<td><fmt:formatDate value="${appointVO.hou_app_date}" pattern="yyyy-MM-dd"/></td>
			<td>${appointVO.app_status}</td>
			<td>${appointVO.app_remind}</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back/appoint/appoint.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="appoint_id"  value="${appointVO.appoint_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back/appoint/appoint.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="appoint_id"  value="${appointVO.appoint_id}"><!-- hidden表示看不到 但因為submit的關係，所以按下按鈕後整個Form表單會一起被送出。 -->
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
<!-- //git上傳註解用無意義 -->
</html>