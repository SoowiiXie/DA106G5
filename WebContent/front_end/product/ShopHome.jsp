
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%


ProductService productService = new ProductService();



List<ProductVO> list = productService.listOnTheMarket(2);

Collections.reverse(list);

pageContext.setAttribute("list", list); //for EL
%>


  購物車品項數量: 
<c:if test="${buylistCount!=0}">
	                   ${buylistCount}
  </c:if>
</br>
</br>

登入的會員帳號:${mb_id}
	<br>
	<br>
<jsp:include page="/front_end/product/ShopHomeBar.jsp" flush="ture" />


<jsp:useBean id="pd_typeService" scope="page"
	class="com.pd_type.model.Pd_typeService" />
	
<html>
<head>
<meta charset="UTF-8">
<title>列出所有商品</title>
</head>
<body>


	
	<a
		href="<%=request.getContextPath()%>/front_end/product/MemberSingIn.jsp">會員登錄</a>
	<br>
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
		<%@ include file="page3.file"%>
		<%@ include file="page4.file"%>
		<c:forEach var="productVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td><a
					href='<%=request.getContextPath()%>/ShoppingServlet?action=findOneProduct&pd_no=${productVO.pd_no}&&whichPage=<%=whichPage%>'><img
						src="<%= request.getContextPath()%>/ProductPicReader?pd_no=${productVO.pd_no}"
						width="100px"></a></td>
				<td><a
					href='<%=request.getContextPath()%>/ShoppingServlet?action=findOneProduct&pd_no=${productVO.pd_no}&&whichPage=<%=whichPage%>'>${productVO.pd_name}</a></td>
				<td>${productVO.pd_price}</td>
				<td>${pd_typeService.searchType(productVO.pd_typeNo).pd_typeName}</td>

			</tr>

		</c:forEach>
	</table>
	<%@ include file="page4.file"%>

	<%--    <jsp:include page="/front_end/product//ProductCart.jsp" flush="true" /> --%>
	<br>
	<br>
	<a
		href="<%=request.getContextPath()%>/back_end/product/ShopManager.jsp">回管理商城首頁</a>
	<br>
	<br>
	<a
		href="<%=request.getContextPath()%>/front_end/product/ProductCart.jsp">${mb_id}的購物車</a>
	<br>
	<br>
	<a
		href="<%=request.getContextPath()%>/front_end/product/MemberLookSelfCoupon.jsp">會員${mb_id}的優惠卷</a>
	<br>
	<br>
	<a
		href="<%=request.getContextPath()%>/front_end/product/MemberLookSelfPd_follow.jsp">${mb_id}的商品收藏</a>
	
	

</body>
</html>