<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.pd_type.model.*"%>
<%@ page import="java.util.*"%>

<%
	ProductVO productVO = (ProductVO) request.getAttribute("productVO");
%>


<%
	Pd_typeService pd_typeService = new Pd_typeService();

	List<Pd_typeVO> list = pd_typeService.getAll();

	pageContext.setAttribute("list", list);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商家商品</title>

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
<body>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<form method="POST"
		action="<%=request.getContextPath()%>/ProductServlet" name="form1"
		enctype="multipart/form-data">
		<table>

			<tr>
				<td>商品名稱：</td>
				<td><input type="TEXT" name="pd_name"
					value="${productVO.pd_name}"></td>
			</tr>
			<tr>
				<td>商品價格：</td>
				<td><input type="TEXT" name="pd_price"
					value="${productVO.pd_price}"></td>
			</tr>
			<tr>
				<td>商品詳述：</td>
				<td><input type="TEXT" name="pd_detail"
					value="${productVO.pd_detail}"></td>
			</tr>

			<%-- 			<jsp:useBean id="pd_typeService" scope="page" class="com.pd_type.model.Pd_typeService" />	 --%>
			<tr>
					<td>產品商品分類：</td>
					<td><select size="1" name="pd_typeNo">
							<c:forEach var="pd_typeVO" items="${list}">
								<option value="${pd_typeVO.pd_typeNo}" ${(productVO.pd_typeNo==pd_typeVO.pd_typeNo)? 'selected':''}>${pd_typeVO.pd_typeName}
							</c:forEach>
					</select></td>

				<%-- 			<td>產品商品分類:<font color=red><b>*</b></font></td>
		<td><select size="1" name="pd_typeNo">
			<c:forEach var="pd_typeVO" items="${pd_typeService.getAll}">
				<option value="${pd_typeVO.pd_typeNo}" ${(productVO.pd_typeNo==pd_typeVO.pd_typeNo)? 'selected':'' } >${pd_typeVO.pd_typeName}
			</c:forEach>
		</select></td>	 --%>
			</tr>
			<tr>
				<td>商品圖片</td>
				<td><input type="file" name="pd_pic" id="upfile1"></td>
			</tr>

			<tr>
				<td>預覽:</td>
				<td><img src="/DA106_G5/NoData/none2.jpg" width="100px"></td>
			</tr>


		</table>

		<input type="hidden" name="action" value="addProduct"> <input
			type="submit" name="Submit" value="增加商品">
	</form>

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





	<br>
	<br>
	<a
		href="<%=request.getContextPath()%>/back_end/product/ShopManager.jsp">回管理商城首頁</a>


</body>
</html>