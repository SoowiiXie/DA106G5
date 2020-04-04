<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>DA106G5 Weather_detail: Home</title>

<style>
table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 3px ridge Gray;
	height: 80px;
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

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td><h3>DA106G5 Weather_detail: Home</h3>
				<h4>( MVC )</h4></td>
		</tr>
	</table>

	<p>This is the Home page for DA106G5 Weather_detail: Home</p>

	<h3>資料查詢:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<ul>
		<li><a href='listAllWeather_detail.jsp'>List</a> all Weather_details. <br> <br></li>
		<li>
			<FORM METHOD="post" ACTION="weather_detail.do">
				<b>輸入地點 (如台北):</b> <input type="text" name="weather_place"><br>
				<b>輸入時間:</b> <input name="weather_time" id="f_date1" type="text">
				<input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>
		<!--// weather_time, weather_place, wth_status, wth_high, wth_low, wth_comfort,
			// wth_rain_chance -->
		<jsp:useBean id="weather_detailSvc" scope="page" class="com.weather_detail.model.Weather_detailService" />
		<li>
			<FORM METHOD="post" ACTION="weather_detail.do">
				<b>選擇欲查詢地點:</b> 
				<select size="1" name="weather_place">
					<c:forEach var="weather_detailVO" items="${weather_detailSvc.all}">
						<option value="${weather_detailVO.weather_place}">${weather_detailVO.weather_place}
					</c:forEach>
				</select> 
				<input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>

		<li>
			<FORM METHOD="post" ACTION="weather_detail.do">
				<b>選擇欲查詢時間:</b> 
				<select size="1" name="weather_place">
					<c:forEach var="weather_detailVO" items="${weather_detailSvc.all}">
						<option value="${weather_detailVO.weather_time}">${weather_detailVO.weather_time}
					</c:forEach>
				</select> 
				<input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>
	</ul>


	<h3>天氣管理</h3>

	<ul>
		<li><a href='addWeather_detail.jsp'>Add</a> a new Weather_detail.</li>
	</ul>

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