<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.product.model.*"%>
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>Mode II 範例程式  - Cart.jsp</title>
</head>
<body bgcolor="#FFFFFF">

<% Vector<ProductVO> buylist = (Vector<ProductVO>) session.getAttribute("shoppingCart");%>


<%if (buylist != null && (buylist.size() > 0)) {%>

<img src="images/tomcat.gif"> <font size="+3">目前您購物車的內容如下：</font><p>

<table border="1" width="740">
	<tr bgcolor="#999999">
		<th width="200">產品圖片</th><th width="200">產品分類</th><th width="100">產品名稱</th><th width="100">單價</th><th width="100">數量</th>
		<th width="120"></th>
	</tr>
	
	<%
	 for (int index = 0; index < buylist.size(); index++) {
		 ProductVO order = buylist.get(index);
	%>
	
	<tr>
	<td><a href='<%=request.getContextPath()%>/ShoppingServlet?action=findOneProduct&pd_no=${order.pd_no}'><img src="<%= request.getContextPath()%>/ProductPicReader?pd_no=<%=order.getPd_no()%>" width="100px"></a></td>
		<td width="100"><div align="center"><b><%=order.getPd_typeNo()%></b></div></td>
		<td width="100"><div align="center"><b><%=order.getPd_name()%></b></div></td> 
		<td width="100"><div align="center"><b><%=order.getPd_price()%></b></div></td>
		<td width="100"><div align="center"><b><%=order.getPd_quantity()%></b></div></td>  
	
		
		<td width="100"><div align="center">
          <form name="deleteForm" action="<%=request.getContextPath()%>/ShoppingServlet" method="POST">
              <input type="hidden" name="action" value="DELETE">
              <input type="hidden" name="del" value="<%= index %>">
              <input type="submit" value="刪除"></div>
        </td></form>
	</tr>
	<%}%>
</table>
<p>
          <form name="checkoutForm" action="<%=request.getContextPath()%>/ShoppingServlet" method="POST">
              <input type="hidden" name="action"	value="CHECKOUT"> 
              <input type="submit" value="付款結帳">
          </form>
          
<%}%>

<%if (buylist==null){%>
購物車沒東西喔
<% }%>
<br>
<br>
<a href="<%=request.getContextPath()%>/front_end/product/ShopHome.jsp">回到首頁增加商品</a>
</body>
</html>