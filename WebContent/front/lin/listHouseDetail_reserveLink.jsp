<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.goodhouse.house.model.*"%>
<%@ page import="com.goodhouse.appointment.model.*"%>
<%@ page import="com.goodhouse.house_noappointment.model.*"%>
<%@ page import="com.goodhouse.member.model.*"%>
<%@ page import="com.goodhouse.landlord.model.*"%>
<%@ page import="com.goodhouse.house_evaluate.model.*"%>
<%@ page import="com.goodhouse.house_track.model.*"%>
<%@ page import="com.goodhouse.ad_report.model.*"%>
<%@ page import="com.goodhouse.house.model.*" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%
	session.setAttribute("RentMess_insert_key",new Object());
	String hou_id = request.getParameter("hou_id");
	HouseService houSvc = new HouseService();
	HouseVO houVO = houSvc.getOneHouse(hou_id);
	pageContext.setAttribute("houVO", houVO);
	
	String lan_id = houVO.getLan_id();
	LanService lanSvc = new LanService();
	LanVO lanVO = lanSvc.getOneLan(lan_id);
	pageContext.setAttribute("lan_id", lan_id);
	pageContext.setAttribute("lanVO", lanVO);
	
%>

<%
	SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
	Date date = new Date();
	String strDate = sdFormat.format(date);
	
%>

<!--======================= 慈慈 start-->
<%
	House_TrackService houTraSvc = new House_TrackService();
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	pageContext.setAttribute("memVO", memVO);
	
	House_EvaluateService houEvaSvc = new House_EvaluateService();
	List<House_EvaluateVO> houEvaList = houEvaSvc.getListByHouId(houVO.getHou_id());
	pageContext.setAttribute("houEvaList", houEvaList);
	
%>
<!--======================== 慈慈 end -->
<!--======================= TIM start-->
<%	

 	Ad_reportVO ad_repVO = (Ad_reportVO) request.getAttribute("ad_repVO");

%>

<!--======================== TIM end -->



<%
	String appoint_id = request.getParameter("appoint_id");
	AppointService appointSvc = new AppointService();
	AppointVO appointVO = appointSvc.getOneAppoint(appoint_id);
%>

<!-- 轉交請求，可選大日曆版 -->


<jsp:useBean id="memSvc" scope="page" class="com.goodhouse.member.model.MemService" />
<jsp:useBean id="houNoAppSvc" scope="page" class="com.goodhouse.house_noappointment.model.HouNoAppService" />
<jsp:useBean id="adSvc" scope="page" class="com.goodhouse.ad.model.AdService" />
<jsp:useBean id="rentMessSvc" scope="page" class="com.goodhouse.rental_message.model.RentMessService" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List HouseDetail</title>
<style type="text/css">

	#back{
		position:fixed;
		top: 20%;
		z-index: 994;
		left: 4%;
	}


	.wrapper{
	display: -webkit-box;
	display: -ms-flexbox;
	display: flex;
	width: 400px;
	margin: 50vh auto 0;
	-ms-flex-wrap: wrap;
	    flex-wrap: wrap;
	-webkit-transform: translateY(-50%);
	        transform: translateY(-50%);
}

.switch_box{
	display: -webkit-box;
	display: -ms-flexbox;
	display: flex;
	max-width: 200px;
	min-width: 200px;
	height: 200px;
	-webkit-box-pack: center;
	    -ms-flex-pack: center;
	        justify-content: center;
	-webkit-box-align: center;
	    -ms-flex-align: center;
	        align-items: center;
	-webkit-box-flex: 1;
	    -ms-flex: 1;
	        flex: 1;
}

/* Switch 1 Specific Styles Start */

.box_1{
	background: #eee;
}

input[type="checkbox"].switch_1{
	font-size: 30px;
	-webkit-appearance: none;
	   -moz-appearance: none;
	        appearance: none;
	width: 3.5em;
	height: 1.5em;
	background: #ddd;
	border-radius: 3em;
	position: relative;
	cursor: pointer;
	outline: none;
	-webkit-transition: all .2s ease-in-out;
	transition: all .2s ease-in-out;
  }
  
  input[type="checkbox"].switch_1:checked{
	background: #0ebeff;
  }
  
  input[type="checkbox"].switch_1:after{
	position: absolute;
	content: "";
	width: 1.5em;
	height: 1.5em;
	border-radius: 50%;
	background: #fff;
	-webkit-box-shadow: 0 0 .25em rgba(0,0,0,.3);
	        box-shadow: 0 0 .25em rgba(0,0,0,.3);
	-webkit-transform: scale(.7);
	        transform: scale(.7);
	left: 0;
	-webkit-transition: all .2s ease-in-out;
	transition: all .2s ease-in-out;
  }
  
  input[type="checkbox"].switch_1:checked:after{
	left: calc(100% - 1.5em);
  }
  
   .myContainer {
  	padding: 10px;
  	margin: 10px 0;
  }
	
/* Switch 1 Specific Style End */
/* TIM'S TABLE CSS */

#table1 {
	font-family:Microsoft JhengHei;;
	font-size: 16px;
	width: 1200px;
	text-align: center;
	border-collapse: collapse;
	margin-left: auto;
	margin-right: auto;
}

#table1  th {
	background-color: #009FCC;
	padding: 10px;
	color: #fff;

}

#table1  td {

	padding: 5px;
}
.swiper-container {
    width: 100%;
    height: 500px;
    margin-left: auto;
    margin-right: auto;
}
.swiper-slide {
    background-size: cover;
    background-position: center;
}
#panorama {
    width: 600px;
    height: 500px;
}

</style>
</head>
<body>
	<jsp:include page="/FrontHeaderFooter/Header.jsp" />
	<div class="container">
	
		<div class="row">
			<div id="back">
					
				<a href="<%=request.getContextPath()%>/front/index.jsp">
					<img src="<%=request.getContextPath()%>/images/back.png" width="100" height="100" border="0">
				</a>
			</div>
		
		</div>
		
		<div class="row">
			<div class="col-12">
				<div class="row">
					<!-- Swiper -->
					<div class="swiper-container">
						<div class="swiper-wrapper">
							<div class="swiper-slide swiper-no-swiping" id="panorama"></div>
							<div class="swiper-slide" style="background-image: url(<%=request.getContextPath()%>/HouseServlet?hou_id=<%=houVO.getHou_id()%>&photo=2); "></div>
							<div class="swiper-slide" style="background-image: url(<%=request.getContextPath()%>/HouseServlet?hou_id=<%=houVO.getHou_id()%>&photo=1); "></div>
						</div>
						<!-- Add Pagination -->
       					<div class="swiper-pagination"></div>
				    	<!-- Add Arrows -->
				    	<div class="swiper-button-next swiper-button-white"></div>
				    	<div class="swiper-button-prev swiper-button-white"></div>
				    </div>
				</div>
				<div class="row justify-content-end">
					<div class="col-sm-4"></div>
					<div class="col-sm-4" style="text-align: right;">
						<div class="price">
							<span>房租:<%=houVO.getHou_rent()%></span><span>元/月</span>
						</div>
						<div class="addr">
							<span>房屋地址:<%=houVO.getHou_address()%></span>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-6" style="text-align: left;">
						<div class="addr">
							<span>房屋名稱 :<%=houVO.getHou_name()%></span>
						</div>
						<div class="addr">
							<span>房屋編號 :<%=houVO.getHou_id()%></span>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-3">
						<span class="lan">房東 : </span> 
							<span> 
								<c:forEach var="memVO" items="${memSvc.all}">
									<c:if test="${lanVO.mem_id == memVO.mem_id}">
										${memVO.mem_name}
									</c:if>
								</c:forEach>
							</span>
					</div>
					<div class="col-sm-3"></div>
					<div class="col-sm-6">
						<div class="row">
							<div class="col-sm-6">
								<span class='type'>型別：</span> <span><%=houVO.getHou_type()%></span>
							</div>
							<div class="col-sm-6">
								<span class='type'>坪數：</span> <span><%=houVO.getHou_size()%></span>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<span class='type'>開火：</span> <span><%=houVO.getHou_cook()%></span>
							</div>
							<div class="col-sm-6">
								<span class='type'>管理費：</span> <span><%=houVO.getHou_managefee()%></span>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
<!--========================================= 以下是慈慈的加入追蹤功能 =======================================--->			
					<jsp:useBean id="houTraSvc1" scope="page" class="com.goodhouse.house_track.model.House_TrackService" />
					<div class="col-sm-3">
						<c:if test="${memVO != null}">
							<img src="<%=request.getContextPath()%>/front/lin/${houTraSvc1.findByHouIdAndMem_id(houVO.hou_id, memVO.mem_id) == null ? 'heart_white.png' : 'heart_red.png'}" 
								class="heart"  title="${houTraSvc1.findByHouIdAndMem_id(houVO.hou_id, memVO.mem_id) != null ? '取消收藏' : '加入收藏' }"
								alt="${houTraSvc1.findByHouIdAndMem_id(houVO.hou_id, memVO.mem_id) != null ? 'favorite' : 'unfavorite' }"
								style="width:50px;heidth:50px" id="${houVO.hou_id}" >
						
							<input type="hidden" name="hou_id" value="${houVO.hou_id}">
							<input type="hidden" name="mem_id" value="${memVO.mem_id}">
						</c:if>
					</div>
<!--==========================================以上是慈慈的加入追蹤功能 ==================================---->
					<div class="col-sm-12">
						<ul class="nav nav-tabs" role="tablist">
							<li class="nav-item">
								<a class="nav-link active" href="#profile" role="tab" data-toggle="tab" id="note">備註</a>
							</li>
							
							<li class="nav-item">
								<a class="nav-link" href="#buzz" role="tab" data-toggle="tab">問與答</a>
							</li>
							
							<c:choose>
								<c:when test="${memVO != null }">
									<li class="nav-item" id="myTest">
										<a class="nav-link" href="#references" role="tab" data-toggle="tab">預約行程</a>
									</li>
								</c:when>
							</c:choose>
<!---======================================= 以下是慈慈的房屋評價功能 ===================================================--->						
<%-- 							<c:if test="${memVO != null }"> --%>
								<li class="nav-item">
									<a class="nav-link" href="#evaluate" role="tab" data-toggle="tab">評價</a>
								</li>
<%-- 							</c:if> --%>
						
<!---========================================以上是慈慈的房屋評價功能 ================================================-->
<!---========================================= 以下是 TIM 的廣告檢舉功能 =========================================--->
<%-- 						<c:if test="${memVO != null }"> --%>
								<li class="nav-item">
									<a class="nav-link" href="#ad_report" role="tab" data-toggle="tab">廣告檢舉</a>
								</li>
<%-- 						</c:if> --%>
<!---=========================================以上是 TIM 的廣告檢舉功能 ==========================================--->
<!---========================================= 以下是 TIM 的廣告檢舉功能 =========================================--->
<%-- 						<c:if test="${memVO != null }"> --%>
								<li class="nav-item">
									<a class="nav-link" href="#hou_map" role="tab" data-toggle="tab">房屋地標</a>
								</li>
<%-- 						</c:if> --%>
<!---=========================================以上是 TIM 的廣告檢舉功能 ==========================================--->	
						</ul>


						<!-- Tab panes -->
						<div class="tab-content">
						
							<div role="tabpanel" class="tab-pane fade in active" id="profile">
								<div class="myrow"><%=houVO.getHou_note()%></div>
							</div>
							
							<div role="tabpanel" class="tab-pane fade" id="buzz">
								  <div class="container myContainer">
<%-- 								  ${rentMessSvc.getPart(param.hou_id).size()} --%>
								  <c:forEach var="rentMessVO" items="${rentMessSvc.getPart(param.hou_id)}">
								    <div class="row">
								        <div class="col-sm-2">
								        	<div>
								        		<span>問題編號: ${rentMessVO.ren_mes_id}</span>
								        	</div>
								        </div>
								        <div class="col-sm-10">
								        	<div class="row">
												<span>會員編號: ${rentMessVO.mem_id}</span>		<span>∫留言日期:    ${rentMessVO.ren_mes_time}∫</span>
								        	</div>
								        	<div class="row">
								        		<span>提問: ${rentMessVO.ren_mes_request}</span>
								        	</div>
								        	<div class="row">
								        		<span>房東回覆: ${rentMessVO.ren_mes_response}</span>
								        	</div>
								        </div>
								    </div>
								   </c:forEach>
							    </div>
<%-- 							    <% if(!lan_id.equals(lan_id_mem)){%> --%>
							    	
							    
								    <div class="container myContainer">
								    	<% if (memVO != null) {%>
								    		<% if (memVO.getMem_id() != null) {%>
								    			<% if (lanVO != null) {%>
								    				<% if (!lanVO.getMem_id().equals( memVO.getMem_id())){%>
												    	<form METHOD="post" ACTION="<%=request.getContextPath()%>/front/rentMess/rentMess.do">
													    	<div class="row">
													    		<span>提出問題</span>
													    	</div>
													    	<div class="row">
													    		<span>提醒您開車不喝酒，喝酒開車吃大便!</span>
													    	</div>
													    	<div class="row">
													    		<div>
													    			<textarea id="myAsk" cols=100 rows=5 name="ren_mes_request"></textarea>
													    		</div>	
													    	</div>
															<div class="row">
													    		<div class="col-sm-2"></div>
													    		<div class="col-sm-8">
													    			
													    				<input  type="hidden" name="mem_id" value="${memVO.mem_id}">
													    				<input  type="hidden" name="lan_id" value="<%=lan_id%>">
													    				<input  type="hidden" name="hou_id" value="${houVO.hou_id }">
													    				<input  type="hidden" name="ren_mes_time" value="<%=strDate%>">
													    				<input  type="hidden" name="ren_mes_response" value="<%=""%>">
														    			<input id="mySumit" class="btn btn-danger" type="submit"  value="提出問題">
<%-- 														    			<% if(action != null){ %> --%>
														    			<input type="hidden" name="action" value="insert">
<%-- 														    			<% } %> --%>
														    			<input id="myClear" class="btn btn-primary" type="button" name="" value="重新填寫">
													    			
													    		</div>
													    		<div class="col-sm-2"></div>
													    	</div>
													    </form>
											    	<% } %>
											    <% } %>
										    <% } %>
										  <% } %>
								    </div>
<%-- 								  <% } %> --%>
							</div>
							
							<div role="tabpanel" class="tab-pane fade" id="references" style="height: 1000px;">
								<div>
									<input type="hidden" name="action" value="insert">
<!-- 									選擇要轉交的日曆頁面 -->
<%-- 									<input type="button" value="我要預約" onClick="location.href='<%=request.getContextPath()%>/memPickReserveDate_doGet.jsp'"> --%>
										<% if (memVO != null) {%>
								    		<% if (memVO.getMem_id() != null) {%>
								    			<% if (lanVO != null) {%>
								    				<% if (!lanVO.getMem_id().equals( memVO.getMem_id())){%>
														<jsp:include page="/memPickReserveDate_doGet.jsp" />
													<% }else { %>
														<span>這是你的房屋!</span>
													<% } %>
											   <% } %>
									   		<% } %>
										<% } %>

								</div>
							</div>
<!---=================================== 以下是慈慈的房屋評價功能 ==================================--->
							<div role="tabpanel" class="tab-pane fade in active" id="evaluate">
									<c:if test="${memVO != null}">
<!-- 									<form class="houEvaForm"> -->
										<dl class="row ">
											<dt class="col-sm-3">請選擇評價等級</dt>
											<dd class="col-sm-9">
												<div class="custom-control custom-radio">
												  <input type="radio" id="customRadio1" name="hou_eva_grade" class="custom-control-input houEvaGgrade" value="G1非常不好">
												  <label class="custom-control-label" for="customRadio1">非常不好</label>
												</div>
												<div class="custom-control custom-radio">
												  <input type="radio" id="customRadio2" name="hou_eva_grade" class="custom-control-input houEvaGgrade" value="G2不好">
												  <label class="custom-control-label" for="customRadio2">不好</label>
												</div>
												<div class="custom-control custom-radio">
												  <input type="radio" id="customRadio3" name="hou_eva_grade" class="custom-control-input houEvaGgrade" value="G3普通">
												  <label class="custom-control-label" for="customRadio3">普通</label>
												</div>
												<div class="custom-control custom-radio">
												  <input type="radio" id="customRadio4" name="hou_eva_grade" class="custom-control-input houEvaGgrade" value="G4好">
												  <label class="custom-control-label" for="customRadio4" id="click4">好</label>
												</div>
												<div class="custom-control custom-radio">
												  <input type="radio" id="customRadio5" name="hou_eva_grade" class="custom-control-input houEvaGgrade" value="G5非常好">
												  <label class="custom-control-label" for="customRadio5" id="click5">非常好</label>
												</div>
											</dd>
											
											<dt class="col-sm-3">請寫下評論</dt>
											<dd class="col-sm-9">
												<div class="form-group">
<!-- 												    <label for="exampleFormControlTextarea1">Example textarea</label> -->
													<textarea id="exampleFormControlTextarea1" name="hou_eva_content" rows="3" cols=50 class="form-control houEevaContent"></textarea>
<!-- 												    <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea> -->
												</div>
											</dd>
										</dl>
										<input type="hidden" name="action" value="insert2">
										<input type="hidden" name="hou_id" value="${houVO.hou_id}" class="houId">
										<input type="hidden" name="mem_id" value="${memVO.mem_id}" class="memId">
										<input type="submit" value="送出" class="btn btn-outline-secondary" id="submitEva">
<!-- 								</form> -->
									</c:if>
								
								<table class="table table-borderless">
									<thead>
									    <tr>
									      	<th scope="col">評價等級</th>
									      	<th scope="col">評價內容</th>
									    </tr>
								  	</thead>
								  	<tbody>
								  	<c:forEach var="houEvaVO" items="${houEvaList}" varStatus="houEva">
									    <tr class="hou_eva_vo" >
									      	<td id="grade">${houEvaVO.hou_eva_grade.substring(2)}</td>
									      	<td>${houEvaVO.hou_eva_content}</td>
									    </tr>
								   	</c:forEach>
								  	</tbody>
								</table>
								
							</div>
<!----===================================== 以上是慈慈的房屋評價功能 ====================================--->					
<!----===================================== 以下是TIM功能 ====================================--->	
							<div role="tabpanel" class="tab-pane fade in active" id="ad_report">
								<c:if test="${memVO != null and houVO.hou_id eq adSvc.getOneAD(adSvc.getOneAdByHou(houVO.hou_id).ad_id).hou_id}">
									<table class="table table-borderless">
										<tbody>
										<tr>
											<th>房屋檢舉 : </th>				
												<td><%= houVO.getHou_name()%></td>
										</tr>
										<tr>
											<th>檢舉人 : </th>
												<td><%=memVO.getMem_name()%></td>
										</tr>
										<tr>
												<th>檢舉事由</th><td><label for="exampleFormControlTextarea1"></label> 
												<textarea name="ad_rep_reason" class="form-control ad_rep_reason" placeholder="請輸入事由"  id="exampleFormControlTextarea1" rows="3"></textarea>
											</td>
										</tr>	
										
										<tr>
											
											<th>檢舉日期</th><td><input name="ad_rep_date" id="f_date1" type="text" class="ad_rep_date">
											</td>
										</tr>
										<tr>
											<th> </th><td>	
												<input type="hidden" name="mem_id" value="<%=memVO.getMem_id()%>" class="ad_memId">	
												<input type="hidden" name="ad_id" value="${adSvc.getOneAdByHou(houVO.hou_id).ad_id}" class="ad_Id">
												<input type="hidden" name="ad_rep_status" value="檢舉審核中" class="ad_rep_status">
												<input type="hidden" name="action" value="front_insert" />
												<input type="submit" class="btn btn-outline-secondary" value="送出" id="sum_ad_report" />
											</td>
										</tr>
											</tbody>
									</table>					
							
								</c:if>
							</div>					
<!----===================================== 以上是TIM功能 ====================================--->		
<!----===================================== 以下是TIM的MAP功能 ====================================--->								
							<div role="tabpanel" class="tab-pane fade in active embed-responsive embed-responsive-16by9" id="hou_map">				
		 							 <iframe class="embed-responsive-item"
    									  width="600" 
     									 height="450" 
     									 frameborder="0" 
     									 style="border:0" 
     									 src="https://www.google.com/maps/embed/v1/place?key=AIzaSyBool9F4tfRQvkhMlnIVxEX_cYowoKzsS0&q=<%=houVO.getHou_address()%>" 
     									 >
 									 </iframe>
							</div>						
			
<!----===================================== 以上是TIM的MAP功能 ====================================--->							
						</div>				
					</div>
					
				</div>
			</div>
		</div>
	</div>
		<script type="text/javascript">
/****************************以下慈慈的加入最愛追蹤功能************************************/
		
		$(".heart").click(function(){
			var element = $(this);
				if($(this).attr('alt') == "unfavorite") {
					$.ajax({
						type: "POST",
						url: "<%=request.getContextPath()%>/front/lin/house_track.do",
						data: {
							"hou_id":$(this).next().attr('value'),
							"action":"insert",
							"mem_id":$(this).next().next().attr("value")
							},
						dataType: "json",
						
						success: function(){
							hou_id = element.attr('id');
							$('img[id=' + hou_id + ']').attr({
								"src":"<%=request.getContextPath()%>/front/lin/heart_red.png",
								"title": "取消追蹤",
								"alt": "favorite"
							});
							swal("完成","成功加入追蹤","success");
						},
						error: function(){alert("AJAX發生錯誤")}
					});
				} else if ($(this).attr("alt") == "favorite") {
					
					$.ajax({
						type: "POST",
						url: "<%=request.getContextPath()%>/front/lin/house_track.do",
						data: {
							"hou_id":$(this).next().attr('value'),
							"action":"delete",
							"mem_id":$(this).next().next().attr("value")
							},
						dataType: "json",
						success: function(){
							hou_id = element.attr('id');
							$('img[id=' + hou_id + ']').attr({
								"src": "<%=request.getContextPath()%>/front/lin/heart_white.png",
								"title": "加入追蹤",
								"alt": "unfavorite"						
							});
							swal("完成","成功取消追蹤","error");
						},
						error: function(){alert("AJAX發生錯誤")}
					});
				};
			});
	
	
/****************************以上慈慈的加入最愛追蹤功能************************************/
 </script>
 <script type="text/javascript">
/****************************以下慈慈的評價功能********************************************/
		 	$('#submitEva').click(function(){
		 		var hou_eva_grade=0;
		 		
				if(	$("input:radio[name='hou_eva_grade']:checked")){
					hou_eva_grade = $("input:radio[name='hou_eva_grade']:checked").val();
		 			
				}
				
		 		$.ajax({

		 			type: "POST",
					url: "<%=request.getContextPath()%>/front/lin/house_evaluate.do",
					data: {
						"hou_eva_grade":hou_eva_grade,
						"hou_eva_content":$(".houEevaContent").val(),
						"mem_id":$(".memId").val(),
						"hou_id":$(".houId").val(),
						"action":"insert2"
						},
						
					dataType: "json",
					
					success: function(data){
						
							var evaluate = data.hou_eva_grade;
							var evaGrade = evaluate.substr(2);
							var gradeNo = evaluate.substr(1,1);
							
// 							if(gradeNo == 1) {
// 								$('tbody').append( '<tr> <td> <img src="/front/lin/heart_red.png"></td> <td>' + data.hou_eva_content + '</td> </tr>');
// 							}
							
							$('tbody').append( '<tr> <td>'+ evaGrade +'</td> <td>' + data.hou_eva_content + '</td> </tr>');
							
							$("input:radio[name='hou_eva_grade']").prop("checked",false) ;
							$(".houEevaContent").val("") ;
							
							swal("完成","評價成功","success");
						},
					error: function(){alert("AJAX發生錯誤")}
					
		 			});
			});
 
 
/****************************以上慈慈的評價功能********************************************/
/****************************以下TIM的評價功能********************************************/
		 	$('#sum_ad_report').click(function(){
				console.log($(".ad_Id").val());
				console.log($(".ad_memId").val());
				console.log($(".ad_rep_status").val());
				console.log($(".ad_rep_reason").val());
				console.log($(".ad_rep_date").val());
				
		 		$.ajax({

		 			type: "POST",
					url: "<%=request.getContextPath()%>/front/ad_report/ad_rep.do",
					data: {
						
						"ad_id":$(".ad_Id").val(),
						"mem_id":$(".ad_memId").val(),
						"ad_rep_status":$(".ad_rep_status").val(),
						"ad_rep_reason":$(".ad_rep_reason").val(),
						"ad_rep_date":$(".ad_rep_date").val(),
						"action":"front_insert"
						},
						
					dataType: "json",
					
					success: function(data){
							$(".ad_rep_reason").val("") ;
							swal("完成","檢舉成功","success");
						},
					error: function(){alert("AJAX發生錯誤")}					
		 			});
			});



/****************************以下TIM的評價功能********************************************/
	</script>
	
	
	
<jsp:include page="/FrontHeaderFooter/Footer.jsp" />	
</body>
<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<%
	java.sql.Date hou_app_date = null;
	try {
		hou_app_date = appointVO.getHou_app_date();
	} catch (Exception e) {
		hou_app_date = new java.sql.Date(System.currentTimeMillis());
	}
%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back/datetimepicker/jquery.datetimepicker.css" />
<!-- Link Swiper's CSS -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/dist/css/swiper.min.css">
<link rel="stylesheet" href="https://cdn.pannellum.org/2.4/pannellum.css"/>
<script
	src="<%=request.getContextPath()%>/back/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/back/datetimepicker/jquery.datetimepicker.full.js"></script>
<!-- Swiper JS -->
<script src="<%=request.getContextPath()%>/dist/js/swiper.min.js"></script>
<script type="text/javascript" src="https://cdn.pannellum.org/2.4/pannellum.js"></script>
<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>

$(function(){
	$('#myTest').on('click', function(){
		$('input[gldp-id="mydate"]').trigger('click');
		$('input[gldp-id="mydate"]').css('visibility', 'hidden');
		$('div[gldp-el="mydate"]').css('top', '100px');
		$('div[gldp-el="mydate"]').css('left', '20px');
	});
	
	$('#profile').addClass('show');
	
	$('#myClear').on('click', function(){
		$('#myAsk').val('');
	})
	
});
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=hou_app_date%>', // value:   new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	//minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});

	// ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

	//      1.以下為某一天之前的日期無法選擇
	//      var somedate1 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      2.以下為某一天之後的日期無法選擇
	//      var somedate2 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
	//      var somedate1 = new Date('2017-06-15');
	//      var somedate2 = new Date('2017-06-25');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//		             ||
	//		            date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});
	//git上傳註解用無意義
	var swiper = new Swiper('.swiper-container', {
        pagination: '.swiper-pagination',
        nextButton: '.swiper-button-next',
        prevButton: '.swiper-button-prev',
        paginationClickable: true,
        spaceBetween: 30
    });
	pannellum.viewer('panorama', {
        "type": "equirectangular",
        "panorama": "<%=request.getContextPath()%>/HouseServlet?hou_id=<%=houVO.getHou_id()%>&photo=3",
        "autoLoad": true,
        "showControls": true,
        "showZoomCtrl": false,
        "showFullscreenCtrl": false,
        "compass": false,
        "northOffset": 247.5,
    });
</script>
</html>
