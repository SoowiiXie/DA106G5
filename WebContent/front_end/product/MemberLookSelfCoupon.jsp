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
<jsp:include page="/front_end/product/ShopHomeBar.jsp" flush="ture" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
	<div style="width:100%; height:100px; border-style:solid; background-color:#FFD382;"></div>

<div align="center" style="margin-top: 100px;">
		<font size="+3">目前您可使用的優惠券如下：</font>
		<hr>
<table style="margin-top: 50px;">
<tr bgcolor="#999999">  

<th style="width:150px;">可使用的優惠券</th>
<th style="width:150px;">獲取時間</th>
<th style="width:150px;">備註</th>
</tr>

<c:forEach var="cp_getVO" items="${list}">
<tr bgcolor=#C4E1FF>
<td align="center" >${couponService.searchCoupon(cp_getVO.cp_no).cp_name}</td>
<td align="center" >${cp_getVO.cp_javaGetTime}</td>
<td align="center" >${couponService.searchCoupon(cp_getVO.cp_no).cp_detail}</td>
</tr>
</c:forEach>

</table>

</div>

<%-- <table>
<tr>
<% for(Cp_getVO cp_getVO : list){%>
<td><%=cp_getVO.getCp_no()%></td>
<%}%>
</tr>
</table> --%>



</body>
</html>