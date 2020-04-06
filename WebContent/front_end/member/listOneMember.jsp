<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.Base64"%>
<%@ page import="com.mb.model.*"%>

<html>
<head>
<meta charset="BIG5">
<title>listOneMember</title>
</head>
<body>
	<%
		MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
		String base64 = Base64.getEncoder().encodeToString(memberVO.getMb_pic());
	%>
	
	<img src="data:image/jpeg;base64,<%= base64 %>"/><br>
	${memberVO.mb_name}
	
	<form METHOD="POST" action="member.do">
        <input type="hidden" name="action" value="logout">
        <input type="submit" value="登出"><br>
	</form>
	<a href="update_member.jsp">修改會員資料</a>

</body>
</html>