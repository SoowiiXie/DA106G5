<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.record.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  RecordVO recordVO = (RecordVO) request.getAttribute("recordVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
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
		 <h3>員工資料 - ListOneRECORD.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>紀錄編號</th>
		<th>上傳時間</th>
		<th>紀錄內容</th>
		<th>總按讚數</th>
		<th>總metoo數</th>
		<th>紀錄狀態</th>
		<th>紀錄編號</th>
		<th>會員編號</th>
	</tr>
	<tr>
		<td>${recordVO.rcd_no}</td>
		<td>${recordVO.rcd_uploadtime}</td><!-- <fmt:formatDate value="${recordVO.rcd_uploadtime}" pattern="yyyy-MM-dd"/> -->
		<td>${recordVO.rcd_content}</td>
		<td>${recordVO.rcd_thumb_amount}</td>
		<td>${recordVO.rcd_metoo_amount}</td>
		<td>${recordVO.rcd_status}</td> 
		<td>${recordVO.path_no}</td>
		<td>${recordVO.mb_id}</td>
	</tr>
</table>

</body>
</html>