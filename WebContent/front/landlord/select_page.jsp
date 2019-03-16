<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>      

<html>
<head>
<meta charset="UTF-8">
<title>Lan :Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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
</style>
</head>
<body bgcolor='white'>
<jsp:include page="/FrontHeaderFooter/Header.jsp"/>
<table id="table-1">
   <tr><td><h3>房東首頁</h3></td>
      </tr>
</table>
	<h4><a href="<%=request.getContextPath()%>/fornt/member/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
<h3>資料查詢:</h3>
<%-- 錯誤表列 --%>

<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/landlord/lan.do" >
        <b>輸入房東編號 (如:L000000001):</b>
        <input type="text" name="lan_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="lanSvc" scope="page" class="com.goodhouse.landlord.model.LanService" />
   

  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/landlord/lan.do" >
       <b>選擇房東編號:</b>
       <select size="1" name="Lan_id">
         <c:forEach var="lanVO" items="${lanSvc.all}" > 
          <option value="${lanVO.lan_id}">${lanVO.lan_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>

h3>房東管理</h3>

<ul>
  <li><a href='addLan.jsp'>申請成為房東</a> a new Lan.</li>
</ul>
<jsp:include page="/FrontHeaderFooter/Footer.jsp"/>
</body>
</html>