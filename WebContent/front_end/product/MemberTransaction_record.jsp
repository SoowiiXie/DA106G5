<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.orders.model.*"%>

<%
	String mb_id = (String) session.getAttribute("mb_id");

	OrdersService ordersService = new OrdersService();

	List<OrdersVO> list = ordersService.searchMemberOrders(mb_id);
	Collections.reverse(list);
	                  pageContext.setAttribute("list", list);
%>

<jsp:useBean id="couponService" scope="page"
	class="com.coupon.model.CouponService" />
<jsp:include page="ShopHomeBar2.jsp" />
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta charset="UTF-8">

<style type="text/css" media="screen">
table, th, td {
	border: 2px solid #FFFFFF;
}

th, td {
	padding: 1px;
	text-align: center;
}
</style>
<title>管理所有訂單</title>
</head>
<body>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<div 
		style="float: left; margin-left: -140px; margin-top: 150px;">
		<form method="POST"
			action="<%=request.getContextPath()%>/OrdersServlet" name="form1">
			<table border="1">

				<tr>
					<td> <%@ include
							file="MemberTransaction_record1.file"%></td>
							<td><%@ include
							file="MemberTransaction_record3.file"%></td>
					<td style="margin-left:360px; float: left;"><%@ include
							file="MemberTransaction_record2.file"%></td>
				</tr>
			</table>
			<input type="hidden" name="mb_id" value="${mb_id}">
			<input type="hidden" name="action" value="searchSelfOrders">
		</form>

	</div>


	<div align="center" >
		<table border="1" Style="width: 1000px; align: center">

			<tr bgcolor="#999999">
				<th Style="width: 100px; align: center">訂單編號</th>
				<th Style="width: 150px; align: center">訂單時間</th>
				<th Style="width: 100px; align: center">訂單狀態</th>
				<th Style="width: 100px; align: center">訂單總金額</th>
				<th Style="width: 100px; align: center">優惠券號碼</th>
				<th Style="width: 100px; align: center">優惠後金額</th>
				<th Style="width: 200px; align: center">收貨地址</th>
				<th Style="width: 150px; align: center"></th>
				<th Style="width: 100px; align: center"></th>

			</tr>

			<c:forEach var="ordersVO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">
				<tr>

					<td align="center" bgcolor="#C4E1FF">${ordersVO.od_no}</td>
					<td align="center" bgcolor="#C4E1FF">${ordersVO.od_javaTime}</td>
					<td align="center" bgcolor="#C4E1FF">${ordersVO.od_status==1?'已發貨':'未發貨'}</td>
					<td align="center" bgcolor="#C4E1FF">${ordersVO.od_totalPrice}元</td>
					<td align="center" bgcolor="#C4E1FF">${couponService.searchCoupon(ordersVO.cp_no).cp_name}
						<c:if test="${ordersVO.cp_no==null}">
	                   無使用優惠券
  </c:if>
					</td>
					<td align="center" bgcolor="#C4E1FF">${ordersVO.od_discount}元</td>
					<td align="center" bgcolor="#C4E1FF">${ordersVO.od_add}</td>

					<td align="center" bgcolor="#C4E1FF"><FORM method="POST"
							ACTION="<%=request.getContextPath()%>/OrdersServlet">

							<input type="submit" value="查詢訂單明細"> <input type="hidden"
								name="od_no" value="${ordersVO.od_no}"> <input
								type="hidden" name="action" value="MemberSearchSelfOrders">
							<input type="hidden" name="whichPage" value="<%=whichPage%>">
						</FORM></td>
					<td align="center" bgcolor="#C4E1FF"><FORM METHOD="post"
							ACTION="OrdersServlet" style="margin-bottom: 0px;">
							<input type="submit" value="刪除"> <input type="hidden"
								name="pd_no" value="${productVO.pd_no}"> <input
								type="hidden" name="action" value="delete">

						</FORM></td>
				</tr>
			</c:forEach>
		</table>
	</div>



	<c:if test="${openModal!=null}">

		<div class="modal fade" id="basicModal" tabindex="-1" role="dialog"
			aria-labelledby="basicModal" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">

					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h3 class="modal-title" id="myModalLabel">訂單明細</h3>
					</div>

					<div class="modal-body">
						<!-- =========================================以下為原listOneEmp.jsp的內容========================================== -->
						<jsp:include page="MemberLookSelfOd_detail.jsp" /> 
						<!-- =========================================以上為原listOneEmp.jsp的內容========================================== -->
					</div>

					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-primary">Save
							changes</button>
					</div>

				</div>
			</div>
		</div>

		<script>
			$("#basicModal").modal({
				show : true
			});
		</script>
	</c:if>


</body>
</html>