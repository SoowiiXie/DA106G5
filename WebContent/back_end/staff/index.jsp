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
	  src: url('font/Mamelon.otf') format("truetype")
	}
	@font-face {
	  font-family: 'italics_hollow';
	  src: url('font/italics_hollow.ttf') format("truetype"),
	  url('font/italics_hollow.woff') format("woff")
	}	
	@font-face {
	  font-family: 'sleek_hollow';
	  src: url('font/sleek_hollow.ttf') format("truetype"),
	  url('font/sleek_hollow.woff') format("woff")
	}		
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
	
	#left_bar div,#left_bar div botton{
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
		<form METHOD="POST" action="staff.do" id="form1">
	    	<img id="logo" src="images/LogoText.png">
	    	<div onclick="location.href='update_self.jsp';">個人資料管理</div>
	    	
			<%-- 權限，用button是為了讓value可以使用 --%>
			<c:forEach var="map" items="${abilityMap}">
<%-- 			<div onclick="javascript:this.parentNode.submit();">${map.value}</div> --%>
				<div><button type="submit" form="form1" name="management" value="${map.key}">${map.value}</button></div>	
			</c:forEach>
			
			<input type="hidden" name="action" value="select_management">
			<input type="hidden" name="servletPath" value="<%=request.getServletPath()%>">
		</form>	
				
	    	
	    </div>
	    <div id="top_bar">
	    	<span id="top_title">後台管理系統</span>
	    	<span id="manager">管理員 ${staffVO.staff_name}</span>
	    	<button id="logout">登出</button>
	    	<img id="horizontalLine" src="images/HorizontalLine2.png">
	    </div>
	    <div class="content">
	    	<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color:red">請修正以下錯誤:</font>
				<ul>
				    <c:forEach var="message" items="${errorMsgs}">
						<li style="color:red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
	    </div>

    </div>
</body>
<script
  src="https://code.jquery.com/jquery-3.5.0.js"
  integrity="sha256-r/AaFHrszJtwpe+tHyNi/XCfMxYpbsRg2Uqn0x3s2zc="
  crossorigin="anonymous">
</script>

<script type="text/javascript">
	
</script>
</html>