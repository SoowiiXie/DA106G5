<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.od_detail.model.*"%>
<!DOCTYPE html>
<html>
<head>


<%
	String od_no = request.getParameter("od_no");

	Od_detailService od_detailService = new Od_detailService();

	List<Od_detailVO> list = od_detailService.search_one_oeder_detail(od_no);
	
	                     pageContext.setAttribute("list", list);
	
%>


<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>



	<table>

		<tr>

			<th>訂單編號：</th>
			<td>${od_no}</td>
		</tr>

	</table>
<jsp:useBean id="productService" scope="page" class="com.product.model.ProductService" />
	<table border="1">

			<c:forEach var="od_detailVO" items="${list}">
		<tr>
		    <td><img src="<%= request.getContextPath()%>/ProductPicReader?pd_no=${od_detailVO.pd_no}" width="100px"></td>
			<td width="100">商品編號：</td><td width="100">${od_detailVO.pd_no}</td>
            <td width="100">商品名稱：</td><td width="100">${productService.findOneProduct(od_detailVO.pd_no).pd_name}</td>
            <td width="100">商品數量:</td><td width="100">${od_detailVO.od_amount}</td>
            <td width="100">商品單價:</td><td width="100">${od_detailVO.od_price}</td>
		</tr>
</c:forEach>


	</table>

	<a
		href="<%=request.getContextPath()%>/back_end/product/ShopManager.jsp">回管理商城首頁</a>

</body>
</html>