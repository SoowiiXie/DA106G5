<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cp_get.model.*, java.util.List"%>


<%
	String mb_id = (String)session.getAttribute("mb_id");

	Cp_getService cp_getService = new Cp_getService();
	Cp_getVO cp_getVO = new Cp_getVO();
	cp_getVO.setMb_id(mb_id);
	cp_getVO.setCp_status(1);
	List<Cp_getVO> list = cp_getService.listAmemberCpGetStatus(cp_getVO);
	
	pageContext.setAttribute("list", list );
	
	
%>
<jsp:useBean id="couponService" scope="page" class="com.coupon.model.CouponService" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%-- éŒ¯èª¤è¡¨åˆ— --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">è«‹ä¿®æ­£ä»¥ä¸‹éŒ¯èª¤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>



<table >
<tr>  
<th width="100">æœƒå“¡å¸³è™Ÿ</th>
<th width="100">å¯ä½¿ç”¨çš„å„ªæƒ åˆ¸</th>
</tr>
<tr>
<td width="100">${mb_id}</td>


<c:forEach var="cp_getVO" items="${list}">

<td width="100">${couponService.searchCoupon(cp_getVO.cp_no).cp_name}</td>


</c:forEach>
</tr>
</table>



       <select size="1" name="cp_non">
         <c:forEach var="cp_getVO2" items="${list}" > 
          <option value="${cp_getVO2.cp_no}">${couponService.searchCoupon(cp_getVO2.cp_no).cp_name}
         </c:forEach>   
       </select>

<%-- <table>
<tr>
<% for(Cp_getVO cp_getVO : list){%>
<td><%=cp_getVO.getCp_no()%></td>
<%}%>
</tr>
</table> --%>

<a href="<%=request.getContextPath()%>/front_end/product/ShopHome.jsp">å›å•†åŸé¦–é </a>

</body>
</html>
=======
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="java.util.* "%>
<%@ page import="com.product.model.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>­q³æ©ú²Ó</title>


<% request.getAttribute("ordersVO"); %>
<jsp:useBean id="pd_typeService" scope="page" class="com.pd_type.model.Pd_typeService" />
</head>
<body bgcolor="#FFFFFF">
	<img src="images/tomcat.gif">
	<font size="+3">­q³æ©ú²Ó </font>
	<hr>
	<p>
	
	­q³æ½s¸¹${ordersVO.od_no}
	<table border="1" width="720">
		<tr bgcolor="#999999">
		    <th width="200">²£«~¹Ï¤ù</th> 
		    <th width="100">²£«~¦WºÙ</th>
			<th width="200">²£«~¤ÀÃş</th>
			<th width="100">³æ»ù</th>
			<th width="100">²£«~¼Æ¶q</th>
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

		<form ac>
		
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
						<font color="red"><b>Á`ª÷ÃB¡G</b></font>
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
						<font color="red"><b>Àu´f«áªºª÷ÃB¡G</b></font>
					</div></td>
				<td></td>
				<td><font color="red"><b>${ordersVO.od_discount}</b></font></td>
				<td></td>
			</tr>
			<td>¥I¶O¤è¦¡:</td><td>${payMethod}</td>
			<td> ${ordersVO.od_add}</td>
	</table>
	</form ac>




	<p>
		<a href="<%=request.getContextPath()%>/front_end/product/ShopHome.jsp">¬O§_Ä~ÄòÁÊª«</a>
</body>
</html>
>>>>>>> branch 'master' of https://github.com/SoowiiXie/DA106G5.git
