<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.house.model.*"%>


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>房屋新增-addHouse.jsp</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<style>
  table#table-1 {
    width: 450px;
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
		<h3>新增-addHouse.jsp</h3></td><td style="test-align: center">
		<h4><a href="select_page.jsp">回首頁</a></h4>
		</td></tr>
</table>

<h3>資料新增</h3>

<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message.value}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="hou.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>房屋名稱</td>
		<td><input type="text" name="hou_name" size="45"
			 value="${param.hou_name}"/></td><td>${errorMsgs.hou_name}</td>
	</tr>
		<tr>
		<td>房屋型別</td>
		<td><input type="text" name="hou_type" size="45"
			 value="${param.hou_type}"/></td><td>${errorMsgs.hou_type}</td>
	</tr>
		<tr>
		<td>房屋坪數</td>
		<td><input type="text" name="hou_size" size="45"
			 value="${param.hou_size}"/></td><td>${errorMsgs.hou_size}</td>
	</tr>
		<tr>
		<td>產權證名</td>
		<td><input type="text" name="hou_property" size="45"
			 value="${param.hou_property}"/></td><td>${errorMsgs.hou_property}</td>
	</tr>
		<tr>
		<td>是否含車位</td>
		<td><input type="text" name="hou_parkspace" size="45"
			 value="${param.hou_parkspace}"/></td><td>${errorMsgs.hou_parkspace}</td>
	</tr>
		<tr>
		<td>是否可開火</td>
		<td><input type="text" name="hou_cook" size="45"
			 value="${param.hou_cook}"/></td><td>${errorMsgs.hou_cook}</td>
	</tr>
		<tr>
		<td>房屋管理費用</td>
		<td><input type="text" name="hou_managefee" size="45"
			 value="${param.hou_managefee}"/></td><td>${errorMsgs.hou_managefee}</td>
	</tr>
		<tr>
		<td>房屋地址</td>
		<td><input type="text" name="hou_address" size="45"
			 value="${param.hou_address}"/></td><td>${errorMsgs.hou_address}</td>
	</tr>
		<tr>
		<td>房東編號</td>
		<td><input type="text" name="lan_id" size="45"value="${param.lan_id}"/></td>
	</tr>
		<tr>
		<td>房屋租金</td>
		<td><input type="text" name="hou_rent" size="45"
			 value="${param.hou_rent}"/></td><td>${errorMsgs.hou_rent}</td>
	</tr>
		<tr>
		<td>備註</td>
		<td><input type="text" name="hou_note" size="45"value="${param.hou_note}"/></td>
	</tr>
	<tr>
		<td>房屋圖片一</td>
		<td><input type="file" name="hou_f_picture"value="${param.hou_f_picture}"
		id="d1"/><img src=""  id="picture1"/></td>
	</tr>
		<tr>
		<td>房屋圖片二</td>
		<td><input type="file" name="hou_s_picture"value="${param.hou_s_picture}"
		id="d2"/><img src=""  id="picture2"/></td>
	</tr>
		<tr>
		<td>房屋圖片三</td>
		<td><input type="file" name="hou_t_picture"value="${param.hou_t_picture}"
		id="d3"/><img src="" id="picture3"/></td>
	</tr>
	
	
</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增">
</FORM>

</body>
</html>
<!-- 以下為秀圖片 -->
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