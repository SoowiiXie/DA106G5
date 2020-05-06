<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.grouper.model.GrouperVO"%>
<%@ page import="com.grouper.model.GrouperService"%>
<%@ page import="com.grouper.model.*"%>
<%@ page import="com.location.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	GrouperVO grouperVO = (GrouperVO) request.getAttribute("grouperVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
	LocationService locSvcJSP = new LocationService();
	LocationVO locVO = locSvcJSP.getOneLocation(grouperVO.getLoc_no());
	pageContext.setAttribute("locVO", locVO);
%>

<jsp:useBean id="grp_detailSvc" scope="page" class="com.group_detail.model.Grp_detailService" />
<jsp:useBean id="locSvc" scope="page" class="com.location.model.LocationService" />
<jsp:useBean id="grpSvc" scope="page" class="com.grouper.model.GrouperService" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- 取出對應的LocationVO物件 -->


<html>
<head>
<title>員工資料 - listOneGroup.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
    width: 100%;
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
  #w10{
  width: 10%;
  }
  #w40{
  width: 40%;
  }
</style>

<style>
  table {
	width: 100%;
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

<!-- <h4>此頁暫練習採用 Script 的寫法取值:</h4> -->
<table id="table-1">
	<tr><td>
<!-- 		 <h3>揪團資料 - ListOneGroup.jsp</h3> -->
<!-- 		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4> -->
	</td></tr>
</table>

<table id="oneGroup_table">
	<tr>
		<th>揪團編號</th>
<!-- 		<th>發起人會員編號</th> -->
		<th>地標編號</th>
		<th>報名開始時間</th>
		<th>報名結束時間</th>
		<th>活動開始時間</th>
		<th>活動結束時間</th>
<!-- 		<th>揪團標題</th> -->
		
<!-- 		<th>人數上限</th> -->
<!-- 		<th>人數下限</th> -->
<!-- 		<th>目前人數</th> -->
<!-- 		<th>揪團狀態</th> -->
<!-- 		<th>關注揪團數量</th> -->
	</tr>
	<tr>
		<td id="w10"><%=grouperVO.getGrp_no()%></td>
<%-- 		<td><%=grouperVO.getMb_id()%></td> --%>
		<td id="w10">
<!-- 		%=grouperVO.getLoc_no()%> -->
		<%=locVO.getLoc_address() %></td>
<%-- 		<td><%=grouperVO.getGrp_applystart()%></td> --%>
<td id="w10"><fmt:formatDate value="<%=grouperVO.getGrp_applystart()%>" pattern="yyyy-MM-dd H:mm"/></td>
<%-- 		<td><%=grouperVO.getGrp_applyend()%></td> --%>
<td id="w10"><fmt:formatDate value="<%=grouperVO.getGrp_applyend()%>" pattern="yyyy-MM-dd H:mm"/></td>
<%-- 		<td><%=grouperVO.getGrp_start()%></td> --%>
<td id="w10"><fmt:formatDate value="<%=grouperVO.getGrp_start()%>" pattern="yyyy-MM-dd H:mm"/></td>		
<%-- 		<td><%=grouperVO.getGrp_end()%></td> --%>
<td id="w10"><fmt:formatDate value="<%=grouperVO.getGrp_end()%>" pattern="yyyy-MM-dd H:mm"/></td>		
<%-- 		<td><%=grouperVO.getGrp_name()%></td> --%>
		<tr>
			<th colspan="6">揪團內容</th>
		</tr>
		
		<tr>		
			<td colspan="6" id="w40"><%=grouperVO.getGrp_content()%></td>
		</tr>
<%-- 		<td><%=grouperVO.getGrp_personmax()%></td> --%>
<%-- 		<td><%=grouperVO.getGrp_personmin()%></td> --%>
<%-- 		<td><%=grouperVO.getGrp_personcount()%></td> --%>
<%-- 		<td><%=grouperVO.getGrp_status()%></td> --%>
<%-- 		<td><%=grouperVO.getGrp_follow()%></td> --%>
	</tr>
	
</table>

</body>
</html>