<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.goodhouse.ele_contract.model.*"%>
<%
	Ele_ContractVO eleConVO = (Ele_ContractVO) request.getAttribute("eleConVO");
%>

<!doctype html>
<html lang="en">
<head>
<style>
	#back{
		position:fixed;
		top: 20%;
		z-index: 994;
		left: 4%;
	}
</style>
<title></title>
</head>
<body>
	<jsp:include page="/FrontHeaderFooter/Header.jsp" />

	<!-- 工作區開始 -->

	<div class="container">
		
		<div id="back">
			<a href="<%=request.getContextPath()%>/front/ele_contract/lan_listAll_ele_contract.jsp">
				<img src="<%=request.getContextPath()%>/images/back.png" width="100" height="100" border="0">
			</a>
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<c:forEach var="message" items="${errorMsgs}">
					<b style="color: red">${message}</b>
					<br>
				</c:forEach>
			</c:if>
		</div>
	
	
		<div class="row justfy-content-center " style="margin-top:15px">
			<img src="<%=request.getContextPath()%>/images/magic.png" width="25" height="25" style="margin-bottom:10px" id="magic">
			<div class="card">
				<div class="card-header">
					<div class="text-center" style="">
						<b style="font-size: 60px;">房 屋 租 賃 契 約 書</b>
					</div>
				</div>

				<jsp:useBean id="houSvc" scope="page"
					class="com.goodhouse.house.model.HouseService" />
				<jsp:useBean id="memSvc" scope="page"
					class="com.goodhouse.member.model.MemService" />
				<jsp:useBean id="lanSvc" scope="page"
					class="com.goodhouse.landlord.model.LanService" />
					
				<div class="card-body">
					<p type="text" value="${conVO.con_name}">
					
					<form method="post" action="ele_contract.do" name="form1" autocomplete="off">

						<div style="font-size: 20px">

							立契約書人：出租人 <b>${memVO.mem_name}</b>（以下簡稱甲方）、 承租人 <b><input
								type="text" id="mem_name" name="mem_name" class="btn btn-light"
								value="${memSvc.getOneMem(eleConVO.mem_id).mem_name}"
								placeholder="輸入房客姓名" /></b> （以下簡稱乙方），茲為房屋一部租賃、雙方議定契約條款如下：<br>
							<br> 第一條︰租賃房屋地址︰

							<div class="form-group">
								<label for="exampleFormControlSelect1"> 
								<select size="1"
									name="hou_id"
									style="overflow: hidden; text-overflow: ellipsis; white-space: nowrap; width: 210px;"
									class="form-control " id="exampleFormControlSelect1"
									data-toggle="tooltip" data-placement="right"
									title="沒有房屋記得新增房屋喔!!">
										<c:forEach var="houVO" items="${houSvc.all}">
											<c:if test="${houVO.lan_id eq lanSvc.getOneLanByMemId(memVO.mem_id).lan_id }">
												<option id="houId" value="${houVO.hou_id}"
													class=" form-control btn btn-light" />
													${houVO.hou_address}
											</c:if>
										</c:forEach>
								</select><br>
								</label>
							</div>

							第二條︰出租部份︰廁所浴室及廚房共用（即租用一樓者共同使用一樓之衛生設備。租用二樓者共同使用二樓之衛生設備）。<br>
							<br> 
							
							第三條︰租賃期間︰共<b><input id="ele_rent_time" type="text"
								name="ele_rent_time" value="${eleConVO.ele_rent_time}"
								class="btn btn-light" placeholder="輸入總租期(共幾個月)" /></b>個月 （即<b>
								<input type="text" name="ele_rent_f_day" id="ele_rent_f_day"
								value="${eleConVO.ele_rent_f_day}" class="btn btn-light" autocomplete="off"/></b>起 至<b>
								<input type="text" name="ele_rent_l_day" id="ele_rent_l_day"
								value="${eleConVO.ele_rent_l_day}" class="btn btn-light" /></b>止）
								期滿乙方應即無條件遷還房屋不得提出任何要求獲條件。乙方並應依規定申報戶口（包括流動戶口）。<br>
							<br> 
							
<!-- 							第四條︰房租︰每月新台幣 <b id="ele_rent_money"></b> -->
<%-- 							元，${Bill_PaymentTypeMap['p1'].type_name}方式繳交不得拖欠。<br> --%>
							第四條︰房租︰每月新台幣  <b><input type="text" name="ele_rent_money" id="ele_rent_money" value="${eleConVO.ele_rent_money}" class="btn btn-light" placeholder="輸入租金"/></b> 元，${Bill_PaymentTypeMap['p1'].type_name}方式繳交不得拖欠。<br>
							<br> 
							
							第五條：押金新台幣 <b><input type="text" name="ele_deposit_money" id="ele_deposit_money"
								value="${eleConVO.ele_deposit_money}" class="btn btn-light"
								placeholder="輸入押金" /> </b>
							元。尤乙方於訂約時交付甲方收取保管，租賃期滿乙方依約遷還房屋時，由甲方無息發還，如有乙方應付甲方之款未付清時由此扣款還乙方不得異議。又租賃期間未滿時不得以任何理由請求退還押租金。<br>
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
								本契約正本二份，分由甲、乙雙方各執為憑。<br> <br>
								
							<div class="text-center">
								<p>合約備註</p>
								<textarea name="ele_con_note" rows="5" cols="100"
									value="${eleConVO.ele_con_note}" class="btn btn-light"
									placeholder="輸入其他特別協議" id="ele_con_note">${eleConVO.ele_con_note}</textarea>
								<br>
							</div>

						</div>

						<div style="font-size: 20px;" class="text-center">
							立契約書人 甲 方：<b>${memVO.mem_name}</b><br> 
							身分證字號：<b>
							<input type="text" name="lan_idnumber" value="${eleConVO.lan_idnumber}"
								class="btn btn-light" placeholder="輸入房東身分證字號" id="lan_idnumber"/>
								</b><br>
							<!-- 												乙            方：<b id="mem_name2"></b><br> -->
							乙            方：<b><input type="text" id="mem_name2" name="mem_name" class="btn btn-light" value="${memSvc.getOneMem(eleConVO.mem_id).mem_name}"/></b><br>
<!-- 							乙 方：<b><p id="mem_name2"></p></b><br>  -->
							身份證字號：<b>
							<input type="text" name="mem_idnumber" value="${eleConVO.mem_idnumber}"
								class="btn btn-light" id="mem_idnumber" placeholder="輸入房客身份證字號" />
								</b><br> 
							簽約日：<b>
							<input type="text" name="ele_singdate" id="ele_singdate"
								value="${eleConVO.ele_singdate}" class="btn btn-light" />
							</b><br>

							<input type="submit" value="送出" class="btn btn-outline-success btn-lg">
						</div>

						<input type="hidden" name="action" value="insert"> 
						<input type="hidden" name="con_id" value="${conVO.con_id}" /> 
						<input type="hidden" name="ele_con_status" value="s1"> 
						<input type="hidden" name="bill_paymenttype" value="${Bill_PaymentTypeMap['p1'].type_no}" /> 
						<input type="hidden" name="lan_id" value="${lanSvc.getOneLanByMemId(memVO.mem_id).lan_id}" />

					</form>
				</div>
			</div>
		</div>
	</div>

	<%
		java.sql.Date ele_rent_f_day = null;
		java.sql.Date ele_rent_l_day = null;
		java.sql.Date ele_singdate = null;

		try {
			ele_rent_f_day = eleConVO.getEle_rent_f_day();
			ele_rent_l_day = eleConVO.getEle_rent_l_day();
			ele_singdate = eleConVO.getEle_singdate();
		} catch (Exception e) {
			ele_rent_f_day = new java.sql.Date(System.currentTimeMillis());
			ele_rent_l_day = new java.sql.Date(System.currentTimeMillis());
			ele_singdate = new java.sql.Date(System.currentTimeMillis());
		}
	%>
	<link rel="stylesheet" type="text/css"
		href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
	<script
		src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

	<script>
	//乙方名字連動改變
// 	$('#mem_name').change(function(){
		
// 	  	$('#mem_name2').text( $('#mem_name').val() );
	  	
// 	});

	
	</script>
<script>

$.datetimepicker.setLocale('zh');

$(function(){
	$('#ele_rent_f_day').datetimepicker({
  		theme: '',              //theme: 'dark',
//    timepicker:false,       //timepicker:true,
   		step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
   		format:'Y-m-d',         //format:'Y-m-d H:i:s',
<%--    value: '<%=ele_rent_f_day%>', // value:   new Date(), --%>
   //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
   //startDate:	            '2017/07/10',  // 起始日
   		minDate:               '-1970-01-01', // 去除今日(不含)之前
   //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
   		onShow:function(){
	   this.setOptions({
	    maxDate:$('#ele_rent_l_day').val() ? $('#ele_rent_l_day').val():false
	   })
	  },
	  timepicker:false
   
	});

	$('#ele_rent_l_day').datetimepicker({
	   theme: '',              //theme: 'dark',
// 	   timepicker:false,       //timepicker:true,
	   step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	   format:'Y-m-d',         //format:'Y-m-d H:i:s',
<%-- 	   value: '<%=ele_rent_l_day%>', // value:   new Date(), --%>
	   //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	   //startDate:	            '2017/07/10',  // 起始日
	   minDate:               '-1970-01-01', // 去除今日(不含)之前
	   //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	   onShow:function(){
		   this.setOptions({
		    minDate:$('#ele_rent_f_day').val() ? $('#ele_rent_f_day').val():false
		   })
		  },
		  timepicker:false
	   
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
});
</script>
<script > 


$('#magic').click(function(){
	
	$('#mem_name').val('PETER');
	$('#mem_name2').val('PETER');
	$('#ele_rent_time').val('6');
// 	$('#ele_rent_money').val('');
// 	$('#ele_deposit_money').val('');
	$('#lan_idnumber').val('P125478456');
	$('#mem_idnumber').val('A125487953');
	
})
 </script> 
<script>

$('#exampleFormControlSelect1').change(function(){
	
	var houId = $('#exampleFormControlSelect1').val();
	console.log(houId);
	
	$.ajax({

			type: "POST",
			url: "<%=request.getContextPath()%>/front/ele_contract/ele_contract.do",
			data: {
				"hou_id":houId,
				"action":"lookHou"
				},
				
			dataType: "json",
			
			success: function(data){
				
					var hou_rent = data.hou_rent;
					
					$('#ele_rent_money').val(hou_rent);
					
				},
			error: function(){alert("AJAX發生錯誤")}
			
				});
})



</script> 

	<!-- 工作區結束 -->
	<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
	
</body>
</html>