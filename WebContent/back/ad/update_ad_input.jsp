<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.goodhouse.house.model.*" %>
<%@ page import="com.goodhouse.ad_sort.model.*" %>
<%@ page import="com.goodhouse.ad.model.*" %>
<%@ page import="com.goodhouse.member.model.*" %>
<%@ page import="com.goodhouse.landlord.model.*" %>

<%
	AdVO adVO =(AdVO) request.getAttribute("adVO");
%>
<%-- HouseVO物件--%>
<jsp:useBean id="ad_sorSvc" scope="page" class="com.goodhouse.ad_sort.model.Ad_sortService"/>
<jsp:useBean id="lanSvc" scope="page" class="com.goodhouse.landlord.model.LanService"/>
<jsp:useBean id="houSvc" scope="page" class="com.goodhouse.house.model.HouseService"/>
<jsp:useBean id="memSvc" scope="page" class="com.goodhouse.member.model.MemService"/>

<html>
<head>

</head>
<body bgcolor='white'>
<h3><a href="<%=request.getContextPath()%>/back/ad/select_page.jsp">回首頁</a></h3>

<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

 
<form method="post" action="ad.do" name="form1">
<table>
	<tr>
		<td>廣告類別:</td>
				<td><c:forEach var="ad_sorVO" items="${ad_sorSvc.all}">
					<c:if test="${adVO.ad_sort_id eq ad_sorVO.ad_sort_id }">
						${ad_sorVO.ad_chargetype}	
					</c:if>
				</c:forEach></td>
	</tr>
<!--  	<tr>
		<td>廣告編號:</td>
		<td><%--${adVO.ad_id}--%></td>
	</tr>-->
	<tr>
		<td>房東姓名:</td>
				<td><c:forEach var="memVO" items="${memSvc.all}">
					<c:forEach var="lanVO" items="${lanSvc.all}">
						<c:if test="${lanVO.mem_id eq memVO.mem_id}">
							<c:if test="${adVO.lan_id eq lanVO.lan_id}">
								${memVO.mem_name}
							</c:if>
						</c:if>
					</c:forEach>
				</c:forEach></td>
	</tr>	
	<tr>
		<td>房屋名稱</td>
				<td><c:forEach var="houVO" items="${houSvc.all}">
					<c:if test="${adVO.hou_id eq houVO.hou_id}">
						${houVO.hou_name}
					</c:if>	
				</c:forEach></td>
	</tr>

	<tr>
		<td>廣告備註:</td>
		<td><input type="text" name="ad_forfree" size="45" value="<%=adVO.getAd_forfree()%>"/></td>
	</tr>	
	<tr>
		<td>繳費狀態:</td>
		<td>
			<select name ="ad_statue">
				<option value="以付款">以付款</option>
				<option value="付款">未付款</option>
			</select>
		</td>
	</tr>	
	<tr>
		<td>付款方式:</td>
		<td>			
			<select name ="ad_paymethod">
				<option value="信用卡">信用卡
				<option value="VISA金融卡">VISA金融卡
			</select>
		</td>
	</tr>	
	<tr>
		<td>廣告刊登日:</td>
		<td><input type="text" name="ad_date" id="f_date" value="<%=adVO.getAd_date()%>"></td>
	</tr>	
	<tr>
		<td>廣告狀態:</td>
		<td>
			<select name ="ad_status">
				<option value="上架">上架</option>
				<option value="審核中">審核中</option>
				<option value="下架">下架</option>
			</select>
		</td>
	</tr>	
</table>
<br>
<!-- 送積分的地方 -->
<input type="hidden" name="action" value="update">
	<c:forEach var="memVO" items="${memSvc.all}">
		<c:forEach var="lanVO" items="${lanSvc.all}">
			<c:if test="${lanVO.mem_id eq memVO.mem_id}">
				<c:if test="${adVO.lan_id eq lanVO.lan_id}">
<input type="hidden" name="mem_id" value="${memVO.mem_id}">
				</c:if>
			</c:if>
		</c:forEach>
	</c:forEach>
<!-- END -->
<input type="hidden" name="ad_id" value="<%=adVO.getAd_id()%>">
<input type="hidden" name="ad_sort_id" value="<%=adVO.getAd_sort_id()%>">
<input type="hidden" name="lan_id" value="<%=adVO.getLan_id()%>">
<input type="hidden" name="hou_id" value="<%=adVO.getHou_id()%>">
<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">
<input type="submit" value="送出修改">
</form>
</body>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/datetimepicker/jquery.datetimepicker.css"/>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
<style>
	.xdsoft_datetimepicker .xdsoft_datepicker {
			width: 300px;
	}
	.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box{
			height: 151px;
	}

</style>
<script>
		$.datetimepicker.setLocale('zh');
		$('#f_date').datetimepicker({
			theme: '',
			timepicker:false,
			step:1,
			format:'Y-m-d',
			value:'<%=adVO.getAd_date()%>', 
			// value: new Date(),
			//diabledDates:   ['2017/06/08','2017/06/09','2017/06/10'],
			//startDate:       '2017/07/10'
			//minDate:			'-1970-01-01',
			//maxDate:			'+1970-01-01',
		});
</script>
</html>