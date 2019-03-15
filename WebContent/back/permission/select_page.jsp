<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Per : Home</title>
</head>
<body>

<table id="table-1">
   <tr><td><h3>IBM Per: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<h3>資料查詢:</h3>

<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllPer.jsp'>List</a> all Pers.  <br><br></li>

 <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back/permission/per.do" >
        <b>輸入權限編號 (如:P000000001):</b>
        <input type="text" name="per_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

<jsp:useBean id="perSvc" scope="page" class="com.goodhouse.permission.model.PerService" />

<li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back/permission/per.do" >
       <b>選擇權限編號:</b>
       <select size="1" name="per_id">
         <c:forEach var="perVO" items="${perSvc.all}" > 
          <option value="${perVO.per_id}">${perVO.per_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>

  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back/permission/per.do" >
       <b>選擇權限名稱:</b>
       <select size="1" name="per_id">
         <c:forEach var="perVO" items="${perSvc.all}" > 
          <option value="${perVO.per_id}">${perVO.per_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>

h3>員工管理</h3>

<ul>
  <li><a href='addPer.jsp'>Add</a> a new Per.</li>
</ul>

</body>
</html>