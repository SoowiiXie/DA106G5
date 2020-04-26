<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="java.util.* "%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.cp_get.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mode II 範例程式 - 結帳頁面</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
<script src="vendor/TWzipcode/jquery.twzipcode.min.js"></script>


<style>
.city, .town{width: 100%;}
.f1{float:left;margin-left:5px;margin-right:5px;width:calc(5% - 10px)}
.f2{float:left;margin-left:5px;margin-right:5px;width:calc(10% - 10px)}
.f3{float:left;margin-left:5px;margin-right:5px;width:calc(15% - 10px)}
.f4{float:left;margin-left:5px;margin-right:5px;width:calc(20% - 10px)}
.f5{float:left;margin-left:5px;margin-right:5px;width:calc(25% - 10px)}
.f6{float:left;margin-left:5px;margin-right:5px;width:calc(30% - 10px)}
.f7{float:left;margin-left:5px;margin-right:5px;width:calc(35% - 10px)}
.f8{float:left;margin-left:5px;margin-right:5px;width:calc(40% - 10px)}
.f9{float:left;margin-left:5px;margin-right:5px;width:calc(45% - 10px)}
.f10{float:left;margin-left:5px;margin-right:5px;width:calc(50% - 10px)}
.f11{float:left;margin-left:5px;margin-right:5px;width:calc(55% - 10px)}
.f12{float:left;margin-left:5px;margin-right:5px;width:calc(60% - 10px)}
.f13{float:left;margin-left:5px;margin-right:5px;width:calc(65% - 10px)}
.f14{float:left;margin-left:5px;margin-right:5px;width:calc(70% - 10px)}
.f15{float:left;margin-left:5px;margin-right:5px;width:calc(75% - 10px)}
.f16{float:left;margin-left:5px;margin-right:5px;width:calc(80% - 10px)}
.f17{float:left;margin-left:5px;margin-right:5px;width:calc(85% - 10px)}
.f18{float:left;margin-left:5px;margin-right:5px;width:calc(90% - 10px)}
.f19{float:left;margin-left:5px;margin-right:5px;width:calc(95% - 10px)}
.f20{float:left;margin-left:5px;margin-right:5px;width:calc(100% - 10px)}

</style>
</head>
<jsp:useBean id="pd_typeService" scope="page" class="com.pd_type.model.Pd_typeService" />
<body bgcolor="#FFFFFF">
<jsp:include page="/front_end/product/ShopHomeBar2.jsp" flush="ture" />



	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	
<div align="center" style="margin-top:100px;">
	<font size="+3">商城 - 填寫收費與取貨資訊 </font></div>
	<hr>
	<p>
	<div align="center">
	<table  style="width:1000px;">
		<tr bgcolor="#999999">
		    <th style ="width:150px;">產品圖片</th>
			<th style ="width:200px;">產品分類</th>
			<th style ="width:100px;">產品名稱</th>
			<th style ="width:100px;">產品編號</th>
			<th style ="width:100px;">單價</th>
			<th style ="width:100px;">尺寸</th>
			<th style ="width:100px;">數量</th>
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

		
			<tr bgcolor=#C4E1FF>
				<td style ="height:100px;"><div align="center">
				<a href='<%=request.getContextPath()%>/ShoppingServlet?action=findOneProduct&pd_no=<%=order.getPd_no()%>'></a><img src="<%= request.getContextPath()%>/ProductPicReader?pd_no=<%=order.getPd_no()%>" style="width:auto;">
						
					</div></td>
					<td width="100"><div align="center">
						<b>${pd_typeService.searchType(order.pd_typeNo).pd_typeName}</b>
					</div></td>
				<td><div align="center">
						<b><%=pd_name%></b>
					</div></td>
				<td><div align="center">
						<b><%=pd_no%></b>
					</div></td>
				<td><div align="center">
						<b><%=pd_price%></b>
					</div></td>
					<td><div align="center">
						<b><%=pd_typeSize%></b>
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
				
				<td align="center"><font color="red"><b>$<%=totalPrice%></b></font></td>
				<td></td>
				<td></td>
			</tr>
			
			<tr bgcolor=#C4E1FF>
				<td></td>
				<td></td>
				<td></td>
				<td><div align="center">
						<font color="red"><b>優惠後金額：</b></font>
					</div></td>
				<td align="center"><font color="red"><b>$${discount}</b></font></td>
				<td></td>
				
				<td></td>
			</tr>
			
	</table>
	
</div>
	<form method="POST"
		action="<%=request.getContextPath()%>/CheckOutServlet">
		<table>
		<tr>
				<td>	   </td>
				
			</tr>
			<tr>
				<td>填寫收費地址</td>
				<td>	<div id="twzipcode"></div>

<script type="text/javascript">
	$("#twzipcode").twzipcode({
	 /* zipcodeName: "number", */ // 自訂郵遞區號input標籤的name值,方便送後端
		zipcodeIntoDistrict: true
	});

// 寫一個jQuery把選擇的文字塞到地址欄位,使用者友善介面
$("#twzipcode").on('change', function () {
	
 var county = $("#twzipcode").twzipcode('get','county');
 var district = $("#twzipcode").twzipcode('get','district');

 var result = county+district;
 $("#address").val(result);
 });
</script><td></td><td style="width:100px;"><input type="TEXT" name="od_add" id="address"></td>
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
