<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.grouper.model.GrouperVO"%>
<%@ page import="com.grouper.model.*"%>
<%@ page import="com.mb.model.*"%>

<%
 	 GrouperVO grouperVO = (GrouperVO) request.getAttribute("grouperVO");

	MemberService memberSvc = new MemberService();
	MemberVO memberVO =(MemberVO)session.getAttribute("memberVO");
	pageContext.setAttribute("mb_id", memberVO.getMb_id());
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/front_end/group/webFront/groupIndex.css">
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>揪團資料新增 - addGroup.jsp</title>

<style>

	body{
	font-family: microsoft jhengHei;
	style:#008080;
	}
	table#tableAdd{
		width: 50%;
		margin:0px auto;
		background-color: #87CEEB;
	    border: 2px solid black;
	    text-align: center;
	}
	table#tableAdd h4 {
	    color: red;
	    display: block;
	    margin-bottom: 1px;
	}
	#groupJoin{
	color:#FFB300;
	text-align:left;
	margin:0px auto;
	background-color:rgba(72,72,72,0.4);
	-webkit-border-radius: 7px;
	width: 50%;
	}
</style>

<style>
  
  table, th, td {
    border: 0px solid #0008080;
   
  }
  th, td {
    padding: 1px;
    text-align: left;
  }
  #divAll {
	background-image: url('/DA106_G5/front_end/group/webFront/c8.jpg');
	width: 100%;
/* 	height: 100%; */
	background-size: cover;
}
</style>

</head>


<body>
<div id="divAll">
		<table id="tableAdd">
			<tr><td>
				 <h3>發布揪團</h3></td><td>
				 <h4>
					<a href="<%= request.getContextPath() %>/front_end/index.jsp?pageRun=group/select_page.jsp">
					<img src="<%= request.getContextPath() %>/front_end/group/images/homeIcon.png" width="75" height="75" border="0">
					回到瀏覽揪團</a>
				 </h4>
			</td></tr>
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
	
	<FORM METHOD="post" ACTION="group.do" name="form1">
	<table id="groupJoin">
		<tbody>
			<tr>
				<td>揪團名稱:</td><td>編號自動產生</td>
				<td><input type="hidden" name="grp_no" size="45" 
					 value="<%= (grouperVO==null)? "自動產生" : grouperVO.getGrp_no()%>" /></td>
			</tr>
			<tr>
<!-- 				<td>揪團狀態:</td><td>未滿</td> -->
				<td><input type="hidden" name="grp_status" size="45" 
					 value="<%= (grouperVO==null)? "1" : grouperVO.getGrp_status()%>" /></td>
			</tr>
			<tr>
				<td>發起人會員編號:</td><td><%= memberVO.getMb_id()%></td>
				<td><input type="hidden" name="mb_id" size="45"
					 value="<%= memberVO.getMb_id()%>" /> </td>
			</tr>
				<jsp:useBean id="locationSvc" scope="page" class="com.location.model.LocationService" />
			<tr>
				<td>地點:<font color=red><b>*</b></font></td>
				<td>
					<select size="1" name="loc_no" class="validate[required,custom[onlyLetter],length[0,100]] feedback-input" placeholder="請輸入揪團地點" id="name" /> >
							<option value="">
								<c:forEach var="LocationVO" items="${locationSvc.all}">
								<c:if test="${LocationVO.loc_typeno==1 && LocationVO.loc_status==1}">
								<option value="${LocationVO.loc_no}">${LocationVO.loc_address}
								</c:if>
								</c:forEach> </select><br>
<!-- 							<div> -->
<%-- 								<img alt="" src="<%= request.getContextPath() %>/img/LogoNoBack.png" id="loc_choose_pic" style="display:block; margin:auto; height:5rem;" > --%>
<!-- 							</div> -->
				</td>
			</tr>
			
			<tr>
				<td>報名開始時間:</td>
				<td><input name="grp_applystart" id="a_date1" type="text" 
				class="validate[required,custom[onlyLetter],length[0,100]] feedback-input" placeholder="請選擇報名開始時間" /> </td>
			</tr>
			
			<tr>
				<td>報名結束時間:</td>
				<td><input name="grp_applyend" id="a_date2" type="text" 
				class="validate[required,custom[onlyLetter],length[0,100]] feedback-input" placeholder="請選擇報名結束時間" /> </td>
			</tr>
			
			<tr>
				<td>活動開始時間:</td>
				<td><input name="grp_start" id="s_date1" type="text" 
				class="validate[required,custom[onlyLetter],length[0,100]] feedback-input" placeholder="請選擇揪團開始時間" /> </td>
			</tr>
			
			<tr>
				<td>活動結束時間:</td>
				<td><input name="grp_end" id="s_date2" type="text" 
				class="validate[required,custom[onlyLetter],length[0,100]] feedback-input" placeholder="請選擇揪團報名結束時間" /> </td>
			</tr>
			
			<tr>
				<td>揪團標題:</td>
				<td><input type="TEXT" name="grp_name" size="45"
					 value="<%= (grouperVO==null)? "測試活動" : grouperVO.getGrp_name()%>" 
					 class="validate[required,custom[onlyLetter],length[0,100]] feedback-input" placeholder="請輸入揪團標題" id="grp_name" /> </td>
			</tr>
			
			<tr>
				<td>揪團內容:</td>
				<td><textarea type="textarea" name="grp_content" size="45" rows="2"
					 value="<%= (grouperVO==null)? "好好玩喔喔喔" : grouperVO.getGrp_content()%>"  
					 class="validate[required,custom[onlyLetter],length[0,100]] feedback-input" placeholder="請輸入揪團內容" id="grp_content" /></textarea></td>
			</tr>
			
			<tr>
				<td>揪團人數上限:</td>
				<td><input type="TEXT" name="grp_personmax" size="45"
					 value="<%= (grouperVO==null)? " " : grouperVO.getGrp_personmax()%>" 
					 class="validate[required,custom[onlyLetter],length[0,100]] feedback-input" placeholder="請輸入揪團人數上限" id="grp_personmax" /> </td>
			</tr>
			
			<tr>
				<td>揪團人數下限:</td>
				<td><input type="TEXT" name="grp_personmin" size="45"
					 value="<%= (grouperVO==null)? " " : grouperVO.getGrp_personmin()%>" 
					 class="validate[required,custom[onlyLetter],length[0,100]] feedback-input" placeholder="請輸入揪團人數下限" id="grp_personmin" /> </td>
			</tr>
			
			<tr>
		<!-- 		<td>揪團人數:</td> -->
				<td><input type="hidden" name="grp_personcount" size="45"
					 value="<%= (grouperVO==null)? "1" : grouperVO.getGrp_personcount()%>" 
					 class="validate[required,custom[onlyLetter],length[0,100]] feedback-input" placeholder="請輸入揪團注人數" id="name" /> </td>
			</tr>
			
			
			<tr>
		<!-- 		<td>揪團追蹤人數:</td> -->
				<td><input type="hidden" name="grp_follow" size="45"
					 value="<%= (grouperVO==null)? "1" : grouperVO.getGrp_follow()%>"
					 class="validate[required,custom[onlyLetter],length[0,100]] feedback-input" placeholder="請輸入查詢的關注人數" id="name" /> </td>
			
			
			
			
			<jsp:useBean id="deptSvc" scope="page" class="com.grouper.model.GrouperService" />
			<tr>
	
				<td colspan="2">
				<input type="hidden" name="action" value="insert">
				<input type="submit" value="送出新增" id="button-blue"/>
				<div class="ease"></div>			
				</td>
			</tr>
			</table>
		</tbody>	
		</FORM>
		<button onClick="magic()">神奇小按鈕</button>
	</div>
</body>

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->


<% 
  java.sql.Timestamp grp_applystart = null;
  try {
	  grp_applystart = grouperVO.getGrp_applystart();
   } catch (Exception e) {
	   grp_applystart = new java.sql.Timestamp(System.currentTimeMillis());
   }
%>
<% 
  java.sql.Timestamp grp_applyend = null;
  try {
	  grp_applyend = grouperVO.getGrp_applyend();
   } catch (Exception e) {
	  grp_applyend = new java.sql.Timestamp(System.currentTimeMillis());
   }
%>
<% 
  java.sql.Timestamp grp_start = null;
  try {
	  grp_start = grouperVO.getGrp_start();
   } catch (Exception e) {
	  grp_start = new java.sql.Timestamp(System.currentTimeMillis());
   }
%>
<% 
  java.sql.Timestamp grp_end = null;
  try {
	  grp_end = grouperVO.getGrp_end();
   } catch (Exception e) {
	  grp_end = new java.sql.Timestamp(System.currentTimeMillis());
   }
%>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
// for locale settings
        $.datetimepicker.setLocale('zh');
$(function(){
        $('#a_date1').datetimepicker({
        	theme: '',              //theme: 'dark',
		       timepicker:true,       //timepicker:true,
		       step: 10,				                //step: 60 (這是timepicker的預設間隔60分鐘)
		       format:'Y-m-d H:i',         //format:'Y-m-d H:i:s',
<%-- 		   value: '<%=grp_applystart%>', // value:   new Date(), --%>	       
        	   onShow:function(){
			   this.setOptions({
			    maxDate:$('#a_date2').val()?$('#a_date2').val():false
			   })
			  },			  
        });
        
        $.datetimepicker.setLocale('zh');
        $('#a_date2').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:true,       //timepicker:true,
	       step: 10,				                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d H:i',         //format:'Y-m-d H:i:s',
<%-- 	   value: '<%=grp_applyend%>', // value:   new Date(), --%>
		   onShow:function(){
			   this.setOptions({
			    minDate:$('#a_date1').val()?$('#a_date1').val():false
			   })
			  },
        });
        
        $.datetimepicker.setLocale('zh');
        $('#s_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:true,       //timepicker:true,
	       step: 10,				                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d H:i',         //format:'Y-m-d H:i:s', 
<%-- 	   value: '<%=grp_start%>', // value:   new Date(), --%>
		   onShow:function(){
			   this.setOptions({
				minDate:$('#a_date2').val()?$('#a_date2').val():false,
			    maxDate:$('#s_date2').val()?$('#s_date2').val():false
			   })
			  },
        });
        
        $.datetimepicker.setLocale('zh');
        $('#s_date2').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:true,       //timepicker:true,
	       step: 10,				                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d H:i',         //format:'Y-m-d H:i:s',
<%-- 	   value: '<%=grp_end%>', // value:   new Date(), --%>          
		   onShow:function(){
			   this.setOptions({
			    minDate:$('#s_date1').val()?$('#s_date1').val():false
			   })
			  },
        });
});

	function magic(){
	
	$("#a_date1").val("2020-05-14 08:30");
	$("#a_date2").val("2020-05-15 15:30");
	$("#s_date1").val("2020-05-15 19:30");
	$("#s_date2").val("2020-05-15 20:30");
	$("#grp_name").val("宣揚古羅馬文化");
	$("#grp_content").val("週末下班後來場爭鬥，爭鬥的不是輸贏，而是歷史的洪流中的一絲喘息空間");
	$("#grp_personmax").val("15");
	$("#grp_personmin").val("7");
	}
</script>
</html>