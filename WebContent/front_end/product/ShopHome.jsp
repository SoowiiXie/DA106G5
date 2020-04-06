<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%
	ProductService productService = new ProductService();

	List<ProductVO> list = productService.listOnTheMarket(2);

	pageContext.setAttribute("list", list);
%>



<html>
<head>
<meta charset="UTF-8">
<title>列出所有商品</title>
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
	<table border="3">

		<tr>

			<th width="100">商品編號</th>
			<th width="100">商品名稱</th>
			<th width="100">商品價格</th>
			<th width="100">商品詳述</th>
			<th width="100">商品狀態</th>
			<th width="100">商品類別</th>
			

		</tr>
		<%@ include file="page3.file"%>
		<c:forEach var="productVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">
		
			<tr>
				<td>${productVO.pd_no}</td>
				<td><a href='<%=request.getContextPath()%>/ShoppingServlet?action=findOneProduct&pd_no=${productVO.pd_no}'>${productVO.pd_name}</a></td>
				<td>${productVO.pd_price}</td>
				<td>${productVO.pd_detail}</td>
				<td>${productVO.pd_status}</td>
				<td>${productVO.pd_typeNo}</td>




			</tr>

		</c:forEach>
	</table>
	<%@ include file="page4.file"%>

	<br>
	<br>
	<a href="<%=request.getContextPath()%>/back_end/product/ShopManager.jsp">回管理商城首頁</a>
</body>
</html>