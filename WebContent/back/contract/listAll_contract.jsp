<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.goodhouse.contract.model.*"%>

<%
    ContractService conSvc = new ContractService();
    List<ContractVO> list = conSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>合約分類資料 - listAll_contract.jsp</title>
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
		 <h3>合約分類資料 - listAll_contract.jsp</h3>
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
		<th>合約分類編號</th>
		<th>合約分類名稱</th>
		<th>合約內容</th>
		<th>合約分類狀態</th>
		<th>修改/停用</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="conVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${conVO.con_id}</td>
			<td>${conVO.con_name}</td>
			<td>${conVO.con_content}</td>
			<td>${conVO.con_status}</td>	
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back/contract/contract.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改/停用">
			     <input type="hidden" name="con_id"  value="${conVO.con_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>