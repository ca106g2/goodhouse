<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.goodhouse.ad.model.*"%>

<%
	AdVO adVO = (AdVO) request.getAttribute("adVO");
%>

<jsp:useBean id="houSvc" scope="page" class="com.goodhouse.house.model.HouseService"/>
<jsp:useBean id="memSvc" scope="page" class="com.goodhouse.member.model.MemService"/>
<jsp:useBean id="lanSvc" scope="page" class="com.goodhouse.landlord.model.LanService"/>
<jsp:useBean id="ad_sortSvc" scope="page" class="com.goodhouse.ad_sort.model.Ad_sortService"/>


<html>
<head>
<meta http-equiv="X-UA-Compatible" conten"IE=edge,chrom=1">
<script src="https://ajax.googleapis.com/ajax/libs"></script>
 <script language="javascript">
    function _onchange() {
        var sel = document.getElementById("paytype");
        if (sel.value == "ADS0000001") {
            document.getElementById("paystofe").style.display = "none";
            document.getElementById("paystofe").style.display = "none";
            document.getElementById("paystofe").style.display = "none";
        } else if (sel.value == "ADS0000002") {
            document.getElementById("paystofe").style.display = "";
            document.getElementById("paystofe").style.display = "";
            document.getElementById("paystofe").style.display = "";
        } else if (sel.value == "ADS0000003") {
            document.getElementById("paystofe").style.display = "";
            document.getElementById("paystofe").style.display = "";
            document.getElementById("paystofe").style.display = "";
        } else if (sel.value == "ADS0000004") {
            document.getElementById("paystofe").style.display = "";
            document.getElementById("paystofe").style.display = "";
            document.getElementById("paystofe").style.display = "";
        }
    }
    </script>
</head>
<body bgcolor='white'>
<table id="table-1">
	<tr><td>
	<h3>新增</h3>
	<h4><a href="select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<h3>新增資料</h3>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
		<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
		<!--
			<td>房東名子</td><!--房東姓名 LAN_id
			<td>房屋名稱</td><!--房屋名稱 HOU_id
			<td>廣告備註</td><!--廣告備註 AD_FORFREE
			<td>繳費狀態</td><!--繳費狀態(已收,未收)AD_STATUE
			<td>付款方式</td><!--付款方式 AD_PAYMETHODS
			<td>廣告刊登日</td><!--廣告刊登日 AD_DATE
			<td>廣告狀態</td><!--(上架,下架)AD_STATUS 
<select name="con_status">
	<c:forEach var="con_status" items="${Con_statusList}">
		<option value="${con_status.status_name}" ${(con_status.status_no_name == 
		conVO.con_status)?'selected':''}>${con_status.status_name}
	</c:forEach>
</select>
			 -->
<form method="post" action="ad.do" name="form1" enctype="multipart/form-date">
	<table>
		<tr>
			<td>廣告類別</td>
			<td>
				<select id="paytype" name="ad_sort_id" onChange="_onchange()">
						<option value="ADS0000001">廣告兌換</option> 
						<option value="ADS0000002">月計費</option>  
				</select>
			</td>
		</tr>
		<tr>
			<td>房東名子</td>
			<td>
				<select name="lan_id">
					<c:forEach var="lanVO" items="${lanSvc.all}">
						<c:forEach var="memVO" items="${memSvc.all}">
							<c:if test="${lanVO.mem_id == memVO.mem_id}">
				                 <option value="${lanVO.lan_id}">${memVO.mem_name }
			                </c:if>
						</c:forEach>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td>房屋名稱</td>
			<td>
				<input type="text" name="hou_id" size="45" 
				value="<%=(adVO==null)? "HOU0000001" : adVO.getHou_id()%>"/>
			</td>
		</tr>
			<tr>
		<td>繳費狀態:</td>
		<td>
			<select id="paystofe" name ="ad_statue" style="display:none;">
				<option value="ad_satue000">免費</option> 
				<option value="ad_satue001">以付款</option> 
				<option value="ad_satue002">未付款</option> 
			</select> 
		</td>
	</tr>	
	<tr>
		<td>付款方式:</td>
		<td>			
			<select name ="ad_paymethod">
				<option value="ad_pay000">免費
				<option value="ad_pay001">信用卡
				<option value="ad_pay002">VISA金融卡
				<option value="ad_pay003">積分
			</select>
		</td>
	</tr>	
	<tr>
		<td>廣告狀態:</td>
		<td>
			<select name ="ad_status">
				<option value="ad_satus001">上架
				<option value="ad_satus002">下架
			</select>
		</td>
	</tr>
	<tr>
		<td>廣告備註:</td>
		<td>
			<input type="text" name="ad_forfree" size="45"
			value="<%=(adVO==null)? "請填寫註解" : adVO.getAd_forfree()%>"/>
		</td>
	</tr>
	<tr>
		<td>廣告刊登日:</td>
		<td><input type="text" name="ad_date" id="f_date1"></td>
	</tr>	
	</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增">

</form>
</body>
<%
	java.sql.Date ad_date = null;
	try{
		ad_date = adVO.getAd_date();
	}catch(Exception e){
		ad_date = new java.sql.Date(System.currentTimeMillis());
	}
%>
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
			value:'<%=ad_date%>', 
			// value: new Date(),
			//diabledDates:   ['2017/06/08','2017/06/09','2017/06/10'],
			//startDate:       '2017/07/10'
			//minDate:			'-1970-01-01',
			//maxDate:			'+1970-01-01',
		});
</script>
</html>