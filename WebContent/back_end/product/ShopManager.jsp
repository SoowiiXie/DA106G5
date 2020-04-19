<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>後台管理</title>
</head>
<body>


<a href="<%=request.getContextPath()%>/back_end/product/addProduct.jsp">增加商品</a>
<a href="<%=request.getContextPath()%>/back_end/product/ListAll.jsp">管理所有商品</a>
<a href="<%=request.getContextPath()%>/ProductServlet?action=searchTypeList&&pd_typeNo=">管理所有商品2</a>
<a href="<%=request.getContextPath()%>/OrdersServlet?action=getAllList">管理所有訂單</a>
<a href="<%=request.getContextPath()%>/back_end/product/SearchOneMemberCpGet.jsp">查詢單一會員持有優惠券</a>
<br>
	<br>
	<a href="<%=request.getContextPath()%>/front_end/product/ShopHome.jsp">到商城頁面</a>
    
</body>
</html>