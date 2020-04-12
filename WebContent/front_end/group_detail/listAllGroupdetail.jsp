<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.grouper.model.GrouperVO"%>
<%@ page import="com.grouper.model.GrouperService"%>
<%@ page import="com.grouper.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    GrouperService grpSvc = new GrouperService();
    List<GrouperVO> list = grpSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有員工資料 - listAllGroup.jsp</title>

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
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr>
		<td>
		 	<h3>所有揪團資料 - listAllGroup.jsp</h3>
		 	<h4>
			 <a href="<%= request.getContextPath() %>/front_end/group/select_page.jsp">
		 	<img src="<%= request.getContextPath() %>/front_end/group/images/back1.gif" width="100" height="32" border="0">回首頁
	   		</a>
	   		</h4>
		</td>
	</tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>揪團編號</th>
		<th>發起人會員編號</th>
		<th>地標編號</th>
		<th>報名開始時間</th>
		<th>報名結束時間</th>
		<th>活動開始時間</th>
		<th>活動結束時間</th>
		<th>揪團標題</th>
		<th>揪團內容</th>
		<th>人數上限</th>
		<th>人數下限</th>
		<th>目前人數</th>
		<th>揪團狀態</th>
		<th>關注揪團數量</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="grouperVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${grouperVO.grp_no}</td>
			<td>${grouperVO.mb_id}</td>
			<td>${grouperVO.loc_no}</td>
			<td>${grouperVO.grp_applystart}</td>
			<td>${grouperVO.grp_applyend}</td>
			<td>${grouperVO.grp_start}</td> 
			<td>${grouperVO.grp_end}</td>
			<td>${grouperVO.grp_name}</td>
			<td>${grouperVO.grp_content}</td>
			<td>${grouperVO.grp_personmax}</td>
			<td>${grouperVO.grp_personmin}</td>
			<td>${grouperVO.grp_personcount}</td>
			<td>${grouperVO.grp_status}</td> 
			<td>${grouperVO.grp_follow}</td>
			<td>
			  <FORM METHOD="post" ACTION="group.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="grp_no"  value="${grouperVO.grp_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="group.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="grp_no"  value="${grouperVO.grp_no}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>