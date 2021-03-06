<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.landlord.model.*"%>    
<%@ page import="com.goodhouse.member.model.*" %>   
  
<%
    LanVO lanVO = (LanVO) request.getAttribute("lanVO");
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	pageContext.setAttribute("memVO", memVO);
%>  
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>房東資料新增</title>
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
		 <h3>房東資料新增</h3></td><td>

	</td></tr>
</table>



<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="lan.do" name="form1" enctype="multipart/form-data">
<table id="table1">
	<tr>
		<td>會員名稱:</td>
		<td>${memVO.mem_name}</td>
	</tr>
	<tr>
		<td>房東發票:</td>
		<td>
			<select name="lan_receipt">
				<option  value="1">慈善機構</option>
				<option  value="2" selected>自動兌獎(中獎匯款)</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>房東戶頭:</td>
		<td><input type="TEXT"  name="lan_account" size="45"
		value="<%= (lanVO==null)? "" : lanVO.getLan_account()%>"/></td>
		
	</tr>


	<tr>
		<td>良民證:</td>
		<td><input type="file" name="lan_ciziten" autocomplete="current-password" class="lowin-input" id="img">						
	    <img id="preview" src="#" style="display: none;" width="100" height="100"></td>
	</tr>

	<tr>
		<td></td>
		<td>
		
			<input type="submit" class="btn btn-secondary" value="送出新增">
			<input type="hidden" name="action" value="insert">
			<input type="hidden" name="mem_id" value="${memVO.mem_id}">
		
		</td>
	</tr>
</table>
</FORM>
<br>
<script type="text/javascript">
  
  $("#img").click(function(){
    $("#preview").attr('src',  '#');
    $("#preview").hide();
  });
  
  $("#img").change(function(){
    readURL(this,"#preview");
    $("#preview").show();
  });
  
  function readURL(input,imgid){    
      if(input.files && input.files[0]){
      var reader = new FileReader();
      reader.onload = function (e) {
         $(imgid).attr('src', e.target.result);
      }
      reader.readAsDataURL(input.files[0]);
    }
  }
</script>


<jsp:include page="/FrontHeaderFooter/Footer.jsp"/>
</body>
</html>