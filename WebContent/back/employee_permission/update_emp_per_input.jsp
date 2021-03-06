<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.employee_permission.model.*"%>
<%@ page import="com.goodhouse.employee.model.*"%>
<%@ page import="java.util.*"%>

<%

%>

<!doctype html>
<html lang="zh-Hant-TW">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>員工權限資料修改 - update_emp_per_input.jsp</title>

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
		 <h3>員工權限資料修改 - update_emp_per_input.jsp</h3>
		 <h4><a href="../back/employee/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
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

<FORM METHOD="post" ACTION="emp_per.do" name="form1">
<table>
	<jsp:useBean id="empSvc" scope="page" class="com.goodhouse.employee.model.EmpService" />
	<tr>
		<td>員工名稱:<font color=red><b>*</b></font></td>
		<td>
			 ${empVO.emp_name}
		</td>

	</tr>
		<jsp:useBean id="perSvc" scope="page" class="com.goodhouse.permission.model.PerService" />

	<tr>
		<td>權限名稱</td>
		<td>
		<c:forEach var="perVO" items="${perSvc.all}" varStatus="s" >
		${(s.index%3==0)? '<br>' : ''}
			<input type="checkbox"  name="per_ID" value="${perVO.per_id}" ${listPer_ID.contains(perVO.per_ID)?'checked':''}> ${perVO.per_name}
		</c:forEach>
		
		</td>

	</tr>
	
	
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="emp_id" value="${empVO.emp_id}">
<input type="submit" value="送出修改"></FORM>
  
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>
</html>