<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mb.model.*" %>
<%@ page import="java.util.Base64"%>

<html>
<head>
<meta charset="BIG5">
<title>addMember</title>
<%  // 圖片路徑初始化
	String imgStr = request.getContextPath() + "/NoData/null2.jpg";

	MemberVO memberVO = (MemberVO)request.getAttribute("memberVO");
	if(memberVO != null && memberVO.getMb_pic() != null){  // 錯誤處理回來後，若先前有上傳圖片，則顯示原圖片
		imgStr = "data:image/png;base64," + Base64.getEncoder().encodeToString(memberVO.getMb_pic());
	}
%>
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
<script
  src="https://code.jquery.com/jquery-3.5.0.js"
  integrity="sha256-r/AaFHrszJtwpe+tHyNi/XCfMxYpbsRg2Uqn0x3s2zc="
  crossorigin="anonymous">
</script>
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
	<form METHOD="POST" action="<%=request.getContextPath()%>/front_end/member/member.do" enctype="multipart/form-data">
		<span id="star">*</span>為必填
		<div>
			<span id="star">*</span>
			帳號：<input id="mb_id" type="text" name="mb_id" value="${requestScope.memberVO.mb_id}">
			<img id="icon"/><span id="check"></span>
		</div>
		
		<span id="star">*</span>
		密碼：<input type="password" name="mb_pwd" value="${requestScope.memberVO.mb_pwd}"><br>
		
		<span id="star">*</span>
		名字：<input type="text" name="mb_name" value="${requestScope.memberVO.mb_name}"><br>
		
		<span id="star">*</span>
		性別：
		<c:forEach var="genderMapEntry" items="${memberGender}">
			<label>
			<input type="radio" name="mb_gender" value="${genderMapEntry.key}" ${requestScope.memberVO.mb_gender==genderMapEntry.key?"checked":""}>
	    	${genderMapEntry.value}
	    	</label>
		</c:forEach>
    	<br>
    	
<!--     	Line部分 -->
<%-- 		Line：<input type="text" name="mb_line" value="${memberVO.mb_line}"><br> --%>
		
		生日：<input type="text" name="mb_birthday" id="f_date" value="${requestScope.memberVO.mb_birthday}"><br>
		
		<span id="star">*</span>
		e-mail：<input type="text" name="mb_email" value="${requestScope.memberVO.mb_email}"><br>
		
		大頭照：<input type="file" name="mb_pic" onchange="setImg(this)" accept="image/*"><br>  <!-- 改版限定圖片種類 -->
		<img id="mb_pic" src="<%=imgStr%>" width="100px"><br>
		<!-- 第一次有送出照片，錯誤回來後沒有再選擇照片時，用picBase64送出 -->
		<%if(memberVO != null && memberVO.getMb_pic() != null){ %>
			<input type="hidden" name="picBase64" value="<%=Base64.getEncoder().encodeToString(memberVO.getMb_pic())%>">
		<%}; %>
		<input type="hidden" name="servletPath" value="<%=request.getServletPath()%>">
        <input type="hidden" name="action" value="insert"><br>
        <input type="reset" value="清除">
        <input id="submit" type="submit" value="送出"><br>
        
	</form>


<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<script>  

// AJAX 測試會員帳號

	$(document).ready(function(){
		$("#mb_id").on("blur",function(){
			$.ajax({
				type:"GET",
				url: "member.do",
				data:{
					"action":"check_id",
					"mb_id":$(this).val()
				},
				dataType: "json",
				success: function(data){
					$("#check").html(data.result);
					$("#icon").css("height","32");
					$("#icon").css("width","25");
					
					if(data.result != "此帳號可以使用"){
						$("#check").css("color","red");
						$("#icon").attr("src","images/no.png");
						$("#submit").attr("disabled","disabled");
					}else{
						$("#check").css("color","black");
						$("#icon").attr("src","images/ok.png");
						$("#submit").removeAttr("disabled");
					}
				},
				error: function(){alert("AJAX-class發生錯誤囉!")}
			});
		});
	});

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