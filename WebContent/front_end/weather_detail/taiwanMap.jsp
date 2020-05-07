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
	<div class="btn-group-vertical col-1" >
		<div class="mx-auto" data-aos="fade-right" data-aos-duration="1800">離島</div>
		<button type="button" class="btn btn-secondary wth_loc_btn" value="連江縣" data-aos="fade-right" data-aos-duration="2200">連江縣</button>
		<button type="button" class="btn btn-secondary wth_loc_btn" value="金門縣" data-aos="fade-right" data-aos-duration="2600">金門縣</button>
		<button type="button" class="btn btn-secondary wth_loc_btn" value="澎湖縣" data-aos="fade-right" data-aos-duration="3000">澎湖縣</button>
	</div>
	<div class="btn-group-vertical col-1">
		<div class="mx-auto" data-aos="fade-right" data-aos-duration="1000">南部</div>
		<button type="button" class="btn btn-success wth_loc_btn" value="嘉義縣" data-aos="fade-right" data-aos-duration="1400">嘉義縣</button>
		<button type="button" class="btn btn-success wth_loc_btn" value="嘉義市" data-aos="fade-right" data-aos-duration="1800">嘉義市</button>
		<button type="button" class="btn btn-success wth_loc_btn" value="臺南市" data-aos="fade-right" data-aos-duration="2200">臺南市</button>
		<button type="button" class="btn btn-success wth_loc_btn" value="高雄市" data-aos="fade-right" data-aos-duration="2600">高雄市</button>
		<button type="button" class="btn btn-success wth_loc_btn" value="屏東縣" data-aos="fade-right" data-aos-duration="3000">屏東縣</button>
	</div>
	<div class="btn-group-vertical col-1">
		<div class="mx-auto" data-aos="fade-right" data-aos-duration="1000">中部</div>
		<button type="button" class="btn btn-danger wth_loc_btn" value="臺中市" data-aos="fade-right" data-aos-duration="1400">臺中市</button>
		<button type="button" class="btn btn-danger wth_loc_btn" value="彰化縣" data-aos="fade-right" data-aos-duration="1800">彰化縣</button>
		<button type="button" class="btn btn-danger wth_loc_btn" value="雲林縣" data-aos="fade-right" data-aos-duration="2200">雲林縣</button>
		<button type="button" class="btn btn-danger wth_loc_btn" value="南投縣" data-aos="fade-right" data-aos-duration="2600">南投縣</button>
	</div>
	
	<img data-aos="fade-up" data-aos-duration="700" src="<%=request.getContextPath()%>/img/TaiwanMap.png" class="mx-auto d-inline-block col-5 mt-4" alt="Responsive image">
	
	<div class="btn-group-vertical col-1">
		<div class="mx-auto" data-aos="fade-left" data-aos-duration="1000">天龍國</div>
		<button type="button" class="btn btn-warning wth_loc_btn" value="基隆市" data-aos="fade-left" data-aos-duration="1400">基隆市</button>
		<button type="button" class="btn btn-warning wth_loc_btn" value="新北市" data-aos="fade-left" data-aos-duration="1800">新北市</button>
		<button type="button" class="btn btn-warning wth_loc_btn" value="臺北市" data-aos="fade-left" data-aos-duration="2200">臺北市</button>
	</div>
	<div class="btn-group-vertical col-1" data-aos="fade-left" data-aos-duration="1400">
		<div class="mx-auto">北部</div>
		<button type="button" class="btn btn-warning wth_loc_btn" value="桃園市" data-aos="fade-left" data-aos-duration="1800">桃園市</button>
		<button type="button" class="btn btn-warning wth_loc_btn" value="新竹縣" data-aos="fade-left" data-aos-duration="2200">新竹縣</button>
		<button type="button" class="btn btn-warning wth_loc_btn" value="新竹市" data-aos="fade-left" data-aos-duration="2600">新竹市</button>
		<button type="button" class="btn btn-warning wth_loc_btn" value="苗栗縣" data-aos="fade-left" data-aos-duration="3000">苗栗國</button>
	</div>
	<div class="btn-group-vertical col-1">
		<div class="mx-auto" data-aos="fade-left" data-aos-duration="1800">東部</div>
		<button type="button" class="btn btn-primary wth_loc_btn" value="宜蘭縣" data-aos="fade-left" data-aos-duration="2200">宜蘭縣</button>
		<button type="button" class="btn btn-primary wth_loc_btn" value="花蓮縣" data-aos="fade-left" data-aos-duration="2600">花蓮縣</button>
		<button type="button" class="btn btn-primary wth_loc_btn" value="台東縣" data-aos="fade-left" data-aos-duration="3000">台東縣</button>
	</div>
</div>