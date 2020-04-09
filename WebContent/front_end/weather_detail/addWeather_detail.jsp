<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.weather_detail.model.Weather_detailVO"%>
<%@ page import="com.weather.model.WeatherVO"%>
<%@ page import="com.weather_detail.model.*"%>
<%@ page import="com.weather.model.*"%>

<%
	Weather_detailVO weather_detailVO = (Weather_detailVO) request.getAttribute("weather_detailVO");
%>
<%=weather_detailVO == null%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>天氣資料新增 - addLocation.jsp</title>

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
				<h3>天氣資料新增 - addWeather_detail.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="<%= request.getContextPath() %>/front_end/weather_detail/select_page.jsp">
						<img src="<%= request.getContextPath() %>/front_end/weather_detail/images/back1.gif" width="100" height="32" border="0">
						回首頁
					</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料新增:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="weather_detail.do" name="form1"	enctype="multipart/form-data">
		<table>
			<!--// weather_time, weather_place, wth_status, wth_high, wth_low, wth_comfort,
			// wth_rain_chance -->
			<tr>
				<td>時間:<font color=red><b>*</b></font></td>
				<td><input name="weather_time" id="f_date1" type="text"></td>
			</tr>
			<tr>
				<td>地點:<font color=red><b>*</b></font></td>
				<td><input type="TEXT" name="weather_place" size="45"
					value="<%=(weather_detailVO == null) ? "健志溫暖的小窩" : weather_detailVO.getWeather_place()%>" /></td>
			</tr>
			<jsp:useBean id="weatherSvc" scope="page" class="com.weather.model.WeatherService" />
			<tr>
				<td>天氣:</td>
				<td><select size="1" name=wth_status>
						<c:forEach var="weatherVO" items="${weatherSvc.all}">
							<option value="${weatherVO.wth_status}"${(weather_detailVO.wth_status==weatherVO.wth_status)? 'selected':'' }>
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
		<br> <input type="hidden" name="action" value="insert"> 
		<input type="submit" value="送出新增">
	</FORM>
</body>


<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<%
	java.sql.Date weather_time = null;
	// 	try {
	// 		hiredate = empVO.getHiredate();
	// 	} catch (Exception e) {
	weather_time = new java.sql.Date(System.currentTimeMillis());
	// 	}
%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
		   value: '<%=weather_time%>'	// value:   new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	//minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});

	// ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

	//      1.以下為某一天之前的日期無法選擇
	     var somedate1 = new Date();
	     var somedate2 = new Date();
	     somedate2=new Date(somedate2.valueOf() + (3 * 24 * 60 * 60 * 1000));
	     $('#f_date1').datetimepicker({
	         beforeShowDay: function(date) {
	       	  if (  date.getYear() <  somedate1.getYear() || 
			           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
			           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate()) || 
			           date.getYear() >  somedate2.getYear() || 
			           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
			           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	       	  ) {
	                  return [false, ""]
	             }
	             return [true, ""];
	     }});

	//      2.以下為某一天之後的日期無法選擇
// 	     var somedate2 = new Date();
// 	     somedate2=new Date(somedate2.valueOf() + (3 * 24 * 60 * 60 * 1000));
// 	     $('#f_date1').datetimepicker({
// 	         beforeShowDay: function(date) {
// 	       	  if (  date.getYear() >  somedate2.getYear() || 
// 			           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
// 			           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
// 	             ) {
// 	                  return [false, ""]
// 	             }
// 	             return [true, ""];
// 	     }});

	//      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
	//      var somedate1 = new Date('2017-06-15');
	//      var somedate2 = new Date('2017-06-25');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//		             ||
	//		            date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});
</script>
</html>