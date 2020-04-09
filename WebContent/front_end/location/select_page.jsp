<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>DA106G5 Location: Home</title>

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
			<td><h3>DA106G5 Location: Home</h3>
				<h4>( MVC )</h4></td>
		</tr>
	</table>

	<p>This is the Home page for DA106G5 Location: Home</p>

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
		<li><a href='listAllLocation.jsp'>List</a> all Locations. <br>
		<br></li>


		<li>
			<FORM METHOD="post" ACTION="location.do">
				<b>輸入地標編號 (如loc00001):</b> <input type="text" name="loc_no">
				<input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>
		<!--loc_no, loc_typeno, longitude, latitude, loc_status, loc_address, loc_pic -->
		<jsp:useBean id="locationSvc" scope="page" class="com.location.model.LocationService" />

		<li>
			<FORM METHOD="post" ACTION="location.do">
				<b>選擇地標編號:</b> <select size="1" name="loc_no">
					<c:forEach var="locationVO" items="${locationSvc.all}">
						<option value="${locationVO.loc_no}">${locationVO.loc_no}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>

		<li>
			<FORM METHOD="post" ACTION="location.do">
				<b>選擇地址:</b> 
				<select size="1" name="loc_no">
					<c:forEach var="locationVO" items="${locationSvc.all}">
						<option value="${locationVO.loc_no}">${locationVO.loc_address}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>
	</ul>


	<h3>地標管理</h3>

	<ul>
		<li><a href='addLocation.jsp'>Add</a> a new Location.</li>
	</ul>

</body>
</html>