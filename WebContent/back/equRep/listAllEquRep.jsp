<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.goodhouse.equipment_repair.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    EquRepService equRepSvc = new EquRepService();
    List<EquRepVO> list = equRepSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有租屋訊息資料 - listAllEquRep.jsp</title>

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
		 <h3>所有設備維修資訊 - listAllEquRep.jsp</h3>
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
		<th>設備報修編號</th>
		<th>房屋編號</th>
		<th>會員編號</th>
		<th>房東編號</th>
		<th>維修申請時間</th>
		<th>維修廠商</th>
		<th>維修廠商電話</th>
		<th>維修設備</th>
		<th>維修設備照片</th>
		<th>維修說明</th>
		<th>維修狀態</th>
		<th>預定維修時間</th>
		<th>維修完成時間</th>
	</tr>
    <%@ include file="page1.file" %> 
	<c:forEach var="equRepVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${equRepVO.equ_rep_id}</td>
			<td>${equRepVO.hou_id}</td>
			<td>${equRepVO.mem_id}</td>
			<td>${equRepVO.lan_id}</td>
			<td><fmt:formatDate value="${equRepVO.equ_rep_accetime}" pattern="yyyy-MM-dd"/></td>
			<td>${equRepVO.equ_rep_staff}</td>
			<td>${equRepVO.equ_rep_staffphone}</td> 
			<td>${equRepVO.equ_rep_event}</td>
			<td><img src= "<%=request.getContextPath()%>/back/equRep/equRep.do?equ_rep_id=${equRepVO.equ_rep_id}"></td>
			<td>${equRepVO.equ_rep_descri}</td>
			<td>${equRepVO.equ_rep_status}</td>
			<td><fmt:formatDate value="${equRepVO.equ_rep_expectime}" pattern="yyyy-MM-dd"/></td>
			<td><fmt:formatDate value="${equRepVO.equ_rep_finish}" pattern="yyyy-MM-dd"/></td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back/equRep/equRep.do" style="margin-bottom: 0px;" enctype="multipart/form-data">
			     <input type="submit" value="修改">
			     <input type="hidden" name="equ_rep_id"  value="${equRepVO.equ_rep_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back/equRep/equRep.do" style="margin-bottom: 0px;" enctype="multipart/form-data">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="equ_rep_id"  value="${equRepVO.equ_rep_id}"><!-- hidden表示看不到 但因為submit的關係，所以按下按鈕後整個Form表單會一起被送出。 -->
			     <input type="hidden" name="action" value="delete"></FORM>	<!-- name為parameter value=value 等同於get的?name=value方式傳送參數 -->
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>

</html>