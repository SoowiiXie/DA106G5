<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.loc_rpt.model.Loc_rptVO"%>
<%@ page import="com.loc_rpt.model.Loc_rptService"%>
<%@ page import="com.loc_rpt.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	Loc_rptVO loc_rptVO = (Loc_rptVO) request.getAttribute("loc_rptVO");
	//EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>地標檢舉資料 - listOneLoc_rpt.jsp</title>

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
				<h3>地標檢舉資料 - ListOneLoc_rpt.jsp</h3>
				<h4>
					<a href="<%= request.getContextPath() %>/back_end/loc_rpt/select_page.jsp">
						<img src="<%= request.getContextPath() %>/back_end/loc_rpt/images/back1.gif" width="100" height="32" border="0">
						回首頁
					</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>檢舉編號</th>
			<th>原因</th>
			<th>狀態</th>
			<th>地標編號</th>
			<th>檢舉會員</th>
		</tr>
		<!--//loc_rpt_no, rpt_reason, rpt_status, loc_no, mb_id -->
		<tr>
			<td><%=loc_rptVO.getLoc_rpt_no()%></td>
			<td><%=loc_rptVO.getRpt_reason()%></td>
			<td><%=loc_rptVO.getRpt_status()%></td>
			<td><%=loc_rptVO.getLoc_no()%></td>
			<td><%=loc_rptVO.getMb_id()%></td>
		</tr>
	</table>

</body>
</html>