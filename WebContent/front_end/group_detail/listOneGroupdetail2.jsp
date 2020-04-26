<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.group_detail.model.*"%>
<%@ page import="com.location.model.*"%>
<%@ page import="java.util.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	Grp_detailVO grp_detailVO = (Grp_detailVO) request.getAttribute("grp_detailVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
	LocationVO locationVO = (LocationVO) request.getAttribute("locationVO");
%>

<jsp:useBean id="grp_detailSvc" scope="page" class="com.group_detail.model.Grp_detailService" />
<jsp:useBean id="locSvc" scope="page" class="com.location.model.LocationService" />
<jsp:useBean id="grpSvc" scope="page" class="com.grouper.model.GrouperService" />

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
		<th>地點</th>
		<th>報名開始時間</th>
		<th>報名結束時間</th>
		<th>活動開始時間</th>
		<th>活動結束時間</th>
		<th>揪團標題</th>
		<th>揪團內容</th>
		
<!-- 		<th>報到狀態</th> -->
		
	</tr>
	<tr>		

		<td><%=grp_detailVO.getGrp_no()%></td>	
		<td>
<%-- 				<c:forEach var="locationVO" items="${locSvc.all}"> --%>
<%-- 		        <c:if test="${grp_detailVO.grp_no==grouperVO.grp_no}">  --%>
<%-- 		        	<c:if test="${grouperVO.loc_no==locationVO.loc_no}">   		        				     --%>
<%-- 		                 ${grouperVOlocationVO.loc_address}<br> --%>
<%-- 		       		 </c:if > --%>
<%-- 		        </c:if >         --%>
					${locSvc.getOneLocation(grpSvc.getOneGroup(grp_detailVO.getGrp_no()).loc_no).loc_address}
<%-- 		        </c:forEach> --%>
		</td>
		<td>
		<c:forEach var="grouperVO" items="${grpSvc.all}">
                   <c:if test="${grp_detailVO.grp_no==grouperVO.grp_no}">
                    ${grouperVO.grp_applystart}                                           
                   </c:if>
        </c:forEach>
		</td>
		<td>
		<c:forEach var="grouperVO" items="${grpSvc.all}">
                   <c:if test="${grp_detailVO.grp_no==grouperVO.grp_no}">
                    ${grouperVO.grp_applyend}                                           
                   </c:if>
        </c:forEach>
		</td>
		<td>
		<c:forEach var="grouperVO" items="${grpSvc.all}">
                   <c:if test="${grp_detailVO.grp_no==grouperVO.grp_no}">
                    ${grouperVO.grp_start}                                           
                   </c:if>
        </c:forEach>
		</td>
		<td>
		<c:forEach var="grouperVO" items="${grpSvc.all}">
                   <c:if test="${grp_detailVO.grp_no==grouperVO.grp_no}">
                    ${grouperVO.grp_end}                                           
                   </c:if>
        </c:forEach>
		</td>
		<td>
		<c:forEach var="grouperVO" items="${grpSvc.all}">
                   <c:if test="${grp_detailVO.grp_no==grouperVO.grp_no}">
                    ${grouperVO.grp_name}                                           
                   </c:if>
        </c:forEach>
		</td>
		<td>
		<c:forEach var="grouperVO" items="${grpSvc.all}">
                   <c:if test="${grp_detailVO.grp_no==grouperVO.grp_no}">
                    ${grouperVO.grp_content}                                           
                   </c:if>
        </c:forEach>
		</td>
		
<%-- 		<%  --%>
<!-- // 		Map<Integer,String>register = new HashMap<>(); -->
<!-- // 		register.put(1,"未到"); -->
<!-- // 		register.put(2,"已到"); -->
<!-- // 		request.setAttribute("register", new String[]{"","未到","已到"}); -->
<%-- 		%> --%>
		
<%-- 		<td>${register[grp_detailVO.grp_register]}</td> --%>

	</tr>
	
</table>

</body>
</html>