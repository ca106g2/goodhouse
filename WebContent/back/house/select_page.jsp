<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>House: Home</title>

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
   <tr><td><h3>goodhouse House: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for goodhouse House: Home</p>

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
  <li><a href='listAllHouse.jsp'>List</a> all Houses.  <br><br></li>
  <!-- .........start........... -->
  
    <li>
    <FORM METHOD="post" ACTION="hou.do" >
        <b></b>
        <input type="text" name="hou_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="houSvc" scope="page" class="com.goodhouse.house.model.HouseService" />
   
  <li>
     <FORM METHOD="post" ACTION="hou.do" >
      <b>房屋編號:</b>
       <select size="1" name="hou_id">
         <c:forEach var="houVO" items="${houSvc.all}" > 
          <option value="${houVO.hou_id}">${houVO.hou_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
<!--   <li>
     <FORM METHOD="post" ACTION="hou.do" >
       <b>房屋名稱:</b>
       <select size="1" name="hou_id">
        // <c:forEach var="houVO" items="${houSvc.all}" > 
         // <option value="${houVO.hou_name}">${houVO.hou_name}
         //</c:forEach>   
        </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li> -->
 </ul>  
  
  
  
    <!-- .........end........... -->

  

<h3>房屋管理</h3>

<ul>
  <li><a href='addHouse.jsp'>Add</a> a new House.</li>
</ul>

</body>
</html>