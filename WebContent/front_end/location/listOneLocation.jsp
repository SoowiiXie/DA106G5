<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.location.model.LocationVO"%>
<%@ page import="com.location.model.LocationService"%>
<%@ page import="com.location.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	LocationVO locationVO = (LocationVO) request.getAttribute("locationVO");
	//EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>員工資料 - listOneLocation.jsp</title>

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
				<h3>員工資料 - ListOneLocation.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>地標編號</th>
			<th>地標類別編號</th>
			<th>經度</th>
			<th>緯度</th>
			<th>地標狀態</th>
			<th>地址</th>
			<th>圖片</th>
		</tr>
		<tr>
			<td><%=locationVO.getLoc_no()%></td>
			<td><%=locationVO.getLoc_typeno()%></td>
			<td><%=locationVO.getLongitude()%></td>
			<td><%=locationVO.getLatitude()%></td>
			<td><%=locationVO.getLoc_status()%></td>
			<td><%=locationVO.getLoc_address()%></td>
			<td><%=locationVO.getLoc_pic()%></td>
		</tr>
	</table>

</body>
</html>