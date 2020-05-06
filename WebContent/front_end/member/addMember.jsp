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
<style type="text/css">
@font-face {
	  font-family: 'italics_hollow';
	  src: url('<%=request.getContextPath()%>/back_end/staff/font/italics_hollow.ttf') format("truetype"),
	  url('<%=request.getContextPath()%>/back_end/staff/font/italics_hollow.woff') format("woff")
	}	
</style>

<style>
	body {
        background-image: url(<%=request.getContextPath()%>/front_end/member/login/Login/running.jpg);
        background-repeat: no-repeat;
        background-size: cover;
    }
    
	#wrapAll{
		margin: 10vh auto 0px auto; 
		background-color: rgba(255, 255, 255, 0.6);
            
        width: 25vw;
        height: 75vh;
        border-radius: 30px;
	}
	.textBox{
        height: 2.8vh;
        width: 12vw;
        border-radius: 0.8vh;
        padding-left: 0.5vh;
	}
	
	#title{
		width:fit-content;
		line-height: 10vh;
		margin:0px auto;
		color: #333;
		font-size: 5vh;
        font-weight: bold;
        font-family: ZhengHei;
	}

	#icon{
		vertical-align:middle;
		margin-left:2vw;
	}
	
	#mb_pic{
		width:12vw;
		height:20vh;
	}
	
	#star{
		color:red;
		font-weight:bold;
	}
	#add_table td{
/* 		border:1px solid #000; */
	}
	#add_table{
		margin:0px auto;
		font-weight:bold;
		color: #333;
		font-size: 2vh;
	}
	#td_check{
		height:3.4vh;
		
	}
	#wrap_btn{
		width:fit-content;
		margin:5vh auto 0px auto;
	}
	.btn{
		width:6vw;
		height:5vh;
		border-radius: 1.5vh;
		box-shadow: 2px 3px 3px #333;
		background-color: rgba(110, 195, 219, 0.7);
		cursor: pointer;
		
		color: #fff;
		font-weight: bold;
        font-size: 2vh;
        font-family: ZhengHei;
        text-align: center;
        line-height: 3.3vh;
        text-shadow: 1px 1px 3px #333;
	}
	a{
		color: #fff;
	}
	#submit{
		margin-left:2.5vw;
	}
</style>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<script>
	$(document).ready(function(){
		<c:if test="${not empty errorMsgs}">
			Swal.fire({
			  icon: 'error',
			  title: '請修正以下錯誤:',
			  html: 
					<c:forEach var="message" items="${errorMsgs}">
						'<font color="red">${message}</font><br>'+
					</c:forEach>''
			})
		</c:if>
	});
	
</script>
</head>
<body>
<div id="wrapAll">
<%-- 錯誤表列 --%>

	<form METHOD="POST" action="<%=request.getContextPath()%>/front_end/member/member.do" enctype="multipart/form-data">
		<div id="title">註冊會員</div><br>
		<table id="add_table">
		<tr>
			<td align="right"><span id="star">*</span>為必填</td>
			<td></td>
		</tr>
		<tr>
			<td align="right"><span id="star">*</span>帳號：</td>
			<td><input class="textBox" id="mb_id" type="text" name="mb_id" value="${requestScope.memberVO.mb_id}"></td>
		</tr>
		<tr>
			<td colspan="2" id="td_check"><img id="icon"/><span id="check"></span></td>
		</tr>
		<tr>
			<td align="right"><span id="star">*</span>密碼：</td>
			<td><input class="textBox" type="password" name="mb_pwd" value="${requestScope.memberVO.mb_pwd}"></td>
		</tr>
		<tr>
			<td align="right"><span id="star">*</span>名字：</td>
			<td><input class="textBox" type="text" name="mb_name" value="${requestScope.memberVO.mb_name}"></td>
		</tr>
		<tr>
			<td align="right"><span id="star">*</span>性別：</td>
			<td>
				<c:forEach var="genderMapEntry" items="${memberGender}">
					<label>
					<input type="radio" name="mb_gender" value="${genderMapEntry.key}" ${requestScope.memberVO.mb_gender==genderMapEntry.key?"checked":""}>
			    	${genderMapEntry.value}
			    	</label>
				</c:forEach>
			</td>
		</tr>
		<tr>
			<td align="right">生日：</td>
			<td><input class="textBox" type="text" name="mb_birthday" id="f_date" value="${requestScope.memberVO.mb_birthday}"></td>
		</tr>
		<tr>
			<td align="right"><span id="star">*</span>e-mail：</td>
			<td><input class="textBox" type="text" name="mb_email" value="${requestScope.memberVO.mb_email}"></td>
		</tr>
		<tr>
			<td align="right">大頭照：</td>
			<td><input type="file" name="mb_pic" onchange="setImg(this)" accept="image/*"></td>  <!-- 改版限定圖片種類 -->
		</tr>
		<tr>
			<td align="right">預覽圖片：</td>
			<td><img id="mb_pic" src="<%=imgStr%>"></td>
		</tr>
		<tr>
			<td colspan="2">
			<div id="wrap_btn">
				<!-- 第一次有送出照片，錯誤回來後沒有再選擇照片時，用picBase64送出 -->
				<%if(memberVO != null && memberVO.getMb_pic() != null){ %>
					<input type="hidden" name="picBase64" value="<%=Base64.getEncoder().encodeToString(memberVO.getMb_pic())%>">
				<%}; %>
				<input type="hidden" name="servletPath" value="<%=request.getServletPath()%>">
		        
		        <button class="btn" type="submit" name="action" value="logout">返回</button>
		        <button class="btn" id="submit" type="submit" name="action" value="insert">送出</button>
			</div>
			</td>
		</tr>
        </table>
	</form>

</div>

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