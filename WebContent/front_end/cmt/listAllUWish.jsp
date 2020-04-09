<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cmt.model.CmtVO"%>
<%@ page import="com.cmt.model.CmtService"%>
<%@ page import="com.cmt.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	@SuppressWarnings("unchecked")
	List<CmtVO> list = (List<CmtVO>)request.getAttribute("cmtVO_list");
	pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>所有留言資料 - listAllUWish.jsp</title>

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
				<h3>所有留言資料 - listAllUWish.jsp</h3>
				<h4>
					<a href="<%= request.getContextPath() %>/front_end/cmt/select_page.jsp">
						<img src="<%= request.getContextPath() %>/front_end/cmt/images/back1.gif" width="100" height="32" border="0">
						回首頁
					</a>
				</h4>
			</td>
		</tr>
	</table>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<!--cmt_no, cmt_content, cmt_time, cmt_status, rcd_no, mb_id -->
	<table>
		<tr>
			<th>留言編號</th>
			<th>內容</th>
			<th>時間</th>
			<th>狀態</th>
			<th>紀錄編號</th>
			<th>留言會員</th>
			<th>修改</th>
			<th>上/下架</th>
		</tr>
<%-- 		<%@ include file="page1.file"%> --%>
<%-- 		<c:forEach var="cmtVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"> --%>
		<c:forEach var="cmtVO" items="${list}">
		<!--cmt_no, cmt_content, cmt_time, cmt_status, rcd_no, mb_id -->			
			<tr>
				<td>${cmtVO.cmt_no}</td>
				<td>${cmtVO.cmt_content}</td>
				<td>${cmtVO.cmt_time}</td>
				<td>${cmtVO.cmt_status}</td>
				<td>${cmtVO.rcd_no}</td>
				<td>${cmtVO.mb_id}</td>
				<td>
					<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/cmt/cmt.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> 
						<input type="hidden" name="cmt_no" value="${cmtVO.cmt_no}"> 
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/cmt/cmt.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="上/下架"> 
						<input type="hidden" name="cmt_no" value="${cmtVO.cmt_no}"> 
						<input type="hidden" name="cmt_time" value="${cmtVO.cmt_time}">
						<input type="hidden" name="cmt_status" value="${cmtVO.cmt_status}">
						<input type="hidden" name="rcd_no" value="${cmtVO.rcd_no}">
						<input type="hidden" name="mb_id" value="${cmtVO.mb_id}">
						<input type="hidden" name="cmt_content" value="${cmtVO.cmt_content}">
						<input type="hidden" name="action" value="fakeDelete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
<%-- 	<%@ include file="page2.file"%> --%>


</body>
</html>