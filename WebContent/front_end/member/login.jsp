<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="BIG5">
<title>login</title>
</head>
<body>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<h2>會員登入</h2>
	<form METHOD="POST" action="member.do">
		<input type="text" name="mb_id" placeholder="請輸入帳號" ><br>
        <input type="password" name="mb_pwd" placeholder="請輸入密碼"><br>
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="LOGIN"><br>
	</form>
	<a href="addMember.jsp">註冊會員</a>

</body>
</html>