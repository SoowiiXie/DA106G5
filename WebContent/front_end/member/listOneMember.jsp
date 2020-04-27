<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mb.model.*"%>
<%@ page import="com.mb_follow.model.*"%>
<%@ page import="java.util.*"%>
<%
    MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
    MemberVO searchMbVO = (MemberVO)request.getAttribute("searchMbVO");
    
    Mb_followService mb_followService = new Mb_followService();
    boolean hasFollow = mb_followService.hasFollow(memberVO.getMb_id(),searchMbVO.getMb_id());
    pageContext.setAttribute("hasFollow",hasFollow);
%>
<html>
<head>
<meta charset="BIG5">
<title>listOneMember</title>
<style>
	#follow_img{
		 width:20px;
		 display:inline-block;
		 vertical-align:middle;
		 margin-left:80px;
	}
	table{
		font-size: 24px;
		font-weight: bold;
		font-family: ZhengHei;
	}
</style>
</head>
<body>
	<!--  改searchMbVO  -->
	<table class="ml-4 table" style="background-color:rgb(229, 233, 236);">
	<tr>
		<td colspan="2" align="center">
			<img src="<%= request.getContextPath()%>/MemberPicReader?mb_id=${searchMbVO.mb_id}" width="150rem" class="rounded">
		</td>
	</tr>
	<tr>
		<td style="text-align:left">
			會員：
		</td>
		<td style="text-align:left">
			${searchMbVO.mb_name}
		</td>
	</tr>
	<tr>
		<td style="text-align:left">
			性別：
		</td>
		<td style="text-align:left">
			${searchMbVO.mb_gender}
		</td>
	</tr>
	<tr>
		<td style="text-align:left">
			生日：
		</td>
		<td style="text-align:left">
			${searchMbVO.mb_birthday}
		</td>
	</tr>
	<tr>
		<td colspan="2"  style="text-align:left">
			E-mail：
		</td>
	</tr>
	<tr>
		<td colspan="2"  style="text-align:left">
			${searchMbVO.mb_email}
		</td>
	</tr>
	<tr>
		<td style="text-align:left">
			會員等級：
		</td>
		<td style="text-align:left">
			${searchMbVO.mb_lv}
		</td>
	</tr>
	<tr>
		<td style="text-align:left">
			被檢舉次數：
		</td>
		<td style="text-align:left">
			${searchMbVO.mb_rpt_times}
		</td>
	</tr>
	<tr>
		<td colspan="2"  style="text-align:left">
			<span id="follow">
				<img id="follow_img" src="${hasFollow?'images/red_heart.png':'images/black_heart.png'}">
				<label id="label">${hasFollow?"已關注":"關注"}</label>
			</span>
		</td>
	</tr>
	</table>
	
	<script
		  src="https://code.jquery.com/jquery-3.5.0.js"
		  integrity="sha256-r/AaFHrszJtwpe+tHyNi/XCfMxYpbsRg2Uqn0x3s2zc="
		  crossorigin="anonymous">
	</script>
	
	<script>  

	// AJAX 關注

	$(document).ready(function(){
		$("#follow").on("click",function(){
			$.ajax({
				type:"GET",
				url: "mb_follow.do",
				data:{
					"action":"follow",
					"mb_id":"${memberVO.mb_id}",
					"mb_id_followed":"${searchMbVO.mb_id}"
				},
				dataType: "text",
				success: function(data){
					
					if(data == "addFollow"){
						$("#follow_img").attr("src","images/red_heart.png");
						$("#label").text("已關注");
					}else if(data == "deleteFollow"){
						$("#follow_img").attr("src","images/black_heart.png");
						$("#label").text("關注");
					}
				},
				error: function(){alert("AJAX-class發生錯誤囉!")}
			});
		});
	});
	</script>
</body>
</html>