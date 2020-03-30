<%@page import="com.soowii.weather.model.Weather_detailVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	//List<Weather_detailVO> list = (List<Weather_detailVO>)session.getAttribute("list"); 
	//EmpServlet.java(Concroller), 存入session的list物件
%>

<%-- 以下等同第8行--%>
<jsp:useBean id="list" scope="session" type="java.util.List<Weather_detailVO>" />
<%-- <jsp:useBean id="list" scope="session" --%>
<%-- 	class="com.soowii.weather.model.Weather_detailDAO" --%>
<%-- 	type="java.util.List<Weather_detailVO>" /> --%>

<html>
<head>
<title>所有員工資料 - listAllEmp2_getFromSession.jsp</title>

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
</style>

</head>
<body bgcolor='white'>

	<h4>此頁練習採用 EL 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>所有員工資料 - listAllEmp2_getFromSession.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table>
		<tr>
			<th>員工編號</th>
			<th>員工姓名</th>
			<th>職位</th>
			<th>雇用日期</th>
			<th>薪水</th>
			<th>獎金</th>
			<th>部門</th>
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="Weather_detailVO" items="${list}"
			begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			<tr>
				<td>${Weather_detailVO.weather_time}</td>
				<td>${Weather_detailVO.weather_place}</td>
				<td>${Weather_detailVO.wth_status}</td>
				<td>${Weather_detailVO.wth_high}</td>
				<td>${Weather_detailVO.wth_low}</td>
				<td>${Weather_detailVO.wth_comfort}</td>
				<td>${Weather_detailVO.wth_rain_chance}</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>

</body>
</html>