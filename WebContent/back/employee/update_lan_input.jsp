<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goodhouse.landlord.model.*"%>    
<%@ page import="com.sun.org.apache.xerces.internal.impl.dv.util.Base64" %>
<%
  LanVO lanVO = (LanVO) request.getAttribute("lanVO"); 
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>房東資料修改 - update_lan_input.jsp</title>
<style>
 #table1 { 

  font-family: 微軟正黑體; 
  font-size:16px; 
  width:500px;
  text-align:center;
  margin-left:auto; 
  margin-right:auto;
} 
 #table1 th { 
  background-color: #009FCC;
  padding:10px;

  color:#fff;
} 
 #table1 td { 
  padding:5px;
} 


</style>
</head>
<body>
<jsp:include page="/BackHeaderFooter/Header.jsp"/>
<div class="container">
  <div class="row">
    <div class="col-8">
    	<FORM METHOD="post" ACTION="lan.do" name="form1" enctype="multipart/form-data">
			<table id="table1">
				<tr>
					<td>房東編號:<font color=red><b>*</b></font></td>
					<td><%=lanVO.getLan_id()%></td>
				</tr>
			
			
				<tr>
					<td>會員編號:<font color=red><b>*</b></font></td>
					<td><input type="TEXT" name="mem_id" size="45" value="<%=lanVO.getMem_id()%>"></td>
				</tr>
				<tr>
					<td>房東發票:</td>
					<td>
						<select name="lan_receipt">
							<option  value="1">慈善機構</option>
							<option  value="2" selected>自動兌獎(中獎匯款)</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>房東戶頭:</td>
					<td><input type="TEXT" name="lan_account" size="45"	value="<%=lanVO.getLan_account()%>" /></td>
				</tr>
			
				
				<tr>
					<td>房東帳戶狀態:</td>
					<td>
						<select name="lan_accountstatus">
							<option  value="1" selected>審核中</option>
							<option  value="2" >已審核</option>
						</select>
					</td>
				</tr>
				
				<tr>
					<td>良民證:</td>
					<td>
						<input type="file" name="lan_ciziten" size="45"	/>
						<%
		    				byte b[] = null;
		    				String encoding = null;
		    				b = lanVO.getLan_ciziten();
		    				encoding = Base64.encode(b);
		    			%>
	    				<img id="bc" src="data:image/jpg;base64,<%=encoding %>" width="100px" height="100px">
					</td>
				</tr>
		<tr>
			<td></td>
				<td>
			<input type="hidden" name="action" value="update">
			<input type="hidden" name="lan_id" value="<%=lanVO.getLan_id()%>">
			<input type="submit" class="btn btn-secondary" value="送出修改">
				</td>
		</tr>
			
			</table>
			<br>
		
		
		</FORM>
    </div>
  </div>
</div>
<jsp:include page="/BackHeaderFooter/Footer.jsp"/>
</body>
</html>