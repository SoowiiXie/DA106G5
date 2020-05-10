<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.group_detail.model.*"%>
<%@ page import="com.grouper.model.*"%>
<%@ page import="com.grouper.model.*"%>


<%
  Grp_detailService grp_detailSvc = new Grp_detailService();
//   String mb_id = (String)request.getAttribute("mb_id");
  String mb_id = (String)request.getParameter("mb_id");
  String grp_no = (String)request.getParameter("grp_no");
//   String grp_no = (String)request.getAttribute("grp_no");

  
  
  Grp_detailVO group_detailVO = grp_detailSvc.addGrp_detail(mb_id, grp_no, 1);
//   Grp_detailVO group_detailVO = grp_detailSvc.get
//   GrouperVO grouperVO = (GrouperVO) request.getAttribute("grouperVO");
  pageContext.setAttribute("group_detailVO", group_detailVO);

%>
JSP<%=mb_id %>
EL${param.mb_id}
<!--揪團Service -->
<jsp:useBean id="grouperSvcEL" scope="page"	class="com.grouper.model.GrouperService" />
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>加入揪團 - addGroup.jsp</title>

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
		 <h3>加入揪團 - addGroup.jsp</h3></td><td>
		 <h4><p>
			<a href="<%= request.getContextPath() %>/front_end/group_detail/select_page.jsp">
			<img src="<%= request.getContextPath() %>/front_end/group_detail/images/back1.gif" width="100" height="32" border="0">
			回首頁</a>
		 </h4>
	</td></tr>
</table>

<h3>加入揪團:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="/front_end/group_detail/grp_detail.do">
"${group_detailVO.grp_no}"

<table>
	
	<tr>
		<td>會員編號:</td>
		<td><input type="TEXT" name="mb_id" size="45"
			 value="<%= (group_detailVO==null)? " " : group_detailVO.getMb_id()%>" /></td>
	</tr>
	<tr>
		<td>${grp_detailVO.grp_no}揪團名稱:</td>
		<td><input type="TEXT" name="grp_no" size="45" 
			 value="${grp_detailVO.grp_no}"/></td>
	</tr>
	<tr>
		<td>揪團狀態:</td>
		<td><input type="TEXT" name="grp_register" size="45"
			 value=1 /></td>
	</tr>	
	
	

	<jsp:useBean id="grpdetailSvc" scope="page" class="com.group_detail.model.Grp_detailService" />
	<jsp:useBean id="grpSvc" scope="page" class="com.grouper.model.GrouperService" />


</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="加入"></FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->
</html>