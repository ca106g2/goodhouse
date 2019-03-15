<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.goodhouse.ele_contract.model.*"%>
<%@ page import="com.goodhouse.contract.model.*" %>
<%@ page import="com.goodhouse.member.model.*" %>
<%@ page import="com.goodhouse.landlord.model.*" %>
<%@ page import="com.goodhouse.house.model.*" %>
<%
	ContractVO conVO = (ContractVO) session.getAttribute("conVO");
	Ele_ContractVO eleConVO = (Ele_ContractVO) request.getAttribute("eleConVO");
	MemVO mVO = (MemVO) session.getAttribute("mVO");
%>

<!doctype html>
<html lang="en">
<head>
<style>
	  table#table-1 {
		background-color: #CCCCFF;
	    border: 2px solid black;
	    text-align: center;
	  }
	  table#table-1 h4 {
	    color: red;
	    display: block;
	    margin-bottom: 1px;
	  }
	  h4 {
	    color: blue;
	    display: inline;
	  }
	  
	  table {
		width: 450px;
		background-color: white;
		margin-top: 1px;
		margin-bottom: 1px;
	  }
	  table, th, td {
	    border: 0px solid #CCCCFF;
	  }
	  th, td {
	    padding: 1px;
	  }
</style>
<title></title>
</head>
<body>
	<jsp:include page="/FrontHeaderFooter/Header.jsp" />
	<h1></h1>

	<!-- 工作區開始 -->

<div class="container">
	<div class="row justfy-content-center">
		<div class="row col-12 justfy-content-center" >
			<table id="table-1">
				<tr>
					<td>
						<h3>新增電子合約 - add_ele_contract.jsp</h3>
						<h4>
						 <a href="lan_select_page.jsp"><img src="<%=request.getContextPath()%>/share_pic/back1.gif" width="100" height="32" border="0">回首頁</a>
						</h4>
					</td>
				</tr>
				<tr>
					<td>
						<%-- 錯誤表列 --%>
						<c:if test="${not empty errorMsgs}">
							<font style="color:red">請修正以下錯誤:</font>
							<c:forEach var="message" items="${errorMsgs}">
								<b style="color:red">${message}</b>
							</c:forEach>
						</c:if>
					</td>
				</tr>
			</table>
			
				<p type="hidden" name="con_content" value="<%=conVO.getCon_content()%>" >
				<form method="post" action="ele_contract.do" name="form1">
				<jsp:useBean id="houSvc" scope="page" class="com.goodhouse.house.model.HouseService"/>
												<div>房   屋   租   賃   契   約   書</div>
				<%
					String lan_id= null;
					LanService lanSvc = new LanService();
					lan_id = lanSvc.getOneLanByMemId(mVO.getMem_id()).getLan_id();
				%>
				立契約書人：出租人   <b><%=mVO.getMem_name()%></b>（以下簡稱甲方）、
				承租人       <b><input type="text" name="mem_name" class="btn btn-light"/></b> （以下簡稱乙方），茲為房屋一部租賃、雙方議定契約條款如下：<br>
				第一條︰租賃房屋地址︰
				<div class="form-group">
				<label for="exampleFormControlSelect1"></label>
					<select size="1" name="hou_id" style="overflow:hidden; text-overflow:ellipsis;white-space:nowrap;width:210px;" class="form-control" id="exampleFormControlSelect1">
						<c:forEach var="houVO" items="${houSvc.all}">
							<c:if test="${houVO.lan_id eq lan_id }">
								<b><option value="${houVO.hou_id}" class=" form-control btn btn-light"/>${houVO.hou_address}</b>
							</c:if>
						</c:forEach>
					</select><br>
				</div>
				第二條︰出租部份︰廁所浴室及廚房共用（即租用一樓者共同使用一樓之衛生設備。租用二樓者共同使用二樓之衛生設備）。<br>			
				第三條︰租賃期間︰共<b><input type="text" name="ele_rent_time" value="<%=(eleConVO==null)? "" : eleConVO.getEle_rent_time()%>" class="btn btn-light"/></b>個月
								（即<b><input type="text" name="ele_rent_f_day" id="ele_rent_f_day" value="<%=(eleConVO==null)? "" : eleConVO.getEle_rent_f_day()%>" class="btn btn-light"/></b>起
								  至<b><input type="text" name="ele_rent_l_day" id="ele_rent_l_day" value="<%=(eleConVO==null)? "" : eleConVO.getEle_rent_l_day()%>" class="btn btn-light"/></b>止）期滿乙方應即無條件遷還房屋不得提出任何要求獲條件。乙方並應依規定申報戶口（包括流動戶口）。<br>
				第四條︰房租︰每月新台幣  <b><input type="text" name="ele_rent_money" value="<%=(eleConVO==null)? "" : eleConVO.getEle_rent_money()%>" class="btn btn-light"/></b> 元，${Bill_PaymentTypeMap['p1'].type_name}方式繳交不得拖欠。<br>
				第五條：押金新台幣  <b><input type="text" name="ele_deposit_money" value="<%=(eleConVO==null)? "" : eleConVO.getEle_deposit_money()%>" class="btn btn-light"/> </b> 元。尤乙方於訂約時交付甲方收取保管，租賃期滿乙方依約遷還房屋時，由甲方無息發還，如有乙方應付甲方之款未付清時由此扣款還乙方不得異議。又租賃期間未滿時不得以任何理由請求退還押租金。<br>
				第六條：特約事項︰<br>
					一、乙方租賃之房間應用於正當用途，如有違反法令使用、或存放危險物品，甲方得隨時終止本契約，乙方應即日遷出不得異議。<br>
					二、租用期間應繳納之政府稅捐由甲方負擔，但每月水電費由乙方負擔與其他房客分攤，繳納後收據交由甲方保存。<br>
					三、租賃期間原有設施及水電衛生設備乙方有使用權，但應負保管之責，如有破壞應負責修復並不得擅自增添設備，但經甲方允許者不在此限。<br>
					四、租用期間乙方對房屋任何部份如有損失應負回復原狀（或照價賠償）之責，交還房屋時如有發現有損害時亦同。<br>
					五、乙方承租之房間止限於乙方及其家族居住不得引進外人居住，並應注重公共道德及衛生，違者甲方得隨時終止租約，請求乙方遷出，乙方不得異議。<br>
					上列各項條款均經雙方自願決不反悔，恐口無憑特立本契約二紙各執一份切實履行。<br>
							本契約正本二份，分由甲、乙雙方各執為憑。<br>
				<p>合約備註</p>
				<textarea name="ele_con_note" rows="3" cols="30" value="<%=(eleConVO==null)? "" : eleConVO.getEle_con_note()%>" class="btn btn-light"><%=(eleConVO==null)? "" : eleConVO.getEle_con_note()%></textarea><br>

										立契約書人  甲            方：<%=mVO.getMem_name()%><br>
												身分證字號：<input type="text" name="lan_idnumber" value="<%=(eleConVO==null)? "" : eleConVO.getLan_idnumber()%>" class="btn btn-light"/><br>

												乙            方：<input type="text" name="mem_name" class="btn btn-light"/><br>
												身份證字號：<input type="text" name="mem_idnumber" value="<%= (eleConVO==null)? "" : eleConVO.getMem_idnumber()%>" class="btn btn-light"/><br>

												簽約日：<input type="text" name="ele_singdate" id="ele_singdate" value="<%=(eleConVO==null)? "" : eleConVO.getEle_singdate()%>" class="btn btn-light"/><br>
							
						<input type="hidden" name="action" value="insert">
						<input type="hidden" name="con_id" value="<%=conVO.getCon_id()%>"/>
						<input type="hidden" name="ele_con_status" value="s1">
						<input type="hidden" name="bill_paymenttype" value="${Bill_PaymentTypeMap['p1'].type_no}"/>
						<input type="hidden" name="lan_id" value="<%=lan_id%>"/>
						
						<input type="submit" value="送出" class="btn btn-outline-secondary">
				</form>
		</div>
	</div>
</div>

<%

	java.sql.Date ele_rent_f_day = null;
	java.sql.Date ele_rent_l_day = null;
	java.sql.Date ele_singdate = null;
	
	try{
		ele_rent_f_day = eleConVO.getEle_rent_f_day();
		ele_rent_l_day = eleConVO.getEle_rent_l_day();
		ele_singdate = eleConVO.getEle_singdate();
	} catch (Exception e){
		ele_rent_f_day = new java.sql.Date(System.currentTimeMillis());
		ele_rent_l_day = new java.sql.Date(System.currentTimeMillis());
		ele_singdate = new java.sql.Date(System.currentTimeMillis());
	}

%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<script>

$.datetimepicker.setLocale('zh');
$('#ele_rent_f_day').datetimepicker({
   theme: '',              //theme: 'dark',
   timepicker:false,       //timepicker:true,
   step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
   format:'Y-m-d',         //format:'Y-m-d H:i:s',
   value: '<%=ele_rent_f_day%>', // value:   new Date(),
   //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
   //startDate:	            '2017/07/10',  // 起始日
   minDate:               '-1970-01-01', // 去除今日(不含)之前
   //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});

$('#ele_rent_l_day').datetimepicker({
	   theme: '',              //theme: 'dark',
	   timepicker:false,       //timepicker:true,
	   step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	   format:'Y-m-d',         //format:'Y-m-d H:i:s',
	   value: '<%=ele_rent_l_day%>', // value:   new Date(),
	   //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	   //startDate:	            '2017/07/10',  // 起始日
	   minDate:               '-1970-01-01', // 去除今日(不含)之前
	   //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});
	
$('#ele_singdate').datetimepicker({
	   theme: '',              //theme: 'dark',
	   timepicker:false,       //timepicker:true,
	   step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	   format:'Y-m-d',         //format:'Y-m-d H:i:s',
	   value: '<%=ele_singdate%>', // value:   new Date(),
	   //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	   //startDate:	            '2017/07/10',  // 起始日
	   minDate:               '-1970-01-01', // 去除今日(不含)之前
	   //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});

</script>

	<!-- 工作區結束 -->
<jsp:include page="/FrontHeaderFooter/Footer.jsp" />

</body>
</html>