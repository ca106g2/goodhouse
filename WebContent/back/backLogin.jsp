<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>backLogin</title>
</head>
<body>

	<form action="<%=request.getContextPath()%>/BackLoginHandler" method="post">
		
			<table border=1>
				<tr>
					<td colspan=2>
						<p align=center>
							輸入<b>(測試登入)</b>:<br> 
							員工ID:<b>E000000003</b><br>
							員工密碼:<b>888777</b><br>
					</td>
				</tr>

				<tr>
					<td>
						<p align=right>
							<b>員工ID:</b>
					</td>
					<td>
						<p>
							<input type=text name="emp_id" value="" size=15>
					</td>
				</tr>

				<tr>
					<td>
						<p align=right>
							<b>員工密碼:</b>
					</td>
					<td>
						<p>
							<input type=password name="emp_password" value="" size=15>
					</td>
				</tr>


				<tr>
					<td colspan=2 align=center>
						
							<input type=submit value="  ok   ">
						
					</td>
				</tr>
			</table>
	</form>


</body>
</html>