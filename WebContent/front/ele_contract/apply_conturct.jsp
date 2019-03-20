<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.goodhouse.ele_contract.model.*"%>
<%@ page import="com.goodhouse.apply_conturct.model.*"%>
<%@ page import="com.goodhouse.member.model.*"%>
<%@ page import="com.goodhouse.house.model.*"%>

<%
	Ele_ContractVO eleConVO = (Ele_ContractVO)session.getAttribute("eleConVO");
	Apply_ConturctVO appConVO = (Apply_ConturctVO)request.getAttribute("appConVO");
	String acc = (String)session.getAttribute("appConChoose");
	pageContext.setAttribute("acc",acc);
	pageContext.setAttribute("eleConVO",eleConVO);
	pageContext.setAttribute("appConVO",appConVO);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<jsp:include page="/FrontHeaderFooter/Header.jsp" />
	
	<div class="container">
		<div class="row justfy-content-center">
			<div class="row col-2">
				<table id="table-1">
					<p>
						回首頁<a href="mem_select_page.jsp"><img
							src="<%=request.getContextPath()%>/share_pic/back1.gif"
							width="100" height="30 !important"></a>
					</p>
					<tr>
						
					</tr>
				</table>
				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
			</div>
			<div class="row col-10">
				<form method="post" action="apply_conturct.do">
					<table>
						<tr>
							<td>電子合約編號</td>
							<td>${eleConVO.ele_con_id}</td>
						</tr>
						<jsp:useBean id="memSvc" class="com.goodhouse.member.model.MemService"/>
						<tr>
							<td>會員編號</td>
							<td>${memSvc.getOneMem(eleConVO.mem_id).mem_name}</td>
						</tr>
						<jsp:useBean id="houSvc" class="com.goodhouse.house.model.HouseService"/>
						<tr>
							<td>房屋編號</td>
							<td>${houSvc.getOneHouse(eleConVO.hou_id).hou_name}</td>
						</tr>
						<tr>
							<td>申請選項</td>
							<td>
							<c:forEach var="AppConChoose" items="${Apply_ConturctChooseMap}">
								<c:if test="${AppConChoose.key eq acc}">
									${AppConChoose.value.choose_name}
								</c:if>
							</c:forEach>
<!-- 								<label> -->
<!-- 									<input type="radio" name="app_con_content" value="a1">解約<br> -->
<!-- 								</label> -->
<!-- 								<label> -->
<!-- 									<input type="radio" name="app_con_content" value="a2">續約<br> -->
<!-- 								</label> -->
							</td>
						</tr>
						<tr>
							<td>申請內容</td>
							<td>
								<textarea name="app_con_other" rows="3" cols="30" ></textarea>
							</td>
						</tr>
					</table>
					
					<input type="hidden" name="action" value="insert">
					<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
					<input type="hidden" name="ele_con_id" value="${eleConVO.ele_con_id}">
					<input type="hidden" name="mem_id" value="${eleConVO.mem_id}">
					<input type="hidden" name="hou_id" value="${eleConVO.hou_id}">
					<input type="hidden" name="app_con_content" value="${acc}">
					<input type="hidden" name="app_con_status" value="${Apply_ConturctStatusMap['s1'].status_no}">
					<input type="submit" value="申請">
					
				</form>
			</div>
		</div>
	</div>

</body>
</html>