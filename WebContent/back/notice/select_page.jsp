<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Notice: Home</title>

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
   <tr><td><h3>IBM Notice: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Notice: Home</p>

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
  <li><a href='listAllNotice.jsp'>List</a> all Notice.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="notice.do" >
        <b>輸入預約行程表編號 (如NOT0000001):</b>
        <input type="text" name="notice_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="noticeSvc" scope="page" class="com.goodhouse.notice.model.NoticeService" />
   
  <li>
     <FORM METHOD="post" ACTION="notice.do" >
       <b>選擇通知事項編號:</b>
       <select size="1" name="notice_id">
         <c:forEach var="noticeVO" items="${noticeSvc.all}" > 
          <option value="${noticeVO.notice_id}">${noticeVO.notice_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="notice.do" >
       <b>選擇會員編號:</b>
       <select size="1" name="notice_id">
         <c:forEach var="noticeVO" items="${noticeSvc.all}" > 
          <option value="${noticeVO.notice_id}">${noticeVO.mem_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>通知事項管理</h3>

<ul>
  <li><a href='addNotice.jsp'>Add</a> a new Notice.</li>
</ul>

</body>
</html>