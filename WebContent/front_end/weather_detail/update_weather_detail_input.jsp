<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.weather_detail.model.Weather_detailVO"%>
<%@ page import="com.weather.model.WeatherVO"%>
<%@ page import="com.weather_detail.model.*"%>
<%@ page import="com.weather.model.*"%>
<%@ page import="java.util.*" %>

<%
	List<Weather_detailVO> weather_detailVO_list = (List<Weather_detailVO>)request.getAttribute("weather_detailVO_list");
	Weather_detailVO weather_detailVO = (Weather_detailVO)weather_detailVO_list.toArray()[0];
	//EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>天氣資料修改 - update_weather_detail_input.jsp</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>地標資料修改 - update_weather_detail_input.jsp</h3>
				<h4>
					<a href="/DA106_G5/front_end/weather_detail/select_page.jsp"> <img
						src="/DA106_G5/front_end/weather_detail/images/back1.gif"
						width="100" height="32" border="0"> 回首頁
					</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料修改:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<FORM METHOD="post" ACTION="weather_detail.do" name="form1"
		enctype="multipart/form-data">
		<table>
			<!--// weather_time, weather_place, wth_status, wth_high, wth_low, wth_comfort,
			// wth_rain_chance -->
			<tr>
				<td>時間:</td>
				<td><%=weather_detailVO.getWeather_time()%></td>
			</tr>
			<tr>
				<td>地點:</td>
				<td><%=weather_detailVO.getWeather_place()%></td>
			</tr>
			<jsp:useBean id="weatherSvc" scope="page"
				class="com.weather.model.WeatherService" />
			<tr>
				<td>天氣:<font color=red><b>*</b></font></td>
				<td><select size="1" name=wth_status>
						<c:forEach var="weatherVO" items="${weatherSvc.all}">
							<option value="${weatherVO.wth_status}"
								${(weather_detailVO.wth_status==weatherVO.wth_status)? 'selected':'' }>
								${weatherVO.wth_status}
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>最高溫:</td>
				<td><input type="TEXT" name="wth_high" size="45"
					value="<%=(weather_detailVO == null) ? "777" : weather_detailVO.getWth_high()%>" /></td>
			</tr>
			<tr>
				<td>最低溫:</td>
				<td><input type="TEXT" name="wth_low" size="45"
					value="<%=(weather_detailVO == null) ? "-777" : weather_detailVO.getWth_low()%>" /></td>
			</tr>
			<tr>
				<td>舒適度:</td>
				<td><input type="TEXT" name="wth_comfort" size="45"
					value="<%=(weather_detailVO == null) ? "超級雞巴爽" : weather_detailVO.getWth_comfort()%>" /></td>
			</tr>
			<tr>
				<td>降雨機率:</td>
				<td><input type="TEXT" name="wth_rain_chance" size="45"
					value="<%=(weather_detailVO == null) ? "100" : weather_detailVO.getWth_rain_chance()%>" /></td>
			</tr>

		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="weather_time"
			value="<%=weather_detailVO.getWeather_time()%>"> <input
			type="hidden" name="weather_place"
			value="<%=weather_detailVO.getWeather_place()%>"> <input
			type="submit" value="送出修改">
	</FORM>
</body>
</html>