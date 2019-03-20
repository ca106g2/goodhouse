<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.sun.org.apache.xerces.internal.impl.dv.util.Base64"%>
<%@ page import="com.goodhouse.landlord.model.*"%>    
<%@ page import="com.goodhouse.member.model.*" %> 
<%
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	pageContext.setAttribute("memVO", memVO);
%>
<html>
<head>
<title>Mem</title>

<style>
  table#table-1 {
	width: 1100px;
	background-color: green;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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
  
    #bc{
  	width:200px;
  	height:200px;
  
  }
  
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
  
</style>

</head>
<body bgcolor='white'>
<jsp:include page="/FrontHeaderFooter/Header.jsp"/>
<table id="table-1">
   <tr><td><h3>會員中心</h3></td></tr>
</table>

<c:if test="${empty memVO}">	
<h3>會員資料管理</h3>
<b>請輸入Email查詢個人資訊</b>

<c:if test="${not empty memVO}">	
<%-- 錯誤表列 --%>

	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
<!-- <li> -->
<%--     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/member/mem.do" > --%>
        
<!--         <b>輸入會員編號 (如:M000000001):</b> -->
<!--         <input type="text" name="mem_id"> -->
<!--         <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--         <input type="submit" value="送出"> -->
<!--     </FORM> -->
<!--   </li> -->

  <jsp:useBean id="memSvc" scope="page" class="com.goodhouse.member.model.MemService" />

  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/member/mem.do" >
        
        <b>輸入會員email :</b>
        <input type="text" name="mem_email">
        <input type="hidden" name="action" value="getOne_For_Display_Email">
        <input type="submit" value="送出">
    </FORM>
  </li>
 </ul>
</c:if>
<c:if test="${not empty memVO}">
<h3>基本會員資料</h3>
<table>
	<tr>
		<th>會員編號</th>
		<th>會員姓名</th>
		<th>會員生日</th>
		<th>會員密碼</th>
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
	<tr>
		<td><%=memVO.getMem_id()%></td>
		<td><%=memVO.getMem_name()%></td>
		<td><%=memVO.getMem_birthday()%></td>
		<td><%=memVO.getMem_password()%></td>
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
		
<%-- 		<td><%=memVO.getMem_picture()%></td>			 --%>
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
		<FORM METHOD ="post" ACTION="<%=request.getContextPath()%>/front/member/mem.do" style="margin-bottom: 0px;">
				<input type="submit" value="修改">
				<input type="hidden" name="mem_id"  value="${memVO.mem_id}">
				<input type="hidden" name="mem_name"  value="${memVO.mem_name}">
				<input type="hidden" name="mem_birthday"  value="${memVO.mem_birthday}">
				<input type="hidden" name="mem_password"  value="${memVO.mem_password}">
				<input type="hidden" name="mem_address"  value="${memVO.mem_address}">
				<input type="hidden" name="mem_zipcode"  value="${memVO.mem_zipcode}">
				<input type="hidden" name="mem_telephone"  value="${memVO.mem_telephone}">
				<input type="hidden" name="mem_phone"  value="${memVO.mem_phone}">
				<input type="hidden" name="mem_email"  value="${memVO.mem_email}">
				<input type="hidden" name="mem_status"  value="${memVO.mem_status}">
				<input type="hidden" name="good_total"  value="${memVO.good_total}">
				<input type="hidden" name="mem_sex"  value="${memVO.mem_sex}">
		     	<input type="hidden" name="action"	value="getOne_For_Update"></FORM>
		</td>
		
	</tr>
</table>

<form method="post" action="<%=request.getContextPath()%>/FrontLogoutHandler">
	<input type="submit" value="登出">
</form>


</c:if>




<h3>會員註冊</h3>

<ul>
<c:choose>
	<c:when test="${not empty memVO}">
 		 <li><input type="button" value="註冊" onclick="location.href='addMem.jsp'" disabled></li>
  	</c:when>
  	<c:otherwise>
  		<li><input type="button" value="註冊" onclick="location.href='addMem.jsp'"></li>
  	</c:otherwise>
</c:choose>
</ul>
<jsp:useBean id="landSvc" scope="page" class="com.goodhouse.landlord.model.LanService"/>
<h3>房東管理</h3>
<h5>若要申請房東，請先登入會員</h5>
<c:if test="${not empty memVO}">
	<c:if test="${landSvc.getOneLanByMemId(memVO.mem_id).lan_accountstatus == 1}">
		<form method="post" action="<%=request.getContextPath()%>/front/landlord/addLan.jsp">
   			<input type="submit" value="申請房屋物件" class="btn btn-success" disabled>
		</form>
	</c:if>
	<c:if test="${landSvc.getOneLanByMemId(memVO.mem_id).lan_accountstatus == 2}">
		<input type="submit" value="申請房屋物件" class="btn btn-success" >
	</c:if>
	<c:if test="${landSvc.getOneLanByMemId(memVO.mem_id).lan_accountstatus == null}">
		<form method="post" action="<%=request.getContextPath()%>/front/landlord/addLan.jsp">
   			<input type="submit" value="申請成為房東" class="btn btn-success" >
		</form>
	</c:if>
</c:if>






<%-- <c:choose> --%>
<%-- 	<c:when test="${landSvc.getOneLanByMemId(memVO.mem_id).lan_id == null}"> --%>
<!-- 		<ul> -->
<%-- 		  <li><a href='<%=request.getContextPath()%>/front/landlord/addLan.jsp'>申請成為房東</a> a new Landlord.</li> --%>
<!-- 		</ul> -->
<%-- 	</c:when> --%>
<%-- 	<c:otherwise> --%>
<%-- 		<c:choose> --%>
<%-- 			<c:when test="${landSvc.getOneLanByMemId(memVO.mem_id).lan_accountstatus == 1}"> --%>
<!-- 				<input type="button" value="申請房屋物件" class="btn btn-success" disabled> -->
<%-- 			</c:when> --%>
<%-- 			<c:otherwise> --%>
<!-- 				<input type="button" value="申請房屋物件" class="btn btn-success"> -->
<%-- 			</c:otherwise> --%>
<%-- 		</c:choose> --%>
<%-- 	</c:otherwise> --%>
<%-- </c:choose> --%>



<jsp:include page="/FrontHeaderFooter/Footer.jsp"/>
</body>
</html>