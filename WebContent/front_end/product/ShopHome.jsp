
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


<%--   購物車品項數量: 
<c:if test="${buylistCount!=0}">
	                   ${buylistCount}
  </c:if>
</br>
</br>

登入的會員帳號:${mb_id}
	<br>
	<br> --%>
<jsp:include page="/front_end/product/ShopHomeBar.jsp" flush="ture" />


<jsp:useBean id="pd_typeService" scope="page"
	class="com.pd_type.model.Pd_typeService" />
	
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
	<div class="a" align="right">
		<form method="POST"
			action="<%=request.getContextPath()%>/ProductServlet" name="form1">

			<table>
				<tr>
					<td>產品商品分類：</td>
					<td><select size="1" name="pd_typeNo">
							<c:forEach var="pd_typeVO" items="${typeList}">
								<option value="${pd_typeVO.pd_typeNo}"
									${(productVO.pd_typeNo==pd_typeVO.pd_typeNo)? 'selected':''}>${pd_typeVO.pd_typeName}
							</c:forEach>
					</select></td>

					<td>商品名稱：</td>
					<td><input type="TEXT" name="pd_name" 
						value="${productVO.pd_name}"></td>


					<td>最低價格：</td>
					<td><input type="TEXT" name="lowPrice" style="width:50px;"
						value="${productVO.pd_price}"></td>


					<td>最高價格：</td>
					<td><input type="TEXT" name="highPrice" style="width:50px;"
						value="${productVO.pd_price}"></td>
					<td><input type="submit" name="Submit" value="搜尋商品"></td>
				</tr>
			</table>


			<input type="hidden" name="action" value="superGetAll">
		</form>
	</div>

		
	<%@ include file="page3.file"%>
	<%@ include file="page4.file"%>
	<c:forEach var="productVO" items="${list}" begin="<%=pageIndex%>"
		end="<%=pageIndex+rowsPerPage-1%>">
		<div class="context">
			<table border="1" class="Product">

				<tr>
					<td class="aaa"><a
						href='<%=request.getContextPath()%>/ShoppingServlet?action=findOneProduct&pd_no=${productVO.pd_no}&&whichPage=<%=whichPage%>'><img
							src="<%= request.getContextPath()%>/ProductPicReader?pd_no=${productVO.pd_no}"
							></a></td>
				</tr>
				<tr>
					<td class="bbb"><a
						href='<%=request.getContextPath()%>/ShoppingServlet?action=findOneProduct&pd_no=${productVO.pd_no}&&whichPage=<%=whichPage%>'>${productVO.pd_name}</a></td>
				<tr>
					<td class="ccc"><font color="red"><b>${productVO.pd_price} 元</b></font></td>

				</tr>
				<tr>
					<td class="ccc">${pd_typeService.searchType(productVO.pd_typeNo).pd_typeName}</td>
				</tr>

			</table>
		</div>
	</c:forEach>
	<div class="foot"></div>

	

</body>
</html>