<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Appoint: Home</title>

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
   <tr><td><h3>IBM Appoint: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Appoint: Home</p>

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
  <li><a href='listAllAppoint.jsp'>List</a> all Appoint.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="appoint.do" >
        <b>輸入預約行程表編號 (如APP0000001):</b>
        <input type="text" name="appoint_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="appointSvc" scope="page" class="com.goodhouse.appointment.model.AppointService" />
   
  <li>
     <FORM METHOD="post" ACTION="appoint.do" >
       <b>選擇預約行程表息編號:</b>
       <select size="1" name="appoint_id">
         <c:forEach var="appointVO" items="${appointSvc.all}" > 
          <option value="${appointVO.appoint_id}">${appointVO.appoint_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="appoint.do" >
       <b>選擇房屋編號:</b>
       <select size="1" name="appoint_id">
         <c:forEach var="appointVO" items="${appointSvc.all}" > 
          <option value="${appointVO.appoint_id}">${appointVO.hou_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>預約行程表管理</h3>

<ul>
  <li><a href='addAppoint.jsp'>Add</a> a new Appoint.</li>
</ul>

</body>
</html>