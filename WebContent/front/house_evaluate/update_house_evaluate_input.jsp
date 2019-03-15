<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.house_evaluate.model.*"%>


<%
  House_EvaluateVO heVO = (House_EvaluateVO) request.getAttribute("House_EvaluateVO"); //House_EvaluateServlet.java (Concroller) 存入req的House_EvaluateVO物件 (包括幫忙取出的House_EvaluateVO 也包括輸入資料錯誤時的House_EvaluateVO物件)
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>房屋評價修改</title>
</head>
<body bgcolor='white'>
	
	<table id="table-1">
		<tr>
			<td>
				<h3>房屋評價修改 - update_house_evaluate_input.jsp</h3>
				<h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
			</td>
		</tr>
	</table>
	
	<h3>資料修改:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color:red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color:red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	
	<form name="the_form" METHOD="post" ACTION="house_evaluate.do">
		<table>
			
			<tr>
				<td>評價等級</td><br>
				<td>
					<label>
						<input type="radio" name="hou_eva_grade" value="G1非常不好"  ${(House_EvaluateVO.hou_eva_grade eq 'G1非常不好') ? 'checked' : ''} >非常不好<br>
					</label>
					<label>
						<input type="radio" name="hou_eva_grade" value="G2不好" ${(House_EvaluateVO.hou_eva_grade eq 'G2不好') ? 'checked' : ''}>不好<br>
					</label>
					<label>
						<input type="radio" name="hou_eva_grade" value="G3普通" ${(House_EvaluateVO.hou_eva_grade eq 'G3普通') ? 'checked' : ''}>普通<br>
					</label>
					<label>
						<input type="radio" name="hou_eva_grade" value="G4好" ${(House_EvaluateVO.hou_eva_grade eq 'G4好') ? 'checked' : ''}>好<br>
					</label>
					<label>
						<input type="radio" name="hou_eva_grade" value="G5非常好" ${(House_EvaluateVO.hou_eva_grade eq 'G5非常好') ? 'checked' : ''}>非常好<br>
					</label>
				</td>
			</tr><br>
			<tr>
				<td>評價內容</td><br>
				<td>
					<textarea name="hou_eva_content" value="<%=heVO.getHou_eva_content()%>" rows="3" cols=50><%=heVO.getHou_eva_content()%></textarea>
				</td>
			</tr>
		</table>
		<input type="hidden" name="action" value="update">
		<input type="hidden" name="hou_eva_id" value="<%=heVO.getHou_eva_id()%>">
		<input type="hidden" name="mem_id" value="<%=heVO.getMem_id()%>">
		<input type="hidden" name="hou_id" value="<%=heVO.getHou_id()%>">
		<input type="submit" value="送出修改">
	</form>
	
</body>
</html>