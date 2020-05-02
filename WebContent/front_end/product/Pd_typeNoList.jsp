<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>

<%
                
	List<ProductVO> list = (List<ProductVO>) request.getAttribute("list");


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

.buttonBar{
width:100%;
background-color:#3960D0;
height:100px;
margin-top:100;
}
</style>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<div class="third" align="center" ><img src="<%=request.getContextPath()%>/img/ProductAD.jpg" alt="跑馬燈圖片"></div>
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
	
	<div class="a" style="margin-left:5%; float:left;" >
	  <table style="float:left;">
	    <tr>
	      <td><%@ include file="Pd_typeNoList1.file"%></td>
		  <td><%@ include file="Pd_typeNoList2.file"%></td>
		</tr>
	  </table>
	  
	  </div>
		
		
		<form method="POST"
			action="<%=request.getContextPath()%>/ProductServlet" name="form1">

			<table style="margin-left:38%;">
				<tr>
					<td>商品分類：</td>
					
					<td><select size="1" name="pd_typeNo">
					<option value="">請選擇
							<c:forEach var="pd_typeVO" items="${pd_typeService.all}">
								<option value="${pd_typeVO.pd_typeNo}"
									${(productVO.pd_typeNo==pd_typeVO.pd_typeNo)? 'selected':''}>${pd_typeVO.pd_typeName}
							</c:forEach>
					</select></td>

					<td>商品名稱：</td>
					<td><input type="TEXT" name="pd_name" 
						value="${productVO.pd_name}"></td>


					<td>最低價格：</td>
					<td><input type="TEXT" name="lowPrice" style="width:50px;"
						value="${productVO.pd_price}"></td>


					<td>最高價格：</td>
					<td><input type="TEXT" name="highPrice" style="width:50px;"
						value="${productVO.pd_price}"></td>
					<td><input type="submit" name="Submit" value="搜尋商品"></td>
				</tr>
			</table>


			<input type="hidden" name="action" value="CompositeQuery_Product">
		</form>
			<div class="a" style="margin-left:5%;float:left;" >
	  <table  style="float:left;">
	    <tr>
	     
	      <td><%@ include file="ShopHomePage3.file"%></td>
	    
	    </tr>
	   </table>

	</div>
	


		
		
		
		
	<c:forEach var="productVO" items="${list}" begin="<%=pageIndex%>"
		end="<%=pageIndex+rowsPerPage-1%>">
		<div class="context" style="margin-top:50px; background-color:#858796;">
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
<div class="buttonBar" style="margin-top:100px"></div>
</body>
</html>