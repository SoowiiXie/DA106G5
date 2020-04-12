<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.staff.model.*"%>
<%@ page import="java.util.*"%>

<%
	// 封裝此頁面(不能直接透過網址列進來)，需要登入管理員並擁有權限
	StaffService staffSvc = new StaffService();
    List<StaffVO> list = staffSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>所有管理員資料 - listAllStaff.jsp</title>

<style>
  #table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
    text-align: center;
  }
  #table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
  
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
<body>

<table id="table-1">
	<tr>
		<td>
			<h3>所有管理員資料 - listAllEmp.jsp</h3>
			<h4><a href="select_page.jsp">回首頁</a></h4>
		</td>
	</tr>
</table>

<table>
	<tr>
		<th>管理員ID</th>
		<th>管理員姓名</th>
		<th>加入日期</th>
		<th>狀態</th>
		<th>修改</th>
	</tr>
	<c:forEach var="staffVO" items="${list}">
		
		<tr>
			<td>${staffVO.staff_id}</td>
			<td>${staffVO.staff_name}</td>
			<td>${staffVO.staff_join}</td>
			<td>${staffVO.staff_status}</td>
			<td>
			  <FORM METHOD="post" ACTION="staff.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="staff_id"  value="${staffVO.staff_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update">
			  </FORM>
			</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>