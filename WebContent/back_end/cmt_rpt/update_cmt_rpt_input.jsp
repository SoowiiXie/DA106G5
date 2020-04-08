<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cmt_rpt.model.Cmt_rptVO"%>
<%@ page import="com.cmt_rpt.model.Cmt_rptService"%>
<%@ page import="com.cmt_rpt.model.*"%>

<%
	Cmt_rptVO cmt_rptVO = (Cmt_rptVO) request.getAttribute("cmt_rptVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>留言檢舉資料修改 - update_cmt_rpt_input.jsp</title>

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
				<h3>留言檢舉資料修改 - update_cmt_rpt_input.jsp</h3>
				<h4>
					<a href="<%= request.getContextPath() %>/back_end/cmt_rpt/select_page.jsp">
						<img src="<%= request.getContextPath() %>/back_end/cmt_rpt/images/back1.gif" width="100" height="32" border="0">回首頁
					</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料修改:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<!--//cmt_rpt_no, rpt_reason, rpt_status, cmt_no, mb_id -->
	<FORM METHOD="post" ACTION="cmt_rpt.do" name="form1" enctype="multipart/form-data">
		<table>
			<tr>
				<td>留言檢舉編號:</td>
				<td><%=cmt_rptVO.getCmt_rpt_no()%></td>
			</tr>
			<tr>
				<td>狀態:</td>
				<td><%=cmt_rptVO.getRpt_status()%></td>
			</tr>
			<tr>
				<td>被檢舉留言編號:</td>
				<td><%=cmt_rptVO.getCmt_no()%></td>
			</tr>
			<tr>
				<td>檢舉會員編號(不是被檢舉會員):</td>
				<td><%=cmt_rptVO.getMb_id()%></td>
			</tr>
			<tr>
				<td>內容:</td>
				<td><input type="TEXT" name="rpt_reason" size="45" value="<%=cmt_rptVO.getRpt_reason()%>" /></td>
			</tr>

		</table>
		<br> 
		<input type="hidden" name="action" value="update"> 
		<input type="hidden" name="cmt_rpt_no" value="<%=cmt_rptVO.getCmt_rpt_no()%>">
		<input type="hidden" name="rpt_status" value="<%=cmt_rptVO.getRpt_status()%>">
		<input type="hidden" name="cmt_no" value="<%=cmt_rptVO.getCmt_no()%>">
		<input type="hidden" name="mb_id" value="<%=cmt_rptVO.getMb_id()%>">
		<input type="submit" value="送出修改">
	</FORM>
</body>
</html>