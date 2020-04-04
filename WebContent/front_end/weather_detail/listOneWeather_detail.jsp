<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.weather_detail.model.Weather_detailVO"%>
<%@ page import="com.weather_detail.model.Weather_detailService"%>
<%@ page import="com.weather_detail.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	Weather_detailVO weather_detailVO = (Weather_detailVO) request.getAttribute("weather_detailVO");
	//EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>員工資料 - listOneWeather_detail.jsp</title>

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
	width: 600px;
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

	<h4>此頁暫練習採用 Script 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>員工資料 - ListOneWeather_detail.jsp</h3>
				<h4>
					<a href="/DA106_G5/front_end/weather_detail/select_page.jsp">
						<img src="/DA106_G5/front_end/weather_detail/images/back1.gif" width="100" height="32" border="0">
						回首頁
					</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>時間</th>
			<th>地點</th>
			<th>天氣</th>
			<th>最高溫</th>
			<th>最低溫</th>
			<th>舒適度</th>
			<th>降雨機率</th>
		</tr>
		<!--// weather_time, weather_place, wth_status, wth_high, wth_low, wth_comfort,
			// wth_rain_chance -->
		<tr>
			<td><%=weather_detailVO.getWeather_time()%></td>
			<td><%=weather_detailVO.getWeather_place()%></td>
			<td><%=weather_detailVO.getWth_status()%></td>
			<td><%=weather_detailVO.getWth_high()%></td>
			<td><%=weather_detailVO.getWth_low()%></td>
			<td><%=weather_detailVO.getWth_comfort()%></td>
			<td><%=weather_detailVO.getWth_rain_chance()%></td>
		</tr>
	</table>

</body>
</html>