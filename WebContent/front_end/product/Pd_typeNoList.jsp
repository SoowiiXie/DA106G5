<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>

<%
                
	List<ProductVO> list = (List<ProductVO>) request.getAttribute("list");

Collections.reverse(list);
    if(list==null){
    list = (List<ProductVO>) session.getAttribute("list");
    }
    session.setAttribute("list", list);
   
%>
 
<jsp:include page="/front_end/product/ShopHomeBar.jsp" flush="ture" />
<jsp:useBean id="pd_typeService" scope="page"
	class="com.pd_type.model.Pd_typeService" />

<!DOCTYPE html>
<html>
<head>
<style type="text/css" media="screen">
.a{
position: relative;


}
.bbb{
background-color:#context;
height:50px;
}
.ccc{
background-color:#context;
height:30px;
}

</style>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<%--   <%=request.getRequestURI()%>
 
<a
		href="<%=request.getContextPath()%>/front_end/product/ShopHome.jsp">商城首頁</a>
	<br> --%>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	


		<%@ include file="page5.file"%>
		<%@ include file="page6.file"%>
	<c:forEach var="productVO" items="${list}" begin="<%=pageIndex%>"
		end="<%=pageIndex+rowsPerPage-1%>">
		<div class="context">
			<table border="1" class="Product">

				<tr>
					<td class="aaa"><a
						href='<%=request.getContextPath()%>/ShoppingServlet?action=findOneProduct&pd_no=${productVO.pd_no}&&whichPage=<%=whichPage%>'><img
							src="<%= request.getContextPath()%>/ProductPicReader?pd_no=${productVO.pd_no}"
							width="100px"></a></td>
				</tr>
				<tr>
					<td class="bbb"><a
						href='<%=request.getContextPath()%>/ShoppingServlet?action=findOneProduct&pd_no=${productVO.pd_no}&&whichPage=<%=whichPage%>'>${productVO.pd_name}</a></td>
				<tr>
					<td class="ccc"><font color="red"><b>${productVO.pd_price} 元</b></font></td>
				</tr>
				<tr>
					<td class="ccc">${pd_typeService.searchType(productVO.pd_typeNo).pd_typeName}</td>
				</tr>

			</table>
		</div>
	</c:forEach>
	<div class="foot"></div>
	<%@ include file="page6.file"%>
</body>
</html>