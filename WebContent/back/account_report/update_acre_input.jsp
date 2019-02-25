<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.account_report.model.*" %>
<%@ page import="com.goodhouse.employee.model.*" %>
<%
	Account_reportVO acreVO = (Account_reportVO) request.getAttribute("acreVO");

%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>

</head>
<body bgcolor='white'>
<table id="table-1">
	<tr><td>
		 <h3>員工資料修改 - update_account_report_input</h3>
		 <h4><a href="select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>
<h3>資料修改</h3>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="acre.do" name="form1">

<table>
	<tr>	
		<td>檢舉編號<font color=red><b>*</b></font></td>
		<td><%=acreVO.getAcc_rep_id()%></td>
	</tr>
	<tr>
		<td>員工編號</td>
		<td><input type="text" name="emp_id" size="45" value="<%=acreVO.getEmp_id()%>"></td>
	</tr>
	<tr>
		<td>房客編號</td>
		<td><input type="text" name="mem_id" size="45" value="<%=acreVO.getMem_id()%>"></td>
	</tr>
	<tr>
		<td>房東編號</td>
		<td><input type="text" name="lan_id" size="45" value="<%=acreVO.getLan_id()%>"></td>
	</tr>
	<tr>
		<td>檢舉狀態</td>
		<td><input type="text" name="acc_rep_status" size="45" value="<%=acreVO.getAcc_rep_status()%>"></td>
	</tr>
	<tr>
		<td>檢舉事由</td>
		<td><input type="text" name="acc_rep_reason" size="45" value="<%=acreVO.getAcc_rep_reason()%>"></td>
	</tr>
		<tr> <!--  Date acc_rep_date -->
		<td>檢舉日期</td>
		<td><input type="text" name="acc_rep_date" id="f_date1" ></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="acc_rep_id" value="<%=acreVO.getAcc_rep_id() %>">
<input type="submit" value="送出修改">
</FORM>
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
		$('#f_date1').datetimepicker({
			theme: '',
			timepicker:false,
			step:1,
			format:'Y-m-d',
			value:'<%=acreVO.getAcc_rep_date()%>', 
			// value: new Date(),
			//diabledDates:   ['2017/06/08','2017/06/09','2017/06/10'],
			//startDate:       '2017/07/10'
			//minDate:			'-1970-01-01',
			//maxDate:			'+1970-01-01',
		});
</script>
</html>