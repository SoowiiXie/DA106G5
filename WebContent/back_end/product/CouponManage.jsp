<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.util.*"%>
<%@ page import="com.coupon.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>優惠券管理</title>


<%
	CouponService couponService = new CouponService();

	List<CouponVO> list = couponService.getAll();

	pageContext.setAttribute("list", list);
%>



</head>
<body>
	<jsp:include page="ShopManagerBar.jsp" />
<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<div align="center" style="margin-top: 100px;">
<form method="POST"
		action="<%=request.getContextPath()%>/CpGetServlet" name="form1"
		method="POST">
		<table>
          
			<tr bgcolor=#C4E1FF>
				<td style="width: 100px;">優惠券種類：</td>
				<td align="center"><input type="TEXT" name="cp_name"
					value="${couponVO.cp_name}"></td>
				<td style="width: 100px;">優惠券金額：</td>
				<td align="center"><input type="TEXT" name="cp_price"
					value="${couponVO.cp_price}"></td>
				<td style="width: 100px;">優惠券備註：</td>
				<td align="center"><input type="TEXT" name="cp_detail"
					value="${couponVO.cp_detail}"></td>
					<td><input type="hidden" name="action" value="addCouponTypeNo"> <input
			type="submit" name="Submit" value="增加優惠券"></td>

			</tr>

		</table>

</form>

		<font size="+3">優惠券列表：</font>
		<hr>
		<table style="margin-top: 50px;">
			<tr bgcolor="#999999">

				<th style="width: 150px;">優惠券編號</th>
				<th style="width: 150px;">優惠券種類</th>
				<th style="width: 150px;">優惠券價格</th>
				<th style="width: 150px;">優惠券備註</th>
				<th style="width: 150px;">產生日期</th>
			</tr>

			<c:forEach var="couponVO" items="${list}">
				<tr bgcolor=#C4E1FF>
					<td align="center">${couponVO.cp_no}</td>
					<td align="center">${couponVO.cp_name}</td>
					<td align="center">${couponVO.cp_price}元</td>
					<td align="center">${couponVO.cp_detail}</td>
					<td align="center">${couponVO.cp_javaTime}</td>
				</tr>
			</c:forEach>

		</table>

	</div>


















</body>
</html>