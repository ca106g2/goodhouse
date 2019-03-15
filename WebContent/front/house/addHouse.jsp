<%@ page contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.goodhouse.house.model.*" %>
<%@ page import="com.goodhouse.landlord.model.*" %>
<%@ page import="com.goodhouse.member.model.*" %>

<%
	MemVO memVO = (MemVO)session.getAttribute("mVO");
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
												
<input placeholder="請輸入房屋名稱" class="form-control" required="true" type="text" name="hou_name" value=""/>${errorMsgs.hou_name}

										</div>
									</div>
								</div>
				
								<div class="form-group">
									<label class="col-md-4 control-label">房屋型別:</label>
									<div class="col-md-8 inputGroupContainer">
										<div class="input-group">
											<span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
												<select  size="1" name="hou_type"class="form-control" id="exampleFormControlSelect5">
														<option value=""disabled selected>請選擇房屋型別</option>
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
												
<input placeholder="請輸入房屋坪數" class="form-control" required="true" type="text" name="hou_size" value="${param.hou_size}"/>${errorMsgs.hou_size}

										</div>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-md-4 control-label">房屋狀態:</label>
									<div class="col-md-8 inputGroupContainer">
										<div class="input-group">
											<span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
												<select  size="1" name="hou_property"class="form-control" id="exampleFormControlSelect5">
														<option value=""disabled selected>請選擇</option>
														<option value="以出租">已出租</option>
														<option value="未出租">未出租</option>
												</select>											
										</div>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-md-4 control-label">是否可炊煮:</label>
									<div class="col-md-8 inputGroupContainer">
										<div class="input-group">
											<span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
												<select  size="1" name="hou_cook"class="form-control" id="exampleFormControlSelect5">
														<option value=""disabled selected>請選擇</option>
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
												
<input placeholder="請輸入房屋管理費" class="form-control" required="true" value="" type="text" name="hou_managefee" value="${param.hou_managefee}"/>${errorMsgs.hou_managefee}

										</div>
									</div>
								</div>


								<div class="form-group">
									<label class="col-md-4 control-label">房屋租金:</label>
										<div class="col-md-8 inputGroupContainer">
											<div class="input-group">
												<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
												
<input placeholder="請輸入房屋租金" class="form-control" required="true"  type="text" name="hou_rent_str" value="${param.hou_rent_str}"/>${errorMsgs.hou_rent_str}

										</div>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-md-4 control-label">請輸入姓名:</label>
										<div class="col-md-8 inputGroupContainer">
											<div class="input-group">
												<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
												
<%-- <input placeholder="請輸入姓名" class="form-control" required="true" value="" type="text" name="lan_id" value="<%=memVO.getMem_name()%>"/>${errorMsgs.lan_id} --%>
											<p/><%=memVO.getMem_name()%>

										</div>
									</div>
								</div>								
								
								
								<div class="form-group">
									<label class="col-md-4 control-label">房屋地址:</label>
									<div class="col-md-8 inputGroupContainer">
										<div class="input-group">
											<span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
											
<input placeholder="請輸入房屋地址" class="form-control" required="true" type="text" name="hou_address" value="${param.hou_address}"/>${errorMsgs.hou_address}


										</div>
									</div>
								</div>								
								
								
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
											
<input placeholder="房屋備註" class="form-control" required="true" value="" type="text" name="hou_note" value="${param.hou_note}"/>${errorMsgs.hou_note}

										</div>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-md-4 control-label">房屋圖片一:</label>
									<div class="col-md-8 inputGroupContainer">
										<div class="input-group">
											<span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
											
<input id="d1"  value="" type="file" name="hou_f_picture" value="${param.hou_f_picture}"/>
<img src="" id="picture1" >
										</div>
									</div>
								</div>	
								
								
								
								
								<div class="form-group">
									<label class="col-md-4 control-label">房屋圖片二:</label>
									<div class="col-md-8 inputGroupContainer">
										<div class="input-group">
											<span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
											
<input id="d2" value="" type="file" name="hou_s_picture" value="${param.hou_s_picture}"/>
<img src="" id="picture2" >
										</div>
									</div>
								</div>	
								
								
								
								<div class="form-group">
									<label class="col-md-4 control-label">房屋圖片三:</label>
									<div class="col-md-8 inputGroupContainer">
										<div class="input-group">
											<span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
											
<input id="d3"  value="" type="file" name="hou_t_picture" value="${param.hou_t_picture}"/>
<img src="" id="picture3">
										</div>
									</div>
								</div>						
								<%
									LanService lanSvc = new  LanService();
								%>	

							<input type="hidden" name="lan_id" value="<%=lanSvc.getOneLanByMemId(memVO.getMem_id()).getLan_id()%>">
									
							<input type="hidden" name="action" value="frontinsert">			
										
							<input type="submit" value="送出新增" class="btn btn-primary">
							<input type="hidden" name="action" value="getOne_For_Display">	
							</fieldset>


						</form>
						
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
	
</script>
