<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.goodhouse.pointgoods.model.*"%>
<%@ page import="com.goodhouse.house.model.*"%>
<%
	HouseService houSvc = new HouseService();
	List<HouseVO> list = houSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HouseBrowse</title>
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
</style>
</head>
<body>
	<jsp:include page="/FrontHeaderFooter/Header.jsp" />

	<%@ include file="page1.file"%>

	<div class="container">
		<div class="row">
			<div class="col-sm-4">
				<div class="row">
					<div class="col-sm-2"></div>
					<div class="col-sm-10">
						<div class="card w-75">
							<div class="card-header">
								<span class="house_search">房屋搜尋</span>
							</div>
							<div class="card-body">
								<div class="input-group">
									<input type="text" class="form-control"
										placeholder="Search this house">
									<div class="input-group-append">
										<button class="btn btn-secondary" type="button">
											<i class="fa fa-search"></i>
										</button>
									</div>
								</div>
								<!--   <h5 class="card-title">Special title treatment</h5>
                    <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
                    <a href="#" class="btn btn-primary">Go somewhere</a> -->
							</div>
						</div>
						<div class="card w-75">
							<div class="card-header">房型分類搜尋</div>
							<div class="card-body">
								<form action="/action_page.php">
									<div class="form-check-inline">
										<label class="form-check-label" for="check1"> <input
											type="checkbox" class="form-check-input" id="check1"
											name="vehicle1" value="something" checked> <span>套房</span>
										</label>
									</div>
									<div class="form-check-inline">
										<label class="form-check-label" for="check2"> <input
											type="checkbox" class="form-check-input" id="check2"
											name="vehicle2" value="something"> <span>住宅</span>
										</label>
									</div>
									<div class="form-check-inline">
										<label class="form-check-label" for="check3"> <input
											type="checkbox" class="form-check-input" id="check3"
											name="vehicle3" value="something"> <span>雅房</span>
										</label>
									</div>
									<div class="form-check-inline">
										<label class="form-check-label" for="check3"> <input
											type="checkbox" class="form-check-input" id="check3"
											name="vehicle3" value="something"> <span>雅房</span>
										</label>
									</div>
									<button type="submit" class="btn btn-primary">Submit</button>
								</form>
							</div>
						</div>
						<div class="card w-75">
							<div class="card-header">地區分類搜尋</div>
							<div class="card-body">
								<form action="/action_page.php">
									<div class="form-check-inline">
										<label class="form-check-label" for="check1"><input
											type="checkbox" class="form-check-input" id="check1"
											name="vehicle1" value="something" checked> <span>台北</span>
										</label>
									</div>
									<div class="form-check-inline">
										<label class="form-check-label" for="check2"> <input
											type="checkbox" class="form-check-input" id="check2"
											name="vehicle2" value="something"> <span>台中</span>
										</label>
									</div>
									<div class="form-check-inline">
										<label class="form-check-label" for="check3"> <input
											type="checkbox" class="form-check-input" id="check3"
											name="vehicle3" value="something"> <span>高雄</span>
										</label>
									</div>
									<div class="form-check-inline">
										<label class="form-check-label" for="check3"> <input
											type="checkbox" class="form-check-input" id="check3"
											name="vehicle3" value="something"> <span>桃園</span>
										</label>
									</div>
									<button type="submit" class="btn btn-primary">Submit</button>
								</form>
							</div>
						</div>

						<div class="card w-75">
							<div class="card-header">
								<span class="house_search">價錢搜尋</span>
							</div>
							<div class="card-body">
								<div class="input-group">
									<input type="text" class="price" placeholder="lower"> <input
										type="text" class="price" placeholder="height">
									<div class="input-group-append">
										<button class="btn btn-secondary" type="button">
											<i class="fa fa-search"></i>
										</button>
									</div>
								</div>
								<!--   <h5 class="card-title">Special title treatment</h5>
                    <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
                    <a href="#" class="btn btn-primary">Go somewhere</a> -->
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-8">
				<c:forEach var="houVO" varStatus="s" items="${list}"
					begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					
					<c:if test="${(s.count%3-1) == 0 }">
						<div class="row">
					</c:if>
					
					<div class="col-sm-4">
					
						<div class="card">
							<img
								src="<%=request.getContextPath() %>/HouseServlet?hou_id=${houVO.hou_id}&photo=1"
								class="card-img-top">

							<div class="card-body">
								<h5 class="card-title">${houVO.hou_name}</h5>
								<p class="card-text">${houVO.hou_rent}</p>
								<Form METHOD="post" ACTION="<%=request.getContextPath()%>/back/house/hou.do"> 
									<input type="hidden" name="hou_id" value="${houVO.hou_id}">
									<input type="hidden" name="action" value="front_getOne_For_Display">
									<input type="submit" value="查看詳情" >
								</Form>
								<p class="card-text">
									<small class="text-muted">${houVO.hou_note}</small>
								</p>
							</div>

						</div>
					</div>
					
					
					<c:if test="${s.count%3 == 0 }">
<!-- 	卡片大小格式跑掉		------ -->
			
		
				</c:if>
				</c:forEach>
				</div>
			</div>
		</div>
		<%@ include file="page2.file"%>
		<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
</body>
</html>