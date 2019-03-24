<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.goodhouse.landlord.model.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.sun.org.apache.xerces.internal.impl.dv.util.Base64" %>
<%
	LanService lanSvc = new LanService();
	List<LanVO> list = lanSvc.getAll();
	pageContext.setAttribute("list",list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CheckLand</title>
</head>
<body>
<jsp:include page="/BackHeaderFooter/Header.jsp"/>
<div class="container">
  <div class="row col-12" >
 	
    <table border="1" class="table table-hover">
    	<tr>
    		<td>會員姓名</td>
    		<td>房東戶頭</td>
    		<td>房東狀態</td>
    		<td>良民證</td>
    		<td>修改狀態</td>
    	</tr>
    	<%@ include file="page1.file"%>
    	<jsp:useBean id="memSvc" scope="page" class="com.goodhouse.member.model.MemService" />
    	<c:forEach var="LanVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1 %>">
	    	<tr>
	    		<td>${memSvc.getOneMem(LanVO.mem_id).mem_name}</td>
	    		<td>${LanVO.lan_account}</td>
	    		<td>
	    			<c:choose>
		    			<c:when test="${LanVO.lan_accountstatus == 1}">
		    				<font color="red"><b>未審核</b></font>
		    			</c:when>
		    			<c:otherwise>
		    				<font color="green"><b>審核通過</b></font>
		    			</c:otherwise>
		    		</c:choose>
	    		</td>
	    		<td>
	    			<%
	    				byte b[] = null;
	    				String encoding = null;
	    				b = ((LanVO)pageContext.getAttribute("LanVO")).getLan_ciziten();
	    				encoding = Base64.encode(b);
	    			%>
	    				<img id="bc" src="data:image/jpg;base64,<%=encoding %>" width="100px" height="100px">
	    		</td>
	    		<td>
	    			<form action="lan.do" method="post">
	    				<input type="submit" value="修改狀態"  class="btn btn-success">
	    				<input type="hidden" name="action" value="getOne_For_Update">
	    				<input type="hidden" name="lan_id" value="${LanVO.lan_id}">
	    			</form>
	    		</td>
	    	</tr>
	    </c:forEach>
    </table>
    <%@ include file="page2.file" %>
  </div>
</div> 
<jsp:include page="/BackHeaderFooter/Footer.jsp"/>
</body>
</html>