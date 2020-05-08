<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.grouper.model.*"%>
<%@ page import="com.mb.model.*"%>
<%
MemberService memberSvc = new MemberService();
MemberVO memberVO =(MemberVO)session.getAttribute("memberVO");
pageContext.setAttribute("mb_id", memberVO.getMb_id());
%>

<%-- 萬用複合查詢-可由客戶端select_page.jsp隨意增減任何想查詢的欄位 --%>
<%-- 此頁只作為複合查詢時之結果練習，可視需要再增加分頁、送出修改、刪除之功能--%>

<jsp:useBean id="listGrouper_ByCompositeQuery" scope="session" type="java.util.List<GrouperVO>" /> <!-- 於EL此行可省略 -->
<jsp:useBean id="locationSvc" scope="page" class="com.location.model.LocationService" /> 
<jsp:useBean id="groupdetailSvc" scope="page" class="com.group_detail.model.Grp_detailService" />
<jsp:useBean id="groupfollowSvc" scope="page" class="com.group_follow.model.Group_followService" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<html>
<head><title>複合查詢 - listGrouper_ByCompositeQuery.jsp</title>

<style>
	#comAll{
	width:100%;
/* 	background-image: url('/DA106_G5/front_end/group/webFront/c7.jpg'); */
	}
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
	#ByCom{
	width: 98%;
	background-color:rgba(220,231,117 ,0.5);
	border:2px solid #CCCCFF;
	font-size:18px;
    color: black;
	}
  table {
	width: 1200px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
	font-family:microsoft jhengHei;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  #ByCom th, #ByCom td {
    padding: 5px;
    text-align: center;
    border: 1px solid #CCCCFF;
  }
input:disabled,
input[disabled]{
  border: 1px solid #999999;
  background-color: #cccccc;
  color: #666666;
}
</style>

</head>
<body bgcolor='white'>
<div id="comAll">
${pageRun}123
listGrouper_ByCompositeQuery.jsp<br>
<!-- ☆萬用複合查詢  - 可由客戶端 select_page.jsp 隨意增減任何想查詢的欄位<br> -->
<!-- ☆此頁作為複合查詢時之結果練習，<font color=red>已增加分頁、送出修改、刪除之功能</font> -->
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


<table id="ByCom">
	<tr>
		<th>揪團編號</th>
		<th>會員編號</th>
		<th>地標編號</th>
<!-- 		<th>報名開始時間</th> -->
<!-- 		<th>報名結束時間</th> -->
<!-- 		<th>揪團開始時間</th> -->
<!-- 		<th>揪團結束時間</th> -->
		<th>揪團名稱</th>
<!-- 		<th>揪團內容</th> -->
<!-- 		<th>人數上限</th> -->
		<th>人數下限</th>
<!-- 		<th>人數</th> -->
		<th>揪團狀態</th>
		<th>關注揪團數</th>
		
		<th>修改</th>
<!-- 		不給刪除 -->
<!-- 		<th>刪除</th> -->
		<th>目前人數/人數上限</th>

	</tr>
	<%@ include file="pages/page1_ByCompositeQuery.file" %>
	<c:forEach var="grouperVO" items="${listGrouper_ByCompositeQuery}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle' ${(grouperVO.grp_no==param.grp_no) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色而已-->
			<td><A href="group.do?grp_no=${grouperVO.grp_no}&action=getOne_Time2">${grouperVO.grp_no}</A></td>
			<td>${grouperVO.mb_id}</td>			
<%-- 			<td>${grouperVO.loc_no}</td> --%>
			<td><c:forEach var="LocationVO" items="${locationSvc.all}">
                    <c:if test="${grouperVO.loc_no==LocationVO.loc_no}">
<%-- 	                    ${LocationVO.loc_no}<br> --%>
	                    ${LocationVO.loc_address}
                    </c:if>
                </c:forEach>
			</td>									
<%-- 			<td><fmt:formatDate value="${grouperVO.grp_applystart}" pattern="yyyy-MM-dd H:mm"/></td> --%>
<%-- 			<td><fmt:formatDate value="${grouperVO.grp_applyend}" pattern="yyyy-MM-dd H:mm"/></td> --%>
<%-- 			<td><fmt:formatDate value="${grouperVO.grp_start}" pattern="yyyy-MM-dd H:mm"/></td> --%>
<%-- 			<td><fmt:formatDate value="${grouperVO.grp_end}" pattern="yyyy-MM-dd H:mm"/></td> --%>
			<td>${grouperVO.grp_name}</td>
<%-- 			<td>${grouperVO.grp_content}</td> --%>
<!-- 			人數上限 -->
<%-- 			<td>${grouperVO.grp_personmax}</td> --%>
			<td>${grouperVO.grp_personmin}</td>
<!-- 			總人數 -->
<%-- 			<td>${groupdetailSvc.getTotalPeople(grouperVO.getGrp_no())}</td> --%>
			<%
			request.setAttribute("grp_status", new String[]{"","未滿","已滿","取消","成功"} );			
			%>
			
						
			<td>
<%-- 			${grouperVO.grp_status} --%>
<%-- 			${grp_status[grouperVO.grp_status]} --%>
			${grouperVO.grp_personmax > groupdetailSvc.getTotalPeople(grouperVO.getGrp_no())?"未滿":"人數已滿"}
			</td>
<%-- 			<td>${grouperVO.grp_follow}</td>	 --%>
			<td>${groupfollowSvc.totalFollowPeople(grouperVO.getGrp_no())}</td>
			<td>													   
<!-- 																   /front_end/index.jsp?pageRun=group/group.do"  -->
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front_end/group/group.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改" ${mb_id ==  grouperVO.mb_id?"":"disabled"}> 
			     <input type="hidden" name="grp_no"      value="${grouperVO.grp_no}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			     <input type="hidden" name="action"	    value="getOne_For_Update"></FORM>
			</td>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front_end/group/group.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="刪除"> -->
<%-- 			     <input type="hidden" name="grp_no"      value="${grouperVO.grp_no}"> --%>
<%-- 			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller--> --%>
<%-- 			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller--> --%>
<!-- 			     <input type="hidden" name="action"     value="delete"></FORM> -->
<!-- 			</td> -->
			<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front_end/group_detail/group_detail.do" style="margin-bottom: 0px;">
				${groupdetailSvc.getTotalPeople(grouperVO.getGrp_no())}/${grouperVO.grp_personmax}<br>
				<input type="submit" value="加入" ${grouperVO.grp_personmax > groupdetailSvc.getTotalPeople(grouperVO.getGrp_no())?"":"disabled"}/><br>
				
<!-- 				觀察用 -->
				<input type="hidden"   name="grp_no"       size=8 value="${grouperVO.grp_no}">
				<input type="hidden"   name="mb_id"     	 size=8 value="<%= memberVO.getMb_id() %>">
				<input type="hidden"   name="grp_register" size=8 value="1">	
				<br>			
				<input type="hidden" name="requestURL"	 value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
				<input type="hidden" name="whichPage"  	 value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
				<input type="hidden" name="action"       value="insert"></FORM>
			
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front_end/group_follow/group_follow.do" style="margin-bottom: 0px;">
				<input type="submit" value="關注" />
				<img src="<%= request.getContextPath() %>/front_end/group/images/followIcon2un.png" width="75" height="75" border="0" id="follow_img">
				<br> 
<span id="follow">
	<img id="follow_img" src="${isFollow?'images/followIcon2un.png':'images/followIcon2.png'}">
	<label id="label">${isFollow?"已關注":"關注"}</label>
</span>				
				
<!-- 				觀察用 -->
				<input type="hidden"   name="grp_no"       size=8 value="${grouperVO.grp_no}">
				<input type="hidden"   name="mb_id"     	 size=8 value="<%= memberVO.getMb_id() %>">			
				<br>
				<input type="hidden" name="requestURL"	 value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
				<input type="hidden" name="whichPage"  	 value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
				<input type="hidden" name="action"       value="insert"></FORM>
			</td>
			
		</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2_ByCompositeQuery.file" %>
</div>
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
               <jsp:include page="listOneGroup2.jsp" />
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

<!-- <br>本網頁的路徑:<br><b> -->
<%--    <font color=blue>request.getServletPath():</font> <%=request.getServletPath()%><br> --%>
<%--    <font color=blue>request.getRequestURI(): </font> <%=request.getRequestURI()%> </b> --%>
<script> 
	// AJAX 加入

	$(document).ready(function(){
		$("#follow").on("click",function(){
			$.ajax({
				type:"GET",
				url: "mb_follow.do",
				data:{
					"action":"follow",
					"mb_id":"${memberVO.mb_id}",
					"mb_id_followed":"${searchMbVO.mb_id}"
				},
				dataType: "text",
				success: function(data){
					
					if(data == "addFollow"){
						$("#follow_img").attr("src","images/followIcon2un.png");
						$("#label").text("已關注");
					}else if(data == "deleteFollow"){
						$("#follow_img").attr("src","images/followIcon2.png");
						$("#label").text("關注");
					}
				},
				error: function(){alert("AJAX-class發生錯誤囉!")}
			});
		});
	});
	
	
	// AJAX 關注

	$(document).ready(function(){
		$("#follow").on("click",function(){
			$.ajax({
				type:"GET",
				url: "group_follow.do",
				data:{
					"action":"update",
					"grp_no":"${grouperVO.grp_no}",
					"mb_id":"mb_id"
				},
				dataType: "text",
				success: function(data){
					
					if(data == "addGroupfollowToAjax"){
						$("#follow_img").attr("src","images/followIcon2.png");
						$("#label").text("已關注");
					}else if(data == "deleteGroupfollowToAjax"){
						$("#follow_img").attr("src","images/followIcon2.png");
						$("#label").text("關注");
					}
				},
				error: function(){alert("AJAX-class發生錯誤囉!")}
			});
		});
	});
	</script>	 

	<script>
		  src="https://code.jquery.com/jquery-3.5.0.js"
		  integrity="sha256-r/AaFHrszJtwpe+tHyNi/XCfMxYpbsRg2Uqn0x3s2zc="
		  crossorigin="anonymous"
	</script>

</body>

</html>