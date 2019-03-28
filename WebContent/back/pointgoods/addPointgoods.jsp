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
</head>
<body>
<jsp:include page="/BackHeaderFooter/Header.jsp" />
<div class="container">
  <div class="row">
    <div class="col-12">
    <div class="card">
		<div class="card-header">
			<h4>新增商品</h4>
		</div>
      <form method="post" action="pg.do" name="form1" enctype="multipart/form-data">
        <div class="form-group row">
          <label for="good_nam" class="col-sm-3 col-form-label">積分商品名稱:</label>
            <div class="col-sm-9">
              <input type="text" class="form-control" id="good_nam" placeholder="商品名稱"
                name="good_nam" value="<%= (pointgoodsVO==null)? "馬桶吸吸" : pointgoodsVO.getGood_nam()%>">
          </div>
        </div>
        <div class="form-group row">
          <label for="good_dsc" class="col-sm-3 col-form-label">積分商品描述:</label>
            <div class="col-sm-9">
              <input type="text" class="form-control" id="good_dsc" placeholder="商品名稱"
                name="good_dsc" value="<%= (pointgoodsVO==null)? "強力吸力，一吸上癮" : pointgoodsVO.getGood_dsc()%>">
          </div>
        </div>
        <div class="form-group row">
          <label for="good_amo" class="col-sm-3 col-form-label">積分商品存量:</label>
            <div class="col-sm-9">
              <input type="text" class="form-control" id="good_amo" placeholder="商品名稱"
                name="good_amo" value="<%= (pointgoodsVO==null)? "100" : pointgoodsVO.getGood_amo()%>">
          </div>
        </div>
        <div class="form-group row">
          <label for="good_pri" class="col-sm-3 col-form-label">積分商品價格:</label>
            <div class="col-sm-9">
              <input type="text" class="form-control" id="good_pri" placeholder="商品名稱"
                name="good_pri" value="<%= (pointgoodsVO==null)? "20000" : pointgoodsVO.getGood_pri()%>">
          </div>
        </div>
        <div class="form-group row">
          <label for="good_sta" class="col-sm-3 col-form-label">積分商品狀態:</label>
            <div class="col-sm-9">
              <select name="good_sta" class="form-control" id="good_pri" >
              <option value="P001">上架
              <option value="P002">下架
            </select>
          </div>
        </div>
        <div class="form-group row">
          <label for="img" class="col-sm-3 col-form-label">積分商品圖片:</label>
            <div class="col-sm-9">
              <input type="file" class="form-control-file" name="good_pic" id="img" />
            <img id="preview" src="#" style="display: none;" width="100" height="100">
          </div>
        </div>
        <br>
        <div class="form-group row">
          <div class="col-sm-3">
          </div>
          <div class="col-sm-9">
            <input type="hidden" name="action" value="insert">
              <button type="submit" class="btn btn-outline-success" style="float: right;">送出新增</button>
          </div>
        </div>
      </form>
      </div>
    </div>
  </div>
</div>
<jsp:include page="/BackHeaderFooter/Footer.jsp" /> 


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