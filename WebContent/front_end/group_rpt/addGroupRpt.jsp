<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.group_rpt.model.*"%>

<%
  Group_rptVO group_rptVO = (Group_rptVO) request.getAttribute("group_rptVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>揪團資料新增 - addGroup_rpt.jsp</title>

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
		 <h3>揪團資料新增 - addGroup.jsp</h3></td><td>
		 <h4><a href="select_page.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

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
		<td>輸入揪團檢舉編號:</td>
		<td><input type="TEXT" name="group_rpt_no" size="45" 
			 value="<%= (group_rptVO==null)? "grr00010" : group_rptVO.getGroup_rpt_no()%>" /></td>
	</tr>
	<tr>
		<td>揪團編號:</td>
		<td><input type="TEXT" name="grp_no" size="45"
			 value="<%= (group_rptVO==null)? "grp00010" : group_rptVO.getGrp_no()%>" /></td>
	</tr>
	<tr>
		<td>檢舉會員編號:</td>
		<td><input type="TEXT" name="mb_id" size="45"
			 value="<%= (group_rptVO==null)? "michael123" : group_rptVO.getMb_id()%>" /></td>
	</tr>
	
	
	<tr>
		<td>檢舉原因:</td>
		<td><input type="TEXT" name="rpt_reason" size="45"
			 value="<%= (group_rptVO==null)? "理由" : group_rptVO.getRpt_reason()%>" /></td>
	</tr>
	
	<tr>
		<td>處理狀態:</td>
		<td><input type="TEXT" name="rpt_status" size="45"
			 value="<%= (group_rptVO==null)? "1" : group_rptVO.getRpt_status()%>" /></td>
	</tr>
	


</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

</html>