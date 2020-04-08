<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>DA106G5 Cmt: Home</title>

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
			<td><h3>DA106G5 Cmt: Home</h3>
				<h4>( MVC )</h4></td>
		</tr>
	</table>

	<p>This is the Home page for DA106G5 Cmt: Home</p>

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
		<li><a href='listAllCmt.jsp'>List</a> all Cmt. <br>
		<br></li>


		<li>
			<FORM METHOD="post" ACTION="cmt.do">
				<b>輸入留言編號 (如cmt00001):</b> 
				<input type="text" name="cmt_no">
				<input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>
		<!--cmt_no, cmt_content, cmt_time, cmt_status, rcd_no, mb_id -->
		<jsp:useBean id="cmtSvc" scope="page" class="com.cmt.model.CmtService" />
		<jsp:useBean id="rcdSvc" scope="page" class="com.record.model.RecordService" />
		<jsp:useBean id="mbSvc" scope="page" class="com.mb.model.MemberService" />

		<li>
			<FORM METHOD="post" ACTION="cmt.do">
				<b>選擇留言編號:</b> 
				<select size="1" name="cmt_no">
					<c:forEach var="cmtVO" items="${cmtSvc.all}">
						<option value="${cmtVO.cmt_no}">${cmtVO.cmt_no}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>
		
		<li>
			<FORM METHOD="post" ACTION="cmt.do">
				<b>選擇欲查詢紀錄編號:</b> 
				<select size="1" name="rcd_no">
					<c:forEach var="cmtVO" items="${rcdSvc.all}">
						<option value="${cmtVO.rcd_no}">${cmtVO.rcd_no}
					</c:forEach>
				</select> 
				<input type="hidden" name="action" value="getByRcd_no">
				<input type="submit" value="送出">
			</FORM>
		</li>

		<li>
			<FORM METHOD="post" ACTION="cmt.do">
				<b>選擇欲查詢的會員:</b> 
				<select size="1" name="mb_id">
					<c:forEach var="cmtVO" items="${mbSvc.all}">
						<option value="${cmtVO.mb_id}">${cmtVO.mb_id}
					</c:forEach>
				</select> 
				<input type="hidden" name="action" value="getByMb_id">
				<input type="submit" value="送出">
			</FORM>
		</li>


	</ul>


	<h3>留言管理</h3>

	<ul>
		<li><a href='addCmt.jsp'>Add</a> a new Cmt.</li>
	</ul>

</body>
</html>