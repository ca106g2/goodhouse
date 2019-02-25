<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="UTF-8">
<title>Account_Report HomePage</title>

<style>
table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 3px ridge Gray;
	height: 80px;
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
</head>
<body bgcolor='white'>
	<table id="table-1">
		<tr>
			<td><h3>Account_Report HomePage</h3>
				<h4>MVC</h4></td>
		</tr>
	</table>
	<p>This is the Home page for Account_Report�GHome</p>

	<h3>��Ƭd��</h3>

	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">message</li>
			</c:forEach>
		</ul>
	</c:if>
	<ul>
		<li><a href="listAllAccount_report.jsp">list</a> all
			Account_Report.<br>
		<br></li>
		<li>
			<form METHOD="post" ACTION="acre.do">
				<b>��J���|�s��</b> <input type="text" name="acc_rep_id"> <input
					type="hidden" name="action" value="getOne_For_Display"> <input
					type="submit" value="�e�X">
			</FORM>
		</li>

		<jsp:useBean id="acreSvc" scope="page"
			class="com.goodhouse.account_report.model.Account_reportService" />

		<li>
			<FORM METHOD="post" ACTION="acre.do">
				<b>���|�s��:</b> <select size="1" name="acc_rep_id">
					<c:forEach var="acreVO" items="${acreSvc.all}">
						<option value="${acreVO.acc_rep_id}">${acreVO.acc_rep_id}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="�e�X">
			</FORM>
		</li>

	</ul>


	<h3>���|�޲z</h3>


	<ul>
		<li><a href='addAccount_report.jsp'>Add</a> a new Report.</li>
	</ul>
</body>
</html>