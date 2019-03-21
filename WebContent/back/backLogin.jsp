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
<div class="lowin lowin-purple">
	<div class="lowin-brand">
		<img src="images/kodinger.jpg" alt="logo">
	</div>
	<div class="lowin-wrapper">
		<div class="lowin-box lowin-login">
			<div class="lowin-box-inner">
						     輸入<b>(測試登入)</b>:<br> 
							員工ID:<b>E000000003</b><br>
							員工密碼:<b>888777</b><br>
				<form action="<%=request.getContextPath()%>/BackLoginHandler" method="post">
					<p>Sign in to continue</p>
					<div class="lowin-group">
						<label for="mem_email">會員信箱: <a href="#" class="login-back-link">登入</a></label>
						<input type="text" autocomplete="email" name="emp_id" id="mem_email" class="lowin-input">
					</div>
					<div class="lowin-group password-group">
						<label for="mem_password">會員密碼: <a href="#" class="forgot-link">忘記密碼</a></label>
						<input type="password" name="emp_password" id="mem_password" autocomplete="current-password" class="lowin-input">
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

</body>
</html>