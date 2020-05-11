<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.loc_rpt.model.Loc_rptVO"%>
<%@ page import="com.loc_rpt.model.*"%>

<%
	Loc_rptVO loc_rptVO = (Loc_rptVO) request.getAttribute("loc_rptVO");
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>新增地標檢舉 - addLoc_rpt.jsp</title>

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

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/loc_rpt.do" name="form1" enctype="multipart/form-data">
		<table>
<!-- 		//cmt_rpt_no, rpt_reason, rpt_status, cmt_no, mb_id -->
			<div class="form-group ml-1 mb-1 mr-1">
				<label style="font-size: 1rem">我覺得這一定有問題: </label>
				<input type="TEXT" name="rpt_reason" size="70" value="" placeholder="詳細原因(eg:不存在、需要收費、停止營業...)"/>
			</div>
		</table>
		<br> 
		<div class="fblightbox-footer bg-white">
			<input type="hidden" name="loc_no" id="loc_no4rptInsert" value=""> 
			<input type="hidden" name="mb_id" id="mb_id4rpt" value=""> 
			<input type="hidden" name="action" value="insert"> 
		    <input type="submit" value="檢舉這地標" class="fbbutton" id="loc_rpt_submit">
			<a href="#" id="close" class="fbbutton fbclose">這座標應該沒問題</a>
		 </div>
	</FORM>
</body>
</html>