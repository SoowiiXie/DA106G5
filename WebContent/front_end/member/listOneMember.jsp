<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mb.model.*"%>
<%@ page import="com.mb_follow.model.*"%>
<%@ page import="java.util.*"%>
<%
	MemberService memberSvc = new MemberService();

    MemberVO memberVO = memberSvc.getOneMember("xuan123");
    pageContext.setAttribute("memberVO",memberVO);
    
    MemberVO searchMbVO = memberSvc.getOneMember("vain123");
    pageContext.setAttribute("searchMbVO",searchMbVO);
    
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
</style>
</head>
<body>
	<!--  改searchMbVO  -->
	<table>
	<tr>
		<td colspan="2" align="center">
			<img src="<%= request.getContextPath()%>/MemberPicReader?mb_id=${searchMbVO.mb_id}" width="100px">
		</td>
	</tr>
	<tr>
		<td align="center">
			會員：
		</td>
		<td>
			${searchMbVO.mb_name}
		</td>
	</tr>
	<tr>
		<td align="center">
			性別：
		</td>
		<td>
			${searchMbVO.mb_gender}
		</td>
	</tr>
	<tr>
		<td align="center">
			生日：
		</td>
		<td>
			${searchMbVO.mb_birthday}
		</td>
	</tr>
	<tr>
		<td align="center">
			E-mail：
		</td>
		<td>
			${searchMbVO.mb_email}
		</td>
	</tr>
	<tr>
		<td align="center">
			會員等級：
		</td>
		<td>
			${searchMbVO.mb_lv}
		</td>
	</tr>
	<tr>
		<td align="center">
			被檢舉次數：
		</td>
		<td>
			${searchMbVO.mb_rpt_times}
		</td>
	</tr>
	<tr>
		<td colspan="2">
			
			<img id="follow_img" src="${hasFollow?'images/red_heart.png':'images/black_heart.png'}">
			<label id="follow" for="follow_img">${hasFollow?"已關注":"關注"}</label>
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
						$("#follow").text("已關注");
					}else if(data == "deleteFollow"){
						$("#follow_img").attr("src","images/black_heart.png");
						$("#follow").text("關注");
					}
				},
				error: function(){alert("AJAX-class發生錯誤囉!")}
			});
		});
	});
	</script>
</body>
</html>