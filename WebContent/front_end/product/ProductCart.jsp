<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.product.model.*"%>
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>Mode II 範例程式  - Cart.jsp</title>
</head>
<jsp:useBean id="pd_typeService" scope="page" class="com.pd_type.model.Pd_typeService" />
<body bgcolor="#FFFFFF">


<% Vector<ProductVO> buylist = (Vector<ProductVO>) session.getAttribute("shoppingCart");%>
<% if (buylist==null ||buylist.size()==0){%>
		 購物車沒東西喔
		 <% }%>


<%if (buylist != null && (buylist.size() > 0)) {%>

<img src="images/tomcat.gif"> <font size="+3">目前您購物車的內容如下：</font><p>

<table style="border:1px; width:740px" >
	<tr bgcolor="#999999">
		<th width="200">產品圖片</th><th width="200">產品分類</th><th width="100">產品名稱</th><th width="100">單價</th>
		<th width="100">尺寸</th><th width="100">數量</th>
		<th width="120"></th>
	</tr>
	
	<%
	
	 for (int index = 0; index < buylist.size(); index++) {
		
		 ProductVO order = buylist.get(index);
		 
		 pageContext.setAttribute("order", order);
		 
		 
		 
	%>
	
	
	<tr>
	<td><a href='<%=request.getContextPath()%>/ShoppingServlet?action=findOneProduct&pd_no=${order.pd_no}'><img src="<%= request.getContextPath()%>/ProductPicReader?pd_no=<%=order.getPd_no()%>" width="100px"></a></td>
		<td width="100"><div align="center"><b>${pd_typeService.searchType(order.pd_typeNo).pd_typeName}</b></div></td> 
		<td width="100"><div align="center"><b><%=order.getPd_name()%></b></div></td> 
		<td width="100"><div align="center"><b><%=order.getPd_price()%></b></div></td>
		<td width="100"><div align="center"><b><%=order.getPd_typeSize()%></b></div></td>
		<td width="100"><div align="center"><b><%=order.getPd_quantity()%></b></div></td>  
	
		
		<td width="100">
			<div align="center">
	          	<form name="deleteForm" action="<%=request.getContextPath()%>/ShoppingServlet" method="POST">
		            <input type="hidden" name="action" value="DELETE">
		            <input type="hidden" name="del" value="<%= index %>">
		            <input type="submit" value="刪除">
	            </form>
        	</div>
        </td>
	</tr>
	<%}%>
	
	
	
</table>
<p>
          <form name="checkoutForm" action="<%=request.getContextPath()%>/ShoppingServlet" method="POST">
              <input type="hidden" name="action"	value="GoToWriteShopInformation"> 
              <input type="submit" value="填寫購買資訊">
          </form>
          
<%}%>
 


<br>
<br>
<a href="<%=request.getContextPath()%>/front_end/product/ShopHome.jsp">回到首頁增加商品</a>
</body>
</html>