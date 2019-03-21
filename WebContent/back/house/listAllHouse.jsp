<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.goodhouse.house.model.*"%>
<%@ page import="java.sql.*, javax.sql.*" %> 
<%
	HouseService houSvc = new HouseService();
	List<HouseVO> list = houSvc.getAll();
	pageContext.setAttribute("list",list);
%>
<jsp:useBean id="lanSvc" scope="page" class="com.goodhouse.landlord.model.LanService"/>
<jsp:useBean id="memSvc" scope="page" class="com.goodhouse.member.model.MemService"/>

<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<style>
 #table1 { 

  font-family: 微軟正黑體; 
  font-size:16px; 
  width:1500px;
  border:2px solid #000;
  text-align:center;
  border-collapse:collapse;
  margin-left:auto; 
  margin-right:auto;
} 
 #table1 th { 
  background-color: #009FCC;
  padding:10px;

  color:#fff;
  border:2px solid #000;
} 
 #table1 td { 
  border:1px solid #000;
  padding:5px;
} 


</style>

</head>
<body bgcolor='white'>
</head>


<jsp:include page="/BackHeaderFooter/Header.jsp" />	
	<body> 

<div class="container-fluid">
<div class="row">
<div class="col-2">
 <jsp:include page="/back/house/select_page.jsp" />
    
</div>
 <div class="col-10">
	<c:if test="${not empty errorMsgs}">
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
	</c:if>
<br><br>
<table id="table1">
	<tr class="table-black" >
		 <th>房屋名稱</th>
		<th>房屋型別</th>
		<th>房屋坪數</th>
		<th>房屋狀態</th>
		<th>房屋審核 </th>
		<th>是否可開火</th>
		<th>是否有管理費</th>
		<th>房屋地址</th>
		<th>房東名子</th>
		<th>房屋租金</th>
		<th>備註</th>
		<th>修改</th>
		<th>去看看</th>
	</tr>
	
	<%@ include file="pages/page1.file"%>
	<c:forEach var="houVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1 %>">
	 	<tr  class="table-info" align='center' >
			<td>${houVO.hou_name}</td>
			<td>${houVO.hou_type}</td>
			<td>${houVO.hou_size}</td>
			<td>${houVO.hou_property}</td>
			<td>${houVO.hou_parkspace}</td>
			<td>${houVO.hou_cook}</td>
			<td>${houVO.hou_managefee}</td>
			<td>${houVO.hou_address}</td>
			<td>${memSvc.getOneMem(lanSvc.getOneLan(houVO.lan_id).mem_id).mem_name}</td>
			<td>${houVO.hou_rent}</td>
			<td>${houVO.hou_note}</td>
			<td>
					  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back/house/hou.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="hou_id"  value="${houVO.hou_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			<form method="post" action="<%=request.getContextPath()%>/back/house/hou.do" style="margin-bottom: 0px;">
			<input type="submit" value="去看看">
			<input type="hidden" name="hou_id" value="${houVO.hou_id}">
			<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
			<input type="hidden" name="whichPage" value="<%=whichPage%>">
			<input type="hidden" name="action" value="getOne_For_Display">
			</form>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2.file" %>
	</div>
</div>
</div>
	<jsp:include page="/BackHeaderFooter/Footer.jsp" />	
</body>
</html>