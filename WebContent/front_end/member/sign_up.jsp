<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="BIG5">
<title>Login</title>
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
	<form METHOD="POST" action="member.do" enctype="multipart/form-data">
	
		帳號：<input type="text" name="mb_id"><br>
		密碼：<input type="password" name="mb_pwd"><br>
		名字：<input type="text" name="mb_name"><br>
		
		性別：
		<input type="radio" id="gender1" name="mb_gender">
    	<label for="gender1">男</label>
    	<input type="radio" id="gender2" name="mb_gender">
    	<label for="gender2">女</label><br>
    	
		Line：<input type="text" name="mb_line"><br>
		生日：<input type="text" name="mb_birthday"><br>
		e-mail：<input type="text" name="mb_email"><br>
		大頭照：<input type="file" name="mb_pic"><br>
		
        <input type="hidden" name="action" value="insert"><br>
        <input type="reset" value="清除">
        <input type="submit" value="送出"><br>
        
	</form>

</body>
</html>