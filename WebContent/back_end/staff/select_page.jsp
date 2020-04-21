<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.abl.model.*"%>
<%@ page import="java.util.*"%>

<%
	AbilityService abilitySvc = new AbilityService();
	Map<String, String> abilityMap = abilitySvc.getAllToMap();
	pageContext.setAttribute("abilityMap",abilityMap);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Staff Select Page</title>
</head>
<body>

	<h3>管理員${staffVO.staff_name}</h3>
	
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color:red">請修正以下錯誤:</font>
		<ul>
		    <c:forEach var="message" items="${errorMsgs}">
				<li style="color:red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	
	<a href="update_self.jsp">個人資料管理</a><br><br>
	
	<form METHOD="POST" action="staff.do" id="form1">
		<%-- 權限，用button是為了讓value可以使用 --%>
		<c:set var="entrySet" value="${abilityMap}"/> 
		<c:forEach var="map" items="${entrySet}">
		<button type="submit" form="form1" name="management" value="${map.key}">${map.value}</button>
		<br><br>
		</c:forEach>
		
		<input type="hidden" name="action" value="select_management">
		<input type="hidden" name="servletPath" value="<%=request.getServletPath()%>"><br>
	</form>
	
	<%-- 登出 --%>
	<form METHOD="POST" action="staff.do">
        <input type="hidden" name="action" value="logout">
        <input type="submit" value="登出"><br>
	</form>

</body>
</html>