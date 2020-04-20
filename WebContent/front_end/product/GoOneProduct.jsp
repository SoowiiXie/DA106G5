<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>

 <%=request.getRequestURI()%>

<%
	 ProductVO productVO = (ProductVO) request.getAttribute("productVO");  
%>
 
<jsp:useBean id="pd_typeService" scope="page" class="com.pd_type.model.Pd_typeService" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${productVO.pd_name}</title>
</head>
<body>
<br>
<br>
<br>
 ${addType_follow}
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<form method="POST" action="<%=request.getContextPath()%>/ShoppingServlet" name="form1">
		<table>
            <tr>
            <td width="100">商品圖片：</td>
             <td width="100"><img src="<%= request.getContextPath()%>/ProductPicReader?pd_no=${productVO.pd_no}" width="100px"></td>
			</tr>
			<tr>
				<td width="100">商品類別：</td>
				<td width="100">${pd_typeService.searchType(productVO.pd_typeNo).pd_typeName}</td>
			</tr>


			<tr>
				<td width="100">商品名稱：</td>
				<td width="100">${productVO.pd_name}</td>
			</tr>
			<tr>
				<td width="100">商品價格：</td>
				<td width="100">${productVO.pd_price}</td>
			</tr>
			<tr>
				<td width="100">商品詳述：</td>
				<td width="400">${productVO.pd_detail}</td>
			</tr>
			
			<tr>
			<td width="100">商品尺寸：</td>
			<td>      <select size="1" name="pd_typeSize">
         <c:forEach var="pd_typeSize" items="${sizeList}" > 
          <option value="${pd_typeSize}">${pd_typeSize}
         </c:forEach>   
       </select></td>
			
			</tr>
			
			
			<tr>
				<td width="100">數量：</td>
				<td><select name="pd_quantity">
						<option value="1">1
						<option value="2">2
						<option value="3">3
						<option value="4">4
						<option value="5">5
						<option value="6">6
						<option value="7">7
						<option value="8">8
						<option value="9">9
						<option value="10">10
				</select>
			</tr>

		</table>
		<input type="submit" name="Submit" value="加入購物車"> <input
			type="hidden" name="pd_no" value="${productVO.pd_no}"> <input
			type="hidden" name="pd_name" value="${productVO.pd_name}"> <input
			type="hidden" name="pd_price" value="${productVO.pd_price}">
		<input type="hidden" name="pd_typeNo" value="${productVO.pd_typeNo}">
		<input type="hidden" name="pd_typeSize" value="${pd_typeSize}">
		<input type="hidden" name="action" value="AddProductToCar">
	</form>
	
	<form method="POST" action="<%=request.getContextPath()%>/Pd_followServlet" name="form1">
	<input type="hidden" name="pd_no" value="${productVO.pd_no}"> 
	<input type="hidden" name="pd_name" value="${productVO.pd_name}"> 
	<input type="hidden" name="action" value="AddPd_follow">
	<input type="submit" name="Submit" value="加入商品收藏">
	
	</form>
<a href="<%=request.getContextPath()%>/front_end/product/MemberLookSelfPd_follow.jsp">會員的商品收藏</a>
<a href="<%=request.getContextPath()%>/front_end/product/ShopHome.jsp?">回商城首頁</a>
	


</body>
</html>