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
</head>
<body bgcolor="white">
<jsp:include page="/BackHeaderFooter/Header.jsp"/>

<div class="container-fluid">

	<div class="row col-12">
		<h4><a href="<%=request.getContextPath()%>/back/back_index.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回後台首頁</a></h4>
		<c:if test="${not empty errorMsgs}">
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<p style="color:red">${message}</p><br>
				</c:forEach>
			</ul>
		</c:if>
	</div>
	
	<div class="row col-12">
		<table>
			<tr>
				<th>房東編號</th>
				<th>會員編號</th>
				<th>房東發票</th>
				<th>房東帳號</th>
				<th>房東狀態</th>
				<th>良民證</th>
				<th>修改</th>
		    </tr>
			<%@ include file="page1.file" %>
			<c:forEach var="lanVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
			<tr>
				<td>${lanVO.lan_id}</td>
				<td>${lanVO.mem_id}</td>
				
				<c:if test="${lanVO.lan_receipt==1}">
					<td>慈善機構</td>
				</c:if>
				<c:if test="${lanVO.lan_receipt==2}">
					<td>自動兌獎</td>
				</c:if>
				
				
				
				<td>${lanVO.lan_account}</td>
				
				<c:if test="${lanVO.lan_accountstatus==1}">
					<td>審核中</td>
				</c:if>
				<c:if test="${lanVO.lan_accountstatus==2}">
					<td>已審核</td>
				</c:if>
					
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
			</tr>
		</c:forEach>
		</table>
	</div>
</div>
<%@ include file="page2.file" %>


<jsp:include page="/BackHeaderFooter/Footer.jsp"/>
</body>
</html>