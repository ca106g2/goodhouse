<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.goodhouse.appointment.model.*"%>
<%@ page import="com.goodhouse.member.model.*" %>
<%@ page import="com.goodhouse.landlord.model.*" %>
<%@ page import="com.goodhouse.house.model.*" %>
<%-- 此頁練習採用 EL 的寫法取值 --%>


<%
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	String mem_id = memVO.getMem_id();
	
	LanService lanSvc = new LanService();
	LanVO lanVO = lanSvc.getOneLanByMemId(mem_id);
	String lan_id = lanVO.getLan_id();

%>

<%
    AppointService appointSvc = new AppointService();
    List<AppointVO> list = appointSvc.getPartLan(lanVO.getLan_id());
//     List<AppointVO> list = appointSvc.getPartLan("L000000005");
    pageContext.setAttribute("list",list);
%>



<jsp:useBean id="memSvc" scope="page" class="com.goodhouse.member.model.MemService" />
<jsp:useBean id="lanSvc2" class="com.goodhouse.landlord.model.LanService"></jsp:useBean>
<jsp:useBean id="houSvc" class="com.goodhouse.house.model.HouseService" />


<html>
<head>
<title>房東的預約看房行程表 - listPart_lanAppoint.jsp</title>
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
		 <h3><%= memVO.getMem_name()%>房東的帶客看房行程表 - listPart_lanAppoint.jsp</h3>
		 <h4><a href="<%=request.getContextPath() %>/front/index.jsp"><img src="<%=request.getContextPath() %>/front/appoint/images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
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
		<th>預約看房日期</th>
<!-- 		<th>預約時段</th> -->
		<th>房屋名稱</th>
		<th>房屋地址</th>
		<th>房租</th>
		<th>預約狀態</th>
		<th>取消預約</th>
		<th>會員編號</th>
		<th>會員姓名</th>
		<th>檢舉會員</th>
	</tr>
    <%@ include file="page1.file" %> 
	<c:forEach var="appointVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	<tr>
		<td><fmt:formatDate value="${appointVO.hou_app_date}" pattern="yyyy-MM-dd"/></td>
<!-- 		<td> -->
<%-- 				<c:if test="${appointVO.hou_app_time.equals('A1')}"> --%>
<!-- 					早上	 -->
<%-- 				</c:if> --%>
<%-- 				<c:if test="${appointVO.hou_app_time.equals('A2')}"> --%>
<!-- 					下午	 -->
<%-- 				</c:if> --%>
<%-- 				<c:if test="${appointVO.hou_app_time.equals('A3')}"> --%>
<!-- 					晚上	 -->
<%-- 				</c:if> --%>
<!-- 		</td> -->
			<td>
				<c:forEach var="houVO" items="${houSvc.all}">
						<c:if test="${appointVO.hou_id == houVO.hou_id}">
								${houVO.hou_name}
						</c:if>
				</c:forEach>
			</td>
			<td>
				<c:forEach var="houVO" items="${houSvc.all}">
						<c:if test="${appointVO.hou_id == houVO.hou_id}">
								${houVO.hou_address}
						</c:if>
				</c:forEach>
			</td>
			
			<td>
				<c:forEach	var="houVO" items="${houSvc.all}">
					<c:if test="${appointVO.hou_id == houVO.hou_id }">
								${houVO.hou_rent}
					</c:if>
				</c:forEach>				
			</td>
			
			<td>
				<c:if test="${appointVO.app_status.equals('A0')}">
					已預約	
				</c:if>
				<c:if test="${appointVO.app_status.equals('A1')}">
					取消預約
				</c:if>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/appoint/appoint.do" style="margin-bottom: 0px;">
			     <input type="submit" value="取消預約">
			     <input type="hidden" name="appoint_id"  value="${appointVO.appoint_id}">
			     <input type="hidden" name="action"	value="deleteLan"></FORM>
			</td>
			<td>
				<c:forEach var="lanVO" items="${lanSvc2.all }">
					<c:forEach var="memVO" items="${memSvc.all }">
						<c:if test="${appointVO.lan_id == lanVO.lan_id }">
							<c:if test="${appointVO.mem_id == memVO.mem_id}">
								${memVO.mem_id}
							</c:if>
						</c:if>
					</c:forEach>
				</c:forEach>
			</td>
			<td>
				<c:forEach var="lanVO" items="${lanSvc2.all}">
					<c:forEach var="memVO" items="${memSvc.all }">
						<c:if test="${appointVO.lan_id == lanVO.lan_id }">
							<c:if test="${appointVO.mem_id == memVO.mem_id}">
								${memVO.mem_name }
							</c:if>
						</c:if>
					</c:forEach>
				</c:forEach>
			</td>
<!-- 			<td> -->
<!-- 				<input type="button" value="檢舉會員"> -->
<!-- 			</td> -->
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
</body>
<!-- //git上傳註解用無意義 -->
</html>