<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>


<body bgcolor='white'>
<table id="table-1">
   <tr><td><h3>GoodHouse emp_per: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for GoodHouse emp_per: Home</p>

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
  <li><a href='listAllEmp_Per.jsp'>List</a> all empAuth.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="emp_per.do" >
        <b>輸入員工ID :</b>
        <input type="text" name="emp_id">
        <input type="hidden" name="action" value="getSome_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="emp_perSvc" scope="page" class="com.goodhouse.employee_permission.model.Emp_PerService" />
   
  <li>
     <FORM METHOD="post" ACTION="emp_per.do" >
       <b>選擇員工ID:</b>
       <select size="1" name="emp_id">
         <c:forEach var="emp_perVO" items="${emp_perSvc.all}" > 
          <option value="${emp_perVO.getEmp_id()}">${emp_perVO.getEmp_id()}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getSome_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <jsp:useBean id="PerSvc" scope="page" class="com.goodhouse.permission.model.PerService" />
  <li>
     <FORM METHOD="post" ACTION="per.do" >
       <b>選擇權限:</b>
       <select size="1" name="per_id">
         <c:forEach var="PerVO" items="${PerSvc.all}" > 
          <option value="${PerVO.per_id}">${PerVO.per_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getSome_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>   
  
<!--   <li> -->
<!--      <FORM METHOD="post" ACTION="empAuth.do" > -->
<!--        <b>選擇員工權限ID:</b> -->
<!--        <select size="1" name="prod_ID"> -->
<%--          <c:forEach var="empAuthVO" items="${empAuthSvc.all}" >  --%>
<%--           <option value="${empAuthVO.prod_ID}">${empAuthVO.prod_ID} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="hidden" name="action" value="getSome_For_Display"> -->
<!--        <input type="submit" value="送出"> -->
<!--      </FORM> -->
<!--   </li> -->
</ul>


<h3>員工權限管理</h3>

<ul>
  <li><a href='addEmp_Per.jsp'>Add</a> a new emp_per.</li>
</ul>
  
</body>
</html>