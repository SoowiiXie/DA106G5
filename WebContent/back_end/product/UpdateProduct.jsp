<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.pd_type.model.*"%>
<%
	ProductVO productVO = (ProductVO) request.getAttribute("productVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
	productVO.getPd_status();
%>

<%
	Pd_typeService pd_typeService = new Pd_typeService();

	List<Pd_typeVO> list = pd_typeService.getAll();

	pageContext.setAttribute("list", list);
%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${productVO.pd_name}</title>
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

	<form method="POST"
		action="<%=request.getContextPath()%>/ProductServlet" name="form1"
		enctype="multipart/form-data">
		<table>
			<tr>
				<td>商品編號:<font color=red><b>*</b></font></td>
				<td>${productVO.pd_no}</td>
			</tr>

			<tr>
				<td>商品名稱：</td>
				<td><input type="TEXT" name="pd_name"
					value="${productVO.pd_name}"></td>
			</tr>
			<tr>
				<td>商品價格：</td>
				<td><input type="TEXT" name="pd_price"
					value="${productVO.pd_price}"></td>
			</tr>
			<tr>
				<td>商品詳述：</td>
				<td><input type="TEXT" name="pd_detail"
					value="${productVO.pd_detail}"></td>
			</tr>

			<tr>
				<td>產品商品分類：</td>
				
				<td><select size="1" name="pd_typeNo">
						<c:forEach var="pd_typeVO" items="${list}">
							<option value="${pd_typeVO.pd_typeNo}" ${(productVO.pd_typeNo==pd_typeVO.pd_typeNo)? 'selected':''}>${pd_typeVO.pd_typeName}
						</c:forEach>
				</select></td>
			</tr>

			<tr>
				<td>商品分類狀態：</td>
				<td><select size="1" name="pd_status">
						<option value="1" ${productVO.pd_status==1?'selected':''}>下架</option>
						<option value="2" ${productVO.pd_status==2?'selected':''}>上架</option>

				</select></td>
			</tr>

			<tr>
				<td>商品圖片</td>
				<td><input type="file" name="pd_pic" id="upfile1"
					value="${productVO.pd_pic}"></td>
			<tr>
		</table>
		<input type="hidden" name="action" value="updateProduct"> <input
			type="hidden" name="pd_no" value="${productVO.pd_no}"> <input
			type="submit" name=送出修改>
	</form>

	<br>
	<br>
	<a
		href="<%=request.getContextPath()%>/back_end/product/ShopManager.jsp">回管理商城首頁</a>



</body>
</html>