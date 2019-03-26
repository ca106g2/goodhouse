<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="loginJS/auth.css">
<title>backLogin</title>
</head>
<body>
<jsp:include page="/BackHeaderFooter/Header.jsp" />
<div class="lowin lowin-purple">
	<div class="lowin-brand">
		<img src="images/kodinger.jpg" alt="logo">
	</div>
	<div class="lowin-wrapper">
		<div class="lowin-box lowin-login">
			<div class="lowin-box-inner">
<!-- 						     輸入<b>(測試登入)</b>:<br>  -->
<!-- 							員工ID:<b>E000000003</b><br> -->
<!-- 							員工密碼:<b>888777</b><br> --> 
				<img src="<%=request.getContextPath()%>/images/magic.png" width="25" height="25" style="margin-bottom:10px" id="magic">			
				<form action="<%=request.getContextPath()%>/BackLoginHandler" method="post">
					<p>Sign in to continue</p>
					<div class="lowin-group">
						<label for="mem_email">員工ID: <a href="#" class="login-back-link">登入</a></label>
						<input type="text" autocomplete="email" name="emp_id" id="emp_id" class="lowin-input">
					</div>
					<div class="lowin-group password-group">
						<label for="mem_password">員工密碼: </label>
						<input type="password" name="emp_password" id="emp_password" autocomplete="current-password" class="lowin-input">
					</div>
					<button class="lowin-btn login-btn" type="submit">
						登入
					</button>
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
		login_url: '<%=request.getContextPath()%>/BackLoginHandler'
	}); 
	  
	
</script>
	<jsp:include page="/BackHeaderFooter/Footer.jsp" />
</body>

<script>
$('#magic').click(function(){
	$('#emp_id').val('E000000003');
	$('#emp_password').val('888777');
})
</script>
</html>