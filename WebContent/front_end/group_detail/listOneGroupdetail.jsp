<<<<<<< HEAD
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.group_detail.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	Grp_detailVO grp_detailVO = (Grp_detailVO) request.getAttribute("grp_detailVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>員工資料 - listOneGroup.jsp</title>

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
	<tr><td>
		 <h3>揪團資料 - ListOneGroup.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>會員編號</th>
		<th>揪團編號</th>
		<th>報到狀態</th>
		
	</tr>
	<tr>		
		<td><%=grp_detailVO.getMb_id()%></td>
		<td><%=grp_detailVO.getGrp_no()%></td>
		<td><%=grp_detailVO.getGrp_register()%></td>

	</tr>
	
</table>

</body>
=======
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.grouper.model.GrouperVO"%>
<%@ page import="com.grouper.model.GrouperService"%>
<%@ page import="com.grouper.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	GrouperVO grouperVO = (GrouperVO) request.getAttribute("grouperVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>員工資料 - listOneGroup.jsp</title>

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
	<tr><td>
		 <h3>揪團資料 - ListOneGroup.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

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
	</tr>
	<tr>
		<td><%=grouperVO.getGrp_no()%></td>
		<td><%=grouperVO.getMb_id()%></td>
		<td><%=grouperVO.getLoc_no()%></td>
		<td><%=grouperVO.getGrp_applystart()%></td>
		<td><%=grouperVO.getGrp_applyend()%></td>
		<td><%=grouperVO.getGrp_start()%></td>
		<td><%=grouperVO.getGrp_end()%></td>
		<td><%=grouperVO.getGrp_name()%></td>
		<td><%=grouperVO.getGrp_content()%></td>
		<td><%=grouperVO.getGrp_personmax()%></td>
		<td><%=grouperVO.getGrp_personmin()%></td>
		<td><%=grouperVO.getGrp_personcount()%></td>
		<td><%=grouperVO.getGrp_status()%></td>
		<td><%=grouperVO.getGrp_follow()%></td>
	</tr>
	
</table>

</body>
>>>>>>> branch 'master' of https://github.com/SoowiiXie/DA106G5.git
</html>