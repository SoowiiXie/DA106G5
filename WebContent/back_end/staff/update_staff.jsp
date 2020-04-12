<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>update_staff</title>
</head>
<body>
	<form METHOD="POST" action="staff.do">
	
		帳號：${memberVO.mb_id}<br>
		密碼：<input type="password" name="mb_pwd" value="${memberVO.mb_pwd}"><br>
		名字：<input type="text" name="mb_name" value="${memberVO.mb_name}"><br>
		
		性別：
		<input type="radio" id="gender1" name="mb_gender" value="1" ${(memberVO.mb_gender==1)?'checked':''}>   
		<label for="gender1">男</label>
    	<input type="radio" id="gender2" name="mb_gender" value="2" ${(memberVO.mb_gender==2)?'checked':''}>
    	<label for="gender2">女</label><br>
    	
		Line：<input type="text" name="mb_line" value="${memberVO.mb_line}"><br>
		生日：<input type="text" id="f_date" name="mb_birthday" value="${memberVO.mb_birthday}"><br>
		e-mail：<input type="text" name="mb_email" value="${memberVO.mb_email}"><br>
		
		<input type="hidden" name="mb_id" value="${memberVO.mb_id}"><br>
		<input type="hidden" name="mb_lv" value="${memberVO.mb_lv}"><br>
		<input type="hidden" name="mb_rpt_times" value="${memberVO.mb_rpt_times}"><br>
		<input type="hidden" name="mb_status" value="${memberVO.mb_status}"><br>
		
        <input type="hidden" name="action" value="update"><br>
        <input type="reset" value="清除">
        <input type="submit" value="送出"><br>
        
        
	</form>
	
</body>
</html>