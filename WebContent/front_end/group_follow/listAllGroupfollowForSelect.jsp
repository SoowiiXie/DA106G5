<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.group_follow.model.*"%>
<%@ page import="com.grouper.model.*"%>
<%@ page import="com.location.model.*"%>
<%@ page import="java.util.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	List<Group_followVO> group_followVOList = (List<Group_followVO>)request.getAttribute("group_followVOList"); //EmpServlet.java(Concroller), 存入req的empVO物件
	pageContext.setAttribute("group_followVOList",group_followVOList);
	GrouperService grouperSvc = new GrouperService();
	List<GrouperVO> grouperVOList = grouperSvc.getAll();//5
	
	LocationVO locationVO = (LocationVO) request.getAttribute("locationVO");
	
%>

<jsp:useBean id="locSvc" scope="page" class="com.location.model.LocationService" />
<jsp:useBean id="grpSvc" scope="page" class="com.grouper.model.GrouperService" />
<jsp:useBean id="group_followSvc" scope="page" class="com.group_follow.model.Group_followService" />

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
		 <h3>揪團詳細資料</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>
${list}123
<table>
	<tr>
		<th>會員編號</th>
		<th>揪團編號</th>
		
		<th>發起人會員編號</th>
		<th>地點</th>
		<th>報名開始時間</th>
		<th>報名結束時間</th>
		<th>活動開始時間</th>
		<th>活動結束時間</th>
		<th>揪團標題</th>
		<th>揪團內容</th>
		
	</tr>
${group_followSvc.all}
<c:forEach var="group_followVO" items="${group_followVOList}">
	<tr>		
		<td>${group_followVO.mb_id}</td>

		<td>${group_followVO.grp_no}</td>
				
		<td> ${grpSvc.getOneGroup(group_followVO.grp_no).mb_id}</td>
		
		<td>${locSvc.getOneLocation(grpSvc.getOneGroup(group_followVO.grp_no).loc_no).loc_address}</td>
		
		<td>${grpSvc.getOneGroup(group_followVO.grp_no).grp_applystart}</td>
		
		<td>${grpSvc.getOneGroup(group_followVO.grp_no).grp_applyend}</td>
		
		<td>${grpSvc.getOneGroup(group_followVO.grp_no).grp_start}</td>
		
		<td>${grpSvc.getOneGroup(group_followVO.grp_no).grp_end}</td>
		
		<td>${grpSvc.getOneGroup(group_followVO.grp_no).grp_name}</td>
		
		<td>${grpSvc.getOneGroup(group_followVO.grp_no).grp_content}</td>
		
	</tr>
   </c:forEach>

</table>

</body>
</html>