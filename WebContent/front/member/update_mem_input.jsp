<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.member.model.*"%>   

<%
  MemVO memVO = (MemVO) request.getAttribute("memVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>


<html>
<head>
<link   rel="stylesheet" type="text/css" href="datetimepicker/jquery.datetimepicker.css" />



<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>會員資料修改</title>
<meta charset="UTF-8">
<style>
 #table1 { 

  font-family: 微軟正黑體; 
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
<table id="table1">
	<tr><td>
		 <h3>會員資料修改</h3>
		 <h4><a href="">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/member/mem.do" name="form1" enctype="multipart/form-data">
<table id="table1">
	<tr>
		<td>會員姓名:</td>
		<td><input type="TEXT" name="mem_name" size="45" 
			 value="<%= (memVO==null)? "" : memVO.getMem_name()%>" /></td>
	</tr>
	<tr>
		<td>會員生日:</td>
		<td><input name="mem_birthday" class="f_date1" type="text"></td>
	</tr>
	<tr>
		<td>會員密碼:</td>
		<td><input type="password"  name="mem_password" size="45"
		value="<%= (memVO==null)?"" : memVO.getMem_password()%>"/></td>
		
	</tr>
	<tr>
		<td>會員地址:</td>
		<td><input type="TEXT" name="mem_address" size="45"
			 value="<%= (memVO==null)?""  : memVO.getMem_address()%>" /></td>
	</tr>
	<tr>
		<td>會員郵遞區號:</td>
		<td><input type="TEXT" name="mem_zipcode" size="45"
			 value="<%= (memVO==null)? "" : memVO.getMem_zipcode()%>" /></td>
	</tr>
	<tr>
		<td>會員電話:</td>
		<td><input type="TEXT" name="mem_telephone" size="45"
			 value="<%= (memVO==null)? "" : memVO.getMem_telephone()%>" /></td>
	</tr>
	<tr>
		<td>會員手機:</td>
		<td><input type="TEXT" name="mem_phone" size="45"
			 value="<%= (memVO==null)? "" : memVO.getMem_phone()%>" /></td>
	</tr>
	<tr>
		<td>會員信箱:</td>
		<td><input type="TEXT" name="mem_email" size="45"
			 value="<%= (memVO==null)? "" : memVO.getMem_email()%>" /></td>
	</tr>
	<tr>
		<td>會員狀態:</td>
		<td>
			<select name="mem_status">
				<option  value="1" selected>一般會員</option>
				
			</select>
		</td>
	</tr>
	<tr>
		<td>會員圖片:</td>
		<td><input type="file" name="mem_picture" size="45"	alt="..."/></td>
	</tr>
	<tr>
		<td>積分總和:</td>
		<td><%= (memVO==null)? "" : memVO.getGood_total()%></td>
	</tr>

	
	<tr>
		<td>會員性別:</td>
		<td>
			<select name="mem_sex">
				<option  value="1" selected>男</option>
				<option  value="2" >女</option>
			</select>
		</td>
	</tr>
	<tr>
		<td></td>
		<td>	

<input type="hidden" name="action" value="update">
<input type="hidden" name="good_total" value="<%=memVO.getGood_total()%>">
<input type="hidden" name="mem_id" value="<%=memVO.getMem_id()%>">
<input type="submit" class="btn btn-secondary" value="送出修改"></FORM>
			</td>
	</tr>
	
</table>
<br>


<jsp:include page="/FrontHeaderFooter/Footer.jsp"/>
</body>

<script src="datetimepicker/jquery.js"></script>
<script src="datetimepicker/jquery.datetimepicker.full.js"></script>
<script>
        $.datetimepicker.setLocale('zh'); // kr ko ja en
        $('.f_date1').datetimepicker({
           theme: '',          //theme: 'dark',
           timepicker: false,   //timepicker: false,
           step: 1,            //step: 60 (這是timepicker的預設間隔60分鐘)
	       format: 'Y-m-d ',
	       value: '',
           
        });
        
//      1.以下為某一天之前的日期無法選擇
             var somedate1 = new Date('2017-06-15');
             $('#f_date1').datetimepicker({
                 beforeShowDay: function(date) {
               	  if (  date.getYear() <  somedate1.getYear() || 
        		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
                     ) {
                          return [false, ""]
                     }
                     return [true, ""];
             }});
 
</script>

</html>