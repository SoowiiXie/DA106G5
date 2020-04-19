<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.Base64"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


<%

	String pd_typeNo = (String) session.getAttribute("pd_typeNo");
	ProductService productService = new ProductService();
	List<ProductVO> list = (List<ProductVO>)session.getAttribute("list");
	
	if (pd_typeNo == null || pd_typeNo.equals("")) {

		list = productService.getAll();
		
		
	
		Collections.reverse(list);
		
		session.setAttribute("list", list);
	
		
	}
		else {
		list = productService.useTypeSearchProducts(pd_typeNo);
		Collections.reverse(list);
		session.setAttribute("list", list);
	}
	
	
%>



<jsp:useBean id="pd_typeService" scope="page" class="com.pd_type.model.Pd_typeService" />
</head>
<body>

現在的session.pd_typeNo是 ${pd_typeNo}
</br>
  <%=request.getRequestURI()%>
  </br> 
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
<a href="<%=request.getContextPath()%>/ProductServlet?action=searchTypeList&&pd_typeNo=">管理所有商品2</a>
<a href="<%=request.getContextPath()%>/ProductServlet?action=searchTypeList&&pd_typeNo=PTN00001">服飾-男上身</a>
<a href="<%=request.getContextPath()%>/ProductServlet?action=searchTypeList&&pd_typeNo=PTN00002">服飾-男下身</a>
<a href="<%=request.getContextPath()%>/ProductServlet?action=searchTypeList&&pd_typeNo=PTN00003">服飾-男鞋</a>
<a href="<%=request.getContextPath()%>/ProductServlet?action=searchTypeList&&pd_typeNo=PTN00004">服飾-女上身</a>
<a href="<%=request.getContextPath()%>/ProductServlet?action=searchTypeList&&pd_typeNo=PTN00005">服飾-女下身</a>
<a href="<%=request.getContextPath()%>/ProductServlet?action=searchTypeList&&pd_typeNo=PTN00006">服飾-女鞋</a>
<a href="<%=request.getContextPath()%>/ProductServlet?action=searchTypeList&&pd_typeNo=PTN00007">服飾-兒童上身</a>
<a href="<%=request.getContextPath()%>/ProductServlet?action=searchTypeList&&pd_typeNo=PTN00008">服飾-兒童下身</a>
<a href="<%=request.getContextPath()%>/ProductServlet?action=searchTypeList&&pd_typeNo=PTN00009">服飾-兒童鞋類</a>
<a href="<%=request.getContextPath()%>/ProductServlet?action=searchTypeList&&pd_typeNo=PTN00010">服飾-配件-護具</a>
<a href="<%=request.getContextPath()%>/ProductServlet?action=searchTypeList&&pd_typeNo=PTN00011">服飾-配件-包款</a>
<a href="<%=request.getContextPath()%>/ProductServlet?action=searchTypeList&&pd_typeNo=PTN00012">服飾-配件-3C</a>


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
		<%@ include file="page1.file"%>
		<br>
		<%@ include file="page2.file"%>
		<c:forEach var="productVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">
			<tr>
				<td><img
					src="<%= request.getContextPath()%>/ProductPicReader?pd_no=${productVO.pd_no}"
					width="100px"></td>
				<td>${productVO.pd_no}</td>
				<td>${productVO.pd_name}</td>
				<td>${productVO.pd_price}</td>
				<td>${productVO.pd_detail}</td>
				<td>${productVO.pd_status==1?'下架':'上架'}</td>
				<td>${pd_typeService.searchType(productVO.pd_typeNo).pd_typeName}</td>


				<td><FORM METHOD="POST"
						ACTION="<%=request.getContextPath()%>/ProductServlet"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="pd_no" value="${productVO.pd_no}"> <input
							type="hidden" name="pd_name" value="${productVO.pd_name}">
						<input type="hidden" name="action" value="getOne_For_update">
						<input type="hidden" name="whichPage"	value="<%=whichPage%>">    
					</FORM></td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/ProductServlet"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="pd_no" value="${productVO.pd_no}"> 
							<input type="hidden" name="whichPage"	value="<%=whichPage%>">  <input
							type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>

		</c:forEach>
	</table>
	

	<br>
	<br>
	<a
		href="<%=request.getContextPath()%>/back_end/product/ShopManager.jsp">回管理商城首頁</a>

</body>
</html>