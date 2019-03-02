<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM RentMess: Home</title>

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

<table id="table-1">
   <tr><td><h3>IBM RentMess: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM RentMess: Home</p>

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
  <li><a href='listAllRentMess.jsp'>List</a> all RentMesss.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="rentMess.do" >
        <b>輸入租屋訊息編號 (如RENM000001):</b>
        <input type="text" name="ren_mes_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="rentMessSvc" scope="page" class="com.goodhouse.rental_message.model.RentMessService" />
   
  <li>
     <FORM METHOD="post" ACTION="rentMess.do" >
       <b>選擇租屋訊息編號:</b>
       <select size="1" name="ren_mes_id">
         <c:forEach var="rentMessVO" items="${rentMessSvc.all}" > 
          <option value="${rentMessVO.ren_mes_id}">${rentMessVO.ren_mes_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="rentMess.do" >
       <b>選擇房屋編號:</b>
       <select size="1" name="ren_mes_id">
         <c:forEach var="rentMessVO" items="${rentMessSvc.all}" > 
          <option value="${rentMessVO.ren_mes_id}">${rentMessVO.hou_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>租屋留言管理</h3>

<ul>
  <li><a href='addRentMess.jsp'>Add</a> a new RentMess.</li>
</ul>

</body>
</html>