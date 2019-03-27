<%@ page contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.goodhouse.house.model.*" %>
<%@ page import="com.goodhouse.landlord.model.*" %>
<%@ page import="java.util.*, com.goodhouse.member.model.*" %>

<%
	MemVO memVO = (MemVO)session.getAttribute("memVO");

	String[] arrayCity  = new String[] {"台北市","基隆市","新北市","桃園市","新竹市","新竹縣","苗栗縣","台中市","彰化縣","南投縣","雲林縣","嘉義縣","台南市","高雄市","屏東縣","宜蘭縣","花蓮縣","台東縣","澎湖縣","金門縣","連江縣"};
	List<String> listCity = Arrays.asList(arrayCity);  
	pageContext.setAttribute("listCity", listCity);

%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

</head>
<style>
.form-gradient .font-small {
	font-size: 0.8rem;
}

.form-gradient .header {
	border-top-left-radius: .3rem;
	border-top-right-radius: .3rem;
}
div .form-control {
display: initial;
}
</style>
<body>
	<jsp:include page="/FrontHeaderFooter/Header.jsp" />

	<div class="container">
		<table class="table table-striped">
			<tbody>
				<tr>
					<td colspan="1">
						<form class="well form-horizontal" method="post" action="hou.do" name="form1" enctype="multipart/form-data">
							<fieldset>
				
								<div class="form-group">
									<label class="col-md-4 control-label">審核狀態:</label>
									<div class="col-md-8 inputGroupContainer">
										<div class="input-group">
											<span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
												<select  size="1" name="hou_parkspace"class="form-control" id="exampleFormControlSelect5">
														<option value="未審核">未審核</option>
												</select>											
										</div>
									</div>
								</div>
								
	
								<div class="form-group">
									<label class="col-md-4 control-label">房屋名稱:</label>
									<div class="col-md-8 inputGroupContainer">
										<div class="input-group">
											<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
												
<input id="hou_name" placeholder="請輸入房屋名稱" class="form-control" required="true" type="text" name="hou_name" value="${param.hou_name}"/>${errorMsgs.hou_name}

										</div>
									</div>
								</div>
				
								<div class="form-group">
									<label class="col-md-4 control-label">房屋型別:</label>
									<div class="col-md-8 inputGroupContainer">
										<div class="input-group">
											<span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
												<select  size="1" name="hou_type"class="form-control" id="exampleFormControlSelect5">
														<option value="${param.hou_type}"disabled selected>請選擇房屋型別</option>
														<option value="公寓">公寓</option>
														<option value="套房">套房</option>
												</select>											
										</div>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-md-4 control-label">房屋坪數:</label>
										<div class="col-md-8 inputGroupContainer">
											<div class="input-group">
												<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
												
<input id="hou_size" placeholder="請輸入房屋坪數" class="form-control" required="true" type="text" name="hou_size" value="${param.hou_size}"/>${errorMsgs.hou_size}

										</div>
									</div>
								</div>
								
<!-- 								<div class="form-group"> -->
<!-- 									<label class="col-md-4 control-label">房屋狀態:</label> -->
<!-- 									<div class="col-md-8 inputGroupContainer"> -->
<!-- 										<div class="input-group"> -->
<!-- 											<span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span> -->
<!-- 												<select  size="1" name="hou_property"class="form-control" id="exampleFormControlSelect5"> -->
<%-- 														<option value="${param.hou_property}"disabled selected>請選擇</option> --%>
<!-- 														<option value="已出租">已出租</option> -->
<!-- 														<option value="未出租">未出租</option> -->
<!-- 												</select>											 -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 								</div> -->
								
								<div class="form-group">
									<label class="col-md-4 control-label">是否可炊煮:</label>
									<div class="col-md-8 inputGroupContainer">
										<div class="input-group">
											<span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
												<select  size="1" name="hou_cook"class="form-control" id="exampleFormControlSelect5">
														<option value="${param.hou_cook}"disabled selected>請選擇</option>
														<option value="可">可</option>
														<option value="不可">不可</option>
												</select>											
										</div>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-md-4 control-label">房屋管理費:</label>
										<div class="col-md-8 inputGroupContainer">
											<div class="input-group">
												<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
												
<input id="hou_m" placeholder="請輸入房屋管理費" class="form-control" required="true" type="text" name="hou_managefee" value="${param.hou_managefee}"/>${errorMsgs.hou_managefee}
										</div>
									</div>
								</div>


								<div class="form-group">
									<label class="col-md-4 control-label">房屋租金:</label>
										<div class="col-md-8 inputGroupContainer">
											<div class="input-group">
												<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
												
<input id="hou_r" placeholder="請輸入房屋租金" class="form-control" required="true"  type="text" name="hou_rent_str" value="${param.hou_rent_str}"/>${errorMsgs.hou_rent_str}

										</div>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-md-4 control-label">請輸入姓名:</label>
										<div class="col-md-8 inputGroupContainer">
											<div class="input-group">
												<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
												
<%-- <input placeholder="請輸入姓名" class="form-control" required="true" value="" type="text" name="lan_id" value="<%=memVO.getMem_name()%>"/>${errorMsgs.lan_id} --%>
											<p><%=memVO.getMem_name()%></p>

										</div>
									</div>
								</div>	
					<div class="form-group">
						<label class="col-md-4 control-label" for="name">地址</label>
							<div class="col-md-9">
								<select id="twCityName" name="twCityName" class="form-control" style="width:24%;">
									<option value="0" >--請選擇縣市--</option>
								    	<c:forEach var="city" items="${listCity}">
											<option value="${city}"> ${city}</option>
								    	</c:forEach>
								</select>
								<select id="CityAreaName" name="CityAreaName" class="form-control" style="width:24%;">
									<option value="0" >--請選擇區域--</option>
							    </select>
								<select id="AreaRoadName" name="AreaRoadName" class="form-control" style="width:24%;">
									<option value="0" >--請選擇路名--</option>
							  	</select>
							  	<input type="text" placeholder="請輸入門牌號碼" id="num" name="num" class="form-control" style="width:25%;">
							 </div>
						</div>
						<div class="form-group">
							<font color="red"><b>${errorMsgs.twCityName}</b></font>
							<font color="red"><b>${errorMsgs.CityAreaName}</b></font>
							<font color="red"><b>${errorMsgs.AreaRoadName}</b></font>
							<font color="red"><b>${errorMsgs.num}</b></font>
						</div>					
<!-- 								<div class="form-group"> -->
<!-- 									<label class="col-md-4 control-label">房屋地址:</label> -->
<!-- 									<div class="col-md-8 inputGroupContainer"> -->
<!-- 										<div class="input-group"> -->
<!-- 											<span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span> -->									
<%-- <input placeholder="請輸入房屋地址" class="form-control" required="true" type="text" name="hou_address" value="${param.hou_address}"/>${errorMsgs.hou_address} --%>
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 								</div>								 -->
								
								
<!-- 							<div class="form-group">
									<label class="col-md-4 control-label">房屋地址:</label>
									<div class="col-md-8 inputGroupContainer">
										<div class="input-group">
											<span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
											
<input placeholder="請輸入房屋地址" class="form-control" required="true" value="" type="text">

										</div>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-md-4 control-label">區域:</label>
									<div class="col-md-8 inputGroupContainer">
										<div class="input-group">
											<span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
											
<input id="city" name="city" placeholder="地區" class="form-control" required="true" value="" type="text">

										</div>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-md-4 control-label">城市:</label>
									<div class="col-md-8 inputGroupContainer">
										<div class="input-group">
											<span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
											
<input id="state" name="state" placeholder="城市" class="form-control" required="true" value="" type="text">

										</div>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-md-4 control-label">郵遞區號:</label>
									<div class="col-md-8 inputGroupContainer">
										<div class="input-group">
											<span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
											
<input id="postcode" name="postcode" placeholder="郵遞區號" class="form-control" required="true" value="" type="text">

										</div>
									</div>
								</div>-->


								<div class="form-group">
									<label class="col-md-4 control-label">房屋備註:</label>
									<div class="col-md-8 inputGroupContainer">
										<div class="input-group">
											<span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
											
<input id="hou_n" placeholder="房屋備註" class="form-control" required="true" type="text" name="hou_note" value="${param.hou_note}"/>${errorMsgs.hou_note}

										</div>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-md-4 control-label">房屋圖片一:</label>
									<div class="col-md-8 inputGroupContainer">
										<div class="input-group">
											<span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
											
<input id="d1"  value="" type="file" name="hou_f_picture" value="${param.hou_f_picture}"/>
<img style="height: 700;width: 800px; display:none;" src="" id="picture1" >
										</div>
									</div>
								</div>	
								
								
								
								
								<div class="form-group">
									<label class="col-md-4 control-label">房屋圖片二:</label>
									<div class="col-md-8 inputGroupContainer">
										<div class="input-group">
											<span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
											
<input id="d2" value="" type="file" name="hou_s_picture" value="${param.hou_s_picture}"/>
<img style="height: 700;width: 800px; display:none;" src="" id="picture2" >
										</div>
									</div>
								</div>	
								
								
								
								<div class="form-group">
									<label class="col-md-4 control-label">房屋圖片三:</label>
									<div class="col-md-8 inputGroupContainer">
										<div class="input-group">
											<span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
											
<input id="d3"  value="" type="file" name="hou_t_picture" value="${param.hou_t_picture}"/>
<img style="height: 700;width: 800px; display:none;" src="" id="picture3">
										</div>
									</div>
								</div>						
								<%
									LanService lanSvc = new  LanService();
								%>	
 							<input type="hidden" name="hou_property" value="未出租">
							<input type="hidden" name="lan_id" value="<%=lanSvc.getOneLanByMemId(memVO.getMem_id()).getLan_id()%>">	
							<input type="hidden" name="action" value="frontinsert">								
							<input type="submit" value="送出新增" class="btn btn-primary">
							<input type="hidden" name="action" value="getOne_For_Display">
							</fieldset>
		

						</form>
							<button class="btn btn-primary" onclick="FirstHouse()">神奇小按鈕</button>	
							<br>
							<br>
					</td>
				</tr>
			</tbody>
		</table>
	</div>

	<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
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


function FirstHouse(){
	$("#hou_name").val("一般套房.套房短期出租");
	$("#hou_size").val("15坪");
	$("#hou_m").val("666");
	$("#hou_r").val("7777");
	$("#hou_n").val("房源描述一.面向淡水河，視野遼闊。二.離捷運站近，附近學校，市場，公園，生活機能方便。三.新交屋，所有家具都是新的，住起來就是舒服。四.水電費依帳單繳納。");	
}
</script>
