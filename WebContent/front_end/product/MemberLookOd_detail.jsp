<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.cp_get.model.*, java.util.List"%>
<%@ page import="java.util.* "%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>訂單明細</title>


<%
	request.getAttribute("ordersVO");
%>
<jsp:useBean id="pd_typeService" scope="page"
	class="com.pd_type.model.Pd_typeService" />
<style type="text/css" media="screen">
.a {
	position: relative;
}

.bbb {
	background-color: #context;
	height: 50px;
}

.ccc {
	background-color: #context;
	height: 30px;
}
</style>
</head>
<body bgcolor="#FFFFFF">
	<jsp:include page="/front_end/product/ShopHomeBar2.jsp" flush="ture" />
	<font size="+3">訂單明細 </font>
	<hr>
	<p>
	<div align="center">
		訂單編號${ordersVO.od_no}

		<table style="width: 1000px;">
			<tr bgcolor="#999999">
				<th style="width: 150px;">產品圖片</th>
				<th style="width: 200px;">產品名稱</th>
				<th style="width: 150px;">產品分類</th>
				<th style="width: 150px;">單價</th>
				<th style="width: 150px;">產品數量</th>
				<th style="width: 150px;"></th>
			</tr>




			<%
				Vector<ProductVO> buylist = (Vector<ProductVO>) session.getAttribute("shoppingCart");
				String amount = (String) request.getAttribute("amount");
			%>
			<%
				for (int i = 0; i < buylist.size(); i++) {
					ProductVO order = buylist.get(i);
					String pd_no = order.getPd_no();
					String pd_name = order.getPd_name();
					int pd_price = order.getPd_price();
					String pd_typeNo = order.getPd_typeNo();
					int pd_quantity = order.getPd_quantity();
					pageContext.setAttribute("pd_typeNo", pd_typeNo);
			%>



			<tr bgcolor=#C4E1FF>

				<td style ="height:100px;"><div align="center">
						<a
							href='<%=request.getContextPath()%>/ShoppingServlet?action=findOneProduct&pd_no=<%=order.getPd_no()%>'><img
							src="<%=request.getContextPath()%>/ProductPicReader?pd_no=<%=order.getPd_no()%>"
							style="width:auto;"></a>
						${pd_typeService.searchType(productVO.pd_typeNo).pd_typeName}
					</div></td>

				<td><div align="center">
						<b><%=pd_name%></b>
					</div></td>

				<td><div align="center">
						<b>${pd_typeService.searchType(pd_typeNo).pd_typeName}</b>
					</div></td>

				<td><div align="center">
						<b><%=pd_no%></b>
					</div></td>
				<td><div align="center">
						<b><%=pd_price%></b>
					</div></td>
				<td><div align="center">
						<b><%=pd_quantity%></b>
					</div></td>
			</tr>

			<%
				}
			%>
			<tr bgcolor=#C4E1FF>
				<td></td>
				<td></td>
				<td></td>
				<td><div align="center">
						<font color="red"><b>總金額：</b></font>
					</div></td>
				<td></td>
				<td><font color="red"><b>$${ordersVO.od_totalPrice}</b></font></td>

			</tr>
			<tr bgcolor=#C4E1FF>
				<td></td>
				<td></td>
				<td></td>
				<td><div align="center">
						<font color="red"><b>優惠後的金額：</b></font>
					</div></td>
				<td></td>
				<td><font color="red"><b>$${ordersVO.od_discount}</b></font></td>

			</tr>
			
			<tr bgcolor=#C4E1FF align="center">
				<td>付款方式:</td>
				<td>${payMethod}</td>
				<td >收貨地址:</td>
				<td colspan="3">${ordersVO.od_add}</td>
				
				
				
			</tr>
		</table>



	</div>

	<p>
		<a href="<%=request.getContextPath()%>/front_end/product/ShopHome.jsp">是否繼續購物</a>
</body>
</body>
</html>
