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
			top: 90%;
			left: 90%;
			margin: 0px;
		}
		
		#fieldset, input {
			font-family: microsoft jhengHei;
		}
		
		#right form{/*id名稱為right的區塊底下的表單*/
	        color:#633;/*文字色彩*/
	        font-size:1rem;/*文字大小*/
	        margin-bottom: 20px;/*下外距*/
	    }
		
		footer{/*頁尾*/
	    	clear:both;/*清除前區塊圍繞帶來的浮動效果*/
	    	color:#39c;/*文字色彩*/
	    	text-align: center;/*內容水平置中*/
	    	margin-bottom: 15px;/*下外距 增加頁面下緣空間*/
	    }
		</style>
	</head>
	<body>
		<div id="contentMiddle" class="col-12 d-block" style="hight:100%;">
			<nav aria-label="breadcrumb" class="col-12 my_breadcrumb" style="padding:0;">
			<ol class="breadcrumb m-0">
				<li class="breadcrumb-item"><a
					href="<%=request.getContextPath()%>/front_end/member/login.jsp">登入畫面</a></li>
				<li class="breadcrumb-item"><a
					href="<%=request.getContextPath()%>/front_end/member/login.jsp">揪團</a></li>
				<li class="breadcrumb-item active" aria-current="page">開團</li>
			</ol>
			</nav>
			<video class="col-10" autoplay loop controls muted poster="<%=request.getContextPath()%>/front_end/group_detail/webFront/c2.jpg">
				<source src="<%=request.getContextPath()%>/front_end/group_detail/webFront/run.mp4" type="video/mp4">
				<source	src="<%=request.getContextPath()%>/front_end/group_detail/webFront/run.ogg"	type="video/ogg">
				<source	src="<%=request.getContextPath()%>/front_end/group_detail/webFront/run.webm" type="video/webm">
			</video>
			<div id="right">
				<img src="<%=request.getContextPath()%>/front_end/group_detail/webFront/c1.jpg"	alt="" class="col-3 ml-4" style="height: 12rem;"> 
				<img src="<%=request.getContextPath()%>/front_end/group_detail/webFront/c4.jpg" alt="" class="col-3 mx-4" style="height: 12rem;"> 
				<img src="<%=request.getContextPath()%>/front_end/group_detail/webFront/c3.jpg"	alt="" class="col-3 mx-auto" style="height: 12rem;">
				<form action="" oninput="answer.innerText=parseInt(s1.value)" class="col-12">
					<fieldset class="mt-3 ml-3">
		<!-- 				<legend>joinUS</legend> -->
						<p>
							<label for="grp_name">揪團標題: </label> 
							<input type="text" id="grp_name" required placeholder="請輸入你的揪團標題" style=" width: 14rem; height: 1.2rem;">
						</p>
						<p>
							<label for="grp_applystart">開始時間: </label> 
							<input type="datetime-local" id="grp_applystart" style="width: 14rem; height: 1.2rem;">
						</p>
						<p>
							<label for="grp_applyend">結束時間: </label> 
							<input type="datetime-local" id="grp_applyend" style="width: 14rem; height: 1.2rem;">
						</p>
						<p>
							<label for="grp_start">報名開始時間: </label> 
							<input type="datetime-local" id="grp_start"	style="width: 12rem; height: 1.2rem;">
						</p>
						<p>
							<label for="grp_end">報名截止時間: </label> 
							<input type="datetime-local"	id="grp_end" style="width: 12rem; height: 1.2rem;">
						</p>
						<p>
							地點: <input type="text" id="product" list="info"	style="width: 16rem; height: 1.2rem;">
						</p>
						<datalist id="info">
							<option value="中央大學籃球場"></option>
							<option value="中央大學排球場"></option>
							<option value="中央大學羽球場"></option>
						</datalist>
						<span>
							<label for="grp_content">揪團內容: </label> 
							<input type="textarea" id="grp_content"	style="width: 14rem; height: 5rem;" value="">
						</span>
		
						<p class="mt-3 mx-auto">
							<input type="submit" value="送出" class="btn btn-primary">
							<input type="reset"	value="清除" class="btn btn-primary">
						</p>
		
						<div id="service">線上客服</div>
					</fieldset>
				</form>
				<form>
				  <div class="form-row">
				    <div class="form-group col-md-3">
				      <label for="inputEmail4">Email</label>
				      <input type="email" class="form-control" id="inputEmail4" placeholder="Email">
				    </div>
				    <div class="form-group col-md-3">
				      <label for="inputPassword4">Password</label>
				      <input type="password" class="form-control" id="inputPassword4" placeholder="Password">
				    </div>
				  </div>
				  <div class="form-group col-6">
				    <label for="inputAddress">Address</label>
				    <input type="text" class="form-control" id="inputAddress" placeholder="1234 Main St">
				  </div>
				  <div class="form-group col-6">
				    <label for="inputAddress2">Address 2</label>
				    <input type="text" class="form-control" id="inputAddress2" placeholder="Apartment, studio, or floor">
				  </div>
				  <div class="form-row">
				    <div class="form-group col-md-3">
				      <label for="inputCity">City</label>
				      <input type="text" class="form-control" id="inputCity">
				    </div>
				    <div class="form-group col-md-2">
				      <label for="inputState">State</label>
				      <select id="inputState" class="form-control">
				        <option selected>Choose...</option>
				        <option>...</option>
				      </select>
				    </div>
				    <div class="form-group col-md-1">
				      <label for="inputZip">Zip</label>
				      <input type="text" class="form-control" id="inputZip">
				    </div>
				  </div>
				  <div class="form-group">
				    <div class="form-check">
				      <input class="form-check-input" type="checkbox" id="gridCheck">
				      <label class="form-check-label" for="gridCheck">
				        Check me out
				      </label>
				    </div>
				  </div>
				  <button type="submit" class="btn btn-primary">Sign in</button>
				</form>
			</div>
			<footer>RUNNABLE &copy;Copyright 2020 by Your LOGO - NYKD54@gmail.com</footer>
		</div>
	</body>
<!-- 	<body> -->
<!-- 		<form action="" oninput="answer.innerText=parseInt(s1.value)"> -->
<!-- 	</body> -->
