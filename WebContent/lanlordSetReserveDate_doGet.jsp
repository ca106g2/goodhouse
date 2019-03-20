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
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<%	
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	LanService lanSvc = new LanService();
	LanVO lanVO = lanSvc.getOneLanByMemId(memVO.getMem_id());
	String lan_id = lanVO.getLan_id();
	
	HouNoAppService houNoAppSvc = new HouNoAppService();
// 	List<HouNoAppVO> list = houNoAppSvc.getPart("L000000001");
	List<HouNoAppVO> list = houNoAppSvc.getPart(lan_id);
	pageContext.setAttribute("list",list);
%>
<%
	AppointService AppointSvc = new AppointService();
	
// 	List<AppointVO> listAppoint = AppointSvc.getPartLan("L000000001");
	List<AppointVO> listAppoint = AppointSvc.getPartLan(lanSvc.getOneLanByMemId(memVO.getMem_id()).getLan_id());
	
	pageContext.setAttribute("listAppoint",listAppoint);
%>

<%
// 	String hou_id =  request.getParameter("hou_id");
	HouseService houSvc = new HouseService();
	HouseVO houVO = houSvc.getOneByLanId(lan_id);
	String hou_id = houVO.getHou_id();
// 	String hou_id =  "HOU0000007";

%>

<html>

<head>
<script type="text/javascript">
var action = "insert";
// var lan_id = "L000000001";
var lan_id = "<%= lan_id %>";
// var hou_id = "HOU0000001";
var hou_id = "<%= hou_id %>";
var hou_noapp_time = "A1";
</script>


<title>房屋不可預約日期設定-lanlordSetReserve</title>

<link  href="<%=request.getContextPath() %>/glDatePicker/glDatePicker.default2.css"   rel="stylesheet" type="text/css">  <!-- 此版自定 -->
<link  href="<%=request.getContextPath() %>/glDatePicker/glDatePicker.darkneon1.css"  rel="stylesheet" type="text/css">  <!-- 原版預設 -->
<link  href="<%=request.getContextPath() %>/glDatePicker/glDatePicker.flatwhite1.css" rel="stylesheet" type="text/css">  <!-- 原版預設 -->
<script src="<%=request.getContextPath() %>/glDatePicker/jquery.min.js"></script>
<script src="<%=request.getContextPath() %>/glDatePicker/glDatePicker2_80precent_backup9_lan_doGet.js"></script>                                     <!-- 此版自定 -->
<style>
.parent {
	position: relative;
	float: left;
	width: 92%;
	height: 100px;
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
	bottom: -60%;				
	text-align: center;
}
</style>
<style>
a:visited, a:link {
	color: inherit;
	text-decoration: none;
}
a:hover, a:active {
	color: cyan;
	text-decoration: none;
}
</style>
</head>
<body>
	
	<input gldp-id="mydate" style="width: 1000px; height: 30px; visibility: visible; color:blue; font-weight: bold;" type="text" id="mydate"/>
    <div   gldp-el="mydate" style="width: 1000px; height:1000px; position:  absolute;"> </div>
<!-- div控制顏色外框，白色內框詳glDatePicker2_80precent 664行註解     -->
	<script type="text/javascript">
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

    
    
    </body>
</html>