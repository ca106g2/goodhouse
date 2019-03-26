<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="javax.servlet.*"%>
<%@ page import="javax.servlet.http.*"%>
<%@ page import="com.goodhouse.appointment.model.*" %>
<%@ page import="com.goodhouse.house_noappointment.model.*" %>
<%@ page import="com.goodhouse.member.model.*"  %>
<%@ page import="com.goodhouse.landlord.model.*" %>
<%@ page import="com.goodhouse.house.model.*" %>


<!-- 參考網址 -->
<!-- http://glad.github.io/glDatePicker/ -->
<!-- https://github.com/oschina/glDatePicker -->
<%	
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	LanService lanSvc = new LanService();
	LanVO lanVO = lanSvc.getOneLanByMemId(memVO.getMem_id());
	String lan_id ="";
	if(lanVO != null)
	lan_id = lanVO.getLan_id();
	
	HouNoAppService houNoAppSvc = new HouNoAppService();
	List<HouNoAppVO> list = houNoAppSvc.getPart(lan_id);
	pageContext.setAttribute("list",list);
%>
	
<%
	AppointService AppointSvc = new AppointService();
	
	List<AppointVO> listAppoint = AppointSvc.getPartLan(lanSvc.getOneLanByMemId(memVO.getMem_id()).getLan_id());
	
	pageContext.setAttribute("listAppoint",listAppoint);
%>

<%
	HouseService houSvc = new HouseService();
	HouseVO houVO = houSvc.getOneByLanId(lan_id);
	String hou_id = "";
	if (houVO != null){
		hou_id = houVO.getHou_id();
	}else{%>
		<jsp:include page="/FrontHeaderFooter/Header.jsp" />
		<div class="container">
			<div class="row">
				<div class="col-sm-1"></div>
				<div class="col-sm-10">
					<div class="card">
						<div class="card-header">
							  	溫馨小提醒
						</div>
						<div class="card-body">
						    <h5 class="card-title">QQ...還未申請房屋!</h5>
						    <p class="card-text">請先完成新增房屋流程再使用本功能</p>
						    <a href="<%=request.getContextPath()%>/front/index.jsp" class="btn btn-primary">回到首頁</a>
						</div>
					</div>
				</div>
				<div class="col-sm-1"></div>
			</div>
		</div>
	<%}%>

<%if (houVO != null){ %>

<html>

<head>

<table id="table-1">
	<tr><td>
		 <h4><a href="<%=request.getContextPath()%>/front/index.jsp"><img src="<%=request.getContextPath()%>/images/back.png" ></a></h4>
	</td></tr>
</table>



<script type="text/javascript">
var action = "insert";
var lan_id = "<%= lan_id %>";

var hou_id = "<%= hou_id %>";

var hou_noapp_time = "A1";
</script>


<title>房屋不可預約日期設定-lanlordSetReserve</title>

<link  href="<%=request.getContextPath() %>/glDatePicker/glDatePicker.default2.css"   rel="stylesheet" type="text/css">  <!-- 此版自定 -->
<link  href="<%=request.getContextPath() %>/glDatePicker/glDatePicker.darkneon1.css"  rel="stylesheet" type="text/css">  <!-- 原版預設 -->
<link  href="<%=request.getContextPath() %>/glDatePicker/glDatePicker.flatwhite1.css" rel="stylesheet" type="text/css">  <!-- 原版預設 -->
<script src="<%=request.getContextPath() %>/glDatePicker/jquery.min.js"></script>
<script src="<%=request.getContextPath() %>/glDatePicker/glDatePicker2_80precent_backup9_lan_doGet.js"></script>

                                     <!-- 此版自定 -->
<style>
.parent {
	position: relative;
	float: left;
	width: 92%;
	height: 40px;
	text-align: left;
	z-index: 1;
	border: solid 0px red;
	margin: 5%;
}

.link {		/* 調整日期位置 */
	position: absolute;
	left: 50%;
	margin-left: -50%;
	width: 100%;
	bottom: -200%;				
	text-align: center;
}
</style>
<style>
a:visited, a:link {
/* 	color: inherit; */
	text-decoration: none;
}
a:hover, a:active {
/* 	color: cyan; */
	text-decoration: none;
}

.gldp-default .noday {
    color: #88bfe8;
    font-weight: normal;
    background: #fff7ac;
  }
  
.gldp-default .border {
    border-style: solid;
 }
 
 .gldp-default .core {
/*  	background-color: #9dff9d; */
	font-size: 28;
 }
 
 .gldp-default .dow {
 	font-family: Microsoft JhengHei;
 	font-size: 24px;
 	font-weight: bold;
    cursor: wait !important;
 	background: #dff5f0;
 }
 
 element.style {
    background-color: #88bfe8;
}

.gldp-default .monyear,
.gldp-default .monyear select {
    color: aliceblue;
    font-family: Microsoft JhengHei;
    font-size: 32px;
    font-weight: bold;
    background-color: #88bfe8;
    
}

.gldp-default {
    position: absolute;
    font-family: Microsoft JhengHei;
    background-color: #88bfe8;
}
 
 .gldp-default .selected {
 
 	background-color: #fb6e6e;
 
 }
 
 .gldp-default .today {
 	background: #a1f0f8;
 }





</style>
</head>
<body>
	<div role="tabpanel" class="tab-pane fade" id="references" style="height: 1000px;">
	<input gldp-id="mydate" style="width: 1000px; height: 30px; visibility: visible; color:#88bfe8; font-weight: bold; background-color:#fff7ac; font-size:24px; font-family:Microsoft JhengHei;" type="text" id="mydate"/>
    <div   gldp-el="mydate" style="width: 1000px; height:1000px; position:  absolute;"> </div>
<!-- div控制顏色外框，白色內框詳glDatePicker2_80precent 664行註解     -->
	<script type="text/javascript">
// $(function(){
			
	$('#mydate').glDatePicker(
			{
				showAlways : true,       // 預設為 false
 				cssName: 'default',      // 可用 'default' 或  'darkneon' 或  ''
//              format: 'yyyy-mm-dd',    // 預設
//              dowOffset: 0,            // 預設
             	allowMonthSelect: false, // 預設
            	 allowYearSelect: false,   // 預設
//              prevArrow: '\u25c4',     // 預設
//              nextArrow: '\u25ba',     // 預設
                dowNames : [ '<font color=red>星期日</font>', '星期一', '星期二', '星期三', '星期四', '星期五', '<font color=red>星期六</font>' ], //自定
                monthNames: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'], //自定
                
// ====================================================================================================              
//              以下的'月'為陣列index，加1才為正確的幾月

                
// 			    selectedDate: new Date(),             // 今天的日期        (藍色-->橘色)(預設原有)
// 			    selectedDate: new Date(2019, 03, 16), // 自選選定的日期 (藍色)


// 			    selectableDates: // 可選的數個日期 (白色)
// 			    [
// 			        { date: new Date(2019, 02, 15) },
// 			        { date: new Date(2019, 02, 20) },
// 			        { date: new Date(2019, 02, 25) },
// 			        { date: new Date(2019, 03, 25) },
// 			        { date: new Date(2019, 04, 25) },
// 			        { date: new Date(2019, 05, 25) },
// 			        { date: new Date(2019, 06, 25) }
// 			    ],
				<%Calendar calendar = new GregorianCalendar();%>	//建立一個GregorianCalendar物件，叫做calendar
							
							selectableDateRange: // 可選的日期範圍 每個月的第1天到今天前的日期無法被預約
			 			    [
			 			    	
			 			        { from:	new Date(<%=calendar.get(Calendar.YEAR)%>, <%=calendar.get(Calendar.MONTH)%>, <%=1%>),  
			 			        	to: new Date(<%=calendar.get(Calendar.YEAR)%>, <%=calendar.get(Calendar.MONTH)%>, <%=calendar.get(Calendar.DAY_OF_MONTH) - 1%>)  },
			 			    ],
			 			    
			 			    
			 			   selectableYears: [ 
						    	<%=calendar.get(Calendar.YEAR)%>
						    	], //可選的年份	今年
						   selectableMonths: [
						    	<%=calendar.get(Calendar.MONTH)%>  
						    	], //可選的月份	這個月
			 			    
			 			    
			 			    
							selectableDates:[	//從不可預約時間(HouNoAppVO)和可預約時間(AppointVO)的物件拿出已被設定的日期並顯示
					    		<%

									for (HouNoAppVO houNoAppVO : list) {	

										calendar.setTime(houNoAppVO.getHou_noapp_date());%>
	 	 								{ date : new Date(<%=calendar.get(Calendar.YEAR)%>, <%=calendar.get(Calendar.MONTH)%>, <%=calendar.get(Calendar.DAY_OF_MONTH)%>)},
	 	 			    
	 							<%
										}
								%> 
								
 			
 								<%

									for (AppointVO appointVO : listAppoint) {

										calendar.setTime(appointVO.getHou_app_date());%>
						 	 			{ date : new Date(<%=calendar.get(Calendar.YEAR)%>, <%=calendar.get(Calendar.MONTH)%>, <%=calendar.get(Calendar.DAY_OF_MONTH)%>)},
						 	 			    
 								<%
										}
								%>
										],
								

// 			    specialDates: [                      // 特殊日期的日期 + 信息 (綠色)
// 			        {
// 			            date: new Date(2018, 0, 15),
// 			            data: { message: '每月15日會議' },
// 			            repeatMonth: true
// 			        },
// 			        {
// 			            date: new Date(0, 0, 1),
// 			            data: { message: '新年快樂!' },
// 			            repeatYear: true
// 			        },
// 			    ],


                onClick: function(target, cell, date, data) {
	                 target.val(date.getFullYear() + '-' +
	                		   (((date.getMonth()+1)<10)? "0"+(date.getMonth()+1):(date.getMonth()+1)) + '-' +
			                   ((date.getDate()<10)? "0"+date.getDate():date.getDate()));

	                 if(data != null) {
	                     alert(data.message + '\n' + date);
	                 }
                }

			});
// });

// 	$(function(){
// 		$('#myTest').on('click', function(){
// 			$('input[gldp-id="mydate"]').trigger('click');
// 			$('input[gldp-id="mydate"]').css('visibility', 'hidden');
// 			$('div[gldp-el="mydate"]').css('top', '100px');
// 			$('div[gldp-el="mydate"]').css('left', '20px');
// 		});
		
// 		$('#profile').addClass('show');
		
// 		$('#myClear').on('click', function(){
// 			$('#myAsk').val('');
// 		})
		
// 	});
//***js傳參數到servlet方法參考:https://www.itread01.com/p/1076679.html***	   
// 	function test()
// 	{
// 	  var f=document.forms[0];
// // 	  var name = f.elements.name.value;
// // 	  console.log(name);
// // 	  alert(name);
// 	  f.action="houNoApp2.do";
	  
// 	  f.submit();
// 	 }
//*******************************************************************
    </script>
    </div>
<%--     <jsp:include page="/FrontHeaderFooter/Header.jsp" /> --%>
<%--     <jsp:include page="/FrontHeaderFooter/Footer.jsp" /> --%>
    </body>
</html>
<% }%>