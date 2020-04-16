<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.rcd_rpt.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  Rcd_rptVO rcd_rptVO = (Rcd_rptVO) request.getAttribute("Rcd_rptVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
//	System.out.println(recordVO.getRcd_uploadtime());
%>

<html>
<head>
<title>員工資料 - listOneEmp.jsp</title>

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
		 <h3>員工資料 - ListOneRCD_RPT.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>檢舉編號</th>
		<th>檢舉內容</th>
		<th>檢舉狀態</th>
		<th>紀錄編號</th>
		<th>會員編號</th>

	</tr>
	<tr>
		<td>${rcd_rptVO.rcd_rpt_no}</td>
		<td>${rcd_rptVO.rpt_reason}</td>
		<td>${(rcd_rptVO.rpt_status)!=1?(rcd_rptVO.rpt_status==2?'成功':'失敗'):'尚未審核'}</td> 
		<td>${rcd_rptVO.rcd_no}</td>
		<td>${rcd_rptVO.mb_id}</td>
	</tr>
</table>

</body>
</html>