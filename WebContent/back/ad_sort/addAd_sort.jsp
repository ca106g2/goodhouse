<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.ad_sort.model.*"%>
<%
	Ad_sortVO ad_sortVO = (Ad_sortVO) request.getAttribute("ad_sortVO");
%>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body bgcolor='white' span style="font-family:Microsoft JhengHei;">
<jsp:include page="/BackHeaderFooter/Header.jsp" />	

	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<form method="post" action="ad_sor.do" name="form1">
 
<table class="table">
  <thead>
    <tr>
      <th scope="col">廣告類型</th>
      <th scope="col">廣告費用型態</th>
      <th scope="col">費用</th>
      <th scope="col"></th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><input type="text" name="ad_forfree" size="45" value="<%=(ad_sortVO == null) ? "年費型" : ad_sortVO.getAd_forfree()%>"></td>
      <td><input type="text" name="ad_chargetype" size="45" value="<%=(ad_sortVO == null) ? "年計費" : ad_sortVO.getAd_chargetype()%>"></td>
      <td><input type="text" name="ad_charge_vr" size="45" value="<%=(ad_sortVO == null) ? "24000" : ad_sortVO.getAd_charge()%>"></td>
      <td>
      		<input type="hidden" name="action" value="insert" /> 
      		<input type="submit" class="btn btn-secondary" value="送出新增" />
      </td>
    </tr>
  </tbody>
</table>
	</form>
	<jsp:include page="/BackHeaderFooter/Footer.jsp" />	
</body>
</html>