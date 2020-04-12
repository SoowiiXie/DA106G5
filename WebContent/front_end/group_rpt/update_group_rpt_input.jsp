<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.group_rpt.model.Group_rptVO"%>
<%@ page import="com.group_rpt.model.Group_rptService"%>
<%@ page import="com.group_rpt.model.*"%>

<%
	Group_rptVO group_rptVO = (Group_rptVO) request.getAttribute("group_rptVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>揪團資料修改 - update_group_rpt_put.jsp</title>

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
	<tr><td>
		 <h3>揪團資料修改 - update_group_rpt_input.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="group_rpt.do" name="form1">
<table>
	<tr>
		<td>檢舉揪團編號:</td>
		<td><input type="TEXT" name="group_rpt_no" size="45" 
			 value="<%= (group_rptVO==null)? "grr00010" : group_rptVO.getGroup_rpt_no()%>" /></td>
	</tr>

	<tr>
		<td>揪團編號:</td>
		<td><input type="TEXT" name="grp_no" size="45"
			 value="<%= (group_rptVO==null)? "Tommy" : group_rptVO.getGrp_no()%>" /></td>
	</tr>

	<tr>
		<td>檢舉會員編號:</td>
		<td><input type="TEXT" name="mb_id" size="45"
			 value="<%= (group_rptVO==null)? "loc00003" : group_rptVO.getMb_id()%>" /></td>
	</tr>
	
	<tr>
		<td>檢舉原因:</td>
		<td><input type="TEXT" id="rpt_reason" size="45"
			value="<%= (group_rptVO==null)? "Tommy" : group_rptVO.getRpt_reason()%>" /></td>
	</tr>
	
	<tr>
		<td>處理狀態:</td>
		<td><input type="TEXT" id="rpt_status" size="45"
			value="<%= (group_rptVO==null)? "Tommy" : group_rptVO.getRpt_status()%>" /></td>
	</tr>
	
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="group_rpt_no" value="<%=group_rptVO.getGroup_rpt_no()%>">
<input type="submit" value="送出修改"></FORM>
</body>


</html>