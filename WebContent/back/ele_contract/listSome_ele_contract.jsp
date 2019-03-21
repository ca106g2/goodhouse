<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.goodhouse.ele_contract.model.*"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="com.goodhouse.member.model.*"%>
<%@ page import="com.goodhouse.contract.model.*"%>
<%@ page import="com.goodhouse.house.model.*"%>
<%@ page import="com.goodhouse.landlord.model.*"%>


<!doctype html>
<html lang="en">
<head>

</head>
<body>
	<jsp:include page="/BackHeaderFooter/Header.jsp" />

	<!-- 工作區開始 -->
	
	<div class="container">
		<div class="row justfy-content-center">
			<div class="row col-12">
				<table id="table-1">
					<p>回電子合約列表<a href="<%=request.getContextPath()%>/back/ele_contract/listAll_ele_contract.jsp">
					<img src="<%=request.getContextPath()%>/share_pic/back1.gif" width="100	" height="30 !important" ></a></p>
				</table>
				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color:red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color:red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
			</div>
			<div class="row col-12	" >
				<table>
					<tr>
						<td>電子合約編號</td>
						<td>合約分類名稱</td>
						<td>會員編號</td>
						<td>會員身份證字號</td>
						<td>房東編號</td>
						<td>房東身份證字號</td>
						<td>房屋編號</td>
						<td>每期租金</td>
						<td>押金</td>
						<td>租賃期限</td>
						<td>租賃起訖日</td>
						<td>租賃結束日</td>
						<td>簽約日期</td>
						<td>合約狀態</td>
						<td>繳費型態</td>
						<td>備註</td>
						
					</tr>
						
						<%
							List<Ele_ContractVO> list = (List<Ele_ContractVO>) session.getAttribute("list");
							
							ContractService conSvc = new ContractService();
							MemService mSvc = new MemService();
							HouseService houSvc = new HouseService();
							LanService lanSvc = new LanService();
							
							for(int i = 0 ; i < list.size() ; i++){
								Ele_ContractVO eleConVO = list.get(i);
								String ele_con_id = eleConVO.getEle_con_id();
								String con_id = eleConVO.getCon_id();
								String mem_id = eleConVO.getMem_id();
								String mem_idnumber = eleConVO.getMem_idnumber();
								String lan_id = eleConVO.getLan_id();
								String lan_idnumber = eleConVO.getLan_idnumber();
								String hou_id = eleConVO.getHou_id();
								Integer ele_rent_money = eleConVO.getEle_rent_money();
								Integer ele_deposit_money = eleConVO.getEle_deposit_money();
								Integer ele_rent_time = eleConVO.getEle_rent_time();
								Date ele_rent_f_day = eleConVO.getEle_rent_f_day();
								Date ele_rent_l_day = eleConVO.getEle_rent_l_day();
								Date ele_singdate = eleConVO.getEle_singdate();
								String ele_con_status = eleConVO.getEle_con_status();
								String bill_paymenttype = eleConVO.getBill_paymenttype();
								String ele_con_note = eleConVO.getEle_con_note();
								
						%>
							<tr>
								<td><%=ele_con_id%></td>
								<td><%=conSvc.getOneCon(con_id).getCon_name()%></td>
								<td><%=mSvc.getOneMem(mem_id).getMem_name()%></td>
								<td><%=mem_idnumber%></td>
								<td><%=mSvc.getOneMem(lanSvc.getOneLan(lan_id).getMem_id()).getMem_name()%></td>
								<td><%=lan_idnumber%></td>
								<td><%=houSvc.getOneHouse(hou_id).getHou_name()%></td>
								<td><%=ele_rent_money%></td>
								<td><%=ele_deposit_money%></td>
								<td><%=ele_rent_time%></td>
								<td><%=ele_rent_f_day%></td>
								<td><%=ele_rent_l_day%></td>
								<td><%=ele_singdate%></td>
								<td><%=ele_con_status%></td>
								<td><%=bill_paymenttype%></td>
								<td><%=ele_con_note%></td>
								
								<td>
									<form method="post" action="<%=request.getContextPath()%>/back/ele_contract/ele_contract.do" style="margin-bottom:0px;">
										<input type="submit" value="查看">
										<input type="hidden" name="ele_con_id" value="<%=ele_con_id%>">
										<input type="hidden" name="action" value="getOne_For_Display">
									</form>
								</td>
							</tr>
						<% 
							}
						%>
				</table>
			</div>
				
		</div>
	</div>
	
	<!-- 工作區結束 -->
	<jsp:include page="/BackHeaderFooter/Footer.jsp" />
</body>
</html>