<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>frontLogin</title>
</head>
<body>
<jsp:include page="/FrontHeaderFooter/Header.jsp"/>
	<form action="<%=request.getContextPath()%>/FrontLoginHandler" method="post">
		
			<table border=1>
				<tr>
					<td colspan=2>
						<p align=center>
							輸入<b>(測試登入)</b>:<br> 
							會員帳號:LOREN88@abc.com<br>
							會員密碼:LOREN88<br>
					</td>
				</tr>

				<tr>
					<td>
						<p align=right>
							<b>會員信箱:</b>
					</td>
					<td>
						<p>
							<input type=text name="mem_email" value="" size=15>
					</td>
				</tr>

				<tr>
					<td>
						<p align=right>
							<b>會員密碼:</b>
					</td>
					<td>
						<p>
							<input type=password name="mem_password" value="" size=15>
					</td>
				</tr>


				<tr>
					<td colspan=2 align=center>
										
							<input type=submit value="  ok   ">
						
					</td>
				</tr>
			</table>
			
	</form>
	

<jsp:include page="/FrontHeaderFooter/Footer.jsp"/>
</body>
</html>