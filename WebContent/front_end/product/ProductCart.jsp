<<<<<<< HEAD
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

<table border="1" width="740">
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
              <input type="hidden" name="action"	value="GoToWriteShopInformation"> 
              <input type="submit" value="填寫購買資訊">
          </form>
          
<%}%>
 


<br>
<br>
<a href="<%=request.getContextPath()%>/front_end/product/ShopHome.jsp">回到首頁增加商品</a>
</body>
=======
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mode II 範例程式 - Cart.jsp</title>
</head>
<jsp:useBean id="pd_typeService" scope="page"
	class="com.pd_type.model.Pd_typeService" />
	<jsp:useBean id="couponService" scope="page" class="com.coupon.model.CouponService" />
<body bgcolor="#FFFFFF">


	<%
		Vector<ProductVO> buylist = (Vector<ProductVO>) session.getAttribute("shoppingCart");
	%>
	<%
		if (buylist == null || buylist.size() == 0) {
	%>
	購物車沒東西喔
	<%
		}
	%>


	<%
		if (buylist != null && (buylist.size() > 0)) {
	%>

	<img src="images/tomcat.gif">
	<font size="+3">目前您購物車的內容如下：</font>
	<p>
	<table border="1" width="740">
		<tr bgcolor="#999999">
			<th width="200">產品圖片</th>
			<th width="200">產品分類</th>
			<th width="100">產品名稱</th>
			<th width="100">單價</th>
			<th width="100">尺寸</th>
			<th width="100">數量</th>
			<th width="120"></th>
		</tr>

		<%
			for (int index = 0; index < buylist.size(); index++) {

					ProductVO order = buylist.get(index);

					pageContext.setAttribute("order", order);
		%>


		<tr>
			<td><a
				href='<%=request.getContextPath()%>/ShoppingServlet?action=findOneProduct&pd_no=${order.pd_no}'><img
					src="<%=request.getContextPath()%>/ProductPicReader?pd_no=<%=order.getPd_no()%>"
					width="100px"></a></td>
			<td width="100"><div align="center">
					<b>${pd_typeService.searchType(order.pd_typeNo).pd_typeName}</b>
				</div></td>
			<td width="100"><div align="center">
					<b><%=order.getPd_name()%></b>
				</div></td>
			<td width="100"><div align="center">
					<b><%=order.getPd_price()%></b>
				</div></td>
			<td width="100"><div align="center">
					<b><%=order.getPd_typeSize()%></b>
				</div></td>
			<td width="100"><div align="center">
					<b><%=order.getPd_quantity()%></b>
				</div></td>


			<td width="100"><div align="center">
					<form name="deleteForm"
						action="<%=request.getContextPath()%>/ShoppingServlet"
						method="POST">
						<input type="hidden" name="action" value="DELETE"> <input
							type="hidden" name="del" value="<%=index%>"> <input
							type="submit" value="刪除">
				</div></td>
			</form>
		</tr>
		<%
			}
		%>

       	<tr>
				<td></td>
				<td></td>
				<td></td>
				<td><div align="center">
						<font color="red"><b>目前金額：</b></font>
					</div></td>
				<td></td>
				<td><font color="red"><b>$${total}</b></font></td>
				<td></td>
			</tr>

	</table>
	<p>
	<form name="checkoutForm"
		action="<%=request.getContextPath()%>/ShoppingServlet" method="POST">
		<table>
		<tr>
		<td>可使用的優惠券：</td>
		<td><select size="1" name="cp_get">
			<option value="請選擇">請選擇
				<c:forEach var="cp_getVO" items="${couponList}">
					<option value="${cp_getVO.cp_no}">${couponService.searchCoupon(cp_getVO.cp_no).cp_name}
				</c:forEach>
		</select></td>
		</tr>
	</table>
		<input type="hidden" name="action" value="GoToWriteShopInformation">
		<input type="submit" value="填寫購買資訊">
		
	</form>

	<%
		}
	%>



	<br>
	<br>
	<a href="<%=request.getContextPath()%>/front_end/product/ShopHome.jsp">回到首頁增加商品</a>
</body>
>>>>>>> SoowiiLoc
</html>