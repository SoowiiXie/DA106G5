<<<<<<< HEAD
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.grouper.model.*"%>

<%-- 萬用複合查詢-可由客戶端select_page.jsp隨意增減任何想查詢的欄位 --%>
<%-- 此頁只作為複合查詢時之結果練習，可視需要再增加分頁、送出修改、刪除之功能--%>

<jsp:useBean id="listGrouper_ByCompositeQuery" scope="request" type="java.util.List<GrouperVO>" /> <!-- 於EL此行可省略 -->
<jsp:useBean id="locationSvc" scope="page" class="com.location.model.LocationService" /> 


<html>
<head><title>複合查詢 - listGrouper_ByCompositeQuery.jsp</title>

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
<body bgcolor='white'>

<h4>
☆萬用複合查詢  - 可由客戶端 select_page.jsp 隨意增減任何想查詢的欄位<br>
☆此頁作為複合查詢時之結果練習，<font color=red>已增加分頁、送出修改、刪除之功能</font></h4>
<table id="table-1">
	<tr><td>
		 <h3>所有員工資料 - listAllgroup.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>


<table>
	<tr>
		<th>揪團編號</th>
		<th>會員編號</th>
		<th>地標編號</th>
		<th>報名開始時間</th>
		<th>報名結束時間</th>
		<th>揪團開始時間</th>
		<th>揪團結束時間</th>
		<th>揪團名稱</th>
		<th>揪團內容</th>
		<th>人數上限</th>
		<th>人數下限</th>
		<th>人數</th>
		<th>揪團狀態</th>
		<th>關注揪團數</th>
		
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="pages/page1_ByCompositeQuery.file" %>
	<c:forEach var="grouperVO" items="${listGrouper_ByCompositeQuery}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle' ${(groupVO.grp_no==param.grp_no) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色而已-->
			<td>${grouperVO.grp_no}</td>
			<td>${grouperVO.mb_id}</td>			
<%-- 			<td>${grouperVO.loc_no}</td> --%>
			<td><c:forEach var="LocationVO" items="${locationSvc.all}">
                    <c:if test="${grouperVO.loc_no==LocationVO.loc_no}">
	                    ${LocationVO.loc_no}【${LocationVO.loc_address}】
                    </c:if>
                </c:forEach>
			</td>									
			<td>${grouperVO.grp_applystart}</td>
			<td>${grouperVO.grp_applyend}</td>
			<td>${grouperVO.grp_start}</td>
			<td>${grouperVO.grp_end}</td>
			<td>${grouperVO.grp_name}</td>
			<td>${grouperVO.grp_content}</td>
			<td>${grouperVO.grp_personmax}</td>
			<td>${grouperVO.grp_personmin}</td>
			<td>${grouperVO.grp_personcount}</td>			
			<td>${grouperVO.grp_status}</td>
			<td>${grouperVO.grp_follow}</td>	
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front_end/group/group.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改"> 
			     <input type="hidden" name="grp_no"      value="${grouperVO.grp_no}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			     <input type="hidden" name="action"	    value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front_end/group/group.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="grp_no"      value="${grouperVO.grp_no}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			     <input type="hidden" name="action"     value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2_ByCompositeQuery.file" %>

<br>本網頁的路徑:<br><b>
   <font color=blue>request.getServletPath():</font> <%=request.getServletPath()%><br>
   <font color=blue>request.getRequestURI(): </font> <%=request.getRequestURI()%> </b>

</body>
=======
<<<<<<< HEAD

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.grouper.model.*"%>

<%-- 萬用複合查詢-可由客戶端select_page.jsp隨意增減任何想查詢的欄位 --%>
<%-- 此頁只作為複合查詢時之結果練習，可視需要再增加分頁、送出修改、刪除之功能--%>

<jsp:useBean id="listGrouper_ByCompositeQuery" scope="request" type="java.util.List<GrouperVO>" /> <!-- 於EL此行可省略 -->
<jsp:useBean id="locationSvc" scope="page" class="com.location.model.LocationService" /> 


<html>
<head><title>複合查詢 - listGrouper_ByCompositeQuery.jsp</title>

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
<body bgcolor='white'>

<h4>
☆萬用複合查詢  - 可由客戶端 select_page.jsp 隨意增減任何想查詢的欄位<br>
☆此頁作為複合查詢時之結果練習，<font color=red>已增加分頁、送出修改、刪除之功能</font></h4>
<table id="table-1">
	<tr><td>
		 <h3>所有員工資料 - listAllgroup.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>


<table>
	<tr>
		<th>揪團編號</th>
		<th>會員編號</th>
		<th>地標編號</th>
		<th>報名開始時間</th>
		<th>報名結束時間</th>
		<th>揪團開始時間</th>
		<th>揪團結束時間</th>
		<th>揪團名稱</th>
		<th>揪團內容</th>
		<th>人數上限</th>
		<th>人數下限</th>
		<th>人數</th>
		<th>揪團狀態</th>
		<th>關注揪團數</th>
		
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="pages/page1_ByCompositeQuery.file" %>
	<c:forEach var="grouperVO" items="${listGrouper_ByCompositeQuery}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle' ${(groupVO.grp_no==param.grp_no) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色而已-->
			<td>${grouperVO.grp_no}</td>
			<td>${grouperVO.mb_id}</td>			
<%-- 			<td>${grouperVO.loc_no}</td> --%>
			<td><c:forEach var="LocationVO" items="${locationSvc.all}">
                    <c:if test="${grouperVO.loc_no==LocationVO.loc_no}">
	                    ${LocationVO.loc_no}【${LocationVO.loc_address}】
                    </c:if>
                </c:forEach>
			</td>									
			<td>${grouperVO.grp_applystart}</td>
			<td>${grouperVO.grp_applyend}</td>
			<td>${grouperVO.grp_start}</td>
			<td>${grouperVO.grp_end}</td>
			<td>${grouperVO.grp_name}</td>
			<td>${grouperVO.grp_content}</td>
			<td>${grouperVO.grp_personmax}</td>
			<td>${grouperVO.grp_personmin}</td>
			<td>${grouperVO.grp_personcount}</td>			
			<td>${grouperVO.grp_status}</td>
			<td>${grouperVO.grp_follow}</td>	
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front_end/group/group.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改"> 
			     <input type="hidden" name="grp_no"      value="${grouperVO.grp_no}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			     <input type="hidden" name="action"	    value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front_end/group/group.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="grp_no"      value="${grouperVO.grp_no}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			     <input type="hidden" name="action"     value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2_ByCompositeQuery.file" %>

<br>本網頁的路徑:<br><b>
   <font color=blue>request.getServletPath():</font> <%=request.getServletPath()%><br>
   <font color=blue>request.getRequestURI(): </font> <%=request.getRequestURI()%> </b>

</body>
=======
<<<<<<< HEAD
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.grouper.model.*"%>

<%-- 萬用複合查詢-可由客戶端select_page.jsp隨意增減任何想查詢的欄位 --%>
<%-- 此頁只作為複合查詢時之結果練習，可視需要再增加分頁、送出修改、刪除之功能--%>

<jsp:useBean id="listGrouper_ByCompositeQuery" scope="request" type="java.util.List<GrouperVO>" /> <!-- 於EL此行可省略 -->
<jsp:useBean id="locationSvc" scope="page" class="com.location.model.LocationService" /> 


<html>
<head><title>複合查詢 - listGrouper_ByCompositeQuery.jsp</title>

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
<body bgcolor='white'>

<h4>
☆萬用複合查詢  - 可由客戶端 select_page.jsp 隨意增減任何想查詢的欄位<br>
☆此頁作為複合查詢時之結果練習，<font color=red>已增加分頁、送出修改、刪除之功能</font></h4>
<table id="table-1">
	<tr><td>
		 <h3>所有員工資料 - listAllgroup.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>


<table>
	<tr>
		<th>揪團編號</th>
		<th>會員編號</th>
		<th>地標編號</th>
		<th>報名開始時間</th>
		<th>報名結束時間</th>
		<th>揪團開始時間</th>
		<th>揪團結束時間</th>
		<th>揪團名稱</th>
		<th>揪團內容</th>
		<th>人數上限</th>
		<th>人數下限</th>
		<th>人數</th>
		<th>揪團狀態</th>
		<th>關注揪團數</th>
		
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="pages/page1_ByCompositeQuery.file" %>
	<c:forEach var="grouperVO" items="${listGrouper_ByCompositeQuery}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle' ${(groupVO.grp_no==param.grp_no) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色而已-->
			<td>${grouperVO.grp_no}</td>
			<td>${grouperVO.mb_id}</td>			
<%-- 			<td>${grouperVO.loc_no}</td> --%>
			<td><c:forEach var="LocationVO" items="${locationSvc.all}">
                    <c:if test="${grouperVO.loc_no==LocationVO.loc_no}">
	                    ${LocationVO.loc_no}【${LocationVO.loc_address}】
                    </c:if>
                </c:forEach>
			</td>									
			<td>${grouperVO.grp_applystart}</td>
			<td>${grouperVO.grp_applyend}</td>
			<td>${grouperVO.grp_start}</td>
			<td>${grouperVO.grp_end}</td>
			<td>${grouperVO.grp_name}</td>
			<td>${grouperVO.grp_content}</td>
			<td>${grouperVO.grp_personmax}</td>
			<td>${grouperVO.grp_personmin}</td>
			<td>${grouperVO.grp_personcount}</td>			
			<td>${grouperVO.grp_status}</td>
			<td>${grouperVO.grp_follow}</td>	
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front_end/group/group.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改"> 
			     <input type="hidden" name="grp_no"      value="${grouperVO.grp_no}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			     <input type="hidden" name="action"	    value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front_end/group/group.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="grp_no"      value="${grouperVO.grp_no}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			     <input type="hidden" name="action"     value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2_ByCompositeQuery.file" %>

<br>本網頁的路徑:<br><b>
   <font color=blue>request.getServletPath():</font> <%=request.getServletPath()%><br>
   <font color=blue>request.getRequestURI(): </font> <%=request.getRequestURI()%> </b>

</body>
=======
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.grouper.model.*"%>

<%-- 萬用複合查詢-可由客戶端select_page.jsp隨意增減任何想查詢的欄位 --%>
<%-- 此頁只作為複合查詢時之結果練習，可視需要再增加分頁、送出修改、刪除之功能--%>

<jsp:useBean id="listGrouper_ByCompositeQuery" scope="request" type="java.util.List<GrouperVO>" /> <!-- 於EL此行可省略 -->
<jsp:useBean id="LocSvc" scope="page" class="com.location.model.LocationService" />


<html>
<head><title>複合查詢 - listGrouper_ByCompositeQuery.jsp</title>

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
<body bgcolor='white'>

<h4>
☆萬用複合查詢  - 可由客戶端 select_page.jsp 隨意增減任何想查詢的欄位<br>
☆此頁作為複合查詢時之結果練習，<font color=red>已增加分頁、送出修改、刪除之功能</font></h4>
<table id="table-1">
	<tr><td>
		 <h3>所有員工資料 - listAllgroup.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>


<table>
	<tr>
		<th>揪團編號</th>
		<th>會員編號</th>
		<th>地標編號</th>
		<th>報名開始時間</th>
		<th>報名結束時間</th>
		<th>揪團開始時間</th>
		<th>揪團結束時間</th>
		<th>揪團名稱</th>
		<th>揪團內容</th>
		<th>人數上限</th>
		<th>人數下限</th>
		<th>人數</th>
		<th>揪團狀態</th>
		<th>關注揪團數</th>
		
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="pages/page1_ByCompositeQuery.file" %>
	<c:forEach var="grouperVO" items="${listGrouper_ByCompositeQuery}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle' ${(groupVO.grp_no==param.grp_no) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色而已-->
			<td>${grouperVO.grp_no}</td>
			<td>${grouperVO.mb_id}</td>
			<td>${grouperVO.loc_no}</td>
			<td>${grouperVO.grp_applystart}</td>
			<td>${grouperVO.grp_applyend}</td>
			<td>${grouperVO.grp_start}</td>
			<td>${grouperVO.grp_end}</td>
			<td>${grouperVO.grp_name}</td>
			<td>${grouperVO.grp_content}</td>
			<td>${grouperVO.grp_personmax}</td>
			<td>${grouperVO.grp_personmin}</td>
			<td>${grouperVO.grp_personcount}</td>			
			<td>${grouperVO.grp_status}</td>
			<td>${grouperVO.grp_follow}</td>	
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front_end/group/group.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改"> 
			     <input type="hidden" name="grp_no"      value="${grouperVO.grp_no}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			     <input type="hidden" name="action"	    value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front_end/group/group.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="grp_no"      value="${grouperVO.grp_no}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			     <input type="hidden" name="action"     value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2_ByCompositeQuery.file" %>

<br>本網頁的路徑:<br><b>
   <font color=blue>request.getServletPath():</font> <%=request.getServletPath()%><br>
   <font color=blue>request.getRequestURI(): </font> <%=request.getRequestURI()%> </b>

</body>
>>>>>>> branch 'master' of https://github.com/SoowiiXie/DA106G5.git
>>>>>>> branch 'master' of https://github.com/SoowiiXie/DA106G5.git
>>>>>>> branch 'master' of https://github.com/SoowiiXie/DA106G5.git
</html>