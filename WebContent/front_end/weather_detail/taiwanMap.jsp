<%@page import="java.lang.ProcessBuilder.Redirect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- <div class="col-3">&nbsp&nbsp個人頁面</div> -->
<div id="contentMiddle" class="col-12 d-block" style="hight:100%;">
	<nav aria-label="breadcrumb" class="col-12 my_breadcrumb">
		<ol class="breadcrumb m-0">
			<li class="breadcrumb-item"><a
				href="<%=request.getContextPath()%>/front_end/member/login.jsp">登入畫面</a></li>
			<li class="breadcrumb-item"><a
				href="<%=request.getContextPath()%>/front_end/member/login.jsp">準備</a></li>
			<li class="breadcrumb-item active" aria-current="page">天氣</li>
		</ol>
	</nav>
	<div class="btn-group-vertical col-1">
		<div class="mx-auto">離島</div>
		<button type="button" class="btn btn-secondary wth_loc_btn" value="連江縣">連江縣</button>
		<button type="button" class="btn btn-secondary wth_loc_btn" value="金門縣">金門縣</button>
		<button type="button" class="btn btn-secondary wth_loc_btn" value="澎湖縣">澎湖縣</button>
	</div>
	<div class="btn-group-vertical col-1">
		<div class="mx-auto">南部</div>
		<button type="button" class="btn btn-success wth_loc_btn" value="嘉義縣">嘉義縣</button>
		<button type="button" class="btn btn-success wth_loc_btn" value="嘉義市">嘉義市</button>
		<button type="button" class="btn btn-success wth_loc_btn" value="台南市">台南市</button>
		<button type="button" class="btn btn-success wth_loc_btn" value="高雄市">高雄市</button>
		<button type="button" class="btn btn-success wth_loc_btn" value="屏東縣">屏東縣</button>
	</div>
	<div class="btn-group-vertical col-1">
		<div class="mx-auto">中部</div>
		<button type="button" class="btn btn-danger wth_loc_btn" value="台中市">台中市</button>
		<button type="button" class="btn btn-danger wth_loc_btn" value="彰化縣">彰化縣</button>
		<button type="button" class="btn btn-danger wth_loc_btn" value="雲林縣">雲林縣</button>
		<button type="button" class="btn btn-danger wth_loc_btn" value="南投縣">南投縣</button>
	</div>
	
	<img src="<%=request.getContextPath()%>/img/TaiwanMap.png" class="mx-auto d-inline-block col-5 mt-4" alt="Responsive image">
	
	<div class="btn-group-vertical col-1">
		<div class="mx-auto">天龍國</div>
		<button type="button" class="btn btn-warning wth_loc_btn" value="基隆市">基隆市</button>
		<button type="button" class="btn btn-warning wth_loc_btn" value="新北市">新北市</button>
		<button type="button" class="btn btn-warning wth_loc_btn" value="台北市">台北市</button>
	</div>
	<div class="btn-group-vertical col-1">
		<div class="mx-auto">北部</div>
		<button type="button" class="btn btn-warning wth_loc_btn" value="桃園市">桃園市</button>
		<button type="button" class="btn btn-warning wth_loc_btn" value="新竹縣">新竹縣</button>
		<button type="button" class="btn btn-warning wth_loc_btn" value="新竹市">新竹市</button>
		<button type="button" class="btn btn-warning wth_loc_btn" value="苗栗縣">苗栗國</button>
	</div>
	<div class="btn-group-vertical col-1">
		<div class="mx-auto">東部</div>
		<button type="button" class="btn btn-primary wth_loc_btn" value="宜蘭縣">宜蘭縣</button>
		<button type="button" class="btn btn-primary wth_loc_btn" value="花蓮縣">花蓮縣</button>
		<button type="button" class="btn btn-primary wth_loc_btn" value="台東縣">台東縣</button>
	</div>
</div>