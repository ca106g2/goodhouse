<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.equipment_repair.model.*"%>

<%
  EquRepVO equRepVO = (EquRepVO) request.getAttribute("equRepVO"); //EquRepServlet.java (Concroller) 存入req的equRepVO物件 (包括幫忙取出的equRepVO, 也包括輸入資料錯誤時的equRepVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>設備維修修改 - update_equRep_input.jsp</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>設備維修修改 - update_equRep_input.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="equRep.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>設備維修編號:<font color=red><b>*</b></font></td>
		<td><%=equRepVO.getEqu_rep_id()%></td>
	</tr>
	<tr>
		<td>房屋編號:</td>
		<td><input type="TEXT" name="hou_id" size="45" value="<%=equRepVO.getHou_id()%>" /></td>
	</tr>
	<tr>
	<tr>
		<td>會員編號:</td>
		<td><input type="TEXT" name="mem_id" size="45" value="<%=equRepVO.getMem_id()%>" /></td>
	</tr>
	<tr>
		<td>房東編號:</td>
		<td><input type="TEXT" name="lan_id" size="45"	value="<%=equRepVO.getLan_id()%>" /></td>
	</tr>
	<tr>
		<td>維修申請時間:</td>
		<td><input name="equ_rep_accetime" id="f_date1" type="text"></td>
	</tr>
	<tr>
		<td>維修廠商:</td>
		<td><input type="TEXT" name="equ_rep_staff" size="45" value="<%= equRepVO.getEqu_rep_staff()%>" /></td>
	</tr>
	
	<tr>
		<td>維修廠商電話:</td>
		<td><input type="TEXT" name="equ_rep_staffphone" size="45" value="<%=  equRepVO.getEqu_rep_staffphone()%>" /></td>
	</tr>
	
	<tr>
		<td>維修設備:</td>
		<td><input type="TEXT" name="equ_rep_event" size="45" value="<%= equRepVO.getEqu_rep_event()%>" /></td>
	</tr>
	<tr>
		<td>維修設備照片:</td>
        <td>
        <input type="file" name="upfile" value="<%= equRepVO.getEqu_rep_picture()%>" />
        </td>
	<tr>
		<td>維修說明:</td>
		<td><input type="TEXT" name="equ_rep_descri" size="45" value="<%= equRepVO.getEqu_rep_descri()%>" /></td>
	</tr>
	<tr>
		<td>維修狀態:</td>
		<td><input type="TEXT" name="equ_rep_status" size="45" value="<%= equRepVO.getEqu_rep_status()%>" /></td>
	</tr>
	<tr>
		<td>預定維修時間:</td>
		<td><input name="equ_rep_expectime" id="f_date2" type="text"></td>
	</tr>
	<tr>
		<td>維修完成時間:</td>
		<td><input name="equ_rep_finish" id="f_date3" type="text"></td>
	</tr>
	
	
	

	<% /*<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> */%>
	<!--<tr>
		<td>部門:<font color=red><b>*</b></font></td>
		<td><select size="1" name="deptno">
			<c:forEach var="deptVO" items="${deptSvc.all}">
				<option value="${deptVO.deptno}" ${(equRepVO.deptno==deptVO.deptno)?'selected':'' } >${deptVO.dname}
			</c:forEach>
		</select></td>
	</tr>
	-->

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="equ_rep_id" value="<%=equRepVO.getEqu_rep_id()%>">
<input type="submit" value="送出修改"></FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

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
   value: '<%=equRepVO.getEqu_rep_accetime()%>', // value:   new Date(),
   //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
   //startDate:	            '2017/07/10',  // 起始日
   //minDate:               '-1970-01-01', // 去除今日(不含)之前
   //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
});

$('#f_date2').datetimepicker({
    theme: '',              //theme: 'dark',
    timepicker:false,       //timepicker:true,
    step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
    format:'Y-m-d',         //format:'Y-m-d H:i:s',
	   value: '<%=equRepVO.getEqu_rep_expectime()%>', // value:   new Date(),
    //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
    //startDate:	            '2017/07/10',  // 起始日
    //minDate:               '-1970-01-01', // 去除今日(不含)之前
    //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
 });

$('#f_date3').datetimepicker({
    theme: '',              //theme: 'dark',
    timepicker:false,       //timepicker:true,
    step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
    format:'Y-m-d',         //format:'Y-m-d H:i:s',
	   value: '<%=equRepVO.getEqu_rep_finish()%>', // value:   new Date(),
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
       
        
</script>
</html>