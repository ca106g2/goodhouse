<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.goodhouse.landlord.model.*"%>
<%@ page import="com.sun.org.apache.xerces.internal.impl.dv.util.Base64" %>


<%
LanService lanSvc = new LanService();
List<LanVO> list = lanSvc.getAll();
pageContext.setAttribute("list",list);
%>

<html>
<head>
<meta charset="UTF-8">
<title>所有房東資料</title>
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
  
  #bc{
  width:100px;
  height:100px;
 
  }
  
</style>


</head>
<body bgcolor="white">
<jsp:include page="/FrontHeaderFooter/Header.jsp"/>
<table id="table-1">
	<tr><td>
		<h3>所有房東資料 </h3>
		<h4><a href="<%=request.getContextPath()%>/back/employee/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回員工管理</a></h4>

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
		<th>房東編號</th>
		<th>會員編號</th>
		<th>房東發票</th>
		<th>房東帳號</th>
		<th>房東狀態</th>
		<th>良民證</th>
    </tr>
	<%@ include file="page1.file" %>
	<c:forEach var="lanVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

	<tr>
		<td>${lanVO.lan_id}</td>
		<td>${lanVO.mem_id}</td>
		<td>${lanVO.lan_receipt}</td>
		<td>${lanVO.lan_account}</td>
		<td>${lanVO.lan_accountstatus}</td>
		<c:set var="lanVO" value="${lanVO}"/>
								<%
				byte b[] = null;
				b = ((LanVO)pageContext.getAttribute("lanVO")).getLan_ciziten();	
				String encoding = null;
				if(b != null){
				encoding = Base64.encode(b);
			%>
				<td><img id="bc" src="data:image/jpg;base64,<%=encoding %>"></td>
			<%
				}%>
		
		
		
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/landlord/lan.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="lan_id"  value="${lanVO.lan_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/landlord/lan.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="lan_id"  value="${lanVO.lan_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
		</td>	
	</tr>
</c:forEach>
</table>

<%@ include file="page2.file" %>
<jsp:include page="/FrontHeaderFooter/Footer.jsp"/>
</body>
</html>