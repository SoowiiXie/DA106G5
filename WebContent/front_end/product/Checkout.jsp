<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="java.util.* "%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.cp_get.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mode II 範例程式 - Checkout.jsp</title>
</head>
<jsp:useBean id="pd_typeService" scope="page" class="com.pd_type.model.Pd_typeService" />
<body bgcolor="#FFFFFF">

會員：${mb_id}


	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	
	<img src="images/tomcat.gif">
	<font size="+3">商城 - 填寫收費與取貨資訊 </font>
	<hr>
	<p>
	<table border="1" width="720">
		<tr bgcolor="#999999">
		    <th width="200">產品圖片</th>
			<th width="200">產品分類</th>
			<th width="100">產品名稱</th>
			<th width="100">產品編號</th>
			<th width="100">單價</th>
			<th width="100">尺寸</th>
			<th width="100">數量</th>
			<th width="120"></th>
		</tr>
		<%

			Vector<ProductVO> buylist = (Vector<ProductVO>) session.getAttribute("shoppingCart");
		Integer totalPrice =  (Integer)session.getAttribute("totalPrice");
			List<Cp_getVO> couponList = (List<Cp_getVO> )request.getAttribute("couponList");
			request.setAttribute("couponList", couponList);
		%>
		<%
			for (int i = 0; i < buylist.size(); i++) {
				ProductVO order = buylist.get(i);
				String pd_no = order.getPd_no();
				String pd_name = order.getPd_name();
				int pd_price = order.getPd_price();
				String pd_typeSize = order.getPd_typeSize();
				String pd_typeNo = order.getPd_typeNo();
				int pd_quantity = order.getPd_quantity();
				pageContext.setAttribute("order", order);
		%>

		<form ac>
			<tr>
				<td width="100"><div align="center">
				<a href='<%=request.getContextPath()%>/ShoppingServlet?action=findOneProduct&pd_no=<%=order.getPd_no()%>'><img src="<%= request.getContextPath()%>/ProductPicReader?pd_no=<%=order.getPd_no()%>" width="100px">
						
					</div></td>
					<td width="100"><div align="center">
						<b>${pd_typeService.searchType(order.pd_typeNo).pd_typeName}</b>
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
						<b><%=pd_typeSize%></b>
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
				<td><font color="red"><b>$<%=totalPrice%></b></font></td>
				<td></td>
			</tr>
			
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td><div align="center">
						<font color="red"><b>優惠後金額：</b></font>
					</div></td>
				<td></td>
				<td><font color="red"><b>${discount}</b></font></td>
				<td></td>
			</tr>
			
	</table>
	</form ac>

	<form method="POST"
		action="<%=request.getContextPath()%>/CheckOutServlet">
		<table>
			<tr>
				<td>填寫收費地址</td>
				<td><input type="TEXT" name="od_add"></td>
			</tr>

			<tr>
				<td>選擇收費方式</td>
				<td><input type="radio" name="payMethod" value="貨到付款">貨到付款
					<input type="radio" name="payMethod" value="信用卡付費">信用卡付費 <input
					type="radio" name="payMethod" value="ibon繳費">ibon繳費</td>
			</tr>

		</table>
	   

		<input type="hidden" name="action" value="getOd_detail_Information"> <input
			type="submit" name="Submit" value="結帳">
		
	</form>

	<p>
		<a href="<%=request.getContextPath()%>/front_end/product/ShopHome.jsp">是否繼續購物</a>
</body>
</html>
