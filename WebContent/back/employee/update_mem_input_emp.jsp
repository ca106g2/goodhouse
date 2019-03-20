<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.member.model.*"%>   

<%
  MemVO memVO = (MemVO) request.getAttribute("memVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>


<html>
<head>

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>會員資料修改 - update_mem_input.jsp</title>
<meta charset="UTF-8">

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
<jsp:include page="/FrontHeaderFooter/Header.jsp"/>	
<table id="table-1">
	<tr><td>
		 <h3>會員資料修改 - update_mem_input_emp.jsp</h3>
		 <a href="<%=request.getContextPath()%>/back/employee/select_page.jsp">回首頁</a>
	</td></tr>
</table>

<h3>資料修改:</h3>
<h1><%=memVO.getMem_id()%></h1>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="mem.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>會員姓名:</td>
		<td><%=memVO.getMem_name()%></td>
	</tr>
	<tr>
		<td>會員生日:</td>
		<td><%=memVO.getMem_birthday()%></td>
	</tr>
	<tr>
		<td>會員密碼:</td>
		<td><%= memVO.getMem_password()%></td>
		
	</tr>
	<tr>
		<td>會員地址:</td>
		<td><%= memVO.getMem_address()%></td>
	</tr>
	<tr>
		<td>會員郵遞區號:</td>
		<td><%= memVO.getMem_zipcode()%></td>
	</tr>
	<tr>
		<td>會員電話:</td>
		<td><%= memVO.getMem_telephone()%></td>
	</tr>
	<tr>
		<td>會員手機:</td>
		<td><%= memVO.getMem_phone()%></td>
	</tr>
	<tr>
		<td>會員信箱:</td>
		<td><%= memVO.getMem_email()%></td>
	</tr>
	<tr>
		<td>會員狀態:</td>
		<td>
			<select name="mem_status">
				<option  value="1" selected>一般會員</option>
				<option  value="2" >封鎖中</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>會員圖片:</td>
<%-- 		<td><%= memVO.getMem_picture%></td> --%>
		<td><input type="file" name="mem_picture" size="45"	alt="..."/></td>
	</tr>
	<tr>
		<td>積分總和:</td>
		<td><%= memVO.getGood_total()%></td>
	</tr>

	
	<tr>
		<td>會員性別:</td>
		<td><%= memVO.getMem_sex()%></td>

	</tr>
	
	
</table>
<br>
<input type="hidden" name="action" value="updateback">
<input type="hidden" name="mem_id" value="<%=memVO.getMem_id()%>">
<input type="hidden" name="mem_name" value="<%=memVO.getMem_name()%>">
<input type="hidden" name="mem_birthday" value="<%=memVO.getMem_birthday()%>">
<input type="hidden" name="mem_password" value="<%=memVO.getMem_password()%>">
<input type="hidden" name="mem_address" value="<%=memVO.getMem_address()%>">
<input type="hidden" name="mem_zipcode" value="<%=memVO.getMem_zipcode()%>">
<input type="hidden" name="mem_telephone" value="<%=memVO.getMem_telephone()%>">
<input type="hidden" name="mem_phone" value="<%=memVO.getMem_phone()%>">
<input type="hidden" name="mem_email" value="<%=memVO.getMem_email()%>">
<input type="hidden" name="mem_status" value="<%=memVO.getMem_status()%>">
<input type="hidden" name="mem_picture" value="<%=memVO.getMem_picture()%>">
<input type="hidden" name="good_total" value="<%=memVO.getGood_total()%>">
<input type="hidden" name="mem_sex" value="<%=memVO.getMem_sex()%>">

<input type="submit" value="送出修改"></FORM>


<jsp:include page="/FrontHeaderFooter/Footer.jsp"/>
</body>
<script src="datetimepicker/jquery.js"></script>
<script src="datetimepicker/jquery.datetimepicker.full.js"></script>
<link   rel="stylesheet" type="text/css" href="datetimepicker/jquery.datetimepicker.css" />
<script>
        $.datetimepicker.setLocale('zh'); // kr ko ja en
        $('#f_date1').datetimepicker({
           theme: '',          //theme: 'dark',
           timepicker: false,   //timepicker: false,
           step: 1,            //step: 60 (這是timepicker的預設間隔60分鐘)
	       format: 'Y-m-d ',
	       value: '',  
        });
 
</script>
</html>