<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.house.model.*"%>

<%
  HouseVO houVO = (HouseVO) request.getAttribute("houVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>

		 <h4><a href="select_page.jsp">回首頁</a></h4>
	</td></tr>
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

<FORM METHOD="post" ACTION="hou.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>房屋編號<font color=red><b>*</b></font></td>
		<td><%=houVO.getHou_id()%></td>
	</tr>
	<tr>
		<td>房屋名稱:</td>
		<td><input type="TEXT" name="hou_name" size="45" value="<%=houVO.getHou_name()%>" /></td>
	</tr>
	
		<tr>
		<td>房屋型別</td>
		<td><input type="TEXT" name="hou_type" size="45" value="<%=houVO.getHou_type()%>" /></td>
	</tr>
		<tr>
		<td>房屋坪數</td>
		<td><input type="TEXT" name="hou_size" size="45" value="<%=houVO.getHou_size()%>" /></td>
	</tr>
		<tr>
		<td>產權證名</td>
		<td><input type="TEXT" name="hou_property" size="45" value="<%=houVO.getHou_property()%>" /></td>
	</tr>
		<tr>
		<td>是否含車位</td>
		<td><input type="TEXT" name="hou_parkspace" size="45" value="<%=houVO.getHou_parkspace()%>" /></td>
	</tr>
		<tr>
		<td>是否可開火</td>
		<td><input type="TEXT" name="hou_cook" size="45" value="<%=houVO.getHou_cook()%>" /></td>
	</tr>
		<tr>
		<td>房屋管理費用</td>
		<td><input type="TEXT" name="hou_managefee" size="45" value="<%=houVO.getHou_managefee()%>" /></td>
	</tr>
		<tr>
		<td>房屋地址</td>
		<td><input type="TEXT" name="hou_address" size="45" value="<%=houVO.getHou_address()%>" /></td>	
	</tr>
		<tr>
		<td>房屋租金</td>
		<td><input type="TEXT" name="hou_rent" size="45" value="<%=houVO.getHou_rent()%>" /></td>	
	</tr>
		<tr>
		<td>備註</td>
		<td><input type="TEXT" name="hou_note" size="45" value="<%=houVO.getHou_note()%>" /></td>	
	</tr>

	<tr>
		<td>房屋圖片一</td>
		<td><input type="file" name="hou_f_picture"
		id="d1"/><img src="<%=request.getContextPath() %>/HouseServlet?hou_id=${houVO.hou_id}&photo=1"  id="picture1"/></td>
	</tr>
		<tr>
		<td>房屋圖片二</td>
		<td><input type="file" name="hou_s_picture"
		id="d2"/><img src="<%=request.getContextPath() %>/HouseServlet?hou_id=${houVO.hou_id}&photo=2"  id="picture2"/></td>
	</tr>
		<tr>
		<td>房屋圖片三</td>
		<td><input type="file" name="hou_t_picture"
		id="d3"/><img src="<%=request.getContextPath() %>/HouseServlet?hou_id=${houVO.hou_id}&photo=3" id="picture3"/></td>
	</tr>
 
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="hou_id" value="<%=houVO.getHou_id()%>">
<input type="submit" value="送出修改">
</FORM>


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