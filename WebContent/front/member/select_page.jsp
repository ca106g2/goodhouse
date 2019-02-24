<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mem :Home</title>

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
</style >

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>IBM Mem: Home</h3><h4></h4></td></tr>
</table>

<h3>會員資料查詢:</h3>

<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllMem.jsp'>List</a> all Mems.  <br><br></li>

  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/employee/emp.do" >
        <b>輸入會員編號 (如:M000000001):</b>
        <input type="text" name="mem_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="memSvc" scope="page" class="com.goodhouse.member.model.MemService" />
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/employee/emp.do" >
       <b>選擇會員編號:</b>
       <select size="1" name="mem_id">
         <c:forEach var="memVO" items="${memSvc.all}" > 
          <option value="${memVO.mem_id}">${memVO.mem_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  	
<h3>會員管理</h3>

<ul>
  <li><a href='addMem.jsp'>Add</a> a new Mem.</li>
</ul>

</body>
</html>