<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>DA106G5 Cmt_rpt: Home</title>

<style>
table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 3px ridge Gray;
	height: 80px;
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

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td><h3>DA106G5 Cmt_rpt: Home</h3>
				<h4>( MVC )</h4></td>
		</tr>
	</table>

	<p>This is the Home page for DA106G5 Cmt_rpt: Home</p>

	<h3>資料查詢:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<ul>
		<li><a href='listAllCmt_rpt.jsp'>List</a> all Cmt_rpt. <br>
		<br></li>

<!--//cmt_rpt_no, rpt_reason, rpt_status, cmt_no, mb_id -->
		<li>
			<FORM METHOD="post" ACTION="cmt_rpt.do">
				<b>輸入留言檢舉編號 (如cmtr00001):</b> 
				<input type="text" name="cmt_rpt_no">
				<input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>
		<jsp:useBean id="cmt_rptSvc" scope="page" class="com.cmt_rpt.model.Cmt_rptService" />
		<jsp:useBean id="mbSvc" scope="page" class="com.mb.model.MemberService" />

		<li>
			<FORM METHOD="post" ACTION="cmt_rpt.do">
				<b>選擇留言檢舉編號:</b> 
				<select size="1" name="cmt_rpt_no">
					<c:forEach var="cmt_rptVO" items="${cmt_rptSvc.all}">
						<option value="${cmt_rptVO.cmt_rpt_no}">${cmt_rptVO.cmt_rpt_no}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>
		<li>
			<FORM METHOD="post" ACTION="cmt_rpt.do">
				<b>選擇欲查詢的會員:</b> 
				<select size="1" name="mb_id">
					<c:forEach var="cmt_rptVO" items="${mbSvc.all}">
						<option value="${cmt_rptVO.mb_id}">${cmt_rptVO.mb_id}
					</c:forEach>
				</select> 
				<input type="hidden" name="action" value="getByMb_id">
				<input type="submit" value="送出">
			</FORM>
		</li>
	</ul>
</body>
</html>