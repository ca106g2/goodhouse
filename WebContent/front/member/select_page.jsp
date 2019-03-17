<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.landlord.model.*"%>    
<%@ page import="com.goodhouse.member.model.*" %> 
<%
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	pageContext.setAttribute("memVO", memVO);
%>
<html>
<head>
<title>Mem: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
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
</style>

</head>
<body bgcolor='white'>
<jsp:include page="/FrontHeaderFooter/Header.jsp"/>
<table id="table-1">
   <tr><td><h3>會員中心</h3></td></tr>
</table>



<h3>會員資料管理</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
 
  

<li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/member/mem.do" >
        
        <b>輸入會員編號 (如:M000000001):</b>
        <input type="text" name="mem_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="memSvc" scope="page" class="com.goodhouse.member.model.MemService" />
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/member/mem.do" >
       <b>選擇會員email</b>
       <select size="1" name="mem_id">
         <c:forEach var="memVO" items="${memSvc.all}" > 
          <option value="${memVO.mem_id}">${memVO.mem_email}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/member/mem.do" >
        
        <b>輸入會員email :</b>
        <input type="text" name="mem_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>
  
  
</ul>


<h3>會員註冊</h3>

<ul>
  <li><a href='addMem.jsp'>註冊</a> a new Member.</li>
</ul>
<jsp:useBean id="landSvc" scope="page" class="com.goodhouse.landlord.model.LanService"/>
<h3>房東管理</h3>
<c:choose>
	<c:when test="${landSvc.getOneLanByMemId(memVO.mem_id).lan_id == null}">
		<ul>
		  <li><a href='<%=request.getContextPath()%>/front/landlord/addLan.jsp'>申請成為房東</a> a new Landlord.</li>
		</ul>
	</c:when>
	<c:otherwise>
		<c:choose>
			<c:when test="${landSvc.getOneLanByMemId(memVO.mem_id).lan_accountstatus == 1}">
				<input type="button" value="申請房屋物件" class="btn btn-success" disabled="true">
			</c:when>
			<c:otherwise>
				<input type="button" value="申請房屋物件" class="btn btn-success">
			</c:otherwise>
		</c:choose>
	</c:otherwise>
</c:choose>


	<c:if test="${landSvc.getOneLanByMemId(memVO.mem_id).lan_lan_accountstatus eq 2}">
		<ul>
		  <li><a href='<%=request.getContextPath()%>/front/member/select_page.jsp'>123</a> a new Landlord.</li>
		</ul>
	</c:if>
	

<ul>
	<li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/landlord/lan.do" >
        
        <b>輸入房東編號 (如:L000000001):</b>
        <input type="text" name="lan_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>
</ul>
<jsp:include page="/FrontHeaderFooter/Footer.jsp"/>
</body>
</html>