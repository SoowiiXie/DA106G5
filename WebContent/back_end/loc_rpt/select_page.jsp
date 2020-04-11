<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>DA106G5 Loc_rpt: Home</title>

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
			<td><h3>DA106G5 Loc_rpt: Home</h3>
				<h4>( MVC )</h4></td>
		</tr>
	</table>

	<p>This is the Home page for DA106G5 Loc_rpt: Home</p>

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
		<li><a href='listAllLoc_rpt.jsp'>List</a> all Loc_rpt. <br>
		<br></li>

<!--//loc_rpt_no, rpt_reason, rpt_status, loc_no, mb_id -->
		<li>
			<FORM METHOD="post" ACTION="loc_rpt.do">
				<b>輸入地標檢舉編號 (如locr00001):</b> 
				<input type="text" name="loc_rpt_no">
				<input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>
		<jsp:useBean id="loc_rptSvc" scope="page" class="com.loc_rpt.model.Loc_rptService" />
		<jsp:useBean id="mbSvc" scope="page" class="com.mb.model.MemberService" />

		<li>
			<FORM METHOD="post" ACTION="loc_rpt.do">
				<b>選擇地標檢舉編號:</b> 
				<select size="1" name="loc_rpt_no">
					<c:forEach var="loc_rptVO" items="${loc_rptSvc.all}">
						<option value="${loc_rptVO.loc_rpt_no}">${loc_rptVO.loc_rpt_no}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>
		<li>
			<FORM METHOD="post" ACTION="loc_rpt.do">
				<b>選擇欲查詢的會員:</b> 
				<select size="1" name="mb_id">
					<c:forEach var="loc_rptVO" items="${mbSvc.all}">
						<option value="${loc_rptVO.mb_id}">${loc_rptVO.mb_id}
					</c:forEach>
				</select> 
				<input type="hidden" name="action" value="getByMb_id">
				<input type="submit" value="送出">
			</FORM>
		</li>
	</ul>
</body>
</html>