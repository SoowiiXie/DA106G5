<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.weather_detail.model.Weather_detailVO"%>
<%@ page import="com.weather_detail.model.Weather_detailService"%>
<%@ page import="com.weather_detail.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	@SuppressWarnings("unchecked")
	List<Weather_detailVO> list = (List<Weather_detailVO>)request.getAttribute("weather_detailVO_list");
	if(list==null || list.size() == 0){
		list=(List<Weather_detailVO>)session.getAttribute("list");
	}
	session.setAttribute("list", list);
%>


<html>
<head>
<title>所有天氣資料 - listAllWeather_detail.jsp</title>

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
				<h3>所有天氣資料 - listAllUWish.jsp</h3>
				<h4>
					<a href="<%= request.getContextPath() %>/front_end/weather_detail/select_page.jsp">
						<img src="<%= request.getContextPath() %>/front_end/weather_detail/images/back1.gif" width="100" height="32" border="0">
						回首頁
					</a>
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
			<th>時間</th>
			<th>地點</th>
			<th>天氣</th>
			<th>最高溫</th>
			<th>最低溫</th>
			<th>舒適度</th>
			<th>降雨機率</th>
			<th>修改</th>
			<th>刪除</th>
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="weather_detailVO" items="${weather_detailVO_list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
<%-- 		<c:forEach var="weather_detailVO" items="${list}" > --%>
			<!--// weather_time, weather_place, wth_status, wth_high, wth_low, wth_comfort,
			// wth_rain_chance -->
			<tr>
				<td>${weather_detailVO.weather_time}</td>
				<td>${weather_detailVO.weather_place}</td>
				<td>${weather_detailVO.wth_status}</td>
				<td>${weather_detailVO.wth_high}</td>
				<td>${weather_detailVO.wth_low}</td>
				<td>${weather_detailVO.wth_comfort}</td>
				<td>${weather_detailVO.wth_rain_chance}%</td>
				<td>
					<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/weather_detail/weather_detail.do"	style="margin-bottom: 0px;">
						<input type="submit" value="修改"> 
						<input type="hidden" name="weather_time" value="${weather_detailVO.weather_time}"> 
						<input type="hidden" name="weather_place" value="${weather_detailVO.weather_place}"> 
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/weather_detail/weather_detail.do"	style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> 
						<input type="hidden" name="weather_time" value="${weather_detailVO.weather_time}"> 
						<input type="hidden" name="weather_place" value="${weather_detailVO.weather_place}"> 
						<input type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>

</body>
</html>