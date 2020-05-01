<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.grouper.model.*"%>
<%@ page import="com.location.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>
getOne_Time
<%
    GrouperService grpSvc = new GrouperService();
    List<GrouperVO> list = grpSvc.getAll();
    pageContext.setAttribute("list",list);
    
    GrouperVO grouperVO = (GrouperVO) request.getAttribute("grouperVO");
    LocationVO locationVO = (LocationVO) request.getAttribute("locationVO");
%>
<jsp:useBean id="locSvc" scope="page" class="com.location.model.LocationService" />
<jsp:useBean id="groupdetailSvc" scope="page" class="com.group_detail.model.Grp_detailService" />
<jsp:useBean id="groupfollowSvc" scope="page" class="com.group_follow.model.Group_followService" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<html>
<head>
<title>所有員工資料 - listAllGroup.jsp</title>

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

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr>
		<td>
		 	<h3>所有揪團資料 - listAllGroup.jsp</h3>
		 	<h4>
			 <a href="<%= request.getContextPath() %>/front_end/group/select_page.jsp">
		 	<img src="<%= request.getContextPath() %>/front_end/group/images/back1.gif" width="100" height="32" border="0">回首頁
	   		</a>
	   		</h4>
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
		<th>揪團編號</th>
		<th>發起人會員編號</th>
		<th>地標編號</th>
<!-- 		<th>報名開始時間</th> -->
<!-- 		<th>報名結束時間</th> -->
<!-- 		<th>活動開始時間</th> -->
<!-- 		<th>活動結束時間</th> -->
		<th>揪團標題</th>
		<th>揪團內容</th>
		<th>人數上限</th>
		<th>人數下限</th>
		<th>目前人數</th>
		<th>揪團狀態</th>
		<th>關注揪團數量</th>
		<th>修改</th>
		<th>刪除</th>
		<th>矮油</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="grouperVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td><A href="group.do?grp_no=${grouperVO.grp_no}&action=getOne_Time">${grouperVO.grp_no}</td>
			<td>${grouperVO.mb_id}</td>
<%-- 			<td>${grouperVO.loc_no}</td> --%>
<!-- 			改寫join -->

			<td>${locSvc.getOneLocation(grouperVO.getLoc_no()).loc_address} </td>
			
<%-- 			<td><c:forEach var="LocationVO" items="${locSvc.all}"> --%>
<%--                     <c:if test="${grouperVO.loc_no==LocationVO.loc_no}"> --%>
<%-- 	                    ${LocationVO.loc_no} --%>
<%-- 	                                                【${LocationVO.loc_address}】 --%>
<%--                     </c:if> --%>
<%--                 </c:forEach> --%>
<!-- 			</td> -->
			<%
// 			Map<Integer,String>status = new HashMap<>();
// 				status.put(1, "未滿");
// 				status.put(2, "已滿");
// 				status.put(3, "成功");
// 				status.put(4, "取消");
				request.setAttribute("status", new String[]{"","未滿", "已滿", "成功", "取消"});
// 				request.setAttribute("status", status);
			%>
		
<%-- 			<td>${grouperVO.grp_applystart}</td> --%>
<%-- 			<td>${grouperVO.grp_applyend}</td> --%>
<%-- 			<td>${grouperVO.grp_start}</td>  --%>
<%-- 			<td>${grouperVO.grp_end}</td> --%>
			<td>${grouperVO.grp_name}</td>
			<td>${grouperVO.grp_content}</td>
			<td>${grouperVO.grp_personmax}</td>
			<td>${grouperVO.grp_personmin}</td>
<%-- 			<td>${grouperVO.grp_personcount}</td> --%>
			<!-- 從揪團詳細表格算出揪團人數 -->
			<td>${groupdetailSvc.getTotalPeople(grouperVO.getGrp_no())}</td>
			<td>
<%-- 				${grouperVO.grp_status} --%>
				${status[grouperVO.grp_status]}</td> 
			
<%-- 		${status.get(Integer.parseInt(2))} --%>
<%-- 		${status.get(Integer(2))} --%>
<!-- 			他是基本型別的long -->
			<%--${status.get("2")} --%>
			<%--${status.get(String.valueOf(Integer.parseInt(2)))} --%>
			<%--<%= (String)(grouperVO.getGrp_status()) %> --%>
			<%--${status.get(grouperVO.grp_status)} --%>
 			<!-- 從揪團關注表格算出關注揪團人數 --> 
			<td>${groupfollowSvc.totalFollowPeople(grouperVO.getGrp_no())}</td>
			<td>
			  <FORM METHOD="post" ACTION="group.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="grp_no"  value="${grouperVO.grp_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="group.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="grp_no"  value="${grouperVO.grp_no}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="/front_end/group_detail/grp_detail.do" style="margin-bottom: 0px;">
			     <input type="submit" value="加入揪團">
			     <input type="text" name="grp_no"  value="${grouperVO.grp_no}">
			     <input type="text" name="mb_id"  value="${grouperVO.mb_id}">
			     <input type="hidden" name="action" value="update"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

<c:if test="${openModal!=null}">

<div class="modal fade" id="basicModal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
				
			<div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h3 class="modal-title" id="myModalLabel">The Bootstrap modal-header</h3>
            </div>
			
			<div class="modal-body">
<!-- =========================================以下為原listOneEmp.jsp的內容========================================== -->
               <jsp:include page="listOneGroup.jsp" />
<!-- =========================================以上為原listOneEmp.jsp的內容========================================= -->
			</div>
			
			<div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
		
		</div>
	</div>
</div>

        <script>
    		 $("#basicModal").modal({show: true});
        </script>
 </c:if>
 
</body>
</html>