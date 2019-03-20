<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="accordion" id="accordionExample">
  <div class="card">
    <div class="card-header" id="headingOne">
      <h2 class="mb-0">
        <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
          	會員
        </button>
      </h2>
    </div>

    <div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordionExample">
        <div class="list-group">
<!-- 		  <button type="button" class="list-group-item list-group-item-action">我的最愛積分商品</button> -->
<!-- 		  <button type="button" class="list-group-item list-group-item-action">積分明細紀錄</button> -->
<!-- 		  <button type="button" class="list-group-item list-group-item-action">積分商品訂單查看</button> -->
		  <input type="button" class="list-group-item list-group-item-action" value="我的最愛積分商品" onclick="location.href='<%=request.getContextPath()%>/front/pointgoods/good_favorite_check.jsp'">
		  <input type="button" class="list-group-item list-group-item-action" value="積分明細紀錄" onclick="location.href='<%=request.getContextPath()%>/front/pointgoods/good_record_check.jsp'">
		  <input type="button" class="list-group-item list-group-item-action" value="積分商品訂單查看" onclick="location.href='<%=request.getContextPath()%>/front/pointgoods/good_order_check.jsp'">
		</div>
    </div>
  </div>
  <div class="card">
    <div class="card-header" id="headingTwo">
      <h2 class="mb-0">
      	<input type="button" class="btn btn-link collapsed" value="瀏覽積分商城" onclick="location.href='<%=request.getContextPath()%>/front/pointgoods/listAllPointgoods.jsp'" data-toggle="collapse" data-target="#collapseSecond" aria-expanded="false" aria-controls="collapseSecond">
      </h2>
    </div>
  </div>
  <div class="card">
    <div class="card-header" id="headingThree">
      <h2 class="mb-0">
        <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
          	討論區
        </button>
      </h2>
    </div>
    <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordionExample">
      <div class="card-body">
        content3
      </div>
    </div>
  </div>
</div>