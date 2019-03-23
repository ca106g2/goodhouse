<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.goodhouse.member.model.*"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sun.org.apache.xerces.internal.impl.dv.util.Base64"%>
<%
  MemVO memVO = (MemVO) request.getAttribute("memVO"); 
%>

<html>
<head>


<meta charset="UTF-8">
<title>會員資料 - listOneMem.jsp</title>


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
	width: 600px;
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
  	width:200px;
  	height:200px;
  
  }
  
</style>

</head>
<body bgcolor='white'>
<jsp:include page="/BackHeaderFooter/Header.jsp"/>

<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<table class="table table-hover">
	<tr>
		<th>會員編號</th>
		<th>會員姓名</th>
		<th>會員生日</th>
<!-- 		<th>會員密碼</th> -->
		<th>會員地址</th>
		<th>會員郵遞區號</th>
		<th>會員電話</th>
		<th>會員手機</th>
		<th>會員信箱</th>
		<th>會員狀態</th>
		<th>會員照片</th>
		<th>積分分數總合</th>
		<th>會員性別</th>
<!-- 		<th>修改</th> -->
	
	</tr>
	<tr>
		<td><%=memVO.getMem_id()%></td>
		<td><%=memVO.getMem_name()%></td>
		<td><%=memVO.getMem_birthday()%></td>
<%-- 		<td><%=memVO.getMem_password()%></td> --%>
		<td><%=memVO.getMem_address()%></td>
		<td><%=memVO.getMem_zipcode()%></td>
		<td><%=memVO.getMem_telephone()%></td>
		<td><%=memVO.getMem_phone()%></td>
		<td><%=memVO.getMem_email()%></td>
		<%
			if(memVO.getMem_status().equals("1")){
		%>
			<td>一般會員</td>				
		<%}else{%>
			<td>封鎖中</td>		
		<%}%>

			<%
				byte b[] = null;
   				String encoding = null;
				b = memVO.getMem_picture();	
				encoding = Base64.encode(b);
			%>
				<td><img id= bc src="data:image/jpg;base64,<%=encoding %>"></td>		
		<td><%=memVO.getGood_total()%></td>
		<%
			if(memVO.getMem_sex().equals("1")){
		%>
			<td>男</td>				
		<%}else{%>
			<td>女</td>		
		<%}%>
		<td>
<%-- 		<FORM METHOD ="post" ACTION="<%=request.getContextPath()%>/back/employee/emp.do" style="margin-bottom: 0px;"> --%>
<!-- 				<input type="submit" value="修改"> -->
<%-- 				<input type="hidden" name="mem_id"  value="${memVO.mem_id}"> --%>
<%-- 				<input type="hidden" name="mem_name"  value="${memVO.mem_name}"> --%>
<%-- 				<input type="hidden" name="mem_birthday"  value="${memVO.mem_birthday}"> --%>
<%-- 				<input type="hidden" name="mem_password"  value="${memVO.mem_password}"> --%>
<%-- 				<input type="hidden" name="mem_address"  value="${memVO.mem_address}"> --%>
<%-- 				<input type="hidden" name="mem_zipcode"  value="${memVO.mem_zipcode}"> --%>
<%-- 				<input type="hidden" name="mem_telephone"  value="${memVO.mem_telephone}"> --%>
<%-- 				<input type="hidden" name="mem_phone"  value="${memVO.mem_phone}"> --%>
<%-- 				<input type="hidden" name="mem_email"  value="${memVO.mem_email}"> --%>
<%-- 				<input type="hidden" name="mem_status"  value="${memVO.mem_status}"> --%>
<%-- 				<input type="hidden" name="good_total"  value="${memVO.good_total}"> --%>
<%-- 				<input type="hidden" name="mem_sex"  value="${memVO.mem_sex}"> --%>
<!-- 		     	<input type="hidden" name="action"	value="getOne_For_Update_emp"></FORM> -->
<!-- 		</td> -->

	
	
	</tr>
</table>



<jsp:include page="/BackHeaderFooter/Footer.jsp"/>
</body>

<link   rel="stylesheet" type="text/css" href="datetimepicker/jquery.datetimepicker.css" />
<script src="datetimepicker/jquery.js"></script>
<script src="datetimepicker/jquery.datetimepicker.full.js"></script>


</html>