<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cmt.model.CmtVO"%>
<%@ page import="com.cmt.model.CmtService"%>
<%@ page import="com.cmt.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	CmtVO cmtVO = (CmtVO) request.getAttribute("cmtVO");
	//EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>留言資料 - listOneCmt.jsp</title>

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
	width: 600px;
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

	<h4>此頁暫練習採用 Script 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>留言資料 - ListOneCmt.jsp</h3>
				<h4>
					<a href="<%= request.getContextPath() %>/front_end/cmt/select_page.jsp">
						<img src="<%= request.getContextPath() %>/front_end/cmt/images/back1.gif" width="100" height="32" border="0">
						回首頁
					</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>留言編號</th>
			<th>內容</th>
			<th>時間</th>
			<th>狀態</th>
			<th>紀錄編號</th>
			<th>會員</th>
		</tr>
		<!--cmt_no, cmt_content, cmt_time, cmt_status, rcd_no, mb_id -->
		<tr>
			<td><%=cmtVO.getCmt_no()%></td>
			<td><%=cmtVO.getCmt_content()%></td>
			<td><%=cmtVO.getCmt_time()%></td>
			<td><%=cmtVO.getCmt_status()%></td>
			<td><%=cmtVO.getRcd_no()%></td>
			<td><%=cmtVO.getMb_id()%></td>
		</tr>
	</table>

</body>
</html>