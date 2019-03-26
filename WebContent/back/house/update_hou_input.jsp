<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.house.model.*"%>
<jsp:useBean id="lanSvc" scope="page" class="com.goodhouse.landlord.model.LanService"/>
<jsp:useBean id="memSvc" scope="page" class="com.goodhouse.member.model.MemService"/>

<%
  HouseVO houVO = (HouseVO) request.getAttribute("houVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<title></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>



<style>
  h3{
  	margin-left:auto; 
    margin-right:auto;
  }
  table {
	width: 1500px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
	margin-left:auto; 
    margin-right:auto;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>
<jsp:include page="/BackHeaderFooter/Header.jsp" />


<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<br>
<br>
<FORM METHOD="post" ACTION="hou.do" name="form1" enctype="multipart/form-data">
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
	<div class="row justify-content-center">
		<div class="card">
		<div class="card-body">
		<div class="table-responsive">
		
		<table class="table table-bordered table-hover mb-0 text-nowrap text-center">
			<tbody>
			
		<tr>
		<th>房屋審核 :</th>
		<td>												
			<select  size="1" name="hou_parkspace"class="form-control" id="exampleFormControlSelect5">	
				<option value="已審核">已審核</option>
			</select></td>
			
			</tr>
		<tr><th scope="row">房屋名稱 : </th><td><%=houVO.getHou_name()%></td></tr>
		<tr><th scope="row">房屋型別 : </th><td><%=houVO.getHou_type()%></td></tr>
		<tr><th scope="row">房屋坪數 : </th><td><%=houVO.getHou_size()%></td></tr>
		<tr><th scope="row">房屋狀態 :</th> <td><%=houVO.getHou_property()%></td></tr>
		<tr><th scope="row">是否可開火 :</th> <td><%=houVO.getHou_cook()%></td></tr>
		<tr><th scope="row">是否有管理費 :</th> <td><%=houVO.getHou_managefee()%></td></tr>
		<tr><th scope="row">房屋地址 :</th> <td><%=houVO.getHou_address()%></td></tr>
		<tr><th scope="row">房屋租金 : </th><td><%=houVO.getHou_rent()%></td></tr>
		<tr><th scope="row">備註 : </th><td>
				<textarea disabled rows="20" cols="100" style="resize:none;" >
				<%=houVO.getHou_note()%></textarea></td></tr>

		<tr><th scope="row"></th> <td>
		<input type="hidden" name="action" value="update">
		<input type="hidden" name="mem_id" value="${memSvc.getOneMem(lanSvc.getOneLan(houVO.lan_id).mem_id).mem_id}">
		<input type="hidden" name="hou_id" value="<%=houVO.getHou_id()%>">
		<input type="hidden" name="hou_name" value="<%=houVO.getHou_name()%>">
		<input type="hidden" name="hou_type" value="<%=houVO.getHou_type()%>">
		<input type="hidden" name="hou_size" value="<%=houVO.getHou_size()%>">
		<input type="hidden" name="hou_property" value="<%=houVO.getHou_property()%>">
		<input type="hidden" name="hou_cook" value="<%=houVO.getHou_cook()%>">
		<input type="hidden" name="hou_managefee" value="<%=houVO.getHou_managefee()%>">
		<input type="hidden" name="hou_address" value="<%=houVO.getHou_address()%>">
		<input type="hidden" name="hou_rent" value="<%=houVO.getHou_rent()%>">
		<input type="hidden" name="hou_note" value="<%=houVO.getHou_note()%>">
		<input type="hidden" name="hou_f_picture">
		<input type="hidden" name="hou_s_picture">
		<input type="hidden" name="hou_t_picture">
		<input type="submit"  class="btn btn-outline-success" value="送出修改" >	
		</td></tr>
	
				</tbody>
			</table>		
		</div>
		</div>
		</div>	
	</div>
</div>
</FORM>

<jsp:include page="/BackHeaderFooter/Footer.jsp" />	
</body>
</html>

<script>
$("#d1").change(function(){
	readURL(this,"#picture1");
	   $("#picture1").show();
	     });
$("#d2").change(function(){
	readURL(this,"#picture2");
	   $("#picture2").show();
	     });
$("#d3").change(function(){
	readURL(this,"#picture3");
	   $("#picture3").show();
	     });
	     
	function readURL(input,imgid){
	if(input.files && input.files[0]){
	var reader = new FileReader();
	reader.onload=function(e){
	$(imgid).attr('src',e.target.result);
	}
	reader.readAsDataURL(input.files[0]);
	}
	}
</script>