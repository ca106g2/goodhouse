<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.* , com.goodhouse.member.model.*" %>
<%@ page import="com.goodhouse.good_ord.model.*, com.goodhouse.good_ord_list.model.*" %>
<%
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	String mem_id = memVO.getMem_id();
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>訂單查看</title>
</head>
<body>

</body>
</html>