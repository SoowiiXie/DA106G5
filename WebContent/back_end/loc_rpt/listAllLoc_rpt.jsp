<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.loc_rpt.model.Loc_rptVO"%>
<%@ page import="com.loc_rpt.model.Loc_rptService"%>
<%@ page import="com.loc_rpt.model.*"%>
<%@ page import="com.mb.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	Loc_rptService loc_rptSvc = new Loc_rptService();
	List<Loc_rptVO> list = loc_rptSvc.getAll();
	pageContext.setAttribute("list", list);
	MemberService memberSvc = new MemberService();
%>


<html>
<head>
<title>所有檢舉資料 - listAllLoc_rpt.jsp</title>

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

<!-- 	<h4>此頁練習採用 EL 的寫法取值:</h4> -->
<!-- 	<table id="table-1"> -->
<!-- 		<tr> -->
<!-- 			<td> -->
<!-- 				<h3>所有地標檢舉資料 - listAllLoc_rpt.jsp</h3> -->
<!-- 				<h4> -->
<%-- 					<a href="<%= request.getContextPath() %>/back_end/loc_rpt/select_page.jsp"> --%>
<%-- 						<img src="<%= request.getContextPath() %>/back_end/loc_rpt/images/back1.gif" width="100" height="32" border="0"> --%>
<!-- 						回首頁 -->
<!-- 					</a> -->
<!-- 				</h4> -->
<!-- 			</td> -->
<!-- 		</tr> -->
<!-- 	</table> -->

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<!-- 		//cmt_rpt_no, rpt_reason, rpt_status, cmt_no, mb_id -->
	<table>
		<tr>
			<th>檢舉編號</th>
			<th>原因</th>
			<th>檢舉狀態</th>
			<th>地標編號</th>
			<th>檢舉會員</th>
			<th>檢舉會員等級</th>
<!-- 			<th>修改</th> -->
			<th>審核</th>
		</tr>
		<jsp:useBean id="loc_rptSvcEL" scope="page" class="com.loc_rpt.model.Loc_rptService" />
		<jsp:useBean id="memberSvcEL" scope="page" class="com.mb.model.MemberService" />
<%-- 		<%@ include file="page1.file"%> --%>
		<c:forEach var="loc_rptVO" items="${list}">
<%-- 		<c:forEach var="loc_rptVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"> --%>
			<tr>
				<td>${loc_rptVO.loc_rpt_no}</td>
				<td>${loc_rptVO.rpt_reason}</td>
				<td>${(loc_rptVO.rpt_status!=1?(loc_rptVO.rpt_status==2?'成功':'失敗'):'未審')}</td>
				<td>${loc_rptVO.loc_no}</td>
				<td>${loc_rptVO.mb_id}</td>
				<td>${memberSvcEL.getOneMember(loc_rptVO.mb_id).mb_lv}</td>
<!-- 				<td> -->
<%-- 					<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/loc_rpt/loc_rpt.do" style="margin-bottom: 0px;"> --%>
<!-- 						<input type="submit" value="修改">  -->
<%-- 						<input type="hidden" name="loc_rpt_no" value="${loc_rptVO.loc_rpt_no}">  --%>
<!-- 						<input type="hidden" name="action" value="getOne_For_Update"> -->
<!-- 					</FORM> -->
<!-- 				</td> -->
				<td>
					<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/loc_rpt/loc_rpt.do" style="margin-bottom: 0px;">
						<input type="submit" value="失敗/成功"> 
						<!-- 		//cmt_rpt_no, rpt_reason, rpt_status, cmt_no, mb_id -->
						<input type="hidden" name="loc_rpt_no" value="${loc_rptVO.loc_rpt_no}"> 
						<input type="hidden" name="rpt_reason" value="${loc_rptVO.rpt_reason}">
						<input type="hidden" name="rpt_status" value="${loc_rptVO.rpt_status}">
						<input type="hidden" name="loc_no" value="${loc_rptVO.loc_no}">
						<input type="hidden" name="mb_id" value="${loc_rptVO.mb_id}">
						<input type="hidden" name="action" value="fakeDelete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
<%-- 	<%@ include file="page2.file"%> --%>

</body>
</html>