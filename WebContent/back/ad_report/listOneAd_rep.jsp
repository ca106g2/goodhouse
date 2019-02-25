<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ page import ="com.goodhouse.ad_report.model.*" %>

<%
	Ad_reportVO ad_repVO = (Ad_reportVO) request.getAttribute("ad_repVO");
%>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table{
	width:1100px;
	background-color:balck;
	margin-top: 5px;
	margin-bottom:5px;
	
}
table, th ,td{
	border: 1.5px solid #CCCCFF;
}
th, td{
	padding: 5px;
	text-align:center;
	text: 12px;
	font-family:Microsoft JhengHei;
}

</style>
</head>
<body>

	<table id="table-1">
		<tr><td>
			<h3>檢舉單筆資料</h3>
			<h4><a href="select_page.jsp">回首頁</a></h4>
		</td></tr>
	</table>
	
	<table>
		<tr>
		<th>廣告檢舉編號</th>
		<th>被檢舉廣告</th>
		<th>檢舉房客</th>
		<th>處理員工</th>
		<th>檢舉內容</th>
		<th>檢舉狀態</th>
		<th>檢舉日期</th>
		</tr>
		<tr>
		<td><%=ad_repVO.getAd_rep_id() %></td>
		<td><%=ad_repVO.getAd_id() %></td>
		<td><%=ad_repVO.getMem_id() %></td>
		<td><%=ad_repVO.getEmp_id() %></td>
		<td><%=ad_repVO.getAd_rep_reason() %></td>
		<td><%=ad_repVO.getAd_rep_status() %></td>
		<td><%=ad_repVO.getAd_rep_date() %></td>
		</tr>
	</table>
</body>
</html>