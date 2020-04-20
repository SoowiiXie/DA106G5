<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.staff.model.*"%>
<%@ page import="com.abl.model.*"%>
<%@ page import="com.auth.model.*"%>
<%@ page import="java.util.*"%>

<%
	// 封裝此頁面(不能直接透過網址列進來)，需要登入管理員並擁有權限
	// 會員帳號若是全數字，會有問題(權限列表打不開)
	StaffService staffSvc = new StaffService();
    List<StaffVO> list = staffSvc.getAll();
    pageContext.setAttribute("list",list);
    
    AbilityService abilitySvc = new AbilityService();
    Map<String, String> abilityMap = abilitySvc.getAllToMap();
    pageContext.setAttribute("abilityMap",abilityMap);
    
    AuthorityService authoritySvc = new AuthorityService();
    pageContext.setAttribute("authoritySvc",authoritySvc);
    
    String action = request.getParameter("action");  // 用來判斷是怎麼回來此頁面，決定alert什麼訊息
    pageContext.setAttribute("action",action);
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
  
  .authorityRow{
  	display:none;
  }
  form{
  	margin:0px;
  }
</style>

<script>
	$(document).ready(function(){
		$(".authorityRow").find("*").addClass("authorityRow");
		<%if("update_authority".equals(action) || "update".equals(action)){%>
			alert("修改成功!");
		<%}else if("insert".equals(action)){%>
			alert("新增成功!");
		<%};%>
	});
	
	function show(data){
		
		
// 		if($(".tag").has("span").length == $(data).has("span").length){
// 			var tag = $(".tag").has("span").length;
// 			var data = $(data).has("span").length;
//  			alert("tag = " + tag);
// 			alert("data = " + data);
// 		}
		
// 		if($(".tag").length && !($(".tag") === $(data))){
// 			$(".tag").find("*").toggleClass("authorityRow");
// 			$(".tag").animate({height:'0px'},"slow",function(){
// 				$(".tag").toggleClass("authorityRow");
// 				$(".tag").removeClass("tag");
// 			})
// 		}
		
// 		$(data).toggleClass("authorityRow");
// 		$(data).animate({height:'50px'},"slow",function(){
// 			$(data).find("*").toggleClass("authorityRow");
// 			$(data).addClass("tag");
// 			$(data).append($("<span id='node'>"));
// 		})
		
		$(data).stop(false,true);
		if($(data).attr("style") == "height: 0px;"){
			$(data).toggleClass("authorityRow");
			$(data).animate({height:'30px'},"slow",function(){
				$(data).find("*").toggleClass("authorityRow");
			})
		}else{
			$(data).find("*").toggleClass("authorityRow");
			$(data).animate({height:'0px'},"slow",function(){
				$(data).toggleClass("authorityRow");
			})
		}
	}
	
</script>
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

<a href="addStaff.jsp"><button>新增管理員</button></a>
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
		
		<tr ${param.staff_id.equals(staffVO.staff_id)?"bgcolor='#CCCCFF'":""}>
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
			<td><button onclick="show(${staffVO.staff_id})">修改</button></td>
		</tr>
		<%-- 權限 --%>
		<tr id="${staffVO.staff_id}" class="authorityRow" style="height: 0px;">
			<td colspan="6">
				<FORM METHOD="post" ACTION="authority.do">
				<c:set var="entrySet" value="${abilityMap.entrySet()}"/> 
				<c:set var="authoritySet" value="${authoritySvc.getOneStaffAuthority(staffVO.staff_id)}"/> 
					
				<c:forEach var="map" items="${entrySet}">
					<label>
					<input type="checkbox" name="ability_no" value="${map.key}" ${authoritySet.contains(map.key)?'checked':''}>
					${map.value}
					</label>&emsp;
				</c:forEach>&emsp;
				<input type="submit" value="送出修改">
				<input type="hidden" name="action"	value="update_authority">
				<input type="hidden" name="staff_id"  value="${staffVO.staff_id}">
				<input type="hidden" name="servletPath" value="<%=request.getServletPath()%>">
				</FORM>
			</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>