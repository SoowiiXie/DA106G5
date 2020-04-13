<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="BIG5">
<title>login</title>
</head>
<body>
	<h2>管理員登入</h2>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color:red">請修正以下錯誤:</font>
		<ul>
		    <c:forEach var="message" items="${errorMsgs}">
				<li style="color:red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<form METHOD="POST" action="staff.do">
		<input type="text" name="staff_id" placeholder="請輸入帳號" ><br>
        <input type="password" name="staff_pwd" placeholder="請輸入密碼"><br>
        <input type="hidden" name="action" value="login">
        <input type="submit" value="LOGIN"><br>
	</form>

</body>
</html>