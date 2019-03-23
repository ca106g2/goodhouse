<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.goodhouse.member.model.*"%>
<%@ page import="com.sun.org.apache.xerces.internal.impl.dv.util.Base64" %>

<%	
MemService memSvc = new MemService();
List<MemVO> list =memSvc.getAll();
pageContext.setAttribute("list",list);
%>



<html>
<head>
<meta charset="UTF-8">

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
  	width:200px;
  	height:200px;
  	
  }
  
</style>


</head>
<body bgcolor="white">
<jsp:include page="/BackHeaderFooter/Header.jsp"/>	

<div class="container-fluid">
	<div class="row col-12">
		<c:if test="${not empty errorMsgs }">
				<c:forEach var="message" items="${errorMsgs}">
					<p style="color:red">${message}</p><br>
				</c:forEach>
		</c:if>
	</div>
	<div class="row col-12">
		<table class="table table-hover">
			<tr>
				<th>會員編號</th>
				<th>會員姓名</th>
				<th>會員生日</th>
<!-- 				<th>會員密碼</th> -->
				<th>會員地址</th>
				<th>會員郵遞區號</th>
				<th>會員電話</th>
				<th>會員手機</th>
				<th>會員信箱</th>
				<th>會員狀態</th>
				<th>會員照片</th>
				<th>積分分數總合</th>
				<th>會員性別</th>
				<th>修改</th>
				
			</tr>
			<%@ include file="page1.file" %>
			<c:forEach 	var="memVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				<tr>
					<td>${memVO.mem_id}</td>
					<td>${memVO.mem_name}</td>
					<td>${memVO.mem_birthday}</td>
<%-- 					<td>${memVO.mem_password}</td> --%>
					<td>${memVO.mem_address}</td>
					<td>${memVO.mem_zipcode}</td>
					<td>${memVO.mem_telephone}</td>
					<td>${memVO.mem_phone}</td>
					<td>${memVO.mem_email}</td>
					<c:if test="${memVO.mem_status==1}">
						<td>一般會員</td>
					</c:if>
					<c:if test="${memVO.mem_status==2}">
						<td>封鎖中</td>
					</c:if>
					<c:set var="memVO" value="${memVO}"/>
										<%
						byte b[] = null;
						b = ((MemVO)pageContext.getAttribute("memVO")).getMem_picture();	
						String encoding = null;
						if(b != null){
						encoding = Base64.encode(b);
					%>
						<td><img id="bc" src="data:image/jpg;base64,<%=encoding %>"></td>
					<%
						}%>
					
					<td>${memVO.good_total}</td>
					<c:if test="${memVO.mem_sex==1}">
						<td>男</td>
					</c:if>
					<c:if test="${memVO.mem_sex==2}">
						<td>女</td>
					</c:if>
					<td>
						<FORM METHOD ="post" ACTION="mem.do" style="margin-bottom: 0px;">
							<input type="submit" value="修改">
							<input type="hidden" name="mem_id"  value="${memVO.mem_id}">
					     	<input type="hidden" name="action"	value="getOne_For_Update_emp"></FORM>
					</td>
		
				</tr>
			</c:forEach>
		</table>
	</div>


<%@ include file="page2.file" %>
</div>

<jsp:include page="/BackHeaderFooter/Footer.jsp"/>
</body>
</html>