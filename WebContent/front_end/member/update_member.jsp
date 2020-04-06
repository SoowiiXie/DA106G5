<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>update_member</title>
</head>
<body>
	<form METHOD="POST" action="member.do" enctype="multipart/form-data">
	
		帳號：${memberVO.mb_id}<br>
		密碼：<input type="password" name="mb_pwd" value="${memberVO.mb_pwd}"><br>
		名字：<input type="text" name="mb_name" value="${memberVO.mb_name}"><br>
		
		性別：
		<input type="radio" id="gender1" name="mb_gender">
    	<label for="gender1">男</label>
    	<input type="radio" id="gender2" name="mb_gender">
    	<label for="gender2">女</label><br>
    	
		Line：<input type="text" name="mb_line" value="${memberVO.mb_line}"><br>
		生日：<input type="text" name="mb_birthday" value="${memberVO.mb_birthday}"><br>
		e-mail：<input type="text" name="mb_email" value="${memberVO.mb_email}"><br>
		大頭照：<input type="file" name="mb_pic"><br>
		
        <input type="hidden" name="action" value="update"><br>
        <input type="reset" value="清除">
        <input type="submit" value="送出"><br>
        
	</form>
</body>
</html>