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
	<tr style="background-color: #EDF9DE;">
<!-- 		<th>房東編號</th> -->
		<th>會員姓名</th>
		<th>房東發票</th>
		<th>房東戶頭</th>
		<th>房東狀態</th>
		<th>良民證</th>
		<th>修改</th>
    </tr>
	<%@ include file="page1.file" %>
	<jsp:useBean id="memSvc" scope="page" class="com.goodhouse.member.model.MemService"/>
	<c:forEach var="lanVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

	<tr>
<%-- 		<td>${lanVO.lan_id}</td> --%>
		<td>${memSvc.getOneMem(lanVO.mem_id).mem_name}</td>
		
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

<%@ include file="page2.file" %>
<jsp:include page="/BackHeaderFooter/Footer.jsp"/>
</body>
</html>