<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.goodhouse.ad.model.*"%>
<%@ page import="com.goodhouse.member.model.*" %>
<%@ page import="com.goodhouse.house.model.*" %>
<%@ page import="com.goodhouse.landlord.model.*" %>
<%
	request.setCharacterEncoding("utf-8");
	AdVO adVO = (AdVO) request.getAttribute("adVO");
	MemVO memVO = (MemVO)session.getAttribute("memVO");
// 	HouseVO houVO = (HouseVO)session.getAttribute("houVO");
	String hou_id = request.getParameter("hou_id");
	String hou_name = request.getParameter("hou_name");
%>

<jsp:useBean id="houSvc" scope="page" class="com.goodhouse.house.model.HouseService"/>
<jsp:useBean id="memSvc" scope="page" class="com.goodhouse.member.model.MemService"/>
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
            document.getElementById("pay").style.display = "none"
        } else if (sel.value == "ADS0000002") {
            document.getElementById("paystofe").style.display = "";
            document.getElementById("paystofe").style.display = "";
            document.getElementById("pay").style.display = ""
        } 
    }
    </script>
    
    <style>
    
#table2 {
	font-family:Microsoft JhengHei;;
	font-size: 16px;
	width: 1200px;
	text-align: center;
	border-collapse: collapse;
	margin-left: auto;
	margin-right: auto;
}

th {
	background-color: #009FCC;
	padding: 10px;
	color: #fff;

}

td {

	padding: 5px;
}
    
    
    </style>
</head>
<body bgcolor='white'>
	<jsp:include page="/FrontHeaderFooter/Header.jsp" />
<table id="table-1">

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
<br>
<br>
<form method="post" action="ad.do" name="form1" enctype="multipart/form-date">
	<table id="table2">
		<tr>
			<td>廣告類別</td>
			<td>
				<select id="paytype" name="ad_sort_id" onChange="_onchange()">
						<option value="ADS0000002">月計費</option>  
						<option value="ADS0000001">廣告兌換</option> 
				</select>
			</td>
		</tr>
		<tr>
			<td>房東名子</td>
			<td>
				<p><%=memVO.getMem_name()%>
			</td>
		</tr>
		<tr>
			<td>房屋名稱</td>
			<td>
				<p><%=hou_name%></p>
				<input type="hidden" name="hou_id" size="45" 
				value="<%=hou_id%>"/>
			</td>
		</tr>
			<tr>
		<td>繳費狀態:</td>
		<td>
			<select id="paystofe" name ="ad_statue">
				<option value="未付款">未付款</option> 
			</select> 
		</td>
	</tr>	
	<tr>
		<td>付款方式:</td>
		<td>			
			<select id="pay" name ="ad_paymethod">
				<option value="信用卡">信用卡</option> 
			</select>
		</td>
	</tr>	
	<tr>
		<td>廣告狀態:</td>
		<td>
			<select name ="ad_status">
				<option value="審核中">"審核中"
			</select>
		</td>
	</tr>
	<tr class="table-light">
		<td>廣告備註:</td>
			<td><label for="exampleFormControlTextarea1"></label> <textarea
				name="ad_forfree" class="form-control" 
				placeholder="<%=(adVO==null)? "請填寫備註" : adVO.getAd_forfree()%>" 
				id="exampleFormControlTextarea1" rows="3"></textarea>
		</td>
	</tr>
	<tr>
		<td>廣告刊登日:</td>
		<td><input type="text" name="ad_date" id="f_date1"></td>
	</tr>
		<tr>
		<td></td>
		<td>
			<%
				LanService lanSvc = new  LanService();
			%>	

			<input type="hidden" name="lan_id" value="<%=lanSvc.getOneLanByMemId(memVO.getMem_id()).getLan_id()%>">
			<input type="hidden" name="action" value="front_insert">
			<input type="submit" class="btn btn-secondary" value="送出新增">
		</td>
		
	</tr>	
	</table>
<br>
</form>


	<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
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
		$('#f_date1').datetimepicker({
			theme: '',
			timepicker:false,
			step:1,
			format:'Y-m-d',
			value: '<%=ad_date%>', // value:   new Date(),
			// value: new Date(),
			//diabledDates:   ['2017/06/08','2017/06/09','2017/06/10'],
			//startDate:       '2017/07/10'
			//minDate:			'-1970-01-01',
			//maxDate:			'+1970-01-01',
		});
</script>
</html>