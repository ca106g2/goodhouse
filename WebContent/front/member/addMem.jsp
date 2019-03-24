<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.member.model.*"%>
<%@ page import="com.sun.org.apache.xerces.internal.impl.dv.util.Base64" %>   

<%
  MemVO memVO = (MemVO) request.getAttribute("memVO");
%>
<html>
<head>

<link   rel="stylesheet" type="text/css" href="datetimepicker/jquery.datetimepicker.css" />

<meta charset="UTF-8">
<title>會員新增資料</title>
<link rel="stylesheet" href="loginJS/auth.css">
</head>
<body>
<jsp:include page="/FrontHeaderFooter/Header.jsp"/>	
<div class="lowin">
	<div class="lowin-brand">
		<img src="images/kodinger.jpg" alt="logo">
	</div>
	<div class="lowin-wrapper">
		<div class="lowin-box lowin-register">
			<div class="lowin-box-inner">
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/member/mem.do" name="form1" enctype="multipart/form-data">
					<p>Let's create your account</p>
					<div class="lowin-group">
						<label>會員姓名:</label>
						<input id="mem_name" type="text" name="mem_name" autocomplete="name" class="lowin-input" 
							value="<%= (memVO==null)? "" : memVO.getMem_name()%>">
						<font color="red"><b>${errorMsgs.mem_name}</b></font>
					</div>
					<div class="lowin-group">
						<label>會員生日:</label>
						<input type="text" name="mem_birthday" id="f_date2" class="lowin-input">
						<font color="red"><b>${errorMsgs.mem_birthday}</b></font>
					</div>
					<div class="lowin-group">
						<label>會員密碼:</label>
						<input id="mem_password" type="password" name="mem_password" autocomplete="current-password" class="lowin-input" 
							value="<%= (memVO==null)?"" : memVO.getMem_password()%>">
						<font color="red"><b>${errorMsgs.mem_password}</b></font>
					</div>
					<div class="lowin-group">
						<label>會員地址:</label> 
						<input id="mem_address" type="TEXT" name="mem_address" autocomplete="current-password" class="lowin-input" 
							value="<%= (memVO==null)?""  : memVO.getMem_address()%>">
						<font color="red"><b>${errorMsgs.mem_address}</b></font>
					</div>
					<div class="lowin-group">
						<label>會員郵遞區號:</label>
						<input id="mem_zipcode" type="TEXT" name="mem_zipcode" autocomplete="current-password" class="lowin-input" 
							value="<%= (memVO==null)? "" : memVO.getMem_zipcode()%>">
						<font color="red"><b>${errorMsgs.mem_zipcode}</b></font>
					</div>
					<div class="lowin-group">
						<label>會員電話:</label>
						<input id="mem_telephone" type="TEXT" name="mem_telephone" autocomplete="current-password" class="lowin-input"
							value="<%= (memVO==null)? "" : memVO.getMem_telephone()%>">
						<font color="red"><b>${errorMsgs.mem_telephone}</b></font>
					</div>
					<div class="lowin-group">
						<label>會員手機:</label>
						<input id="mem_phone" type="TEXT" name="mem_phone" autocomplete="current-password" class="lowin-input" 
							value="<%= (memVO==null)? "" : memVO.getMem_phone()%>">
						<font color="red"><b>${errorMsgs.mem_phone}</b></font>
					</div>
					<div class="lowin-group">
						<label>會員信箱:</label>
						<input id="mem_email" type="email" name="mem_email" autocomplete="current-password" class="lowin-input"
							value="<%= (memVO==null)? "" : memVO.getMem_email()%>">
						<font color="red"><b>${errorMsgs.mem_email}</b></font>
					</div>
					<div class="lowin-group">
						<label>會員圖片:</label>
						<input type="file" name="mem_picture" autocomplete="current-password" class="lowin-input" id="img">						
	    				<img id="preview" src="#" style="display: none;" width="100" height="100">
						<font color="red"><b>${errorMsgs.mem_picture}</b></font>
					</div>
					<div class="lowin-group">
						<label>會員性別:</label>
						<select name="mem_sex" class="lowin-input">
							<option  value="1" selected>男</option>
							<option  value="2" >女</option>
						</select>
					</div>
					<input type="hidden" name="action" value="insert">
					<input type="hidden" name="mem_status" value="1">
					<button class="lowin-btn" type="submit">
						註冊
					</button>
					<font color="red"><b>${errorMsgs.Exception}</b></font>
				</form>
			</div>
		</div>
	</div>
	<footer class="lowin-footer">
		<input type="button" value="神奇小按鈕" id="magic" class="btn btn-secondary">	
	</footer>
</div>
<jsp:include page="/FrontHeaderFooter/Footer.jsp"/>
</body>

<script src="datetimepicker/jquery.js"></script>
<script src="datetimepicker/jquery.datetimepicker.full.js"></script>
<script src="<%=request.getContextPath()%>/front/member/loginJS/auth.js"></script>

<script>

$("#magic").click(function(){
	  $("#mem_name").val("曾冠娼");
	  $("#mem_password").val("123456");
	  $("#mem_address").val("新北市蘆洲區三民路1號");
	  $("#mem_zipcode").val("24751");
	  $("#mem_telephone").val("0229786230");
	  $("#mem_phone").val("0933022999");
	  $("#mem_email").val("cow18181818@gmail.com");
	});

</script>

<script>

        $.datetimepicker.setLocale('zh'); // kr ko ja en
        $('#f_date2').datetimepicker({
           theme: '',          //theme: 'dark',
           timepicker: false,   //timepicker: false,
           step: 1,            //step: 60 (這是timepicker的預設間隔60分鐘)
	       format: 'Y-m-d ',
	       value: '',  
        });
        
 
</script>
<script>
	Auth.init({
		forgot_url: '<%=request.getContextPath()%>/front/member/mem.do'
	});
</script>
<script>
  
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
</html>