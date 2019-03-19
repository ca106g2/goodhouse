<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="accordion" id="accordionExample">
  <div class="card">
    <div class="card-header" id="headingOne">
      <h2 class="mb-0">
        <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
          	積分商城管理
        </button>
      </h2>
    </div>

    <div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordionExample">
        <div class="list-group">
		  <input type="button" class="list-group-item list-group-item-action" value="積分商品管理" onclick="location.href='/RentHouse/back/pointgoods/listAllPointgoods.jsp'">
		  <input type="button" class="list-group-item list-group-item-action" value="新增積分商品" onclick="location.href='/RentHouse/back/pointgoods/addPointgoods.jsp'">
		  <input type="button" class="list-group-item list-group-item-action" value="訂單管理" onclick="location.href='/RentHouse/back/pointgoods/good_ordManage.jsp'">
		</div>
    </div>
  </div>
  <div class="card">
    <div class="card-header" id="headingTwo">
      <h2 class="mb-0">
      	<input type="button" class="btn btn-link collapsed" value="瀏覽積分商城" onclick="location.href='/RentHouse/front/pointgoods/listAllPointgoods.jsp'" data-toggle="collapse" data-target="#collapseSecond" aria-expanded="false" aria-controls="collapseSecond">
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