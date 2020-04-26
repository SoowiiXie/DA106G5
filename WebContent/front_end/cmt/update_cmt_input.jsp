<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cmt.model.CmtVO"%>
<%@ page import="com.cmt.model.CmtService"%>
<%@ page import="com.cmt.model.*"%>

<%
	CmtVO cmtVO = (CmtVO) request.getAttribute("cmtVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
	CmtService cmtSrv= new CmtService();
	if (cmtVO==null){
		cmtVO=cmtSrv.getOneCmt("cmt00001");
	}
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>留言資料修改 - update_cmt_input.jsp</title>

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

<!-- 	<table id="table-1"> -->
<!-- 		<tr> -->
<!-- 			<td> -->
<!-- 				<h3>留言資料修改 - update_cmt_input.jsp</h3> -->
<!-- 				<h4> -->
<%-- 					<a href="<%= request.getContextPath() %>/front_end/cmt/select_page.jsp"> --%>
<%-- 						<img src="<%= request.getContextPath() %>/front_end/cmt/images/back1.gif" width="100" height="32" border="0">回首頁 --%>
<!-- 					</a> -->
<!-- 				</h4> -->
<!-- 			</td> -->
<!-- 		</tr> -->
<!-- 	</table> -->

<!-- 	<h3>資料修改:</h3> -->

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<!--cmt_no, cmt_content, cmt_time, cmt_status, rcd_no, mb_id -->
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/cmt/cmt.do" name="form1" enctype="multipart/form-data">
		<table>
<!-- 			<tr> -->
<!-- 				<td>留言編號:</td> -->
<%-- 				<td><%=cmtVO.getCmt_no()%></td> --%>
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>時間:</td> -->
<%-- 				<td><%=cmtVO.getCmt_time()%></td> --%>
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>狀態:</td> -->
<%-- 				<td><%=cmtVO.getCmt_status()%></td> --%>
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>紀錄編號:</td> -->
<%-- 				<td><%=cmtVO.getRcd_no()%></td> --%>
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>留言會員:</td> -->
<%-- 				<td><%=cmtVO.getMb_id()%></td> --%>
<!-- 			</tr> -->
			<tr>
				<td>內容:</td>
				<td><input type="TEXT" id="cmt_contentFB" name="cmt_content" size="45" value="" /></td>
			</tr>

		</table>
		<br> 
		<input type="hidden" name="action" value="update"> 
		<input type="hidden" id="cmt_noFB" name="cmt_no" value="">
		<input type="hidden" id="cmt_timeFB" name="cmt_time" value="">
		<input type="hidden" id="cmt_statusFB" name="cmt_status" value="">
		<input type="hidden" id="rcd_noFB" name="rcd_no" value="">
		<input type="hidden" id="mb_idFB" name="mb_id" value="">
		<input type="hidden" id="mbDoThisID" name="mbDoThisID" value="">
		<input type="hidden" id="pageRun" name="pageRun" value="">
		<div class="fblightbox-footer bg-white">
		    <input type="submit" value="送出這些字" class="fbbutton">
			<a href="#" id="close" class="fbbutton">先不送了</a>
		 </div>
		
	</FORM>
</body>
</html>