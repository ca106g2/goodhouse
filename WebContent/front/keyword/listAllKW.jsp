<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.goodhouse.keyword.model.*"%>
<!DOCTYPE html>
<html>
<head>
<title>����r�C�X - listAllKW.jsp</title>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>
<table id="table-1">
	<tr><td>
		 <h3>�C�X�Ҧ�����r- listAllKW.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<table>
	<tr>
		<th>����r</th>
	</tr>
</table>

</body>
</html>