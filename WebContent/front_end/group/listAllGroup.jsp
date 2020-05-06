<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.grouper.model.*"%>
<%@ page import="com.location.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>
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
<title>所有揪團資料 - listAllGroup.jsp</title>

<style>
  #title{
	background-color:#F9F900;
    border: 5px solid #000080;
    border-radius: 10px;
    text-align: center;
    width: 98%;
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
  table, th, td {
    border: 1px solid #000080;
    background-color: rgba(256,256,256,0);
    
  }
  table {
	width: 90%;
 	background-color: white; 
	margin-top: 5px;
	margin-bottom: 5px;
	background-color: rgba(256,256,256,0);
  }  
  th, td {
    padding: 5px;
    text-align: center;
  }
  #oneGroup_table, #oneGroup_table tr,#oneGroup_table td,#oneGroup_table th{
  	border: 1px solid #000;
  	margin: 50px auto 5px auto;
  }
  #oneGroup_table{
  width: 100%
  }
  body{
  	font-family:Microsoft JhengHei;"
  }
  #oneGroup_table th{
  background: #D5E0CC;
  }
  #allGroup{
  	width: 95%
   }
  #allGroup, #allGroup tr,#allGroup td,#allGroup th{ 	
  	border: 1px solid #000;
  	margin: 50px auto 5px auto;
  }
  #w10, #w10 tr, #w10 td{
  width: 10%;
  }
  #w15 #w10 tr,#w10 td{
  width: 15%;
  }
</style>

</head>
<body bgcolor='white'>


<table id="title">
	<tr>
		<td>
		 	<h3>目前揪團列表</h3>
		 	<h4>
			 <a href="<%= request.getContextPath() %>/front_end/index.jsp?pageRun=group/select_page.jsp">
		 	<img src="<%= request.getContextPath() %>/front_end/group/images/homeIcon.png" width="75" height="75" border="0">
		 	回到查詢揪團
	   		</a><br>
	   		</h4>
		</td>
	</tr>
</table><br>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<br>
<table id=allGroup style="background-color: rgba(33,33,33,0);">
	<tr>
		<th id="w15">揪團編號</th>
		<th id="w15">發起人會員編號</th>
<!-- 		<th>地標編號</th> -->
<!-- 		<th>報名開始時間</th> -->
<!-- 		<th>報名結束時間</th> -->
<!-- 		<th>活動開始時間</th> -->
<!-- 		<th>活動結束時間</th> -->
		<th id="w15">揪團標題</th>
<!-- 		<th>揪團內容</th> -->
		<th id="w10">人數上限</th>
		<th id="w10">人數下限</th>
		<th id="w10">目前人數</th>
		<th id="w15">揪團狀態</th>
		<th id="w10">關注人數</th>
<!-- 		<th>修改</th> -->
<!-- 		<th>刪除</th> -->
<!-- 		<th>矮油</th> -->
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="grouperVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td><A href="group.do?grp_no=${grouperVO.grp_no}&action=getOne_Time">${grouperVO.grp_no}</td>
			<td id="w15">${grouperVO.mb_id}</td>
<%-- 			<td>${grouperVO.loc_no}</td> --%>
<!-- 			改寫join -->

<%-- 			<td>${locSvc.getOneLocation(grouperVO.getLoc_no()).loc_address} </td> --%>
			
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
			<td id="w15">${grouperVO.grp_name}</td>
<%-- 			<td>${grouperVO.grp_content}</td> --%>
			<td id="w10">${grouperVO.grp_personmax}</td>
			<td id="w10">${grouperVO.grp_personmin}</td>
<%-- 			<td>${grouperVO.grp_personcount}</td> --%>
			<!-- 從揪團詳細表格算出揪團人數 -->
			<td id="w10">${groupdetailSvc.getTotalPeople(grouperVO.getGrp_no())}</td>
			<td id="w15">
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
			<td id="w10">${groupfollowSvc.totalFollowPeople(grouperVO.getGrp_no())}</td>
<!-- 			<td> -->
<!-- 			  <FORM METHOD="post" ACTION="group.do" style="margin-bottom: 0px;"> -->
<!-- 			     <input type="submit" value="修改"> -->
<%-- 			     <input type="hidden" name="grp_no"  value="${grouperVO.grp_no}"> --%>
<!-- 			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
<!-- 			</td> -->
<!-- 			<td> -->
<!-- 			  <FORM METHOD="post" ACTION="/front_end/grouper/group.do" style="margin-bottom: 0px;"> -->
<!-- 			     <input type="submit" value="刪除"> -->
<%-- 			     <input type="hidden" name="grp_no"  value="${grouperVO.grp_no}"> --%>
<!-- 			     <input type="hidden" name="action" value="delete"></FORM> -->
<!-- 			</td> -->
<!-- 			<td> -->
<!-- 			  <FORM METHOD="post" ACTION="/front_end/group_detail/grp_detail.do" style="margin-bottom: 0px;"> -->
<!-- 			     <input type="submit" value="加入揪團"> -->
<%-- 			     <input type="text" name="grp_no"  value="${grouperVO.grp_no}"> --%>
<%-- 			     <input type="text" name="mb_id"  value="${grouperVO.mb_id}"> --%>
<!-- 			     <input type="hidden" name="action" value="update"></FORM> -->
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
            </div>
            <h3 class="modal-title" id="myModalLabel">揪團內容</h3>
			
			<div class="modal-body">
<!-- =========================================以下為原listOneEmp.jsp的內容========================================== -->
               <jsp:include page="listOneGroup.jsp" />
<!-- =========================================以上為原listOneEmp.jsp的內容========================================= -->
			</div>
			
			<div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
<!--                 <button type="button" class="btn btn-primary">Save changes</button> -->
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