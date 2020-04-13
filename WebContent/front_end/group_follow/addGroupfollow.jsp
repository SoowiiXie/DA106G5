<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.group_follow.model.*"%>

<%
  Group_followVO group_followVO = (Group_followVO) request.getAttribute("group_followVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>揪團資料新增 - addGroupfollow.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>揪團資料新增 - addGroupfollow.jsp</h3></td><td>
		 <h4>
			<a href="<%= request.getContextPath() %>/front_end/group_follow/select_page.jsp">
			<img src="<%= request.getContextPath() %>/front_end/group_follow/images/back1.gif" width="100" height="32" border="0">
			回首頁</a>
		 </h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="group_follow.do" name="form1">
<table>
	<tr>
		<td>揪團名稱:</td>
		<td><input type="TEXT" name="grp_no" size="45" 
			 value="<%= (group_followVO==null)? "grp00004" : group_followVO.getGrp_no()%>" /></td>
	</tr>
	<tr>
		<td>發起人會員編號:</td>
		<td><input type="TEXT" name="mb_id" size="45"
			 value="<%= (group_followVO==null)? "yiwen123" : group_followVO.getMb_id()%>" /></td>
	</tr>
	

	<jsp:useBean id="deptSvc" scope="page" class="com.group_follow.model.Group_followService" />



</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

</html>