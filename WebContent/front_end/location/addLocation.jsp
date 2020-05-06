<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.location.model.LocationVO"%>
<%@ page import="com.location.model.*"%>

<%
	LocationVO locationVO = (LocationVO) request.getAttribute("locationVO");
%>
<%-- <%=locationVO == null%> --%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>地標資料新增 - addLocation.jsp</title>

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

<!-- 	<table id="table-1"> -->
<!-- 		<tr> -->
<!-- 			<td> -->
<!-- 				<h3>地標資料新增 - addLocation.jsp</h3> -->
<!-- 			</td> -->
<!-- 			<td> -->
<!-- 				<h4> -->
<%-- 					<a href="<%= request.getContextPath() %>/front_end/location/select_page.jsp"> --%>
<%-- 						<img src="<%= request.getContextPath() %>/front_end/location/images/back1.gif" width="100" height="32" border="0"> --%>
<!-- 						回首頁 -->
<!-- 					</a> -->
<!-- 				</h4> -->
<!-- 			</td> -->
<!-- 		</tr> -->
<!-- 	</table> -->

<!-- 	<h3>資料新增:</h3> -->

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/location/location.do" name="form1" enctype="multipart/form-data" id="loc_form">
		<table>
<!-- 			<tr> -->
<!-- 				<td>類別編號:</td> -->
<!-- 								loc_no, loc_typeno, longitude, latitude, loc_status, loc_address, loc_pic -->
<!-- 				<td><input type="TEXT" name="loc_typeno" size="45" -->
<%-- 					value="<%=(locationVO == null) ? "3" : locationVO.getLoc_typeno()%>" /></td> --%>
<!-- 			</tr> -->
	<jsp:useBean id="loc_typeSvc" scope="page"	class="com.loc_type.model.Loc_typeService" />
			<div class="form-group ml-1 mb-1 mr-1">
				<label style="font-size: 1.4rem">種類: </label>
				<select style="font-size: 1.4rem" name="loc_typeno">
						<c:forEach var="loc_typeVO" items="${loc_typeSvc.all}">
							<option value="${loc_typeVO.loc_typeno}"${(locationVO.loc_typeno==loc_typeVO.loc_typeno)? 'selected':'' }>
							
							<c:choose>
							<c:when test="${ loc_typeVO.loc_info == '地點'}">
							運動場
							</c:when>
							
							<c:when test="${ loc_typeVO.loc_info == '廁所'}">
							御手洗
							</c:when>
							
							<c:otherwise>
							補水點
							</c:otherwise>
							</c:choose>
							
						</c:forEach>
				</select>
			</div>
			<div class="form-group m-1">
				<label style="font-size: 1.4rem;">名稱: </label>
				<input type="TEXT" name="loc_address" size="45"	placeholder="明確的位置(幾樓哪間)" style="font-size: 1.4rem; width:19rem;"/>
			</div>
			<div class="form-group m-1">
				<label style="font-size: 1.4rem;">經度: </label>
				<label class="lng_td" style="font-size: 1.4rem"></label >
				<input type="hidden" name="longitude" id="lng_input" value="">
			</div>
<%-- 				<td><input readonly type="TEXT" name="longitude" size="45" value="<%=(locationVO == null) ? "123.456" : locationVO.getLongitude()%>" /></td> --%>
			<div class="form-group m-1">
				<label style="font-size: 1.4rem;">緯度: </label>
				<label class="lat_td" style="font-size: 1.4rem"></label>
				<input type="hidden" name="latitude" id="lat_input" value="">
<%-- 				<td><input readonly type="TEXT" name="latitude" size="45" value="<%=(locationVO == null) ? "78.90" : locationVO.getLatitude()%>" /></td> --%>
			</div>
			<!-- 			<tr> -->
			<!-- 				<td>雇用日期:</td> -->
			<!-- 				<td><input name="hiredate" id="f_date1" type="text"></td> -->
			<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>地標狀態:</td> -->
<!-- 				<td><input type="TEXT" name="loc_status" size="45" -->
<%-- 					value="<%=(locationVO == null) ? "1" : locationVO.getLoc_status()%>" /></td> --%>
<!-- 			</tr> -->
			<div class="form-group m-1">
				<label style="font-size: 1.4rem;">照片: </label>
				<input type="file" name="loc_pic" id="upfileLoc" style="font-size: 1.4rem;"/>
			</div>
			<img alt="" src="" id="loc_insert_pic" style="height:5rem;">
<!-- 			<tr> -->
<!-- 				<td>預覽:</td> -->
<!-- 				<td><img src="/DA106_G5/NoData/none2.jpg" width="100px"></td> -->
<!-- 			</tr> -->




		</table>
		<br> 
		<div class="fblightbox-footer bg-white">
			<input type="hidden" name="action" value="insert"> 
		    <input type="submit" value="申請這地標" class="fbbutton" id="loc_submit">
			<a href="#" id="close" class="fbbutton fbclose">先不送了</a>
		 </div>
	</FORM>
<!-- 	<script> -->
<!-- 		var x = new FileReader; -->
		
<!-- 		document.forms[0].elements[5].onchange = function() { -->
<!-- 			x.readAsDataURL(this.files[0]); -->
<!-- 		} -->
<!-- 		x.onloadend = function() { -->
<!-- 			document.images[1].src = this.result; -->
<!-- 			console.log(x.herf); -->
<!-- 		} -->
<!-- 	</script> -->
</body>
<!-- jquery -->
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.2.1.min.js"></script>
<script>
// 		var x = new FileReader;
		var y = new FileReader;
// 		document.forms[0].elements[0].onchange = function() {
// 			x.readAsDataURL(this.files[0]);
// 		}
// 		$("#upfile1").onchange = function() {
// 			x.readAsDataURL(this.files[0]);
// 		}
// 		x.onloadend = function() {
// 			$("#rcd_insert_pic").src = this.result;
// 			console.log(x.herf);
// 		}
		
		document.getElementById('upfileLoc').onchange = function() {
			y.readAsDataURL(this.files[0]);
		}
		y.onloadend = function() {
			document.getElementById('loc_insert_pic').src = this.result;
		}
		
		
		
// 		document.forms[0].elements[1].onchange = function() {
// 			y.readAsDataURL(this.files[0]);
// 		}
// 		y.onloadend = function() {
// 			document.images[0].src = this.result;
// 		}
		
</script>
<script type="text/javascript">
	$('#loc_submit').click(function(e){
		e.preventDefault();
		$('.locInsertBox').hide();
		$('.overlay').hide();
		Swal.fire({
			  position: 'top: 50%; left: 50%;',
			  icon: 'success',
			  title: '你已成功提交，待審核中',
			  showConfirmButton: true,
			  timer: 3000
		}).then((result) => {$('#loc_form').submit()});
	})
</script>
</html>