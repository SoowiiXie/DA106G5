<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.grouper.model.GrouperVO"%>
<%@ page import="com.grouper.model.*"%>

<%
  GrouperVO grouperVO = (GrouperVO) request.getAttribute("grouperVO");
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
  
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
  #divAll {
	background-image: url('/DA106_G5/front_end/group/webFront/c5.jpg');
	width: 100%;
	height: 100%;
	background-size: cover;
}
</style>

</head>
<body>
<div id="divAll">
	<table id="table-1">
		<tr><td>
			 <h3>揪團資料新增1234566 - addGroup.jsp</h3></td><td>
			 <h4>
				<a href="<%= request.getContextPath() %>/front_end/group/select_page.jsp">
				<img src="<%= request.getContextPath() %>/front_end/group/images/back1.gif" width="100" height="32" border="0">
				回首頁</a>
			 </h4>
		</td></tr>
	</table>
	
	<h3>資料新增:</h3>
	
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
	<table>
		<tr>
			<td>揪團名稱:</td> <td>自動產生</td>
			<td><input type="hidden" name="grp_no" size="45" 
				 value="<%= (grouperVO==null)? "自動產生" : grouperVO.getGrp_no()%>" /></td>
		</tr>
		<tr>
			<td>揪團狀態:</td><td>未滿</td>
			<td><input type="hidden" name="grp_status" size="45" 
				 value="<%= (grouperVO==null)? "1" : grouperVO.getGrp_status()%>" /></td>
		</tr>
		<tr>
			<td>發起人會員編號:</td>
			<td><input type="TEXT" name="mb_id" size="45"
				 value="<%= (grouperVO==null)? "yiwen123" : grouperVO.getMb_id()%>" 
				 class="validate[required,custom[onlyLetter],length[0,100]] feedback-input" placeholder="請輸入發起人會員編號" id="name" /> </td>
		</tr>
			<jsp:useBean id="locationSvc" scope="page" class="com.location.model.LocationService" />
		<tr>
			<td>地點:<font color=red><b>*</b></font></td>
			<td><select size="1" name="loc_no" class="validate[required,custom[onlyLetter],length[0,100]] feedback-input" placeholder="請輸入揪團地點" id="name" /> >
				<c:forEach var="group4locVO" items="${locationSvc.all}">
					<option value="${group4locVO.loc_no}" ${(grouperVO.loc_no==group4locVO.loc_no)? 'selected':'' } >${group4locVO.loc_address}
				</c:forEach>
			</select></td>
		</tr>
		
		<tr>
			<td>報名開始時間:</td>
			<td><input name="grp_applystart" id="a_date1" type="text" 
			class="validate[required,custom[onlyLetter],length[0,100]] feedback-input" placeholder="請選擇報名開始時間" id="name" /> </td>
		</tr>
		
		<tr>
			<td>報名結束時間:</td>
			<td><input name="grp_applyend" id="a_date2" type="text" 
			class="validate[required,custom[onlyLetter],length[0,100]] feedback-input" placeholder="請選擇報名結束時間" id="name" /> </td>
		</tr>
		
		<tr>
			<td>活動開始時間:</td>
			<td><input name="grp_start" id="s_date1" type="text" 
			class="validate[required,custom[onlyLetter],length[0,100]] feedback-input" placeholder="請選擇揪團開始時間" id="name" /> </td>
		</tr>
		
		<tr>
			<td>活動結束時間:</td>
			<td><input name="grp_end" id="s_date2" type="text" 
			class="validate[required,custom[onlyLetter],length[0,100]] feedback-input" placeholder="請選擇揪團報名結束時間" id="name" /> </td>
		</tr>
		
		<tr>
			<td>揪團標題:</td>
			<td><input type="TEXT" name="grp_name" size="45"
				 value="<%= (grouperVO==null)? "測試活動" : grouperVO.getGrp_name()%>" 
				 class="validate[required,custom[onlyLetter],length[0,100]] feedback-input" placeholder="請輸入揪團標題" id="name" /> </td>
		</tr>
		
		<tr>
			<td>揪團內容:</td>
			<td><input type="TEXT" name="grp_content" size="45"
				 value="<%= (grouperVO==null)? "好好玩喔喔喔" : grouperVO.getGrp_content()%>"  
				 class="validate[required,custom[onlyLetter],length[0,100]] feedback-input" placeholder="請輸入揪團內容" id="name" /> </td>
		</tr>
		
		<tr>
			<td>揪團人數上限:</td>
			<td><input type="TEXT" name="grp_personmax" size="45"
				 value="<%= (grouperVO==null)? " " : grouperVO.getGrp_personmax()%>" 
				 class="validate[required,custom[onlyLetter],length[0,100]] feedback-input" placeholder="請輸入揪團人數上限" id="name" /> </td>
		</tr>
		
		<tr>
			<td>揪團人數下限:</td>
			<td><input type="TEXT" name="grp_personmin" size="45"
				 value="<%= (grouperVO==null)? " " : grouperVO.getGrp_personmin()%>" 
				 class="validate[required,custom[onlyLetter],length[0,100]] feedback-input" placeholder="請輸入揪團人數下限" id="name" /> </td>
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
		</tr>
	
	
		<jsp:useBean id="deptSvc" scope="page" class="com.grouper.model.GrouperService" />
	
	
	
	</table>
	<br>
	
	<input type="hidden" name="action" value="insert">
	<input type="submit" value="送出新增" id="button-blue"/></FORM>
	<div class="ease"></div>
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
		       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
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
	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
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
	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s', 
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
	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
<%-- 	   value: '<%=grp_end%>', // value:   new Date(), --%>          
		   onShow:function(){
			   this.setOptions({
			    minDate:$('#s_date1').val()?$('#s_date1').val():false
			   })
			  },
        });
});          
</script>
</html>