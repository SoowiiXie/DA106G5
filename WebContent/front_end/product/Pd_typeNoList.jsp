<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>

<%
                
	List<ProductVO> list = (List<ProductVO>) request.getAttribute("list");

    if(list==null){
    list = (List<ProductVO>) session.getAttribute("list");
    }
    session.setAttribute("list", list);
   
%>
 
<jsp:include page="/front_end/product/ShopHomeBar.jsp" flush="ture" />
<jsp:useBean id="pd_typeService" scope="page"
	class="com.pd_type.model.Pd_typeService" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
  <%=request.getRequestURI()%>
 
<a
		href="<%=request.getContextPath()%>/front_end/product/ShopHome.jsp">商城首頁</a>
	<br>

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
			<th width="100">商品圖片</th>
			<th width="100">商品名稱</th>
			<th width="100">商品價格</th>
			<th width="100">商品類別</th>


		</tr>
		<%@ include file="page5.file"%>
		<%@ include file="page6.file"%>
		<c:forEach var="productVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td><a
					href='<%=request.getContextPath()%>/ShoppingServlet?action=findOneProduct&pd_no=${productVO.pd_no}'><img
						src="<%= request.getContextPath()%>/ProductPicReader?pd_no=${productVO.pd_no}"
						width="100px"></a></td>
				<td><a
					href='<%=request.getContextPath()%>/ShoppingServlet?action=findOneProduct&pd_no=${productVO.pd_no}'>${productVO.pd_name}</a></td>
				<td>${productVO.pd_price}</td>
				<td>${pd_typeService.searchType(productVO.pd_typeNo).pd_typeName}</td>

			</tr>

		</c:forEach>
	</table>
	<%@ include file="page6.file"%>
</body>
</html>