<%@page import="java.lang.ProcessBuilder.Redirect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<head>
<title>DA106G5揪團</title>
<%-- 	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/front_end/group_detail/webFront/show.css"> --%>
<style type="text/css">
#service {
	width: 3rem;
	height: 3rem;
	border-radius: 1.5rem;
	border: 2px #fff solid;
	background-color: #4E73DF;
	line-height: 60px;
	font-size: 14px;
	color: #fff;
	text-align: center;
	position: fixed;
	top: 87%;
	left: 87%;
	margin: 0px;
}

#fieldset, input {
	font-family: microsoft jhengHei;
}
</style>
</head>
<body>
	<nav aria-label="breadcrumb" class="col-12 my_breadcrumb" style="padding:0;">
	<ol class="breadcrumb m-0">
		<li class="breadcrumb-item"><a
			href="<%=request.getContextPath()%>/front_end/member/login.jsp">登入畫面</a></li>
		<li class="breadcrumb-item"><a
			href="<%=request.getContextPath()%>/front_end/member/login.jsp">揪團</a></li>
		<li class="breadcrumb-item active" aria-current="page">開團</li>
	</ol>
</nav>
	<header>
<!-- 		<h1>揪團</h1> -->
		<small class="ml-3">用運動來彼此蕉流</small>
	</header>
<!-- 	<nav> -->
<!-- 		<a href="">個人頁面</a> <a href="">準備出發</a> <a href="">購物商城</a> <a href="">運動直播</a> -->
<!-- 	</nav> -->
	<video class="col-10" autoplay loop controls muted
		poster="<%=request.getContextPath()%>/front_end/group_detail/webFront/v1.jpg">
		<source
			src="<%=request.getContextPath()%>/front_end/group_detail/webFront/run.mp4"
			type="video/mp4">
		<source
			src="<%=request.getContextPath()%>/front_end/group_detail/webFront/run.ogg"
			type="video/ogg">
		<source
			src="<%=request.getContextPath()%>/front_end/group_detail/webFront/run.webm"
			type="video/webm">
	</video>
	<!-- 	<div id="left"> -->
	<!-- 		<h2>個人頁面</h2> -->
	<!-- 		<p></p> -->
	<!-- 		<p></p> -->
	<!-- 		<p></p> -->
	<!-- 		<h2>準備出發</h2> -->
	<!-- 		<p>天氣</p> -->
	<!-- 		<p>補水點</p> -->
	<!-- 		<p>運動場</p> -->
	<!-- 		<h2>購物商城</h2> -->
	<!-- 		<p>買</p> -->
	<!-- 		<p>買</p> -->
	<!-- 		<p>買</p> -->
	<!-- 		<h2>運動直播</h2> -->
	<!-- 		<p>正在直播</p> -->
	<!-- 		<p>直播預告</p> -->
	<!-- 		<p>直播紀錄</p> -->
	<!-- 	</div> -->
	<div id="right">
		<img src="<%=request.getContextPath()%>/front_end/group_detail/webFront/c1.jpg"	alt="" class="col-3 ml-2" style="height: 10rem;"> 
		<img src="<%=request.getContextPath()%>/front_end/group_detail/webFront/c4.jpg" alt="" class="col-3 mx-4" style="height: 10rem;"> 
		<img src="<%=request.getContextPath()%>/front_end/group_detail/webFront/c3.jpg"	alt="" class="col-3 mx-auto" style="height: 10rem;">
		<form action="" oninput="answer.innerText=parseInt(s1.value)" class="col-12">
			<fieldset>
<!-- 				<legend>joinUS</legend> -->
				<p>
					<label for="grp_name">揪團標題: </label> <input type="text"
						id="grp_name" required placeholder="請輸入你的揪團標題"
						style="width: 250px; height: 20px;">
				</p>

				<p>
					<label for="grp_applystart">開始時間: </label> <input
						type="datetime-local" id="grp_applystart"
						style="width: 248px; height: 20px;">
				</p>
				<p>
					<label for="grp_applyend">結束時間: </label> <input
						type="datetime-local" id="grp_applyend"
						style="width: 248px; height: 20px;">
				</p>
				<p>
					<label for="grp_start">報名開始時間: </label> <input
						type="datetime-local" id="grp_start"
						style="width: 220px; height: 20px;">
				</p>
				<p>
					<label for="grp_end">報名截止時間: </label> <input type="datetime-local"	id="grp_end" style="width: 220px; height: 20px;">
				</p>
				地點: <input type="text" id="product" list="info"	style="width: 292px; height: 20px;">

				<datalist id="info">
					<option value="中央大學籃球場"></option>
					<option value="中央大學排球場"></option>
					<option value="中央大學羽球場"></option>
				</datalist>
				</p>
				<span><label for="grp_content">揪團內容: </label> <input
					type="textarea" id="grp_content"
					style="width: 250px; height: 100px;"></span>


				<p>
					<input type="submit" value="送出"><input type="reset"
						value="清除">
				</p>

				<div id="service">線上客服</div>
			</fieldset>
		</form>
	</div>
	<footer class="mx-auto">RUNNABLE &copy;Copyright 2020 by Your LOGO - NYKD54@gmail.com</footer>
</body>
<body>

	<form action="" oninput="answer.innerText=parseInt(s1.value)">
</body>