><%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.ad_report.model.*"%>
<%
	Ad_reportVO ad_repVO = (Ad_reportVO) request.getAttribute("ad_repVO");
%>
<html>
<head>
<title>Insert title here</title>
</head>
<body bgcolor='white' span style="font-family:Microsoft JhengHei;">
	<table id="table-1">
		<tr>
			<td>
				<h3>新增</h3>
			</td>
			<td>
				<h4><a href="select_page.jsp">回首頁</a></h4>
			</td>
		</tr>
	</table>

	<h3>新增資料</h3>

	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<form method="post" action="ad_rep.do" name="form1">
		<table>
			<tr>
				<td>廣告檢舉編號</td>
				<td><input type="text" name="ad_id" size="45"
					value="<%=(ad_repVO == null) ? "AD00000001" : ad_repVO.getAd_id()%>" />
				</td>
			</tr>
			<tr>
				<td>檢舉人</td>
				<td><input type="text" name="mem_name" size="45"
					value="<%=(ad_repVO == null) ? "" : ad_repVO.getMem_id()%>"></td>
			</tr>
			<tr>
				<td>檢舉事由</td>
				<td><input type="text" name="ad_rep_reason" size="45"
					value="<%=(ad_repVO == null) ? "請輸入事由" : ad_repVO.getAd_rep_reason()%>">
				</td>
			</tr>
			<tr>
				<td>檢舉狀態</td>
				<td><select name="ad_rep_status">
						<c:forEach var="ad_status" items="${Ad_statusList}">
							<option value="${ad_status.rep_status_all}"
								${(ad_status.rep_status_all == ad_repVO.ad_rep_status)?'selected':''}>${ad_status.rep_status_type}
						</c:forEach>
				</select></td>
			</tr>

			<tr>
				<td>檢舉日期</td>
				<td><input name="ad_rep_date" id="f_date1" type="text">
				</td>
			</tr>
		</table>
		<input type="hidden" name="action" value="insert" /> <input
			type="submit" value="送出新增" />
	</form>
</body>

<%
	java.sql.Date ad_rep_date = null;
	try{
		
	} catch (Exception e){
		ad_rep_date = new java.sql.Date(System.currentTimeMillis());
	}
%>
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

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=ad_rep_date%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
</script>
</html>