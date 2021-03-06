<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.* , com.goodhouse.member.model.*" %>
<%@ page import="com.goodhouse.good_ord.model.*, com.goodhouse.good_ord_list.model.*" %>
<%
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	String mem_id = memVO.getMem_id();
	Good_ordService good_ordSvc = new Good_ordService();
	List<Good_ordVO> list = (List<Good_ordVO>)good_ordSvc.getPartGood_ord(mem_id);
	pageContext.setAttribute("list", list);
	pageContext.setAttribute("good_ordSvc", good_ordSvc);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>訂單查看</title>
<!--Style css-->
<link rel="stylesheet" href="<%=request.getContextPath()%>/front/pointgoods/select2/style.css">
</head>
<body>
<jsp:include page="/FrontHeaderFooter/Header.jsp" />
<div class="container">
	<div class="row">
		<div class="col-lg-6">
			<div class="card">
				<div class="card-header">
					<h4>我的訂單列表</h4>
				</div>
				<%if(list != null && (list.size() > 0)) {%>
					<div class="card-body p-0">
							<div class="table-responsive">
								<table class="table table-striped mb-0">
									<tr style="background-color:#EDF9DE">
										<td>收件人</td>
										<td>積分總額</td>
										<td>地址</td>
										<td>訂單狀態</td>
										<td>訂單日期</td>
										<td>查看詳情</td>
									</tr>

									<c:forEach var="good_ordVO" items="${list}">
										<tr>
											<td>${good_ordVO.good_ord_nam}</td>
											<td>${good_ordVO.good_ord_tot}</td>
											<td>${good_ordVO.good_ord_add}</td>
											<td>
												<c:if test="${good_ordVO.good_ord_sta eq 'GO001'}">
													<font color ="red"><b>備貨中</b></font>
												</c:if>
												<c:if test="${good_ordVO.good_ord_sta eq 'GO002'}">
													<font color ="green"><b>已出貨</b></font>
												</c:if>
											</td>
											<td>							
												<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${good_ordVO.good_ord_dat}"/>
											</td>
											<td>
												<input type="button" value="查看詳情" class="btn btn-outline-success heart" id="${good_ordVO.good_ord_id}">
											</td>
										</tr>
									</c:forEach>
								</table>
							</div>
						</div>
					<%}else{ %>
						<h4>尚無訂單</h4>
					<%} %>
				</div>
			</div>
					
					
			<div class="col-lg-6">
				<div class="card">
					<div class="card-header">
						<h4>我的訂單明細</h4>
					</div>
					<c:forEach var="good_ordVO" items="${list}">
						<div class="card-body p-0">
							<div class="table-responsive">
								<table id="${good_ordVO.good_ord_id}" style="display:none" class="table table-striped mb-0 checklist">
									<tr style="background-color:#EDF9DE">
										<td>商品名稱</td>
										<td>商品描述</td>
										<td>商品價格</td>
										<td>商品數量</td>
									</tr>
									<jsp:useBean id="pointgoodsSvc" scope="page" class="com.goodhouse.pointgoods.model.PointgoodsService"/>
									<jsp:useBean id="good_ord_listSvc" scope="page" class="com.goodhouse.good_ord_list.model.Good_ord_listService"/>
									<c:forEach var="good_ord_listVO" items="${good_ord_listSvc.getPart(good_ordVO.good_ord_id)}">
										<tr>
											<td>${pointgoodsSvc.getOnePointgoods(good_ord_listVO.good_id).good_nam}</td>
											<td>${pointgoodsSvc.getOnePointgoods(good_ord_listVO.good_id).good_dsc}</td>
											<td>${pointgoodsSvc.getOnePointgoods(good_ord_listVO.good_id).good_pri}</td>
											<td>${good_ord_listVO.good_ord_amo}</td>
										</tr>
									</c:forEach>
									<tr>
										<td></td>
										<td></td>
										<td>總價 :</td>
										<td><font color="red"><b>${good_ordSvc.getOneGood_ord(good_ordVO.good_ord_id).good_ord_tot}</b><font></td>		
									</tr>
								</table>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
	</div>
</div>
<script type="text/javascript">
	$(".heart").click(function(){
		$("table.checklist").attr('style','display:none');
		var id = $(this).attr('id');
		$('table[id=' + id + ']').attr('style','display:inline');
	});
</script>
<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
</body>
</html>