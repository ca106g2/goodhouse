<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.employee.model.*"%>

<%
  EmpVO empVO = (EmpVO) request.getAttribute("empVO"); //EmpServlet.java (Concroller) �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>���u��ƭק� </title>
<style>
 #table1 { 

  font-family: �L�n������; 
  font-size:16px; 
  width:500px;
  text-align:center;
  margin-left:auto; 
  margin-right:auto;
} 
 #table1 th { 
  background-color: #009FCC;
  padding:10px;

  color:#fff;
} 
 #table1 td { 
  padding:5px;
} 


</style>


</head>
<body bgcolor='white'>
<jsp:include page="/FrontHeaderFooter/Header.jsp"/>
<table id="table-1">
	<tr><td>
		 <h4><a href="select_page.jsp">�^���u����</a></h4>
	</td></tr>
</table>



<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="emp.do" name="form1">
<table id="table1">
	<tr>
		<td>���u�s��:<font color=red><b>*</b></font></td>
		<td><%=empVO.getEmp_id()%></td>
	</tr>


	<tr>
		<td>���u�m�W:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="emp_name" size="45" value="<%=empVO.getEmp_name()%>"></td>
	</tr>
	<tr>
		<td>���u�q��:</td>
		<td><input type="TEXT" name="emp_phone" size="45" value="<%=empVO.getEmp_phone()%>" /></td>
	</tr>
	<tr>
		<td>���u�b��:</td>
		<td><input type="TEXT" name="emp_account" size="45"	value="<%=empVO.getEmp_account()%>" /></td>
	</tr>
	<tr>
		<td>���u�K�X:</td>
		<td><input type="password" name="emp_password" size="45" value="<%=empVO.getEmp_password()%>" /></td>
	</tr>

	<tr>
		<td>���u���A:</td>
		<td>
			<select name="emp_status">
				<option  value="1">��¾</option>
				<option  value="2" selected>��¾</option>
			</select>
		</td>
	</tr>
	
	<tr>
		<td></td>
			<td>
			<input type="hidden" name="action" value="update">
			<input type="hidden" name="emp_id" value="<%=empVO.getEmp_id()%>">
			<input type="submit" class="btn btn-secondary" value="�e�X�s�W">
		</td>
	</tr>
</table>
<br>
</FORM>

<jsp:include page="/FrontHeaderFooter/Footer.jsp"/>
</body>
</html>