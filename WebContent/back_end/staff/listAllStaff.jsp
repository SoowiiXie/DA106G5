<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.staff.model.*"%>
<%@ page import="com.abl.model.*"%>
<%@ page import="com.auth.model.*"%>
<%@ page import="java.util.*"%>

<%
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
<title>所有管理員資料 - listAllStaff.jsp</title>

<style>
	form{
	  	margin:0px;
	  }
	#wrap{
        text-align:center;
        font-family: 'Mamelon';	
	}
	#allStaff_title{
		
		line-height:10vh;
		font-size: 5vh;
		font-weight:bold;
		letter-spacing: 0.3vw;
		color:#0373f0;
		border-bottom:3px solid #0373f0;
	}
  table {
	width: 60vw;
	background-color: white;
	margin:2vh auto 0px auto;
	
	letter-spacing: 0.1vw;
	font-size: 3vh;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 10px;
    text-align: center;
  }
  
  .authorityRow{
  	display:none;
  	
  }
  .authorityRowFont{
  	font-size: 2.5vh;
  	letter-spacing: 0vw;
  }
  .btn{
  	font-size: 2.5vh;
	color: #fff;
	font-family: 'Mamelon';
		
	background-color: #60A5F3;
	border-radius: 2vh;
 	height: 5vh; 
 	width: 6.5vw; 
  }
  .btnAuthority{
 	color: #333;
  	background-color: #FEFF99;
  }
  .btnAddStaff{
  	position: fixed;
  	top:17vh;
  	right:15vw;
  	
  	height: 6vh;
  	width: 8vw;
  }
</style>

<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<script>
		//  改EL
	$(document).ready(function(){
		$(".authorityRow").find("*").addClass("authorityRow");
		<%if("update_authority".equals(action) || "update".equals(action)){%>
			Swal.fire({
				  icon: 'success',
				  title: '修改成功',
				  showConfirmButton: false,
				  timer: 1500
				})
		<%}else if("insert".equals(action)){%>
			Swal.fire({
			  icon: 'success',
			  title: '新增成功',
			  showConfirmButton: false,
			  timer: 1500
			})
		<%};%>
	});
	
	function show(data){
		
		$(data).stop(false,true);
		if($(data).attr("style") == "height: 0px;"){
			$(data).toggleClass("authorityRow");
			$(data).animate({height:'10vh'},"slow",function(){
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
<div id="wrap">
	

	<!-- 新增管理員 -->
	<form METHOD="POST" action="<%=request.getContextPath()%>/back_end/staff/staff.do">
		<span id="allStaff_title">所有管理員資料</span>
		<button class="btn btnAddStaff" type="submit" name="action" value="for_addStaff">新增管理員</button>
	</form>	

<table>
	<tr>
		<th>管理員ID</th>
		<th>管理員姓名</th>
		<th>加入日期</th>
		<th>Status</th>
		<th>資料修改</th>
		<th>權限修改</th>
	</tr>
	<c:forEach var="staffVO" items="${list}">
		
		<tr ${param.staff_id.equals(staffVO.staff_id)?"bgcolor='#CCCCFF'":""}>
			<td>${staffVO.staff_id}</td>
			<td>${staffVO.staff_name}</td>
			<td><fmt:formatDate value="${staffVO.staff_join}" pattern="yyyy-MM-dd"/></td>
			<td>${staffStatus[staffVO.staff_status]}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/staff/staff.do" style="margin-bottom: 0px;">
			     <input type="hidden" name="staff_id"  value="${staffVO.staff_id}">
			     <button class="btn" type="submit" name="action" value="getOne_For_Update">修改</button>
			  </FORM>
			</td>
			<td><button class="btn" onclick="show(${staffVO.staff_id})">顯示權限</button></td>
		</tr>
		<%-- 權限 --%>
		<tr id="${staffVO.staff_id}" class="authorityRow authorityRowFont" style="height: 0px;">
			<td colspan="6">
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/staff/authority.do">
				<c:set var="authoritySet" value="${authoritySvc.getOneStaffAuthority(staffVO.staff_id)}"/> 
				<c:forEach var="map" items="${abilityMap}">
					<label>
					<input type="checkbox" name="ability_no" value="${map.key}" ${authoritySet.contains(map.key)?'checked':''}>
					${map.value}
					</label>&emsp;
				</c:forEach>
				
				<input type="hidden" name="staff_id"  value="${staffVO.staff_id}">
				<button class="btn btnAuthority" type="submit" name="action" value="update_authority">送出修改</button>
				</FORM>
			</td>
		</tr>
	</c:forEach>
</table>
</div>

</body>
</html>