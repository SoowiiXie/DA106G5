<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.od_detail.model.*"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<%
	String od_no = request.getParameter("od_no");

	Od_detailService od_detailService = new Od_detailService();

	List<Od_detailVO> list = od_detailService.search_one_oeder_detail(od_no);

	pageContext.setAttribute("list", list);
%>

<style type="text/css" media="screen">
table {
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 3px solid #FFFFFF;
}

th, td {
	padding: 1px;
	text-align: center;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<div align="center">
	<table>

		<tr>

			<th>訂單編號：</th>
			<td>${od_no}</td>
		</tr>

	</table>
	<jsp:useBean id="productService" scope="page"
		class="com.product.model.ProductService" />
		
	<table border="1">
		<tr bgcolor="#999999">
			<th Style="width: 100px; align: center">商品圖片</th>
			<th Style="width: 100px; align: center">商品編號</th>
			<th Style="width: 150px; align: center">商品名稱</th>
			<th Style="width: 100px; align: center">商品數量</th>
			<th Style="width: 100px; align: center">商品單價</th>

		</tr>
		<c:forEach var="od_detailVO" items="${list}">
			<tr>
				<td style="height: 100px; width: 100px;" ><img
					src="<%= request.getContextPath()%>/ProductPicReader?pd_no=${od_detailVO.pd_no}"
					width="100px"></td>
			
				<td width="100">${od_detailVO.pd_no}</td>
				
				<td width="100">${productService.findOneProduct(od_detailVO.pd_no).pd_name}</td>
			
				<td width="100">${od_detailVO.od_amount}</td>
			
				<td width="100">${od_detailVO.od_price}</td>
			</tr>
		</c:forEach>


	</table>
</div>


</body>
=======
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.od_detail.model.*"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<%
	String od_no = request.getParameter("od_no");

	Od_detailService od_detailService = new Od_detailService();

	List<Od_detailVO> list = od_detailService.search_one_oeder_detail(od_no);

	pageContext.setAttribute("list", list);
%>

<style type="text/css" media="screen">
table {
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 3px solid #FFFFFF;
}

th, td {
	padding: 1px;
	text-align: center;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<div align="center">
	<table>

		<tr>

			<th>訂單編號：</th>
			<td>${od_no}</td>
		</tr>

	</table>
	<jsp:useBean id="productService" scope="page"
		class="com.product.model.ProductService" />
		
	<table border="1">
		<tr bgcolor="#999999">
			<th Style="width: 100px; align: center">商品圖片</th>
			<th Style="width: 100px; align: center">商品編號</th>
			<th Style="width: 150px; align: center">商品名稱</th>
			<th Style="width: 100px; align: center">商品數量</th>
			<th Style="width: 100px; align: center">商品單價</th>

		</tr>
		<c:forEach var="od_detailVO" items="${list}">
			<tr>
				<td style="height: 100px; width: 100px;" ><img
					src="<%= request.getContextPath()%>/ProductPicReader?pd_no=${od_detailVO.pd_no}"
					width="100px"></td>
			
				<td width="100">${od_detailVO.pd_no}</td>
				
				<td width="100">${productService.findOneProduct(od_detailVO.pd_no).pd_name}</td>
			
				<td width="100">${od_detailVO.od_amount}</td>
			
				<td width="100">${od_detailVO.od_price}</td>
			</tr>
		</c:forEach>


	</table>
</div>
	<a
		href="<%=request.getContextPath()%>/back_end/product/ShopManager.jsp">回管理商城首頁</a>

</body>
>>>>>>> branch 'master' of https://github.com/SoowiiXie/DA106G5.git
</html>