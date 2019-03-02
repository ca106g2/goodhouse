<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM EquRep: Home</title>

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
   <tr><td><h3>IBM EquRep: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM EquRep: Home</p>

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
  <li><a href='listAllEquRep.jsp'>List</a> all EquReps.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="equRep.do" >
        <b>輸入設備維修編號 (如EQU0000001):</b>
        <input type="text" name="equ_rep_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="equRepSvc" scope="page" class="com.goodhouse.equipment_repair.model.EquRepService" />
   
  <li>
     <FORM METHOD="post" ACTION="equRep.do" >
       <b>選擇設備維修編號:</b>
       <select size="1" name="equ_rep_id">
         <c:forEach var="equRepVO" items="${equRepSvc.all}" > 
          <option value="${equRepVO.equ_rep_id}">${equRepVO.equ_rep_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="equRep.do" >
       <b>選擇房屋編號:</b>
       <select size="1" name="equ_rep_id">
         <c:forEach var="equRepVO" items="${equRepSvc.all}" > 
          <option value="${equRepVO.equ_rep_id}">${equRepVO.hou_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>



<h3>設備維修管理</h3>

<ul>
  <li><a href='addEquRep.jsp'>Add</a> a new EquRep.</li>
</ul>

</body>
</html>


<!-- 	 <FORM action="equRep.do" method=post enctype="multipart/form-data">
        <input type="file" name="upfile">
        <br>
        <input type="submit" value="上傳">
        <br>
        <input type="hidden" name="action" value="getOne_For_Display">
  	</FORM>
 -->