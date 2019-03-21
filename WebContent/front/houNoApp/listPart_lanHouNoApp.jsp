<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.goodhouse.house_noappointment.model.*"%>
<%@ page import="com.goodhouse.member.model.*" %>
<%@ page import="com.goodhouse.landlord.model.*" %>
<%-- 此頁練習採用 EL 的寫法取值 --%>


<%
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	
	LanService lanSvc = new LanService();
	LanVO lanVO = lanSvc.getOneLanByMemId(memVO.getMem_id());
    HouNoAppService houNoAppSvc = new HouNoAppService();
//     HouNoAppVO houNoAppVO = new HouNoAppVO();
    List<HouNoAppVO> list = houNoAppSvc.getPart(lanVO.getLan_id());
//     List<HouNoAppVO> list = houNoAppSvc.getPart("L000000007");
    pageContext.setAttribute("list",list);
%>

<jsp:useBean id="houSvc" scope="page" class="com.goodhouse.house.model.HouseService"/>


<html>
<head>
<title>所有租屋訊息資料 - listAllHouNoApp.jsp</title>

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
<jsp:include page="/FrontHeaderFooter/Header.jsp" />

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3><%= memVO.getMem_name() %>房東的不可預約行程 - listPart_lanHouNoApp.jsp</h3>
		 <h4><a href="<%=request.getContextPath() %>/front/index.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
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
		<th>房屋編號</th>
		<th>房屋名稱</th>
		<th>房屋地址</th>
		<th>租金</th>
		<th>預約日期</th>
		<th>取消不可預約</th>
	</tr>
    <%@ include file="page1.file" %> 
	<c:forEach var="houNoAppVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
				<td>
					${houNoAppVO.hou_id}
				</td>
				
				<td>
					<c:forEach var="houVO" items="${houSvc.all}">
						<c:if test="${houNoAppVO.hou_id == houVO.hou_id}">
								${houVO.hou_name}
						</c:if>
					</c:forEach>
				</td>
				<td>
					<c:forEach var="houVO" items="${houSvc.all}">
						<c:if test="${houNoAppVO.hou_id == houVO.hou_id}">
								${houVO.hou_address}
						</c:if>
					</c:forEach>
				</td>
				<td>
					<c:forEach var="houVO" items="${houSvc.all}">
						<c:if test="${houNoAppVO.hou_id == houVO.hou_id}">
								${houVO.hou_rent}
						</c:if>
					</c:forEach>
				</td>
				<td><fmt:formatDate value="${houNoAppVO.hou_noapp_date}" pattern="yyyy-MM-dd"/></td>
				<td>
				  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/houNoApp/houNoApp.do" style="margin-bottom: 0px;">
				     <input type="submit" value="取消不可預約">
				     <input type="hidden" name="hou_noapp_id"  value="${houNoAppVO.hou_noapp_id}"><!-- hidden表示看不到 但因為submit的關係，所以按下按鈕後整個Form表單會一起被送出。 -->
				     <input type="hidden" name="action" value="delete"></FORM>
				</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
</body>

</html>