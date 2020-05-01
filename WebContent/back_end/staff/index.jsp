<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.abl.model.*"%>
<%@ page import="java.util.*"%>

<%
	AbilityService abilitySvc = new AbilityService();
	Map<String, String> abilityMap = abilitySvc.getAllToMap();
	pageContext.setAttribute("abilityMap",abilityMap);
	
%>
<html>
<head>
<meta charset="utf-8">
<title>Runnable 後台系統</title>
<style type="text/css">
	@font-face {
	  font-family: 'Mamelon';
	  src: url('<%=request.getContextPath()%>/back_end/staff/font/Mamelon.otf') format("truetype")
	}
	@font-face {
	  font-family: 'italics_hollow';
	  src: url('<%=request.getContextPath()%>/back_end/staff/font/italics_hollow.ttf') format("truetype"),
	  url('<%=request.getContextPath()%>/back_end/staff/font/italics_hollow.woff') format("woff")
	}	
	@font-face {
	  font-family: 'sleek_hollow';
	  src: url('<%=request.getContextPath()%>/back_end/staff/font/sleek_hollow.ttf') format("truetype"),
	  url('<%=request.getContextPath()%>/back_end/staff/font/sleek_hollow.woff') format("woff")
	}
</style>
<style type="text/css">		
	body{
		margin: 0px;
	}
	#left_bar{
		
		position: fixed;
		top: 0;
		left: 0;

		width: 15vw;
		height: 100vh;
		background-color: #9cc7f6;
		z-index: 50;
		/*border-radius: 50px;*/
	}
	#logo{
		width:85%;
		margin: 3% 7.5%;
	}
	
	#left_bar div{
		text-align: center;
		font-size: 3vh;
		line-height:6vh;
		color: #FEFF99;
		font-family: 'Mamelon';
		border-radius: 20px;

		border: 4px solid #9cc7f6;
		border-bottom: 4px solid #f3f8fe;
		margin: 0px 7.5% 3vh 7.5%;
		width: 85%;
		height: 6vh;
	}
	
	#left_bar div:hover{
		color: #f3f8fe;
		border: 4px solid #f3f8fe;
		background-color: #0373F0;
	}
	#top_bar{
		position: fixed;
		top:0;
		right: 0;

		width: 85vw;
		height: 13vh;
		background-color: #f3f8fe;
		/*border-radius: 50px;*/
	}
	#top_title{
		line-height: 10vh;
		color: #0373f0;
		font-size: 5vh;
        font-weight: bold;
        font-family: 'italics_hollow';
        margin-left: 50px;
	}
	#manager{
		font-size: 3vh;
		font-family: 'Mamelon';
		
		position: fixed;
		top:5vh;
		right: 15vw;

	}
	#logout{
		font-size: 2.5vh;
		line-height:1vh;
		color: #fff;
		font-family: 'Mamelon';
		background-color: #60A5F3;
		border-radius: 50px;
		
		height: 5vh;
		width: 4vw;
		position: fixed;
		top:4.5vh;
		right: 8vw;
	}

	#horizontalLine{
		width: 80vw;
		height: 2vh;
	}

	.content{
		background-color: #f3f8fe;
		
		width: 85vw;
		height: 90vh;
		margin-left: 15vw;
		margin-top: 13vh;
	}
	
</style>
</head>
<body>
	<div id="wrap">
	    <div id="left_bar">
	    	<img id="logo" src="<%=request.getContextPath()%>/back_end/staff/images/LogoText.png">
	    	
			<!-- 所有人都可以管理自己的資料，所以不在權限裡面 -->
	    	<form METHOD="POST" action="<%=request.getContextPath()%>/back_end/staff/staff.do">
	    		<div onclick="javascript:this.parentNode.submit();">個人資料管理</div>
	    		<input type="hidden" name="management" value="00">
				<input type="hidden" name="action" value="select_management">
			</form>	
			
			<%-- 管理權限 --%>
			<c:forEach var="map" items="${abilityMap}">
			<form METHOD="POST" action="<%=request.getContextPath()%>/back_end/staff/staff.do">
				<div onclick="javascript:this.parentNode.submit();">${map.value}</div>
				<input type="hidden" name="management" value="${map.key}">
				<input type="hidden" name="action" value="select_management">
			</form>	
			</c:forEach>
	    	
	    </div>
	    <div id="top_bar">
	    	<span id="top_title">後台管理系統</span>
	    	<span id="manager">管理員 ${sessionScope.staffVO.staff_name}</span>
	    	
			<!-- 登出 -->
	    	<form METHOD="POST" action="<%=request.getContextPath()%>/back_end/staff/staff.do">
		        <input type="hidden" name="action" value="logout">
		        <input type="submit" value="登出" id="logout">
			</form>
			
	    	<img id="horizontalLine" src="<%=request.getContextPath()%>/back_end/staff/images/HorizontalLine2.png">
	    </div>
	    <div class="content">
	    	
			<!-- include 部分 ， 若有預設畫面則將IF搬去預設畫面那邊 -->
			<c:if test="${not empty incluePath}">
				<jsp:include page="${incluePath}" flush="true"/>
			</c:if>
			
	    </div>
    </div>
</body>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<%
	String no_authority = (String)request.getAttribute("no_authority");  // 用來判斷是怎麼回來此頁面，決定alert什麼訊息
%>
<script>
		//  改EL
	$(document).ready(function(){
		<%if(no_authority!= null){%>
			Swal.fire({
			  icon: 'error',
			  title: 'Oops...',
			  text: '<%=no_authority%>',
			})
		<%}%>
	});
	
</script>
</html>