<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.ad_report.model.*"%>
<%@ page import="com.goodhouse.landlord.model.*" %>
<%@ page import="com.goodhouse.member.model.*" %>

<%
	MemVO memVO = (MemVO)session.getAttribute("mVO");
	Ad_reportVO ad_repVO = (Ad_reportVO) request.getAttribute("ad_repVO");
%>




<html>
<head>
<meta charset="UTF-8">
<style>
#table1 {
	font-family:Microsoft JhengHei;;
	width: 1200px;
	text-align: center;
	border-collapse: collapse;
	margin-left: auto;
	margin-right: auto;
}

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
	border: 3px solid #000;
}

td {
	border: 3px solid #000;
	padding: 5px;
}

</style>

</head>
<body>
	<jsp:include page="/FrontHeaderFooter/Header.jsp" />
	<h4>
		<a href="select_page.jsp">回首頁</a>
	</h4>
	<form name="form1" action="ad_rep.do" method="post">
		<table id="table2">
			<tr class="table-light">
				<td>廣告檢舉編號</td>
				<td><input type="text" name="ad_id" size="45"
					value="<%=(ad_repVO == null) ? "AD00000001" : ad_repVO.getAd_id()%>" />
				</td>
			</tr>
	
			<tr class="table-light">
				<td>檢舉人</td>
				<td><%=memVO.getMem_name()%></td>
			</tr>
			<tr class="table-light">
				<td>檢舉事由</td>
				<td><label for="exampleFormControlTextarea1"></label> <textarea
						name="ad_rep_reason" class="form-control" placeholder="請輸入事由" 
						id="exampleFormControlTextarea1" rows="3"></textarea>
				</td>
			</tr>	
			<tr class="table-light">
				<td>檢舉狀態</td>
				<td><select name="ad_rep_status">
						<c:forEach var="ad_status" items="${Ad_statusList}">
							<option value="${ad_status.rep_status_all}"
								${(ad_status.rep_status_all == ad_repVO.ad_rep_status)?'selected':''}>${ad_status.rep_status_type}
						</c:forEach>
				</select></td>
			</tr>

			<tr class="table-light">
				<td>檢舉日期</td>
				<td><input name="ad_rep_date" id="f_date1" type="text">
				</td>
			</tr>
			<tr class="table-light"><td></td>
			<td>
			
		<%
			MemService memSvc = new  MemService();
		%>	

		<input type="hidden" name="mem_id" value="<%=memVO.getMem_id()%>">	

		<input type="hidden" name="action" value="front_insert" />
		<input type="submit" value="送出新增" />
		</td></tr>
		</table>

	</form>
	<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
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