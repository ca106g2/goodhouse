<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.ad_report.model.*"%>
<%@ page import="com.goodhouse.member.model.*" %>
<%@ page import="com.goodhouse.employee.model.*" %>
<%
	Ad_reportVO ad_repVO = (Ad_reportVO) request.getAttribute("ad_repVO");
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table id="table-1">
		<tr>
			<td>
				<h3>合約分類</h3>
				<h4>
					<a href="select_page.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料修改</h3>

	<c:if test="${not empty errorMsgs}">
		<form style="color: red">請修正以下錯誤</form>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<form name="form1" action="ad_rep.do" method="post">
		<table>
			<tr>
				<td>廣告檢舉編號</td>
				<br>
				<td>
					<p name="ad_rep_id">${ad_repVO.ad_rep_id}</p>
				</td>
			</tr>
			<tr>
				<td>被檢舉廣告</td>
				<br>
				<td>
					
					<p name="ad_id">${ad_repVO.ad_id}</p>
				</td>
			</tr>
			<jsp:useBean id="memSvc" scope="page" class="com.goodhouse.member.model.MemService"/>
			<tr>
				<td>檢舉房客</td>
				<br>
				<td>
					<%
						MemVO memVO = memSvc.getOneMem(ad_repVO.getMem_id());
					%>
					<%=memVO.getMem_name() %>
				</td>
			</tr>
			<jsp:useBean id="empSvc" scope="page" class="com.goodhouse.employee.model.EmpService"/>
			<tr>
				<td>處理員工</td>
				<br>
				<td>
					<% 
						EmpVO empvo = empSvc.getOneEmp(ad_repVO.getEmp_id());
					%>
					<%=empvo.getEmp_name() %>
				</td>
			</tr>
			<tr>
				<td>檢舉內容</td>
				<br>
				<td>
					<input type="text" name="ad_rep_reason" value="${ad_repVO.ad_rep_reason}">
				</td>
			</tr>
			<tr>
				<td>檢舉狀態</td><br>
				<td>
						<!--Ad_statusList指向--Ad_reportStatus.java第43行>-->
					<select name="ad_rep_status">
						<c:forEach var="ad_status" items="${Ad_statusList}">
							<option value="${ad_status.rep_status_all}" ${(ad_status.rep_status_all == ad_repVO.ad_rep_status)?'selected':''}>${ad_status.rep_status_type}
						</c:forEach>
						</select>	
			</td>
			</tr>
			<tr>
				<td>檢舉日期</td>
				<td>
					<input tepe="text" name="ad_rep_date" id="f_date1">
				</td>
			</tr>

		</table>
		<br>
		<input type="hidden" name="action" value="update">
		<input type="hidden" name="ad_rep_id" value="<%=ad_repVO.getAd_rep_id() %>">
		<input type="hidden" name="ad_rep_id" value="${ad_repVO.ad_rep_id}">
		<input type="hidden" name="ad_id" value="${ad_repVO.ad_id}">

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
		$('#f_date1').datetimepicker({
			theme: '',
			timepicker:false,
			step:1,
			format:'Y-m-d',
			value:'<%=ad_repVO.getAd_rep_date()%>', 
			// value: new Date(),
			//diabledDates:   ['2017/06/08','2017/06/09','2017/06/10'],
			//startDate:       '2017/07/10'
			//minDate:			'-1970-01-01',
			//maxDate:			'+1970-01-01',
		});
</script>
</html>