<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="BIG5">
<title>addMember</title>
<style>

	#icon{
		vertical-align:middle
	}
	
	#mb_pic{
		width:200px;
		height:200px;
	}
	
	#star{
		color:red;
		font-weight:bold;
	}
	
</style>
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
		<span id="star">*</span>為必填
		<div>
			<span id="star">*</span>
			帳號：<input id="mb_id" type="text" name="mb_id" onblur="checkId()" value="${memberVO.mb_id}">
			<img id="icon"/><span id="check"></span>
		</div>
		
		<span id="star">*</span>
		密碼：<input type="password" name="mb_pwd" value="${memberVO.mb_pwd}"><br>
		
		<span id="star">*</span>
		名字：<input type="text" name="mb_name" value="${memberVO.mb_name}"><br>
		
		<span id="star">*</span>
		性別：
		<input type="radio" id="gender1" name="mb_gender" value="1" ${(memberVO.mb_gender==1)?'checked':''}>
    	<label for="gender1">男</label>
    	<input type="radio" id="gender2" name="mb_gender" value="2" ${(memberVO.mb_gender==2)?'checked':''}>
    	<label for="gender2">女</label><br>
    	
		Line：<input type="text" name="mb_line" value="${memberVO.mb_line}"><br>
		
		生日：<input type="text" name="mb_birthday" id="f_date" value="${memberVO.mb_birthday}"><br>
		
		<span id="star">*</span>
		e-mail：<input type="text" name="mb_email" value="${memberVO.mb_email}"><br>
		
		大頭照：<input type="file" name="mb_pic" onchange="setImg(this)"><br>  <!-- 改版限定圖片種類 -->
		<img id="mb_pic" src="<%= request.getContextPath()%>/NoData/null2.jpg">
		<br>
		
        <input type="hidden" name="action" value="insert"><br>
        <input type="reset" value="清除">
        <input id="submit" type="submit" value="送出"><br>
        
	</form>


<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<script>  

// AJAX 測試會員帳號
	var xhr = null;
	
	function checkId(){ 
		var mb_id = document.getElementById("mb_id").value;
		if(mb_id.trim().length == 0){
			icon.src="images/no.png";
			icon.width="25";
			icon.height="32";
			check.innerHTML = "帳號不得空白";
			test.style.color = "red";
		}
		
	  	var xhr = new XMLHttpRequest();
	  	//設定好回呼函數   
	  	xhr.onload = function (){
	   	   if( xhr.status == 200){
	    	  var check = document.getElementById("check");
	    	  var icon = document.getElementById("icon"); 
	    	  var submit = document.getElementById("submit"); 
	    	  if(xhr.response == 1){
	    		  icon.src="images/ok.png";
	    		  icon.width="25";
	    		  icon.height="32";
	    		  check.innerHTML = "此帳號可以使用";
	    		  check.style.color = "black";
	    		  submit.removeAttribute("disabled");;
	    	  }else{
	    		  icon.src="images/no.png";
	    		  icon.width="25";
	    		  icon.height="32";
	    		  check.innerHTML = "此帳號已被使用";
	    		  check.style.color = "red";
	    		  submit.disabled="disabled";
	    	  }
	      	}else{
	        	alert( xhr.status );
	      	}//xhr.status == 200
	 	 };//onload 
	  
	  	//建立好Get連接
	  	var url= "checkId.jsp?mb_id=" + document.getElementById("mb_id").value;
	  	xhr.open("Get",url,true); 
	  	//送出請求 
	  	xhr.send( null );
	}

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
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
        });
</script>
</body>
</html>