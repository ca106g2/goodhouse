<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.contract.model.*"%>
<jsp:useBean id="conSvc" scope="page"
	class="com.goodhouse.contract.model.ContractService" />

<%
	ContractVO conVO = (ContractVO) request.getAttribute("conVO");
	pageContext.setAttribute("conVO", conVO);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
	#back{
		position:fixed;
		top: 20%;
		z-index: 994;
		left: 4%;
	}
</style>
</head>
<body>
	<jsp:include page="/BackHeaderFooter/Header.jsp" />

	<div class="container">
		<div class="row justify-content-center">
			<div class="" id="back">
				<h4>
					<a
						href="<%=request.getContextPath()%>/back/contract/listAll_contract.jsp">
						<img src="<%=request.getContextPath()%>/images/back.png"
						width="100" height="100" border="0">
					</a>
				</h4>
			</div>
				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<c:forEach var="message" items="${errorMsgs}">
						<p style="color: red">${message}</p><br>
						<br>
					</c:forEach>
				</c:if>
		</div>
		<div class="row justify-content-center">
			<div class="col-2"></div>
			<div class="col-10">
				<div class="card">
					<div class="card-body">
						<div class="table-responsive">
							<form name="the_form" method="post" action="contract.do"
								class="text-center">
								<table
									class="table table-bordered table-hover mb-0 text-nowrap text-center h3">
									<tbody>
										<tr>
											<td>合約分類名稱</td>
											<br>
											<td><input type="text" name="con_name"
												value="${conVO.con_name}" class="btn btn-light h3"></td>
										</tr>
										<tr>
											<td>合約使用狀態</td>
											<br>
											<c:forEach var="con_status" items="${Con_statusList}">
												<c:if
													test="${con_status.status_no_name eq conVO.con_status}">
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
									</tbody>
								</table>
								<input type="hidden" name="action" value="update"> 
								<input type="hidden" name="con_id" value="${conVO.con_id}"> 
								<input type="hidden" name="con_content" value="${conVO.con_content}" id="con_content"> 
								<input type="hidden" name="con_status" value="${conVO.con_status}"> 
								<input type="submit" value="送出修改" class="btn btn-outline-info" style="margin-top: 15px;font-weight:bold;font-size:20px">
							</form>
						</div>
					</div>
				</div>
			</div>

			<div class="" style="margin-top: 15px">
				<div class="card" style="background-color: #E8F8F5">
					<div class="card-body">
						<textarea name="con_content" value=""
							class="btn btn-light text-left" rows="50" cols="180"
							placeholder="" id="conContent">
								                           	   房   屋   租   賃   契   約   書
								                            
			立契約書人：	出租人                                    （以下簡稱甲方）、承租人                                   （以下簡稱乙方），茲為房屋一部租賃、雙方議定契約條款如下：
						
			第一條︰租賃房屋標示︰座落於                                                                                                       之鋼筋水泥建築洋式樓房。
						
			第二條︰出租部份︰廁所浴室及廚房共用（即租用一樓者共同使用一樓之衛生設備。租用二樓者共同使用二樓之衛生設備）。
						
			第三條︰租賃期間︰
				共                      個月（即                       起 至                             止）期滿乙方應即無條件遷還房屋不得提出任何要求獲條件。
				乙方並應依規定申報戶口（包括流動戶口）。
						
			第四條︰房租︰每月新台幣                                         元，月繳 方式繳交不得拖欠。
						
			第五條：押金新台幣				元。
			
				尤乙方於訂約時交付甲方收取保管，租賃期滿乙方依約遷還房屋時，由甲方無息發還，	如有乙方應付甲方之款未付清時由此扣款還乙方不得異議。
				又租賃期間未滿時不得以任何理由請求退還押租金。
						
			第六條：特約事項︰
			
				一、乙方租賃之房間應用於正當用途，如有違反法令使用、或存放危險物品，甲方得隨時終止本契約，乙方應即日遷出不得異議。
								
				二、租用期間應繳納之政府稅捐由甲方負擔，但每月水電費由乙方負擔與其他房客分攤，繳納後收據交由甲方保存。
								
				三、租賃期間原有設施及水電衛生設備乙方有使用權，但應負保管之責，如有破壞應負責修復並不得擅自增添設備，但經甲方允許者不在此限。
								
				四、租用期間乙方對房屋任何部份如有損失應負回復原狀（或照價賠償）之責，交還房屋時如有發現有損害時亦同。
								
				五、乙方承租之房間止限於乙方及其家族居住不得引進外人居住，並應注重公共道德及衛生，違者甲方得隨時終止租約，請求乙方遷出，乙方不得異議。
								
				上列各項條款均經雙方自願決不反悔，恐口無憑特立本契約二紙各執一份切實履行。
				
				本契約正本二份，分由甲、乙雙方各執為憑。
				
				合約備註：
									
									
								
										立契約書人
										
										 甲            方：______________________________________
										身分證字號：______________________________________
			
										乙            方： ______________________________________
										身份證字號：______________________________________
			
										簽約日：
									
			
			</textarea>
					</div>
				</div>
			</div>
		</div>
	</div>
		<jsp:include page="/BackHeaderFooter/Footer.jsp" />

		<script type="text/javascript">
			
			var contractName = ${conVO.con_name};
			
			$('#conContent').change(function(){
				
				if(contractName == '套房合約' ){
					$('#con_content').val('套房合約內容');
				}else if(contractName == '雅房合約'){
					$('#con_content').val('雅房合約內容');
				}else if(contractName == '公寓合約'){
					$('#con_content').val('雅房合約內容');
				}else if(contractName == '大樓合約'){
					$('#con_content').val('大樓合約內容');
				}else if(contractName == '透天合約'){
					$('#con_content').val('透天合約內容');
				}
				
			};
		
		</script>
</body>
</html>