
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.pd_type.model.*"%>
<!-- <link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> -->
<%
	ProductService productService = new ProductService();

	List<ProductVO> list = productService.listOnTheMarket(2);

	Collections.reverse(list);

	pageContext.setAttribute("list", list); //for EL
%>


<jsp:useBean id="pd_typeService" scope="page"
	class="com.pd_type.model.Pd_typeService" />


<%--   購物車品項數量: 
<c:if test="${buylistCount!=0}">
	                   ${buylistCount}
  </c:if>
</br>
</br>

登入的會員帳號:${mb_id}
	<br>
	<br> --%>
<jsp:include page="/front_end/product/ShopHomeBar.jsp" flush="ture" />


<html>
<head>
<style type="text/css" media="screen">
.a{
position: relative;


}
.PdName{
background-color:#context;
height:50px;
}
.PdPrice{
background-color:#context;
height:30px;
}
.buttonBar{
width:100%;
background-color:#3960D0;
height:100px;
margin-top:100;
}
.breadcrumb > .active {
color: #777;
}
</style>
<meta charset="UTF-8">
<title>列出所有商品</title>
</head>
<body>
<div style="width:100%; border-style: solid; border-color:red;" >
 <div class="third" align="center" ><img src="<%=request.getContextPath()%>/img/ProductAD.jpg" alt="跑馬燈圖片"></div> 

	<%-- 
	
	<a
		href="<%=request.getContextPath()%>/front_end/product/MemberSingIn.jsp">會員登錄</a>
	<br>
	<br> 

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
<div style="border-style: solid; border-color:red;">
	<div class="a" style="float:left;" >
	  <table style="float:left; border-style: solid; border-color:red;">
	    <tr>
	      <td><%@ include file="ShopHomePage1.file"%></td>
	      <td><%@ include file="ShopHomePage2.file"%></td>   
	    </tr>
	   </table>

	</div>

	
		 <form method="POST" action="<%=request.getContextPath()%>/ProductServlet" name="form1">

			<table style="float:right; border-style: solid; border-color:red;">
				<tr>
					<td>商品分類：</td>
					<td><select size="1" name="pd_typeNo">
					              <option value="">全部商品
							<c:forEach var="pd_typeVO" items="${pd_typeService.all}">
								<option value="${pd_typeVO.pd_typeNo}"
									${(productVO.pd_typeNo==pd_typeVO.pd_typeNo)? 'selected':''}>${pd_typeVO.pd_typeName}
							</c:forEach>
					</select></td>

					<td>商品名稱：</td>
					<td><input type="TEXT" name="pd_name" value="${productVO.pd_name}" style="width:100px;"></td>


					<td>最低價格：</td>
					<td><input type="TEXT" name="lowPrice" style="width:50px;" value="${productVO.pd_price}"></td>


					<td>最高價格：</td>
					<td><input type="TEXT" name="highPrice" style="width:50px;" value="${productVO.pd_price}"></td>
					<td><input type="submit" name="Submit" value="搜尋商品"></td>
				</tr>
			</table>


			<input type="hidden" name="action" value="CompositeQuery_Product">
			
		</form> 
		<div class="a" style="float:left;" >
	  <table  style="float:left; border-style: solid; border-color:red;">
	    <tr>
	      <td><%@ include file="ShopHomePage3.file"%></td>
	    </tr>
	   </table>

	</div>
</div>


	<%-- <%-- 				<jsp:useBean id="pd_typeService" scope="page" class="com.pd_type.model.Pd_typeService" />	 --%>

	<c:forEach var="productVO" items="${list}" begin="<%=pageIndex%>"
		end="<%=pageIndex+rowsPerPage-1%>">
		<div class="context" style="margin-top:100px; background-color:#858796;">
			<table border="1" class="Product" >

				<tr>
					<td class="aaa"><a
						href='<%=request.getContextPath()%>/ShoppingServlet?action=findOneProduct&pd_no=${productVO.pd_no}&&whichPage=<%=whichPage%>'><img
							src="<%= request.getContextPath()%>/ProductPicReader?pd_no=${productVO.pd_no}"
							></a></td>
				</tr>
				<tr>
					<td class="PdName"><a
						href='<%=request.getContextPath()%>/ShoppingServlet?action=findOneProduct&pd_no=${productVO.pd_no}&&whichPage=<%=whichPage%>'>${productVO.pd_name}</a></td>
				<tr>
					<td class="PdPrice"><font color="red"><b>${productVO.pd_price} 元</b></font></td>

				</tr>
				<tr>
					<td class="PdPrice">${pd_typeService.searchType(productVO.pd_typeNo).pd_typeName}</td>
				</tr>

			</table>
		</div>
	</c:forEach>
	<div class="foot"></div>
  <div class="buttonBar" style="margin-top:100px"></div>


</div>


</body>
</html>