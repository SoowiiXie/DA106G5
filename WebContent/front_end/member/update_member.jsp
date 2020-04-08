<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		<input type="radio" id="gender1" name="mb_gender" ${(memberVO.mb_gender==1)?'checked':''}>   
		<label for="gender1">男</label>
    	<input type="radio" id="gender2" name="mb_gender" ${(memberVO.mb_gender==2)?'checked':''}>
    	<label for="gender2">女</label><br>
    	
		Line：<input type="text" name="mb_line" value="${memberVO.mb_line}"><br>
		生日：<input type="text" id="f_date" name="mb_birthday" value="${memberVO.mb_birthday}"><br>
		e-mail：<input type="text" name="mb_email" value="${memberVO.mb_email}"><br>
		
		大頭照：<input type="file" name="mb_pic" onchange="setImg(this)"><br>
		<img id="mb_pic" src="/DA106_G5/MemberPicReader?mb_id=${memberVO.mb_id}" width="100px">
		
		<input type="hidden" name="mb_lv" value="${memberVO.mb_lv}"><br>
		<input type="hidden" name="mb_rpt_times" value="${memberVO.mb_rpt_times}"><br>
		<input type="hidden" name="mb_status" value="${memberVO.mb_status}"><br>
		
        <input type="hidden" name="action" value="update"><br>
        <input type="reset" value="清除">
        <input type="submit" value="送出"><br>
        
        
	</form>
	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
	
	<script>
	
	// 預覽圖片
	function setImg(input){
  		if(input.files && input.files[0]){
  			var reader = new FileReader();
  			reader.onload = function (e) {
    			document.getElementById("mb_pic").setAttribute("src", e.target.result);
    		}
    	reader.readAsDataURL(input.files[0]);
  		}
	}
	
	// 日期
	
 	$.datetimepicker.setLocale('zh');
        $('#f_date').datetimepicker({
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
        });
	</script>
</body>
</html>