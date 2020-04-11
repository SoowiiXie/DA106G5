<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.loc_rpt.model.Loc_rptVO"%>
<%@ page import="com.loc_rpt.model.*"%>

<%
	Loc_rptVO loc_rptVO = (Loc_rptVO) request.getAttribute("loc_rptVO");
%>
<%=loc_rptVO == null%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>新增地標檢舉 - addLoc_rpt.jsp</title>

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
		<tr>
			<td>
				<h3>地標檢舉資料新增 - addLoc_rpt.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="<%= request.getContextPath() %>/front_end/location/select_page.jsp">
						<img src="<%= request.getContextPath() %>/front_end/location/images/back1.gif" width="100" height="32" border="0">
						回首頁
					</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料新增:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="loc_rpt.do" name="form1" enctype="multipart/form-data">
		<table>
<!-- 		//cmt_rpt_no, rpt_reason, rpt_status, cmt_no, mb_id -->
			<tr>
				<td>檢舉原因:</td>
				<td>
					<input type="TEXT" name="rpt_reason" size="45" value="<%=(loc_rptVO == null) ? "this is reason" : loc_rptVO.getRpt_reason()%>" />
				</td>
			</tr>
			<tr>
				<td>地標編號:</td>
				<td>
					<input type="TEXT" name="loc_no" size="45" value="<%=(loc_rptVO == null) ? "loc00001" : loc_rptVO.getLoc_no()%>" />
				</td>
			</tr>
			<tr>
				<td>檢舉會員(不是被檢舉的會員):</td>
				<td>
					<input type="TEXT" name="mb_id" size="45" value="<%=(loc_rptVO == null) ? "soowii123" : loc_rptVO.getMb_id()%>" />
				</td>
			</tr>

		</table>
		<br> 
		<input type="hidden" name="action" value="insert"> 
		<input type="submit" value="送出新增">
	</FORM>
</body>
</html>