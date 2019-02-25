<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.goodhouse.pointgoods.model.*" %>

<%
	PointgoodsVO pointgoodsVO = (PointgoodsVO) request.getAttribute("pointgoodsVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add a new Product</title>

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
<script type="text/javascript" src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<body bgcolor='white'>
<table id="table-1">
	<tr>
		<td>
			<h3>新增積分商品</h3>
		</td>
		<td>
			<h4><a href="select_page.jsp"><img src="images/test1.jpg" width="100" height="100" border="0">回首頁</a></h4>
		</td>
	</tr>
</table>
<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<form method="post" action="pg.do" name="form1" enctype="multipart/form-data">

<table>
	<tr>
		<td>積分商品名稱:</td>
		<td><input type="TEXT" name="good_nam" size="45" 
			 value="<%= (pointgoodsVO==null)? "Rick" : pointgoodsVO.getGood_nam()%>" /></td>
	</tr>
	<tr>
		<td>積分商品描述:</td>
		<td><input type="TEXT" name="good_dsc" size="45"
			 value="<%= (pointgoodsVO==null)? "帥哥" : pointgoodsVO.getGood_dsc()%>" /></td>
	</tr>
	<tr>
		<td>積分商品存量:</td>
		<td><input type="TEXT" name="good_amo" size="45"
			 value="<%= (pointgoodsVO==null)? "100" : pointgoodsVO.getGood_amo()%>" /></td>
	</tr>
	<tr>
		<td>積分商品價格:</td>
		<td><input type="TEXT" name="good_pri" size="45"
			 value="<%= (pointgoodsVO==null)? "10000" : pointgoodsVO.getGood_pri()%>" /></td>
	</tr>
	<tr>
		<td>積分商品狀態:</td>
		<td>
			<select name="good_sta">
				<option value="P001">上架
				<option value="P002">下架
			</select>
		</td>
	</tr>
	<tr>
		<td>積分商品圖片:</td>
		<td><input type="file" name="good_pic" id="img" />
			 <img id="preview" src="#" style="display: none;" width="100" height="100">
		</td>
	</tr>

</table>
<br>

<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增">
</form>

<script type="text/javascript">
	
	$("#img").click(function(){
		$("#preview").attr('src',  '#');
		$("#preview").hide();
	});
	
	$("#img").change(function(){
		readURL(this,"#preview");
		$("#preview").show();
	});
	
	function readURL(input,imgid){		
	  	if(input.files && input.files[0]){
	    var reader = new FileReader();
	    reader.onload = function (e) {
	       $(imgid).attr('src', e.target.result);
	    }
	    reader.readAsDataURL(input.files[0]);
	  }
	}
</script>
</body>
</html>