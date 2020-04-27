<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mb.model.*"%>

<html>
<head>
<meta charset="BIG5">
<title>listOneMember</title>
</head>
<body>
	
	
	<img src="<%= request.getContextPath()%>/MemberPicReader?mb_id=${memberVO.mb_id}" width="100px"><br>
	會員：${memberVO.mb_name}
	
	<form METHOD="POST" action="member.do">
        <input type="hidden" name="action" value="logout">
        <input type="submit" value="登出"><br>
	</form>
	<a href="update_member.jsp">修改會員資料</a>

</body>
</html>