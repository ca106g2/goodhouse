<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.goodhouse.ele_contract.model.*"%>
<%
	Ele_ContractVO eleConVO = (Ele_ContractVO) session.getAttribute("eleConVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	
</head>
<body onload="connect();" onunload="disconnect();">
<jsp:include page="/FrontHeaderFooter/Header.jsp" />
	<div class="row-12">
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
	<div class="row-12">
		<p>
			回首頁<a href="mem_select_page.jsp"><img
				src="<%=request.getContextPath()%>/share_pic/back1.gif"
				width="100" height="30 !important"></a>
		</p>
		<h3>填寫繳費帳單資料</h3>
		<form method="post" action="bill.do">
			<table>
			<jsp:useBean id="memSvc" scope="page" class="com.goodhouse.member.model.MemService"></jsp:useBean>
			<jsp:useBean id="houSvc" scope="page" class="com.goodhouse.house.model.HouseService"></jsp:useBean>
				<tr>
					<td>會員編號(由電子合約帶出資料)</td>
					<td>${memSvc.getOneMem(eleConVO.mem_id).mem_name}</td>
				</tr>
				<tr>
					<td>房屋編號(由電子合約帶出資料)</td>
					<td>${houSvc.getOneHouse(eleConVO.hou_id).hou_name}</td>
				</tr>
				<tr>
					<td>第一期繳交費用(租金+押金)</td>
					<td>${eleConVO.ele_rent_money + eleConVO.ele_deposit_money}</td>
				</tr>
				<tr>
					<td>輸入信用卡卡號</td>
					<td>
						<input type="text" name="phovisa0" maxlength="4" size="4" onKeyUp="next(this)">
						<input type="text" name="phovisa1" maxlength="4" size="4" onKeyUp="next(this)">
						<input type="text" name="phovisa2" maxlength="4" size="4" onKeyUp="next(this)">
						<input type="text" name="phovisa3" maxlength="4" size="4" onKeyUp="next(this)">
					</td>
				</tr>
				<tr>
					<td>繳費型態(由電子合約帶出資料)</td>
					<c:forEach var="Bill_PaymentType" items="${Bill_PaymentTypeMap}">
						<c:if test="${Bill_PaymentType.key eq eleConVO.bill_paymenttype}">
							<td>${Bill_PaymentType.value.type_name}</td>
						</c:if>
					</c:forEach>
				</tr>
			</table>
			<%
				java.sql.Date bill_date = new java.sql.Date(System.currentTimeMillis());
				java.sql.Date bill_producetime = new java.sql.Date(System.currentTimeMillis());
			%>
			<input type="hidden" name="action" value="creatFirstBill">
			<input type="hidden" name="ele_con_id" value="${eleConVO.ele_con_id}">
			<input type="hidden" name="bill_pay" value="${eleConVO.ele_rent_money + eleConVO.ele_deposit_money}">
			<input type="hidden" name="bill_date" value="<%=bill_date%>">
			<input type="hidden" name="bill_producetime" value="<%=bill_producetime%>">
			<input type="hidden" name="bill_status" value="s3">
			<input type="hidden" name="bill_paymethod" value="VISACard">
			<input type="hidden" name="bill_paymenttype" value="${eleConVO.bill_paymenttype}">
			<input type="submit" value="確認付款" id="sendMessage" >
		</form>
	</div>
	<script>
		function next(obj){
			if(obj.value.length == obj.maxlength){
				do{
					obj = obj.nextSibling;
				} while (obj.nodeName != "INPUT");
				obj.focus();
			}
		}
		
		//////////////////////////////////////////////////////webSocket功能
		
// 		var eleConDone = "/EleConDoneWebSocket";
// 		var host = window.location.host;
// 	    var path = window.location.pathname;
// 	    var webCtx = path.substring(0, path.indexOf('/', 1));
// 	    var endPointURL = "ws://" + window.location.host + webCtx + eleConDone;
		
// 	    var webSocket;
	    
// 		function connect(){
// 			// 建立 websocket 物件
// 			webSocket = new WebSocket(endPointURL);
			
// 			webSocket.onopen = function(event) {
				
// 			};
			
// 			webSocket.onmessage = function(event) {
// 		        var jsonObj = JSON.parse(event.data);
// 		        alert(jsonObj.eleConDoneMsgs);
// 			};

// 			webSocket.onclose = function(event) {
// 			};
// 		}
		
	
		
	</script>
	
	<jsp:include page="/FrontHeaderFooter/Footer.jsp"></jsp:include>

</body>
</html>