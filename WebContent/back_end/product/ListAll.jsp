<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.Base64"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>


<!DOCTYPE html>
<%
	ProductService productService = new ProductService();

	List<ProductVO> list = productService.getAll();

	 pageContext.setAttribute("list", list); 
%>

<jsp:useBean id="pd_typeService" scope="page" class="com.pd_type.model.Pd_typeService" />
<html>
<head>
<meta charset="UTF-8">
<title>後端管理頁面</title>
</head>
<body>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

	<table border="1">

		<tr>
            <th width="100">商品圖片</th>
			<th width="100">商品編號</th>
			<th width="100">商品名稱</th>
			<th width="100">商品價格</th>
			<th width="100">商品詳述</th>
			<th width="100">商品狀態</th>
			<th width="100">商品類別</th>
			<th width="100"></th>
			<th width="100"></th>

		</tr>
<%@ include file="page1.file" %>
<br> 
<%@ include file="page2.file" %>
<c:forEach var="productVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
<tr>
            <td><img src="<%= request.getContextPath()%>/ProductPicReader?pd_no=${productVO.pd_no}" width="100px"></td>
			<td>${productVO.pd_no}</td>
			<td>${productVO.pd_name}</td>
			<td>${productVO.pd_price}</td>
			<td>${productVO.pd_detail}</td>
			<td>${productVO.pd_status==1?'下架':'上架'}</td>
			<td>${pd_typeService.searchType(productVO.pd_typeNo).pd_typeName}</td> 
			
			
			<td><FORM METHOD="POST" ACTION="<%=request.getContextPath()%>/ProductServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="pd_no"  value="${productVO.pd_no}">
			      <input type="hidden" name="pd_name"  value="${productVO.pd_name}">
			     <input type="hidden" name="action"	value="getOne_For_update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ProductServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="pd_no"  value="${productVO.pd_no}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>

</c:forEach>
	</table>
	<%@ include file="page2.file" %>
	
	<br>
	<br>
	<a href="<%=request.getContextPath()%>/back_end/product/ShopManager.jsp">回管理商城首頁</a>
	
</body>
</html>