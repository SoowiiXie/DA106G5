<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.group_follow.model.*"%>
<%@ page import="com.grouper.model.*"%>
<%@ page import="com.location.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mb.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	MemberService memberSvc = new MemberService();
	MemberVO memberVO =(MemberVO)session.getAttribute("memberVO");
	pageContext.setAttribute("mb_id", memberVO.getMb_id());
	
	List<Group_followVO> group_followVOList = (List<Group_followVO>)request.getAttribute("group_followVOList"); //EmpServlet.java(Concroller), 存入req的empVO物件
	pageContext.setAttribute("group_followVOList",group_followVOList);
	GrouperService grouperSvc = new GrouperService();
	List<GrouperVO> grouperVOList = grouperSvc.getAll();//5
	
	LocationVO locationVO = (LocationVO) request.getAttribute("locationVO");
	
%>

<jsp:useBean id="locSvc" scope="page" class="com.location.model.LocationService" />
<jsp:useBean id="grpSvc" scope="page" class="com.grouper.model.GrouperService" />
<jsp:useBean id="group_followSvc" scope="page" class="com.group_follow.model.Group_followService" />

<html>
<head>
<title>員工資料 - listOneGroup.jsp</title>

<style>
#title{
	background-color:#F9F900;
    border: 5px solid #000080;
    font-family : Microsoft JhengHei;
    border-radius: 10px;
    text-align: center;
    width: 98%;
    }
  table#title h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
  body{
  font-family:Microsoft JhengHei;"
  }
</style>

<style>
  #listDetail{
	width: 98%;
	background: #FFFFFF;
	margin-top: 5px;
	margin-bottom: 5px;
	font-size:24px;
  }
  #listDetail table,#listDetail th,#listDetail td {
    border: 1px solid #CCCCFF;
  }
  #listDetail th{
  background:#D5E0CC;
  }
  th#listDetail, td#listDetail {
    padding: 5px;
    text-align: center;   
  }
</style>

</head>
<body>

<table id="title">
	<tr><td>
     	 <a href="<%= request.getContextPath() %>/front_end/index.jsp?pageRun=group/select_page.jsp">
		 <img src="<%= request.getContextPath() %>/front_end/group/images/homeIcon.png" width="75" height="75" border="0">
					回到瀏覽揪團</a>
	</td></tr>
</table>
<table id="listDetail" style="background-color: rgba(256,256,256,0);">
	<tr>
<!-- 		<th>會員編號</th> -->
		<th>揪團編號</th>
		
		<th>發起人編號</th>
		<th>地點</th>
<!-- 		<th>報名開始時間</th> -->
<!-- 		<th>報名結束時間</th> -->
		<th>活動開始時間</th>
		<th>活動結束時間</th>
		<th>揪團標題</th>
		<th>揪團內容</th>
		
	</tr>
<%-- ${group_followSvc.all} --%>
<c:forEach var="group_followVO" items="${group_followSvc.getAllGroup_followByMb_id(mb_id)}">
	<tr>		
<%-- 		<td>${group_followVO.mb_id}</td> --%>

		<td>${group_followVO.grp_no}</td>
				
		<td> ${grpSvc.getOneGroup(group_followVO.grp_no).mb_id}</td>
		
		<td>${locSvc.getOneLocation(grpSvc.getOneGroup(group_followVO.grp_no).loc_no).loc_address}</td>
		
<!-- 		<td>		 -->
<%-- 		<fmt:formatDate value="${grpSvc.getOneGroup(group_followVO.grp_no).grp_applystart}" pattern="yyyy-MM-dd H:mm"/> --%>
<!-- 		</td> -->
		
<!-- 		<td>		 -->
<%-- 		<fmt:formatDate value="${grpSvc.getOneGroup(group_followVO.grp_no).grp_applyend}" pattern="yyyy-MM-dd H:mm"/> --%>
<!-- 		</td> -->
		
		<td>		
		<fmt:formatDate value="${grpSvc.getOneGroup(group_followVO.grp_no).grp_start}" pattern="yyyy-MM-dd H:mm"/>
		</td>
		
		<td>		
		<fmt:formatDate value="${grpSvc.getOneGroup(group_followVO.grp_no).grp_end}" pattern="yyyy-MM-dd H:mm"/>
		</td>
		
		<td>${grpSvc.getOneGroup(group_followVO.grp_no).grp_name}</td>
		
		<td>${grpSvc.getOneGroup(group_followVO.grp_no).grp_content}</td>
		
	</tr>
   </c:forEach>

</table>

</body>
</html>