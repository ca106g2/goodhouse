<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.goodhouse.pointgoods.model.*"%>
<%@ page import="com.goodhouse.house.model.*"%>
<jsp:useBean id="adSvc2" scope="page"
	class="com.goodhouse.ad.model.AdService" />
<jsp:useBean id="ad_sorSvc2" scope="page"
	class="com.goodhouse.ad_sort.model.Ad_sortService" />
<jsp:useBean id="houSvc2" scope="page"
	class="com.goodhouse.house.model.HouseService" />

<%
	HouseService houSvc = new HouseService();
	List<HouseVO> list = houSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GoodHouse</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front/dist/css/swiper.min.css">
<style type="text/css">
.page-item:first-child .page-link, .page-item:last-child .page-link {
	border-radius: 0;
}

.page-link {
	margin-left: 6px;
	margin-right: 6px;
}

.has-search .form-control {
	padding-left: 2.375rem;
}

.has-search .form-control-feedback {
	position: absolute;
	z-index: 2;
	display: block;
	width: 2.375rem;
	height: 2.375rem;
	line-height: 2.375rem;
	text-align: center;
	pointer-events: none;
	color: #aaa;
}

.house_search {
	
}

.card-img-top {
	height: 200px;
	weight: 300px;
}

.myClass {
	display: inline-block;
}

.carousel .carousel-item img {
	height: 50%;
	width: 50%;
}

/*     input[type="image"] { */
/*         border: 0; */
/*         background: url('request.getContextPath() %>/HouseServlet?hou_id=${adVO.hou_id}&photo=1') no-repeat -40px left; */
/*  		height: 50%; */
/*   		width : 50%; */
/*     } */
  .swiper-container {
        width: 90%;
        height: 450px;
        margin-left: auto;
        margin-right: auto;
    }
  .swiper-slide {
      text-align: center;
      font-size: 18px;
      background: #fff;
      /* Center slide text vertically */
      display: -webkit-box;
      display: -ms-flexbox;
      display: -webkit-flex;
      display: flex;
      -webkit-box-pack: center;
      -ms-flex-pack: center;
      -webkit-justify-content: center;
      justify-content: center;
      -webkit-box-align: center;
      -ms-flex-align: center;
      -webkit-align-items: center;
      align-items: center;
    }
    
    #howming {
	  overflow : hidden;
	  text-overflow : ellipsis;
	  white-space : nowrap;
	  width : 200px;
	}

</style>
</head>
<body>
	<jsp:include page="/FrontHeaderFooter/Header.jsp" />
	<%@ include file="page1.file"%>
	<div class="container">
		<div class="row">

	<div class="swiper-container">
		<div class="swiper-wrapper">		
						<c:forEach var="adVO2" varStatus="s" items="${adSvc2.all}">
								<c:forEach var="hou_VO2" items="${houSvc2.all}">
									<c:if test="${adVO2.ad_sort_id eq 'ADS0000002' and adVO2.ad_status eq '上架'}">
										<c:if test="${adVO2.hou_id eq hou_VO2.hou_id}">
													<div class="swiper-slide" style="background-repeat: no-repeat; background-image: url(<%=request.getContextPath() %>/HouseServlet?hou_id=${adVO2.hou_id}&photo=1); margin-right: 30px; background-size: 100%;">
		          				 			</div>
	   									</c:if>
	   								</c:if>
	       	   					</c:forEach>
	        				</c:forEach>
	        						</div> 		
		<!-- Add Pagination -->
		<div class="swiper-pagination"></div>
		<!-- Add Arrows -->
		<div class="swiper-button-next"></div>
		<div class="swiper-button-prev"></div>
	</div>
</div>
</div>
<br>
<br>
	<div class="container">
		<div class="row">
			<div class="col-sm-4">
				<div class="row">
					<div class="col-sm-2"></div>
					<div class="col-sm-10">
						<div class="card w-75">
							<div class="card-header">
								<span class="house_search">地址搜尋</span>
							</div>
							<div class="card-body">
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/front/house/hou.do">
									<div class="input-group">
										<input type="text" class="form-control"
											placeholder="Search this house" name="hou_address">
										<div class="input-group-append">
											<input class="btn btn-secondary" type="submit" value="送出">
											<input type="hidden" name="action"
												value="listHou_ByCompositeQueryForIndex">
										</div>
									</div>
								</FORM>
							</div>
						</div>
						<div class="card w-75">
							<div class="card-header">房型分類搜尋</div>
							<div class="card-body">
								<form METHOD="post"
									ACTION="<%=request.getContextPath()%>/front/house/hou.do">
									<div class="form-check-inline">
										<label class="form-check-label" for="check1"> <input
											type="radio" class="form-check-input" id="check1"
											name="hou_type" value="套房" checked> <span>套房</span>
										</label>
									</div>
									<div class="form-check-inline">
										<label class="form-check-label" for="check2"> <input
											type="radio" class="form-check-input" id="check2"
											name="hou_type" value="住宅"> <span>住宅</span>
										</label>
									</div>
									<div class="form-check-inline">
										<label class="form-check-label" for="check3"> <input
											type="radio" class="form-check-input" id="check3"
											name="hou_type" value="雅房"> <span>雅房</span>
										</label>
									</div>
									<div class="form-check-inline">
										<label class="form-check-label" for="check4"> <input
											type="radio" class="form-check-input" id="check4"
											name="hou_type" value="公寓"> <span>公寓</span>
										</label>
									</div>
									<div class="input-group-append">
										<input class="btn btn-secondary" type="submit" value="查詢">
										<input type="hidden" name="action"
											value="listHou_ByCompositeQueryForIndex">
									</div>
								</form>
							</div>
						</div>
						<div class="card w-75">
							<div class="card-header">地區分類搜尋</div>
							<div class="card-body">
								<form METHOD="post"
									ACTION="<%=request.getContextPath()%>/front/house/hou.do">
									<div class="form-check-inline">
										<label class="form-check-label" for="check5"><input
											type="radio" class="form-check-input" id="check5"
											name="hou_address" value="台北"> <span>台北</span> </label>
									</div>
									<div class="form-check-inline">
										<label class="form-check-label" for="check6"> <input
											type="radio" class="form-check-input" id="check6"
											name="hou_address" value="台中"> <span>台中</span>
										</label>
									</div>
									<div class="form-check-inline">
										<label class="form-check-label" for="check7"> <input
											type="radio" class="form-check-input" id="check7"
											name="hou_address" value="高雄"> <span>高雄</span>
										</label>
									</div>
									<div class="form-check-inline">
										<label class="form-check-label" for="check8"> <input
											type="radio" class="form-check-input" id="check8"
											name="hou_address" value="桃園" checked> <span>桃園</span>
										</label>
									</div>
									<div class="input-group-append">
										<input class="btn btn-secondary" type="submit" value="查詢">
										<input type="hidden" name="action"
											value="listHou_ByCompositeQueryForIndex">
									</div>
								</form>
							</div>
						</div>

						<div class="card w-75">
							<div class="card-header">
								<span class="house_search">價錢搜尋</span>
							</div>
							<div class="card-body">
								<form METHOD="post"
									ACTION="<%=request.getContextPath()%>/front/house/hou.do">
									<div class="input-group">
										<input type="text" class="form-control" placeholder="金額以下"
											name="hou_rent">
										<div class="input-group-append">
											<input class="btn btn-secondary" type="submit" value="查詢">
											<input type="hidden" name="action"
												value="listHou_ByCompositeQueryForIndex">
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<div class="col-sm-8">
				<div class="row">
					<c:forEach var="houVO" varStatus="s" items="${list}"
						begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
						<c:if
							test="${houVO.hou_property eq '未出租' && houVO.hou_parkspace eq '已審核'}">
							<div class="col-sm-4 myClass">
								<div class="card" style="margin-bottom: 10px;">
									<img
										src="<%=request.getContextPath() %>/HouseServlet?hou_id=${houVO.hou_id}&photo=1"
										class="card-img-top">
									<div class="card-body" style="height: 175px;">
										<h8 class="card-title">${houVO.hou_id}</h5>
										<h5 class="card-title" id="howming" style="font-family:Microsoft JhengHei">${houVO.hou_name}</h5>
										<div class="card-text" style="color:#ffc107; font-family:Microsoft JhengHei">$ ${houVO.hou_rent}</div>
										<Form METHOD="post"
											ACTION="<%=request.getContextPath()%>/front/house/hou.do">
											<input type="hidden" name="hou_id" value="${houVO.hou_id}">
											<input type="hidden" name="action" value="front_getOne_For_Display">
											<input class="btn btn-warning" type="submit" value="查看詳情">
										</Form>
										<!-- 											<p class="card-text"> -->
										<%-- 												<small class="text-muted">${houVO.hou_note}</small> --%>
										<!-- 											</p> -->
									</div>
								</div>
							</div>
						</c:if>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="page2.file"%>
	<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
	<script src="<%=request.getContextPath()%>/front/dist/js/swiper.min.js"></script>

	<script>

var swiper = new Swiper('.swiper-container', {
        pagination: '.swiper-pagination',
        nextButton: '.swiper-button-next',
        prevButton: '.swiper-button-prev',
        paginationClickable: true,
        spaceBetween: 30,
        loop :true
    });
	</script>

</body>
</html>