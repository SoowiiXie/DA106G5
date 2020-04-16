<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="java.util.* "%>
<%@ page import="com.product.model.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>訂單明細</title>


<% request.getAttribute("ordersVO"); %>
</head>
<body bgcolor="#FFFFFF">
	<img src="images/tomcat.gif">
	<font size="+3">訂單明細 </font>
	<hr>
	<p>
	
	訂單編號${ordersVO.od_no}
	<table border="1" width="720">
		<tr bgcolor="#999999">
			<th width="200">產品分類</th>
			<th width="100">產品名稱</th>
			<th width="100">單價</th>
			<th width="100">產品數量</th>
			<th width="100">數量</th>
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
		%>





		<form ac>
			<tr>
				<td width="100"><div align="center">
						<a
							href='<%=request.getContextPath()%>/ShoppingServlet?action=findOneProduct&pd_no=<%=order.getPd_no()%>'><img
							src="<%=request.getContextPath()%>/ProductPicReader?pd_no=<%=order.getPd_no()%>"
							width="100px"> <b><%=pd_typeNo%></b>
					</div></td>
				<td width="100"><div align="center">
						<b><%=pd_name%></b>
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
	</table>
	</form ac>




	<p>
		<a href="<%=request.getContextPath()%>/front_end/product/ShopHome.jsp">是否繼續購物</a>
</body>
</html>
