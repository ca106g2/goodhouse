<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Emp: Home</title>

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
<jsp:include page="/BackHeaderFooter/Header.jsp"/>
<table id="table-1">
   <tr><td><h3>後台員工管理</h3></td></tr>
</table>



<h3>員工資料查詢</h3>
	
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
  <li><a href='listAllEmp.jsp'>全部員工資料</a> all Emps.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back/employee/emp.do" >
        <b>輸入員工編號 (如:E000000001):</b>
        <input type="text" name="emp_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="empSvc" scope="page" class="com.goodhouse.employee.model.EmpService" />
  
<!--   <li> -->
<%--     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back/employee/emp.do" > --%>
        
<!--         <b>輸入員工姓名 :</b> -->
<!--         <input type="text" name="emp_name"> -->
<!--         <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--         <input type="submit" value="送出"> -->
<!--     </FORM> -->
<!--   </li> -->
  

</ul>


<h3>新增員工</h3>

<ul>
  <li><a href='addEmp.jsp'>新增員工資料</a> a new Employee.</li>
</ul>



<h3>會員管理</h3>
<ul>
  <li><a href='<%=request.getContextPath()%>/back/employee/listAllMem_emp.jsp'>全部會員資料</a> a new Member.</li>
</ul>

<h3>房東管理</h3>
<ul>
  <li><a href='<%=request.getContextPath()%>/front/landlord/listAllLan.jsp'>全部房東資料</a> a new Landlord.</li>
</ul>
<ul>
  <li><a href='<%=request.getContextPath()%>/back/employee/checklan.jsp'>審核房東資料</a> a new Landlord.</li>
</ul>



<form method="post" action="<%=request.getContextPath()%>/BackLogoutHandler">
	<input type="submit" value="登出">
</form>

<jsp:include page="/BackHeaderFooter/Footer.jsp"/>
</body>
</html>