<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.Base64"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.pd_type.model.*"%>





<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style type="text/css" media="screen">


  table {

	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 3px solid 	#FFFFFF;
  }
  th, td {
    padding: 1px;
    text-align: center;
  }

</style>
<meta charset="UTF-8">
<title>商品管理</title>


<%
	String pd_typeNo = (String) session.getAttribute("pd_typeNo");
	ProductService productService = new ProductService();

	List<ProductVO> list = (List<ProductVO>) session.getAttribute("list");

	if (pd_typeNo == null || ("").equals(pd_typeNo)) {

		list = productService.getAll();

		session.setAttribute("list", list);
		Collections.reverse(list);

	} else {
		list = productService.useTypeSearchProducts(pd_typeNo);

		session.setAttribute("list", list);
		Collections.reverse(list);
	}
%>



<jsp:useBean id="pd_typeService" scope="page"
	class="com.pd_type.model.Pd_typeService" />
	<jsp:include page="ShopManagerBar.jsp" />
</head>
<body>




<div style=" margin-left:5%; margin-top:50px; width:91%; border:2px #FFD382 groove;">
<form name="checkoutForm" action="<%=request.getContextPath()%>/ProductServlet?action=searchTypeList" method="POST">
<div style="border:2px #FFD382 groove; width:43%; float:left;"> <select size="1" name="pd_typeNo">
		 <option value="">全部商品類別
         <c:forEach var="pd_typeVO" items="${pd_typeService.all}" > 
          <option value="${pd_typeVO.pd_typeNo}">${pd_typeVO.pd_typeName}
         </c:forEach>   
       </select>
				<input type="submit" value="選取類別">
				
				<input type="hidden" name="pd_typeNo" value="${pd_typeVO.pd_typeNo}">
				<%@ include file="AllList2File1.file"%>
				</div>
				<div style="border:2px #FFD382 groove; width:36%; float:left; margin-left:20%;">
				<%@ include file="AllList2File2.file"%>
				
				<%@ include file="AllList2File3.file"%>
				</div>
			</form>
</div>		
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>



		
<div align="center">
                
	<table  class ="tableList" style="width:90%;">

		<tr bgcolor="#999999">
			<th width="10%">商品圖片</th>
			<th width="10%">商品編號</th>
			<th width="20%">商品名稱</th>
			<th width="10%">商品價格</th>
			<th width="10%">商品類別</th>
			<th width="10%">商品狀態</th>
			<th width="10%"></th>
			<th width="10%"></th>
			<th width="10%"></th>

		</tr>
		
		<c:forEach var="productVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">
			<tr >
				<td align="center" style="height: 100px; width: 150px;"><img
					src="<%= request.getContextPath()%>/ProductPicReader?pd_no=${productVO.pd_no}"
					style="width:auto;"></td>
				<td bgcolor=#FFD2D2>${productVO.pd_no}</td>
				<td bgcolor=#FFD2D2>${productVO.pd_name}</td>
				<td bgcolor=#FFD2D2>${productVO.pd_price}元</td>
				<td bgcolor=#FFD2D2>${pd_typeService.searchType(productVO.pd_typeNo).pd_typeName}</td>
				<td bgcolor=#FFD2D2>${productVO.pd_status==1?'下架':'上架'}</td>
				<td bgcolor=#FFD2D2 align="center" >
			
				<FORM METHOD="POST" ACTION="<%=request.getContextPath()%>/ProductServlet">
				
				 <div style="border:2px #FFD382 groove;">
				    <input type="submit" value="下架">
				    <input type="hidden" name="pd_no" value="${productVO.pd_no}">
				    <input type="hidden" name="action" value="changePd_status1">
				    <input type="hidden" name="whichPage" value="<%=whichPage%>">
				  </div>
				</FORM>
				
		   
				<div style=" border:2px #FFD382 groove; align:center;">	
				    <FORM METHOD="POST" ACTION="<%=request.getContextPath()%>/ProductServlet">	
				    		
				<input type="submit" value="上架">
				<input type="hidden" name="pd_no" value="${productVO.pd_no}">
				<input type="hidden" name="action" value="changePd_status2">
				<input type="hidden" name="whichPage" value="<%=whichPage%>"></FORM>
				</div>
		   
				
					</td>
				


				<td bgcolor=#FFD2D2><FORM METHOD="POST"
						ACTION="<%=request.getContextPath()%>/ProductServlet"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="pd_no" value="${productVO.pd_no}"> <input
							type="hidden" name="pd_name" value="${productVO.pd_name}">
						<input type="hidden" name="action" value="getOne_For_update">
						<input type="hidden" name="whichPage" value="<%=whichPage%>">
					</FORM></td>
				<td bgcolor=#FFD2D2>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/ProductServlet"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="pd_no" value="${productVO.pd_no}"> <input
							type="hidden" name="whichPage" value="<%=whichPage%>"> <input
							type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>

		</c:forEach>
	</table>
	</div>
	<%@ include file="AllList2File2.file"%>

	<br>
	<br>
	<a
		href="<%=request.getContextPath()%>/back_end/product/ShopManager.jsp">回管理商城首頁</a>

</body>
</html>