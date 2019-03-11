<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.goodhouse.house.model.*"%>
<%@ page import="com.goodhouse.appointment.model.*"%>


<%
String hou_id = request.getParameter("hou_id");
HouseService houSvc = new HouseService();
HouseVO houVO = houSvc.getOneHouse(hou_id);
//pageContext.setAttribute("", arg1)
%>

<%
String appoint_id = request.getParameter("appoint_id");
AppointService appointSvc = new AppointService();
AppointVO appointVO = appointSvc.getOneAppoint(appoint_id);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List HouseDetail</title>
<style type="text/css">

</style>
</head>
<body>
	<jsp:include page="/FrontHeaderFooter/Header.jsp" />
	<div class="container">
		<div class="row">
			<div class="col-12">
				<div class="row">
					<div class="col-sm-4">
						<div
							style="background-image: url(http://localhost:8081/<%=request.getContextPath() %>/HouseServlet?hou_id=<%=houVO.getHou_id()%>&photo=1); height: 300px; background-position: center; background-repeat: no-repeat; background-size: cover;">
						</div>
					</div>
					<div class="col-sm-4">
						<div
							style="background-image: url(http://localhost:8081/<%=request.getContextPath() %>/HouseServlet?hou_id=<%=houVO.getHou_id()%>&photo=2); height: 300px; background-position: center; background-repeat: no-repeat; background-size: cover;">
						</div>
					</div>
					<div class="col-sm-4">
						<div
							style="background-image: url(http://localhost:8081/<%=request.getContextPath() %>/HouseServlet?hou_id=<%=houVO.getHou_id()%>&photo=3); height: 300px; background-position: center; background-repeat: no-repeat; background-size: cover;">
						</div>
					</div>
				</div>
				<div class="row justify-content-end">
					<div class="col-sm-4"></div>
					<div class="col-sm-4" style="text-align: right;">
						<div class="price">
							<span><%=houVO.getHou_rent()%></span><span>元/月</span>
						</div>
						<div class="addr">
							<span><%=houVO.getHou_address()%></span>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-4" style="text-align: left;">
						<div class="addr">
							<span><%=houVO.getHou_name()%></span>
						</div>
						<div class="addr">
							<span><%=houVO.getHou_id()%></span>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-3">
						<span class="lan">房東：</span> <span>中壢城伍</span>
					</div>
					<div class="col-sm-3"></div>
					<div class="col-sm-6">
						<div class="row">
							<div class="col-sm-6">
								<span class='type'>型別：</span> <span><%=houVO.getHou_type()%></span>
							</div>
							<div class="col-sm-6">
								<span class='type'>坪數：</span> <span><%=houVO.getHou_size()%></span>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<span class='type'>產權：</span> <span><%=houVO.getHou_property()%></span>
							</div>
							<div class="col-sm-6">
								<span class='type'>車位：</span> <span><%=houVO.getHou_parkspace()%></span>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<span class='type'>開火：</span> <span><%=houVO.getHou_cook()%></span>
							</div>
							<div class="col-sm-6">
								<span class='type'>管理費：</span> <span><%=houVO.getHou_managefee()%></span>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12">
						<ul class="nav nav-tabs" role="tablist">
							<li class="nav-item"><a class="nav-link active"
								href="#profile" role="tab" data-toggle="tab" id="note">備註</a></li>
							<li class="nav-item"><a class="nav-link" href="#buzz"
								role="tab" data-toggle="tab">問與答</a></li>
							<li class="nav-item"><a class="nav-link" href="#references"
								role="tab" data-toggle="tab">預約行程</a></li>
						</ul>

						<!-- Tab panes -->
						<div class="tab-content">
							<div role="tabpanel" class="tab-pane fade in active" id="profile">
								<div class="myrow"><%=houVO.getHou_note()%></div>

							</div>
							<div role="tabpanel" class="tab-pane fade" id="buzz">問與答</div>
							<div role="tabpanel" class="tab-pane fade" id="references">
							<FORM METHOD="post" ACTION="appoint.do" name="form1">
							<table>
							<tr>
								<td><input name="hou_app_date" id="f_date1" type="text"></td>
							</tr>
							</table>
							<input type="hidden" name="action" value="insert">
							<input type="submit" value="送出新增">
							</FORM>	
								
								
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">

	$(".change").click(function(){
		if(this.title == "加入收藏") {
			this.src = "<%=request.getContextPath()%>/images/heart_red.png";
			this.title = "取消收藏";
		} else {
				this.src = "<%=request.getContextPath()%>
		/images/heart_white.png";
								this.title = "加入收藏";
							}
						});

		$(function() {
			// This button will increment the value
			$('.qtyplus').click(
					function(e) {
						// Stop acting like a button
						e.preventDefault();
						// Get the field name
						fieldName = $(this).attr('field');
						// Get its current value
						var currentVal = parseInt($(
								'input[name=' + fieldName + ']').val());
						// If is not undefined
						if (!isNaN(currentVal)) {
							// Increment
							$('input[name=' + fieldName + ']').val(
									currentVal + 1);
						} else {
							// Otherwise put a 0 there
							$('input[name=' + fieldName + ']').val(0);
						}
					});
			// This button will decrement the value till 0
			$(".qtyminus").click(
					function(e) {
						// Stop acting like a button
						e.preventDefault();
						// Get the field name
						fieldName = $(this).attr('field');
						// Get its current value
						var currentVal = parseInt($(
								'input[name=' + fieldName + ']').val());
						// If it isn't undefined or its greater than 0
						if (!isNaN(currentVal) && currentVal > 0) {
							// Decrement one
							$('input[name=' + fieldName + ']').val(
									currentVal - 1);
						} else {
							// Otherwise put a 0 there
							$('input[name=' + fieldName + ']').val(0);
						}
					});
		});
	</script>
	<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
</body>

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
  java.sql.Date hou_app_date = null;
  try {
	  hou_app_date = appointVO.getHou_app_date();
   } catch (Exception e) {
	   hou_app_date = new java.sql.Date(System.currentTimeMillis());
   }
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/back/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/back/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=hou_app_date%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.以下為某一天之後的日期無法選擇
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        //git上傳註解用無意義
        
</script>
</html>