<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.group_follow.model.*"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>關注幾個揪團</title>
</head>
<body>

<%
	Integer groupfollowcount = (Integer)request.getAttribute("peoplefollowcount");
%>

	<jsp:useBean id="group_followSvc" scope="page" class="com.group_follow.model.Group_followService" />
			
			<input type="hidden" name="action" value="getGroupFollowCount">
			
			共有<%=groupfollowcount %>個人

</body>
</html>