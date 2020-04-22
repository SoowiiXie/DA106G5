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

<% request.getAttribute("ordersVO"); %>
<jsp:useBean id="pd_typeService" scope="page" class="com.pd_type.model.Pd_typeService" />
</head>
<body bgcolor="#FFFFFF">
	<font size="+3">訂單明細 </font>
	<hr>
	<p>
	
	訂單編號${ordersVO.od_no}
	<table border="1" width=720px;>
		<tr>
		    <th width="200">產品圖片</th> 
		    <th width="100">產品名稱</th>
			<th width="200">產品分類</th>
			<th width="100">單價</th>
			<th width="100">產品數量</th>
			<th width="120"></th>
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



     			<tr>
				
				<td width="100"><div align="center">
						<a
							href='<%=request.getContextPath()%>/ShoppingServlet?action=findOneProduct&pd_no=<%=order.getPd_no()%>'><img
							src="<%=request.getContextPath()%>/ProductPicReader?pd_no=<%=order.getPd_no()%>"
							width="100px"></a> ${pd_typeService.searchType(productVO.pd_typeNo).pd_typeName}
					</div></td>
					
				<td width="100"><div align="center">
						<b><%=pd_name%></b>
					</div></td>
					
					<td width="100"><div align="center">
						<b>${pd_typeService.searchType(pd_typeNo).pd_typeName}</b>
					</div></td>
					
				<td width="200"><div align="center">
						<b><%=pd_no%></b>
					</div></td>
				<td width="100"><div align="center">
						<b><%=pd_price%></b>
					</div></td>
				<td width="100"><div align="center">
						<b><%=pd_quantity%></b>
					</div></td>
			</tr>

			<%
				}
			%>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td><div align="center">
						<font color="red"><b>總金額：</b></font>
					</div></td>
				<td></td>
				<td><font color="red"><b>${ordersVO.od_totalPrice}</b></font></td>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td><div align="center">
						<font color="red"><b>優惠後的金額：</b></font>
					</div></td>
				<td></td>
				<td><font color="red"><b>${ordersVO.od_discount}</b></font></td>
				<td></td>
			</tr>
			<tr>
			<td>付費方式:</td> <td>${payMethod}</td>
			<td> ${ordersVO.od_add}</td>
	</tr>
	</table>

<%-- <table>
<tr>
<% for(Cp_getVO cp_getVO : list){%>
<td><%=cp_getVO.getCp_no()%></td>
<%}%>
</tr>
</table> --%>

<a href="<%=request.getContextPath()%>/front_end/product/ShopHome.jsp">������擐���</a>

</body>
</html>
