<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.group_detail.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	Integer  peoplecount =  (Integer)request.getAttribute("peoplecount"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

	<jsp:useBean id="grpdetailSvc" scope="page" class="com.group_detail.model.Grp_detailService" />
	          
       <input type="hidden" name="action" value="getGroupCount">
     	
     共有<%=peoplecount %>人
       
   
  
	
</body>
</html>