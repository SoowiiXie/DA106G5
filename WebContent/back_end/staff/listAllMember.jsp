<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mb.model.*"%>
<%@ page import="com.abl.model.*"%>
<%@ page import="com.auth.model.*"%>
<%@ page import="java.util.*"%>

<%
	// 會員帳號若是全數字，會有問題(權限列表打不開)
	MemberService memberSvc = new MemberService();
    List<MemberVO> list = memberSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>所有會員資料 - listAllMember.jsp</title>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<style>
  	#wrap{
        text-align:center;
        font-family: 'Mamelon';	
	}
	#title{
		font-family: 'italics_hollow';
		line-height:10vh;
		font-size: 5.5vh;
		font-weight:bold;
		letter-spacing: 0.3vw;
		color:#0373f0;
		border-bottom:3px solid #0373f0;
	}
  
  table {
	width: 80vw;
	background-color: white;
	margin:2vh auto 0px auto;
	
	letter-spacing: 0.1vw;
	font-size: 2.5vh;
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
<div id="wrap">
<span id="title">所有會員資料</span><br>

<table>
	<tr>
		<th>會員ID</th>
<!-- 		<th>會員密碼</th> -->
		<th>姓名</th>
		<th>性別</th>
		<th>生日</th>
		<th>信箱</th>
		<th>照片</th>
		<th>會員<br>等級</th>
		<th>被檢舉<br>次數</th>
		<th>
			Status<br>
<!-- 			<select> -->
<!-- 				<option value="0">全部</option> -->
<%-- 				<c:forEach var="map" items="${memberStatus}"> --%>
<%-- 					<option value="${map.key}">${map.value}</option> --%>
<%-- 				</c:forEach> --%>
<!-- 			</select> -->
		</th>
<!-- 		<th>會員Line ID</th> -->
<!-- 		<th>會員Line頭貼</th> -->
<!-- 		<th>會員Line名稱</th> -->
<!-- 		<th>會員Line狀態</th> -->
		<th>修改<br>資料</th>
	</tr>
	
	<%@ include file="pages/page1.file" %> 
	<c:forEach var="memberVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr ${param.mb_id.equals(memberVO.mb_id)?"bgcolor='#CCCCFF'":""}>
			<td>${memberVO.mb_id}</td>
<%-- 			<td>${memberVO.mb_pwd}</td> --%>
			<td>${memberVO.mb_name}</td>
			<td>${memberGender[memberVO.mb_gender]}</td>
			<td>${memberVO.mb_birthday}</td>
			<td>${memberVO.mb_email}</td>
			<td><img src="<%= request.getContextPath()%>/MemberPicReader?mb_id=${memberVO.mb_id}" width="100px"></td>
			<td>${memberVO.mb_lv}</td>
			<td>${memberVO.mb_rpt_times}</td>
			<td>${memberStatus[memberVO.mb_status]}</td>
<%-- 			<td>${memberVO.mb_line_id}</td> --%>
<%-- 			<td>${memberVO.mb_line_pic}</td> --%>
<%-- 			<td>${memberVO.mb_line_display}</td> --%>
<%-- 			<td>${memberVO.mb_line_status}</td> --%>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/staff/member.do" style="margin-bottom: 0px;">
			     <input type="hidden" name="mb_id"  value="${memberVO.mb_id}">
			     <input type="hidden" name="includePath" value="${incluePath}">
			     <button type="submit" name="action" value="getOne_Member_For_Update">修改</button>
			  </FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2.file" %>
</div>

</body>
</html>