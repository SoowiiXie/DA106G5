<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mb.model.*"%>

<html>
<head>
<meta charset="BIG5">
<title>listOneMember</title>
<style>
	#follow_img{
		 width:20px;
		 display:inline-block;
		 vertical-align:middle;
	}
</style>
</head>
<body>
	
	<table>
	<tr>
		<td colspan="2" align="center">
			<img src="<%= request.getContextPath()%>/MemberPicReader?mb_id=${memberVO.mb_id}" width="100px">
		</td>
	</tr>
	<tr>
		<td align="center">
			會員：
		</td>
		<td>
			${memberVO.mb_name}
		</td>
	</tr>
	<tr>
		<td align="center">
			性別：
		</td>
		<td>
			${memberVO.mb_gender}
		</td>
	</tr>
	<tr>
		<td align="center">
			生日：
		</td>
		<td>
			${memberVO.mb_birthday}
		</td>
	</tr>
	<tr>
		<td align="center">
			E-mail：
		</td>
		<td>
			${memberVO.mb_email}
		</td>
	</tr>
	<tr>
		<td align="center">
			會員等級：
		</td>
		<td>
			${memberVO.mb_lv}
		</td>
	</tr>
	<tr>
		<td align="center">
			被檢舉次數：
		</td>
		<td>
			${memberVO.mb_rpt_times}
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<label id="follow">
			<img id="follow_img" src="images/black_heart.png">
			關注</label>
		</td>
	</tr>
	</table>
	
	<script>  

	// AJAX 關注

	$(document).ready(function(){
		$("#follow").on("click",function(){
			$.ajax({
				type:"GET",
				url: "member.do",
				data:{
					"action":"follow",
					"mb_id":"${sessionScope.memberVO.mb_name}",
					"mb_id_followed":"${memberVO.mb_name}"
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
	</script>
</body>
</html>