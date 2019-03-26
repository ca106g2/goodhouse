<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.goodhouse.house.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  HouseVO houVO = (HouseVO) request.getAttribute("houVO");
%>

<html>
<head>
<title></title>


<style>
textarea {
		resize:none;
}
</style>

</head>
<body bgcolor='white'>
<jsp:include page="/BackHeaderFooter/Header.jsp" />

<br>
<br>

<div class="container-fluid">

	<div class="row ">
		
		<div class="col-4">
			<div class="card">
				<div class="card-head">
				圖片一
				</div>
				<div class="card-body">
				<img src="<%=request.getContextPath() %>/HouseServlet?hou_id=${houVO.hou_id}&photo=1"style="height: 500;width: 600px;">
				</div>
			</div>
		</div>
		<div class="col-4">
			<div class="card">
				<div class="card-head">
				圖片二
				</div>
				<div class="card-body">
				<img src="<%=request.getContextPath() %>/HouseServlet?hou_id=${houVO.hou_id}&photo=2"style="height: 500;width: 600px;">
				</div>			
			</div>
		</div>
		<div class="col-4">
			<div class="card">
				<div class="card-head">
				圖片三
				</div>
				<div class="card-body">
				<img src="<%=request.getContextPath() %>/HouseServlet?hou_id=${houVO.hou_id}&photo=3"style="height: 500;width: 600px;">
				</div>			
			</div>
		</div>
	</div>
			<a href="<%=request.getContextPath()%>/back/house/listAllHouse.jsp">
				<img src="<%=request.getContextPath()%>/images/back.png" width="100	" height="100 !important">
			</a>
	<div class="row justify-content-center">
		<div class="card">
		<div class="card-body">
		<div class="table-responsive">
		
		<table class="table table-bordered table-hover mb-0 text-nowrap text-center">
			<tbody>
			<tr>
				<th scope="row">房屋名稱 :</th> 
				<td><%=houVO.getHou_name()%></td>
			</tr>	
			<tr>	
				<th scope="row">房屋型別 : </th>
				<td><%=houVO.getHou_type()%></td>
			</tr>	
			<tr>	
				<th scope="row">房屋坪數 : </th>
				<td><%=houVO.getHou_size()%></td>
			</tr>	
			<tr>	
				<th scope="row">房屋狀態 : </th>
				<td><%=houVO.getHou_property()%></td>
			</tr>	
			<tr>	
				<th scope="row">房屋審核 : </th>
				<td><%=houVO.getHou_parkspace()%></td>
			</tr>	
			<tr>	
				<th scope="row">是否可開火 :</th>
				<td><%=houVO.getHou_cook()%></td>
			</tr>	
			<tr>	
				<th scope="row">是否有管理費 : </th>
				<td><%=houVO.getHou_managefee()%></td>
			</tr>	
			<tr>	
				<th scope="row">房屋地址 : </th>
				<td><%=houVO.getHou_address()%></td>
			</tr>	
			<tr>	
				<th scope="row">房屋租金 :</th>
				<td><%=houVO.getHou_rent()%></td>
			</tr>	
			<tr>	
				<th scope="row">備註 : </th>
				<td>
				<textarea disabled rows="20" cols="100" style="resize:none;" >
				<%=houVO.getHou_note()%></textarea></td>
			</tr>		
				
				</tbody>
			</table>
		
		</div>
		</div>
		</div>
	
	</div>


</div>


<jsp:include page="/BackHeaderFooter/Footer.jsp" />	
</body>
</html>