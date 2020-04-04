<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.location.model.LocationVO"%>
<%@ page import="com.location.model.LocationService"%>
<%@ page import="com.location.model.*"%>

<%
	LocationVO locationVO = (LocationVO) request.getAttribute("locationVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>員工資料修改 - update_location_input.jsp</title>

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
				<h3>地標資料修改 - update_location_input.jsp</h3>
				<h4>
					<a href="../front_end/location/select_page.jsp"><img
						src="../front_end/location/images/back1.gif" width="100"
						height="32" border="0">回首頁</a>
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
	<!--loc_no, loc_typeno, longitude, latitude, loc_status, loc_address, loc_pic -->
	<FORM METHOD="post" ACTION="location.do" name="form1" enctype="multipart/form-data">
		<table>
			<tr>
				<td>地標編號:<font color=red><b>*</b></font></td>
				<td><%=locationVO.getLoc_no()%></td>
			</tr>
			<jsp:useBean id="loc_typeSvc" scope="page"
				class="com.loc_type.model.Loc_typeService" />
			<tr>
				<td>類別編號:<font color=red><b>*</b></font></td>
				<td><select size="1" name="loc_typeno">
						<c:forEach var="loc_typeVO" items="${loc_typeSvc.all}">
							<option value="${loc_typeVO.loc_typeno}"
								${(locationVO.loc_typeno==loc_typeVO.loc_typeno)? 'selected':'' }>${loc_typeVO.loc_info}
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>經度:</td>
				<td><input type="TEXT" name="longitude" size="45"
					value="<%=locationVO.getLongitude()%>" /></td>
			</tr>
			<!-- 	<tr> -->
			<!-- 		<td>雇用日期:</td> -->
			<!-- 		<td><input name="hiredate" id="f_date1" type="text" ></td> -->
			<!-- 	</tr> -->
			<tr>
				<td>緯度:</td>
				<td><input type="TEXT" name="latitude" size="45"
					value="<%=locationVO.getLatitude()%>" /></td>
			</tr>
			<tr>
				<td>地標狀態:</td>
				<td><input type="TEXT" name="loc_status" size="45"
					value="<%=locationVO.getLoc_status()%>" /></td>
			</tr>
			<tr>
				<td>地址:</td>
				<td><input type="TEXT" name="loc_address" size="45"
					value="<%=locationVO.getLoc_address()%>" /></td>
			</tr>
			<tr>
				<td>地標圖片:</td>
				<td><input type="file" name="loc_pic" id="upfile1"/></td>
			</tr>
			<tr>
				<td>預覽:</td>
				<td><img src="/DA106_G5/DBGifReader4Location?loc_no=<%=locationVO.getLoc_no()%>" width="100px"></td>
			</tr>

		</table>
		<br> <input type="hidden" name="action" value="update"> 
		<input type="hidden" name="loc_no" value="<%=locationVO.getLoc_no()%>">
		<input type="submit" value="送出修改">
	</FORM>
	<script>
		var x = new FileReader;

		document.forms[0].elements[5].onchange = function() {
			x.readAsDataURL(this.files[0]);
		}
		x.onloadend = function() {
			document.images[1].src = this.result;
			console.log(x.herf);
		}
	</script>
</body>
</html>