<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cp_get.model.*"%>
<%@ page import="javax.servlet.http.*"%>
<%@ page import="javax.servlet.*" %>

<%
	                  request.getSession();
                  
%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>查詢會員持有優惠券</title>
</head>
<body>






<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

	<form method="POST" action="<%=request.getContextPath()%>/MemberServlet" name="form1">
	<table>
		<tr>
			<td>會員帳號：</td>
			<td><input type="TEXT" name="mb_id" value="michael123"></td>
		</tr>


	</table>
  <input type="hidden" name="action" value="memberSingIn">
    <input type="submit" name="Submit" value="會員登入">

  </form>



soowii123<br>
xuan123<br>
michael123<br>
vain123<br>
yiwen123<br>
weijhih123<br>

<a href="<%=request.getContextPath()%>/front_end/product/ShopHome.jsp">回到商城首頁</a>
<%-- <a href="<%=request.getContextPath()%>/front_end/product/ShopHome.jsp">回管理商城首頁</a> --%>

</body>
</html>