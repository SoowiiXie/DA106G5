<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.staff.model.*"%>
<%@ page import="com.abl.model.*"%>
<%@ page import="com.auth.model.*"%>
<%@ page import="java.util.*"%>

<%
	// 封裝此頁面(不能直接透過網址列進來)，需要登入管理員並擁有權限
	StaffService staffSvc = new StaffService();
    List<StaffVO> list = staffSvc.getAll();
    pageContext.setAttribute("list",list);
    
    AbilityService abilitySvc = new AbilityService();
    Map<String, String> abilityList = abilitySvc.getAllToMap();
    pageContext.setAttribute("abilityList",abilityList);
    
    AuthorityService authoritySvc = new AuthorityService();
%>

<html>
<head>
<title>所有管理員資料 - listAllStaff.jsp</title>

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
<body>

<table id="table-1">
	<tr>
		<td>
			<h3>管理員${staffVO.staff_name}</h3>
			<h3>所有管理員資料 - listAllStaff.jsp</h3>
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
		<th>管理員ID</th>
		<th>管理員姓名</th>
		<th>加入日期</th>
		<th>狀態   篩選</th>
		<th>資料修改</th>
		<th>權限修改</th>
	</tr>
	<c:forEach var="staffVO" items="${list}">
		
		<tr>
			<td>${staffVO.staff_id}</td>
			<td>${staffVO.staff_name}</td>
			<td>${staffVO.staff_join}</td>
			<td>${staffStatus[staffVO.staff_status]}</td>
			<td>
			  <FORM METHOD="post" ACTION="staff.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     
			     <input type="hidden" name="staff_id"  value="${staffVO.staff_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update">
			     <input type="hidden" name="servletPath" value="<%=request.getServletPath()%>"><br>
			  </FORM>
			</td>
			<td><button >修改</button></td>
		</tr>
		<%-- 權限 --%>
		<c:set var="staff_id" value="${staffVO.staff_id}" scope="request"/>
		<%
			Set<String> authoritySet = authoritySvc.getOneStaffAuthority(((StaffVO)request.getAttribute("staffVO")).getStaff_id());
			System.out.println(((StaffVO)request.getAttribute("staffVO")).getStaff_id());
		%>
		<tr>
			<td colspan="6">
				<c:set var="entrySet" value="${abilityList.entrySet()}"/> 
				<c:forEach var="map" items="${entrySet}">
					<input type="checkbox" value="${map.key}" ${staffVO.staff_status==map.key?'ckecked':''}>
				</c:forEach>
			</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>