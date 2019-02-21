<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.house_evaluate.model.*"%>

<%
  House_EvaluateVO heVO = (House_EvaluateVO) request.getAttribute("House_EvaluateVO");
//House_EvaluateServlet.java (Concroller) 存入req的House_EvaluateVO物件 (包括幫忙取出的House_EvaluateVO 也包括輸入資料錯誤時的House_EvaluateVO物件)
%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta charset="UTF-8">
<title>新增房屋評價</title>
</head>
<body>
	
	<table id="table-1">
		<tr>
			<td><h3>房屋評價新增 - add_house_evaluate.jsp</h3></td>
			<td><h4><a href="select_page.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4></td>
		</tr>
	</table>
	
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color:red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color:red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	
	
	<form method="post" action="house_evaluate.do" name="form1">
		<table>
		
			<jsp:useBean id="memSvc" scope="page" class="com.goodhouse.member.model.MemService" />
			<tr>
				<td>會員姓名</td>
				<td>
				    <input type="text" name="mem_name" value="${MenVO.mem_name}" size="45">
				</td>
			</tr>
			
			<jsp:useBean id="houSvc" scope="page" class="com.goodhouse.house.model.HouseService" />
			<tr>
				<td>請選擇你的房屋名稱</td>
				<td>
					<select name="hou_id" >
						<c:forEach var="HouseVO" items="${houSvc.all}" > 
				        <option  value="${HouseVO.hou_id}">${HouseVO.hou_name}
				        </c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>請選擇評價等級</td>
				<td>
					<label>
						<input type="radio" name="hou_eva_grade" value="G1非常不好">非常不好<br>
					</label>
					<label>
						<input type="radio" name="hou_eva_grade" value="G2不好">不好<br>
					</label>
					<label>
						<input type="radio" name="hou_eva_grade" value="G3普通">普通<br>
					</label>
					<label>
						<input type="radio" name="hou_eva_grade" value="G4好">好<br>
					</label>
					<label>
						<input type="radio" name="hou_eva_grade" value="G5非常好">非常好<br>
					</label>
				</td>
			</tr>
			<tr>
				<td>請寫下評論</td>
				<td>
					<textarea name="hou_eva_content" rows="3" cols=50></textarea>
				</td>
			</tr>
		</table>
		<input type="hidden" name="action" value="insert">
		<input type="submit" value="送出新增">
	</form>
</body>
</html>