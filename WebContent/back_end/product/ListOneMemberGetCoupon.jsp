<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cp_get.model.*, java.util.List"%>


<%
	String mb_id = (String)request.getAttribute("mb_id");

	Cp_getService cp_getService = new Cp_getService();

	List<Cp_getVO> list = cp_getService.searchMemberGetCoupon(mb_id);
	
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

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>



<table >
<tr>  
<th width="100">會員帳號</th>
<td width="100">${mb_id}</td>
</tr>  
</table>
<table>
<tr>  
<th width="100">持有優惠券</th>
<th width="100">優惠券狀態</th>
</tr>
<tr>

<c:forEach var="cp_getVO2" items="${list}">
<tr> 
<td width="100">${couponService.searchCoupon(cp_getVO2.cp_no).cp_name}</td>
<td width="100">${cp_getVO2.cp_status==1?'可使用':'已使用'}</td>
<tr>

</c:forEach>
</tr>
</table>

優惠卷名稱喔

       <select size="1" name="cp_non">
         <c:forEach var="cp_getVO" items="${list}" > 
          <option value="${cp_getVO.cp_no}"> ${couponService.searchCoupon(cp_getVO.cp_no).cp_name}
         </c:forEach>   
       </select>

<%-- <table>
<tr>
<% for(Cp_getVO cp_getVO : list){%>
<td><%=cp_getVO.getCp_no()%></td>
<%}%>
</tr>
</table> --%>


<a href="<%=request.getContextPath()%>/back_end/product/ShopManager.jsp">回管理商城首頁</a>
</body>
</html>