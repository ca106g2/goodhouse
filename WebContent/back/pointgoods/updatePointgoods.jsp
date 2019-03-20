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
<title>Update</title>
</head>
<body>
<jsp:include page="/BackHeaderFooter/Header.jsp" />
<div class="container">
	<div class="row">
		<div class="col-4">
			<jsp:include page="/BackHeaderFooter/LeftList.jsp" />
		</div>
		<div class="col-8">
			<form method="post" action="pg.do" name="form1" enctype="multipart/form-data">
				<div class="form-group row">
		          <label for="good_nam" class="col-sm-3 col-form-label">積分商品編號:<font color=red><b>*</b></font></label>
		            <div class="col-sm-9">
		              <%=pointgoodsVO.getGood_id()%>
		          </div>
		        </div>
				<div class="form-group row">
		          <label for="good_nam" class="col-sm-3 col-form-label">積分商品名稱:</label>
		            <div class="col-sm-9">
		              <input type="text" class="form-control" id="good_nam" placeholder="商品名稱"
		                name="good_nam" value="<%=pointgoodsVO.getGood_nam()%>">
		          </div>
		        </div>
				<div class="form-group row">
		          <label for="good_dsc" class="col-sm-3 col-form-label">積分商品描述:</label>
		            <div class="col-sm-9">
		              <input type="text" class="form-control" id="good_dsc" placeholder="商品名稱"
		                name="good_dsc" value="<%=pointgoodsVO.getGood_dsc()%>">
		          </div>
		        </div>
				<div class="form-group row">
		          <label for="good_amo" class="col-sm-3 col-form-label">積分商品存量:</label>
		            <div class="col-sm-9">
		              <input type="text" class="form-control" id="good_amo" placeholder="商品名稱"
		                name="good_amo" value="<%=pointgoodsVO.getGood_amo()%>">
		          </div>
		        </div>
		        <div class="form-group row">
		          <label for="good_pri" class="col-sm-3 col-form-label">積分商品價格:</label>
		            <div class="col-sm-9">
		              <input type="text" class="form-control" id="good_pri" placeholder="商品名稱"
		                name="good_pri" value="<%=pointgoodsVO.getGood_pri()%>">
		          </div>
		        </div>
				<div class="form-group row">
		          <label for="good_sta" class="col-sm-3 col-form-label">積分商品狀態:</label>
		            <div class="col-sm-9">
		              <select name="good_sta" class="form-control" id="good_pri" >
		              <option value="P001" ${(pointgoodsVO.good_sta == "P001")?'selected':'' }>上架
		              <option value="P002" ${(pointgoodsVO.good_sta == "P002")?'selected':'' }>下架
		            </select>
		          </div>
		        </div>
				<div class="form-group row">
		          <label for="img" class="col-sm-3 col-form-label">積分商品圖片:</label>
		            <div class="col-sm-9">
		              <input type="file" class="form-control-file" name="good_pic" id="img" />
		              <div class="col-sm-2">
		              	修改前:
		              </div>
		              <div class="col-sm-4">
		              	<img src="<%=request.getContextPath() %>/PointgoodsServlet?good_id=${pointgoodsVO.good_id}" width="100" height="100">
		              </div>
		              <div class="col-sm-2">
		              	修改後:
		              </div>
		              <div class="col-sm-4">
		              	<img id="preview" src="#" style="display: none;" width="100" height="100">
		              </div>
		          </div>
		        </div>

				<br>
				<input type="hidden" name="action" value="update">
				<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL") %>">
				<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage") %>">
				<input type="hidden" name="good_id" value="<%=pointgoodsVO.getGood_id()%>">
				<div class="form-group row">
		          <div class="col-sm-9">
		            <input type="hidden" name="action" value="insert">
		              <button type="submit" class="btn btn-primary">送出修改</button>
		          </div>
		        </div>
				</form>
		</div>
	</div>
</div>
<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
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