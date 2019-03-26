<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.member.model.*"%>       

<%
  MemVO memVO = (MemVO) request.getAttribute("memVO");
%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>frontLogin</title>
<link rel="stylesheet" href="loginJS/auth.css">
</head>
<body>
<jsp:include page="/FrontHeaderFooter/Header.jsp"/>
<div class="lowin">
	<div class="lowin-brand">
		<img src="images/kodinger.jpg" alt="logo">
	</div>
	<div class="lowin-wrapper">
		<div class="lowin-box lowin-login">
			<div class="lowin-box-inner">
				<form action="<%=request.getContextPath()%>/FrontLoginHandler" method="post">
					<p>Sign in to continue</p>
					<div class="lowin-group">
						<label for="mem_email">會員信箱: <a href="#" class="login-back-link">登入</a></label>
						<input type="text" autocomplete="email" name="mem_email" id="mem_email" class="lowin-input">
					</div>
					<div class="lowin-group password-group">
						<label for="mem_password">會員密碼: <a href="#" class="forgot-link">忘記密碼</a></label>
						<input type="password" name="mem_password" id="mem_password" autocomplete="current-password" class="lowin-input">
					</div>
					<button class="lowin-btn login-btn" type="submit">
						登入
					</button>

					<div class="text-foot">
						<a href="" class="register-link">註冊</a>成為會員
					</div>
				</form>
			</div>
		</div>

		<div class="lowin-box lowin-register">
			<div class="lowin-box-inner">
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/member/mem.do" name="form1" enctype="multipart/form-data">
					<p>Let's create your account</p>
						<div class="lowin-group">
							<label>會員姓名:</label>
							<input type="text" name="mem_name" autocomplete="name" class="lowin-input" 
								value="<%= (memVO==null)? "" : memVO.getMem_name()%>">
							<font color="red"><b>${errorMsgs.mem_name}</b></font>
						</div>
						<div class="lowin-group">
							<label>會員生日:</label>
							<input type="text" name="mem_birthday" id="f_date1" class="lowin-input">
							<font color="red"><b>${errorMsgs.mem_birthday}</b></font>
						</div>
						<div class="lowin-group">
							<label>會員密碼:</label>
							<input type="password" name="mem_password" autocomplete="current-password" class="lowin-input" 
								value="<%= (memVO==null)?"" : memVO.getMem_password()%>">
							<font color="red"><b>${errorMsgs.mem_password}</b></font>
						</div>
						<div class="lowin-group">
							<label>會員地址:</label>
							<input type="TEXT" name="mem_address" autocomplete="current-password" class="lowin-input" 
								value="<%= (memVO==null)?""  : memVO.getMem_address()%>">
							<font color="red"><b>${errorMsgs.mem_address}</b></font>
						</div>
						<div class="lowin-group">
							<label>會員郵遞區號:</label>
							<input type="TEXT" name="mem_zipcode" autocomplete="current-password" class="lowin-input" 
								value="<%= (memVO==null)? "" : memVO.getMem_zipcode()%>">
							<font color="red"><b>${errorMsgs.mem_zipcode}</b></font>
						</div>
						<div class="lowin-group">
							<label>會員電話:</label>
							<input type="TEXT" name="mem_telephone" autocomplete="current-password" class="lowin-input"
								value="<%= (memVO==null)? "" : memVO.getMem_telephone()%>">
							<font color="red"><b>${errorMsgs.mem_telephone}</b></font>
						</div>
						<div class="lowin-group">
							<label>會員手機:</label>
							<input type="TEXT" name="mem_phone" autocomplete="current-password" class="lowin-input" 
								value="<%= (memVO==null)? "" : memVO.getMem_phone()%>">
							<font color="red"><b>${errorMsgs.mem_phone}</b></font>
						</div>
						<div class="lowin-group">
							<label>會員信箱:</label>
							<input type="email" name="mem_email" autocomplete="current-password" class="lowin-input"
								value="<%= (memVO==null)? "" : memVO.getMem_email()%>">
							<font color="red"><b>${errorMsgs.mem_email}</b></font>
						</div>
						<div class="lowin-group">
							<label>會員圖片:</label>
							<input type="file" name="mem_picture" autocomplete="current-password" class="lowin-input">
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
						<div class="lowin-group">
							<img src="<%=request.getContextPath()%>/front/pointgoods/images/magic.gif" id="magic" width="30px" height="30px">
						</div>
						<font color="red"><b>${errorMsgs.Exception}</b></font>
					<div class="text-foot">
						Already have an account? <a href="" class="login-link">Login</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	<footer class="lowin-footer">

	</footer>
</div>

<script src="loginJS/auth.js"></script>
<script>
	Auth.init({
		login_url: '<%=request.getContextPath()%>/FrontLoginHandler',
		forgot_url: '<%=request.getContextPath()%>/front/member/mem.do'
	});
</script>
	

<jsp:include page="/FrontHeaderFooter/Footer.jsp"/>
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
</body>
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
</html>