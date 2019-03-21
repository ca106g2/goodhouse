<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.goodhouse.rental_message.model.*"%>
<%@ page import="com.goodhouse.member.model.*" %>
<%@ page import="com.goodhouse.landlord.model.*" %>
<%@ page import="com.goodhouse.house.model.*" %>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	LanService lanSvc = new LanService();
	LanVO lanVO = lanSvc.getOneLanByMemId(memVO.getMem_id());
	String lan_id = lanVO.getLan_id();
	
	HouseService houSvc = new HouseService();
	HouseVO houVO = houSvc.getOneByLanId(lan_id);
	String hou_id = houVO.getHou_id();
	
	
    RentMessService rentMessSvc = new RentMessService();
    List<RentMessVO> list = rentMessSvc.getPart(hou_id);
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>租屋問題列表 - listPartRentMess.jsp</title>

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
</style>

</head>
<body bgcolor='white'>

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>ψ<%= houVO.getHou_name() %>ψ租屋問題列表 - listPartRentMess.jsp</h3>
		 <span>ψ<%= memVO.getMem_name() %>ψ房東liho</span>
		 <h4><a href="<%=request.getContextPath()%>/front/index.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
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
		<th>租屋留言編號</th>
		<th>會員編號</th>
		<th>留言日期</th>
		<th>提問內容</th>
		<th>回覆內容</th>
	</tr>
    <%@ include file="page1.file" %> 
	<c:forEach var="rentMessVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${rentMessVO.ren_mes_id}</td>
			<td>${rentMessVO.mem_id}</td>
			<td><fmt:formatDate value="${rentMessVO.ren_mes_time}" pattern="yyyy-MM-dd"/></td>
			<td>${rentMessVO.ren_mes_request}</td>
			<td>${rentMessVO.ren_mes_response}</td> 
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/rentMess/rentMess.do" style="margin-bottom: 0px;">
			     <input type="submit" value="回覆">
			     <input type="hidden" name="ren_mes_id"  value="${rentMessVO.ren_mes_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/rentMess/rentMess.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="ren_mes_id"  value="${rentMessVO.ren_mes_id}"><!-- hidden表示看不到 但因為submit的關係，所以按下按鈕後整個Form表單會一起被送出。 -->
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>

</html>