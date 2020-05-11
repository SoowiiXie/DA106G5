<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cmt_rpt.model.Cmt_rptVO"%>
<%@ page import="com.cmt_rpt.model.Cmt_rptService"%>
<%@ page import="com.cmt_rpt.model.*"%>
<%@ page import="com.mb.model.*"%>
<%@ page import="com.cmt.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	Cmt_rptService cmt_rptSvc = new Cmt_rptService();
	List<Cmt_rptVO> list = cmt_rptSvc.getAll();
	pageContext.setAttribute("list", list);
	MemberService memberSvc = new MemberService();
	
// 	CmtService cmtSvc = new CmtService();
// 	cmtSvc.getOneCmt("cmt00001").getCmt_content()
	
%>
<!--留言Service -->
<jsp:useBean id="cmtSvcEL" scope="page"	class="com.cmt.model.CmtService" />

<html>
<head>
<title>所有檢舉資料 - listAllCmt_rpt.jsp</title>

	<!-- Custom fonts for this template-->
	<link href="<%= request.getContextPath() %>/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css" />
	<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet" />

	<!-- Custom styles for this template-->
	<link href="<%= request.getContextPath() %>/css/sb-admin-2.min.css" rel="stylesheet" />
	
	<!-- modal -->
<!-- 	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> -->
<!-- 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> -->
<!-- 	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->
	
	<!-- 會員智慧搜尋 -->
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	
	<!-- switch button -->
	<link href="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.6.1/css/bootstrap4-toggle.min.css" rel="stylesheet">
	
	<!-- index.css -->
	<link href="<%= request.getContextPath() %>/css/index.css" rel="stylesheet" />
	
	<!-- sweet alert -->
	<link href="//cdn.jsdelivr.net/npm/@sweetalert2/theme-dark@3/dark.css" rel="stylesheet">
	
	<!-- 彈跳div -->
	<link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">
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

	#allStaff_title{
		font-family: 'italics_hollow';
		line-height:10vh;
		font-size: 5.5vh;
		font-weight:bold;
		letter-spacing: 0.3vw;
		color:#0373f0;
		border-bottom:3px solid #0373f0;
	}
</style>

</head>
<body bgcolor='white'>

<!-- 	<h4>此頁練習採用 EL 的寫法取值:</h4> -->
<!-- 	<table id="table-1"> -->
<!-- 		<tr> -->
<!-- 			<td> -->
<!-- 				<h3>所有留言檢舉資料 - listAllCmt_rpt.jsp</h3> -->
<!-- 				<h4> -->
<%-- 					<a href="<%= request.getContextPath() %>/back_end/cmt_rpt/select_page.jsp"> --%>
<%-- 						<img src="<%= request.getContextPath() %>/back_end/cmt_rpt/images/back1.gif" width="100" height="32" border="0"> --%>
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
	<div class="mx-auto" style="width: 10.5rem;">
		<span id="allStaff_title" class="d-block mx-auto">留言檢舉</span>
	</div>
	<!-- 		//cmt_rpt_no, rpt_reason, rpt_status, cmt_no, mb_id -->
	<table class="table table-hover table-striped col-10 mx-auto my-5">
		<tr>
			<th>檢舉編號</th>
			<th>原因</th>
			<th>檢舉狀態</th>
			<th>留言編號</th>
			<th>留言內容</th>
			<th>檢舉會員</th>
			<th>被檢舉會員</th>
			<th>已被檢舉次數</th>
<!-- 			<th>修改</th> -->
			<th>審核</th>
		</tr>
		<jsp:useBean id="cmt_rptSvcEL" scope="page" class="com.cmt_rpt.model.Cmt_rptService" />
		<jsp:useBean id="memberSvcEL" scope="page" class="com.mb.model.MemberService" />
<%-- 		<%@ include file="page1.file"%> --%>
		<%@ include file="pages/page1.file"%>
		<c:forEach var="cmt_rptVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
			<tr>
				<td>${cmt_rptVO.cmt_rpt_no}</td>
				<td>${cmt_rptVO.rpt_reason}</td>
				<td>${(cmt_rptVO.rpt_status!=1?(cmt_rptVO.rpt_status==2?'成功':'失敗'):'未審')}</td>
				<td>${cmt_rptVO.cmt_no}</td>
				<td>${cmtSvcEL.getOneCmt(cmt_rptVO.cmt_no).getCmt_content()}</td>
				<td>${cmt_rptVO.mb_id}</td>
				<td>${memberSvcEL.getOneMember(cmt_rptSvcEL.getRptedMb_id(cmt_rptVO.cmt_no)).mb_id}</td>
<%-- 				<td><%= memberSvc.getOneMember(cmt_rptSvc.getRptedMb_id(cmt_rptVO.getCmt_no())).getMb_name() %></td> --%>
				<td>${memberSvcEL.getOneMember(cmt_rptSvcEL.getRptedMb_id(cmt_rptVO.cmt_no)).mb_rpt_times}</td>
<!-- 				<td> -->
<%-- 					<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/cmt_rpt/cmt_rpt.do" style="margin-bottom: 0px;"> --%>
<!-- 						<input type="submit" value="修改">  -->
<%-- 						<input type="hidden" name="cmt_rpt_no" value="${cmt_rptVO.cmt_rpt_no}">  --%>
<!-- 						<input type="hidden" name="action" value="getOne_For_Update"> -->
<%-- 						<input type="hidden" name="includePath" value="${includePath}"> --%>
<%-- 						<input type="hidden" name="rpt_includePath" value="${rpt_includePath}"> --%>
<!-- 					</FORM> -->
<!-- 				</td> -->
				<td>
					<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/cmt_rpt/cmt_rpt.do" style="margin-bottom: 0px;">
						<input type="submit" value="失敗/成功"> 
						<!-- 		//cmt_rpt_no, rpt_reason, rpt_status, cmt_no, mb_id -->
						<input type="hidden" name="cmt_rpt_no" value="${cmt_rptVO.cmt_rpt_no}"> 
						<input type="hidden" name="rpt_reason" value="${cmt_rptVO.rpt_reason}">
						<input type="hidden" name="rpt_status" value="${cmt_rptVO.rpt_status}">
						<input type="hidden" name="cmt_no" value="${cmt_rptVO.cmt_no}">
						<input type="hidden" name="mb_id" value="${cmt_rptVO.mb_id}">
						<input type="hidden" name="action" value="fakeDelete">
						<input type="hidden" name="includePath" value="${includePath}">
						<input type="hidden" name="rpt_includePath" value="${rpt_includePath}">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<div class="mx-auto" style="width: 30rem;">
<%-- 		<%@ include file="page2.file"%> --%>
		<%@ include file="pages/page2.file"%>
	</div>

</body>
</html>