<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.* , com.goodhouse.pointgoods.model.*" %>
<%@ page import="com.goodhouse.member.model.*" %>
<%
	List<PointgoodsVO> buylist = (List<PointgoodsVO>) session.getAttribute("shoppingcart");
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	String amount = (String) session.getAttribute("amount");
	
	String[] arrayCity  = new String[] {"台北市","基隆市","新北市","桃園市","新竹市","新竹縣","苗栗縣","台中市","彰化縣","南投縣","雲林縣","嘉義縣","台南市","高雄市","屏東縣","宜蘭縣","花蓮縣","台東縣","澎湖縣","金門縣","連江縣"};
 	List<String> listCity = Arrays.asList(arrayCity);  
 	pageContext.setAttribute("listCity", listCity);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order</title>
</head>
<body>
<jsp:include page="/FrontHeaderFooter/Header.jsp" />
<div class="container">
	<div class="row">
		<div class="col-4">
			<jsp:include page="/FrontHeaderFooter/LeftList.jsp" />
		</div>
		<div class="col-8">
			<form action="goc.do" method="post">
				<div class="form-group">
					<label for="name">收件人</label>
    				<input type="text" class="form-control" name="name" placeholder="輸入姓名" 
    					value="${param.name}" >
    				<font color="red"><b>${errorMsgs.good_ord_nam}</b></font>
				</div>
				<div class="form-group">
					<label for="name">電話</label>
    				<input type="text" class="form-control" name="phone" placeholder="輸入電話"
    					value="${param.phone}" >
    				<font color="red"><b>${errorMsgs.phone}</b></font>    				
				</div>
				<div class="form-group">
					<label for="name">信箱</label>
    				<input type="text" class="form-control" name="email" placeholder="輸入信箱"
    					value="${param.email}">
    				<font color="red"><b>${errorMsgs.email}</b></font>    				
				</div>
				<div class="form-group">
					<div class="dropdown">
					  <select id="twCityName" name="twCityName">
							  <option value="0" >--請選擇縣市--</option>
							  <c:forEach var="city" items="${listCity}">
								<option value="${city}"> ${city}</option>
							  </c:forEach>
					  </select>
					  <select id="CityAreaName" name="CityAreaName">
							  <option value="0" >--請選擇區域--</option>
					  </select>
					  <select id="AreaRoadName" name="AreaRoadName" >
							  <option value="0" >--請選擇路名--</option>
					  </select>
					  <input type="text" placeholder="請輸入門牌號碼" id="num" name="num">   			    
					</div>
				</div>
				<div class="form-group">
					<font color="red"><b>${errorMsgs.twCityName}</b></font>
					<font color="red"><b>${errorMsgs.CityAreaName}</b></font>
					<font color="red"><b>${errorMsgs.AreaRoadName}</b></font>
					<font color="red"><b>${errorMsgs.num}</b></font>
				</div>
				<div class="form-group">
					<label>總價</label>
    				<%=amount %>
				</div>
				<input type="hidden" name="action" value="order">
				<input type="submit" value="送出 " class="btn btn-primary">
			</form>
		</div>
<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
<script> 

$(document).ready(function(){
	
	$("#twCityName").change(function(){
		$.ajax({
			 type: "POST",
			 url: "<%=request.getContextPath()%>/Json2Read",
			 data: {"action":"twCityName",
				 	"twCityName":$('#twCityName option:selected').val()},
			 dataType: "json",
			 success: function(result){
				 $("#CityAreaName").empty();
				
				 $("#CityAreaName").append("<option >--請選擇區域--</option>")
				 for(var i=0; i<result.length; i++){
				 	$("#CityAreaName").append('<option value="'+result[i]+'">'+result[i]+'</option>');
				 }
			 },
	         error: function(){
	        	 alert("AJAX-grade發生錯誤囉!")
	        	 }
	    });
	});
	
	$("#CityAreaName").change(function(){
		$.ajax({
			 type: "POST",
			 url: "<%=request.getContextPath()%>/Json2Read",
			 data: {"action":"CityAreaName",
				 	"twCityName":$('#twCityName option:selected').val(),
				 	"CityAreaName":$('#CityAreaName option:selected').val()},
			 dataType: "json",
			 success: function(result){
				 $("#AreaRoadName").empty();
				 $("#AreaRoadName").append("<option >--請選擇區域--</option>")
				 for(var i=0; i<result.length; i++){
				 	$("#AreaRoadName").append('<option value="'+result[i]+'">'+result[i]+'</option>');
				 }
			 },
	         error: function(){
	        	 alert("AJAX-grade發生錯誤囉!")
	        	 }
	    });
	});
})


</script>
</body>
</html>