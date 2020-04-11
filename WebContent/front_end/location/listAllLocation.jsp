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
				<h3>所有員工資料 - listAllLocation.jsp</h3>
				<h4>
					<a href="<%= request.getContextPath() %>/front_end/location/select_page.jsp">
						<img src="<%= request.getContextPath() %>/front_end/location/images/back1.gif" width="100" height="32" border="0">
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
	<!--loc_no, loc_typeno, longitude, latitude, loc_status, loc_address, loc_pic -->
	<table>
		<tr>
			<th>地標編號</th>
			<th>地標類別編號</th>
			<th>經度</th>
			<th>緯度</th>
			<th>地標狀態</th>
			<th>地址</th>
			<th>圖片</th>
			<th>修改</th>
			<th>刪除</th>
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="locationVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">
	<!--loc_no, loc_typeno, longitude, latitude, loc_status, loc_address, loc_pic -->
			<tr>
				<td>${locationVO.loc_no}</td>
				<td>${locationVO.loc_typeno}</td>
				<td>${locationVO.longitude}</td>
				<td>${locationVO.latitude}</td>
				<td>${(locationVO.loc_status==1?'上架':'下架')}</td>
				<td>${locationVO.loc_address}</td>
				<td><img src="/DA106_G5/DBGifReader4Location?loc_no=${locationVO.loc_no}" width="100px"></td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/location/location.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> 
						<input type="hidden" name="loc_no" value="${locationVO.loc_no}"> 
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/location/location.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> 
						<input type="hidden" name="loc_no" value="${locationVO.loc_no}"> 
						<input type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>

</body>
</html>