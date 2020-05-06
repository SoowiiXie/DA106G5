<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.location.model.LocationVO"%>
<%@ page import="com.location.model.LocationService"%>
<%@ page import="com.location.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	LocationService locationSvc = new LocationService();
	List<LocationVO> list = locationSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>所有地標資料 - listAllLocation.jsp</title>

	<!-- Custom fonts for this template-->
	<link href="<%= request.getContextPath() %>/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css" />
	<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet" />

	<!-- Custom styles for this template-->
	<link href="<%= request.getContextPath() %>/css/sb-admin-2.min.css" rel="stylesheet" />
	
	<!-- modal -->
<!-- 	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> -->
<!-- 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> -->
<!-- 	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->
	
	<!-- 會員智慧搜尋 -->
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	
	<!-- switch button -->
	<link href="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.6.1/css/bootstrap4-toggle.min.css" rel="stylesheet">
	
	<!-- index.css -->
	<link href="<%= request.getContextPath() %>/css/index.css" rel="stylesheet" />
	
	<!-- sweet alert -->
	<link href="//cdn.jsdelivr.net/npm/@sweetalert2/theme-dark@3/dark.css" rel="stylesheet">
	
	<!-- 彈跳div -->
	<link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}

	#allStaff_title{
		font-family: 'italics_hollow';
		line-height:10vh;
		font-size: 5.5vh;
		font-weight:bold;
		letter-spacing: 0.3vw;
		color:#0373f0;
		border-bottom:3px solid #0373f0;
	}
</style>

</head>
<body bgcolor='white'>

<!-- 	<h4>此頁練習採用 EL 的寫法取值:</h4> -->
<!-- 	<table id="table-1"> -->
<!-- 		<tr> -->
<!-- 			<td> -->
<!-- 				<h3>所有員工資料 - listAllLocation.jsp</h3> -->
<!-- 				<h4> -->
<%-- 					<a href="<%= request.getContextPath() %>/front_end/location/select_page.jsp"> --%>
<%-- 						<img src="<%= request.getContextPath() %>/front_end/location/images/back1.gif" width="100" height="32" border="0"> --%>
<!-- 						回首頁 -->
<!-- 					</a> -->
<!-- 				</h4> -->
<!-- 			</td> -->
<!-- 		</tr> -->
<!-- 	</table> -->

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<div class="mx-auto" style="width: 10.5rem;">
		<span id="allStaff_title" class="d-block mx-auto">&ensp;&ensp;地標</span>
	</div>
	<!--loc_no, loc_typeno, longitude, latitude, loc_status, loc_address, loc_pic -->
	<table class="table table-hover table-striped col-10 mx-auto">
		<tr>
			<th>地標編號</th>
			<th>地標類別編號</th>
			<th>經度</th>
			<th>緯度</th>
			<th>地標狀態</th>
			<th>地址</th>
			<th>圖片</th>
			<th>上架/下架</th>
		</tr>
		<%@ include file="pages/page1.file"%>
		<c:forEach var="locationVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
<%-- 		<c:forEach var="locationVO" items="${list}"> --%>
	<!--loc_no, loc_typeno, longitude, latitude, loc_status, loc_address, loc_pic -->
			<tr>
				<td>${locationVO.loc_no}</td>
				<td>${locationVO.loc_typeno}</td>
				<td>${locationVO.longitude}</td>
				<td>${locationVO.latitude}</td>
				<td>${(locationVO.loc_status==1?'上架':'下架')}</td>
				<td>${locationVO.loc_address}</td>
				<td><img src="<%= request.getContextPath() %>/DBGifReader4Location?loc_no=${locationVO.loc_no}" width="100px"></td>
				<td>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/location/location.do" style="margin-bottom: 0px;">
						<input type="submit" value="上架/下架"> 
						<input type="hidden" name="loc_no" value="${locationVO.loc_no}"> 
						<input type="hidden" name="loc_status" value="${locationVO.loc_status}"> 
						<input type="hidden" name="includePath" value="${includePath}">
						<input type="hidden" name="action" value="fakeDelete">
					</FORM>
				</td>
<!-- 				<td> -->
<!-- 					<FORM METHOD="post" -->
<%-- 						ACTION="<%=request.getContextPath()%>/location/location.do" --%>
<!-- 						style="margin-bottom: 0px;"> -->
<!-- 						<input type="submit" value="刪除">  -->
<%-- 						<input type="hidden" name="loc_no" value="${locationVO.loc_no}">  --%>
<!-- 						<input type="hidden" name="action" value="delete"> -->
<!-- 					</FORM> -->
<!-- 				</td> -->
			</tr>
		</c:forEach>
	</table>
	<div class="mx-auto" style="width: 30rem;">
		<%@ include file="pages/page2.file"%>
	</div>
</body>
</html>