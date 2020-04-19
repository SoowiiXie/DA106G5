<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.* "%>
<%@ page import="com.pd_follow.model.*, com.product.model.* "%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%=request.getServletPath()%>
<%  
   
	String mb_id = (String) session.getAttribute("mb_id");
	Pd_followService pd_followService = new Pd_followService();

	List<Pd_followVO> list = pd_followService.searchMemberAllPdFollow(mb_id);

	pageContext.setAttribute("list", list);
%>
<jsp:useBean id="productService" scope="page"
	class="com.product.model.ProductService" />
<jsp:useBean id="pd_typeService" scope="page"
	class="com.pd_type.model.Pd_typeService" />
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	
		<table border="3">

			<tr>
				<th width="100">商品圖片</th>

				<th width="100">商品名稱</th>
				<th width="100">商品價格</th>
				<th width="100"></th>


			</tr>
			<%@ include file="page3.file"%>
			<%@ include file="page4.file"%>
			<c:forEach var="pd_followVO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">

				<tr>
					<td><a
						href='<%=request.getContextPath()%>/ShoppingServlet?action=findOneProduct&pd_no=${pd_followVO.pd_no}'><img
							src="<%= request.getContextPath()%>/ProductPicReader?pd_no=${pd_followVO.pd_no}"
							width="100px"></a></td>
					<td><a
						href='<%=request.getContextPath()%>/ShoppingServlet?action=findOneProduct&pd_no=${pd_followVO.pd_no}'>${productService.findOneProduct(pd_followVO.pd_no).pd_name}</a></td>
					<td>${productService.findOneProduct(pd_followVO.pd_no).pd_price}</td>
					<form method="POST" action="<%=request.getContextPath()%>/Pd_followServlet" name="form1">
					<td><input type="submit" name="Submit" value="刪除商品收藏">
						<input type="hidden" name="pd_no" value="${pd_followVO.pd_no}">
						<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
						<input type="hidden" name="whichPage" value="<%=whichPage%>">
						<input type="hidden" name="action" value="DeleteOnePd_follow">
						</form>
						</td>

				</tr>

			</c:forEach>
		</table>
		
	

	<a href="<%=request.getContextPath()%>/front_end/product/ShopHome.jsp">回商城首頁</a>
</body>
=======
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.* "%>
<%@ page import="com.pd_follow.model.*, com.product.model.* "%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<%  
   
	String mb_id = (String) session.getAttribute("mb_id");
	Pd_followService pd_followService = new Pd_followService();

	List<Pd_followVO> list = pd_followService.searchMemberAllPdFollow(mb_id);

	pageContext.setAttribute("list", list);
%>
<jsp:useBean id="productService" scope="page"
	class="com.product.model.ProductService" />
<jsp:useBean id="pd_typeService" scope="page"
	class="com.pd_type.model.Pd_typeService" />
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form method="POST" action="<%=request.getContextPath()%>/Pd_followServlet" name="form1">
		<table border="3">

			<tr>
				<th width="100">商品圖片</th>

				<th width="100">商品名稱</th>
				<th width="100">商品價格</th>
				<th width="100"></th>


			</tr>
			<%@ include file="page3.file"%>
			<%@ include file="page4.file"%>
			<c:forEach var="pd_followVO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">

				<tr>
					<td><a
						href='<%=request.getContextPath()%>/ShoppingServlet?action=findOneProduct&pd_no=${pd_followVO.pd_no}'><img
							src="<%= request.getContextPath()%>/ProductPicReader?pd_no=${pd_followVO.pd_no}"
							width="100px"></a></td>
					<td><a
						href='<%=request.getContextPath()%>/ShoppingServlet?action=findOneProduct&pd_no=${pd_followVO.pd_no}'>${productService.findOneProduct(pd_followVO.pd_no).pd_name}</a></td>
					<td>${productService.findOneProduct(pd_followVO.pd_no).pd_price}</td>
					<td><input type="submit" name="Submit" value="刪除商品收藏">
						<input type="hidden" name="pd_no" value="${pd_followVO.pd_no}">
						<input type="hidden" name="action" value="DeleteOnePd_follow">
						
						</td>

				</tr>

			</c:forEach>
		</table>
		<%@ include file="page4.file"%>
	</form>

	<a href="<%=request.getContextPath()%>/front_end/product/ShopHome.jsp">回商城首頁</a>
</body>
>>>>>>> SoowiiLoc
</html>