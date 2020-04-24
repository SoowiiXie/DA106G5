<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mb.model.*"%>
<%@ page import="com.abl.model.*"%>
<%@ page import="com.auth.model.*"%>
<%@ page import="java.util.*"%>

<%
	// 會員帳號若是全數字，會有問題(權限列表打不開)
	MemberService staffSvc = new MemberService();
    List<MemberVO> list = staffSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>所有會員資料 - listAllMember.jsp</title>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<style>
  #table-1 {
  	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
    text-align: center;
  }
  #table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
  
  table {
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
  
  .authorityRow{
  	display:none;
  }
  form{
  	margin:0px;
  }
</style>

</head>
<body>

<table id="table-1">
	<tr>
		<td>
			<h3>管理員${staffVO.staff_name}</h3>
			<h3>所有會員資料 - listAllStaff.jsp</h3>
			<h4><a href="select_page.jsp">回首頁</a></h4>
		</td>
	</tr>
</table>

<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color:red">請修正以下錯誤:</font>
		<ul>
		    <c:forEach var="message" items="${errorMsgs}">
				<li style="color:red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

<table>
	<tr>
		<th>會員ID</th>
<!-- 		<th>會員密碼</th> -->
		<th>會員姓名</th>
		<th>會員性別</th>
		<th>會員生日</th>
		<th>會員信箱</th>
		<th>會員照片</th>
<!-- 		<th>會員等級</th> -->
<!-- 		<th>會員被檢舉次數</th> -->
		<th>會員狀態</th>
<!-- 		<th>會員Line ID</th> -->
<!-- 		<th>會員Line頭貼</th> -->
<!-- 		<th>會員Line名稱</th> -->
<!-- 		<th>會員Line狀態</th> -->
		<th>詳細資料</th>
	</tr>
	<c:forEach var="memberVO" items="${list}">
		
		<tr ${param.member_id.equals(memberVO.mb_id)?"bgcolor='#CCCCFF'":""}>
			<td>${memberVO.mb_id}</td>
<%-- 			<td>${memberVO.mb_pwd}</td> --%>
			<td>${memberVO.mb_name}</td>
			<td>${memberGender[memberVO.mb_gender]}</td>
			<td>${memberVO.mb_birthday}</td>
			<td>${memberVO.mb_email}</td>
			<td><img src="<%= request.getContextPath()%>/MemberPicReader?mb_id=${memberVO.mb_id}" width="100px"></td>
<%-- 			<td>${memberVO.mb_lv}</td> --%>
<%-- 			<td>${memberVO.mb_rpt_times}</td> --%>
			<td>${memberStatus[memberVO.mb_status]}</td>
<%-- 			<td>${memberVO.mb_line_id}</td> --%>
<%-- 			<td>${memberVO.mb_line_pic}</td> --%>
<%-- 			<td>${memberVO.mb_line_display}</td> --%>
<%-- 			<td>${memberVO.mb_line_status}</td> --%>
			<td>
			  <FORM METHOD="post" ACTION="member.do" style="margin-bottom: 0px;">
			     <input type="submit" value="查看">
			     
			     <input type="hidden" name="mb_id"  value="${memberVO.mb_id}">
			     <input type="hidden" name="action"	value="getOne_Member_For_Update">
			     <input type="hidden" name="servletPath" value="<%=request.getServletPath()%>"><br>
			  </FORM>
			</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>