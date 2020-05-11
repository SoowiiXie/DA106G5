<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.orders.model.*"%>
<%@ page import="com.mb.model.*"%>

<%
	MemberService memberSvc = new MemberService();
	MemberVO memberVO =(MemberVO)session.getAttribute("memberVO");
	//用memberVO先取得會常使用到的mb_id
	session.setAttribute("mb_id", memberVO.getMb_id());
	String mb_id = (String) session.getAttribute("mb_id");

	OrdersService ordersService = new OrdersService();

	List<OrdersVO> list = ordersService.searchMemberOrders(mb_id);
	Collections.reverse(list);
	                  pageContext.setAttribute("list", list);
%>

<jsp:useBean id="couponService" scope="page"
	class="com.coupon.model.CouponService" />
<jsp:include page="ShopHomeBar.jsp" />
<!DOCTYPE html>
<html>
<head>
 <link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<meta charset="UTF-8">

<style type="text/css" media="screen">
table, th, td {
	border: 2px solid #FFFFFF;
}

th, td {
	padding: 1px;
	text-align: center;
}
 .buttonBar{
width:100%;
background-color:#3960D0;
height:100px;
margin-top:100;
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
	<div align="center">
	<div style="width:100%; height:100px; border-style: solid; border-color:white; "></div>
	<div align="center" style="border-style: solid; border-color:white;width:100%; ">
		<font size="+3">目前您的交易紀錄如下：</font>
		<hr>
		<p></div>
	<div style="float: left; margin-top: 50px;  margin-left:10%; width:80%; border-style: solid; border-color:white;" >
		<form method="POST"
			action="<%=request.getContextPath()%>/OrdersServlet" name="form1">
			<table style="float:left;">
				<tr>
					<td> <%@ include
							file="MemberTransaction_record1.file"%></td>
							<td><%@ include
							file="MemberTransaction_record3.file"%></td>
				</tr>
			</table>
			
			<table style="float:right;">
				<tr>
					<td> <%@ include
							file="MemberTransaction_record2.file"%></td>
						
				</tr>
			</table>
			<table><tr></tr>
			</table>
			<input type="hidden" name="mb_id" value="${mb_id}">
			<input type="hidden" name="action" value="searchSelfOrders">
		</form>

	</div>


	<div align="center" style="border-style: solid; border-color:white; width:80%;">
		<table border="1" Style="width:100%;">

			<tr bgcolor="#999999" >
				<th Style="width: 100px; "><b><font color="black">訂單編號</font></b></th>
				<th Style="width: 150px; "><b><font color="black">訂單時間</font></b></th>
				<th Style="width: 100px; "><b><font color="black">訂單狀態</font></b></th>
				<th Style="width: 100px; "><b><font color="black">訂單總金額</font></b></th>
				<th Style="width: 100px; "><b><font color="black">優惠券號碼</font></b></th>
				<th Style="width: 100px; "><b><font color="black">優惠後金額</font></b></th>
				<th Style="width: 200px; "><b><font color="black">收貨地址</font></b></th>
				<th Style="width: 150px; "></th>
				

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
					
				</tr>
			</c:forEach>
		</table>
	</div>



	<c:if test="${openModal!=null}">

		<div class="modal fade" id="basicModal" tabindex="-1" role="dialog"
			aria-labelledby="basicModal" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">

					<div class="modal-header" align="center">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true" style="float:left;">&times;</button>
					</div>

					<div class="modal-body">
					
						<!-- =========================================以下為原listOneEmp.jsp的內容========================================== -->
						<jsp:include page="MemberLookSelfOd_detail.jsp" /> 
						<!-- =========================================以上為原listOneEmp.jsp的內容========================================== -->
					</div>

					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						
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
</div>
<div id="Footer" class="buttonBar" style="margin-top:500px;" ></div>
</body>
</html>