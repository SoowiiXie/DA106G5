<%@page import="java.lang.ProcessBuilder.Redirect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- <div class="col-3">&nbsp&nbsp個人頁面</div> -->
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
	<button type="button" class="btn btn-secondary">連江縣</button>
	<button type="button" class="btn btn-secondary">金門縣</button>
	<button type="button" class="btn btn-secondary">澎湖縣</button>
</div>
<div class="btn-group-vertical col-1">
	<div class="mx-auto">南部</div>
	<button type="button" class="btn btn-success">嘉義縣</button>
	<button type="button" class="btn btn-success">嘉義市</button>
	<button type="button" class="btn btn-success">台南市</button>
	<button type="button" class="btn btn-success">高雄市</button>
	<button type="button" class="btn btn-success">屏東縣</button>
</div>
<div class="btn-group-vertical col-1">
	<div class="mx-auto">中部</div>
	<button type="button" class="btn btn-danger">台中市</button>
	<button type="button" class="btn btn-danger">彰化縣</button>
	<button type="button" class="btn btn-danger">雲林縣</button>
	<button type="button" class="btn btn-danger">南投縣</button>
</div>

<img src="<%=request.getContextPath()%>/img/TaiwanMap.png"
	class="mx-auto d-inline-block col-5 mt-4" alt="Responsive image">

<div class="btn-group-vertical col-1">
	<div class="mx-auto">天龍國</div>
	<button type="button" class="btn btn-warning">基隆市</button>
	<button type="button" class="btn btn-warning">新北市</button>
	<button type="button" class="btn btn-warning">台北市</button>
</div>
<div class="btn-group-vertical col-1">
	<div class="mx-auto">北部</div>
	<button type="button" class="btn btn-warning">桃園市</button>
	<button type="button" class="btn btn-warning">新竹縣</button>
	<button type="button" class="btn btn-warning">新竹市</button>
	<button type="button" class="btn btn-warning">苗栗國</button>
</div>
<div class="btn-group-vertical col-1">
	<div class="mx-auto">東部</div>
	<button type="button" class="btn btn-primary">宜蘭縣</button>
	<button type="button" class="btn btn-primary">花蓮縣</button>
	<button type="button" class="btn btn-primary">台東縣</button>
</div>