<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.goodhouse.house_evaluate.model.*"%>

<%
    House_EvaluateService heSvc = new House_EvaluateService();
    List<House_EvaluateVO> list = heSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>房屋評價-listAllHouse_Evaluate</title>
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
  
  table {
	width: 1000px;
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
			 <h3>所有房屋評價資料 - listAllHouse_Evaluate.jsp</h3>
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
			<th>房屋評價編號</th>
			<th>評價者會員編號</th>
			<th>被評價房屋編號</th>
			<th>評價等級</th>
			<th>評價內容</th>
			<th>修改</th>
			<th>刪除</th>
		</tr>
		
		<%@ include file="page1.file" %>
		<c:forEach var="House_EvaluateVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
			<tr>
				<td>${House_EvaluateVO.hou_eva_id}</td>
				<td>${House_EvaluateVO.mem_id}</td>
				<td>${House_EvaluateVO.hou_id}</td>
				<td>${House_EvaluateVO.hou_eva_grade}</td>
				<td>${House_EvaluateVO.hou_eva_content}</td>
				<td>
				  <FORM METHOD="post" ACTION="house_evaluate.do" style="margin-bottom: 0px;">
				     <input type="submit" value="修改">
				     <input type="hidden" name="hou_eva_id"  value="${House_EvaluateVO.hou_eva_id}">
				     <input type="hidden" name="action"	value="getOne_For_Update">
				  </FORM>
				</td>
				<td>
				  <FORM METHOD="post" ACTION="house_evaluate.do" style="margin-bottom: 0px;">
				     <input type="submit" value="刪除">
				     <input type="hidden" name="hou_eva_id"  value="${House_EvaluateVO.hou_eva_id}">
				     <input type="hidden" name="action" value="delete">
				  </FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file" %>

</body>
</html>