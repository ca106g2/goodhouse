<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GoodHouse Contract：Home</title>

<style>
  table#table-1 {
	width: 800px;
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
<jsp:include page="/FrontHeaderFooter/Header.jsp" />

	<table id="table-1">
	   <tr>
	   	<td>
	   		<h3>GoodHouse Contract: Home</h3><h4>( MVC )</h4>
	   	</td>
	   </tr>
	</table>

	<p>This is the Home page for GoodHouse Contract: Home</p>

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
	  <li><a href='listAll_contract.jsp'>List</a> all Contract  <br><br></li>
	  
	  <jsp:useBean id="conSvc" scope="page" class="com.goodhouse.contract.model.ContractService" />
	   
	  
	  <li>
	     <FORM METHOD="post" ACTION="contract.do" >
	       <b>選擇合約分類名稱:</b>
	       <select size="1" name="con_id">
	         <c:forEach var="conVO" items="${conSvc.all}" > 
	          	<option value="${conVO.con_id}">${conVO.con_name}
	         </c:forEach>   
	       </select>
	       <input type="hidden" name="action" value="getOne_For_Display">
	       <input type="submit" value="送出">
	     </FORM>
	  </li>
	</ul>

	<ul>
	  <li><a href='add_contract.jsp'>Add</a>新增合約分類</li>
	</ul>

</body>
</html>