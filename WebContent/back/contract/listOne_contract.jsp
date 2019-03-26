<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.goodhouse.contract.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	//ContractServlet.java(Concroller), 存入req的conVO物件
	ContractVO conVO = (ContractVO) request.getAttribute("conVO");
	pageContext.setAttribute("conVO", conVO);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<jsp:include page="/BackHeaderFooter/Header.jsp" />

	<div class="container">
		<div class="row ">


			<div class="col-2">
				<h4>
					<a href="<%=request.getContextPath()%>/back/contract/listAll_contract.jsp">
						<img src="<%=request.getContextPath()%>/images/back.png" width="100" height="100" border="0">
					</a>
				</h4>
			</div>

			<div class="col-10">
				<div class="card">

					<div class="card-header">
						<h3>合約分類內容</h3>
					</div>

					<div class="card-body">
						<div class="table-responsive">
							<table class="table table-bordered table-hover mb-0 text-nowrap text-center h3">
								<tbody>
									<tr>
										<th scope="row">合約分類編號</th>
										<td>${conVO.con_id}</td>
									</tr>
									<tr>
										<th>合約分類名稱</th>
										<td>${conVO.con_name}</td>
									</tr>
									<tr>
										<th>合約分類狀態</th>
<%-- 										<td>${conVO.con_status}</td> --%>
										<c:forEach var="con_status" items="${Con_statusList}">
											<c:if test="${con_status.status_no_name eq conVO.con_status}">
												<c:choose>
													<c:when test="${con_status.status_no_name eq 'S1啟用'}">
														<td style="color: #3498DB">${con_status.status_name}</td>
													</c:when>
													
													<c:when test="${con_status.status_no_name eq 'S2停用' }">
														<td style="color: #E74C3C">${con_status.status_name}</td>
													</c:when>
												</c:choose>
											</c:if>
										</c:forEach>
									</tr>
<!-- 									<tr> -->
<!-- 										<th>合約內容</th> -->
<%-- 										<td><%=conVO.getCon_content()%></td> --%>
<!-- 									</tr	> -->
								</tbody>
							</table>
							
						</div>
					</div>
				</div>

			</div>
		</div>
		
		<div class="row " style="margin-top:15px">
		<div class="card" 	>
					<div class="card-header" >
						<div class="text-center" style="background-color: #E8F8F5">
							<b style="font-size: 60px; color:#E74C3C">房   屋   租   賃   契   約   書</b>
						</div>
					</div>
					
					<div class="card-body">
						<div style="font-size:20px">
						<div>
						立契約書人：出租人  <input class="btn btn-outline-light">（以下簡稱甲方）、
						承租人     <input class="btn btn-outline-light">（以下簡稱乙方），茲為房屋一部租賃、雙方議定契約條款如下：<br>
						<br>
						第一條︰租賃房屋標示︰座落於 <input class="btn btn-outline-light">之鋼筋水泥建築洋式樓房。<br>
						<br>
						第二條︰出租部份︰廁所浴室及廚房共用（即租用一樓者共同使用一樓之衛生設備。租用二樓者共同使用二樓之衛生設備）。<br>
						<br>
						第三條︰租賃期間︰共 <input class="btn btn-outline-light">個月（即<input class="btn btn-outline-light">起
									至 <input class="btn btn-outline-light">止）期滿乙方應即無條件遷還房屋不得提出任何要求獲條件。乙方並應依規定申報戶口（包括流動戶口）。<br>
						<br>
						第四條︰房租︰每月新台幣<input class="btn btn-outline-light">元，
										<b>月繳 </b>方式繳交不得拖欠。<br>
						<br>
						第五條：押金新台幣 <input class="btn btn-outline-light">元。
						尤乙方於訂約時交付甲方收取保管，租賃期滿乙方依約遷還房屋時，由甲方無息發還，如有乙方應付甲方之款未付清時由此扣款還乙方不得異議。又租賃期間未滿時不得以任何理由請求退還押租金。<br>
						<br>
						第六條：特約事項︰<br>
								一、乙方租賃之房間應用於正當用途，如有違反法令使用、或存放危險物品，甲方得隨時終止本契約，乙方應即日遷出不得異議。<br>
								<br>
								二、租用期間應繳納之政府稅捐由甲方負擔，但每月水電費由乙方負擔與其他房客分攤，繳納後收據交由甲方保存。<br>
								<br>
								三、租賃期間原有設施及水電衛生設備乙方有使用權，但應負保管之責，如有破壞應負責修復並不得擅自增添設備，但經甲方允許者不在此限。<br>
								<br>
								四、租用期間乙方對房屋任何部份如有損失應負回復原狀（或照價賠償）之責，交還房屋時如有發現有損害時亦同。<br>
								<br>
								五、乙方承租之房間止限於乙方及其家族居住不得引進外人居住，並應注重公共道德及衛生，違者甲方得隨時終止租約，請求乙方遷出，乙方不得異議。<br>
								<br>
								上列各項條款均經雙方自願決不反悔，恐口無憑特立本契約二紙各執一份切實履行。<br>
										本契約正本二份，分由甲、乙雙方各執為憑。<br>
						</div>
								<div class="text-center">
									<p>(合約備註)</p><br>
									<textarea rows="5" cols="100" placeholder="" class="btn btn-outline-light"></textarea><br>
								</div>
								
						<div class="row">
							<div class="col-4"></div>
								<div style="font-size: 20px;" class="text-left class="col-4"">
									立契約書人  <br><br>
									甲            方：<input class="btn btn-outline-light"><br>
									身分證字號：<input class="btn btn-outline-light"><br>
			
									乙            方： <input class="btn btn-outline-light"><br>
									身份證字號：<input class="btn btn-outline-light"><br>
			
									簽約日：<input class="btn btn-outline-light"><br>
									
								</div>
							<div class="col-4"></div>
						</div>
						
						</div>
					</div>
					
					
				</div>
			
		
		</div>
		
	</div>
	<jsp:include page="/BackHeaderFooter/Footer.jsp" />
</body>
</html>